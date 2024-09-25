<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Genre" %>
<%@ page import="com.example.gestionrh.Model.Entity.EtatUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Filiation" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Page<Utilisateur> utilisateur = (Page<Utilisateur>)request.getAttribute("utilisateurs");
    List<Direction> direction = (List<Direction>)request.getAttribute("directions");
    List<Fonction> fonction = (List<Fonction>)request.getAttribute("fonctions");
    List<Genre> genre = (List<Genre>)request.getAttribute("genre");
    List<EtatUtilisateur> etat = (List<EtatUtilisateur>)request.getAttribute("etat");
%>

<%@include file="../utils/header.jsp" %>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Liste des utilisateurs</h4>
            <div class="table-responsive">

                <div>
                    <a href="/ajout-utilisateur"><button type="button" class="btn btn-outline-primary btn-fw">Ajouter une nouvelle utilisateur</button></a>
                </div>

                </br>
                    
                <div class="col-md-12">
                    <div class="form-group row">
                        <label class="col-sm-1 col-form-label">Direction :</label>
                        <div class="col-sm-3">
                            <select class="form-control">
                                <option value="">Toutes les directions</option>
                                <% for(Direction d : direction) { %>
                                    <option><%= d.getNom() %></option>
                                <% } %>
                            </select>
                        </div>
                        <label class="col-sm-1 col-form-label">Etat :</label>
                        <div class="col-sm-3">
                            <select class="form-control">
                                <option value="">Toutes les etats</option>
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
                            <th>Image</th>
                            <th>Matricule</th>
                            <th>Agent</th>
                            <th>Direction</th>
                            <th>Etat</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            for(Utilisateur u : utilisateur) { 
                                byte[] imageBytes = u.getImage();
                                String imageUtilisateur = (imageBytes != null && imageBytes.length > 0) ? Base64.getEncoder().encodeToString(imageBytes) : null;
                                String imageParDefaut = "/assets/images/faces/user.jpeg";
                                String imageSrc = (imageUtilisateur != null && !imageUtilisateur.isEmpty()) ? "data:image/jpeg;base64, " + imageUtilisateur : imageParDefaut;
                        %>
                        <tr>
                            <td class="py-1">
                                <img src="<%= imageSrc %>" alt="image" class="img-fluid rounded-circle" style="width: 50px; height: 50px;" />
                            </td>
                            <td>
                                <% for (DetailUtilisateur du : u.getDetailUtilisateurs()) { %>
                                    <div><%= du.getMatricule() %></div>
                                <% } %>
                            </td>
                            <td><%= u.getNom() %> <%= u.getPrenom() %></td>
                            <td>
                                <% for (DetailUtilisateur du : u.getDetailUtilisateurs()) { %>
                                    <div><%= du.getFonction().getDirection().getNom() %></div>
                                <% } %>
                            </td>
                            <td>
                                <label class="badge 
                                    <%= ("Activé".equals(u.getEtat_utilisateur().getLibelle())) ? "badge-success" : 
                                        ("Désactivé".equals(u.getEtat_utilisateur().getLibelle())) ? "badge-danger" : 
                                        "badge-warning" %>">
                                    <%= u.getEtat_utilisateur().getLibelle() %>
                                </label>
                            </td>
                            
                            <td>
                                <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" title="Voir les détails" data-target="#userDetailModal<%= u.getId() %>">
                                    <i class="ti-eye"></i>
                                </button>
                                <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" title="Modifier l'utilisateur" data-target="#userModifModal<%= u.getId() %>">
                                    <i class="ti-pencil"></i>
                                </button>

                                <a href="/famille/gererFamille?idUtilisateur=<%= u.getId() %>"><button type="button" class="btn btn-warning btn-rounded btn-icon" title="Gérer la famille">
                                    <i class="mdi mdi-account-multiple"></i>
                                </button></a>
                            </td>
                            
                        </tr>
                        <% } %>
                    </tbody>
                </table>

                <br>
                
                <div class="pagination">
                    <ul class="pagination">
                        <li class="page-item <%= utilisateur.isFirst() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= utilisateur.getNumber() - 1 %>&size=<%= utilisateur.getSize() %>"><< Précédent</a>
                        </li>
                
                        <% for (int i = 0; i < utilisateur.getTotalPages(); i++) { %>
                            <li class="page-item <%= (i == utilisateur.getNumber()) ? "active" : "" %>">
                                <a class="page-link" href="?page=<%= i %>&size=<%= utilisateur.getSize() %>"><%= i + 1 %></a>
                            </li>
                        <% } %>
                
                        <li class="page-item <%= utilisateur.isLast() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= utilisateur.getNumber() + 1 %>&size=<%= utilisateur.getSize() %>">Suivant >></a>
                        </li>
                    </ul>
                </div>
                

            </div>
        </div>
    </div>
