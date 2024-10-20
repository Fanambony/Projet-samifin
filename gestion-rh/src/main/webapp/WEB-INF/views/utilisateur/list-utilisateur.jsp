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
<%@ page import="com.example.gestionrh.Model.Entity.TypeUtilisateur" %>

<%@ page import="com.example.gestionrh.Model.Entity.Categorie" %>
<%@ page import="com.example.gestionrh.Model.Entity.Qualite" %>
<%@ page import="com.example.gestionrh.Model.Entity.ServiceEmployeur" %>
<%@ page import="com.example.gestionrh.Model.Entity.LocaliteService" %>
<%@ page import="com.example.gestionrh.Model.Entity.Indice" %>
<%@ page import="com.example.gestionrh.Model.Entity.CorpsAppartenance" %>
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
    List<TypeUtilisateur> typeUtilisateur = (List<TypeUtilisateur>)request.getAttribute("typeUtilisateurs");

    List<Categorie> categories = (List<Categorie>)request.getAttribute("categories");
    List<Qualite> qualites = (List<Qualite>)request.getAttribute("qualites");
    List<ServiceEmployeur> serviceEmployeurs = (List<ServiceEmployeur>)request.getAttribute("serviceEmployeurs");
    List<LocaliteService> localiteServices = (List<LocaliteService>)request.getAttribute("localiteServices");
    List<Indice> indices = (List<Indice>)request.getAttribute("indices");
    List<CorpsAppartenance> corpsAppartenances = (List<CorpsAppartenance>)request.getAttribute("corpsAppartenances");
%>

<%@include file="../utils/header.jsp" %>


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


    /* Conteneur du switch */
    .switch {
        position: relative;
        display: inline-block;
        width: 60px;
        height: 34px;
    }

    /* Cacher l'input checkbox */
    .switch input {
        opacity: 0;
        width: 0;
        height: 0;
    }

    /* Style de la glissière (slider) */
    .slider {
        position: absolute;
        cursor: pointer;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;
        background-color: #ccc;
        transition: .4s;
    }

    .slider:before {
        position: absolute;
        content: "";
        height: 0px;
        width: 0px;
        left: 4px;
        bottom: 4px;
        background-color: white;
        transition: .4s;
    }

    /* Couleur lorsque l'état est activé */
    input:checked + .slider {
        background-color: #4CAF50; /* Vert pour activé */
    }

    /* Style de l'effet de focus */
    input:focus + .slider {
        box-shadow: 0 0 1px #4CAF50;
    }

    /* Animation de déplacement du cercle lorsque coché */
    input:checked + .slider:before {
        transform: translateX(26px);
    }

    /* Rendre le switch circulaire */
    .slider.round {
        border-radius: 34px;
    }

    .slider.round:before {
        border-radius: 50%;
    }

    /* Espacement des légendes */
    .ml-2 {
        margin-left: 10px;
    }

    .mr-2 {
        margin-right: 10px;
    }

    .d-flex {
        display: flex;
        align-items: center;
    }
</style>


