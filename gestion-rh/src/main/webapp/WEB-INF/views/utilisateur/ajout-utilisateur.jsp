<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Genre" %>
<%@ page import="java.util.List" %>

<%
    List<Direction> direction = (List<Direction>)request.getAttribute("directions");
    List<Fonction> fonction = (List<Fonction>)request.getAttribute("fonctions");
    List<Genre> genre = (List<Genre>)request.getAttribute("genre");
%>

<%@include file="../utils/header.jsp" %>

<div class="col-12 grid-margin">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Ajout Utilisateur</h4>
            <form class="form-sample">
                <p class="card-description">
                    Informations personnel
                </p>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Nom</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="nom" name="nom" placeholder="Entrer nom" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Prenom(s)</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Entrer prenom" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Genre</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="genre" name="genre" required>
                                    <% for(Genre g : genre) { %>
                                        <option value="<%= g.getEtat() %>"><%= g.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date de naissance</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" id="naissance" name="naissance" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Numero décision</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="numero_decision" name="numero_decision" placeholder="Entrer numéro décision" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Matricule</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="matricule" name="matricule" placeholder="Entrer matricule" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Email</label>
                            <div class="col-sm-9">
                                <input type="email" class="form-control" id="email" name="email" placeholder="email@mail.com" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Téléphone</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="telephone" name="telephone" placeholder="+261 ** *** **" required>
                            </div>
                        </div>
                    </div>
                    
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date entre</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" id="entre" name="entre" required>
                            </div>
                        </div>
                    </div>
                </div>

                <p class="card-description">
                    Informations suivant
                </p>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Direction</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="direction" name="direction" required>
                                    <option value="" disabled selected>Veuillez choisir une direction</option>
                                    <% for(Direction d : direction) { %>
                                        <option value="<%= d.getId() %>"><%= d.getNom() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Fonction</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="fonction" name="fonction" required>
                                    <!-- Les fonctions seront insérées ici par JavaScript -->
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Qualite</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="qualite" name="qualite" placeholder="Contractuel" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Categorie</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="categorie" name="categorie" placeholder="A" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Corps d'appartenance</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="appartenance" name="appartenance" placeholder="Concepteur" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Indice</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="indice" name="indice" placeholder="950" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Service employeur</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="service" name="service" placeholder="SAMIFIN" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Localite de service</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="localite" name="localite" placeholder="Antananarivo" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="form-group d-flex">
                    <button type="submit" class="btn btn-primary mr-2">Submit</button>
                    <button class="btn btn-light">Cancel</button>
                </div>
            </form>
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

<%@include file="../utils/footer.jsp" %>