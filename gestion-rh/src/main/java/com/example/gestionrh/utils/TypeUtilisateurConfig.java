package com.example.gestionrh.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;

@Configuration
public class TypeUtilisateurConfig {
    private int collaborateur;
    private int direction;
    private int directeurGeneral;
    private int admin;

    // Constructor to load the XML file and parse the values
    public TypeUtilisateurConfig() {
        try {
            // Use ClassPathResource to load the file from the classpath
            ClassPathResource xmlFile = new ClassPathResource("XML/typeUtilisateur.xml");
            InputStream inputStream = xmlFile.getInputStream();
            
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);

            document.getDocumentElement().normalize();

            NodeList affichageList = document.getElementsByTagName("affichage");
            if (affichageList.getLength() > 0) {
                Element affichageElement = (Element) affichageList.item(0);

                collaborateur = Integer.parseInt(affichageElement.getElementsByTagName("collaborateur").item(0).getTextContent());
                direction = Integer.parseInt(affichageElement.getElementsByTagName("direction").item(0).getTextContent());
                directeurGeneral = Integer.parseInt(affichageElement.getElementsByTagName("directeurGeneral").item(0).getTextContent());
                admin = Integer.parseInt(affichageElement.getElementsByTagName("admin").item(0).getTextContent());
            }

        } catch (Exception e) {
            e.printStackTrace(); // Handle this appropriately in production
        }
    }

    // Getters for each state
    public int getCollaborateur() {
        return collaborateur;
    }

    public int getDirection() {
        return direction;
    }

    public int getDirecteurGeneral() {
        return directeurGeneral;
    }

    public int getAdmin() {
        return admin;
    }
}
