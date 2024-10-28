<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.util.Collections" %>

<%
    List<Object[]> soldeGenrer = (List<Object[]>)request.getAttribute("soldeGenrer");
    Integer maxYear = null;

    // Trouver l'année maximale dans la liste
    for (Object[] result : soldeGenrer) {
        Integer annee = (Integer) result[0];
        if (maxYear == null || annee > maxYear) {
            maxYear = annee;
        }
    }

    // Incrémenter l'année maximale pour la prochaine génération
    int nextYear = (maxYear != null) ? maxYear + 1 : java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

    // Vérifiez s'il y a un message d'erreur
    String errorMessage = (String) request.getAttribute("errorMessage");
%>

<%@ include file="../utils/header.jsp" %>

<style>
    body {
        background-color: #f8f9fa;
    }
    .container {
        margin-top: 50px;
    }
    .card {
        border: none;
        border-radius: 8px;
        box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);
    }
    .card-header {
        background-color: #007bff;
        color: white;
        font-weight: bold;
    }
    .btn-generate {
        margin-top: 20px;
    }
    table {
        margin-top: 20px;
    }
    .select-year {
        width: 150px; /* Largeur personnalisée */
        margin: 0 auto; /* Centrer l'élément */
    }
    .alert {
        position: fixed;
        top: 100px;
        left: 50%;
        transform: translateX(-50%);
        z-index: 1000;
        display: none; /* Masquer par défaut */
        opacity: 0; /* Commencer avec une opacité de 0 */
        transition: all 0.5s ease; /* Transition pour l'animation */
    }
    .alert.show {
        display: block; /* Afficher le message */
        opacity: 1; /* Rendre visible */
        transform: translateX(-50%) translateY(0); /* Remonter en place */
    }
    .alert.hide {
        opacity: 0; /* Rendre invisible */
        transform: translateX(-50%) translateY(-20px); /* Descendre légèrement */
    }
</style>

<!-- Afficher le message d'erreur si disponible -->
<% if (errorMessage != null) { %>
    <div class="alert alert-danger" id="errorAlert">
        <%= errorMessage %>
    </div>
<% } %>

<script>
    // Vérifiez si le message d'erreur est présent et le rendre visible
    window.onload = function() {
        var errorAlert = document.getElementById("errorAlert");
        if (errorAlert) {
            errorAlert.classList.add("show"); // Afficher le message
            setTimeout(function() {
                errorAlert.classList.add("hide"); // Commencer à cacher le message
                setTimeout(function() {
                    errorAlert.style.display = "none"; // Masquer complètement le message après l'animation
                }, 500); // Durée de la transition pour cacher
            }, 5000); // 5000 ms = 5 seconds
        }
    };
</script>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Etat de congé des personnels</h4>

            <div class="card">
                <div class="card-header">
                    Génération de Solde de Congé
                </div>
                <div class="card-body text-center">
                    <h2 class="mt-3">L'année à générer est <strong><%= nextYear %></strong></h2>
                    <form action="/v_soldes_utilisateur/generer-solde-conge" method="post">
                        <!-- Champ select pour l'année avec classes supplémentaires -->
                        <select class="form-control select-year" id="annee" name="annee" required>
                            <option value="" disabled>Choisissez l'année</option>
                            <% 
                                // Boucle pour afficher les années dans le select
                                for (int i = nextYear - 1; i <= nextYear + 5; i++) {
                                    // Vérification pour définir l'option sélectionnée par défaut
                                    String selected = (i == nextYear) ? "selected" : "";
                            %>
                                <option value="<%= i %>" <%= selected %>><%= i %></option>
                            <% } %>
                        </select>
                        <br>
                        <button type="submit" id="genererSolde" class="btn btn-primary btn-generate">Générer Solde de Congé</button>
                        <p class="mt-3">Cliquez sur le bouton ci-dessus pour générer le solde de congé des personnels pour l'année suivante.</p>
                    </form>
                </div>
            </div>

            <div class="card mt-4">
                <div class="card-header">
                    Historique des Soldes de Congé
                </div>
                <div class="card-body">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Année</th>
                                <th>Date de Génération</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for (Object[] result : soldeGenrer) {
                                    Integer annee = (Integer) result[0];
                                    Date dateGenerer = (Date) result[1];
                            %>
                            <tr>
                                <td><%= annee %></td>
                                <td><%= dateGenerer %></td>
                            </tr>
                            <%
                                }
                            %>
                        </tbody>
                    </table>
                </div>
            </div>

        </div>
    </div>
</div>

<%@ include file="../utils/footer.jsp" %>
