<%@ page import="java.util.Optional" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    Utilisateur user = (Utilisateur) request.getAttribute("utilisateur");
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
                    <input type="text" class="form-control" name="qualite" value="<%= detail.getQualite() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Categorie</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="categorie" value="<%= detail.getCategorie() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Corps d'appartenance</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="corps_appartenance" value="<%= detail.getCorpsAppartenance() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Grade ou emploi</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="grade" value="<%= detail.getFonction().getNom() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Indice</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="indice" value="<%= detail.getIndice() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Service Employeur</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="service" value="<%= detail.getServiceEmployeur() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Localite de service</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="localite" value="<%= detail.getLocaliteService() %>"/>
                  </div>
                </div>
              </div>
            </div>
          <% } %>

          <p class="card-description">Renseignements concernant le membre de la famille</p>

          <div class="form-group row mb-2">
            <label class="col-sm-7 col-form-label">Le malade est-il le fonctionnaire lui-même ?</label>
            <div class="col-sm-5">
                <input type="radio" name="fonctionnaire_malade" value="OUI"> Oui -
                <input type="radio" name="fonctionnaire_malade" value="NON"> Non
            </div>
          </div>
        
          <div class="form-group row mb-2">
            <label class="col-sm-7 col-form-label">Le malade est-il un membre de la famille ?</label>
            <div class="col-sm-5">
                <input type="radio" name="membre_famille_malade" value="OUI"> Oui -
                <input type="radio" name="membre_famille_malade" value="NON"> Non
              </div>
          </div>
        
          <div class="form-group row">
            <label class="col-sm-3 col-form-label">Nom et Prénom</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" name="nom_prenom_membre_famille" />
            </div>
          </div>

          <div class="form-group row">
            <label class="col-sm-3 col-form-label">Filiation</label>
            <div class="col-sm-5">
              <input type="text" class="form-control" name="filiation_membre_famille" />
            </div>
          </div>

          <div class="form-group row">
            <label class="col-sm-3 col-form-label">Né(e) le</label>
            <div class="col-sm-5">
              <input type="date" class="form-control" name="date_naissance_membre_famille" />
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

<%@include file="../utils/footer.jsp" %>