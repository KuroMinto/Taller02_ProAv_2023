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
     * La calificacion
     */
    private double calificacion;
    private double numCalificaciones;
    private double promedioCalif;

    private int disponible;

    /**
     * El constructor
     *
     * @param isbn      del libro.
     * @param titulo    del libro.
     * @param autor     del libro
     * @param categoria del libro.
     * @param calif     del libro.
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

        if (calificacion < 0 || calificacion > 5) {
            throw new IllegalArgumentException("Calificacion no valida!");
        }
        this.calificacion = calif;

        if (disponible < 0 || disponible > 1) {
            throw new IllegalArgumentException("Disponibilidad no valida!");
        }
        this.disponible = disponible;

        if (numCalificaciones < 0 || numCalificaciones > 5) {
            throw new IllegalArgumentException("Calificacion no valida!");
        }
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
    public double getCalificacion() {
        return this.calificacion;
    }

    public void setCalificacion(Double calif) {
        this.calificacion = calif;
    }

    public double getNumCalificaciones() {
        return numCalificaciones;
    }

    public void setNumCalificaciones(int contador) {
        this.numCalificaciones = contador;
    }

    public double getPromedioCalif() {
        return promedioCalif;
    }

    public void setPromedioCalif(double promedioCalif) {
        this.promedioCalif = promedioCalif;
    }

    /**
     * @return la disponibilidad
     */
    public int getDisponible() {
        return this.disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }
}
