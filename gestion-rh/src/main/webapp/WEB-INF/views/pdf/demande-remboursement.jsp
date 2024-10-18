<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    boolean ordonnanceChecked = (Boolean) request.getAttribute("ordonnance_checked");
    boolean facturesChecked = (Boolean) request.getAttribute("factures_checked");
    boolean bulletinChecked = (Boolean) request.getAttribute("bulletin_checked");
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Bulletin de Consultation</title>
    <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css"/>
    <link rel="stylesheet" href="/assets/css/pdf/remboursement.css"/>
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
            <h4>DEMANDE DE REMBOURSEMENT DE FRAIS MEDICAUX</h4>
            <br/>
            <h5>N° <%= request.getAttribute("numero") %></h5>
            <br/>
            <p class="haut"><strong>Mme/Mr :</strong> <%= request.getAttribute("nom") %></p>
            <p class="haut"><strong>Matricule :</strong> <%= request.getAttribute("matricule") %></p>
            <p class="haut"><strong>Direction/Service :</strong> <%= request.getAttribute("direction") %></p>
            <p class="haut"><strong>Fonction ou Grade :</strong> <%= request.getAttribute("fonction") %></p>
            <p class="haut"><strong>Sollicite le remboursement des frais médicaux de :</strong> <%= request.getAttribute("frais_medicaux") %></p>
            <p class="haut"><strong>Nom du Malade :</strong> <%= request.getAttribute("nom_malade") %></p>
            <p class="haut"><strong>Lien :</strong> <%= request.getAttribute("lien") %></p>
            <p class="haut"><strong>Montant :</strong> <%= request.getAttribute("montant") %></p>

            <br/>
            <p>Pièces fournies :</p>
            <table border="1" cellpadding="5" cellspacing="0">
                <tbody>
                    <tr>
                        <td>
                            <div class="checkbox <% if (ordonnanceChecked) { %> checked <% } %>"></div>
                        </td>
                        <td> Ordonnance</td>
                        <td>
                            <div class="checkbox <% if (facturesChecked) { %> checked <% } %>"></div>
                        </td>
                        <td> Factures</td>
                        <td>
                            <div class="checkbox <% if (bulletinChecked) { %> checked <% } %>"></div>
                        </td>
                        <td> Bulletin de consultation</td>
                    </tr>
                </tbody>
            </table>

            <br/>

            <p><strong>Date de Consultation :</strong> <%= request.getAttribute("date_consultation") != null ? DateUtil.formatDate((Date) request.getAttribute("date_consultation")) : "" %></p>

            <br/>
            <div class="signature">
                <p class="avis"><strong>Avis :</strong> <%= request.getAttribute("avis") %></p>
                <br/>
                <p class="ville">Antananarivo, le <%= request.getAttribute("date_demande") != null ? DateUtil.formatDate((Date) request.getAttribute("date_demande")) : "" %></p>
                <p class="ville">Le Directeur Administratif et Financier</p>
                <p class="demandeur">Le demandeur</p>
            </div>
        </div>
    </div>

<%@include file="../utils/footerPDF.jsp" %>