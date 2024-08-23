<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeConge" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeAbsence" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<TypeConge> typeConge = (List<TypeConge>)request.getAttribute("typeConge");
    List<TypeAbsence> typeAbsence = (List<TypeAbsence>)request.getAttribute("typeAbsence");
    List<VEtatDemande> etatDemande = (List<VEtatDemande>)request.getAttribute("etatDemandes");
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
        margin-top: 0px;
    }
    /*.card {
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        border: none;
    }
    .card-body {
        display: flex;
        flex-direction: column;
        justify-content: center; 
        height: 100%;
    }
    .card-title {
        margin-bottom: 1rem;
    }*/
  </style>

<div class="container-fluid">
    <div class="row">
        <!-- Tableau de bord -->
        <div class="col-md-9 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                  
                    <h4 class="card-title">Gestion de conge</h4>
                    
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-outline-primary btn-fw btn-block" data-toggle="modal" data-target="#faireDemandeModal">Faire une demande de conge</button>
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
                                <% for(VEtatDemande etat : etatDemande) { 
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
                                        <td class="font-weight-bold"><%= etat.getDateDemande() %></td>
                                        <td><%= etat.getTypeConge() %></td>
                                        <td><%= etat.getDateDebut() %> <%= etat.getDebutAbsence() %></td>
                                        <td><%= etat.getDateFin() %> <%= etat.getFinAbsence() %></td>
                                        <td class="font-weight-bold"><%= etat.getNombreJoursConge() %> jours</td>
                                        <td class="font-weight-medium"><div class="badge <%= badgeClass %>"><%= etat.getEtatDemande() %></div></td>
                                        <td>
                                            <% if (!etat.getIdEtatDemande().equals(5)) { %>
                                                <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#confirmationModal<%= etat.getIdDemandeConge() %>">
                                                    <i class="ti-check"></i>
                                                </button>
                                            <% } %>
                                            <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" data-target="#modifierDemandeModal<%= etat.getIdDemandeConge() %>">
                                                <i class="ti-pencil"></i>
                                            </button>
                                            <button type="button" class="btn btn-danger btn-rounded btn-icon" data-toggle="modal" data-target="#deleteConfirmationModal<%= etat.getIdDemandeConge() %>">
                                                <i class="ti-trash"></i>
                                            </button>
                                        </td>
                                    </tr>
                                <% } %>
                            </tbody>
                            
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Cartes -->
        <div class="col-md-3 grid-margin stretch-card">
            <div class="row">
                <!-- Carte pour les conges restants -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Conge restant</h4>
                            <div class="circle-card" style="border: solid rgb(17, 142, 46) 20px;">
                                <h3>90 jours</h3>
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
                                <h3>06 jours</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- modal demande conge -->
