<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeConge" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeAbsence" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<TypeConge> typeConge = (List<TypeConge>)request.getAttribute("typeConge");
    List<Utilisateur> utilisateurs = (List<Utilisateur>)request.getAttribute("utilisateurs");
    List<TypeAbsence> typeAbsence = (List<TypeAbsence>)request.getAttribute("typeAbsences");
%>

<%@include file="../utils/header.jsp" %>


<%-- Vérification des messages flash --%>
<% String message = (String) request.getAttribute("message"); %>
<% String error = (String) request.getAttribute("error"); %>

<% if (message != null) { %>
    <div class="alert alert-success" id="success-message">
        <%= message %>
    </div>
<% } %>

<% if (error != null) { %>
    <div class="alert alert-danger" id="error-message">
        <%= error %>
    </div>
<% } %>



<style>
    .alert {
        position: fixed;
        top: -70px;
        left: 50%;
        transform: translateX(-50%);
        transition: top 0.5s ease, opacity 0.5s ease;
        opacity: 0; /* Commence invisible */
        z-index: 1000; /* Assurez-vous qu'il soit au-dessus des autres éléments */
    }

    .alert.show {
        top: 70px; /* Position visible */
        opacity: 1; /* Rendre visible */
    }

</style>


<div class="col-12 grid-margin">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">RÉGULARISATION DES CONGÉS DÉJÀ ÉCHUS</h4>

        <form class="form-sample" method="post" action="/demande_conge/regularisation-conge">
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Agent</label>
                        <div class="col-sm-9">
                            <select name="agent" class="form-control js-example-basic-single">
                                <% for(Utilisateur u : utilisateurs) { %>
                                    <option value="<%= u.getId() %>"><%= u.getNom() %> <%= u.getPrenom() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Type Conge</label>
                        <div class="col-sm-9">
                            <select name="typeConge" class="form-control">
                                <% for(TypeConge tc : typeConge) { %>
                                    <option value="<%= tc.getId() %>"><%= tc.getNom() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Date début</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="date_debut" name="date_debut" required>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-5 col-form-label">Début de l'absence</label>
                        <div class="col-sm-7">
                            <% for(TypeAbsence ta : typeAbsence) { %>
                                <div class="form-check">
                                    <input type="radio" class="form-check-input" id="debut_<%= ta.getEtat() %>" name="debut_absence" value="<%= ta.getEtat() %>">
                                    <label class="form-check-label" for="debut_<%= ta.getEtat() %>">
                                        <%= ta.getLibelle() %>
                                    </label>
                                </div>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Date fin</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" id="date_fin" name="date_fin" required>
                            <div id="dateError" class="text-danger"></div> <!-- Ajoutez ceci -->
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-5 col-form-label">Fin de l'absence</label>
                        <div class="col-sm-7">
                            <% for(TypeAbsence ta : typeAbsence) { %>
                            <div class="form-check">
                                <input type="radio" class="form-check-input" id="fin_<%= ta.getEtat() %>" name="fin_absence" value="<%= ta.getEtat() %>">
                                <label class="form-check-label" for="fin_<%= ta.getEtat() %>">
                                    <%= ta.getLibelle() %>
                                </label>
                            </div>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Nombre de jours</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" id="nombreJours" name="nombreJours" readonly>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Commentaire</label>
                        <div class="col-sm-9">
                            <textarea class="form-control" id="exampleTextarea1" rows="5" name="commentaire"></textarea>
                        </div>
                    </div>
                </div>
            </div>

          <button type="submit" class="btn btn-primary">
            Enregistrer conge                                                                          
          </button>

        </form>
      </div>
    </div>
</div>
<script>
    document.addEventListener("DOMContentLoaded", function() {
        const errorMessage = document.getElementById('error-message');
        const successMessage = document.getElementById('success-message');

        // Affichage et masquage des messages après 5 secondes
        if (errorMessage) {
            errorMessage.classList.add('show'); 
            setTimeout(function() {
                errorMessage.classList.remove('show'); 
            }, 5000);
        }

        if (successMessage) {
            successMessage.classList.add('show'); 
            setTimeout(function() {
                successMessage.classList.remove('show'); 
            }, 5000);
        }

        const dateDebutInput = document.getElementById('date_debut');
        const dateFinInput = document.getElementById('date_fin');
        const dateErrorDiv = document.getElementById('dateError');

        function validateDates() {
            const dateDebut = new Date(dateDebutInput.value);
            const dateFin = new Date(dateFinInput.value);

            if (dateDebut && dateFin && dateFin <= dateDebut) {
                dateErrorDiv.textContent = "La date de fin doit être supérieure à la date de début.";
                dateFinInput.value = ""; // Réinitialise la date de fin
                return false; // Empêche l'envoi du formulaire
            } else {
                dateErrorDiv.textContent = ""; // Efface le message d'erreur
                return true; // Autorise l'envoi du formulaire
            }
        }

        dateDebutInput.addEventListener('change', validateDates);
        dateFinInput.addEventListener('change', validateDates);

        document.querySelector('form').addEventListener('submit', function(event) {
            if (!validateDates()) {
                event.preventDefault(); // Annule l'envoi du formulaire
            } else {
                calculerNombreDeJours(); // Appelez la fonction de calcul seulement si les dates sont valides
            }
        });
    });

</script>



<script>
    function calculerNombreDeJours() {
        const dateDebut = document.querySelector('input[name="date_debut"]').value;
        const dateFin = document.querySelector('input[name="date_fin"]').value;
        const debutAbsence = document.querySelector('input[name="debut_absence"]:checked').value;
        const finAbsence = document.querySelector('input[name="fin_absence"]:checked').value;

        if (dateDebut && dateFin && debutAbsence && finAbsence) {
            const debut = new Date(dateDebut);
            const fin = new Date(dateFin);

            let differenceJours = (fin - debut) / (1000 * 60 * 60 * 24);

            if (debutAbsence == 1 && finAbsence == 5) {
                // Congé complet sur une journée
                differenceJours += 1;
            } else if ((debutAbsence == 1 && finAbsence == 1) || (debutAbsence == 5 && finAbsence == 5)) {
                // Congé AM à AM ou PM à PM
                differenceJours += 0.5;
            } else if (debutAbsence == 5 && finAbsence == 1) {
                // Congé PM un jour et AM le jour suivant
                differenceJours += 0;
            } else {
                // Congé complet sur plusieurs jours
                differenceJours += 1; // Pour inclure le premier jour
            }

            document.getElementById('nombreJours').value = differenceJours;
        }
    }

    document.querySelector('input[name="date_debut"]').addEventListener('change', calculerNombreDeJours);
    document.querySelector('input[name="date_fin"]').addEventListener('change', calculerNombreDeJours);
    document.querySelectorAll('input[name="debut_absence"]').forEach(radio => radio.addEventListener('change', calculerNombreDeJours));
    document.querySelectorAll('input[name="fin_absence"]').forEach(radio => radio.addEventListener('change', calculerNombreDeJours));
</script>


<%@include file="../utils/footer.jsp" %>