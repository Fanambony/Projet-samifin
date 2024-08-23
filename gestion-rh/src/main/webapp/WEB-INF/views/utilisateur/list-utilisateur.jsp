<%@ page import="java.util.List" %>
<%@ page import="java.util.Optional" %>
<%@ page import="org.springframework.data.domain.Page" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Genre" %>

<%
    Page<Utilisateur> utilisateur = (Page<Utilisateur>)request.getAttribute("utilisateurs");
    List<Direction> direction = (List<Direction>)request.getAttribute("directions");
    List<Fonction> fonction = (List<Fonction>)request.getAttribute("fonctions");
    List<Genre> genre = (List<Genre>)request.getAttribute("genre");
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
                <h5 class="modal-title" id="exampleModalLabel">Ajouter un utilisateur</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="ajouterUtilisateurForm">
                    <div class="container-fluid">
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
                                        <select class="form-control" id="direction" name="direction">
                                            <% for(Direction d : direction) { %>
                                                <option value="<%= d.getId() %>"><%= d.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group col-md-6">
                                <div class="row">
                                    <label for="direction" class="col-sm-3 col-form-label">Fonction</label>
                                    <div class="col-sm-8">
                                        <select class="form-control" id="direction" name="direction">
                                            <% for(Fonction f : fonction) { %>
                                                <option value="<%= f.getId() %>"><%= f.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>


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
                                        <select class="form-control" id="genre" name="genre">
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
        <div class="modal-dialog modal-xl modal-dialog-centered" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="userDetailModalLabel">Details de l'utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="userDetailContent">
                        <!-- Contenu du modal -->
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Nom</label>
                                    <input type="text" class="form-control" id="nom" value="<%= u.getNom() %>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Genre</label>
                                    <input type="text" class="form-control" id="genre" value="<%= u.getGenre().getLibelle() %>" disabled>
                                </div>
                                <% for(DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                                    <div class="form-group">
                                        <label>Matricule</label>
                                        <input type="text" class="form-control" id="matricule" value="<%= detail.getMatricule() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Email</label>
                                        <input type="text" class="form-control" id="email" value="<%= detail.getEmail() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Direction</label>
                                        <input type="text" class="form-control" id="direction" value="<%= detail.getFonction().getDirection().getNom() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Qualite</label>
                                        <input type="text" class="form-control" id="qualite" value="<%= detail.getQualite() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Corps d'appartenance</label>
                                        <input type="text" class="form-control" id="appartenance" value="<%= detail.getCorpsAppartenance() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Service employeur</label>
                                        <input type="text" class="form-control" id="employeur" value="<%= detail.getServiceEmployeur() %>" disabled>
                                    </div>
                                <% } %>
                                    <div class="form-group">
                                        <label>Type d'utilisateur</label>
                                        <input type="text" class="form-control" id="typeUtilisateur" value="<%= u.getType_utilisateur().getLibelle() %>" disabled>
                                    </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label>Prenom</label>
                                    <input type="text" class="form-control" id="prenom" value="<%= u.getPrenom() %>" disabled>
                                </div>
                                <div class="form-group">
                                    <label>Date de naissance</label>
                                    <input type="text" class="form-control" id="dtn" value="<%= u.getDateNaissance() %>" disabled>
                                </div>
                                <% for(DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                                
                                    <div class="form-group">
                                        <label>Date d'entree</label>
                                        <input type="text" class="form-control" id="dateEntree" value="<%= detail.getDateEntre() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Telephone</label>
                                        <input type="text" class="form-control" id="telephone" value="<%= detail.getTelephone() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Fonction</label>
                                        <input type="text" class="form-control" id="fonction" value="<%= detail.getFonction().getNom() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Categorie</label>
                                        <input type="text" class="form-control" id="categorie" value="<%= detail.getCategorie() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Indice</label>
                                        <input type="text" class="form-control" id="indice" value="<%= detail.getIndice() %>" disabled>
                                    </div>
                                    <div class="form-group">
                                        <label>Localite de service</label>
                                        <input type="text" class="form-control" id="localite" value="<%= detail.getLocaliteService() %>" disabled>
                                    </div>
                                <% } %>
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
                    <h5 class="modal-title" id="userDetailModalLabel">Details de l'utilisateur</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <div id="userDetailContent">
                        <!-- Contenu du modal -->
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
                                        <input type="text" class="form-control" id="direction" value="<%= detail.getFonction().getDirection().getNom() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Qualite</label>
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
                                    <label>Prenom</label>
                                    <input type="text" class="form-control" id="prenom" value="<%= u.getPrenom() %>">
                                </div>
                                <% for(DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                                
                                    <div class="form-group">
                                        <label>Date d'Entree</label>
                                        <input type="text" class="form-control" id="dateEntree" value="<%= detail.getDateEntre() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Telephone</label>
                                        <input type="text" class="form-control" id="telephone" value="<%= detail.getTelephone() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Fonction</label>
                                        <input type="text" class="form-control" id="fonction" value="<%= detail.getFonction().getNom() %>">
                                        
                                        <select name="">
                                            <% for(Fonction f : fonction) { %>
                                                <option value="" selected><%= detail.getFonction().getNom() %></option>
                                                <option value=""><%= f.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                    <div class="form-group">
                                        <label>Categorie</label>
                                        <input type="text" class="form-control" id="categorie" value="<%= detail.getCategorie() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Indice</label>
                                        <input type="text" class="form-control" id="indice" value="<%= detail.getIndice() %>">
                                    </div>
                                    <div class="form-group">
                                        <label>Localite de service</label>
                                        <input type="text" class="form-control" id="localite" value="<%= detail.getLocaliteService() %>">
                                    </div>
                                <% } %>

                                <div class="form-group">
                                    <label class="switch">
                                        Desactiver
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