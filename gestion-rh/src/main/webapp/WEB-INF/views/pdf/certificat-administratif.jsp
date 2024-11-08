<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8"%>


<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Certificat administratif</title>

    <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css"/>
    <link rel="stylesheet" href="/assets/css/pdf/certificat.css"/>
    <!-- endinject -->
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>

</head>
<body>
    <div class="header">

        <div class="container">
            <div class="header row align-items-center">
              <div class="drapeau">
                <img src="/assets/images/Madagascar.png" alt="MADAGASIKARA" />
                <p>REPOBLIKAN'I MADAGASIKARA<br />Fitiavana - Tanindrazana - Fandrosoana</p>
              </div>
              <div class="lettre">
                <p>Service de Renseignement Financier<br />Financial Intelligence Unit</p>
              </div>
              <div class="sary">
                <img src="/assets/images/logoSamifin.png" alt="SAMIFIN" />
              </div>
            </div>
        </div>
    </div>

    <div class="content">
        <div class="container">

            <h5 style="margin-bottom: 75px;">N° ______ - SAMIFIN/DG/DAF/24</h5>
            <h4 style="margin-bottom: 40px;">CERTIFICAT ADMINISTRATIF</h4>
            <p class="paragraphe">Je soussigné, RAKOTOMALALA Faly Andrianaivo Directeur Administratif et Financier, </p>
                <p style="margin-bottom: 50px;">certifie que:</p>

            <p><strong>Nom et Prénoms</strong> : <%= request.getAttribute("nom") %> <%= request.getAttribute("prenom") %></p>
            <br/>
            <p><strong>I.M</strong> : <%= request.getAttribute("matricule") %></p>
            <br/>
            <p><strong>Fonction</strong> : <%= request.getAttribute("fonction") %></p>
            <br/>
            <p><strong>Corps et Grade</strong> : <%= request.getAttribute("corps") %></p>
            <br/>
            <p><strong>Indice</strong> : <%= request.getAttribute("indice") %></p>
            <br/>
            <p><strong>Budget</strong> : Général</p>
            <br/>
            <p><strong>Chapitre</strong> : 00 010 110 </p>
            <br/>
            <p class="paragraphe">Est en service au SAMIFIN depuis le <%= DateUtil.formatDate((Date)request.getAttribute("date_entre")) %> jusqu'à ce jour.</p>
            
            <p>En foi de quoi, le certificat est délivré pour servir et valoir ce que de droit.</p>

            <div class="signature"  style="margin-top: 50px;">
                <p class="ville">Antananarivo, le </p>
                <p class="daf">RAKOTOMALALA Faly Andrianaivo</p>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>SAMIFIN-Lot I 102 A Lohanosy Ambohijanaka - Antananarivo 102 - Tél: (+261)34 30 332 23</p>
        <p>BP 710 - Site web: www.samifin.gov.mg - Email: contact@samifin.net</p>
    </div>
</body>
</html>