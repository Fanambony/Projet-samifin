<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Genre" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Qualite" %>
<%@ page import="com.example.gestionrh.Model.Entity.ServiceEmployeur" %>
<%@ page import="com.example.gestionrh.Model.Entity.LocaliteService" %>
<%@ page import="com.example.gestionrh.Model.Entity.Indice" %>
<%@ page import="com.example.gestionrh.Model.Entity.CorpsAppartenance" %>
<%@ page import="com.example.gestionrh.Model.Entity.Categorie" %>
<%@ page import="java.util.List" %>

<%
    List<Direction> direction = (List<Direction>)request.getAttribute("directions");
    List<Fonction> fonction = (List<Fonction>)request.getAttribute("fonctions");
    List<Genre> genre = (List<Genre>)request.getAttribute("genre");
    List<TypeUtilisateur> typeUser = (List<TypeUtilisateur>)request.getAttribute("typeUtilisateurs");
    List<Qualite> qualites = (List<Qualite>)request.getAttribute("qualites");
    List<ServiceEmployeur> serviceEmployeurs = (List<ServiceEmployeur>)request.getAttribute("serviceEmployeurs");
    List<LocaliteService> localiteServices = (List<LocaliteService>)request.getAttribute("localiteServices");
    List<Indice> indices = (List<Indice>)request.getAttribute("indices");
    List<CorpsAppartenance> corpsAppartenances = (List<CorpsAppartenance>)request.getAttribute("corpsAppartenances");
    List<Categorie> categories = (List<Categorie>)request.getAttribute("categories");
%>

<%@include file="../utils/header.jsp" %>

<div class="col-12 grid-margin">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">AJOUTER UTILISATEURS</h4>
            <form class="form-sample" method="post" action="ajoutUtilisateur">
                <p class="card-description">
                    Informations Personnelles
                </p>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Nom</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="nom" name="nom" placeholder="Saisissez le nom" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Prénom(s)</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="prenom" name="prenom" placeholder="Saisissez le(s) prénom(s)" required>
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
                                <input type="date" class="form-control" id="date_naissance" name="date_naissance" required>
                                <span id="error-date-naissance" style="color: red; display: none;">L'utilisateur doit avoir au moins 18 ans.</span>
                            </div>
                        </div>
                    </div>
                    
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Numéro de décision</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="numero_decision" name="numero_decision" placeholder="Saisissez le numéro de décision" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Matricule</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="matricule" name="matricule" placeholder="Saisissez le matricule" required>
                                <span id="error-matricule" style="color: red; display: none;">Ce matricule existe déjà. Veuillez en saisir un autre.</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Email</label>
                            <div class="col-sm-9">
                                <input type="email" class="form-control" id="email" name="email" placeholder="exemple@mail.com" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Téléphone</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="telephone" name="telephone" placeholder="Saisissez le numéro de téléphone" required>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date d'entrée</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" id="date_entre" name="date_entre" required>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Type d'utilisateur</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="type_utilisateur" name="type_utilisateur" required>
                                    <% for(TypeUtilisateur tu : typeUser) { %>
                                        <option value="<%= tu.getEtat() %>"><%= tu.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <p class="card-description">
                    Informations Professionnelles
                </p>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Direction</label>
                            <div class="col-sm-9">
                                <select class="form-control" id="direction" name="direction" required>
                                    <option value="" disabled selected>Veuillez sélectionner une direction</option>
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
                                <select class="form-control" id="id_fonction" name="id_fonction" required>
                                    <!-- Les fonctions seront insérées ici par JavaScript -->
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Qualité</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="qualite" id="qualite" required>
                                    <option value="" disabled selected>Veuillez sélectionner une qualité</option>
                                    <% for(Qualite qualite : qualites) { %>
                                        <option value="<%= qualite.getId() %>"><%= qualite.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Catégorie</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="categorie" id="categorie" required>
                                    <option value="" disabled selected>Veuillez sélectionner une catégorie</option>
                                    <% for(Categorie categorie : categories) { %>
                                        <option value="<%= categorie.getId() %>"><%= categorie.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Corps d'appartenance</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="corps_appartenance" id="corps_appartenance" required>
                                    <option value="" disabled selected>Veuillez sélectionner une corps d'appartenance</option>
                                    <% for(CorpsAppartenance appartenance : corpsAppartenances) { %>
                                        <option value="<%= appartenance.getId() %>"><%= appartenance.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Indice</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="indice" id="indice" required>
                                    <option value="" disabled selected>Veuillez sélectionner une indice</option>
                                    <% for(Indice indice : indices) { %>
                                        <option value="<%= indice.getId() %>"><%= indice.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Service Employeur</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="service_employeur" id="service_employeur" required>
                                    <option value="" disabled selected>Veuillez sélectionner une service employeur</option>
                                    <% for(ServiceEmployeur service : serviceEmployeurs) { %>
                                        <option value="<%= service.getId() %>"><%= service.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Localité de Service</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="localite_service" id="localite_service" required>
                                    <option value="" disabled selected>Veuillez sélectionner une localite de service</option>
                                    <% for(LocaliteService localite : localiteServices) { %>
                                        <option value="<%= localite.getId() %>"><%= localite.getLibelle() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Mot de passe</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="motdepasse" name="mot_de_passe" placeholder="Saisissez le mot de passe" required>
                                <button type="button" class="btn btn-secondary mt-2" id="generatePassword">Générer un mot de passe</button>
                            </div>
                        </div>
                    </div>
                </div>
                

                <div class="form-group d-flex">
                    <button type="submit" class="btn btn-primary mr-2">Soumettre</button>
                    <button class="btn btn-light">Annuler</button>
                </div>
            </form>
        </div>
    </div>
</div>

<script src="/assets/js/jquery-3.7.1.min.js"></script>
<script>
    $('#direction').on('change', function() {
        var directionId = $(this).val();
        var fonctionSelect = $('#id_fonction');

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

<!-- JS valider age -->
<script>
    $(document).ready(function() {
        $('#date_naissance').on('change', function() {
            var dob = new Date($(this).val());
            var today = new Date();
            var age = today.getFullYear() - dob.getFullYear();
            var monthDifference = today.getMonth() - dob.getMonth();
            
            if (monthDifference < 0 || (monthDifference === 0 && today.getDate() < dob.getDate())) {
                age--;
            }

            if (age < 18) {
                $('#error-date-naissance').show(); // Afficher le message d'erreur
                $('#date_naissance').val(''); // Réinitialiser la date
            } else {
                $('#error-date-naissance').hide(); // Cacher le message si l'âge est correct
            }
        });

        $('form').on('submit', function(e) {
            var dob = new Date($('#date_naissance').val());
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