<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">GESTION DES UTILISATEURS</h4>
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
                                        <strong>Qualité :</strong> <%= u.getDetailUtilisateurs().get(0).getQualite().getLibelle() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Catégorie :</strong> <%= u.getDetailUtilisateurs().get(0).getCategorie().getLibelle() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Corps d'appartenance :</strong> <%= u.getDetailUtilisateurs().get(0).getCorps_appartenance().getLibelle() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Indice :</strong> <%= u.getDetailUtilisateurs().get(0).getIndice().getLibelle() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Service Employeur :</strong> <%= u.getDetailUtilisateurs().get(0).getService_employeur().getLibelle() %>
                                    </li>
                                    <li class="list-group-item" >
                                        <strong>Localite de service :</strong> <%= u.getDetailUtilisateurs().get(0).getLocalite_service().getLibelle() %>
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
                <form class="form-sample" method="post" action="modifierUtilisateur">
                    <div class="modal-body">
                        <div id="userDetailContent">
                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Nom</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="nom" id="nom" value="<%= u.getNom() %>">
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Prénom</label>
                                        <div class="col-sm-8">
                                            <input type="text" class="form-control" name="prenom" id="prenom" value="<%= u.getPrenom() %>">
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Date de naissance</label>
                                        <div class="col-sm-8">
                                            <input type="date" class="form-control" name="dtn" id="dtn" value="<%= u.getDateNaissance() %>">
                                            <span id="error-date-naissance" style="color: red; display: none;">L'utilisateur doit avoir au moins 18 ans.</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-6">
                                    <div class="form-group row">
                                        <label class="col-sm-4 col-form-label">Genre</label>
                                        <div class="col-sm-8">
                                            <select name="genre" id="genre" class="form-control">
                                                <% for(Genre g : genre) {
                                                    String selected = g.getEtat().equals(u.getGenre().getEtat()) ? "selected" : "";
                                                %>
                                                    <option value="<%= g.getEtat() %>" <%= selected %>><%= g.getLibelle() %></option>
                                                <% } %>
                                            </select>
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
                                                <input type="text" class="form-control" name="matricule" id="matricule" value="<%= detail.getMatricule() %>">
                                                <span id="error-matricule" style="color: red; display: none;">Ce matricule existe déjà. Veuillez en saisir un autre.</span>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Date d'Entrée</label>
                                            <div class="col-sm-8">
                                                <input type="date" class="form-control" name="dateEntree" id="dateEntree" value="<%= detail.getDateEntre() %>">
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Email</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" name="email" id="email" value="<%= detail.getEmail() %>">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Téléphone</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" name="telephone" id="telephone" value="<%= detail.getTelephone() %>">
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
                                                <select name="qualite" id="qualite" class="form-control">
                                                    <% for(Qualite q : qualites) { 
                                                        String selected = q.getId().equals(detail.getQualite().getId()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= q.getId() %>" <%= selected %>><%= q.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Catégorie</label>
                                            <div class="col-sm-8">
                                                <select name="categorie" id="categorie" class="form-control">
                                                    <% for(Categorie c : categories) { 
                                                        String selected = c.getId().equals(detail.getCategorie().getId()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= c.getId() %>" <%= selected %>><%= c.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Corps d'appartenance</label>
                                            <div class="col-sm-8">
                                                <select name="appartenance" id="appartenance" class="form-control">
                                                    <% for(CorpsAppartenance ca : corpsAppartenances) { 
                                                        String selected = ca.getId().equals(detail.getCorps_appartenance().getId()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= ca.getId() %>" <%= selected %>><%= ca.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Indice</label>
                                            <div class="col-sm-8">
                                                <select name="indice" id="indice" class="form-control">
                                                    <% for(Indice i : indices) { 
                                                        String selected = i.getId().equals(detail.getIndice().getId()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= i.getId() %>" <%= selected %>><%= i.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Service employeur</label>
                                            <div class="col-sm-8">
                                                <select name="employeur" id="employeur" class="form-control">
                                                    <% for(ServiceEmployeur se : serviceEmployeurs) { 
                                                        String selected = se.getId().equals(detail.getService_employeur().getId()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= se.getId() %>" <%= selected %>><%= se.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Localité de service</label>
                                            <div class="col-sm-8">
                                                <select name="localite" id="localite" class="form-control">
                                                    <% for(LocaliteService ls : localiteServices) { 
                                                        String selected = ls.getId().equals(detail.getLocalite_service().getId()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= ls.getId() %>" <%= selected %>><%= ls.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Type utilisateur</label>
                                            <div class="col-sm-8">
                                                <select name="typeUtilisateur" id="typeUtilisateur" class="form-control">
                                                    <% for(TypeUtilisateur tu : typeUtilisateur) { 
                                                        String selected = tu.getEtat().equals(u.getType_utilisateur().getEtat()) ? "selected" : "";
                                                    %>
                                                        <option value="<%= tu.getEtat() %>" <%= selected %>><%= tu.getLibelle() %></option>
                                                    <% } %>
                                                </select>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Numéro de décision</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="decision" name="decision" value="<%= detail.getNumeroDecision() %>">
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Mot de passe</label>
                                            <div class="col-sm-8">
                                                <input type="text" class="form-control" id="motdepasse" name="mot_de_passe" placeholder="Saisissez le mot de passe">
                                                <button type="button" class="btn btn-secondary mt-2" id="generatePassword">Générer un mot de passe</button>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div class="col-md-6">
                                        <div class="form-group row">
                                            <label class="col-sm-4 col-form-label">Etat</label>
                                            <div class="col-sm-8 d-flex align-items-center">
                                                <span class="mr-2">Désactivé</span>
                                            
                                                <label class="switch">
                                                    <input type="checkbox" name="etatUtilisateur" id="etatUtilisateur" 
                                                        value="1" 
                                                        <%= u.getEtat_utilisateur().getEtat() == 1 ? "checked" : "" %> 
                                                        onchange="toggleEtat()">
                                                    <span class="slider round"></span>
                                                </label>
                                            
                                                <span class="ml-2">Activé</span>
                                            </div>
                                        </div>
                                        
                                        <!-- Champ caché pour s'assurer qu'une valeur est envoyée si la checkbox est décochée -->
                                        <input type="hidden" name="etatUtilisateur" value="5">
                                    </div>
                                </div>

                                <input type="hidden" name="id_utilisateur" value="<%= u.getId() %>">
                                <input type="hidden" name="id_detail_utilisateur" value="<%= detail.getId() %>">
                            <% } %>
                        </div>
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

<!-- generer mot de passe -->
<script>
    document.getElementById("generatePassword").addEventListener("click", function() {
        // Appelle l'API pour générer le mot de passe
        fetch("/generer-mot-de-passe")
            .then(response => response.text())
            .then(motDePasse => {
                // Insère le mot de passe généré dans l'input
                document.getElementById("motdepasse").value = motDePasse;
            })
            .catch(error => console.error('Erreur:', error));
    });
</script>

<!-- envoyer etat utilisateur -->
<script>
    function toggleEtat() {
        var etatUtilisateur = document.getElementById('etatUtilisateur');
        
        // Si la checkbox est cochée, sa valeur est "1", sinon, elle est "5"
        etatUtilisateur.value = etatUtilisateur.checked ? "1" : "5";
    }

    // Initialiser la bonne valeur au chargement de la page
    document.addEventListener("DOMContentLoaded", function() {
        var etatUtilisateur = document.getElementById('etatUtilisateur');
        etatUtilisateur.value = etatUtilisateur.checked ? "1" : "5"; 
    });
</script>

<!-- verifier age -->
<script>
    $(document).ready(function() {
        $('#dtn').on('change', function() {
            var dob = new Date($(this).val());
            var today = new Date();
            var age = today.getFullYear() - dob.getFullYear();
            var monthDifference = today.getMonth() - dob.getMonth();
            
            if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < dob.getDate())) {
                age--;
            }

            if (age < 18) {
                $('#error-date-naissance').show(); // Afficher le message d'erreur
                $('#dtn').val(''); // Réinitialiser la date
            } else {
                $('#error-date-naissance').hide(); // Cacher le message si l'âge est correct
            }
        });

        $('form').on('submit', function(e) {
            var dob = new Date($('#dtn').val());
            var today = new Date();
            var age = today.getFullYear() - dob.getFullYear();
            var monthDifference = today.getMonth() - dob.getMonth();
            
            if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < dob.getDate())) {
                age--;
            }

            if (age < 18) {
                e.preventDefault(); // Empêche l'envoi du formulaire
                $('#error-date-naissance').show(); // Afficher le message d'erreur
            }
        });
    });
</script>

<!-- verifier matricule -->
<script>
    $(document).ready(function() {
        $('#matricule').on('blur', function() {
            var matricule = $(this).val();

            // Appel AJAX pour vérifier si le matricule existe
            $.ajax({
                url: '/verifier-matricule',
                type: 'GET',
                data: { matricule: matricule },
                success: function(existe) {
                    if (existe) {
                        $('#error-matricule').show(); // Afficher le message d'erreur
                        $('#matricule').val(''); // Réinitialiser le champ matricule
                    } else {
                        $('#error-matricule').hide(); // Cacher le message si le matricule est valide
                    }
                },
                error: function(xhr, status, error) {
                    console.error('Erreur lors de la vérification du matricule:', error);
                }
            });
        });
    });
</script>

<%@include file="../utils/footer.jsp" %>