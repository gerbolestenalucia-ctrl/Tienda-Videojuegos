package com.luciager.tiendavideojuegosmvc.gui;

import com.luciager.tiendavideojuegosmvc.base.JuegoFisico;
import com.luciager.tiendavideojuegosmvc.base.JuegoDigital;
import com.luciager.tiendavideojuegosmvc.base.Videojuego;
import org.xml.sax.SAXException;
import util.Util;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.Properties;

public class VideojuegoControlador implements ActionListener, ListSelectionListener, WindowListener{
    private Ventana vista;
    private VideojuegoModelo modelo;
    private File ultimaRutaExportada;

    public VideojuegoControlador(Ventana vista, VideojuegoModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;

        try {
            cargarDatosConfiguracion();
        } catch (IOException e) {
            System.out.println("No se encontró el archivo" + e.getMessage());
        }

        //si no se pone no van los botones
        addActionListener(this);
        addListSelectionListener(this);
        addWindowListener(this);

    }

    //sirve para validar el formulario
    private boolean camposVacios() {
        if (vista.numDiscosDescargaTxt.getText().isEmpty() || vista.nombreTxt.getText().isEmpty()
                || vista.generoComboBox.getSelectedIndex() == 0 || vista.plataformaTxt.getText().isEmpty()
                || vista.fechaCompraDPicker.getText().isEmpty() || vista.precioTxt.getText().isEmpty()
                || vista.observacionesTxtArea.getText().isEmpty()) {
            return true;
        }
        return false;
    }

    //se vacia automaticamente cuando mandas el formulario
    private void limpiarCampos() {
        vista.numDiscosDescargaTxt.setText(null);
        vista.nombreTxt.setText(null);
        vista.generoComboBox.setSelectedIndex(0);
        vista.plataformaTxt.setText(null);
        vista.fechaCompraDPicker.setText(null);
        vista.precioTxt.setText(null);
        vista.observacionesTxtArea.setText(null);
        vista.estadoComboBox.setSelectedIndex(0);
    }

    public void refrescar() {
        vista.dlmVideojuego.clear();
        for (Videojuego unVideojuego : modelo.obtenerVideojuegos()) {
            vista.dlmVideojuego.addElement(unVideojuego);
        }
    }

    private void addActionListener(ActionListener listener) {
        vista.nuevoButton.addActionListener(listener);
        vista.importarButton.addActionListener(listener);
        vista.exportarButton.addActionListener(listener);
        vista.juegoFisicoRadioButton.addActionListener(listener);
        vista.juegoDigitalRadioButton.addActionListener(listener);
    }

    private void addListSelectionListener(ListSelectionListener listener) {
        vista.list1.addListSelectionListener(listener);
    }

    private void addWindowListener(WindowListener listener) {
        vista.frame.addWindowListener(listener);
    }

