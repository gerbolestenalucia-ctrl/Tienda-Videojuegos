package com.luciager.tiendavideojuegosmvc.base;

import java.time.LocalDate;

public abstract class Videojuego {
    private String nombre;
    private float precio;
    private String genero;
    private String plataforma;
    private LocalDate fechaCompra;

    public Videojuego() {

    }

    public Videojuego(String nombre, float precio, String genero, String plataforma, LocalDate fechaCompra) {
        this.nombre = nombre;
        this.precio = precio;
        this.genero = genero;
        this.plataforma = plataforma;
        this.fechaCompra = fechaCompra;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
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
}
