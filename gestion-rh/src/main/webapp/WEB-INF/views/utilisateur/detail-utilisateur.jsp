<%@ page import="java.util.Optional" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.example.gestionrh.utils.DateUtil" %>

<%
    Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
    String message = (String) request.getAttribute("message");
%>

<%@include file="../utils/header.jsp" %>

<style>
    .custom-modal .modal-dialog {
        margin-top: -1%;
    }

    #messageModal .modal-dialog {
        position: fixed;
        top: 1rem; /* Distance du haut de la page */
        left: 50%; /* Positionnement horizontal central */
        transform: translateX(-50%); /* Ajuste pour centrer le modal */
        margin: 0;
        /* max-width: 300px; Ajustez selon vos besoins */
    }

    #messageModal .modal-content {
        border-radius: 0.5rem;
    }

    #messageModal .modal-header.modal-success {
        background-color: #28a745; /* Vert pour le succès */
        color: white;
    }

    #messageModal .modal-header.modal-error {
        background-color: #dc3545; /* Rouge pour l'erreur */
        color: white;
    }

    #messageBox {
        padding: 15px;
        margin-bottom: 15px;
        border-radius: 4px;
        position: fixed;
        top: 10%;
        right: 35%;
        z-index: 1000;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
        display: flex;
        justify-content: space-between;
        align-items: center;
        opacity: 0;
        transform: translateY(-50px); /* Position initiale en haut (hors de la vue) */
        transition: all 0.5s ease; /* Durée et effet de la transition */
    }

    #messageBox.show {
        opacity: 1;
        transform: translateY(0); /* L'alerte descend à sa position normale */
    }

    #messageBox.hide {
        opacity: 0;
        transform: translateY(-50px); /* L'alerte remonte */
    }

    #messageBox button {
        background-color: transparent;
        border: none;
        color: white;
        font-weight: bold;
        cursor: pointer;
        padding: 5px;
    }

    #messageBox.success {
        background-color: #4CAF50; /* Vert pour succès */
        color: white;
    }

    #messageBox.error {
        background-color: #f44336; /* Rouge pour erreur */
        color: white;
    }

</style>

<!-- Profil utilisateur en card -->
<div class="container">
    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <div class="profile-img mb-3">
                        <img src="<%= imgSrc %>" alt="Image de l'utilisateur" class="img-fluid rounded-circle shadow" style="width: 150px; height: 150px;" />
                    </div>
                    <h4 class="font-weight-bold mb-0"><%= user.getNom() %> <%= user.getPrenom() %></h4>
                    <br>
                    <span class="text-muted d-block mb-2"><%= user.getType_utilisateur().getLibelle() %></span>
                    <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>
                        <span class="text-muted d-block mb-2">Matricule : <%= detail.getMatricule() %></span>
                    <% } %>
                </div>
            </div>
        </div>

        <!-- Détails supplémentaires sur l'utilisateur -->
        <div class="col-md-8 mb-4">
            <div class="card shadow-sm">
                <div class="card-body">
                    <h5 class="card-title"><i class="fas fa-info-circle"></i> DETAILS DE L'UTILISATEUR</h5>
                    <div class="row">
                        <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Email :</h6>
                                <p class="text-dark"><%= detail.getEmail() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Téléphone :</h6>
                                <p class="text-dark"><%= detail.getTelephone() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Date de Naissance :</h6>
                                <p class="text-dark"><%= DateUtil.formatDate(user.getDateNaissance()) %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Date d'Entrée :</h6>
                                <p class="text-dark"><%= DateUtil.formatDate(detail.getDateEntre()) %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Numéro de décision :</h6>
                                <p class="text-dark"><%= detail.getNumeroDecision() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Genre :</h6>
                                <p class="text-dark"><%= user.getGenre().getLibelle() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Direction :</h6>
                                <p class="text-dark"><%= detail.getFonction().getDirection().getNom() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Fonction :</h6>
                                <p class="text-dark"><%= detail.getFonction().getNom() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Qualité :</h6>
                                <p class="text-dark"><%= detail.getQualite().getLibelle() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Categorie :</h6>
                                <p class="text-dark"><%= detail.getCategorie().getLibelle() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Corps d'appartenance :</h6>
                                <p class="text-dark"><%= detail.getCorps_appartenance().getLibelle() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Indice :</h6>
                                <p class="text-dark"><%= detail.getIndice().getLibelle() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Service employeur :</h6>
                                <p class="text-dark"><%= detail.getService_employeur().getLibelle() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Localité de service :</h6>
                                <p class="text-dark"><%= detail.getLocalite_service().getLibelle() %></p>
                            </div>
                        <% } %>
                    </div>
                </div>
            </div>
        </div>

        <!-- Section Actions rapides -->
        <div class="col-md-12">
            <div class="card shadow-sm">
                <div class="card-body text-center">
                    <button  type="button" data-toggle="modal" data-target="#modalUploadImage" class="btn btn-outline-success">
                        <i class="fas fa-edit"></i> Modifier l'image
                    </button>
                    <button type="button" class="btn btn-outline-danger" data-toggle="modal" data-target="#modalChangePassword">
                        <i class="fas fa-key"></i> Changer le mot de passe
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Modal pour l'upload de l'image avec un style personnalisé -->
<div class="modal fade custom-modal" id="modalUploadImage" tabindex="-1" role="dialog" aria-labelledby="modalUploadImageLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header bg-success text-white">
                <h5 class="modal-title" id="modalUploadImageLabel">Modifier l'image de profil</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <div class="modal-body">
                <form action="/modifier-image" method="post" enctype="multipart/form-data">
                    <div class="form-group text-center">
                        <label for="file" class="btn btn-outline-success">
                            <i class="fas fa-upload"></i> Choisir une nouvelle image
                        </label>
                        <input type="file" id="file" name="file" class="form-control-file d-none" required onchange="displayFileName(this)">
                        <p class="text-muted mt-2" id="file-name">Aucun fichier sélectionné</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-success">Valider</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    // Fonction pour afficher le nom du fichier sélectionné
    function displayFileName(input) {
        const fileName = input.files[0] ? input.files[0].name : "Aucun fichier sélectionné";
        document.getElementById('file-name').textContent = fileName;
    }
