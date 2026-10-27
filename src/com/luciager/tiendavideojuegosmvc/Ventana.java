package com.luciager.tiendavideojuegosmvc;

import com.github.lgooddatepicker.components.DatePicker;
import com.luciager.tiendavideojuegosmvc.base.Videojuego;

import javax.swing.*;

public class Ventana {
    private JPanel panel1;
    private JRadioButton juegoFisicoRadioButton;
    private JRadioButton juegoDigitalRadioButton;
    private JTextField nombreTxt;
    private JTextField precioTxt;
    private JTextField generoTxt;
    private JTextField plataformaTxt;
    private JLabel stockDescargaLbl;
    private JTextField stockDescargaTxt;
    private JButton nuevoButton;
    private JButton exportarButton;
    private JButton importarButton;
    private JList list1;
    private DatePicker fechaCompraDPicker;

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
    }

}
