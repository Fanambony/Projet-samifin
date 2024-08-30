package com.example.gestionrh.Controller;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
                            @RequestParam("prenom") String prenom,
                            @RequestParam("matricule") String matricule,
                            @RequestParam("date_naissance") String date_naissance,
                            @RequestParam("qualite") String qualite,
                            @RequestParam("categorie") String categorie,
                            @RequestParam("corps_appartenance") String corps_appartenance,
                            @RequestParam("grade") String grade,
                            @RequestParam("indice") String indice,
                            @RequestParam("service") String service,
                            @RequestParam("localite") String localite
                            ) throws Exception {

        Date dtn = Date.valueOf(date_naissance);

        request.setAttribute("nom", nom);
        request.setAttribute("prenom", prenom);
        request.setAttribute("matricule", matricule);
        request.setAttribute("date_naissance", dtn);
        request.setAttribute("qualite", qualite);
        request.setAttribute("categorie", categorie);
        request.setAttribute("corps_appartenance", corps_appartenance);
        request.setAttribute("grade", grade);
        request.setAttribute("indice", indice);
        request.setAttribute("service", service);
        request.setAttribute("localite", localite);

        // Capture the JSP output
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
}
