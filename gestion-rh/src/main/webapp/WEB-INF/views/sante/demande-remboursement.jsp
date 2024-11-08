<%@ page import="java.util.Optional" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
    List<Direction> directions = (List<Direction>)request.getAttribute("directions");
%>

<%@include file="../utils/header.jsp" %>

<style>
    .label {
        padding-top: 4%;
    }
    .checkbox {
        margin-left: 10%;
    }
    td {
        width: 33%;
    }
</style>

<div class="col-12 grid-margin">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">DEMANDE DE REMBOURSEMENT DE FRAIS MEDICAUX</h4>

        <form class="form-sample" id="santeForm" method="post" action="generer_pdf/demande-remboursement">

        <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>
            <!-- More Form Fields -->
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Mme/Mr</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="nom" value="<%= user.getNom() %> <%= user.getPrenom() %>"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Matricule</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="matricule" value="<%= detail.getMatricule() %>">
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Direction / Service</label>
                        <div class="col-sm-9">
                            <select id="direction" name="direction" class="form-control">
                                <% for(Direction d : directions) { %>
                                    <option value="<%= d.getId() %>" <%= d.getId().equals(detail.getFonction().getDirection().getId()) ? "selected" : "" %>><%= d.getNom() %></option>
                                <% } %>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Fonction ou grade</label>
                        <div class="col-sm-9">
                            <select id="fonction" name="fonction" class="form-control" data-selected-function="<%= detail.getFonction().getId() %>">
                                <!-- Les options seront mises à jour dynamiquement par JavaScript -->
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Nom du malade</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="nom_malade" placeholder="Nom du malade :"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Lien</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="lien"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Montant</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="montant" placeholder="En Ariary" id="montantInput"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Montant en lettres</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="montant_let" id="montantLettres" readonly/>
                        </div>
                    </div>
                </div>
            </div>
        <% } %>

        <p class="card-description">Pièces fournies</p>

        <div class="col-lg-12 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                    <div class="table-responsive pt-3">
                        <table class="table table-bordered">
                            <tbody>
                                <tr>
                                    <td>
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="checkbox" class="form-check-input" name="ordonnance">
                                                Ordonnance
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="checkbox" class="form-check-input" name="factures">
                                                Factures
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="checkbox" class="form-check-input" name="bulletin">
                                                Bulletin de consultation
                                            </label>
                                        </div>
                                    </td>
                                    <td>
                                        <div class="form-check">
                                            <label class="form-check-label">
                                                <input type="checkbox" class="form-check-input" name="notice">
                                                Notice
                                            </label>
                                        </div>
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Other Form Fields -->
        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Date de consultation</label>
            <div class="col-sm-5">
                <input type="date" class="form-control" name="date_consultation" />
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Avis</label>
            <div class="col-sm-5">
                <textarea class="form-control" id="exampleTextarea1" rows="4" name="avis"></textarea>
            </div>
        </div>

        <div class="form-group row">
            <label class="col-sm-3 col-form-label">Antananarivo, le</label>
            <div class="col-sm-5">
                <input type="date" class="form-control" name="date_demande" />
            </div>
        </div>

        <button type="submit" class="btn btn-outline-primary btn-icon-text">
            Exporter PDF
            <i class="ti-printer btn-icon-append"></i>
        </button>

        </form>
      </div>
    </div>
</div>

<script src="/assets/js/jquery-3.7.1.min.js"></script>
<script>
    $(document).ready(function() {
            $(document).ready(function () {

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

<script>
    function convertirNombreEnLettres(nombre) {
        const unite = ['', 'un', 'deux', 'trois', 'quatre', 'cinq', 'six', 'sept', 'huit', 'neuf'];
        const dizaine = ['', 'dix', 'vingt', 'trente', 'quarante', 'cinquante', 'soixante', 'soixante-dix', 'quatre-vingt', 'quatre-vingt-dix'];
        const centaine = ['', 'cent', 'deux cents', 'trois cents', 'quatre cents', 'cinq cents', 'six cents', 'sept cents', 'huit cents', 'neuf cents'];
        const milliers = ['mille', ''];
        const millions = ['million', 'millions'];

        if (nombre === 0) return 'ZÉRO';

        let partieEntiere = Math.floor(nombre);
        let partieFractionnaire = Math.round((nombre - partieEntiere) * 100);
        let lettres = '';

        const lireCentaines = (nombre) => {
            if (nombre < 100) return lireDizaines(nombre);
            let reste = nombre % 100;
            let quotient = Math.floor(nombre / 100);
            let centaineStr = quotient > 1 ? unite[quotient] + ' ' + centaine[1] : centaine[1];
            return reste === 0 ? centaineStr : centaineStr + ' ' + lireDizaines(reste);
        };

        const lireDizaines = (nombre) => {
            if (nombre < 10) return unite[nombre];
            if (nombre < 20) return dizaine[nombre - 10];
            let reste = nombre % 10;
            let quotient = Math.floor(nombre / 10);
            return reste === 0 ? dizaine[quotient] : dizaine[quotient] + '-' + unite[reste];
        };

        if (partieEntiere >= 1000000) {
            let quotient = Math.floor(partieEntiere / 1000000);
            lettres += lireCentaines(quotient) + ' ' + (quotient > 1 ? millions[1] : millions[0]);
            partieEntiere %= 1000000;
        }

        if (partieEntiere >= 1000) {
            let quotient = Math.floor(partieEntiere / 1000);
            lettres += quotient > 1 ? ' ' + lireCentaines(quotient) + ' ' : ' ';
            lettres += milliers[0];
            partieEntiere %= 1000;
        }

        if (partieEntiere > 0) {
            lettres += ' ' + lireCentaines(partieEntiere);
        }

        lettres = lettres.trim();

        if (partieFractionnaire > 0) {
            lettres += ' et ' + partieFractionnaire + '/100';
        }

        return lettres.toUpperCase(); // Retire ' Ariary' pour l'affichage en lettres
    }

    $(document).ready(function() {
    $('#montantInput').on('input', function() {
        const montant = parseFloat($(this).val().replace(/[^0-9.]/g, ''));
        if (!isNaN(montant)) {
            const montantLettres = convertirNombreEnLettres(montant);
            $('#montantLettres').val(montantLettres); // Mettez à jour la valeur du champ
        } else {
            $('#montantLettres').val(''); // Effacez le champ si le montant n'est pas un nombre valide
        }
    });
});

</script>


<%@include file="../utils/footer.jsp" %>
