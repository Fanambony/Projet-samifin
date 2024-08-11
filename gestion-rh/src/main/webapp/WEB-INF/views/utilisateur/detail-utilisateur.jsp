<%@ page import="java.util.Optional" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>

<%
    Optional<Utilisateur> utilisateur = (Optional<Utilisateur>) request.getAttribute("utilisateur");
    Utilisateur user = utilisateur.get();
%>

<%@include file="../utils/header.jsp" %>

<div class="col-md-12 grid-margin stretch-card">
    <div class="card position-relative">
        <div class="card-body">
            <h4 class="card-title">Profil de l'utilisateur</h4>
            <div class="row">
                <div class="col-md-12 col-xl-12">
                    <div class="row">

                        <div class="col-md-4 d-flex align-items-center justify-content-center">
                            <div class="profile-img">
                                <img src="/assets/images/faces/face28.jpg" alt="photo de profil" class="img-fluid rounded-circle" style="width: 200px; height: 200px;">
                            </div>
                        </div>

                        <div class="col-md-8">
                            <div class="table-responsive mb-3 mb-md-0 mt-3">
                                <table class="table">
                                    <tr>
                                        <th>Nom</th>
                                        <td><%= user.getNom() %></td>
                                    </tr>
                                    <tr>
                                        <th>Prenom</th>
                                        <td><%= user.getPrenom() %></td>
                                    </tr>
                                    <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>
                                    <tr>
                                        <th>Matricule</th>
                                        <td><%= detail.getMatricule() %></td>
                                    </tr>
                                    <tr>
                                        <th>Date de Naissance</th>
                                        <td><%= user.getDateNaissance() %></td>
                                    </tr>
                                    
                                    <tr>
                                        <th>Email</th>
                                        <td><%= detail.getEmail() %></td>
                                    </tr>
                                    <tr>
                                        <th>Mot de passe</th>
                                        <td><%= detail.getMdp() %></td>
                                    </tr>
                                    <tr>
                                        <th>Telephone</th>
                                        <td><%= detail.getTelephone() %></td>
                                    </tr>
                                    <tr>
                                        <th>Date d'Entree</th>
                                        <td><%= detail.getDateEntre() %></td>
                                    </tr>
                                    <tr>
                                        <th>Direction</th>
                                        <td><%= detail.getFonction().getDirection().getNom() %></td>
                                    </tr>
                                    <tr>
                                        <th>Fonction</th>
                                        <td><%= detail.getFonction().getNom() %></td>
                                    </tr>
                                    <% } %>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
</div>

<%@include file="../utils/footer.jsp" %>

<style>
    .profile-img img {
        border: 2px solid #ccc;
        padding: 5px;
    }
    .table th, .table td {
        padding: 0.75rem 1.5rem;
        vertical-align: middle;
    }
    .table th {
        width: 30%;
    }
    .table td {
        width: 70%;
    }
</style>