</div>


<!-- Modal détails utilisateur -->

<% 
    for(Utilisateur u : utilisateur) { 
        byte[] imageBytes = u.getImage();
        String imageUtilisateur = (imageBytes != null && imageBytes.length > 0) ? Base64.getEncoder().encodeToString(imageBytes) : null;
        String imageParDefaut = "/assets/images/faces/user.jpeg";
        String imageSrc = (imageUtilisateur != null && !imageUtilisateur.isEmpty()) ? "data:image/jpeg;base64, " + imageUtilisateur : imageParDefaut;
    %>
    <div class="modal fade custom-modal" id="userDetailModal<%= u.getId() %>" tabindex="-1" role="dialog" aria-labelledby="userDetailModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userDetailModalLabel">Profil de l'utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="userDetailContent">
                        <!-- Contenu du modal -->
                        <div class="row">
                            <!-- Colonne gauche pour les informations -->
                            <div class="col-md-4">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <strong>Nom :</strong> <%= u.getNom() %>
                                    </li>
                                    <li class="list-group-item">
                                        <strong>Prénom :</strong> <%= u.getPrenom() %>
                                    </li>
                                    <li class="list-group-item">
                                        <strong>Genre :</strong> <%= u.getGenre().getLibelle() %>
                                    </li>
                                    <li class="list-group-item">
                                        <strong>Date de naissance :</strong> <%= DateUtil.formatDate(u.getDateNaissance()) %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Email :</strong> <%= u.getDetailUtilisateurs().get(0).getEmail() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Téléphone :</strong> <%= u.getDetailUtilisateurs().get(0).getTelephone() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Matricule :</strong> <%= u.getDetailUtilisateurs().get(0).getMatricule() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Date d'entrée :</strong> <%= DateUtil.formatDate(u.getDetailUtilisateurs().get(0).getDateEntre()) %>
                                    </li>
                                    <li class="list-group-item">
                                        <strong>État :</strong>
                                        <span class="badge <%= ("Activé".equals(u.getEtat_utilisateur().getLibelle())) ? "badge-success" : "badge-danger" %>">
                                            <%= ("Activé".equals(u.getEtat_utilisateur().getLibelle())) 
                                                ? "<i class='fas fa-check-circle'></i> " + u.getEtat_utilisateur().getLibelle() 
                                                : "<i class='fas fa-times-circle'></i> " + u.getEtat_utilisateur().getLibelle() %>
                                        </span>
                                    </li>
                                    
                                    
                                    
                                </ul>
                            </div>
                            <!-- Colonne du milieu pour la photo de profil -->
                            <div class="col-md-4 text-center">
                                <img src="<%= imageSrc %>" class="img-fluid rounded-circle mb-3" alt="Photo de profil">
                                <h4><%= u.getPrenom() %> <%= u.getNom() %></h4>
                                <p><%= u.getType_utilisateur().getLibelle() %></p>
                            </div>
                            <!-- Colonne droite pour les informations -->
                            <div class="col-md-4">
                                <ul class="list-group">
                                    <li class="list-group-item" >
                                        <strong>Numero de decision :</strong> <%= u.getDetailUtilisateurs().get(0).getNumeroDecision() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Direction :</strong> <%= u.getDetailUtilisateurs().get(0).getFonction().getDirection().getNom() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Fonction :</strong> <%= u.getDetailUtilisateurs().get(0).getFonction().getNom() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Qualité :</strong> <%= u.getDetailUtilisateurs().get(0).getQualite() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Catégorie :</strong> <%= u.getDetailUtilisateurs().get(0).getCategorie() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Corps d'appartenance :</strong> <%= u.getDetailUtilisateurs().get(0).getCorpsAppartenance() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Indice :</strong> <%= u.getDetailUtilisateurs().get(0).getIndice() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Service Employeur :</strong> <%= u.getDetailUtilisateurs().get(0).getServiceEmployeur() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Localite de service :</strong> <%= u.getDetailUtilisateurs().get(0).getLocaliteService() %>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>
<% } %>
<!-- <% 
    for(Utilisateur u : utilisateur) { 
        byte[] imageBytes = u.getImage();
        String imageUtilisateur = (imageBytes != null && imageBytes.length > 0) ? Base64.getEncoder().encodeToString(imageBytes) : null;
        String imageParDefaut = "/assets/images/faces/user.jpeg";
        String imageSrc = (imageUtilisateur != null && !imageUtilisateur.isEmpty()) ? "data:image/jpeg;base64, " + imageUtilisateur : imageParDefaut;
    %>
    <div class="modal fade custom-modal" id="userDetailModal<%= u.getId() %>" tabindex="-1" role="dialog" aria-labelledby="userDetailModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-lg modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header bg-primary text-white">
                    <h5 class="modal-title" id="userDetailModalLabel">Profil de l'utilisateur</h5>
                    <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="userDetailContent">
                        <div class="row">
                            <div class="col-md-4">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <i class="mdi mdi-account"></i> <strong>Nom :</strong> <%= u.getNom() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-account"></i> <strong>Prénom :</strong> <%= u.getPrenom() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-gender-male-female"></i> <strong>Genre :</strong> <%= u.getGenre().getLibelle() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-calendar"></i> <strong>Date de naissance :</strong> <%= DateUtil.formatDate(u.getDateNaissance()) %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-account-box"></i> <strong>Matricule :</strong> <%= u.getDetailUtilisateurs().get(0).getMatricule() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-email"></i> <strong>Email :</strong> <%= u.getDetailUtilisateurs().get(0).getEmail() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-phone"></i> <strong>Téléphone :</strong> <%= u.getDetailUtilisateurs().get(0).getTelephone() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-calendar"></i> <strong>Date d'entrée :</strong> <%= DateUtil.formatDate(u.getDetailUtilisateurs().get(0).getDateEntre()) %>
                                    </li>
                                </ul>
                            </div>
                            <div class="col-md-4 text-center">
                                <img src="<%= imageSrc %>" class="img-fluid rounded-circle border mb-3" alt="Photo de profil" style="width: 150px; height: 150px;">
                                <h4><%= u.getPrenom() %> <%= u.getNom() %></h4>
                                <p class="text-muted"><%= u.getType_utilisateur().getLibelle() %></p>
                            </div>
                            <div class="col-md-4">
                                <ul class="list-group">
                                    <li class="list-group-item">
                                        <i class="mdi mdi-domain"></i> <strong>Direction :</strong> <%= u.getDetailUtilisateurs().get(0).getFonction().getDirection().getNom() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-account-tie"></i> <strong>Fonction :</strong> <%= u.getDetailUtilisateurs().get(0).getFonction().getNom() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-star"></i> <strong>Qualité :</strong> <%= u.getDetailUtilisateurs().get(0).getQualite() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-sitemap"></i> <strong>Catégorie :</strong> <%= u.getDetailUtilisateurs().get(0).getCategorie() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-shield-account"></i> <strong>Corps d'appartenance :</strong> <%= u.getDetailUtilisateurs().get(0).getCorpsAppartenance() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-account-badge"></i> <strong>Indice :</strong> <%= u.getDetailUtilisateurs().get(0).getIndice() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-office-building"></i> <strong>Service Employeur :</strong> <%= u.getDetailUtilisateurs().get(0).getServiceEmployeur() %>
                                    </li>
                                    <li class="list-group-item">
                                        <i class="mdi mdi-map-marker"></i> <strong>Localité de service :</strong> <%= u.getDetailUtilisateurs().get(0).getLocaliteService() %>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer bg-light">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>
<% } %> -->


