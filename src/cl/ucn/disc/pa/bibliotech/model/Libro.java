/*
 * Copyright (c) 2023. Programacion Avanzada, DISC, UCN.
 */

package cl.ucn.disc.pa.bibliotech.model;

/**
 * Clase que representa un Libro.
 *
 * @author Programacion Avanzada.
 */
public final class Libro {

    /**
     * El ISBN.
     */
    private final String isbn;

    /**
     * El Titulo.
     */
    private final String titulo;

    /**
     * El/La Autor/a.
     */
    private final String autor;

    /**
     * La Categoria
     */
    private final String categoria;

    /**
     * Calificacion -> Sumador de todas las clasificaciones dadas a una instancia de un libro.
     * numCalificaciones -> Número total de clasificaciones dadas a una instancia de un libro.
     * promedioCalif -> Promedio total  de  clasificaciones dadas a una instancia de un libro.
     */
    private double sumCalificacion;
    private double numCalificaciones;
    private double promedioCalif;

    /**
     * La disponibilidad
     */
    private int disponible;

    /**
     * @param isbn          recibe el ISBN del libro
     * @param titulo        recibe el titulo del libro.
     * @param autor         recibe el autor del libro.
     * @param categoria     recibe la categoria del libro.
     * @param calif         recibe el sumador de todas las clasificaciones del libro.
     * @param contador      recibe el contador de clasificaciones del libro.
     * @param promedioCalif recibe el promedio de clasificaciones del libro.
     * @param disponible    recibe la disponibilidad que tiene el libro.
     */
    public Libro(final String isbn, final String titulo, final String autor, final String categoria, double calif, double contador, double promedioCalif, int disponible) {
        if (isbn == null || isbn.length() == 0) {
            throw new IllegalArgumentException("ISBN no valido!");
        }
        this.isbn = isbn;

        if (titulo == null || titulo.length() == 0) {
            throw new IllegalArgumentException("Titulo no valido!");
        }
        this.titulo = titulo;

        if (autor == null || autor.length() == 0) {
            throw new IllegalArgumentException("Autor no valido!");
        }
        this.autor = autor;

        if (categoria == null || categoria.length() == 0) {
            throw new IllegalArgumentException("Autor no valido!");
        }
        this.categoria = categoria;

        this.sumCalificacion = calif;

        this.disponible = disponible;

        this.numCalificaciones = contador;

        if (promedioCalif < 0 || promedioCalif > 5) {
            throw new IllegalArgumentException("Calificacion no valida!");
        }
        this.promedioCalif = promedioCalif;
    }

    /**
     * @return el ISBN.
     */
    public String getIsbn() {
        return this.isbn;
    }

    /**
     * @return el titulo.
     */
    public String getTitulo() {
        return this.titulo;
    }

    /**
     * @return el autor
     */
    public String getAutor() {
        return this.autor;
    }

    /**
     * @return la categoria.
     */
    public String getCategoria() {
        return this.categoria;
    }

    /**
     * @return la calificacion
     */
    public double getSumCalificacion() {
        return this.sumCalificacion;
    }

    /**
     * @param calif setea el sumador de las clasificaciones del libro.
     */
    public void setSumCalificacion(double calif) {
        this.sumCalificacion = calif;
    }

    /**
     * @return el número de calificaiones
     */
    public double getNumCalificaciones() {
        return numCalificaciones;
    }

    /**
     * @param contador setea el contador de calificaciones del libro.
     */
    public void setNumCalificaciones(int contador) {
        this.numCalificaciones = contador;
    }

    /**
     * @return el promedio de calificaciones
     */
    public double getPromedioCalif() {
        return promedioCalif;
    }

    /**
     * @param promedioCalif setea el promedio de calificaciones del libro.
     */
    public void setPromedioCalif(double promedioCalif) {
        this.promedioCalif = promedioCalif;
    }

    /**
     * @return la disponibilidad
     */
    public int getDisponible() {
        return this.disponible;
    }

    /**
     * @param disponible setea la disponibilidad del Libro.
     */
    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
