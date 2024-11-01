package com.example.gestionrh.Controller;

import com.example.gestionrh.Model.Entity.Direction;
import com.example.gestionrh.Model.Entity.Fonction;
import com.example.gestionrh.Model.Entity.Utilisateur;
import com.example.gestionrh.Model.Service.DirectionService;
import com.example.gestionrh.Model.Service.FonctionService;
import com.example.gestionrh.Model.Service.UtilisateurService;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private DirectionService directionService;

    @Autowired
    private FonctionService fonctionService;

    @Autowired
    private UtilisateurService utilisateurService;

    @PostMapping("/bulletin-consultation")
    public void generateBulletinConsultation(HttpServletRequest request, 
                            HttpServletResponse response,
                            @RequestParam(value = "nom", required = false) String nom,
                            @RequestParam(value = "matricule", required = false) String matricule,
                            @RequestParam(value = "date_naissance", required = false) String date_naissance,
                            @RequestParam(value = "qualite", required = false) String qualite,
                            @RequestParam(value = "categorie") String categorie,
                            @RequestParam(value = "corps_appartenance", required = false) String corps_appartenance,
                            @RequestParam(value = "grade", required = false) String grade,
                            @RequestParam(value = "indice", required = false) String indice,
                            @RequestParam(value = "service", required = false) String service,
                            @RequestParam(value = "localite", required = false) String localite,
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
                                                // @RequestParam(value = "numero", required = false) String numero,
                                                @RequestParam(value = "nom", required = false) String nom,
                                                @RequestParam(value = "matricule", required = false) String matricule,
                                                @RequestParam(value = "direction", required = false) String id_direction,
                                                @RequestParam(value = "fonction", required = false) String id_fonction,
                                                @RequestParam(value = "nom_malade", required = false) String nomMalade,
                                                @RequestParam(value = "lien", required = false) String lien,
                                                @RequestParam(value = "montant", required = false) String montant,
                                                @RequestParam(value = "montant_let", required = false) String montant_lettre,
                                                @RequestParam(value = "date_consultation", required = false) String dateConsultation,
                                                @RequestParam(value = "date_demande", required = false) String dateDemande,
                                                @RequestParam(value = "avis", required = false) String avis,
                                                @RequestParam(value = "ordonnance", required = false) String ordonnance,
                                                @RequestParam(value = "factures", required = false) String factures,
                                                @RequestParam(value = "bulletin", required = false) String bulletin,
                                                @RequestParam(value = "notice", required = false) String notice
                                                ) throws Exception {

        // Validation de la date de consultation
        Date dateCons = (dateConsultation != null && !dateConsultation.isEmpty()) ? Date.valueOf(dateConsultation) : null;
        Date dateDem = (dateDemande != null && !dateDemande.isEmpty()) ? Date.valueOf(dateDemande) : null;

        Optional<Direction> directionOptional = directionService.getOne(id_direction);
        Direction direction = directionOptional.get();

        Optional<Fonction> fonctionOptional = fonctionService.getOne(id_fonction);
        Fonction fonction = fonctionOptional.get();

        // Mettre les paramètres dans la requête pour les utiliser dans le JSP
        // request.setAttribute("numero", numero);
        request.setAttribute("nom", nom);
        request.setAttribute("matricule", matricule);
        request.setAttribute("direction", direction.getNom());
        request.setAttribute("fonction", fonction.getNom());
        request.setAttribute("nom_malade", nomMalade);
        request.setAttribute("lien", lien);
        request.setAttribute("montant", montant);
        request.setAttribute("montant_let", montant_lettre);
        request.setAttribute("date_consultation", dateCons);
        request.setAttribute("date_demande", dateDem);
        request.setAttribute("avis", avis);

        // Vérification des cases à cocher
        boolean isOrdonnanceChecked = ordonnance != null;
        boolean isFacturesChecked = factures != null;
        boolean isBulletinChecked = bulletin != null;
        boolean isNoticeChecked = notice != null;

        request.setAttribute("ordonnance_checked", isOrdonnanceChecked);
        request.setAttribute("factures_checked", isFacturesChecked);
        request.setAttribute("bulletin_checked", isBulletinChecked);
        request.setAttribute("notice_checked", isNoticeChecked);

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

    @PostMapping("/attestation-non-paiement")
    public void generateAttestationNonPaiement(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(value = "ordonnateur", required = false) String ordonnateur,
            @RequestParam(value = "agent", required = false) String agent,
            @RequestParam(value = "matricule", required = false) String matricule,
            @RequestParam(value = "decision", required = false) String decision,
            @RequestParam(value = "dateDecision", required = false) String dateDecisionString,
            @RequestParam(value = "nom_prenom_membre_famille", required = false) String nomPrenomMembreFamille,
            @RequestParam(value = "filiation_membre_famille", required = false) String filiationMembreFamille,
            @RequestParam(value = "date_naissance_membre_famille", required = false) String dateNaissanceMembreFamille,
            @RequestParam(value = "montantLettre", required = false) String montantLettre,
            @RequestParam(value = "datePiece", required = false) String datePieceString,
            @RequestParam(value = "nowDate", required = false) String nowDateString,
            @RequestParam(value = "libelles", required = false) List<String> libelles,
            @RequestParam(value = "montants", required = false) List<Float> montants,
            @RequestParam(value = "montantTotal", required = false) double montantTotal,
            @RequestParam(value = "justificatifs", required = false) List<String> justificatifs
    ) throws Exception {

        Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(agent);
        Utilisateur utilisateur = utilisateurOpt.get();

        Date dateDecision = Date.valueOf(dateDecisionString);
        Date datePiece = Date.valueOf(datePieceString);
        Date nowDate = Date.valueOf(nowDateString);

        String genre = utilisateur.getGenre().getLibelle();
        String civilite;

        if ("Masculin".equalsIgnoreCase(genre)) {
            civilite = "Monsieur";
        } else if ("Feminin".equalsIgnoreCase(genre)) {
            civilite = "Madame";
        } else {
            civilite = "Client"; // Valeur par défaut au cas où le genre est inconnu
        }
        // Pass the parameters to the JSP
        request.setAttribute("ordonnateur", ordonnateur);
        request.setAttribute("nomAgent", utilisateur.getNom());
        request.setAttribute("prenomAgent", utilisateur.getPrenom());
        request.setAttribute("matricule", matricule);
        request.setAttribute("civilite", civilite);
        request.setAttribute("decision", decision);
        request.setAttribute("dateDecision", dateDecision);
        request.setAttribute("nom_prenom_membre_famille", nomPrenomMembreFamille);
        request.setAttribute("filiation_membre_famille", filiationMembreFamille);
        request.setAttribute("date_naissance_membre_famille", dateNaissanceMembreFamille);
        request.setAttribute("montantLettre", montantLettre);
        request.setAttribute("datePiece", datePiece);
        request.setAttribute("nowDate", nowDate);
        request.setAttribute("libelles", libelles);
        request.setAttribute("montants", montants);
        request.setAttribute("justificatifs", justificatifs);
        request.setAttribute("montantTotal", montantTotal);
    
        // Capture the content of the JSP
        String jspPath = "/WEB-INF/views/pdf/attestation-non-paiement.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
        StringWriter writer = new StringWriter();
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
            @Override
            public PrintWriter getWriter() {
                return new PrintWriter(writer);
            }
        };
        dispatcher.include(request, wrapper);
    
        // Convert the rendered HTML to PDF
        String renderedHtmlContent = writer.toString();
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline; filename=\"attestation_non_paiement.pdf\"");
    
        try (OutputStream os = response.getOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.withHtmlContent(renderedHtmlContent, request.getRequestURL().toString());
            builder.toStream(os);
            builder.run();
        }
    }


    // @PostMapping("/attestation-non-paiement")
    // public void generateAttestationNonPaiement(
    //         HttpServletRequest request,
    //         HttpServletResponse response,
    //         @RequestParam(value = "ordonnateur", required = false) String ordonnateur,
    //         @RequestParam(value = "agent", required = false) String agent,
    //         @RequestParam(value = "matricule", required = false) String matricule,
    //         @RequestParam(value = "decision", required = false) String decision,
    //         @RequestParam(value = "dateDecision", required = false) String dateDecisionString,
    //         @RequestParam(value = "nom_prenom_membre_famille", required = false) String nomPrenomMembreFamille,
    //         @RequestParam(value = "filiation_membre_famille", required = false) String filiationMembreFamille,
    //         @RequestParam(value = "date_naissance_membre_famille", required = false) String dateNaissanceMembreFamille,
    //         @RequestParam(value = "montantLettre", required = false) String montantLettre,
    //         @RequestParam(value = "datePiece", required = false) String datePieceString,
    //         @RequestParam(value = "nowDate", required = false) String nowDateString,
    //         @RequestParam(value = "libelles", required = false) List<String> libelles,
    //         @RequestParam(value = "montants", required = false) List<Float> montants,
    //         @RequestParam(value = "montantTotal", required = false) double montantTotal,
    //         @RequestParam(value = "justificatifs", required = false) List<String> justificatifs
    // ) throws Exception {

    //     // Récupération des données utilisateur
    //     Optional<Utilisateur> utilisateurOpt = utilisateurService.getOne(agent);
    //     Utilisateur utilisateur = utilisateurOpt.get();

    //     // Conversion des dates
    //     Date dateDecision = Date.valueOf(dateDecisionString);
    //     Date datePiece = Date.valueOf(datePieceString);
    //     Date nowDate = Date.valueOf(nowDateString);

    //     // Détermination de la civilité
    //     String genre = utilisateur.getGenre().getLibelle();
    //     String civilite = ("Masculin".equalsIgnoreCase(genre)) ? "Monsieur" : "Madame";

    //     // Définition des attributs pour la première page
    //     request.setAttribute("ordonnateur", ordonnateur);
    //     request.setAttribute("nomAgent", utilisateur.getNom());
    //     request.setAttribute("prenomAgent", utilisateur.getPrenom());
    //     request.setAttribute("matricule", matricule);
    //     request.setAttribute("civilite", civilite);
    //     request.setAttribute("decision", decision);
    //     request.setAttribute("dateDecision", dateDecision);
    //     request.setAttribute("nom_prenom_membre_famille", nomPrenomMembreFamille);
    //     request.setAttribute("filiation_membre_famille", filiationMembreFamille);
    //     request.setAttribute("date_naissance_membre_famille", dateNaissanceMembreFamille);
    //     request.setAttribute("montantLettre", montantLettre);
    //     request.setAttribute("datePiece", datePiece);
    //     request.setAttribute("nowDate", nowDate);
    //     request.setAttribute("libelles", libelles);
    //     request.setAttribute("montants", montants);
    //     request.setAttribute("justificatifs", justificatifs);
    //     request.setAttribute("montantTotal", montantTotal);

    //     // Capture the first JSP content (first page)
    //     String jspPath1 = "/WEB-INF/views/pdf/attestation-non-paiement.jsp";
    //     String contentPage1 = captureJspContent(request, response, jspPath1);

    //     // Capture the second JSP content (second page)
    //     String jspPath2 = "/WEB-INF/views/pdf/decision-remboursement.jsp";
    //     String contentPage2 = captureJspContent(request, response, jspPath2);

    //     System.out.println("Page 1 Content: " + contentPage1);
    //     System.out.println("Page 2 Content: " + contentPage2);


    //     // Combine content with page break
    //     String combinedContent = contentPage1 + "<div style='page-break-before: always;'></div>" + contentPage2;

    //     // Génération du PDF
    //     response.setContentType("application/pdf");
    //     response.setHeader("Content-Disposition", "inline; filename=\"attestation_non_paiement.pdf\"");

    //     try (OutputStream os = response.getOutputStream()) {
    //         PdfRendererBuilder builder = new PdfRendererBuilder();
    //         // Add combined content with page break
    //         builder.withHtmlContent(combinedContent, request.getRequestURL().toString());
    //         builder.toStream(os);
    //         builder.run();
    //     }
    // }

    // private String captureJspContent(HttpServletRequest request, HttpServletResponse response, String jspPath) throws Exception {
    //     RequestDispatcher dispatcher = request.getRequestDispatcher(jspPath);
    //     StringWriter writer = new StringWriter();
    //     HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper(response) {
    //         @Override
    //         public PrintWriter getWriter() {
    //             return new PrintWriter(writer);
    //         }
    //     };
    //     dispatcher.include(request, wrapper);
    //     return writer.toString();
    // }

    
}