package com.luciager.tiendavideojuegosmvc;

import com.luciager.tiendavideojuegosmvc.gui.VideojuegoControlador;
import com.luciager.tiendavideojuegosmvc.gui.VideojuegoModelo;

public class Principal {
    public static void main(String[] args) {
        Ventana vista = new Ventana();
        VideojuegoModelo modelo = new VideojuegoModelo();
        VideojuegoControlador controlador = new VideojuegoControlador(vista, modelo);
    }
}
