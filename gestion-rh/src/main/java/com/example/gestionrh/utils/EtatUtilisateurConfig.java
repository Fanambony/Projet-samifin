package com.example.gestionrh.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

@Configuration
public class EtatUtilisateurConfig {
    private int active;
    private int desactive;

    // Constructor to load the XML file and parse the values
    public EtatUtilisateurConfig() {
        try {
            // Use ClassPathResource to load the file from the classpath
            ClassPathResource xmlFile = new ClassPathResource("XML/etatUtilisateur.xml");
            InputStream inputStream = xmlFile.getInputStream();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList affichageList = document.getElementsByTagName("affichage");
            if (affichageList.getLength() > 0) {
                Element affichageElement = (Element) affichageList.item(0);

                active = Integer.parseInt(affichageElement.getElementsByTagName("active").item(0).getTextContent());
                desactive = Integer.parseInt(affichageElement.getElementsByTagName("desactive").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle this appropriately in production
        }
    }

    // Getters for each state
    public int getActive() {
        return active;
    }

    public int getDesactive() {
        return desactive;
    }
}
