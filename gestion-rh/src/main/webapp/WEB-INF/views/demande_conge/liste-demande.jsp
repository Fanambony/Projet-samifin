<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.List" %>

<%
    // Récupération des données de la requête
    List<VEtatDemande> demande = (List<VEtatDemande>) request.getAttribute("demande");
    int currentPage = (Integer) request.getAttribute("currentPage");
    int totalPages = (Integer) request.getAttribute("totalPages");
    int type_utilisateur = (Integer)session.getAttribute("userType");
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

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">VALIDATION OU REFUS DE DEMANDE DE CONGE</h4>
            <div class="table-responsive">

                <br>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Date demande</th>
                            <th>Agent</th>
                            <th>Type congé</th>
                            <th>Date début</th>
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
                                    <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" title="Voir détails" data-target="#detailModal<%= de.getIdDemandeConge() %>">
                                        <i class="ti-eye"></i>
                                    </button>
                                    <% if (!de.getIdEtatDemande().equals(10) && !de.getIdEtatDemande().equals(15)
                                            && type_utilisateur != 15 && type_utilisateur != 1) { %>
                                        <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" title="Accepter" data-target="#validerModal<%= de.getIdDemandeConge() %>">
                                            <i class="ti-check"></i>
                                        </button>
                                        <button type="button" class="btn btn-danger btn-rounded btn-icon" data-toggle="modal" title="Refuser" data-target="#refuserModal<%= de.getIdDemandeConge() %>">
                                            <i class="ti-close"></i>
                                        </button>
                                    <% } %>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>

                <br>

                <!-- Pagination -->
                <div class="pagination d-flex justify-content-center">
                    <nav aria-label="Page navigation">
                        <ul class="pagination justify-content-center">
                            <li class="page-item <%= currentPage == 1 ? "disabled" : "" %>">
                                <a class="page-link" href="?page=<%= currentPage - 1 %>" aria-label="Previous">
                                    <span aria-hidden="true">&laquo;</span>
                                </a>
                            </li>
                
                            <% 
                                int maxPagesToShow = 4;
                                int startPage = Math.max(1, currentPage - maxPagesToShow / 2);
                                int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);
                
                                if (startPage > 1) {
                            %>
                                <li class="page-item"><a class="page-link" href="?page=1">1</a></li>
                                <% if (startPage > 2) { %>
                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                <% } %>
                            <% } %>
                
                            <% for (int i = startPage; i <= endPage; i++) { %>
                                <li class="page-item <%= currentPage == i ? "active" : "" %>">
                                    <a class="page-link" href="?page=<%= i %>"><%= i %></a>
                                </li>
                            <% } %>
                
                            <% if (endPage < totalPages) { %>
                                <% if (endPage < totalPages - 1) { %>
                                    <li class="page-item disabled"><span class="page-link">...</span></li>
                                <% } %>
                                <li class="page-item"><a class="page-link" href="?page=<%= totalPages %>"><%= totalPages %></a></li>
                            <% } %>
                
                            <li class="page-item <%= currentPage == totalPages ? "disabled" : "" %>">
                                <a class="page-link" href="?page=<%= currentPage + 1 %>" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                            </li>
                        </ul>
                    </nav>
                </div>
                

            </div>
        </div>
    </div>
</div>

<!-- Modal detail information -->
<% for(VEtatDemande de : demande) { %>
    <div class="modal fade custom-modal custom-detail-modal" id="detailModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="detailLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header bg-info text-white">
                    <h5 class="modal-title" id="detailLabel">
                        <i class="ti-info-alt"></i> Détails de la demande pour <%= de.getNomUtilisateur() %> <%= de.getPrenomUtilisateur() %>
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
                        <i class="mdi mdi-arrow-left"></i> Retour
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>



<!-- Modal valider conge -->
<% for(VEtatDemande de : demande) { %>
    <div class="modal fade custom-modal" id="validerModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="validerLabel" aria-hidden="true">
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
                    <a href="/demande_conge/valider-conge?etat=10&&idDemande=<%= de.getIdDemandeConge() %>" class="btn btn-success btn-sm">
                        <i class="mdi mdi-check"></i> Oui, valider
                    </a>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="mdi mdi-close"></i> Retour
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>

<!-- Modal refuser conge -->
<% for(VEtatDemande de : demande) { %>
    <div class="modal fade custom-modal" id="refuserModal<%= de.getIdDemandeConge() %>" tabindex="-1" role="dialog" aria-labelledby="refuserLabel" aria-hidden="true">
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
                    <a href="/demande_conge/refuser-conge?etat=15&&idDemande=<%= de.getIdDemandeConge() %>" class="btn btn-danger btn-sm">
                        <i class="mdi mdi-close"></i> Oui, refuser
                    </a>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="mdi mdi-close"></i> Retour
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>


<%@include file="../utils/footer.jsp" %>
