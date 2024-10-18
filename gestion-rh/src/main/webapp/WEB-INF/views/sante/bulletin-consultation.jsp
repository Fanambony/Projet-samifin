<%@ page import="java.util.Optional" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.Famille" %>

<%@ page import="com.example.gestionrh.Model.Entity.Categorie" %>
<%@ page import="com.example.gestionrh.Model.Entity.Qualite" %>
<%@ page import="com.example.gestionrh.Model.Entity.ServiceEmployeur" %>
<%@ page import="com.example.gestionrh.Model.Entity.LocaliteService" %>
<%@ page import="com.example.gestionrh.Model.Entity.Indice" %>
<%@ page import="com.example.gestionrh.Model.Entity.CorpsAppartenance" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>

<%
    Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
    List<Famille> familles = (List<Famille>)request.getAttribute("famille");

    List<Categorie> categories = (List<Categorie>)request.getAttribute("categories");
    List<Qualite> qualites = (List<Qualite>)request.getAttribute("qualites");
    List<ServiceEmployeur> serviceEmployeurs = (List<ServiceEmployeur>)request.getAttribute("serviceEmployeurs");
    List<LocaliteService> localiteServices = (List<LocaliteService>)request.getAttribute("localiteServices");
    List<Indice> indices = (List<Indice>)request.getAttribute("indices");
    List<CorpsAppartenance> corpsAppartenances = (List<CorpsAppartenance>)request.getAttribute("corpsAppartenances");
    List<Fonction> fonctions = (List<Fonction>)request.getAttribute("fonctions");
%>

<%@include file="../utils/header.jsp" %>

