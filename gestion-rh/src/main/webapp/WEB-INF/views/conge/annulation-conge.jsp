<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="org.springframework.data.domain.Page" %>

<%
    Page<VEtatDemande> demande_valider = (Page<VEtatDemande>)request.getAttribute("list_demande_valider");
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
            <h4 class="card-title">ANNULATION DE DEMANDE DE CONGE</h4>
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

                <br>
                
                <div class="pagination d-flex justify-content-center">
                    <ul class="pagination">
                        <!-- Lien vers la page précédente -->
                        <li class="page-item <%= demande_valider.isFirst() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= demande_valider.getNumber() - 1 %>&size=<%= demande_valider.getSize() %>">&laquo;</a>
                        </li>
                
                        <% 
                            int maxPagesToShow = 4;
                            int currentPage = demande_valider.getNumber() + 1; // Passe en index 1-based
                            int totalPages = demande_valider.getTotalPages();
                            int startPage = Math.max(1, currentPage - maxPagesToShow / 2);
                            int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);
                
                            if (startPage > 1) { 
                        %>
                            <li class="page-item"><a class="page-link" href="?page=0&size=<%= demande_valider.getSize() %>">1</a></li>
                            <% if (startPage > 2) { %>
                                <li class="page-item disabled"><span class="page-link">...</span></li>
                            <% } %>
                        <% } %>
                
                        <!-- Liens pour les pages visibles -->
                        <% for (int i = startPage; i <= endPage; i++) { %>
                            <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                                <a class="page-link" href="?page=<%= i - 1 %>&size=<%= demande_valider.getSize() %>"><%= i %></a>
                            </li>
                        <% } %>
                
                        <% if (endPage < totalPages) { %>
                            <% if (endPage < totalPages - 1) { %>
                                <li class="page-item disabled"><span class="page-link">...</span></li>
                            <% } %>
                            <li class="page-item"><a class="page-link" href="?page=<%= totalPages - 1 %>&size=<%= demande_valider.getSize() %>"><%= totalPages %></a></li>
                        <% } %>
                
                        <!-- Lien vers la page suivante -->
                        <li class="page-item <%= demande_valider.isLast() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= demande_valider.getNumber() + 1 %>&size=<%= demande_valider.getSize() %>">&raquo;</a>
                        </li>
                    </ul>
                </div>
                
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
                    <!-- Formulaire avec le motif d'annulation -->
                    <form method="post" action="/demande_conge/annuler-conge">
                        <!-- Champ caché pour envoyer l'ID de la demande -->
                        <input type="hidden" name="idDemande" value="<%= dv.getIdDemandeConge() %>" />

                        <!-- Champ texte pour le motif d'annulation -->
                        <textarea name="motif" class="form-control" rows="3" placeholder="Veuillez indiquer le motif d'annulation" required></textarea>

                        <!-- Boutons du modal -->
                        <div class="modal-footer d-flex justify-content-center">
                            <button type="submit" class="btn btn-danger btn-sm">
                                <i class="ti-back-left"></i> Oui, annuler
                            </button>
                            <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                                <i class="ti-close"></i> Non, revenir
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
<% } %>


<%@include file="../utils/footer.jsp" %>