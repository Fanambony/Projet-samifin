package com.example.gestionrh.Controller;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Date;


@Controller
@RequestMapping("generer_pdf")
public class PdfController {

    @PostMapping("/bulletin-consultation")
    public void generatePdf(HttpServletRequest request, 
                            HttpServletResponse response,
                            @RequestParam("nom") String nom,
                            @RequestParam("matricule") String matricule,
                            @RequestParam("date_naissance") String date_naissance,
                            @RequestParam("qualite") String qualite,
                            @RequestParam("categorie") String categorie,
                            @RequestParam("corps_appartenance") String corps_appartenance,
                            @RequestParam("grade") String grade,
                            @RequestParam("indice") String indice,
                            @RequestParam("service") String service,
                            @RequestParam("localite") String localite,
                            @RequestParam(value = "fonctionnaire_malade", required = false) String fonctionnaireMalade,
                            @RequestParam(value = "membre_famille_malade", required = false) String membreFamilleMalade,
                            @RequestParam(value = "nom_prenom_membre_famille", required = false) String nomPrenomMembreFamille,
                            @RequestParam(value = "filiation_membre_famille", required = false) String filiationMembreFamille,
                            @RequestParam(value = "date_naissance_membre_famille", required = false) String dateNaissanceMembreFamille,
                            @RequestParam("date_demande") String dateDemande
                            ) throws Exception {

        // Vérification de la validité de la date de naissance avant la conversion
        Date dtn = (date_naissance != null && !date_naissance.isEmpty()) ? Date.valueOf(date_naissance) : null;
        Date dateDem = (dateDemande != null && !dateDemande.isEmpty()) ? Date.valueOf(dateDemande) : null;
        // Gestion de la date de naissance du membre de la famille
        Date dateMembreFamille = (dateNaissanceMembreFamille != null && !dateNaissanceMembreFamille.isEmpty()) 
                                ? Date.valueOf(dateNaissanceMembreFamille) 
                                : null;


        request.setAttribute("nom", nom);
        request.setAttribute("matricule", matricule);
        request.setAttribute("date_naissance", dtn);
        request.setAttribute("qualite", qualite);
        request.setAttribute("categorie", categorie);
        request.setAttribute("corps_appartenance", corps_appartenance);
        request.setAttribute("grade", grade);
        request.setAttribute("indice", indice);
        request.setAttribute("service", service);
        request.setAttribute("localite", localite);

        request.setAttribute("fonctionnaireMalade", fonctionnaireMalade);
        request.setAttribute("membreFamilleMalade", membreFamilleMalade);
        request.setAttribute("nomPrenomMembreFamille", nomPrenomMembreFamille);
        request.setAttribute("filiationMembreFamille", filiationMembreFamille);

        request.setAttribute("date_demande", dateDem);

        request.setAttribute("dateNaissanceMembreFamille", dateMembreFamille);

        // Capture the JSP output (rest of your code)
        String jspPath = "/WEB-INF/views/pdf/bulletin-consultation.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
        StringWriter writer = new StringWriter();
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(writer);
            }
        };
        dispatcher.include(request, wrapper);

        // Convert the JSP output to a PDF
        String renderedHtmlContent = writer.toString();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"bulletin.pdf\"");

        try (OutputStream os = response.getOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(renderedHtmlContent, request.getRequestURL().toString());
            builder.toStream(os);
            builder.run();
        }
    }

    @PostMapping("/demande-remboursement")
    public void generateDemandeRemboursementPdf(HttpServletRequest request, 
                                                HttpServletResponse response,
                                                @RequestParam("numero") String numero,
                                                @RequestParam("nom") String nom,
                                                @RequestParam("matricule") String matricule,
                                                @RequestParam("direction") String direction,
                                                @RequestParam("fonction") String fonction,
                                                @RequestParam("frais_medicaux") String fraisMedicaux,
                                                @RequestParam("nom_malade") String nomMalade,
                                                @RequestParam("lien") String lien,
                                                @RequestParam("montant") String montant,
                                                @RequestParam("date_consultation") String dateConsultation,
                                                @RequestParam("date_demande") String dateDemande,
                                                @RequestParam("avis") String avis,
                                                @RequestParam(value = "ordonnance", required = false) String ordonnance,
                                                @RequestParam(value = "factures", required = false) String factures,
                                                @RequestParam(value = "bulletin", required = false) String bulletin
                                                ) throws Exception {

        // Validation de la date de consultation
        Date dateCons = (dateConsultation != null && !dateConsultation.isEmpty()) ? Date.valueOf(dateConsultation) : null;
        Date dateDem = (dateDemande != null && !dateDemande.isEmpty()) ? Date.valueOf(dateDemande) : null;

        // Mettre les paramètres dans la requête pour les utiliser dans le JSP
        request.setAttribute("numero", numero);
        request.setAttribute("nom", nom);
        request.setAttribute("matricule", matricule);
        request.setAttribute("direction", direction);
        request.setAttribute("fonction", fonction);
        request.setAttribute("frais_medicaux", fraisMedicaux);
        request.setAttribute("nom_malade", nomMalade);
        request.setAttribute("lien", lien);
        request.setAttribute("montant", montant);
        request.setAttribute("date_consultation", dateCons);
        request.setAttribute("date_demande", dateDem);
        request.setAttribute("avis", avis);

        // Vérification des cases à cocher
        boolean isOrdonnanceChecked = ordonnance != null;
        boolean isFacturesChecked = factures != null;
        boolean isBulletinChecked = bulletin != null;

        request.setAttribute("ordonnance_checked", isOrdonnanceChecked);
        request.setAttribute("factures_checked", isFacturesChecked);
        request.setAttribute("bulletin_checked", isBulletinChecked);

        // Capture du contenu JSP
        String jspPath = "/WEB-INF/views/pdf/demande-remboursement.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
        StringWriter writer = new StringWriter();
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(writer);
            }
        };
        dispatcher.include(request, wrapper);

        // Conversion du rendu HTML en PDF
        String renderedHtmlContent = writer.toString();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"demande_remboursement.pdf\"");

        try (OutputStream os = response.getOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(renderedHtmlContent, request.getRequestURL().toString());
            builder.toStream(os);
            builder.run();
        }
    }
}