<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Filiation" %>
<%@ page import="com.example.gestionrh.Model.Entity.Famille" %>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Utilisateur utilisateur = (Utilisateur)request.getAttribute("utilisateur");
    List<Filiation> filiation = (List<Filiation>)request.getAttribute("filiations");
%>


<%@include file="../utils/header.jsp" %>

<style>
    .custom-modal .modal-dialog {
        margin-top: 0%;
    }
    .custom-modal-sm {
        max-width: 450px; /* Augmenter la largeur */
    }
    .custom-modal-content {
        min-height: 250px; /* Réduire la hauteur */
    }
    
</style>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Membres de la Famille de <%= utilisateur.getNom() %> <%= utilisateur.getPrenom() %></h4>
            <div class="table-responsive">

                <div class="row mb-3">
                    <div class="col-md-3">
                        <button type="button" class="btn btn-outline-primary btn-fw btn-block" data-toggle="modal" title="Ajouter Famille" data-target="#addFamilyModal">
                            <i class="mdi mdi-account-multiple-plus mdi-icon"></i> Ajouter Famille
                        </button>
                    </div>
                </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Nom</th>
                            <th>Prénom</th>
                            <th>Filiation</th>
                            <th>Date de naissance</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Famille f : utilisateur.getFamilles()) { %>
                            <tr>
                                <td><%= f.getNom() %></td>
                                <td><%= f.getPrenom() %></td>
                                <td><%= f.getFiliation().getFiliation() %></td>
                                <td><%= DateUtil.formatDate(f.getDateNaissance()) %></td>
                                <td>
                                    <div class="d-flex align-items-center justify-content-start">
                                        <div style="width: 50px;">
                                            <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" title="Voir les détails" data-target="#modifierModal<%= f.getId() %>">    
                                                <i class="ti-pencil"></i>
                                            </button>
                                        </div>
                                        <div style="width: 50px;">
                                            <button type="button" class="btn btn-danger btn-rounded btn-icon" data-toggle="modal" title="Supprimer" data-target="#deleteConfirmationModal<%= f.getId() %>">
                                                <i class="ti-trash"></i>
                                            </button>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>

            </div>
        </div>
    </div>
</div>

<!-- Modal Ajouter Famille -->
<div class="modal fade custom-modal" id="addFamilyModal" tabindex="-1" role="dialog" aria-labelledby="addFamilyModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addFamilyModalLabel">Ajouter Famille</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form action="/famille/ajouterFamille" method="post">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nomFamille">Nom</label>
                        <input type="text" class="form-control" name="nom" required>
                    </div>
                    <div class="form-group">
                        <label for="prenomFamille">Prénom</label>
                        <input type="text" class="form-control" name="prenom" required>
                    </div>
                    <div class="form-group">
                        <label for="dateNaissanceFamille">Date de naissance</label>
                        <input type="date" class="form-control" name="date_naissance" required>
                    </div>
                    <div class="form-group">
                        <label for="filiationFamille">Filiation</label>
                        <select class="form-control" name="filiation">
                            <% for(Filiation f : filiation) { %>
                                <option value="<%= f.getId() %>"><%= f.getFiliation() %></option>
                            <% } %>
                        </select>
                    </div>
                    <input type="hidden" name="id_utilisateur" value="<%= utilisateur.getId() %>">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                    <button type="submit" class="btn btn-primary">Ajouter</button>
                </div>
            </form>
        </div>
    </div>
</div>


<!-- Modal de confirmation de suppression -->
<% for(Famille f : utilisateur.getFamilles()) { %>
    <div class="modal fade custom-modal" id="deleteConfirmationModal<%= f.getId() %>" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmationLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered custom-modal-sm" role="document">
            <div class="modal-content custom-modal-content">
                <!-- Header stylé avec couleur et icône -->
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
                    <i class="mdi mdi-account-remove mdi-36px text-danger"></i>
                    <p class="mt-3" style="font-size: 1rem;">
                        Confirmez-vous la suppression de <strong><%= f.getNom() %> <%= f.getPrenom() %></strong>
                        de la famille de <strong><%= utilisateur.getNom() %> <%= utilisateur.getPrenom() %></strong> ?
                    </p>
                </div>
                <!-- Footer stylé avec des boutons plus petits -->
                <div class="modal-footer d-flex justify-content-center">
                    <a href="/famille/suprimerMembre?idFamille=<%= f.getId() %>&&idUtilisateur=<%= utilisateur.getId() %>" id="confirmDeleteBtn" class="btn btn-danger btn-sm">
                        <i class="mdi mdi-check"></i> Oui, supprimer
                    </a>
                    <button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">
                        <i class="mdi mdi-close"></i> Annuler
                    </button>
                </div>
            </div>
        </div>
    </div>
<% } %>


<!-- Modal Modifier Famille -->
<% for(Famille f : utilisateur.getFamilles()) { %>
    <div class="modal fade custom-modal" id="modifierModal<%= f.getId() %>" tabindex="-1" role="dialog" aria-labelledby="modifierModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="modifierModalLabel">Modifier Information pour la Famille</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <form action="/famille/modifierFamille" method="post">
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="nomFamille">Nom</label>
                            <input type="text" class="form-control" name="nom" value="<%= f.getNom() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="prenomFamille">Prénom</label>
                            <input type="text" class="form-control" name="prenom" value="<%= f.getPrenom() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="dateNaissanceFamille">Date de naissance</label>
                            <input type="date" class="form-control" name="date_naissance" value="<%= f.getDateNaissance() %>" required>
                        </div>
                        <div class="form-group">
                            <label for="filiationFamille">Filiation</label>
                            <select class="form-control" name="filiation">
                                <% for(Filiation fil : filiation) { %>
                                    <option value="<%= fil.getId() %>" <%= f.getFiliation().getId().equals(fil.getId()) ? "selected" : "" %>><%= fil.getFiliation() %></option>
                                <% } %>
                            </select>
                        </div>
                        <input type="hidden" name="id_famille" value="<%= f.getId() %>">
                        <input type="hidden" name="id_utilisateur" value="<%= utilisateur.getId() %>">
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                        <button type="submit" class="btn btn-primary">Enregistrer la modification</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
<% } %>

<%@include file="../utils/footer.jsp" %>