<div class="col-12 grid-margin">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">BULLETIN DE CONSULTATION</h4>

        <p class="card-description">Renseignements concernant l'agent</p>

        <form class="form-sample" id="santeForm" method="post" action="generer_pdf/bulletin-consultation">

          <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Nom et prenom</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="nom" value="<%= user.getNom() %> <%= user.getPrenom() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Date de naissance</label>
                  <div class="col-sm-9">
                    <input type="date" class="form-control" name="date_naissance" value="<%= user.getDateNaissance() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Matricule</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="matricule" value="<%= detail.getMatricule() %>">
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Qualite</label>
                  <div class="col-sm-9">
                    <select class="form-control" name="qualite">
                      <% for(Qualite q : qualites) { 
                        String selected = q.getLibelle().equals(detail.getQualite().getLibelle()) ? "selected" : "";  
                      %>
                        <option value="<%= q.getLibelle() %>" <%= selected %>><%= q.getLibelle() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Categorie</label>
                  <div class="col-sm-9">
                    <select class="form-control" name="categorie">
                      <% for(Categorie c : categories) { 
                        String selected = c.getLibelle().equals(detail.getCategorie().getLibelle()) ? "selected" : "";  
                      %>
                        <option value="<%= c.getLibelle() %>" <%= selected %>><%= c.getLibelle() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Corps d'appartenance</label>
                  <div class="col-sm-9">
                    <select class="form-control" name="corps_appartenance">
                      <% for(CorpsAppartenance ca : corpsAppartenances) { 
                        String selected = ca.getLibelle().equals(detail.getCorps_appartenance().getLibelle()) ? "selected" : "";  
                      %>
                        <option value="<%= ca.getLibelle() %>" <%= selected %>><%= ca.getLibelle() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Grade ou emploi</label>
                  <div class="col-sm-9">
                    <select class="form-control" name="grade">
                      <% for(Fonction f : fonctions) { 
                        String selected = f.getNom().equals(detail.getFonction().getNom()) ? "selected" : "";  
                      %>
                        <option value="<%= f.getNom() %>" <%= selected %>><%= f.getNom() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Indice</label>
                  <div class="col-sm-9">
                    <select class="form-control" name="indice">
                      <% for(Indice i : indices) { 
                        String selected = i.getLibelle().equals(detail.getIndice().getLibelle()) ? "selected" : "";  
                      %>
                        <option value="<%= i.getLibelle() %>" <%= selected %>><%= i.getLibelle() %></option>
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
                    <select class="form-control" name="service">
                      <% for(ServiceEmployeur se : serviceEmployeurs) { 
                        String selected = se.getLibelle().equals(detail.getService_employeur().getLibelle()) ? "selected" : "";  
                      %>
                        <option value="<%= se.getLibelle() %>" <%= selected %>><%= se.getLibelle() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Localite de service</label>
                  <div class="col-sm-9">
                    <select class="form-control" name="localite">
                      <% for(LocaliteService l : localiteServices) { 
                        String selected = l.getLibelle().equals(detail.getLocalite_service().getLibelle()) ? "selected" : "";  
                      %>
                        <option value="<%= l.getLibelle() %>" <%= selected %>><%= l.getLibelle() %></option>
                      <% } %>
                    </select>
                  </div>
                </div>
              </div>
            </div>
          <% } %>

          <p class="card-description">Renseignements concernant le membre de la famille</p>

          <div class="form-group row mb-2">
            <label class="col-sm-7 col-form-label">Le malade est-il le fonctionnaire lui-même ?</label>
            <div class="col-sm-5">
              <input type="radio" id="fonctionnaireOui" name="fonctionnaire_malade" value="OUI" onchange="handleRadioSelection()"> Oui -
              <input type="radio" id="fonctionnaireNon" name="fonctionnaire_malade" value="NON" onchange="handleRadioSelection()"> Non
            </div>
          </div>
          
          <div class="form-group row mb-2">
            <label class="col-sm-7 col-form-label">Le malade est-il un membre de la famille ?</label>
            <div class="col-sm-5">
              <input type="radio" id="familleOui" name="membre_famille_malade" value="OUI" onchange="handleRadioSelection()"> Oui -
              <input type="radio" id="familleNon" name="membre_famille_malade" value="NON" onchange="handleRadioSelection()"> Non
            </div>
          </div>
          
          <div class="form-group row">
            <label class="col-sm-3 col-form-label">Nom et Prénom de la famille</label>
            <div class="col-sm-5">
              <select class="form-control" id="familleSelect" name="nom_famille" onchange="updateFiliationAndNomPrenom()" disabled>
                <option value="" disabled selected>Choisir une famille</option>
                <% for(Famille f : familles) { %>
                  <option value="<%= f.getId() %>" data-nom="<%= f.getNom() %>" data-prenom="<%= f.getPrenom() %>" data-filiation="<%= f.getFiliation().getFiliation() %>" data-date-naissance="<%= f.getDateNaissance() %>">
                    <%= f.getNom() %> <%= f.getPrenom() %>
                  </option>
                <% } %>
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

<!-- Script JavaScript -->
<script type="text/javascript">
  function handleRadioSelection() {
    var fonctionnaireOui = document.getElementById('fonctionnaireOui');
    var fonctionnaireNon = document.getElementById('fonctionnaireNon');
    var familleOui = document.getElementById('familleOui');
    var familleNon = document.getElementById('familleNon');
    var familleSelect = document.getElementById('familleSelect');

    if (fonctionnaireOui.checked) {
      // Si "Le malade est-il le fonctionnaire lui-même ?" est coché OUI
      familleSelect.disabled = true;  // Désactive la sélection de la famille
      familleNon.checked = true;      // Coche "Non" pour "Le malade est-il un membre de la famille ?"
      clearFamilleFields();           // Vide les champs liés à la famille
    } else if (fonctionnaireNon.checked) {
      // Si "Le malade est-il le fonctionnaire lui-même ?" est coché NON
      familleSelect.disabled = false;  // Active la sélection de la famille
      familleOui.checked = true;       // Coche "Oui" pour "Le malade est-il un membre de la famille ?"
    }
  }

  function clearFamilleFields() {
    // Vider les champs liés à la sélection de la famille
    document.getElementById("familleSelect").selectedIndex = 0; // Réinitialise la sélection
    document.getElementById("filiationField").value = "";       // Vide le champ filiation
    document.getElementById("nomPrenomMembreFamille").value = ""; // Vide le champ nom et prénom
    document.getElementById("dateNaissanceField").value = "";    // Vide le champ date de naissance
  }

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
</script>


<%@include file="../utils/footer.jsp" %>