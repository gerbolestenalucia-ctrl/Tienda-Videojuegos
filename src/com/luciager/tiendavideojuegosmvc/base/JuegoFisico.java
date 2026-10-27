package com.luciager.tiendavideojuegosmvc.base;

import java.time.LocalDate;

public class JuegoFisico extends Videojuego {
    private int unidadEnStock;

    public JuegoFisico() {
        super();
    }

    public JuegoFisico(String nombre, float precio, String genero, String plataforma, LocalDate fechaCompra, int unidadEnStock) {
        super(nombre, precio, genero, plataforma, fechaCompra);
        this.unidadEnStock = unidadEnStock;
    }

    public int getUnidadEnStock() {
        return unidadEnStock;
    }

    public void setUnidadEnStock(int unidadEnStock) {
        this.unidadEnStock = unidadEnStock;
    }

    @Override
    public String toString() {
        return "JuegoFisico: " + getNombre() + " " + getPrecio() + " " + getPlataforma();
    }
}
