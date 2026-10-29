package com.luciager.tiendavideojuegosmvc.base;

import java.time.LocalDate;

public class JuegoDigital extends Videojuego {
    private double tamanoDescarga;

    public JuegoDigital() {
        super();
    }

    public JuegoDigital(String nombre, String genero, String plataforma, LocalDate fechaCompra, float precio, String observaciones, double tamanoDescarga) {
        super(nombre, genero, plataforma, fechaCompra, precio, observaciones);
        this.tamanoDescarga = tamanoDescarga;
    }

    public double getTamanoDescarga() {
        return tamanoDescarga;
    }

    public void setTamanoDescarga(double tamanoDescarga) {
        this.tamanoDescarga = tamanoDescarga;
    }

    @Override
    public String toString() {
        return "JuegoDigital: " + getNombre() + " " + getPlataforma() + " " + getPrecio() + " " + getObservaciones();
    }
}
