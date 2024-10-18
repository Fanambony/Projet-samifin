<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Modifier le mot de passe</title>
    <link rel="stylesheet" href="/assets/vendors/feather/feather.css">
    <link rel="stylesheet" href="/assets/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css">
    <link rel="shortcut icon" href="/assets/images/favicon.png" />
    <style>
        .error-message {
            color: #ff0000;
            font-size: 14px;
            margin-top: 5px;
            display: none;
        }
    </style>
    <script>
        // Fonction pour vérifier les mots de passe en temps réel
        function verifierCorrespondance() {
            const nouveauMdp = document.getElementById("nouveauMdp").value;
            const confirmerMdp = document.getElementById("confirmerMdp").value;
            const errorMessage = document.getElementById("error-message");

            // Vérifie si les mots de passe correspondent
            if (nouveauMdp !== confirmerMdp) {
                errorMessage.style.display = "block"; // Affiche le message d'erreur
                errorMessage.textContent = "Les mots de passe ne correspondent pas.";
            } else {
                errorMessage.style.display = "none"; // Cache le message si les mots de passe correspondent
            }
        }
    </script>
</head>

<body>
    <div class="container-scroller">
        <div class="container-fluid page-body-wrapper full-page-wrapper">
            <div class="content-wrapper d-flex align-items-center auth px-0">
                <div class="row w-100 mx-0">
                    <div class="col-lg-4 mx-auto">
                        <div class="auth-form-light text-left py-5 px-4 px-sm-5">
                            <div class="brand-logo">
                                <img src="/assets/images/logo.png" alt="logo">
                            </div>
                            <h4>Modifier votre mot de passe</h4>
                            <h6 class="font-weight-light">Entrez un nouveau mot de passe pour continuer.</h6>
                            <form class="pt-3" action="/detail_utilisateur/modifier-mdp-provisoir" method="post">
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-lg" id="nouveauMdp" name="nouveauMdp" placeholder="Nouveau mot de passe" required oninput="verifierCorrespondance()">
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-lg" id="confirmerMdp" name="confirmerMdp" placeholder="Confirmer le mot de passe" required oninput="verifierCorrespondance()">
                                    <!-- Message d'erreur affiché ici -->
                                    <p id="error-message" class="error-message">Les mots de passe ne correspondent pas.</p>
                                </div>
                                <div class="mt-3">
                                    <button type="submit" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">Modifier le mot de passe</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="/assets/vendors/js/vendor.bundle.base.js"></script>
    <script src="/assets/js/off-canvas.js"></script>
    <script src="/assets/js/hoverable-collapse.js"></script>
    <script src="/assets/js/template.js"></script>
    <script src="/assets/js/settings.js"></script>
    <script src="/assets/js/todolist.js"></script>
</body>
</html>
