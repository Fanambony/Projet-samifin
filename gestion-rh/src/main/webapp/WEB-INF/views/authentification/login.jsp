<%
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Login SAMIFIN</title>
    <link rel="stylesheet" href="/assets/vendors/feather/feather.css">
    <link rel="stylesheet" href="/assets/vendors/ti-icons/css/themify-icons.css">
    <link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.base.css">
    <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css">
    <link rel="shortcut icon" href="/assets/images/favicon.png" />
    <style>
        .error-message {
            color: #ff0000;
            font-size: 14px;
            margin-top: 10px;
            font-weight: bold;
            border: 1px solid #ff0000;
            padding: 10px;
            background-color: #ffe6e6;
            text-align: center;
        }
    </style>
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
                            <h4>Bonjour! C'est parti</h4>
                            <h6 class="font-weight-light">Connectez-vous pour continuer.</h6>
                            <form class="pt-3" action="/detail_utilisateur/verifierLogin" method="post">
                                <div class="form-group">
                                    <input type="text" class="form-control form-control-lg" id="exampleInputEmail1" placeholder="Identifiant" name="mail" required>
                                </div>
                                <div class="form-group">
                                    <input type="password" class="form-control form-control-lg" id="exampleInputPassword1" placeholder="Mot de passe" name="mdp" required>                                
                                </div>
                                <% if (errorMessage != null) { %>
                                    <p class="error-message"><%= errorMessage %></p>
                                <% } %>
                                <div class="mt-3">
                                    <input type="submit" value="Se connecter" class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">
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