<div class="modal fade custom-modal" id="faireDemandeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Demande de conge</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="faireDemandeForm" action="/demande_conge/ajout_conge" method="get">
                    <div class="container-fluid">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="row">
                                    <label class="col-sm-12 col-form-label">Type d'absence</label>
                                    <div class="col-sm-12">
                                        <select class="form-control" id="typeConge" name="typeConge">
                                            <% for(TypeConge type : typeConge) { %>
                                                <option value="<%= type.getId() %>"><%= type.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Date debut</label>
                                    <div class="col-sm-12">
                                        <input type="date" class="form-control" name="date_debut" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <% for(TypeAbsence ta : typeAbsence) { %>
                                    <div class="col-sm-4">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="debut_absence" value="<%= ta.getEtat() %>">
                                            <%= ta.getLibelle() %>
                                            </label>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>                            
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Date fin</label>
                                    <div class="col-sm-12">
                                        <input type="date" class="form-control" name="date_fin" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <% for(TypeAbsence ta : typeAbsence) { %>
                                    <div class="col-sm-4">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="fin_absence" value="<%= ta.getEtat() %>">
                                            <%= ta.getLibelle() %>
                                            </label>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div> 
                        </div>

                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Nombre de jours</label>
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="nombreJours" name="nombreJours" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleTextarea1">Commentaire</label>
                                    <textarea class="form-control" id="exampleTextarea1" rows="4" name="commentaire"></textarea>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group text-right">
                            <button type="button" class="btn btn-secondary mr-2" data-dismiss="modal">Fermer</button>
                            <button type="submit" class="btn btn-primary">Valider</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


<!-- Modal de confirmation -->
<% for(VEtatDemande etat : etatDemande) { %>
<div class="modal fade custom-modal" id="confirmationModal<%= etat.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="confirmationLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="confirmationLabel">Confirmation</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr de vouloir confirme cette demande ?</p>
            </div>
            <div class="modal-footer">
                <a href="/demande_conge/auto-confirmation?etat=5&&idDemande=<%= etat.getIdDemandeConge() %>" class="btn btn-primary">Oui, confirmer</a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
            </div>
        </div>
    </div>
</div>
<% } %>

<!-- Modal de confirmation de suppression -->
<% for(VEtatDemande etat : etatDemande) { %>
<div class="modal fade custom-modal" id="deleteConfirmationModal<%= etat.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmationLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="deleteConfirmationLabel">Confirmation de suppression</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <p>Êtes-vous sûr de vouloir supprimer cette demande ?</p>
            </div>
            <div class="modal-footer">
                <a href="/demande_conge/supprimer-conge?idDemande=<%= etat.getIdDemandeConge() %>" id="confirmDeleteBtn" class="btn btn-danger">Oui, supprimer</a>
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
            </div>
        </div>
    </div>
</div>
<% } %>



    

<!-- Modal de modification de demande de congé -->
<% for(VEtatDemande etat : etatDemande) { %>
<div class="modal fade custom-modal" id="modifierDemandeModal<%= etat.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="modifierDemandeLabel" aria-hidden="true">
    <div class="modal-dialog modal-md modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="modifierDemandeLabel">Modifier une demande de congé</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="modifierDemandeForm" action="/demande_conge/modifier-conge" method="post">
                    <input type="hidden" name="idDemande" value="<%= etat.getIdDemandeConge() %>">
                    <div class="container-fluid">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="row">
                                    <label class="col-sm-12 col-form-label">Type d'absence</label>
                                    <div class="col-sm-12">
                                        <select class="form-control" id="typeConge" name="typeConge">
                                            <% for(TypeConge type : typeConge) { %>
                                                <option value="<%= type.getId() %>" <%= etat.getTypeConge().equals(type.getNom()) ? "selected" : "" %>><%= type.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Date début</label>
                                    <div class="col-sm-12">
                                        <input type="date" class="form-control" name="date_debut" value="<%= etat.getDateDebut() %>" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <% for(TypeAbsence ta : typeAbsence) { %>
                                    <div class="col-sm-4">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="debut_absence" value="<%= ta.getEtat() %>" <%= etat.getDebutAbsence().equals(ta.getLibelle()) ? "checked" : "" %>>
                                            <%= ta.getLibelle() %>
                                            </label>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div>                            
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Date fin</label>
                                    <div class="col-sm-12">
                                        <input type="date" class="form-control" name="date_fin" value="<%= etat.getDateFin() %>" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <% for(TypeAbsence ta : typeAbsence) { %>
                                    <div class="col-sm-4">
                                        <div class="form-check">
                                            <label class="form-check-label">
                                            <input type="radio" class="form-check-input" name="fin_absence" value="<%= ta.getEtat() %>" <%= etat.getFinAbsence().equals(ta.getLibelle()) ? "checked" : "" %>>
                                            <%= ta.getLibelle() %>
                                            </label>
                                        </div>
                                    </div>
                                    <% } %>
                                </div>
                            </div> 
                        </div>

                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Nombre de jours</label>
                                    <div class="col-sm-12">
                                        <input type="text" class="form-control" id="nombreJours" name="nombreJours" value="<%= etat.getNombreJoursConge() %>" readonly>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleTextarea1">Commentaire</label>
                                    <textarea class="form-control" id="exampleTextarea1" rows="4" name="commentaire"><%= etat.getCommentaire() %></textarea>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-group text-right">
                            <button type="button" class="btn btn-secondary mr-2" data-dismiss="modal">Fermer</button>
                            <button type="submit" class="btn btn-primary">Enregistrer les modifications</button>
                        </div>

                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
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