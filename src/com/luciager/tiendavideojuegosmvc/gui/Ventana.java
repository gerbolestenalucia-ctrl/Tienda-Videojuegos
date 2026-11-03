package com.luciager.tiendavideojuegosmvc.gui;

import com.github.lgooddatepicker.components.DatePicker;
import com.luciager.tiendavideojuegosmvc.base.Videojuego;
import sun.awt.windows.WPrinterJob;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
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
    public JButton eliminarButton;
    public JTextField buscarTxt;
    public JButton buscarButton;
    public JButton editarButton;

    //creado por mi
    public JFrame frame;
    public DefaultListModel<Videojuego> dlmVideojuego;

    public Ventana() {
        //titulo
        frame = new JFrame("VideojuegosPro");
        frame.setContentPane(panel1);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        initComponents();

        //tamaño de la ventana
        frame.setSize(500, 630);

        //borde del panel
        panel1.setBorder(new EmptyBorder(12, 12, 12, 12));

        //color de los botones
        nuevoButton.setBackground(Color.BLUE);
        exportarButton.setBackground(Color.BLUE);
        importarButton.setBackground(Color.BLUE);
        buscarButton.setBackground(Color.BLUE);
        eliminarButton.setBackground(Color.BLUE);
        editarButton.setBackground(Color.BLUE);

        //color de la letra
        nuevoButton.setForeground(Color.WHITE);
        importarButton.setForeground(Color.WHITE);
        exportarButton.setForeground(Color.WHITE);
        buscarButton.setForeground(Color.WHITE);
        eliminarButton.setForeground(Color.WHITE);
        editarButton.setForeground(Color.WHITE);

        //fuente de los botones
        nuevoButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
        exportarButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
        importarButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
        buscarButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
        eliminarButton.setFont(new Font("Sans Serif", Font.BOLD, 15));
        editarButton.setFont(new Font("Sans Serif", Font.BOLD, 15));

        //fondo
        panel1.setBackground(new Color(173, 216, 230));
    }

    public void initComponents() {
        dlmVideojuego = new DefaultListModel<Videojuego>();
        list1.setModel(dlmVideojuego);

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

        estadoComboBox.addItem("Nuevo");
        estadoComboBox.addItem("Segunda Mano");

        juegoFisicoRadioButton.setActionCommand("JuegoFisico");
        juegoDigitalRadioButton.setActionCommand("JuegoDigital");

        generoComboBox.setSelectedIndex(-1);
        estadoComboBox.setSelectedIndex(-1);
    }
}