<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Genre" %>
<%@ page import="com.example.gestionrh.Model.Entity.EtatUtilisateur" %>
<%@ page import="java.util.Date" %>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Page<Utilisateur> utilisateur = (Page<Utilisateur>)request.getAttribute("utilisateurs");
    List<Direction> direction = (List<Direction>)request.getAttribute("directions");
    List<Fonction> fonction = (List<Fonction>)request.getAttribute("fonctions");
    List<Genre> genre = (List<Genre>)request.getAttribute("genre");
    List<EtatUtilisateur> etat = (List<EtatUtilisateur>)request.getAttribute("etat");
    int pa = (int)request.getAttribute("page");
%>

<%@include file="../utils/header.jsp" %>


<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Liste des utilisateurs</h4>
            <div class="table-responsive">

                <div>
                    <button type="button" class="btn btn-outline-primary btn-fw" data-toggle="modal" data-target="#ajouterUtilisateurModal">Ajouter une nouvelle utilisateur</button>
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
                                <option value="1">Active</option>
                                <option value="5">Desactive</option>
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
                            <th>Matricule</th>
                            <th>Nom</th>
                            <th>Prenom</th>
                            <th>Direction</th>
                            <th>Etat</th>
                            <th>Details</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(Utilisateur u : utilisateur) { %>
                            <tr>
                                <td>
                                    <% for (DetailUtilisateur du : u.getDetailUtilisateurs()) { %>
                                        <div><%= du.getMatricule() %></div>
                                    <% } %>
                                    </td>
                                <td><%= u.getNom() %></td>
                                <td><%= u.getPrenom() %></td>
                                <td>
                                <% for (DetailUtilisateur du : u.getDetailUtilisateurs()) { %>
                                    <div><%= du.getFonction().getDirection().getNom() %></div>
                                <% } %>
                                </td>
                                <td><label class="badge badge-info">
                                    <%= u.getEtat_utilisateur().getLibelle() %>
                                </label></td>
                                <td>
                                    <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#userDetailModal<%= u.getId() %>">
                                        <i class="ti-eye"></i>
                                    </button>
                                    <button type="button" class="btn btn-success btn-rounded btn-icon" data-toggle="modal" data-target="#userModifModal<%= u.getId() %>">
                                        <i class="ti-pencil"></i>
                                    </button>
                                </td>
                            </tr>
                        <% } %>
                    </tbody>
                </table>

                <%= pa %>

                <br>
                
                <div class="pagination">
                    <ul class="pagination">
                        <li class="page-item <%= utilisateur.isFirst() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= utilisateur.getNumber() - 1 %>&size=<%= utilisateur.getSize() %>"><< Precedent</a>
                        </li>
                        <li class="page-item <%= utilisateur.isLast() ? "disabled" : "" %>">
                            <a class="page-link" href="?page=<%= utilisateur.getNumber() + 1 %>&size=<%= utilisateur.getSize() %>">Suivant >></a>
                        </li>
                    </ul>
                </div>

            </div>
        </div>
    </div>
</div>

