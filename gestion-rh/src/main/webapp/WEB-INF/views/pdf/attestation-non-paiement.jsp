<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="com.example.gestionrh.utils.FormatUtil" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
    List<String> libelles = (List<String>) request.getAttribute("libelles");
    List<Float> montants = (List<Float>) request.getAttribute("montants");
    List<String> justificatifs = (List<String>) request.getAttribute("justificatifs");

    Date datePiece = (Date)request.getAttribute("datePiece");
    Date dateDecision = (Date)request.getAttribute("dateDecision");

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    boolean dateAffichee = false; // Contrôle d'affichage de la date
%>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Bulletin de Consultation</title>

    <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css"/>
    <link rel="stylesheet" href="/assets/css/pdf/attestation.css"/>
    <link rel="shortcut icon" href="/assets/images/favicon.png"/>
</head>
<body>
    <div class="page">
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
            <h4>ATTESTATION DE NON PAIEMENT</h4>
            <br/>

            <p class="paragraphe">Je soussigné, <%= request.getAttribute("ordonnateur") %> , Ordonnateur Secondaire Suppléant,
                atteste que <%= request.getAttribute("civilite") %> <%= request.getAttribute("nomAgent") %> 
                <%= request.getAttribute("prenomAgent") %>, IM. <%= request.getAttribute("matricule") %>  nommée Agent de 
                renseignements au Service de Renseignements Financiers dénommé SAMIFIN par Décision 
                n° <%= request.getAttribute("decision") %> du <%= sdf.format(dateDecision) %> 
                a engagé dans l'exercice de son mandat une dépense d'un montant de <%= request.getAttribute("montantLettre") %> 
                (<%= FormatUtil.formatMontant((Double)request.getAttribute("montantTotal")) %>) pour l'achat des médicaments de 
                <% 
                    // Vérifiez si un membre de la famille existe
                    String nomPrenomMembreFamille = (String) request.getAttribute("nom_prenom_membre_famille");
                    String filiationMembreFamille = (String) request.getAttribute("filiation_membre_famille");

                    if (nomPrenomMembreFamille != null && !nomPrenomMembreFamille.isEmpty()) {
                        // Si un membre de la famille existe, affichez son nom et sa relation
                        out.println("son "+ filiationMembreFamille +" " + nomPrenomMembreFamille );
                    } else {
                        // Si aucun membre de la famille, indiquez que l'agent se réfère à lui-même
                        out.println("l'agent lui-même");
                    }
                %>
                , dont les détails sont les suivants :</p>

            <table>
                <thead>
                    <tr>
                        <th>Date</th>
                        <th>Pièces justificatives</th>
                        <th>Libellé</th>
                        <th>Montant</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                    // Itérer sur la plus grande des listes pour afficher tous les éléments
                    int maxSize = Math.max(libelles != null ? libelles.size() : 0, 
                                        montants != null ? montants.size() : 0);
                    maxSize = Math.max(maxSize, justificatifs != null ? justificatifs.size() : 0);
                
                    for (int i = 0; i < maxSize; i++) {
                        // Vérifier si la date a déjà été affichée
                        if (!dateAffichee) {
                    %>
                    <tr>
                        <td><%= sdf.format(datePiece) %></td>
                        <% dateAffichee = true; %> <!-- Marquer la date comme affichée -->
                        <td>
                            <% if (justificatifs != null && i < justificatifs.size() && justificatifs.get(i) != null) { %>
                                - <%= justificatifs.get(i) %>
                            <% } else { %>
                                <!-- Cellule vide si aucune pièce justificative -->
                            <% } %>
                        </td>
                        <td>
                            <% if (libelles != null && i < libelles.size() && libelles.get(i) != null) { %>
                                - <%= libelles.get(i) %>
                            <% } else { %>
                                <!-- Cellule vide si aucun libelle -->
                            <% } %>
                        </td>
                        <td>
                            <% if (montants != null && i < montants.size() && montants.get(i) != null) { %>
                                - <%= montants.get(i) %>
                            <% } else { %>
                                <!-- Cellule vide si aucun montant -->
                            <% } %>
                        </td>
                    </tr>
                    <% 
                        } else { // Si la date a déjà été affichée
                    %>
                    <tr>
                        <td></td> <!-- Laisser la cellule vide pour la date -->
                        <td>
                            <% if (justificatifs != null && i < justificatifs.size() && justificatifs.get(i) != null) { %>
                                - <%= justificatifs.get(i) %>
                            <% } else { %>
                                <!-- Cellule vide si aucune pièce justificative -->
                            <% } %>
                        </td>
                        <td>
                            <% if (libelles != null && i < libelles.size() && libelles.get(i) != null) { %>
                                - <%= libelles.get(i) %>
                            <% } else { %>
                                <!-- Cellule vide si aucun libelle -->
                            <% } %>
                        </td>
                        <td>
                            <% if (montants != null && i < montants.size() && montants.get(i) != null) { %>
                                - <%= montants.get(i) %>
                            <% } else { %>
                                <!-- Cellule vide si aucun montant -->
                            <% } %>
                        </td>
                    </tr>
                    <% 
                        } // Fin de la condition pour afficher la date
                    }
                    %>
                    <tr class="total-row">
                        <td><strong>Total</strong></td>
                        <td colspan="3">
                            <strong><%= FormatUtil.formatMontant((Double)request.getAttribute("montantTotal")) %></strong>
                        </td>
                    </tr>
                </tbody>
            </table>
            
            <br/>

            <p class="paragraphe">L'établissement sanitaire où le malade a été soigné, est agréé par l'Etat en matière de
                remboursement de frais médicaux. L'intéressée n'a pas encore été remboursée pour lesdites dépenses
                auxquelles elle a droit à un remboursement intégral.</p>
            <p class="paragraphe">Arrêté à la présente attestation de non-paiement à la somme de : <%= request.getAttribute("montantLettre") %> .</p>
            
            <br/>
            
            <div class="signature">
                <p class="ville">Antananarivo, le <%= request.getAttribute("nowDate") != null ? DateUtil.formatDate((Date) request.getAttribute("nowDate")) : "" %></p>
                <p class="ville">L'Ordonnateur Secondaire Suppléant</p>
                <p class="ordonnateur"><strong><%= request.getAttribute("ordonnateur") %></strong></p>
            </div>
        </div>

        <div class="footer">
            <p>SAMIFIN-Lot I 102 A Lohanosy Ambohijanaka - Antananarivo 102 - Tél: (+261)34 30 332 23, (+261)33 33 332 23</p>
            <p>BP 710 - Site web: www.samifin.gov.mg - Email: contact@samifin.net</p>
        </div>
    </div>

    <!-- Forcer un saut de page -->
    <div style="page-break-after: always;"></div>

    <div class="page">
        <div class="header">
            <!-- Contenu de l'en-tête pour la page 2 -->
            <div class="container">
                <div class="row align-items-center">
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
            <!-- Contenu de la deuxième page -->
            <div class="entete">
                <p><strong>DECISION N° ______ /SAMIFIN/DG/24</strong></p>
                <p>Autorisant le remboursement des frais médicaux au nom de</p>
                <p><strong><%= request.getAttribute("nomAgent") %> <%= request.getAttribute("prenomAgent") %></strong></p>
                <p><strong>LE DIRECTEUR GENERAL du Service de Renseignements Financiers ou SAMIFIN</strong></p>
            </div>

            <div class="contenue">
                <p>- Vu la constitution;</p>
                <p>- Vu la Loi Organique n°2004-007 du 26 Juillet 2004 pour les Lois de Finances;</p>
                <p>- Vu la Loi n°2018-043 du 13 Février 2019 sur la lutte contre les blanchiments de capitaux et le financement du terrorisme;</p>
                <p>- Vu la Loi n°2023-021 du 22 décembre 2023 portant Loi de Finances pour 2024;</p>
                <p>- Vu le Décret n°94-077 du 25 janvier 1994 fixant le régime des soins médicaux, d'hospitalisation, d'évacuation sanitaire des fonctionnaires et les agents non encadrés de l'Etat;</p>
                <p>- Vu le Décret n°2004-571 du 1er Juin 2004 définissant les attributions et la responsabilité de l'Ordonnateur dans les phases d'exécution de la dépense publique;</p>
                <p>- Vu le Décret n°2005-003 du 04 janvier 2005 portant règlement général sur la comptabilité de l' exécution budgétaire des organismes publics;</p>
                <p>- Vu le Décret n°2015-1036 du 30 juin 2015 portant abrogation du décret n°2007-510 du 04 Juin 2007 portant création, organisation et fonctionnement du SAMIFIN;</p>
                <p>- Vu le décret n°2024-007 du 04 Janvier 2024 portant nomination du Premier Ministre, Chef du Gouvernement,</p>
                <p>- Vu le Décret n°2021-327 du 24 mars 2021 portant nomination du Directeur Général du Sampandraharaha Malagasy ladiana amin'ny Famotsiam-bola sy Famatsiam-bola ny Fampihorohoroana (SAMIFIN),</p>
                <p>- Vu le Décret n°2023-006 du 04 janvier 2023 portant répartition des crédits autorisés par la Loi 2022-015 du 22 décembre 2022 portant Loi de Finances pour 2023 entre les différents Institutions et Départements ministériels de l'Etat;</p>
                <p>- Vu le décret n°2024-007 du 04 Janvier 2024 portant nomination du Premier Ministre, Chef du Gouvernement,</p>
                <p>- Vu le décret n°2024-020 du 14 janvier 2024 portant nomination des membres du Gouvernement;</p>
                <p>- Vu l'Arrêté n°10903/2021 du 04 Mai 2021 portant nomination de Ordonnateurs Secondaires auprès de la Présidence de la République;</p>
                <p>- Vu l'Arrêté n°007/2024 du 18 janvier 2024 portant ouverture des crédits au niveau du Budget d'exécution de la gestion 2024 du budget général de l'Etat pour 2024;</p>
                <p>- Vu le Circulaire n°0377/MFB/SG/DGD.2/DF.2 du 19 Avril 1994 relative au mode de transmission des dossiers relatifs au remboursement de frais médicaux ou d'hospitalisation des personnels civils de l'Etat;</p>
                <p>- Vu le Circulaire n°1284-MFB/SG DGD.2 du 22 Septembre 1994 relative au mode de transmission des dossiers relatifs au remboursement de frais médicaux ou d'hospitalisation des personnels civils de l'Etat;</p>
                <p>- Vu le Circulaire n°04-2024/ MEF/SG/DGBF du 18 janvier 2024 relative à l'exécution du budget général de l'Etat 2024, des budgets annexes 2024 et des Opérations des Comptes Particuliers du Trésor 2024;</p>
                <p>- Vu les pièces des dossiers;</p>
            </div>

            <p class="entete"><strong>DECIDE</strong></p>

            <div class="decide">
                <p><span class="article">Article premier </span> : Est autorisé le remboursement des frais médicaux au nom de : <%= request.getAttribute("nomAgent") %> 
                <%= request.getAttribute("prenomAgent") %> , IM. <%= request.getAttribute("matricule") %> , Agent de renseignements d'un montant de 
                <strong><%= FormatUtil.formatMontant((Double)request.getAttribute("montantTotal")) %></strong></p>
                
                <div class="article2">
                    <p><span class="article">Article 2 </span> : Les dépenses y afférentes sont prises en charges par le Budget Général de l'Etat exercice 2024.</p>
                    <p>CODE MISSION : 010</p>
                    <p>CODE INDICATEUR D'OBJECTIF : 138-1-1-5-02</p>
                    <p>IMPUTATION BUDGETAIRE : 00-01-0-4D0-00000  Service de « Renseignements Financiers » (SAMIFIN)</p>
                    <p>SECTION CONVENTION : 000 « Ressources propres » paragraphe : 6561</p>
                    <p>FINANCEMENT: 10-001-001-A</p>
                    <p>CODE ORDSEC : 00-010-M-00000</p>
                </div>
                <div class="article3">
                    <p><span class="article">Article 3 </span> : Arrêtée 1a présente Décision la somme « <%= request.getAttribute("montantLettre") %> ».</p>
                </div>
                <div class="article3">
                    <p><span class="article">Article 4 </span> : La présente Décision sera enregistrée et communiquée partout où besoin sera.</p>
                </div>
            </div>

            <div class="signature">
                <p>Antananarivo, le <%= request.getAttribute("nowDate") != null ? DateUtil.formatDate((Date) request.getAttribute("nowDate")) : "" %></p>
                <p class="dg"><strong>RAJAONARISON Mamitiana</strong></p>
            </div>
        </div>
        <div class="footer">
            <!-- Pied de page de la deuxième page -->
            <p>SAMIFIN-Lot I 102 A Lohanosy Ambohijanaka - Antananarivo 102 - Tél: (+261)34 30 332 23</p>
            <p>BP 710 - Site web: www.samifin.gov.mg - Email: contact@samifin.net</p>
        </div>
    </div>

</body>
</html>