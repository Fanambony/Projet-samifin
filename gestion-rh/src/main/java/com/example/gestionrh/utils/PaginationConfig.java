package com.example.gestionrh.utils;

import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import jakarta.annotation.PostConstruct;

@Configuration
public class PaginationConfig {  // Cette classe n'a pas besoin d'être mappée sur XML
    private static final String XML_FILE = "/XML/configuration.xml"; // Assurez-vous que ce chemin est correct
    private int defaultPaginationSize;

    @PostConstruct
    public void init() {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(XmlConfiguration.class); // Utilisation de la classe XmlConfiguration pour le mapping
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            XmlConfiguration config = (XmlConfiguration) unmarshaller.unmarshal(new ClassPathResource(XML_FILE).getInputStream());
            this.defaultPaginationSize = config.getAffichage().getPagination();
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
            this.defaultPaginationSize = 10; // Valeur par défaut en cas d'erreur
        }
    }

    public int getDefaultPaginationSize() {
        return defaultPaginationSize;
    }

    // Classe interne pour la structure XML
    @XmlRootElement(name = "configuration")  // Ceci est l'élément racine du fichier XML
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class XmlConfiguration {

        @XmlElement(name = "affichage")
        private Affichage affichage;

        public Affichage getAffichage() {
            return affichage;
        }

        public void setAffichage(Affichage affichage) {
            this.affichage = affichage;
        }

        @XmlAccessorType(XmlAccessType.FIELD)
        public static class Affichage {
            @XmlElement(name = "pagination")
            private int pagination;

            public int getPagination() {
                return pagination;
            }

            public void setPagination(int pagination) {
                this.pagination = pagination;
            }
        }
    }
}
