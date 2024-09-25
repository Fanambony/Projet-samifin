<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>

<%
    List<VEtatDemande> demande_valider = (List<VEtatDemande>)request.getAttribute("list_demande_valider");
%>

<%@include file="../utils/header.jsp" %>

<style>
    .custom-modal .modal-dialog {
        margin-top: 1rem;
    }
    .custom-modal-sm {
        max-width: 450px;
    }

    .custom-modal-content {
        min-height: 250px;
    }

    /* Nouveau style pour le modal de détail */
    .custom-detail-modal .modal-dialog {
        max-width: 650px; /* Ajustez la largeur selon vos besoins */
    }

    .custom-detail-modal .modal-content {
        min-height: 400px; /* Ajustez la hauteur selon vos besoins */
    }
</style>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Annulation demande conge valider</h4>
            <div class="table-responsive">

                <br>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Date demande</th>
                            <th>Agent</th>
                            <th>Type conge</th>
                            <th>Date debut</th>
                            <th>Date fin</th>
                            <th>Nbr jours</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(VEtatDemande dv : demande_valider) { %>
                            <tr>
                                <td class="font-weight-bold"><%= DateUtil.formatDate(dv.getDateDemande()) %></td>
                                <td><%= dv.getNomUtilisateur() %> <%= dv.getPrenomUtilisateur() %></td>
                                <td><%= dv.getTypeConge() %></td>
                                <td><%= DateUtil.formatDate(dv.getDateDebut()) %> <%= dv.getDebutAbsence() %></td>
                                <td><%= DateUtil.formatDate(dv.getDateFin()) %> <%= dv.getFinAbsence() %></td>
                                <td class="font-weight-bold"><%= dv.getNombreJoursConge() %> jours</td>
                                <td>
                                    <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" title="Voire détails" data-target="#detailModal<%= dv.getIdDemandeConge() %>">
                                        <i class="ti-eye"></i>
                                    </button>
                                    <button type="button" class="btn btn-danger btn-rounded btn-icon" data-toggle="modal" title="Annuler" data-target="#annulationModal<%= dv.getIdDemandeConge() %>">
                                        <i class="ti-back-left"></i>
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

<!-- Modal detail information -->
<% for(VEtatDemande de : demande_valider) { %>
    <div class="modal fade custom-modal custom-detail-modal" id="detailModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="detailLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header bg-info text-white">
                    <h5 class="modal-title" id="detailLabel">
                        <i class="ti-info-alt"></i> Détails de la demande de congé pour <%= de.getNomUtilisateur() %> <%= de.getPrenomUtilisateur() %>
                    </h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div class="container">
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Agent :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= de.getNomUtilisateur() %> <%= de.getPrenomUtilisateur() %></span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Type de congé :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= de.getTypeConge() %></span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Date de demande :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= DateUtil.formatDate(de.getDateDemande()) %></span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Début d'absence :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= DateUtil.formatDate(de.getDateDebut()) %> (<%= de.getDebutAbsence() %>)</span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Fin d'absence :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= DateUtil.formatDate(de.getDateFin()) %> (<%= de.getFinAbsence() %>)</span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Nombre de jours :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= de.getNombreJoursConge() %> jours</span>
                            </div>
                        </div>
                        <div class="row mb-3">
                            <div class="col-md-4">
                                <strong>Commentaire :</strong>
                            </div>
                            <div class="col-md-8">
                                <span><%= de.getCommentaire() %></span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="ti-arrow-left"></i> Retour
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>


<!-- Modal de confirmation d'annulation -->
<% for(VEtatDemande dv : demande_valider) { %>
    <div class="modal fade custom-modal" id="annulationModal<%= dv.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="annulationConfirmationLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered custom-modal-sm" role="document">
            <div class="modal-content custom-modal-content">
                <!-- Header stylé avec une couleur et une icône -->
                <div class="modal-header bg-danger text-white">
                    <h5 class="modal-title" id="annulationConfirmationLabel">
                        <i class="ti-alert"></i> Confirmation d'annulation
                    </h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-center">
                    <!-- Icône visuelle avant le texte, taille réduite -->
                    <i class="ti-back-left mdi-36px text-danger"></i>
                    <p class="mt-3">
                        Voulez-vous vraiment annuler cette demande de congé validée ?
                    </p>
                </div>
                <div class="modal-footer d-flex justify-content-center">
                    <a href="/demande_conge/annuler-conge?idDemande=<%= dv.getIdDemandeConge() %>" id="confirmCancelBtn" class="btn btn-danger btn-sm">
                        <i class="ti-back-left"></i> Oui, annuler
                    </a>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="ti-close"></i> Non, revenir
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>


<%@include file="../utils/footer.jsp" %>