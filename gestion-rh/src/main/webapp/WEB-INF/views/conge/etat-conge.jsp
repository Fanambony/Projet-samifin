<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.gestionrh.Model.Entity.VHistoriqueConge" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Base64" %>

<%
    Page<VHistoriqueConge> historique_conge = (Page<VHistoriqueConge>) request.getAttribute("historique_conge");
    Page<Utilisateur> utilisateur = (Page<Utilisateur>)request.getAttribute("utilisateurs");
    List<VEtatDemande> etat_demande = (List<VEtatDemande>)request.getAttribute("demandeValiderParUtilisateur");
%>

<%@include file="../utils/header.jsp" %>



<style>
    #calendar-conge {
        width: 60%; /* Utiliser toute la largeur du modal */
        height: auto; /* Ajuster la hauteur automatiquement */
        margin-right: auto; /* Centrer le calendrier */
    }

    .fc-popover {
        background-color: rgb(255, 0, 0) !important; /* Fond blanc pour améliorer la lisibilité */
        color: black !important; /* Texte noir */
        border: 1px solid black; /* Bordure noire */
    }

    .fc-event, .fc-popover {
        font-size: 14px; /* Ajuster la taille du texte */
        color: black !important; /* Forcer le texte en noir pour une meilleure visibilité */
    }

    .fc-more {
        color: black !important; /* Couleur du texte du bouton "more" */
        font-weight: bold;
        cursor: pointer;
    }

    .fc-button {
        background-color: #007bff;  /* Couleur de fond des boutons (bleu par défaut) */
        color: white;  /* Couleur du texte des boutons */
        border: none;  /* Enlever la bordure */
        border-radius: 4px;  /* Légèrement arrondi */
        padding: 8px 12px;  /* Espacement interne */
        font-weight: bold;  /* Texte en gras */
        transition: background-color 0.3s ease;  /* Effet de transition au survol */
    }

    .fc-button:hover {
        background-color: #0056b3;  /* Couleur de fond au survol (bleu plus foncé) */
        color: white;  /* Le texte reste blanc au survol */
    }

    .fc-button.fc-prev-button, .fc-button.fc-next-button {
        color: rgb(0, 0, 0);
    }

    .fc-button.fc-prev-button:hover, .fc-button.fc-next-button:hover {
        background-color: #3788D8;  /* Survol pour les boutons "Précédent" et "Suivant" (mois) */
    }

    .fc-button.fc-prevYear-button, .fc-button.fc-nextYear-button {
        color: black;
    }

    .fc-button.fc-prevYear-button:hover, .fc-button.fc-nextYear-button:hover {
        background-color: #3788D8;  /* Survol pour les boutons "Précédent Année" et "Suivant Année" */
    }

    .fc-button.fc-today-button {
        background-color: #000000;  /* Couleur spécifique pour le bouton "Aujourd'hui" */
        color: white;
    }

    .fc-button.fc-today-button:hover {
        background-color: #333333;  /* Survol pour le bouton "Aujourd'hui" */
    }

    .fc-button.fc-dayGridMonth-button, .fc-button.fc-dayGridWeek-button, .fc-button.fc-dayGridDay-button {
        background-color: #17a2b8;  /* Couleur spécifique pour les boutons de vue "Mois", "Semaine", "Jour" */
        color: rgb(0, 0, 0);
    }

    .fc-button.fc-dayGridMonth-button:hover, .fc-button.fc-dayGridWeek-button:hover, .fc-button.fc-dayGridDay-button:hover {
        background-color: #3788D8;  /* Survol pour les boutons de vue "Mois", "Semaine", "Jour" */
    }

    .custom-modal .modal-dialog {
        margin-top: 1%;
    }

    /* pagination */
    .pagination {
        display: flex;
        justify-content: center;
        margin-top: 20px;
    }
    .page-item {
        margin: 0 5px;
    }
    .page-item.active .page-link {
        background-color: #007bff;
        color: white;
    }
    .custom-modal .form-group label {
        font-weight: bold;
    }
</style>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Etat de congé des personnels</h4>
            <div class="table-responsive pt-3">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Image</th>
                            <th>Matricule</th>
                            <th>Agent</th>
                            <th>Direction</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(Utilisateur u : utilisateur) { 
                                byte[] imageBytes = u.getImage();
                                String imageUtilisateur = (imageBytes != null && imageBytes.length > 0) ? Base64.getEncoder().encodeToString(imageBytes) : null;
                                String imageParDefaut = "/assets/images/faces/user.jpeg";
                                String imageSrc = (imageUtilisateur != null && !imageUtilisateur.isEmpty()) ? "data:image/jpeg;base64, " + imageUtilisateur : imageParDefaut;
                        %>
                            <tr>
                                <td class="py-1">
                                    <img src="<%= imageSrc %>" alt="image" class="img-fluid rounded-circle" style="width: 50px; height: 50px;" />
                                </td>
                                <td>
                                    <% for (DetailUtilisateur du : u.getDetailUtilisateurs()) { %>
                                        <div><%= du.getMatricule() %></div>
                                    <% } %>
                                </td>
                                <td><%= u.getNom() %> <%= u.getPrenom() %></td>
                                <td>
                                    <% for (DetailUtilisateur du : u.getDetailUtilisateurs()) { %>
                                        <div><%= du.getFonction().getDirection().getNom() %></div>
                                    <% } %>
                                </td>
                                
                                <td>
                                    <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" title="Voir détails" data-target="#detailHistoriqueModal<%= u.getId() %>">
                                        <i class="ti-eye"></i>
                                    </button>
                                </td>
                                
                            </tr>
                        <% } %>
                    </tbody>
                </table>

                <br>

                <div class="pagination">
                    <ul class="pagination">
                        <li class="page-item <%= utilisateur.isFirst() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= utilisateur.getNumber() - 1 %>&size=<%= utilisateur.getSize() %>"><< Précédent</a>
                        </li>
                
                        <% for (int i = 0; i < utilisateur.getTotalPages(); i++) { %>
                            <li class="page-item <%= (i == utilisateur.getNumber()) ? "active" : "" %>">
                                <a class="page-link" href="?page=<%= i %>&size=<%= utilisateur.getSize() %>"><%= i + 1 %></a>
                            </li>
                        <% } %>
                
                        <li class="page-item <%= utilisateur.isLast() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= utilisateur.getNumber() + 1 %>&size=<%= utilisateur.getSize() %>">Suivant >></a>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Section des modals -->
