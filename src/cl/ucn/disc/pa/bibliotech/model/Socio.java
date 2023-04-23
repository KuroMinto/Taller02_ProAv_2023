/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

import cl.ucn.disc.pa.bibliotech.services.Utils;
import edu.princeton.cs.stdlib.StdOut;

/**
 * Clase que representa a un Socio.
 *
 * @author Programacion Avanzada.
 */
public final class Socio {

    /**
     * Número maximo de libros que puede tener el Socio.
     */
    private static final int NUMERO_LIBROS_MAXIMO = 5;

    /**
     * Nombre del socio.
     */
    private String nombre;

    /**
     * Apellido del socio.
     */
    private String apellido;

    /**
     * Email del socio.
     */
    private String correoElectronico;

    /**
     * Numero del socio.
     */
    private final int numeroDeSocio;

    /**
     * Contrasenia del socio.
     */
    private final String contrasenia;

    /**
     * Libros que el Socio tiene en prestamo (maximo 10).
     */
    private final Libro[] librosEnPrestamo;

    /**
     * Constructor del objeto Socio
     *
     * @param nombre            del socio.
     * @param apellido          del socio.
     * @param correoElectronico del socio.
     * @param numeroDeSocio     del socio.
     * @param contrasenia       del socio.
     */
    public Socio(String nombre, String apellido, String correoElectronico, int numeroDeSocio, String contrasenia) {

        // Verificacion del nombre
        if (nombre == null) {
            throw new IllegalArgumentException("El nombre no existe.");
        } else {
            this.nombre = nombre;
        }

        // Verificacion del Apellido
        if (apellido == null) {
            throw new IllegalArgumentException("El apellido no existe.");
        } else {
            this.apellido = apellido;
        }

        // metodo estatico para validacion de email.
        Utils.validarEmail(correoElectronico);
        this.correoElectronico = correoElectronico;

        // Verificacion del Número de Socio
        if (numeroDeSocio == -1) {
            throw new IllegalArgumentException("El número de socio no existe.");
        } else {
            this.numeroDeSocio = numeroDeSocio;
        }

        // Verificacion de la Contraseña
        if (contrasenia == null) {
            throw new IllegalArgumentException("La contraseña es incorrecta.");
        } else {
            this.contrasenia = contrasenia;
        }

        this.librosEnPrestamo = new Libro[10];
    }

    /**
     * @return el nombre del Socio.
     */
    public String getNombre() {
        return this.nombre;
    }

    /**
     * @return el apellido del Socio.
     */
    public String getApellido() {
        return this.apellido;
    }

    /**
     * @param nombre setea el nombre del socio.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param apellido setea el apellido del socio.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /**
     * @return el nombre completo del Socio.
     */
    public String getNombreCompleto() {
        return this.nombre + " " + this.apellido;
    }

    /**
     * @return el correo electronico del Socio.
     */
    public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    /**
     * @param correoElectronico setea el correo del socio.
     */
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    /**
     * @return el numero del Socio.
     */
    public int getNumeroDeSocio() {
        return this.numeroDeSocio;
    }

    /**
     * @return la contrasenia del Socio.
     */
    public String getContrasenia() {
        return this.contrasenia;
    }

    /**
     * Agrega un libro en prestamo al Socio.
     *
     * @param libro a agregar.
     */
    public void agregarLibro(final Libro libro) {
        // validacion
        if (this.librosEnPrestamo.length == NUMERO_LIBROS_MAXIMO) {
            throw new IllegalArgumentException("El Socio ya tiene la maxima cantidad de libros en prestamo: " + NUMERO_LIBROS_MAXIMO);
        }
        // agrego el libro, recorro el arreglo de librosEnPrestamo hasta encontrar un null, luego procede a agregar los valores de la instancia del libro al array.
        for (int i = 0; i < librosEnPrestamo.length; i++) {
            if (librosEnPrestamo[i] == null) {
                this.librosEnPrestamo[i] = libro;
                StdOut.println("Prestamo del libro realizado.");
                break;
            }
        }
    }
}