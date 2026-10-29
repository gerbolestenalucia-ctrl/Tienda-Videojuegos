package com.luciager.tiendavideojuegosmvc.base;

import java.time.LocalDate;

public class JuegoFisico extends Videojuego {
    private int numDiscos;
    private String estado;

    public JuegoFisico() {
        super();
    }

    public JuegoFisico(String nombre, String genero, String plataforma, LocalDate fechaCompra, float precio, String observaciones, int numDiscos, String estado) {
        super(nombre, genero, plataforma, fechaCompra, precio, observaciones);
        this.numDiscos = numDiscos;
        this.estado = estado;
    }

    public int getNumDiscos() {
        return numDiscos;
    }

    public void setNumDiscos(int numDiscos) {
        this.numDiscos = numDiscos;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


    @Override
    public String toString() {
        return "JuegoFisico: " + getNombre() + " " + getPlataforma() + " " + getPrecio() + " " + getObservaciones();
    }
}
