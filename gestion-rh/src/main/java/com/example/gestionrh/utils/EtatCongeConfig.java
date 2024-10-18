package com.example.gestionrh.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.springframework.core.io.ClassPathResource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

public class EtatCongeConfig {

    private int etatSoumis;
    private int etatAttente;
    private int etatValider;
    private int etatRefuser;

    // Constructor to load the XML file and parse the values
    public EtatCongeConfig() {
        try {
            // Use ClassPathResource to load the file from the classpath
            ClassPathResource xmlFile = new ClassPathResource("XML/etatConge.xml");
            InputStream inputStream = xmlFile.getInputStream();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList affichageList = document.getElementsByTagName("affichage");
            if (affichageList.getLength() > 0) {
                Element affichageElement = (Element) affichageList.item(0);

                etatSoumis = Integer.parseInt(affichageElement.getElementsByTagName("etatSoumis").item(0).getTextContent());
                etatAttente = Integer.parseInt(affichageElement.getElementsByTagName("etatAttente").item(0).getTextContent());
                etatValider = Integer.parseInt(affichageElement.getElementsByTagName("etatValider").item(0).getTextContent());
                etatRefuser = Integer.parseInt(affichageElement.getElementsByTagName("etatRefuser").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle this appropriately in production
        }
    }

    // Getters for each state
    public int getEtatSoumis() {
        return etatSoumis;
    }

    public int getEtatAttente() {
        return etatAttente;
    }

    public int getEtatValider() {
        return etatValider;
    }

    public int getEtatRefuser() {
        return etatRefuser;
    }
}
