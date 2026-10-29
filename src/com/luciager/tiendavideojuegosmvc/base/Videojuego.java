package com.luciager.tiendavideojuegosmvc.base;

import java.time.LocalDate;

public abstract class Videojuego {
    private String nombre;
    private String genero;
    private String plataforma;
    private LocalDate fechaCompra;
    private float precio;
    private String observaciones;

    public Videojuego() {

    }

    public Videojuego(String nombre, String genero, String plataforma, LocalDate fechaCompra, float precio, String observaciones) {
        this.nombre = nombre;
        this.genero = genero;
        this.plataforma = plataforma;
        this.fechaCompra = fechaCompra;
        this.precio = precio;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
