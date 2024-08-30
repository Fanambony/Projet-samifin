<%@ page import="java.util.Optional" %>
<%@ page import="com.example.gestionrh.Model.Entity.Utilisateur" %>
<%@ page import="com.example.gestionrh.Model.Entity.DetailUtilisateur" %>

<%
    Optional<Utilisateur> utilisateur = (Optional<Utilisateur>) request.getAttribute("utilisateur");
    Utilisateur user = utilisateur.get();
%>

<%@include file="../utils/header.jsp" %>

<div class="col-12 grid-margin">
    <div class="card">
      <div class="card-body">
        <h4 class="card-title">Sante</h4>

        <form class="form-sample" id="santeForm" method="post">
          <div class="row">
            <div class="col-md-6">
              <div class="form-group row">
                <label class="col-sm-3 col-form-label">Nom</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="nom" value="<%= user.getNom() %>"/>
                </div>
              </div>
            </div>
            <div class="col-md-6">
              <div class="form-group row">
                <label class="col-sm-3 col-form-label">Prenom</label>
                <div class="col-sm-9">
                  <input type="text" class="form-control" name="prenom" value="<%= user.getPrenom() %>"/>
                </div>
              </div>
            </div>
          </div>
          <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Matricule</label>
                  <div class="col-sm-9">
                    <input type="number" class="form-control" name="matricule" value="<%= detail.getMatricule() %>">
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
                  <label class="col-sm-3 col-form-label">Qualite</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="qualite" value="<%= detail.getQualite() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Categorie</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="categorie" value="<%= detail.getCategorie() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Corps d'appartenance</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="corps_appartenance" value="<%= detail.getCorpsAppartenance() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Grade ou emploi</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="grade" value="<%= detail.getFonction().getNom() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Indice</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="indice" value="<%= detail.getIndice() %>"/>
                  </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-group row">
                  <label class="col-sm-3 col-form-label">Service Employeur</label>
                  <div class="col-sm-9">
                    <input type="text" class="form-control" name="service" value="<%= detail.getServiceEmployeur() %>"/>
                  </div>
                </div>
              </div>
            </div>
            <div class="row">
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

          <button type="button" class="btn btn-outline-primary btn-icon-text" onclick="submitForm('generer_pdf/bulletin-consultation')">
            Bulletin de consultation
            <i class="ti-printer btn-icon-append"></i>                                                                              
          </button>

          <button type="button" class="btn btn-outline-primary btn-icon-text" onclick="submitForm('demandeRemboursementController')">
            Demande de remboursement
            <i class="ti-printer btn-icon-append"></i>                                                                              
          </button>

        </form>
      </div>
    </div>
</div>

<script>
  function submitForm(actionUrl) {
    const form = document.getElementById('santeForm');
    form.action = actionUrl;
    form.submit();
  }
</script>

<%@include file="../utils/footer.jsp" %>