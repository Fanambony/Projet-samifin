<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeConge" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeAbsence" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page import="com.example.gestionrh.Model.Entity.VHistoriqueConge" %>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="org.springframework.data.domain.Page" %>


<%
    List<TypeConge> typeConge = (List<TypeConge>)request.getAttribute("typeConge");
    List<TypeAbsence> typeAbsence = (List<TypeAbsence>)request.getAttribute("typeAbsence");
    Page<VEtatDemande> etatDemandesPage = (Page<VEtatDemande>)request.getAttribute("etatDemandesPage");
    VHistoriqueConge historiqueConge = (VHistoriqueConge)request.getAttribute("historiqueConge");
    VHistoriqueConge historiquePermission = (VHistoriqueConge)request.getAttribute("historiquePersmission");
    String message = (String)request.getAttribute("message");
    String typeMessage = (String)request.getAttribute("type");
    Integer size = (Integer) request.getAttribute("size");
%>

<%@include file="../utils/header.jsp" %>

<style>
    .circle-card {
        border-radius: 50%;
        width: 180px;
        height: 180px;
        margin: 0 auto;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.5rem;
    }
    .custom-modal .modal-dialog {
        margin-top: 1rem;
    }

    .alert {
        padding: 15px;
        margin-bottom: 15px;
        border-radius: 4px;
        position: fixed;
        top: 10%;
        right: 35%;
        z-index: 1000;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        display: flex;
        justify-content: space-between;
        align-items: center;
        opacity: 0;
        transform: translateY(-50px); /* Position initiale en haut (hors de la vue) */
        transition: all 0.5s ease; /* Durée et effet de la transition */
    }

    .alert.show {
        opacity: 1;
        transform: translateY(0); /* L'alerte descend à sa position normale */
    }

    .alert.hide {
        opacity: 0;
        transform: translateY(-50px); /* L'alerte remonte */
    }

    .alert button {
        background-color: transparent;
        border: none;
        color: white;
        font-weight: bold;
        cursor: pointer;
        padding: 5px;
    }

    .alert.success {
        background-color: #4CAF50; /* Vert pour succès */
        color: white;
    }

    .alert.error {
        background-color: #f44336; /* Rouge pour erreur */
        color: white;
    }

    .typeAbs{
        text-align: center;
    }

    .text-danger {
        color: red;
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

<div class="container-fluid">
    <div class="row">
        <!-- Tableau de bord -->
        <div class="col-md-9 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">GESTION DE CONGE</h4>
                    
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-outline-primary btn-fw btn-block" data-toggle="modal" data-target="#faireDemandeModal">Faire une demande de congé</button>
                        </div>
                    </div>
                    
                    <div class="table-responsive">
                        <table class="table table-striped table-borderless">
                            <thead>
                                <tr>
                                    <th>Date demande</th>
                                    <th>Type demande</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Nbr jours</th>
                                    <th>Etat</th>
                                    <th>Action</th>
                                </tr>  
                            </thead>
                            <tbody>
                                <% for(VEtatDemande etat : etatDemandesPage) { // Change ici 
                                    String badgeClass = "badge-info"; // Par défaut, la couleur est bleue (info)
                                    if (etat.getIdEtatDemande().equals(10)) {
                                        badgeClass = "badge-success"; // Vert pour approuvé
                                    } else if (etat.getIdEtatDemande().equals(15)) {
                                        badgeClass = "badge-danger"; // Rouge pour rejeté
                                    } else if (etat.getIdEtatDemande().equals(5)) {
                                        badgeClass = "badge-warning"; // Jaune pour en attente
                                    }
                                %>
                                    <tr>
                                        <td class="font-weight-bold"><%= DateUtil.formatDate(etat.getDateDemande()) %></td>
                                        <td><%= etat.getTypeConge() %></td>
                                        <td><%= DateUtil.formatDate(etat.getDateDebut()) %> <%= etat.getDebutAbsence() %></td>
                                        <td><%= DateUtil.formatDate(etat.getDateFin()) %> <%= etat.getFinAbsence() %></td>
                                        <td class="font-weight-bold"><%= etat.getNombreJoursConge() %> jours</td>
                                        <td class="font-weight-medium"><div class="badge <%= badgeClass %>"><%= etat.getEtatDemande() %></div></td>
                                        <td>
                                            <div class="d-flex align-items-center justify-content-start">
                                                <div style="width: 50px;">
                                                    <% if (!etat.getIdEtatDemande().equals(5) && !etat.getIdEtatDemande().equals(10) && !etat.getIdEtatDemande().equals(15)) { %>
                                                        
                                                        <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#confirmationModal<%= etat.getIdDemandeConge() %>">
                                                            <i class="ti-check"></i>
                                                        </button>
                                                        

                                                    <% } %>
                                                </div>
                                                <% if (!etat.getIdEtatDemande().equals(10) && !etat.getIdEtatDemande().equals(15)) { %>
                                                    <div style="width: 50px;">
                                                        <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" data-target="#modifierDemandeModal<%= etat.getIdDemandeConge() %>">
                                                            <i class="ti-pencil"></i>
                                                        </button>
                                                    </div>
                                                    <div style="width: 50px;">
                                                        <button type="button" class="btn btn-danger btn-rounded btn-icon" data-toggle="modal" data-target="#deleteConfirmationModal<%= etat.getIdDemandeConge() %>">
                                                            <i class="ti-trash"></i>
                                                        </button>
                                                    </div>
                                                <% } %>
                                            </div>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>

                    <div class="pagination">
                        <ul class="pagination">
                            <!-- Lien vers la page précédente -->
                            <li class="page-item <%= etatDemandesPage.isFirst() ? "disabled" : "" %>"> <!-- Change ici -->
                                <a class="page-link" href="?page=<%= etatDemandesPage.getNumber() - 1 %>&size=<%= size %>"><< Précédent</a>
                            </li>
            
                            <!-- Liens pour chaque page -->
                            <% for (int i = 0; i < etatDemandesPage.getTotalPages(); i++) { %> <!-- Modifié ici -->
                                <li class="page-item <%= (i == etatDemandesPage.getNumber()) ? "active" : "" %>">
                                    <a class="page-link" href="?page=<%= i %>&size=<%= size %>"><%= i + 1 %></a>
                                </li>
                            <% } %>
            
                            <!-- Lien vers la page suivante -->
                            <li class="page-item <%= etatDemandesPage.isLast() ? "disabled" : "" %>">
                                <a class="page-link" href="?page=<%= etatDemandesPage.getNumber() + 1 %>&size=<%= size %>">Suivant >></a>
                            </li>
                        </ul>
                    </div>


                </div>
            </div>
        </div>

        <!-- Cartes -->
        <div class="col-md-3 grid-margin stretch-card">
            <div class="row" style="height: 75%;">
                <!-- Carte pour les conges restants -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Conge restant</h4>
                            <div class="circle-card" style="border: solid rgb(17, 142, 46) 20px;">
                                <% if (historiqueConge != null) { %>
                                    <h3><%= historiqueConge.getSoldeRestant() %> jours</h3>
                                <% } else { %>
                                    <h4>Pas de solde</h4> <!-- Ou une autre information pour indiquer qu'il n'y a pas de congé -->
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Carte pour les permissions restantes -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Permission restante</h4>
                            <div class="circle-card" style="border: solid rgb(231, 0, 0) 20px;">
                                <% if (historiquePermission != null) { %>
                                <h3><%= historiquePermission.getSoldeRestant() %> jours</h3>
                                <% } else { %>
                                    <h4>Pas de solde</h4> <!-- Ou une autre information pour indiquer qu'il n'y a pas de congé -->
                                <% } %>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Modal Demande Congé -->
<div class="modal fade custom-modal" id="faireDemandeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title" id="exampleModalLabel">
                    <i class="mdi mdi-calendar-check"></i> Demande de Congé
                </h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="faireDemandeForm" action="/demande_conge/ajout_conge" method="post">
                    <div class="container-fluid">
                        <div class="form-group">
                            <label for="typeConge">Type d'absence</label>
                            <select class="form-control" id="typeConge" name="typeConge">
                                <% for(TypeConge type : typeConge) { %>
                                    <option value="<%= type.getId() %>"><%= type.getNom() %></option>
                                <% } %>
                            </select>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <label for="date_debut">Date début</label>
                                <input type="date" class="form-control" id="date_debut" name="date_debut" required>
                                <small id="date_debut_error" class="form-text text-danger"></small>
                            </div>
                            <div class="form-group col-md-5 typeAbs">
                                <label>Début de l'absence</label>
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
                        <div class="form-row">
                            <div class="form-group col-md-7">
                                <label for="date_fin">Date fin</label>
                                <input type="date" class="form-control" id="date_fin" name="date_fin" required>
                                <small id="date_fin_error" class="form-text text-danger"></small>
                            </div>
                            <div class="form-group col-md-5 typeAbs">
                                <label>Fin de l'absence</label>
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
                        <div class="form-group">
                            <label for="nombreJours">Nombre de jours</label>
                            <input type="text" class="form-control" id="nombreJours" name="nombreJours" readonly>
                        </div>
                        <div class="form-group">
                            <label for="exampleTextarea1">Commentaire</label>
                            <textarea class="form-control" id="exampleTextarea1" rows="4" name="commentaire"></textarea>
                        </div>
                        <div class="form-group text-right">
                            <button type="button" class="btn btn-outline-secondary mr-2" data-dismiss="modal">Fermer</button>
                            <button type="submit" class="btn btn-primary">Valider</button>
                        </div>

                        
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const form = document.getElementById('faireDemandeForm');
        form.addEventListener('submit', function(event) {
            const dateDebut = document.getElementById('date_debut').value;
            const dateFin = document.getElementById('date_fin').value;

            let valid = true;

            // Réinitialiser les messages d'erreur
            document.getElementById('date_debut_error').textContent = '';
            document.getElementById('date_fin_error').textContent = '';

            if (dateDebut && dateFin) {
                if (new Date(dateDebut) > new Date(dateFin)) {
                    document.getElementById('date_debut_error').textContent = "La date de début doit être antérieure à la date de fin.";
                    document.getElementById('date_fin_error').textContent = "La date de fin doit être postérieure à la date de début.";
                    valid = false;
                }
            }

            if (!valid) {
                event.preventDefault(); // Empêche l'envoi du formulaire
            }
        });
    });
</script>

<!-- Modal de confirmation -->
<% for(VEtatDemande etat : etatDemandesPage) { %>
    <div class="modal fade custom-modal" id="confirmationModal<%= etat.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="confirmationLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered custom-modal-sm" role="document">
            <div class="modal-content custom-modal-content">
                <!-- Header stylé avec une couleur et une icône -->
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="confirmationLabel">
                        <i class="mdi mdi-check-circle-outline"></i> Confirmation
                    </h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-center">
                    <!-- Icône visuelle avant le texte, taille réduite -->
                    <i class="mdi mdi-clipboard-check mdi-36px text-primary"></i>
                    <p class="mt-3" style="font-size: 1rem;">
                        Voulez-vous vraiment confirmer cette demande de congé ?
                    </p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <a href="/demande_conge/auto-confirmation?etat=5&&idDemande=<%= etat.getIdDemandeConge() %>" class="btn btn-primary btn-sm">
                        <i class="mdi mdi-check"></i> Oui, confirmer
                    </a>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="mdi mdi-close"></i> Annuler
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>


<!-- message d'erreur ou de succes -->
<% if (message != null && !message.isEmpty()) { %>
    <div id="messageBox" class="alert <%= typeMessage %>">
        <span><%= message %></span>
        <button onclick="closeMessage()">OK</button>
    </div>
    <script>
        // Affiche le message avec effet de descente
        var messageBox = document.getElementById('messageBox');
        messageBox.classList.add('show');
        
        // Disparaît automatiquement après 5 secondes
        setTimeout(function() {
            closeMessage();
        }, 2000);

        function closeMessage() {
            messageBox.classList.remove('show');
            messageBox.classList.add('hide');
            // Retire complètement l'alerte après la transition (0.5s)
            setTimeout(function() {
                messageBox.style.display = 'none';
            }, 500);
        }
    </script>
<% } %>

<!-- Conteneur du message -->
<div id="messageBox" class="alert" style="display: none;"></div>

<!-- Modal de confirmation de suppression -->
<% for(VEtatDemande etat : etatDemandesPage) { %>
    <div class="modal fade custom-modal" id="deleteConfirmationModal<%= etat.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmationLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered custom-modal-sm" role="document">
            <div class="modal-content custom-modal-content">
                <!-- Header stylé avec une couleur et une icône -->
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="deleteConfirmationLabel">
                        <i class="mdi mdi-alert-circle-outline"></i> Confirmation de suppression
                    </h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-center">
                    <!-- Icône visuelle avant le texte, taille réduite -->
                    <i class="mdi mdi-delete-forever mdi-36px text-danger"></i>
                    <p class="mt-3" style="font-size: 1rem;">
                        Voulez-vous vraiment supprimer cette demande de congé ?
                    </p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <a href="/demande_conge/supprimer-conge?idDemande=<%= etat.getIdDemandeConge() %>" id="confirmDeleteBtn" class="btn btn-danger btn-sm">
                        <i class="mdi mdi-delete"></i> Oui, supprimer
                    </a>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="mdi mdi-close"></i> Annuler
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>

<!-- Modal de modification de demande de congé -->
<% for(VEtatDemande etat : etatDemandesPage) { %>
    <div class="modal fade custom-modal" id="modifierDemandeModal<%= etat.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="modifierDemandeLabel" aria-hidden="true">
        <div class="modal-dialog modal-md modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header bg-success text-white">
                    <h5 class="modal-title" id="modifierDemandeLabel">
                        <i class="mdi mdi-calendar-edit"></i> Modifier une demande de congé
                    </h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form id="modifierDemandeForm<%= etat.getIdDemandeConge() %>" action="/demande_conge/modifier-conge" method="post">
                        <input type="hidden" name="idDemande" value="<%= etat.getIdDemandeConge() %>">
                        <div class="container-fluid">
                            <!-- Type d'absence -->
                            <div class="form-group">
                                <label for="typeConge">Type d'absence</label>
                                <select class="form-control" id="typeConge" name="typeConge" disabled>
                                    <% for(TypeConge type : typeConge) { %>
                                        <option value="<%= type.getId() %>" <%= etat.getTypeConge().equals(type.getNom()) ? "selected" : "" %>><%= type.getNom() %></option>
                                    <% } %>
                                </select>
                            </div>
                            <!-- Date début et options de début d'absence -->
                            <div class="form-row">
                                <div class="form-group col-md-7">
                                    <label for="dateDebut<%= etat.getIdDemandeConge() %>">Date début</label>
                                    <input type="date" class="form-control" id="dateDebut<%= etat.getIdDemandeConge() %>" name="date_debut" value="<%= etat.getDateDebut() %>" required>
                                    <small id="date_debut_error<%= etat.getIdDemandeConge() %>" class="form-text text-danger"></small> <!-- Message d'erreur -->
                                </div>
                                <div class="form-group col-md-5 typeAbs">
                                    <label>Début de l'absence</label>
                                    <% for(TypeAbsence ta : typeAbsence) { %>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="debut_<%= ta.getEtat() %>" name="debut_absence" value="<%= ta.getEtat() %>" <%= etat.getDebutAbsence().equals(ta.getLibelle()) ? "checked" : "" %>>
                                            <label class="form-check-label" for="debut_<%= ta.getEtat() %>">
                                                <%= ta.getLibelle() %>
                                            </label>
                                        </div>
                                    <% } %>
                                </div>
                            </div>
                            <!-- Date fin et options de fin d'absence -->
                            <div class="form-row">
                                <div class="form-group col-md-7">
                                    <label for="dateFin<%= etat.getIdDemandeConge() %>">Date fin</label>
                                    <input type="date" class="form-control" id="dateFin<%= etat.getIdDemandeConge() %>" name="date_fin" value="<%= etat.getDateFin() %>" required>
                                    <small id="date_fin_error<%= etat.getIdDemandeConge() %>" class="form-text text-danger"></small> <!-- Message d'erreur -->
                                </div>
                                <div class="form-group col-md-5 typeAbs">
                                    <label>Fin de l'absence</label>
                                    <% for(TypeAbsence ta : typeAbsence) { %>
                                        <div class="form-check">
                                            <input type="radio" class="form-check-input" id="fin_<%= ta.getEtat() %>" name="fin_absence" value="<%= ta.getEtat() %>" <%= etat.getFinAbsence().equals(ta.getLibelle()) ? "checked" : "" %>>
                                            <label class="form-check-label" for="fin_<%= ta.getEtat() %>">
                                                <%= ta.getLibelle() %>
                                            </label>
                                        </div>
                                    <% } %>
                                </div>
                            </div>
                            <!-- Nombre de jours -->
                            <div class="form-group">
                                <label for="nombreJours<%= etat.getIdDemandeConge() %>">Nombre de jours</label>
                                <input type="text" class="form-control" id="nombreJours<%= etat.getIdDemandeConge() %>" name="nombreJours" value="<%= etat.getNombreJoursConge() %>" readonly>
                            </div>
                            <!-- Commentaire -->
                            <div class="form-group">
                                <label for="exampleTextarea1">Commentaire</label>
                                <textarea class="form-control" id="exampleTextarea1" rows="4" name="commentaire"><%= etat.getCommentaire() %></textarea>
                            </div>
                            <!-- Boutons d'action -->
                            <div class="form-group text-right">
                                <button type="button" class="btn btn-outline-secondary mr-2" data-dismiss="modal">Fermer</button>
                                <button type="submit" class="btn btn-success">Enregistrer les modifications</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const form = document.getElementById('modifierDemandeForm<%= etat.getIdDemandeConge() %>');
            form.addEventListener('submit', function(event) {
                const dateDebut = document.getElementById('dateDebut<%= etat.getIdDemandeConge() %>').value;
                const dateFin = document.getElementById('dateFin<%= etat.getIdDemandeConge() %>').value;

                let valid = true;

                // Réinitialiser les messages d'erreur
                document.getElementById('date_debut_error<%= etat.getIdDemandeConge() %>').textContent = '';
                document.getElementById('date_fin_error<%= etat.getIdDemandeConge() %>').textContent = '';

                if (dateDebut && dateFin) {
                    if (new Date(dateDebut) > new Date(dateFin)) {
                        document.getElementById('date_debut_error<%= etat.getIdDemandeConge() %>').textContent = "La date de début doit être antérieure à la date de fin.";
                        document.getElementById('date_fin_error<%= etat.getIdDemandeConge() %>').textContent = "La date de fin doit être postérieure à la date de début.";
                        valid = false;
                    }
                }

                if (!valid) {
                    event.preventDefault(); // Empêche l'envoi du formulaire
                }
            });

            // Ajouter des écouteurs d'événements pour recalculer le nombre de jours
            document.getElementById('dateDebut<%= etat.getIdDemandeConge() %>').addEventListener('change', calculateNombreJours<%= etat.getIdDemandeConge() %>);
            document.getElementById('dateFin<%= etat.getIdDemandeConge() %>').addEventListener('change', calculateNombreJours<%= etat.getIdDemandeConge() %>);
            document.querySelectorAll('input[name="debut_absence"]').forEach(function(elem) {
                elem.addEventListener('change', calculateNombreJours<%= etat.getIdDemandeConge() %>);
            });
            document.querySelectorAll('input[name="fin_absence"]').forEach(function(elem) {
                elem.addEventListener('change', calculateNombreJours<%= etat.getIdDemandeConge() %>);
            });
        });
    </script>
<% } %>


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
