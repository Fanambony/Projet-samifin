<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Famille" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    List<Utilisateur> listUtilisateur = (List<Utilisateur>)request.getAttribute("listUtilisateur");
%>

<%@include file="../utils/header.jsp" %>

<style>
    .custom-modal .modal-dialog {
        margin-top: 2rem;
    }
</style>

<div class="col-12 grid-margin">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">ATTESTATION DE NON PAIEMENT</h4>

            <form class="form-sample" id="santeForm" method="post" action="generer_pdf/attestation-non-paiement">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Ordonnateur</label>
                            <div class="col-sm-9">
                                <select name="ordonnateur" class="form-control  js-example-basic-single">
                                    <% for(Utilisateur u : listUtilisateur) { %>
                                        <option value="<%= u.getNom() %> <%= u.getPrenom() %>"><%= u.getNom() %> <%= u.getPrenom() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Agent</label>
                            <div class="col-sm-9">
                                <select name="agent" class="form-control js-example-basic-single" onchange="updateAgentDetails()">
                                    <option disabled selected>Choisir un agent</option>
                                    <% for (Utilisateur u : listUtilisateur) { 
                                        for (DetailUtilisateur detail : u.getDetailUtilisateurs()) { %>
                                            <option value="<%= u.getId() %>" 
                                                data-matricule="<%= detail.getMatricule() %>"
                                                data-decision="<%= detail.getNumeroDecision() %>"
                                                data-date-decition="<%= detail.getDateEntre() %>">
                                                <%= u.getNom() %> <%= u.getPrenom() %>
                                            </option>
                                        <% } 
                                    } %>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Matricule</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="matricule" id="matriculeField" readonly/>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Décision</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="decision" id="decisionField" readonly/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date de décision</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" name="dateDecision" id="entreField" readonly/>
                            </div>
                        </div>
                    </div>
                </div>

                <p class="card-description">Renseignements concernant le membre de la famille</p>

                <div class="form-group row mb-2">
                    <label class="col-sm-7 col-form-label">Le malade est-il le fonctionnaire lui-même ?</label>
                    <div class="col-sm-5">
                    <input type="radio" id="fonctionnaireOui" name="fonctionnaire_malade" value="OUI" onchange="handleRadioSelection()"> Oui -
                    <input type="radio" id="fonctionnaireNon" name="fonctionnaire_malade" value="NON" onchange="handleRadioSelection()"> Non
                    </div>
                </div>
                
                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Nom et Prénom de la famille</label>
                    <div class="col-sm-5">
                        <select class="form-control" id="familleSelect" name="nom_famille" onchange="updateFiliationAndNomPrenom()" disabled>
                            <option disabled selected>Choisir une famille</option>
                            <!-- Les options seront ajoutées dynamiquement ici via JavaScript -->
                        </select>
                        
                    </div>
                </div>
                
                <!-- Champ caché pour le nom et prénom du membre de la famille -->
                <input type="hidden" id="nomPrenomMembreFamille" name="nom_prenom_membre_famille" />

                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Filiation</label>
                    <div class="col-sm-5">
                    <input type="text" class="form-control" id="filiationField" name="filiation_membre_famille" readonly/>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-sm-3 col-form-label">Né(e) le</label>
                    <div class="col-sm-5">
                    <input type="date" class="form-control" id="dateNaissanceField" name="date_naissance_membre_famille" readonly/>
                    </div>
                </div>

                <p class="card-description">Détail des achats des médicaments</p>

                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addItemModal">Ajouter un libelle</button>

                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive pt-3">
                                <table class="table table-bordered" id="itemTable">
                                    <thead>
                                        <tr>
                                            <th>Libelle</th>
                                            <th>Montant</th>
                                            <th>Action</th> <!-- Nouvelle colonne pour le bouton supprimer -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Les lignes de libellés et montants seront ajoutées ici -->
                                    </tbody>
                                    <tfoot>
                                        <tr>
                                            <td><strong>Total</strong></td>
                                            <td id="totalAmount"><strong>0.00 Ar</strong></td> <!-- Ajout de la devise ici -->
                                        </td>
                                        </tr>
                                    </tfoot>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Montant en lettres</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" id="totalInWords" name="montantLettre"/>
                            </div>
                        </div>
                    </div>
                </div>

                <input type="hidden" id="totalField" name="montantTotal">
                
                <p class="card-description">Pièce justificative</p>

                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Date</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" name="datePiece"/>
                        </div>
                    </div>
                </div>

                <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#pieceJustificative">Ajouter des pièce justificative</button>

                <div class="col-lg-12 grid-margin stretch-card">
                    <div class="card">
                        <div class="card-body">
                            <div class="table-responsive pt-3">
                                <table class="table table-bordered" id="pieceJustificativeTable"> <!-- Ajout de l'ID correct ici -->
                                    <thead>
                                        <tr>
                                            <th>Pièce justificative</th>
                                            <th>Action</th> <!-- Nouvelle colonne pour le bouton supprimer -->
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <!-- Les pièces justificatives ajoutées seront affichées ici -->
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Antananarivo, le</label>
                        <div class="col-sm-9">
                            <input type="date" class="form-control" name="nowDate"/>
                        </div>
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

<!-- Modal pour ajouter un libelle -->
<div class="modal fade custom-modal" id="addItemModal" tabindex="-1" role="dialog" aria-labelledby="addItemModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md" role="document"> <!-- modal-md pour un modal plus large -->
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="addItemModalLabel">Ajouter un Libelle</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addItemForm">
                    <div class="form-group row">
                        <label for="libelle" class="col-sm-4 col-form-label">Libelle</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="libelle" required>
                        </div>
                    </div>
                    <div class="form-group row">
                        <label for="montant" class="col-sm-4 col-form-label">Montant</label>
                        <div class="col-sm-6">
                            <input type="number" class="form-control" id="montant" required>
                        </div>
                        <div class="col-sm-2">
                            <span class="input-group-text">Ar</span> <!-- Afficher la devise -->
                        </div>
                    </div>
                </form>
                <!-- Table des libellés ajoutés temporairement -->
                <div class="table-responsive">
                    <table class="table table-bordered" id="tempItemTable">
                        <thead>
                            <tr>
                                <th>Libelle</th>
                                <th>Montant</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Les libellés ajoutés seront affichés ici temporairement -->
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-success" id="addAnotherItem">Ajouter un autre libellé</button>
                <button type="button" class="btn btn-primary" id="validateAndAddItems">Valider et ajouter au tableau</button>
            </div>
        </div>
    </div>
</div>

<!-- Modal pour afficher les pièces justificatives -->
<div class="modal fade custom-modal" id="pieceJustificative" tabindex="-1" role="dialog" aria-labelledby="pieceJustificativeLabel" aria-hidden="true">
    <div class="modal-dialog modal-md" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="pieceJustificativeLabel">Pièce justificative</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="addJustificativeForm">
                    <div class="form-group row">
                        <label for="justificativeLibelle" class="col-sm-4 col-form-label">Libelle</label>
                        <div class="col-sm-8">
                            <input type="text" class="form-control" id="justificativeLibelle" required>
                        </div>
                    </div>
                </form>
                <div class="table-responsive">
                    <table class="table table-bordered" id="justificativeTable">
                        <thead>
                            <tr>
                                <th>Pièce justificative</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Les lignes de pièces justificatives ajoutées seront affichées ici -->
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
                <button type="button" class="btn btn-success" id="addAnotherJustificative">Ajouter un autre fichier</button> <!-- Nouveau bouton -->
                <button type="button" class="btn btn-primary" id="addJustificative">Valider et ajouter au tableau</button>
            </div>
        </div>
    </div>
</div>

<script>
    let total = 0;
    let tempItems = []; // Tableau pour stocker les items temporairement

    // Ajouter un autre libellé dans le tableau du modal
    document.getElementById('addAnotherItem').addEventListener('click', function() {
        const libelle = document.getElementById('libelle').value.trim();
        const montant = parseFloat(document.getElementById('montant').value);

        // Vérification des valeurs
        if (libelle === "") {
            alert("Veuillez remplir le libelle.");
            return;
        }

        if (isNaN(montant) || montant <= 0) {
            alert("Veuillez entrer un montant valide supérieur à 0.");
            return;
        }

        // Ajouter dans le tableau temporaire
        tempItems.push({ libelle, montant });

        // Mettre à jour la table temporaire dans le modal
        const tempTableBody = document.querySelector('#tempItemTable tbody');
        const newRow = document.createElement('tr');
        const libelleCell = document.createElement('td');
        const montantCell = document.createElement('td');

        libelleCell.textContent = libelle;
        montantCell.textContent = montant.toFixed(2) + " Ar"; // Ajout de la devise ici

        newRow.appendChild(libelleCell);
        newRow.appendChild(montantCell);

        tempTableBody.appendChild(newRow);

        // Réinitialiser les champs du modal
        document.getElementById('addItemForm').reset();
    });

//     document.getElementById('validateAndAddItems').addEventListener('click', function() {
//     const tableBody = document.querySelector('#itemTable tbody');

//     tempItems.forEach(item => {
//         const newRow = document.createElement('tr');
//         const libelleCell = document.createElement('td');
//         const montantCell = document.createElement('td');
//         const actionCell = document.createElement('td');
//         const deleteButton = document.createElement('button');

//         libelleCell.textContent = item.libelle;
//         // montantCell.textContent = item.montant.toFixed(2) + " Ar";
//         montantCell.textContent = item.montant.toLocaleString('fr-FR', { minimumFractionDigits: 0 }) + " Ar";


//         deleteButton.textContent = "Supprimer";
//         deleteButton.className = "btn btn-danger btn-sm";
//         deleteButton.onclick = function() {
//             tableBody.removeChild(newRow);
//             total -= item.montant;
//             document.getElementById('totalAmount').innerText = total.toFixed(2) + " Ar";
//             mettreAJourTotalEnLettres(total); // Mettre à jour le total en lettres
//         };

//         actionCell.appendChild(deleteButton);
//         newRow.appendChild(libelleCell);
//         newRow.appendChild(montantCell);
//         newRow.appendChild(actionCell);

//         tableBody.appendChild(newRow);
//         total += item.montant;
//     });

//     // document.getElementById('totalAmount').innerText = total.toFixed(2) + " Ar";
//     document.getElementById('totalAmount').innerHTML = '<strong>' + total.toLocaleString('fr-FR', { minimumFractionDigits: 0 }) + ' Ar</strong>';


//     mettreAJourTotalEnLettres(total); // Mettre à jour le total en lettres

//     tempItems = [];
//     $('#addItemModal').modal('hide');
// });

document.getElementById('validateAndAddItems').addEventListener('click', function() {
    const tableBody = document.querySelector('#itemTable tbody');

    tempItems.forEach(item => {
        const newRow = document.createElement('tr');
        const libelleCell = document.createElement('td');
        const montantCell = document.createElement('td');
        const actionCell = document.createElement('td');
        const deleteButton = document.createElement('button');

        // Champs cachés pour envoyer les données au serveur
        const hiddenLibelle = document.createElement('input');
        hiddenLibelle.type = 'hidden';
        hiddenLibelle.name = 'libelles[]'; // Ajout à une liste de libellés
        hiddenLibelle.value = item.libelle;

        const hiddenMontant = document.createElement('input');
        hiddenMontant.type = 'hidden';
        hiddenMontant.name = 'montants[]'; // Ajout à une liste de montants
        hiddenMontant.value = item.montant;

        // Ajouter les champs cachés dans le formulaire
        document.getElementById('santeForm').appendChild(hiddenLibelle);
        document.getElementById('santeForm').appendChild(hiddenMontant);

        // Créer et ajouter la ligne du tableau visible
        libelleCell.textContent = item.libelle;
        montantCell.textContent = item.montant.toLocaleString('fr-FR', { minimumFractionDigits: 0 }) + " Ar";

        deleteButton.textContent = "Supprimer";
        deleteButton.className = "btn btn-danger btn-sm";
        deleteButton.onclick = function() {
            tableBody.removeChild(newRow);
            total -= item.montant;
            document.getElementById('totalAmount').innerText = total.toFixed(2) + " Ar";
            mettreAJourTotalEnLettres(total);
        };

        actionCell.appendChild(deleteButton);
        newRow.appendChild(libelleCell);
        newRow.appendChild(montantCell);
        newRow.appendChild(actionCell);

        tableBody.appendChild(newRow);
        total += item.montant;
    });

    document.getElementById('totalAmount').innerHTML = '<strong>' + total.toLocaleString('fr-FR', { minimumFractionDigits: 0 }) + ' Ar</strong>';
    mettreAJourTotalEnLettres(total);
    document.getElementById('totalField').value = total;

    tempItems = [];
    $('#addItemModal').modal('hide');
});




    // conversion de nombre en lettre
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

        return lettres.toUpperCase() + ' Ariary'; // Mise en majuscules ici
    }

    function mettreAJourTotalEnLettres(total) {
        const totalEnLettres = convertirNombreEnLettres(total);
        document.getElementById('totalInWords').value = totalEnLettres;
    }
    
    let justificatives = []; // Tableau pour stocker les pièces justificatives temporairement

    // Ajouter une autre pièce justificative dans le tableau temporaire
    document.getElementById('addAnotherJustificative').addEventListener('click', function() {
        const justificativeLibelle = document.getElementById('justificativeLibelle').value.trim();
        
        // Vérification des valeurs
        if (justificativeLibelle === "") {
            alert("Veuillez remplir le libellé de la pièce justificative.");
            return;
        }

        // Ajouter dans le tableau temporaire
        justificatives.push({ justificativeLibelle });

        // Mettre à jour la table temporaire dans le modal
        const tempTableBody = document.querySelector('#justificativeTable tbody');
        const newRow = document.createElement('tr');
        const justificativeCell = document.createElement('td');

        justificativeCell.textContent = justificativeLibelle;

        newRow.appendChild(justificativeCell);
        tempTableBody.appendChild(newRow);

        // Réinitialiser les champs du modal
        document.getElementById('addJustificativeForm').reset();
    });

    // Valider et ajouter toutes les pièces justificatives dans le tableau principal
    // document.getElementById('addJustificative').addEventListener('click', function() {
    //     const tableBody = document.querySelector('#pieceJustificativeTable tbody'); // Tableau principal pour les pièces justificatives

    //     justificatives.forEach(item => {
    //         // Créer une nouvelle ligne et cellules pour le tableau principal
    //         const newRow = document.createElement('tr');
    //         const justificativeCell = document.createElement('td');
    //         const actionCell = document.createElement('td'); // Nouvelle cellule pour le bouton supprimer
    //         const deleteButton = document.createElement('button'); // Création du bouton supprimer

    //         // Assigner les valeurs récupérées
    //         justificativeCell.textContent = item.justificativeLibelle;
            
    //         // Configurer le bouton supprimer
    //         deleteButton.textContent = "Supprimer";
    //         deleteButton.className = "btn btn-danger btn-sm"; // Classe Bootstrap pour le style
    //         deleteButton.onclick = function() {
    //             tableBody.removeChild(newRow);
    //         };

    //         // Ajouter les cellules à la nouvelle ligne
    //         actionCell.appendChild(deleteButton); // Ajouter le bouton supprimer à la cellule d'action
    //         newRow.appendChild(justificativeCell);
    //         newRow.appendChild(actionCell); // Ajouter la cellule d'action à la ligne

    //         // Ajouter la nouvelle ligne au tableau principal
    //         tableBody.appendChild(newRow);
    //     });

    //     // Vider le tableau temporaire
    //     justificatives = [];
    //     $('#pieceJustificative').modal('hide'); // Fermer le modal
    // });


    document.getElementById('addJustificative').addEventListener('click', function() {
    const tableBody = document.querySelector('#pieceJustificativeTable tbody');

    justificatives.forEach(item => {
        console.log("Adding justificative:", item.justificativeLibelle); // Ajout d'un log ici

        // Créer une nouvelle ligne et cellules pour le tableau principal
        const newRow = document.createElement('tr');
        const justificativeCell = document.createElement('td');
        const actionCell = document.createElement('td'); // Nouvelle cellule pour le bouton supprimer
        const deleteButton = document.createElement('button'); // Création du bouton supprimer

        // Assigner les valeurs récupérées
        justificativeCell.textContent = item.justificativeLibelle;

        // Configurer le bouton supprimer
        deleteButton.textContent = "Supprimer";
        deleteButton.className = "btn btn-danger btn-sm"; // Classe Bootstrap pour le style
        deleteButton.onclick = function() {
            tableBody.removeChild(newRow);
            // Supprimer le champ caché associé à cette pièce justificative
            const hiddenInput = document.querySelector(`input[name="justificatifs[]"][value="${item.justificativeLibelle}"]`);
            if (hiddenInput) {
                hiddenInput.remove();
            }
        };

        // Ajouter les cellules à la nouvelle ligne
        actionCell.appendChild(deleteButton); // Ajouter le bouton supprimer à la cellule d'action
        newRow.appendChild(justificativeCell);
        newRow.appendChild(actionCell); // Ajouter la cellule d'action à la ligne

        // Ajouter la nouvelle ligne au tableau principal
        tableBody.appendChild(newRow);

        // Créer un champ caché pour la pièce justificative
        const hiddenJustificative = document.createElement('input');
        hiddenJustificative.type = 'hidden';
        hiddenJustificative.name = 'justificatifs[]'; // Utiliser un tableau pour les justificatifs
        hiddenJustificative.value = item.justificativeLibelle;

        // Ajouter le champ caché au formulaire
        document.getElementById('santeForm').appendChild(hiddenJustificative);
    });

    console.log("Justificatives array before clearing:", justificatives); // Log pour voir les justificatifs

    justificatives = []; // Vider le tableau temporaire
    $('#pieceJustificative').modal('hide');
});





    // radio membre de la famille
    // function handleRadioSelection() {
    //     var fonctionnaireOui = document.getElementById('fonctionnaireOui');
    //     var fonctionnaireNon = document.getElementById('fonctionnaireNon');
    //     var familleSelect = document.getElementById('familleSelect');

    //     if (fonctionnaireOui.checked) {
    //         // Si "Oui", désactiver la sélection de la famille
    //         familleSelect.disabled = true;
    //         clearFamilleFields();  // Vider les champs liés à la famille
    //     } else if (fonctionnaireNon.checked) {
    //         // Si "Non", activer la sélection de la famille
    //         familleSelect.disabled = false;
    //     }
    // }

    // function clearFamilleFields() {
    //     // Réinitialiser la sélection et vider les champs liés à la famille
    //     document.getElementById("familleSelect").selectedIndex = 0;
    //     document.getElementById("filiationField").value = "";
    //     document.getElementById("nomPrenomMembreFamille").value = "";
    //     document.getElementById("dateNaissanceField").value = "";
    // }


    // function updateFiliationAndNomPrenom() {
    //     var select = document.getElementById("familleSelect");
    //     var selectedOption = select.options[select.selectedIndex];

    //     // Récupérer les valeurs du nom, prénom, filiation, et date de naissance
    //     var nom = selectedOption.getAttribute("data-nom");
    //     var prenom = selectedOption.getAttribute("data-prenom");
    //     var filiation = selectedOption.getAttribute("data-filiation");
    //     var dateNaissance = selectedOption.getAttribute("data-date-naissance");

    //     // Mettre à jour les champs correspondants
    //     document.getElementById("filiationField").value = filiation;
    //     document.getElementById("nomPrenomMembreFamille").value = nom + " " + prenom;
    //     document.getElementById("dateNaissanceField").value = dateNaissance;
    // }


    // function updateAgentDetails() {
    //     var agentSelect = document.querySelector("select[name='agent']");
    //     var selectedOption = agentSelect.options[agentSelect.selectedIndex];

    //     // Récupérer le matricule, décision et date de décision
    //     var matricule = selectedOption.getAttribute("data-matricule");
    //     var decision = selectedOption.getAttribute("data-decision");
    //     var date_decision = selectedOption.getAttribute("data-date-decition");

    //     // Mettre à jour les champs correspondants
    //     document.getElementById("matriculeField").value = matricule;
    //     document.getElementById("decisionField").value = decision;
    //     document.getElementById("entreField").value = date_decision;

    //     // Récupérer l'ID de l'agent sélectionné
    //     var agentId = selectedOption.value;

    //     // Appel AJAX pour obtenir les membres de la famille de cet agent
    //     fetch('/familles-par-agent/' + agentId)
    //         .then(response => response.json())
    //         .then(familles => {
    //             var familleSelect = document.getElementById('familleSelect');
    //             familleSelect.innerHTML = '<option disabled selected>Choisir une famille</option>'; // Réinitialise la sélection

    //             familles.forEach(famille => {
    //                 // Ajouter une option pour chaque membre de la famille
    //                 var option = document.createElement('option');
    //                 option.value = famille.id; // ID du membre de la famille
    //                 option.setAttribute('data-nom', famille.nom);
    //                 option.setAttribute('data-prenom', famille.prenom);
    //                 option.setAttribute('data-filiation', famille.filiation.filiation);
    //                 option.setAttribute('data-date-naissance', famille.dateNaissance);
    //                 option.textContent = famille.nom + ' ' + famille.prenom; // Affichage de l'option

    //                 familleSelect.appendChild(option);
    //             });

    //             // Activer le champ de sélection des familles
    //             familleSelect.disabled = false;
    //         })
    //         .catch(error => {
    //             console.error('Erreur lors du chargement des familles:', error);
    //         });
    // }

    document.addEventListener('DOMContentLoaded', function() {
    // Désactiver la sélection de la famille au démarrage
    document.getElementById('familleSelect').disabled = true;
});

