<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.PeriodeAbsence" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeConge" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>

<%
    List<PeriodeAbsence> periode = (List<PeriodeAbsence>)request.getAttribute("periodes");
    List<TypeConge> typeConge = (List<TypeConge>)request.getAttribute("typeConge");
    List<VEtatDemande> etatDemande = (List<VEtatDemande>)request.getAttribute("etatDemandes");
%>

<%@include file="../utils/header.jsp" %>

<style>
    .circle-card {
        border-radius: 50%;
        width: 180px;
        height: 180px;
        margin: 0 auto;
        display: flex;
        align-items: center;
        justify-content: center;
        font-size: 1.5rem;
    }
    .custom-modal .modal-dialog {
        margin-top: 0px;
    }
    /*.card {
        box-shadow: 0 2px 4px rgba(0,0,0,0.1);
        border: none;
    }
    .card-body {
        display: flex;
        flex-direction: column;
        justify-content: center; 
        height: 100%;
    }
    .card-title {
        margin-bottom: 1rem;
    }*/
  </style>

<div class="container-fluid">
    <div class="row">
        <!-- Tableau de bord -->
        <div class="col-md-9 grid-margin stretch-card">
            <div class="card">
                <div class="card-body">
                  
                    <h4 class="card-title">Gestion de conge</h4>
                    
                    <div class="row mb-3">
                        <div class="col-md-4">
                            <button type="button" class="btn btn-outline-primary btn-fw btn-block" data-toggle="modal" data-target="#faireDemandeModal">Faire une demande de conge</button>
                        </div>
                    </div>
                    
                    <div class="table-responsive">
                        <table class="table table-striped table-borderless">
                            <thead>
                                <tr>
                                    <th>Date demande</th>
                                    <th>Type demande</th>
                                    <th>Date debut</th>
                                    <th>Date fin</th>
                                    <th>Nbr jours</th>
                                    <th>Etat</th>
                                    <th>Action</th>
                                </tr>  
                            </thead>
                            <tbody>
                                <% for(VEtatDemande etat : etatDemande) { %>
                                <tr>
                                    <td class="font-weight-bold"><%= etat.getDateDemande() %></td>
                                    <td><%= etat.getTypeConge() %></td>
                                    <td><%= etat.getDateDebut() %></td>
                                    <td><%= etat.getDateFin() %></td>
                                    <td class="font-weight-bold"> </td>
                                    <td class="font-weight-medium"><div class="badge badge-info"><%= etat.getEtatDemande() %></div></td>
                                    <td>
                                      <button type="button" class="btn btn-info btn-rounded btn-icon" data-toggle="modal" data-target="#userDetailModal">
                                          <i class="ti-eye"></i>
                                      </button>
                                      <button type="button" class="btn btn-success btn-rounded btn-icon">
                                          <i class="ti-pencil"></i>
                                      </button>
                                      <button type="button" class="btn btn-danger btn-rounded btn-icon">
                                          <i class="ti-trash"></i>
                                      </button>
                                    </td>
                                </tr>
                                <% } %>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>

        <!-- Cartes -->
        <div class="col-md-3 grid-margin stretch-card">
            <div class="row">
                <!-- Carte pour les conges restants -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Conge restant</h4>
                            <div class="circle-card" style="border: solid rgb(17, 142, 46) 20px;">
                                <h3>90 jours</h3>
                            </div>
                        </div>
                    </div>
                </div>
                
                <!-- Carte pour les permissions restantes -->
                <div class="col-md-12 mb-4">
                    <div class="card text-center" style="height: 300px;">
                        <div class="card-body">
                            <h4 class="card-title">Permission restante</h4>
                            <div class="circle-card" style="border: solid rgb(231, 0, 0) 20px;">
                                <h3>06 jours</h3>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- modal demande conge -->
<div class="modal fade custom-modal" id="faireDemandeModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog modal-md modal-dialog-centered" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Demande de conge</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <form id="faireDemandeForm" action="/demande_conge/ajout_conge" method="get">
                    <div class="container-fluid">
                        <div class="form-row">
                            <div class="form-group col-md-12">
                                <div class="row">
                                    <label class="col-sm-12 col-form-label">Type d'absence</label>
                                    <div class="col-sm-12">
                                        <select class="form-control" id="typeConge" name="typeConge">
                                            <% for(TypeConge type : typeConge) { %>
                                                <option value="<%= type.getId() %>"><%= type.getNom() %></option>
                                            <% } %>
                                        </select>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Date debut</label>
                                    <div class="col-sm-12">
                                        <input type="date" class="form-control" name="date_debut" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <% for(PeriodeAbsence pa : periode) { %>
                                        <div class="col-sm-4">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                <input type="radio" class="form-check-input" name="debut_absence" id="membershipRadios1" value="<%= pa.getId() %>">
                                                <%= pa.getValeur() %>
                                                </label>
                                            </div>
                                        </div>
                                    <% } %>
                                </div>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <label class="col-sm-12 col-form-label">Date fin</label>
                                    <div class="col-sm-12">
                                        <input type="date" class="form-control" name="date_fin" required>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <div class="form-group row">
                                    <% for(PeriodeAbsence pa : periode) { %>
                                        <div class="col-sm-4">
                                            <div class="form-check">
                                                <label class="form-check-label">
                                                <input type="radio" class="form-check-input" name="fin_absence" id="membershipRadiosfin" value="<%= pa.getId() %>">
                                                <%= pa.getValeur() %>
                                                </label>
                                            </div>
                                        </div>
                                    <% } %>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="card-body">
                                <div class="form-group">
                                    <label for="exampleTextarea1">Commentaire</label>
                                    <textarea class="form-control" id="exampleTextarea1" rows="4" name="commentaire"></textarea>
                                </div>
                            </div>
                        </div>

                    </div>
                <button type="submit" class="btn btn-primary">Valider</button>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Fermer</button>
            </div>
        </div>
    </div>
</div>

<%@include file="../utils/footer.jsp" %>