</script>

<!-- Modal pour changer le mot de passe -->
<div class="modal fade custom-modal" id="modalChangePassword" tabindex="-1" role="dialog" aria-labelledby="modalChangePasswordLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header bg-danger text-white">
                <h5 class="modal-title" id="modalChangePasswordLabel">Changer le mot de passe</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="changePasswordForm" action="/modifier-mot-de-passe" method="post">
                    <div class="form-group">
                        <label for="currentPassword">Mot de passe actuel :</label>
                        <input type="password" id="currentPassword" name="currentPassword" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="newPassword">Nouveau mot de passe :</label>
                        <input type="password" id="newPassword" name="newPassword" class="form-control" required>
                    </div>
                    <div class="form-group">
                        <label for="confirmPassword">Confirmer le nouveau mot de passe :</label>
                        <input type="password" id="confirmPassword" name="confirmPassword" class="form-control" required>
                    </div>
                    <!-- Messages d'erreur -->
                    <div class="alert alert-danger d-none" id="passwordError" role="alert">
                        Les mots de passe ne correspondent pas.
                    </div>
                    <div class="alert alert-danger d-none" id="currentPasswordError" role="alert">
                        Le mot de passe actuel ne peut pas être le même que le nouveau mot de passe.
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" id="submitButton" class="btn btn-danger" disabled>Valider</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const currentPasswordInput = document.getElementById('currentPassword');
        const newPasswordInput = document.getElementById('newPassword');
        const confirmPasswordInput = document.getElementById('confirmPassword');
        const submitButton = document.getElementById('submitButton');
        const passwordError = document.getElementById('passwordError');
        const currentPasswordError = document.getElementById('currentPasswordError');

        function validatePasswords() {
            const currentPassword = currentPasswordInput.value;
            const newPassword = newPasswordInput.value;
            const confirmPassword = confirmPasswordInput.value;

            let valid = true;

            // Réinitialisation des messages d'erreur
            passwordError.classList.add('d-none');
            currentPasswordError.classList.add('d-none');

            // Vérifier si les nouveaux mots de passe correspondent
            if (newPassword !== confirmPassword) {
                valid = false;
                passwordError.classList.remove('d-none');
            }

            // Vérifier si le mot de passe actuel est différent du nouveau mot de passe
            if (currentPassword === newPassword) {
                valid = false;
                currentPasswordError.classList.remove('d-none');
            }

            // Activer ou désactiver le bouton de soumission en fonction de la validité
            submitButton.disabled = !valid;
        }

        // Ajouter des écouteurs d'événements pour les champs de mot de passe
        currentPasswordInput.addEventListener('input', validatePasswords);
        newPasswordInput.addEventListener('input', validatePasswords);
        confirmPasswordInput.addEventListener('input', validatePasswords);
    });
</script>

<!-- Message d'erreur ou de succès -->
<% if (message != null && !message.isEmpty()) { %>
    <div id="messageBox" class="alert">
        <span id="messageContent"><%= message %></span>
        <button onclick="closeMessage()">OK</button>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', function() {
            const message = '<%= message != null ? message : "" %>';
            const messageBox = document.getElementById('messageBox');

            if (message.trim() !== '') {
                document.getElementById('messageContent').textContent = message;

                // Détermine le type de message : succès ou erreur
                if (message.includes('succès')) {
                    messageBox.classList.add('success');
                    messageBox.classList.remove('error');
                } else {
                    messageBox.classList.add('error');
                    messageBox.classList.remove('success');
                }

                // Affiche l'alerte avec un effet de descente
                messageBox.classList.add('show');

                // Disparaît automatiquement après 5 secondes
                setTimeout(function() {
                    closeMessage();
                }, 5000);
            }

            function closeMessage() {
                messageBox.classList.remove('show');
                messageBox.classList.add('hide');
                // Retire complètement l'alerte après la transition (0.5s)
                setTimeout(function() {
                    messageBox.style.display = 'none';
                }, 500);
            }
        });
    </script>
<% } %>




<%@include file="../utils/footer.jsp" %>