<!-- Modal modifier utilisateur -->
<% for(Utilisateur u : utilisateur) { %>
    <div class="modal fade custom-modal" id="userModifModal<%= u.getId() %>" tabindex="-1" role="dialog" aria-labelledby="userDetailModalLabel" aria-hidden="true">
        <div class="modal-dialog modal-xl modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userDetailModalLabel">Modification de l'utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="userDetailContent">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label">Nom</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="nom" value="<%= u.getNom() %>">
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group row">
                                    <label class="col-sm-4 col-form-label">Prénom</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="prenom" value="<%= u.getPrenom() %>">
                                    </div>
                                </div>
                            </div>
                        </div>

                        <% for(DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Matricule</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="matricule" value="<%= detail.getMatricule() %>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Date d'Entrée</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="dateEntree" value="<%= detail.getDateEntre() %>">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Email</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="email" value="<%= detail.getEmail() %>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Téléphone</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="telephone" value="<%= detail.getTelephone() %>">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Direction</label>
                                        <div class="col-sm-8">
                                            <select id="direction" name="direction" class="form-control">
                                                <% for(Direction d : direction) { %>
                                                    <option value="<%= d.getId() %>" <%= d.getId().equals(detail.getFonction().getDirection().getId()) ? "selected" : "" %>><%= d.getNom() %></option>
                                                <% } %>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Fonction</label>
                                        <div class="col-sm-8">
                                            <select id="fonction" name="fonction" class="form-control" data-selected-function="<%= detail.getFonction().getId() %>">
                                                <!-- Les options seront mises à jour dynamiquement par JavaScript -->
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Qualité</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="qualite" value="<%= detail.getQualite() %>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Catégorie</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="categorie" value="<%= detail.getCategorie() %>">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Corps d'appartenance</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="appartenance" value="<%= detail.getCorpsAppartenance() %>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Indice</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="indice" value="<%= detail.getIndice() %>">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Service employeur</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="employeur" value="<%= detail.getServiceEmployeur() %>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Localité de service</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" id="localite" value="<%= detail.getLocaliteService() %>">
                                        </div>
                                    </div>
                                </div>
                            </div>
                        <% } %>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>
<% } %>