// Fonction qui gère le changement de sélection d'agent
function updateAgentDetails() {
    var agentSelect = document.querySelector("select[name='agent']");
    var selectedOption = agentSelect.options[agentSelect.selectedIndex];

    // Récupérer le matricule, décision et date de décision
    var matricule = selectedOption.getAttribute("data-matricule");
    var decision = selectedOption.getAttribute("data-decision");
    var date_decision = selectedOption.getAttribute("data-date-decition");

    // Mettre à jour les champs correspondants
    document.getElementById("matriculeField").value = matricule;
    document.getElementById("decisionField").value = decision;
    document.getElementById("entreField").value = date_decision;

    // Vider la sélection des familles et la désactiver jusqu'à ce que l'utilisateur choisisse "Non"
    var familleSelect = document.getElementById('familleSelect');
    familleSelect.innerHTML = '<option disabled selected>Choisir une famille</option>';
    familleSelect.disabled = true;  // Toujours désactiver la sélection par défaut

    // Empêcher le chargement des familles tant que le radio "Non" n'est pas sélectionné
}

// Fonction qui gère le choix Oui/Non pour la question sur le fonctionnaire malade
function handleRadioSelection() {
    var fonctionnaireOui = document.getElementById('fonctionnaireOui');
    var fonctionnaireNon = document.getElementById('fonctionnaireNon');
    var familleSelect = document.getElementById('familleSelect');

    if (fonctionnaireOui.checked) {
        // Si "Oui", désactiver la sélection de la famille et vider les champs
        familleSelect.disabled = true;
        clearFamilleFields();  // Vider les champs liés à la famille
    } else if (fonctionnaireNon.checked) {
        // Si "Non", activer la sélection de la famille et charger les membres de la famille
        familleSelect.disabled = false;

        var agentSelect = document.querySelector("select[name='agent']");
        var agentId = agentSelect.options[agentSelect.selectedIndex].value;

        // Appel AJAX pour obtenir les membres de la famille de cet agent
        fetch('/familles-par-agent/' + agentId)
            .then(response => response.json())
            .then(familles => {
                familleSelect.innerHTML = '<option disabled selected>Choisir une famille</option>'; // Réinitialiser la sélection

                familles.forEach(famille => {
                    // Ajouter une option pour chaque membre de la famille
                    var option = document.createElement('option');
                    option.value = famille.id; // ID du membre de la famille
                    option.setAttribute('data-nom', famille.nom);
                    option.setAttribute('data-prenom', famille.prenom);
                    option.setAttribute('data-filiation', famille.filiation.filiation);
                    option.setAttribute('data-date-naissance', famille.dateNaissance);
                    option.textContent = famille.nom + ' ' + famille.prenom; // Affichage de l'option

                    familleSelect.appendChild(option);
                });

                // Ajouter un écouteur d'événement sur le changement de sélection
                familleSelect.addEventListener('change', updateFiliationAndNomPrenom);
            })
            .catch(error => {
                console.error('Erreur lors du chargement des familles:', error);
            });
    }
}

