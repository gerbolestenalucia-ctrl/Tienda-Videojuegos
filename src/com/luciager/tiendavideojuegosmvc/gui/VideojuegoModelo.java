package com.luciager.tiendavideojuegosmvc.gui;

import com.luciager.tiendavideojuegosmvc.base.JuegoDigital;
import com.luciager.tiendavideojuegosmvc.base.JuegoFisico;
import com.luciager.tiendavideojuegosmvc.base.Videojuego;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VideojuegoModelo {
    private ArrayList<Videojuego> listaVideojuegos;

    public VideojuegoModelo() {
        listaVideojuegos = new ArrayList<>();
    }

    public ArrayList<Videojuego> obtenerVideojuegos() {
        return listaVideojuegos;
    }

    public void altaJuegoFisico(String nombre, String genero, String plataforma, LocalDate fechaCompra, float precio, String observaciones, int numDiscos, String estado) {
        JuegoFisico nuevoJuegoFisico = new JuegoFisico(nombre, genero, plataforma, fechaCompra, precio, observaciones, numDiscos, estado);
        listaVideojuegos.add(nuevoJuegoFisico);

    }

    public void altaJuegoDigital(String nombre, String genero, String plataforma, LocalDate fechaCompra, float precio, String observaciones, double tamanoDescarga) {
        JuegoDigital nuevoJuegoDigital = new JuegoDigital(nombre, genero, plataforma, fechaCompra, precio, observaciones, tamanoDescarga);
        listaVideojuegos.add(nuevoJuegoDigital);
    }

    public boolean existeVideojuego(String nombre) {
        for (Videojuego unVideojuego : listaVideojuegos) {
            if (unVideojuego.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public void exportarXML(File fichero) throws ParserConfigurationException, TransformerException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        DOMImplementation dom = builder.getDOMImplementation();
        Document documento = dom.createDocument(null, "xml", null);

        //añado el nodo raiz
        Element raiz = documento.createElement("videojuegos");
        documento.getDocumentElement().appendChild(raiz);

        Element nodoVideojuego = null;
        Element nodoDatos = null;
        Text texto = null;

        for (Videojuego unVideojuego : listaVideojuegos) {
            //añadimos dentro de videojuegos, juegoFisico y juegoDigital
            if (unVideojuego instanceof Videojuego) {
                nodoVideojuego = documento.createElement("JuegoFisico");
            } else {
                nodoVideojuego = documento.createElement("JuegoDigital");
            }
            raiz.appendChild(nodoVideojuego);

            //dentro de la etiqueta videojuego -> nombre, precio ...
            nodoDatos = documento.createElement("nombre");
            nodoVideojuego.appendChild(nodoDatos);
            texto = documento.createTextNode(unVideojuego.getNombre());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("genero");
            nodoVideojuego.appendChild(nodoDatos);
            texto = documento.createTextNode(unVideojuego.getGenero());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("plataforma");
            nodoVideojuego.appendChild(nodoDatos);
            texto = documento.createTextNode(unVideojuego.getPlataforma());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("fechaCompra");
            nodoVideojuego.appendChild(nodoDatos);
            texto = documento.createTextNode(unVideojuego.getFechaCompra().toString());
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("precio");
            nodoVideojuego.appendChild(nodoDatos);
            texto = documento.createTextNode(String.valueOf(unVideojuego.getPrecio()));
            nodoDatos.appendChild(texto);

            nodoDatos = documento.createElement("observaciones");
            nodoVideojuego.appendChild(nodoDatos);
            texto = documento.createTextNode(unVideojuego.getObservaciones());
            nodoDatos.appendChild(texto);

            //ponemos el campo diferente
            if (unVideojuego instanceof JuegoFisico) {
                nodoDatos = documento.createElement("numDiscos");
                nodoVideojuego.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((JuegoFisico) unVideojuego).getNumDiscos()));
                nodoDatos.appendChild(texto);

                nodoDatos = documento.createElement("estado");
                nodoVideojuego.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((JuegoFisico) unVideojuego).getEstado()));
                nodoDatos.appendChild(texto);

            } else {
                nodoDatos = documento.createElement("tamanoDescarga");
                nodoVideojuego.appendChild(nodoDatos);
                texto = documento.createTextNode(String.valueOf(((JuegoDigital) unVideojuego).getTamanoDescarga()));
                nodoDatos.appendChild(texto);
            }
        }
        //guardo en fichero
        Source source = new DOMSource(documento);
        Result resultado = new StreamResult(fichero);

        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.transform(source, resultado);
    }

    public void importarXML(File fichero) throws ParserConfigurationException, IOException, SAXException {
        listaVideojuegos = new ArrayList<Videojuego>();
        JuegoFisico nuevoJuegoFisico = null;
        JuegoDigital nuevoJuegoDigital = null;

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document documento = builder.parse(fichero);

        //el * es para coger todos los elementos de la lista
        NodeList listaElementos = documento.getElementsByTagName("*");
        for (int i = 0; i < listaElementos.getLength(); i++) {
            Element nodoVideojuegos = (Element) listaElementos.item(i);

            if (nodoVideojuegos.getTagName().equalsIgnoreCase("JuegoFisico")) {
                nuevoJuegoFisico = new JuegoFisico();
                nuevoJuegoFisico.setNumDiscos(Integer.parseInt(nodoVideojuegos.getChildNodes().item(0).getTextContent()));
                nuevoJuegoFisico.setEstado(nodoVideojuegos.getChildNodes().item(1).getTextContent());
                listaVideojuegos.add(nuevoJuegoFisico);
            } else {
                if (nodoVideojuegos.getTagName().equalsIgnoreCase("JuegoDigital")) {
                    nuevoJuegoDigital = new JuegoDigital();
                    nuevoJuegoDigital.setTamanoDescarga(Double.parseDouble(nodoVideojuegos.getChildNodes().item(0).getTextContent()));
                    listaVideojuegos.add(nuevoJuegoDigital);
                }
            }
        }
    }
}