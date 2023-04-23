/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.services;

import cl.ucn.disc.pa.bibliotech.model.Libro;
import cl.ucn.disc.pa.bibliotech.model.Socio;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import edu.princeton.cs.stdlib.StdOut;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The Sistema.
 *
 * @author Programacion Avanzada.
 */
public final class Sistema {

    /**
     * Procesador de JSON.
     */
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    /**
     * The list of Socios.
     */
    private Socio[] socios;

    /**
     * The list of Libros.
     */
    private Libro[] libros;

    /**
     * Socio en el sistema.
     */
    private Socio socio;

    /**
     * The Sistema.
     */
    public Sistema() throws IOException {

        // no hay socio registrado.
        this.socios = new Socio[0];
        this.libros = new Libro[0];
        this.socio = null;

        // carga de los socios y libros.
        try {
            this.cargarInformacion();
        } catch (FileNotFoundException ex) {
            // no se encontraron datos, se agregar los por defecto.

            // creo un socio
            this.socios = Utils.append(this.socios, new Socio("John", "Doe", "john.doe@ucn.cl", 1, "john123"));

            // creo un libro y lo agrego al arreglo de libros.
            this.libros = Utils.append(this.libros, new Libro("1491910771", "Head First Java: A Brain-Friendly Guide", " Kathy Sierra", "Programming Languages", 0, 0, 0, 1));

            // creo otro libro y lo agrego al arreglo de libros.
            this.libros = Utils.append(this.libros, new Libro("1491910772", "Effective Java", "Joshua Bloch", "Programming Languages", 0, 0, 0, 1));

        } finally {
            // guardo la información.
            this.guardarInformacion();
        }

    }

    /**
     * Activa (inicia sesion) de un socio en el sistema.
     *
     * @param numeroDeSocio a utilizar.
     * @param contrasenia   a validar.
     */
    public void iniciarSession(final int numeroDeSocio, final String contrasenia) {

        // el número de socio siempre es positivo.
        if (numeroDeSocio <= 0) {
            throw new IllegalArgumentException("El numero de socio no es valido!");
        }

        for (Socio socio : socios) { //Objeto - Instancia - Lista
            if (socio.getNumeroDeSocio() == numeroDeSocio && socio.getContrasenia().equals(contrasenia)) {
                this.socio = socio;
                break;
            }
        }
    }

    /**
     * Cierra la session del Socio.
     */
    public void cerrarSession() {
        this.socio = null;
    }

    /**
     * Metodo que mueve un libro de los disponibles y lo ingresa a un Socio.
     *
     * @param isbn del libro a prestar.
     */
    public void realizarPrestamoLibro(final String isbn) throws IOException {
        // el socio debe estar activo.
        if (this.socio == null) {
            throw new IllegalArgumentException("Socio no se ha logeado!");
        }

        // busco el libro.
        Libro libro = this.buscarLibro(isbn);

        // si no lo encontre, lo informo.
        if (libro == null || libro.getDisponible() == 0) {
            StdOut.println("Libro con isbn " + isbn + " no existe o no se encuentra disponible.");
        } else {
            // agrego el libro al socio.
            this.socio.agregarLibro(libro);

            if (libro.getIsbn().equals(isbn)) {
                libro.setDisponible(0);
            }

            // se actualiza la información de los archivos
            this.guardarInformacion();
        }
    }

    /**
     * Obtiene un String que representa el listado completo de libros disponibles.
     *
     * @return the String con la informacion de los libros disponibles.
     */
    public String obtenerCatalogoLibros() {

        StringBuilder sb = new StringBuilder();
        for (Libro libro : this.libros) {
            sb.append("Titulo    : ").append(libro.getTitulo()).append("\n");
            sb.append("Autor     : ").append(libro.getAutor()).append("\n");
            sb.append("ISBN      : ").append(libro.getIsbn()).append("\n");
            sb.append("Categoria : ").append(libro.getCategoria()).append("\n");
            sb.append("Calificacion : ").append(String.format("%.1f", libro.getPromedioCalif())).append("\n");
            sb.append("Disponibilidad : ").append(libro.getDisponible()).append("\n");
            sb.append("\n");
        }

        return sb.toString();
    }

