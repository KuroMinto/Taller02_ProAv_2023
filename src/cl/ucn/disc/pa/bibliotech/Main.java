/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech;

import cl.ucn.disc.pa.bibliotech.services.Sistema;
import edu.princeton.cs.stdlib.StdIn;
import edu.princeton.cs.stdlib.StdOut;

import java.io.IOException;
import java.util.Objects;

/**
 * The Main.
 *
 * @author Programacion Avanzada.
 */
public final class Main {
    static int numeroSocio;
    static String contrasenia;


    /**
     * The main.
     *
     * @param args to use.
     * @throws IOException en caso de un error.
     */
    public static void main(final String[] args) throws Exception {

        // inicializacion del sistema.
        Sistema sistema = new Sistema();

        StdOut.println(sistema.obtenerCatalogoLibros());

        String opcion = null;
        while (!Objects.equals(opcion, "2")) {

            StdOut.println("""
                    [*] Bienvenido a BiblioTech [*]
                                    
                    [1] Iniciar Sesion
                    [2] Salir
                    """);
            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> iniciarSesion(sistema);
                case "2" -> StdOut.println("¡Hasta Pronto!");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Inicia la sesion del Socio en el Sistema.
     *
     * @param sistema a utilizar.
     */
    private static void iniciarSesion(final Sistema sistema) throws Exception {
        StdOut.println("[*] Iniciar sesion en BiblioTech [*]");
        StdOut.print("Ingrese su numero de socio: ");
        numeroSocio = StdIn.readInt();

        StdOut.print("Ingrese su contrasenia: ");
        contrasenia = StdIn.readString();

        // intento el inicio de session
        try {
            sistema.iniciarSession(numeroSocio, contrasenia);
        } catch (IllegalArgumentException ex) {
            StdOut.println("Ocurrio un error: " + ex.getMessage());
            return;
        }

        // mostrar menu principal
        menuPrincipal(sistema);
    }

    /**
     * Método para abrir el menu principal.
     *
     * @param sistema .
     * @throws Exception .
     */
    private static void menuPrincipal(final Sistema sistema) throws Exception {
        String opcion = null;
        while (!Objects.equals(opcion, "4")) {
            StdOut.println("""
                    [*] BiblioTech [*]
                                        
                    [1] Prestamo de un libro
                    [2] Editar información
                    [3] Calificar libro
                                        
                    [4] Cerrar sesion
                    """);

            StdOut.print("Escoja una opcion: ");
            opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> menuPrestamo(sistema);
                case "2" -> editarInformacion(sistema);
                case "3" -> calificarLibro(sistema);
                case "4" -> sistema.cerrarSession();
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Método para abrir el menú de prestamo de libros.
     *
     * @param sistema .
     */
    private static void menuPrestamo(Sistema sistema) {
        StdOut.println("[*] Préstamo de un Libro [*]");
        StdOut.println(sistema.obtenerCatalogoLibros());

        StdOut.print("Ingrese el ISBN del libro a tomar prestado: ");
        String isbn = StdIn.readString();

        try {
            sistema.realizarPrestamoLibro(isbn);
        } catch (IOException ex) {
            StdOut.println("Ocurrio un error, intente nuevamente: " + ex.getMessage());
        }
    }

    /**
     * Método para abrir el menú de edición de información.
     *
     * @param sistema .
     * @throws IOException .
     */
    private static void editarInformacion(Sistema sistema) throws IOException {

        String opcion = null;
        while (!Objects.equals(opcion, "3")) {

            StdOut.println("[*] Editar Perfil [*]");
            StdOut.println(sistema.obtenerDatosSocioLogeado());
            StdOut.println("""               
                    [1] Editar Nombre y/o Apellido
                    [2] Editar Correo
                                        
                    [3] Volver atrás
                    """);
            StdOut.print("Escoja una opción: ");
            opcion = StdIn.readString();

            switch (opcion) {
                case "1" -> cambiarNombreOApellido(sistema);
                case "2" -> editarCorreo(sistema);
                case "3" -> StdOut.println("Volviendo al menú anterior...");
                default -> StdOut.println("Opcion no valida, intente nuevamente");
            }
        }
    }

    /**
     * Método para Calificar un Libro.
     *
     * @param sistema .
     * @throws IOException .
     */
    private static void calificarLibro(Sistema sistema) throws IOException {
        StdOut.println("[*] Calificar un Libro [*]");
        StdOut.println(sistema.obtenerCatalogoLibros());
        StdOut.print("Ingrese el ISBN del libro a calificar: ");
        String isbnLibro = StdIn.readString();
        StdOut.print("Ingrese la calificacion [0 - 5]: ");
        double calif = StdIn.readDouble();
        if (calif < 0 || calif > 5) {
            StdOut.println("\n Error al calificar!!! \n");
        } else {
            sistema.calificarLibro(isbnLibro, calif);
            StdOut.println("\n Libro calificado con exito. \n");
        }
    }

    /**
     * Método para cambiar el Nombre o el Apellido del Socio.
     *
     * @param sistema .
     * @throws IOException .
     */
    private static void cambiarNombreOApellido(Sistema sistema) throws IOException {

        StdOut.println("[*] Editar Nombre y/ Apellido [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.println("""               
                [1] Editar Nombre
                [2] Editar Apellido
                                    
                [3] Volver atrás
                """);
        StdOut.print("Escoja una opción: ");
        String opcion = StdIn.readString();

        switch (opcion) {
            case "1":
                StdOut.print("Ingrese su nuevo nombre: ");
                String nombre = StdIn.readString();
                sistema.cambiareIlNome(nombre);
                break;
            case "2":
                StdOut.print("Ingrese su nuevo apellido: ");
                String apellido = StdIn.readString();
                sistema.cambiareIlCognome(apellido);
                break;
            case "3":
                StdOut.println("Volviendo al menú anterior...");
            default:
                StdOut.println("Opcion no valida, intente nuevamente");
        }
    }

    /**
     * Método para Editar el Correo del Socio.
     *
     * @param sistema .
     * @throws IOException .
     */
    private static void editarCorreo(Sistema sistema) throws IOException {
        StdOut.println("[*] Editar Correo Electronico [*]");
        StdOut.println(sistema.obtenerDatosSocioLogeado());
        StdOut.print("Ingrese el nuevo email: ");
        String correo = StdIn.readString();
        sistema.cambiareIlEmail(correo);
        StdOut.println("Nuevos datos: " +
                sistema.obtenerDatosSocioLogeado());
    }
}
