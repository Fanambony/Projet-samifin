<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>


<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Bulletin de Consultation</title>

    <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css"/>
    <link rel="stylesheet" href="/assets/css/pdf/style.css"/>
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
            <h4>BULLETIN DE CONSULTATION</h4>
            <br/>
            <h5>RENSEIGNEMENTS CONCERNANT L'AGENT</h5>
            <br/>
            <p><strong>Nom et Prénoms :</strong> <%= request.getAttribute("nom") %> <%= request.getAttribute("prenom") %></p>
            <p><strong>Matricule :</strong> <%= request.getAttribute("matricule") %></p>
            <p><strong>QUALITE :</strong> <%= request.getAttribute("qualite") %></p>
            <p><strong>CATEGORIE :</strong> <%= request.getAttribute("categorie") %></p>
            <p><strong>Corps d'appartenance :</strong> <%= request.getAttribute("corps_appartenance") %></p>
            <p><strong>Grade ou Emploi :</strong> <%= request.getAttribute("grade") %></p>
            <p><strong>Indice :</strong> <%= request.getAttribute("indice") %></p>
            <p><strong>Date de naissance :</strong> <%= DateUtil.formatDate((Date) request.getAttribute("date_naissance")) %></p>
            <p><strong>Service Employeur :</strong> <%= request.getAttribute("service") %></p>
            <p><strong>Localité de service :</strong> <%= request.getAttribute("localite") %></p>

            <h5>RENSEIGNEMENTS CONCERNANT LE MEMBRE DE LA FAMILLE</h5>
            <br/>
            <p><strong>Le malade est-il le fonctionnaire lui-même ?</strong> <span class="option1">OUI - NON</span></p>
            <p><strong>Le malade est-il un membre de la famille ?</strong> <span class="option2">OUI - NON</span></p>
            <p><strong>Nom et Prénom :</strong> </p>
            <p><strong>Filiation :</strong> </p>
            <p><strong>Né(e) le :</strong> </p>

            <br/>
            <div class="signature">
                <p class="ville">Antananarivo, le</p>
                <p class="medecin">Le Médecin traitant</p>
            </div>
        </div>
    </div>

    <div class="footer">
        <p>SAMIFIN-Lot I 102 A Lohanosy Ambohijanaka - Antananarivo 102 - Tél: (+261)34 30 332 23, (+261)33 33 332 23</p>
        <p>BP 710 - Site web: www.samifin.gov.mg - Email: contact@samifin.net</p>
    </div>
</body>
</html>
