<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    VEtatDemande detail_demande = (VEtatDemande)request.getAttribute("demande");
    int type_utilisateur = (Integer)session.getAttribute("userType");

    int etat = detail_demande.getIdEtatDemande();
    String badgeClass;

    switch (etat) {
        case 10:
            badgeClass = "badge badge-success";
            break;
        case 5:
            badgeClass = "badge badge-warning";
            break;
        case 15:
            badgeClass = "badge badge-danger";
            break;
        default:
            badgeClass = "badge badge-secondary";
            break;
    }
%>

<%@include file="../utils/header.jsp" %>

<style>
    .custom-modal .modal-dialog {
        margin-top: 0px;
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

<div class="container mt-4">
    <div class="row justify-content-center">
        <div class="col-md-8">
            <div class="card shadow-sm border-0">
                <div class="card-body">
                    <h4 class="text-center text-secondary mb-4">Détail Notification</h4>
                    <div class="row">
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Nom :</h6>
                            <p class="text-dark"><%= detail_demande.getNomUtilisateur() %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Prénom :</h6>
                            <p class="text-dark"><%= detail_demande.getPrenomUtilisateur() %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Type de congé :</h6>
                            <p class="text-dark"><%= detail_demande.getTypeConge() %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Date de demande :</h6>
                            <p class="text-dark"><%= DateUtil.formatDate(detail_demande.getDateDemande()) %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Date début :</h6>
                            <p class="text-dark"><%= DateUtil.formatDate(detail_demande.getDateDebut()) %> <%= detail_demande.getDebutAbsence() %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Date fin :</h6>
                            <p class="text-dark"><%= DateUtil.formatDate(detail_demande.getDateFin()) %> <%= detail_demande.getFinAbsence() %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Nombre de jours :</h6>
                            <p class="text-dark"><%= detail_demande.getNombreJoursConge() %> Jours</p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">Commentaire :</h6>
                            <p class="text-dark"><%= detail_demande.getCommentaire() %></p>
                        </div>
                        <div class="col-sm-6 mb-3">
                            <h6 class="font-weight-bold text-muted">État de demande :</h6>
                            <p class="text-dark">
                                <span class="<%= badgeClass %>"><%= detail_demande.getEtatDemande() %></span>
                            </p>
                        </div>
                    </div>
                    <% if (!detail_demande.getIdEtatDemande().equals(10) && !detail_demande.getIdEtatDemande().equals(15)
                            && type_utilisateur != 15 && type_utilisateur != 1) { %>
                        <div class="text-center mt-4">
                            <a href="" class="btn btn-outline-danger mr-2" data-toggle="modal" data-target="#refuserModal<%= detail_demande.getIdDemandeConge() %>">
                                <i class="fas fa-times-circle"></i> Annuler
                            </a>
                            <a href="" class="btn btn-outline-success" data-toggle="modal" data-target="#validerModal<%= detail_demande.getIdDemandeConge() %>">
                                <i class="fas fa-check-circle"></i> Valider
                            </a>
                        </div>
                    <% } %>
                </div>
            </div>
        </div>
    </div>
</div>

<!-- Valider demande conge -->
<div class="modal fade custom-modal" id="validerModal<%= detail_demande.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="validerLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered custom-modal-sm" role="document">
        <div class="modal-content custom-modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title" id="validerLabel">
                    <i class="mdi mdi-check-circle-outline"></i> Validation de demande de congé
                </h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <i class="mdi mdi-check-circle mdi-36px text-success"></i>
                <p class="mt-3" style="font-size: 1rem;">
                    Confirmez-vous la validation de cette demande de congé ?
                </p>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <a href="/demande_conge/valider-conge?etat=10&&idDemande=<%= detail_demande.getIdDemandeConge() %>" class="btn btn-success btn-sm">
                    <i class="mdi mdi-check"></i> Oui, valider
                </a>
                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                    <i class="mdi mdi-close"></i> Retour
                </button>
            </div>
        </div>
    </div>
</div>

<div class="modal fade custom-modal" id="refuserModal<%= detail_demande.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="refuserLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered custom-modal-sm" role="document">
        <div class="modal-content custom-modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="refuserLabel">
                    <i class="mdi mdi-close-circle-outline"></i> Refus de demande de congé
                </h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body text-center">
                <i class="mdi mdi-close-circle mdi-36px text-danger"></i>
                <p class="mt-3" style="font-size: 1rem;">
                    Voulez-vous vraiment refuser cette demande de congé ?
                </p>
            </div>
            <div class="modal-footer d-flex justify-content-center">
                <a href="/demande_conge/refuser-conge?etat=15&&idDemande=<%= detail_demande.getIdDemandeConge() %>" class="btn btn-danger btn-sm">
                    <i class="mdi mdi-close"></i> Oui, refuser
                </a>
                <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                    <i class="mdi mdi-close"></i> Retour
                </button>
            </div>
        </div>
    </div>
</div>

<%@include file="../utils/footer.jsp" %>