<% for (Utilisateur u : utilisateur) { 
    boolean hasEvents = false; // Variable pour vérifier si l'utilisateur a des demandes de congé
    StringBuilder eventsJson = new StringBuilder("[");
    for (VEtatDemande v : etat_demande) {
        if (v.getIdUtilisateur().equals(u.getId())) { // Filtrer les demandes par utilisateur
            hasEvents = true; // L'utilisateur a des événements
            eventsJson.append("{")
                      .append("\"title\": \"").append(v.getTypeConge()).append("\",")
                      .append("\"start\": \"").append(v.getDateDebut()).append("\"");
            if (v.getDateFin() != null) {
                eventsJson.append(", \"end\": \"").append(v.getDateFin()).append("\"");
            }

            if ("TCG001".equals(v.getIdTypeConge())) {
                eventsJson.append(", \"backgroundColor\": \"green\""); // Correctif : ajout d'une virgule avant la couleur
            } else if ("TCG002".equals(v.getIdTypeConge())) {
                eventsJson.append(", \"backgroundColor\": \"red\""); // Correctif : ajout d'une virgule avant la couleur
            }
            
            
            eventsJson.append("},");
        }
    }
    if (eventsJson.length() > 1) {
        eventsJson.setLength(eventsJson.length() - 1); // Supprimer la dernière virgule
    }
    eventsJson.append("]");
%>

<div class="modal fade custom-modal" id="detailHistoriqueModal<%= u.getId() %>" tabindex="-1" role="dialog" aria-labelledby="userDetailModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="userDetailModalLabel">Détail de prise de congé de <%= u.getNom() %> <%= u.getPrenom() %></h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div class="row"> <!-- Utilisation du système de grille de Bootstrap -->
                    
                    <!-- Colonne gauche : Calendrier -->
                    <div class="col-md-8">
                        <div id="calendar-conge<%= u.getId() %>" style="width: 100%;"></div> <!-- Calendrier pleine largeur dans sa colonne -->
                    </div>
                    
                    <!-- Colonne droite : Statistiques -->
                    <div class="col-md-4" style="border-left: 1px solid #ddd; padding-left: 20px;">
                        <h5 class="stat-title">Statistiques</h5>
                        
                        <% if (u.getHistoriqueConges() != null && !u.getHistoriqueConges().isEmpty()) { %>
                            <div class="stat-section">
                                <% for (VHistoriqueConge hi : u.getHistoriqueConges()) { %>
                                    <div class="stat-item">
                                        <i class="ti-clipboard"></i>
                                        <p>Type d'absence : <span><%= hi.getTypeConge() %></span></p>
                                    </div>
                                    <div class="stat-item">
                                        <i class="ti-time"></i>
                                        <p>Nombre total de congés : <span><%= hi.getSoldeDisponible() %></span></p>
                                    </div>
                                    <div class="stat-item">
                                        <i class="ti-check-box"></i>
                                        <p>Nombre de congés pris : <span><%= hi.getNombreJoursPris() %></span></p>
                                    </div>
                                    <div class="stat-item">
                                        <i class="ti-calendar"></i>
                                        <p>Solde disponible : <span><%= hi.getSoldeRestant() %> jours</span></p>
                                    </div>
                                <% } %>
                            </div>
                        <% } else { %>
                            <p class="no-data">Aucune donnée de congé disponible pour cet utilisateur.</p>
                        <% } %>
                    </div>

                    
                </div>
                
                <% if (!hasEvents) { %>
                    <p>Aucune demande de congé trouvée pour cet utilisateur.</p>
                <% } %>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        $('#detailHistoriqueModal<%= u.getId() %>').on('shown.bs.modal', function () {
            var calendarEl = document.getElementById('calendar-conge<%= u.getId() %>');
    
            var eventsData = <%= eventsJson.toString() %>; // Injecter les événements filtrés par utilisateur
    
            var calendar = new FullCalendar.Calendar(calendarEl, {
                headerToolbar: {
                    left: 'prevYear,prev,next,nextYear today',
                    center: 'title',
                    right: 'dayGridMonth,dayGridWeek,dayGridDay'
                },
                buttonText: {
                    today: 'Aujourd\'hui',
                    month: 'Mois',
                    week: 'Semaine',
                    day: 'Jour',
                    list: 'Liste'
                },
                initialDate: new Date().toISOString().slice(0, 10), // Date d'aujourd'hui
                navLinks: true,
                editable: true,
                dayMaxEvents: true,
                events: eventsData // Passer les événements spécifiques à cet utilisateur
            });
    
            calendar.render();
            calendar.updateSize(); // Ajuster les dimensions
        });
    });
</script>


<% } %>

<script src='/assets/calendar/index.global.js'></script>

<%@include file="../utils/footer.jsp" %>