<script src="/assets/js/jquery-3.7.1.min.js"></script>
<script>
    $(document).ready(function() {
        $('.modal').on('shown.bs.modal', function () {
            var modal = $(this);
            var directionSelect = modal.find('#direction');
            var fonctionSelect = modal.find('#fonction');
            var selectedFunctionId = fonctionSelect.data('selected-function');

            // Fonction pour charger les fonctions pour une direction donnée
            function loadFonctions(directionId) {
                $.ajax({
                    url: '/fonction/getFonctionsByDirection',
                    type: 'GET',
                    data: { directionId: directionId },
                    success: function(fonctions) {
                        fonctionSelect.empty(); // Vider les options actuelles
                        $.each(fonctions, function(index, fonction) {
                            var option = new Option(fonction.nom, fonction.id);
                            if (fonction.id === selectedFunctionId) {
                                $(option).prop('selected', true);
                            }
                            fonctionSelect.append(option);
                        });
                    },
                    error: function(xhr, status, error) {
                        console.error('Erreur lors de la récupération des fonctions:', error);
                    }
                });
            }

            // Lors de l'ouverture du modal, initialisez les fonctions
            var selectedDirectionId = directionSelect.val();
            loadFonctions(selectedDirectionId);

            // Mettre à jour les fonctions lorsque la direction change
            directionSelect.on('change', function() {
                var newDirectionId = $(this).val();
                loadFonctions(newDirectionId);
            });
        });
    });
</script>



<style>
    .custom-modal .modal-dialog {
        margin-top: 1rem;
    }

    .switch input{
        display: none;
    }
    .slider{
        position: relative;
        cursor: pointer;
        display: inline-block;
        width: 50px;
        height: 24px;
    }
    .slider:before{
        content: "";
        position: absolute;
        background-color: #ccc;
        transition: .4s;
        border-radius: 24px;
        width: 50px;
        height: 24px;
    }
    .slider:after{
        content: "";
        position: absolute;
        background-color: rgb(255, 255, 255);
        transition: .4s;
        border-radius: 50%;
        width: 16px;
        height: 16px;
        bottom: 4px;
        left: 4px;
    }
    input:checked + .slider:before{
        background-color: #008234;
    }
    input:checked + .slider:after{
        transform: translateX(26px);
    }
    .mdi-icon {
        font-size: 1.2rem;
        color: white;
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

    
    /* .custom-modal .modal-content {
        border-radius: 15px;
        box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
    }

    .custom-modal .modal-header {
        background-color: #4e73df;
        color: white;
        border-bottom: none;
        border-radius: 15px 15px 0 0;
    }

    .custom-modal .modal-title {
        font-weight: bold;
    }

    .custom-modal .close {
        color: white;
        opacity: 1;
    }

    .custom-modal .modal-body {
        padding: 20px 30px;
        background-color: #f8f9fc;
    }

    .custom-modal .modal-footer {
        border-top: none;
        padding: 15px 30px;
    }

    .custom-modal .form-group label {
        font-weight: bold;
        color: #5a5c69;
    }

    .custom-modal .form-control {
        border-radius: 10px;
        border: 1px solid #d1d3e2;
    }

    .custom-modal .btn-secondary {
        background-color: #858796;
        border-radius: 8px;
    }

    .custom-modal .section-header {
        font-weight: bold;
        font-size: 1.1rem;
        color: #4e73df;
        margin: 10px 0;
        border-bottom: 1px solid #ddd;
        padding-bottom: 5px;
    }

    .custom-modal .row {
        margin-bottom: 15px;
    } */
</style>

<%@include file="../utils/footer.jsp" %>