    /**
     * Metodo que busca un libro en los libros disponibles.
     *
     * @param isbn a buscar.
     * @return el libro o null si no fue encontrado.
     */
    private Libro buscarLibro(final String isbn) {
        // recorro el arreglo de libros.
        for (Libro libro : this.libros) {
            // si lo encontre, retorno el libro.
            if (libro.getIsbn().equals(isbn)) {
                return libro;
            }
        }
        // no lo encontre, retorno null.
        return null;
    }


    /**
     * Lee los archivos libros.json y socios.json.
     *
     * @throws FileNotFoundException si alguno de los archivos no se encuentra.
     */
    private void cargarInformacion() throws FileNotFoundException {

        // trato de leer los socios y los libros desde el archivo.
        this.socios = GSON.fromJson(new FileReader("socios.json"), Socio[].class);
        this.libros = GSON.fromJson(new FileReader("libros.json"), Libro[].class);
    }

    /**
     * Guarda los arreglos libros y socios en los archivos libros.json y socios.json.
     *
     * @throws IOException en caso de algun error.
     */
    private void guardarInformacion() throws IOException {

        // guardo los socios.
        try (FileWriter writer = new FileWriter("socios.json")) {
            GSON.toJson(this.socios, writer);
        }

        // guardo los libros.
        try (FileWriter writer = new FileWriter("libros.json")) {
            GSON.toJson(this.libros, writer);
        }
    }

    /**
     * Obtiene ciertos parametros de una instancia del objeto Socio.
     *
     * @return El nombre completo y el Correo
     */
    public String obtenerDatosSocioLogeado() {
        if (this.socio == null) {
            throw new IllegalArgumentException("No hay un Socio logeado");
        }

        return "Nombre: " + this.socio.getNombreCompleto() + "\n"
                + "Correo Electronico: " + this.socio.getCorreoElectronico();
    }

    /**
     * Método para asignar una clasificación a los libros.
     *
     * @param isbnLibro -> ISBN del Libro
     * @param calif     -> Calificacion que le da el usuario al Libro.
     * @throws IOException .
     */
    public void calificarLibro(final String isbnLibro, double calif) throws IOException {
        for (Libro libro : this.libros) {
            // si lo encontre, retorno el libro.
            if (libro.getIsbn().equals(isbnLibro)) {
                double califInicial = libro.getSumCalificacion();
                // Verifico si la calificacion inicial del libro es 0 o diferente, si es 0 la calificacion del usuario será la seteada en el sumador, +1 en el contador y seteada en el promedio.
                if (califInicial == 0) {
                    libro.setSumCalificacion(calif);
                    libro.setNumCalificaciones(1);
                    libro.setPromedioCalif(calif);
                } else { // Si es diferente de 0, se suman las calificaciones, se agrega 1 al contador de las calificaciones totales del libro y se saca el promedio total del Libro.
                    double sumador = califInicial + calif;
                    libro.setSumCalificacion(sumador);
                    double contador = 1 + libro.getNumCalificaciones();
                    libro.setNumCalificaciones((int) contador);
                    double promedio = (sumador / contador);
                    libro.setPromedioCalif(promedio);
                }
            }
        }
        this.guardarInformacion();
    }

    /**
     * @param nombre -> Es el nombre nuevo que da el usuario.
     * @throws IOException .
     */
    public void cambiareIlNome(String nombre) throws IOException { //Nombre del metodo en Italiano. (cambiarElNombre)
        if (this.socio == null) {
            throw new IllegalArgumentException("No hay un Socio logeado");
        }

        this.socio.setNombre(nombre);
        this.guardarInformacion();
    }

    /**
     * @param apellido -> Es el apellido nuevo que da el usuario.
     * @throws IOException .
     */
    public void cambiareIlCognome(String apellido) throws IOException { //Nombre del metodo en Italiano. (cambiarElApellido)
        if (this.socio == null) {
            throw new IllegalArgumentException("No hay un Socio logeado");
        }

        this.socio.setApellido(apellido);
        this.guardarInformacion();
    }

    /**
     * @param correo -> Es el correo nuevo que da el usuario.
     * @throws IOException .
     */
    public void cambiareIlEmail(String correo) throws IOException { //Nombre del metodo en Italiano. (cambiarElCorreo)
        if (this.socio == null) {
            throw new IllegalArgumentException("No hay un Socio logeado");
        }

        this.socio.setCorreoElectronico(correo);
        this.guardarInformacion();
    }
}
