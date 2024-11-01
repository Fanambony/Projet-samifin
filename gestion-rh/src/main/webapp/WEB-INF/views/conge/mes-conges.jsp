<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page import="com.example.gestionrh.Model.Entity.VHistoriqueConge" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<VEtatDemande> etat_demande = (List<VEtatDemande>) request.getAttribute("demandeValiderParUtilisateur");
    List<VHistoriqueConge> historique_conge = (List<VHistoriqueConge>) request.getAttribute("historiqueConge");
%>

<%@ include file="../utils/header.jsp" %>


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
</style>
<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Etat de congé des personnels</h4>

            <div class="row">
                <!-- Colonne pour le calendrier -->
                <div class="col-md-8">
                    <div id="calendar" class="mt-4"></div>
                    <% if (etat_demande == null || etat_demande.isEmpty()) { %>
                        <p class="text-danger mt-3"><strong>Aucune demande de congé trouvée.</strong> L'utilisateur n'a pas encore soumis de demande de congé.</p>
                    <% } %>
                </div>

                <!-- Colonne pour les statistiques -->
                <div class="col-md-4">
                    <div class="statistique-solde bg-light rounded p-4 shadow-sm">
                        <h5 class="text-center mb-4">Statistiques du Solde de Congé</h5>
                        
                        <% if (historique_conge == null || historique_conge.isEmpty()) { %>
                            <p class="text-danger"><strong>Aucune donnée de congé disponible.</strong> L'utilisateur n'a pas encore pris de congé.</p>
                        <% } else { %>
                        <% for(VHistoriqueConge historique : historique_conge) { %>
                            <div class="mb-4 p-3 rounded border border-info shadow-sm bg-white">
                                <h6 class="text-info"><i class="ti-clipboard"></i> Type d'absence :</h6>
                                <p class="mb-1"><strong><%= historique.getTypeConge() %></strong></p>
                                <p class="mb-1 text-secondary"><i class="ti-time"></i> Nombre total de congés : <strong><%= historique.getSoldeDisponible() %> jours</strong></p>
                                <p class="mb-1 text-danger"><i class="ti-check-box"></i> Congés pris : <strong><%= historique.getNombreJoursPris() %> jours</strong></p>
                                <p class="mb-1 text-success"><i class="ti-calendar"></i> Solde disponible : <strong><%= historique.getSoldeRestant() %> jours</strong></p>
                            </div>
                        <% } %>
                        <% } %>
                    </div>
                </div>

            </div>

        </div>
    </div>
</div>


<script src='/assets/calendar/index.global.js'></script>
<script>
    document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
    
        var calendar = new FullCalendar.Calendar(calendarEl, {
            headerToolbar: {
                left: 'prevYear,prev,next,nextYear today',
                center: 'title',
                right: 'dayGridMonth,dayGridWeek,dayGridDay'
            },
            initialDate: new Date().toISOString().slice(0, 10),
            navLinks: true, // can click day/week names to navigate views
            editable: true,
            dayMaxEvents: true, // allow "more" link when too many events
            events: [
                <% for(int i = 0; i < etat_demande.size(); i++) { 
                    VEtatDemande demande = etat_demande.get(i);
                    String backgroundColor = ""; 
                    
                    if ("TCG001".equals(demande.getIdTypeConge())) {
                        backgroundColor = "green";
                    } else if ("TCG002".equals(demande.getIdTypeConge())) {
                        backgroundColor = "red";
                    } %>
                {
                    title: '<%= demande.getTypeConge().replace("'", "\\'") %>',
                    start: '<%= demande.getDateDebut() %>',
                    end: '<%= demande.getDateFin() %>',
                    backgroundColor: '<%= backgroundColor %>'
                }<%= (i < etat_demande.size() - 1) ? "," : "" %>
                <% } %>
            ]
        });

        calendar.render();
    });
</script>



<%@ include file="../utils/footer.jsp" %>
