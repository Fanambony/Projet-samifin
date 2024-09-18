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
                    <h5 class="card-title"><i class="fas fa-info-circle"></i> Détails Utilisateur</h5>
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
                                <h6 class="font-weight-bold text-muted">Direction :</h6>
                                <p class="text-dark"><%= detail.getFonction().getDirection().getNom() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Fonction :</h6>
                                <p class="text-dark"><%= detail.getFonction().getNom() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Qualité :</h6>
                                <p class="text-dark"><%= detail.getQualite() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Categorie :</h6>
                                <p class="text-dark"><%= detail.getCategorie() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Corps d'appartenance :</h6>
                                <p class="text-dark"><%= detail.getCorpsAppartenance() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Indice :</h6>
                                <p class="text-dark"><%= detail.getIndice() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Service employeur :</h6>
                                <p class="text-dark"><%= detail.getServiceEmployeur() %></p>
                            </div>
                            <div class="col-sm-6 mb-3">
                                <h6 class="font-weight-bold text-muted">Localité de service :</h6>
                                <p class="text-dark"><%= detail.getLocaliteService() %></p>
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
            <div class="modal-header bg-primary text-white">
                <h5 class="modal-title" id="modalUploadImageLabel">Modifier l'image de profil</h5>
                <button type="button" class="close text-white" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            
            <div class="modal-body">
                <form action="/modifier-image" method="post" enctype="multipart/form-data">
                    <div class="form-group text-center">
                        <label for="file" class="btn btn-outline-primary">
                            <i class="fas fa-upload"></i> Choisir une nouvelle image
                        </label>
                        <input type="file" id="file" name="file" class="form-control-file d-none" required onchange="displayFileName(this)">
                        <p class="text-muted mt-2" id="file-name">Aucun fichier sélectionné</p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-outline-secondary" data-dismiss="modal">Annuler</button>
                        <button type="submit" class="btn btn-primary">Valider</button>
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
            <div class="modal-header bg-primary text-white">
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
                        <button type="submit" id="submitButton" class="btn btn-primary" disabled>Valider</button>
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

<!-- Modal pour afficher les messages -->
<div class="modal fade custom-modal" id="messageModal" tabindex="-1" role="dialog" aria-labelledby="messageModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header" id="modalHeader">
                <h5 class="modal-title" id="messageModalLabel">Message</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <div id="messageContent"><%= message %></div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function() {
        const message = '<%= message != null ? message : "" %>';
        const messageModal = $('#messageModal');
        const modalHeader = document.querySelector('#messageModal .modal-header');

        if (message.trim() !== '') {
            document.getElementById('messageContent').textContent = message;

            if (message.includes('succès')) {
                modalHeader.classList.remove('modal-error');
                modalHeader.classList.add('modal-success');
            } else {
                modalHeader.classList.remove('modal-success');
                modalHeader.classList.add('modal-error');
            }

            messageModal.modal('show');
        }
    });
</script>

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
</style>

<%@include file="../utils/footer.jsp" %>