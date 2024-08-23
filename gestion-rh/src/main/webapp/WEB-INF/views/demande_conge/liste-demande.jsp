<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.VEtatDemande" %>

<%
    List<VEtatDemande> demande = (List<VEtatDemande>)request.getAttribute("demande");
%>

<%@include file="../utils/header.jsp" %>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Demande conge</h4>
            <div class="table-responsive">

                <br>
                    
                <div class="col-md-12">
                    <div class="form-group row">
                        <label class="col-sm-1 col-form-label">Etat :</label>
                        <div class="col-sm-3">
                            <select class="form-control">
                                <option value="">Toutes les etats</option>
                                <option value="1">En attente</option>
                                <option value="5">Valider</option>
                                <option value="10">Refuse</option>
                            </select>
                        </div>
                        <div class="col-sm-2">
                            <button type="submit" class="btn btn-primary mr-2">Valider</button>
                        </div>
                    </div>
                </div>

                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Date demande</th>
                            <th>Agent</th>
                            <th>Type conge</th>
                            <th>Date debut</th>
                            <th>Date fin</th>
                            <th>Nbr jours</th>
                            <th>Etat</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% for(VEtatDemande de : demande) { %>
                            <tr>
                                <td class="font-weight-bold"><%= de.getDateDemande() %></td>
                                <td><%= de.getNomUtilisateur() %> <%= de.getPrenomUtilisateur() %></td>
                                <td><%= de.getTypeConge() %></td>
                                <td><%= de.getDateDebut() %> <%= de.getDebutAbsence() %></td>
                                <td><%= de.getDateFin() %> <%= de.getFinAbsence() %></td>
                                <td class="font-weight-bold"><%= de.getNombreJoursConge() %> jours</td>
                                
                                <td><label class="badge badge-info"><%= de.getEtatDemande() %></label></td>
                                <td>
                                    <button type="button" class="btn btn-success btn-rounded btn-icon">
                                        <i class="ti-check"></i>
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

<%@include file="../utils/footer.jsp" %>