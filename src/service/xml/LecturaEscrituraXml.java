package service.xml;

import model.Coche;
import model.Multa;
import service.crud.HibernateCrud;

import javax.xml.stream.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class LecturaEscrituraXml {

    public void writeXmlCoche(List<Coche> cocheList, String path) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        try {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(path));
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeStartElement("coches");

            for (Coche coche : cocheList) {
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("coche");
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("id");
                xmlStreamWriter.writeCharacters(String.valueOf(coche.getId()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("matricula");
                xmlStreamWriter.writeCharacters(coche.getMatricula());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("marca");
                xmlStreamWriter.writeCharacters(coche.getMarca());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

            System.out.println("Fichero xml de coches escrito correctamente");
        } catch (XMLStreamException e) {
            System.out.println("Ups, error al escribir en el xml el archivo de coches");
        } catch (IOException e) {
            System.out.println("Ups, error durante la ejecución de escritura de fichero xml de coches");
        }
    }

    public List<Coche> getCochesList(String path) {
        List<Coche> cocheList = new ArrayList<>();

        String elementoActual = "";
        Integer id = 0;
        String matricula = "";
        String marca = "";

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(path));
            int element;

            while (xmlStreamReader.hasNext()) {
                element = xmlStreamReader.next();

                if (element == XMLStreamConstants.START_ELEMENT) {
                    elementoActual = xmlStreamReader.getLocalName();

                } else if (element == XMLStreamConstants.CHARACTERS) {
                    String text = xmlStreamReader.getText().trim();
                    if ("id".equals(elementoActual) && !text.isEmpty()) {
                        id = Integer.parseInt(text);
                    } else if ("matricula".equals(elementoActual) && !text.isEmpty()) {
                        matricula = text;

                    } else if ("marca".equals(elementoActual) && !text.isEmpty()) {
                        marca = text;
                    }

                } else if (element == XMLStreamConstants.END_ELEMENT) {
                    if ("coche".equals(xmlStreamReader.getLocalName())) {
                        cocheList.add(new Coche(id, matricula, marca));
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.out.println("Ups, error al leer el archivo xml de coches");
        } catch (FileNotFoundException e) {
            System.out.println("Ups, no se encontró el archivo xml de coches");
        }
        return cocheList;
    }

    public void writeXmlMulta(List<Multa> multaList, String path) {
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();

        try {
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileWriter(path));
            xmlStreamWriter.writeStartDocument();
            xmlStreamWriter.writeCharacters("\n");
            xmlStreamWriter.writeStartElement("multas");

            for (Multa multa : multaList) {
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("multa");
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("id");
                xmlStreamWriter.writeCharacters(String.valueOf(multa.getId()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("idcoche");
                xmlStreamWriter.writeCharacters(multa.getIdcoche().toString());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("importe");
                xmlStreamWriter.writeCharacters(String.valueOf(multa.getImporte()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeStartElement("porcentaxereduccion");
                xmlStreamWriter.writeCharacters(String.valueOf(multa.getPorcentaxereduccion()));
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeCharacters("\n");
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.writeEndDocument();
            xmlStreamWriter.close();

            System.out.println("Fichero xml de multas escrito correctamente");
        } catch (XMLStreamException e) {
            System.out.println("Ups, error al escribir en el xml el archivo de multas");
        } catch (IOException e) {
            System.out.println("Ups, error durante la ejecución de escritura de fichero xml de multas");
        }
    }

    public List<Integer> getMultasList(String path) {
        List<Integer> porcentaxeReduccionList = new ArrayList<>();
        String elementoActual = "";
        Integer porcentaxereduccion = 0;


        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new FileInputStream(path));
            int element;

            while (xmlStreamReader.hasNext()) {
                element = xmlStreamReader.next();

                if (element == XMLStreamConstants.START_ELEMENT) {
                    elementoActual = xmlStreamReader.getLocalName();

                } else if (element == XMLStreamConstants.CHARACTERS) {
                    String text = xmlStreamReader.getText().trim();
                    if ("porcentaxereduccion".equals(elementoActual) && !text.isEmpty()) {
                        porcentaxereduccion = Integer.parseInt(text);
                    }

                } else if (element == XMLStreamConstants.END_ELEMENT) {
                    if ("multa".equals(xmlStreamReader.getLocalName())) {
                        System.out.println("Leyendo y actualizando de multas xml");
                        porcentaxeReduccionList.add(porcentaxereduccion);
                    }
                }
            }
        } catch (XMLStreamException e) {
            System.out.println("Ups, error al leer el archivo xml de multas");
        } catch (FileNotFoundException e) {
            System.out.println("Ups, no se encontró el archivo xml de multas");
        }
        return porcentaxeReduccionList;
    }







}


