package com.luciager.tiendavideojuegosmvc.base;

import java.time.LocalDate;

public class JuegoDigital extends Videojuego {
    private double tamanoDescarga;

    public JuegoDigital() {
        super();
    }

    public JuegoDigital(String nombre, float precio, String genero, String plataforma, LocalDate fechaCompra, double tamanoDescarga) {
        super(nombre, precio, genero, plataforma, fechaCompra);
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
        return "JuegoDigital: " + getNombre() + " " + getPrecio() + " " + getPlataforma();
    }
}
