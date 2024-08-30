<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>

<%
    List<VEtatDemande> demande = (List<VEtatDemande>)request.getAttribute("demande");
%>

<%@include file="../utils/header.jsp" %>
<style>
    .modal-dialog-custom {
        top: -10%; /* Ajustez cette valeur pour placer le modal plus haut ou plus bas */
        transform: translateY(-10%); /* Ajuste la position de translation en fonction du `top` */
    }
</style>



<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Demande conge</h4>
            <div class="table-responsive">

                <br>
                    
                <div class="col-md-12">
                    <div class="form-group row">
                        <label class="col-sm-1 col-form-label">Etat :</label>
                        <div class="col-sm-3">
                            <select class="form-control">
                                <option value="">Toutes les etats</option>
                                <option value="1">En attente</option>
                                <option value="5">Valider</option>
                                <option value="10">Refuse</option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-primary mr-2">Valider</button>
                        </div>
                    </div>
                </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Date demande</th>
                            <th>Agent</th>
                            <th>Type conge</th>
                            <th>Date debut</th>
                            <th>Date fin</th>
                            <th>Nbr jours</th>
                            <th>Etat</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>

                        <% for(VEtatDemande de : demande) { 
                            String badgeClass = "badge-info"; // Par défaut, la couleur est bleue (info)
                            if (de.getIdEtatDemande().equals(10)) {
                                badgeClass = "badge-success"; // Vert pour approuvé
                            } else if (de.getIdEtatDemande().equals(15)) {
                                badgeClass = "badge-danger"; // Rouge pour rejeté
                            } else if (de.getIdEtatDemande().equals(5)) {
                                badgeClass = "badge-warning"; // Jaune pour en attente
                            }
                        %>
                            <tr>
                                <td class="font-weight-bold"><%= DateUtil.formatDate(de.getDateDemande()) %></td>
                                <td><%= de.getNomUtilisateur() %> <%= de.getPrenomUtilisateur() %></td>
                                <td><%= de.getTypeConge() %></td>
                                <td><%= DateUtil.formatDate(de.getDateDebut()) %> <%= de.getDebutAbsence() %></td>
                                <td><%= DateUtil.formatDate(de.getDateFin()) %> <%= de.getFinAbsence() %></td>
                                <td class="font-weight-bold"><%= de.getNombreJoursConge() %> jours</td>
                                
                                <td class="font-weight-medium"><div class="badge <%= badgeClass %>"><%= de.getEtatDemande() %></div></td>
                                <td>
                                    <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#detailModal<%= de.getIdDemandeConge() %>">
                                        <i class="ti-eye"></i>
                                    </button>
                                    <% if (!de.getIdEtatDemande().equals(10) && !de.getIdEtatDemande().equals(15)) { %>
                                        <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" data-target="#validerModal<%= de.getIdDemandeConge() %>">
                                            <i class="ti-check"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger btn-rounded btn-icon" data-toggle="modal" data-target="#refuserModal<%= de.getIdDemandeConge() %>">
                                            <i class="ti-trash"></i>
                                        </button>
                                    <% } %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>


            </div>
        </div>
    </div>
</div>

<!-- Modal detail information -->
<% for(VEtatDemande de : demande) { %>
    <div class="modal fade custom-modal" id="detailModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="detailLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-custom" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="detailLabel">Détails de la demande de congé pour l'agent <%= de.getPrenomUtilisateur() %></h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <strong>Agent :</strong> <%= de.getNomUtilisateur() %> <%= de.getPrenomUtilisateur() %>
                        </li>
                        <li class="list-group-item">
                            <strong>Type de congé :</strong> <%= de.getTypeConge() %>
                        </li>
                        <li class="list-group-item">
                            <strong>Date de demande :</strong> <%= DateUtil.formatDate(de.getDateDemande()) %>
                        </li>
                        <li class="list-group-item">
                            <strong>Début d'absence :</strong> <%= DateUtil.formatDate(de.getDateDebut()) %> (<%= de.getDebutAbsence() %>)
                        </li>
                        <li class="list-group-item">
                            <strong>Fin d'absence :</strong> <%= DateUtil.formatDate(de.getDateFin()) %> (<%= de.getFinAbsence() %>)
                        </li>
                        <li class="list-group-item">
                            <strong>Nombre de jours :</strong> <%= de.getNombreJoursConge() %> jours
                        </li>
                        <li class="list-group-item">
                            <strong>Commentaire :</strong> <%= de.getCommentaire() %>
                        </li>
                    </ul>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Retour</button>
                </div>
            </div>
        </div>
    </div>
<% } %>

<!-- Modal valider conge -->
<% for(VEtatDemande de : demande) { %>
    <div class="modal fade custom-modal" id="validerModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="validerLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-custom" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="validerLabel">Validation de demande de congé</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Êtes-vous sûr de vouloir valider cette demande de congé ?</p>
                </div>
                <div class="modal-footer">
                    <a href="/demande_conge/valider-conge?etat=10&&idDemande=<%= de.getIdDemandeConge() %>" class="btn btn-primary">Oui, valider</a>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Retour</button>
                </div>
            </div>
        </div>
    </div>
<% } %>

<!-- Modal refuser conge -->
<% for(VEtatDemande de : demande) { %>
    <div class="modal fade custom-modal" id="refuserModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="refuserLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-custom" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="refuserLabel">Refus de demande de congé</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <p>Êtes-vous sûr de vouloir refuser cette demande de congé ?</p>
                </div>
                <div class="modal-footer">
                    <a href="/demande_conge/refuser-conge?etat=15&&idDemande=<%= de.getIdDemandeConge() %>" id="confirmDeleteBtn" class="btn btn-danger">Oui, refuser</a>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Retour</button>
                </div>
            </div>
        </div>
    </div>
<% } %>


<%@include file="../utils/footer.jsp" %>