<!-- Modal ajout -->
<div class="modal fade custom-modal" id="ajouterUtilisateurModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-xl modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ajout utilisateur</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form action="#" method="post">
                    <div class="container-fluid">
                        
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="nom" class="col-sm-3 col-form-label">Nom</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrer nom" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="prenom" class="col-sm-3 col-form-label">Prenom</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Entrer prenom" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="naissance" class="col-sm-3 col-form-label">Genre</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="genre" name="genre" required>
                                            <% for(Genre g : genre) { %>
                                                <option value="<%= g.getEtat() %>"><%= g.getLibelle() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="naissance" class="col-sm-3 col-form-label">Date naissance</label>
                                    <div class="col-sm-8">
                                        <input type="date" class="form-control" id="naissance" name="naissance" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="email" class="col-sm-3 col-form-label">Matricule</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="matricule" name="matricule" placeholder="Entrer matricule" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="entre" class="col-sm-3 col-form-label">Date entree</label>
                                    <div class="col-sm-8">
                                        <input type="date" class="form-control" id="entre" name="entre" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="direction" class="col-sm-3 col-form-label">Direction</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="direction" name="direction" required>
                                            <option value="" disabled selected>Veuillez choisir une direction</option>
                                            <% for(Direction d : direction) { %>
                                                <option value="<%= d.getId() %>"><%= d.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="fonction" class="col-sm-3 col-form-label">Fonction</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="fonction" name="fonction" required>
                                            <!-- Les fonctions seront insérées ici par JavaScript -->
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <script src="/assets/js/jquery-3.7.1.min.js"></script>
                        <script>
                            $('#direction').on('change', function() {
                                var directionId = $(this).val();
                                var fonctionSelect = $('#fonction');
                        
                                // Envoyer une requête AJAX pour récupérer les fonctions
                                $.ajax({
                                    url: '/fonction/getFonctionsByDirection',
                                    type: 'GET',
                                    data: { directionId: directionId },
                                    success: function(fonctions) {
                                        // Vider la liste des fonctions
                                        fonctionSelect.empty();
                                        
                                        // Ajouter les nouvelles fonctions
                                        $.each(fonctions, function(index, fonction) {
                                            fonctionSelect.append(new Option(fonction.nom, fonction.id));
                                        });
                                    },
                                    error: function(xhr, status, error) {
                                        console.error('Erreur lors de la récupération des fonctions:', error);
                                    }
                                });
                            });
                        </script>
                        

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="email" class="col-sm-3 col-form-label">Email</label>
                                    <div class="col-sm-8">
                                        <input type="email" class="form-control" id="email" name="email" placeholder="email@mail.com" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="telephone" class="col-sm-3 col-form-label">Telephone</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="telephone" name="telephone" placeholder="+261 ** *** **" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="qualite" class="col-sm-3 col-form-label">Qualite</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="qualite" name="qualite" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="categorie" class="col-sm-3 col-form-label">Categorie</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="categorie" name="categorie" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="appartenance" class="col-sm-3 col-form-label">Corps d'appartenance</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="appartenance" name="appartenance" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="indice" class="col-sm-3 col-form-label">Indice</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="indice" name="indice" required>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="form-row">
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="service" class="col-sm-3 col-form-label">Service employeur</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="service" name="service" required>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="localite" class="col-sm-3 col-form-label">Localite de service</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control" id="localite" name="localite" required>
                                    </div>
                                </div>
                            </div>
                        </div>
                        
                        <!-- Ajoutez d'autres champs nécessaires ici en utilisant la même structure -->
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-primary" onclick="submitForm()">Ajouter</button>
            </div>
        </div>
    </div>
</div>





<!-- Modal détails utilisateur -->
<% for(Utilisateur u : utilisateur) { %>
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
                                    <li class="list-group-item">
                                        <strong>Matricule :</strong> <%= u.getDetailUtilisateurs().get(0).getMatricule() %>
                                    </li>
                                </ul>
                            </div>
                            <!-- Colonne du milieu pour la photo de profil -->
                            <div class="col-md-4 text-center">
                                <img src="/assets/images/faces/face1.jpg" class="img-fluid rounded-circle mb-3" alt="Photo de profil">
                                <h4><%= u.getPrenom() %> <%= u.getNom() %></h4>
                                <p><%= u.getType_utilisateur().getLibelle() %></p>
                            </div>
                            <!-- Colonne droite pour les informations -->
                            <div class="col-md-4">
                                <ul class="list-group">
                                    <li class="list-group-item" style="border-color: white;">
                                        <strong>Email :</strong> <%= u.getDetailUtilisateurs().get(0).getEmail() %>
                                    </li>
                                    <li class="list-group-item" style="border-color: white;">
                                        <strong>Téléphone :</strong> <%= u.getDetailUtilisateurs().get(0).getTelephone() %>
                                    </li>
                                    <li class="list-group-item" style="border-color: white;">
                                        <strong>Fonction :</strong> <%= u.getDetailUtilisateurs().get(0).getFonction().getNom() %>
                                    </li>
                                    <li class="list-group-item" style="border-color: white;">
                                        <strong>Direction :</strong> <%= u.getDetailUtilisateurs().get(0).getFonction().getDirection().getNom() %>
                                    </li>
                                    <li class="list-group-item" style="border-color: white;">
                                        <strong>Date d'entrée :</strong> <%= DateUtil.formatDate(u.getDetailUtilisateurs().get(0).getDateEntre()) %>
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
                    <h5 class="modal-title" id="userDetailModalLabel">Détails de l'utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="userDetailContent">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="nom">Nom</label>
                                    <input type="text" class="form-control" id="nom" value="<%= u.getNom() %>">
                                </div>
                                <% for(DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                                    <div class="form-group">
                                        <label>Matricule</label>
                                        <input type="text" class="form-control" id="matricule" value="<%= detail.getMatricule() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="text" class="form-control" id="email" value="<%= detail.getEmail() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Direction</label>
                                        <select id="direction" name="direction" class="form-control">
                                            <% for(Direction d : direction) { %>
                                                <option value="<%= d.getId() %>" <%= d.getId().equals(detail.getFonction().getDirection().getId()) ? "selected" : "" %>><%= d.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Qualité</label>
                                        <input type="text" class="form-control" id="qualite" value="<%= detail.getQualite() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Corps d'appartenance</label>
                                        <input type="text" class="form-control" id="appartenance" value="<%= detail.getCorpsAppartenance() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Service employeur</label>
                                        <input type="text" class="form-control" id="employeur" value="<%= detail.getServiceEmployeur() %>">
                                    </div>
                                <% } %>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Prénom</label>
                                    <input type="text" class="form-control" id="prenom" value="<%= u.getPrenom() %>">
                                </div>
                                <% for(DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                                    <div class="form-group">
                                        <label>Date d'Entrée</label>
                                        <input type="text" class="form-control" id="dateEntree" value="<%= detail.getDateEntre() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Téléphone</label>
                                        <input type="text" class="form-control" id="telephone" value="<%= detail.getTelephone() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Fonction</label>
                                        <select id="fonction" name="fonction" class="form-control" data-selected-function="<%= detail.getFonction().getId() %>">
                                            <!-- Les options seront mises à jour dynamiquement par JavaScript -->
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Catégorie</label>
                                        <input type="text" class="form-control" id="categorie" value="<%= detail.getCategorie() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Indice</label>
                                        <input type="text" class="form-control" id="indice" value="<%= detail.getIndice() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Localité de service</label>
                                        <input type="text" class="form-control" id="localite" value="<%= detail.getLocaliteService() %>">
                                    </div>
                                <% } %>
                                <div class="form-group">
                                    <label class="switch">
                                        Désactiver
                                        <input type="checkbox" id="toggleSwitch">
                                        <span class="slider"></span>
                                        Activer
                                    </label>
                                </div>
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
        margin-top: 0px;
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
</style>

<%@include file="../utils/footer.jsp" %>