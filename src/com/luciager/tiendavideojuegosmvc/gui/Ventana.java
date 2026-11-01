package com.luciager.tiendavideojuegosmvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.luciager.tiendavideojuegosmvc.base.Videojuego;
import sun.awt.windows.WPrinterJob;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class Ventana {
    private JPanel panel1;
    public JRadioButton juegoFisicoRadioButton;
    public JRadioButton juegoDigitalRadioButton;
    public JTextField nombreTxt;
    public JTextField plataformaTxt;
    public JLabel estadoLbl;
    public JButton nuevoButton;
    public JButton exportarButton;
    public JButton importarButton;
    public JList list1;
    public DatePicker fechaCompraDPicker;
    public JLabel numDiscosDescargaLbl;
    public JTextArea observacionesTxtArea;
    public JComboBox estadoComboBox;
    public JComboBox generoComboBox;
    public JTextField numDiscosDescargaTxt;
    public JTextField precioTxt;

    //creado por mi
    public JFrame frame;
    public DefaultListModel<Videojuego> dlmVideojuego;

    public Ventana() {
        frame = new JFrame("VideojuegosMVC");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();
    }

    public void initComponents() {
        dlmVideojuego = new DefaultListModel<Videojuego>();
        list1.setModel(dlmVideojuego);

        generoComboBox.addItem("Elegir un género: ");
        generoComboBox.addItem("Acción");
        generoComboBox.addItem("Aventura");
        generoComboBox.addItem("RPG");
        generoComboBox.addItem("Survival Horror");
        generoComboBox.addItem("Estrategia");
        generoComboBox.addItem("Deportes");
        generoComboBox.addItem("Carreras");
        generoComboBox.addItem("Puzzle");
        generoComboBox.addItem("Mundo Abierto");
        generoComboBox.addItem("Battle Royale");
        generoComboBox.addItem("Lucha");

        estadoComboBox.addItem("Elegir un estado: ");
        estadoComboBox.addItem("Nuevo");
        estadoComboBox.addItem("Segunda Mano");

        juegoFisicoRadioButton.setActionCommand("JuegoFisico");
        juegoDigitalRadioButton.setActionCommand("JuegoDigital");
    }

}