// Fonction pour vider les champs liés à la famille
function clearFamilleFields() {
    document.getElementById("familleSelect").selectedIndex = 0;
    document.getElementById("filiationField").value = "";
    document.getElementById("nomPrenomMembreFamille").value = "";
    document.getElementById("dateNaissanceField").value = "";
}

// Fonction pour mettre à jour la filiation et le nom/prénom de la famille sélectionnée
function updateFiliationAndNomPrenom() {
    var select = document.getElementById("familleSelect");
    var selectedOption = select.options[select.selectedIndex];

    // Récupérer les valeurs du nom, prénom, filiation, et date de naissance
    var nom = selectedOption.getAttribute("data-nom");
    var prenom = selectedOption.getAttribute("data-prenom");
    var filiation = selectedOption.getAttribute("data-filiation");
    var dateNaissance = selectedOption.getAttribute("data-date-naissance");

    // Mettre à jour les champs correspondants
    document.getElementById("filiationField").value = filiation;
    document.getElementById("nomPrenomMembreFamille").value = nom + " " + prenom;
    document.getElementById("dateNaissanceField").value = dateNaissance;
}

// function sendFormDataAndTableData() {
//     const form = document.getElementById('yourFormId');

//     // Gather the table data
//     const items = [];
//     document.querySelectorAll('#tableId tbody tr').forEach(row => {
//         const libelle = row.querySelector('.libelle').value;
//         const montant = row.querySelector('.montant').value;
//         items.push({ libelle, montant });
//     });

//     // Create a new FormData object from the form
//     const formData = new FormData(form);

//     // Prepare the XMLHttpRequest
//     const xhr = new XMLHttpRequest();
//     xhr.open("POST", "/generer_pdf/attestation-non-paiement", true);

//     // Handle the response (optional)
//     xhr.onload = function() {
//         if (xhr.status === 200) {
//             console.log('Request successful');
//         } else {
//             console.log('Error', xhr.responseText);
//         }
//     };

//     // First, convert the FormData object to a query string (URL-encoded format)
//     const queryString = new URLSearchParams(formData).toString();

//     // Set the appropriate headers: URL-encoded for the form submission
//     xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");

//     // Combine the form data and table data, and append the table data as JSON in the body
//     const data = {
//         items: items
//     };

//     // Send the request: URL-encoded form data and JSON-encoded items
//     xhr.send(queryString + "&items=" + encodeURIComponent(JSON.stringify(data.items)));
// }

</script>


<%@include file="../utils/footer.jsp" %>