    private void cargarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.load(new FileReader("videojuegos.conf"));
        ultimaRutaExportada = new File(configuracion.getProperty("ultimaRutaExportada"));
    }
    private void actualizarDatosConfiguracion(File ultimaRutaExportada) {
        this.ultimaRutaExportada = ultimaRutaExportada;
    }

    private void guardarDatosConfiguracion() throws IOException {
        Properties configuracion = new Properties();
        configuracion.setProperty("ultimaRutaExportada", ultimaRutaExportada.getAbsolutePath());
        configuracion.store(new PrintWriter("videojuegos.conf"), "Datos de configuración de videojuegos");
    }


    public void actionPerformed(ActionEvent evento) {
        String actionCommand = evento.getActionCommand();

        switch (actionCommand) {
            case "Nuevo":
                if (camposVacios()) {
                    Util.mensajeError("Los siguientes campos no pueden estar vacios : \nNombre\nGenero" +
                            "\nPlataforma\nFecha Compra\nPrecio\nObservaciones" + "\n" + vista.numDiscosDescargaLbl.getText());
                    break;
                }
                if (modelo.existeVideojuego(vista.nombreTxt.getText())) {
                    Util.mensajeError("Ya existe un videojuego con ese nombre");
                    break;
                }
                if (vista.juegoFisicoRadioButton.isSelected()) {
                    modelo.altaJuegoFisico(vista.nombreTxt.getText(), vista.generoComboBox.getSelectedItem().toString(), vista.plataformaTxt.getText(),
                            vista.fechaCompraDPicker.getDate(), Float.parseFloat(vista.precioTxt.getText()), vista.observacionesTxtArea.getText(),
                            Integer.parseInt(vista.numDiscosDescargaTxt.getText()), vista.estadoComboBox.getSelectedItem().toString());
                } else {
                    modelo.altaJuegoDigital(vista.nombreTxt.getText(), vista.generoComboBox.getSelectedItem().toString(), vista.plataformaTxt.getText(),
                            vista.fechaCompraDPicker.getDate(), Float.parseFloat(vista.precioTxt.getText()), vista.observacionesTxtArea.getText(),
                            Double.parseDouble(vista.numDiscosDescargaTxt.getText()));
                }
                limpiarCampos();
                refrescar();
                break;
            case "Importar":
                JFileChooser selectorFichero = Util.crearSelectorFichero(ultimaRutaExportada, "Archivos XML", "xml");
                int opcion = selectorFichero.showOpenDialog(null);
                if (opcion == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.importarXML(selectorFichero.getSelectedFile());
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    }
                    refrescar();
                }
                break;
            case "Exportar":
                JFileChooser selectorFichero2 = Util.crearSelectorFichero(ultimaRutaExportada,"Archivos XML", "xml");
                int opcion2 = selectorFichero2.showSaveDialog(null);
                if (opcion2 == JFileChooser.APPROVE_OPTION) {
                    try {
                        modelo.exportarXML(selectorFichero2.getSelectedFile());
                        actualizarDatosConfiguracion(selectorFichero2.getSelectedFile());
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (TransformerException e) {
                        e.printStackTrace();
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case "JuegoFisico":
                vista.numDiscosDescargaLbl.setText("Número Discos");
                vista.estadoComboBox.setVisible(true);
                vista.estadoLbl.setVisible(true);
                vista.numDiscosDescargaTxt.setVisible(true);

                vista.frame.revalidate();
                vista.frame.repaint();
                break;
            case "JuegoDigital":
                vista.numDiscosDescargaLbl.setText("Descarga");
                vista.estadoComboBox.setVisible(false);
                vista.estadoLbl.setVisible(false);
                vista.numDiscosDescargaTxt.setVisible(true);

                vista.frame.revalidate();
                vista.frame.repaint();
                break;
        }
    }

    @Override
    public void windowClosing(WindowEvent e) {
        int respuesta = Util.mensajeConfirmacion("¿Deseas cerrar la ventana?", "Salir");
        if (respuesta == JOptionPane.OK_OPTION) {
            try {
                guardarDatosConfiguracion();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            System.exit(0);
        }
    }

    //se encarga de coger el dato
    @Override
    public void valueChanged(ListSelectionEvent e) {
        if (e.getValueIsAdjusting()) {
            Videojuego videojuegoSeleccionado = (Videojuego) vista.list1.getSelectedValue();
            vista.nombreTxt.setText(videojuegoSeleccionado.getNombre());
            vista.generoComboBox.setSelectedItem(videojuegoSeleccionado.getGenero());
            vista.plataformaTxt.setText(videojuegoSeleccionado.getPlataforma());
            vista.fechaCompraDPicker.setDate(videojuegoSeleccionado.getFechaCompra());
            vista.precioTxt.setText(String.valueOf(videojuegoSeleccionado.getPrecio()));
            vista.observacionesTxtArea.setText(videojuegoSeleccionado.getObservaciones());
            if (videojuegoSeleccionado instanceof JuegoFisico) {
                vista.juegoFisicoRadioButton.doClick();
                vista.numDiscosDescargaTxt.setText(String.valueOf(((JuegoFisico) videojuegoSeleccionado).getNumDiscos()));
            } else {
                vista.juegoDigitalRadioButton.doClick();
                vista.numDiscosDescargaTxt.setText(String.valueOf(((JuegoDigital) videojuegoSeleccionado).getTamanoDescarga()));
            }
        }
    }

    //no los uso
    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }


}
