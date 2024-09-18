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
        <h4 class="card-title">DEMANDE DE REMBOURSEMENT DE FRAIS MEDICAUX</h4>

        <form class="form-sample" id="santeForm" method="post" action="generer_pdf/demande-remboursement">

        <% for(DetailUtilisateur detail : user.getDetailUtilisateurs()) { %>

            <!-- Form Fields -->
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Numero de decision</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="numero" value="<%= detail.getNumeroDecision() %>"/>
                        </div>
                    </div>
                </div>
            </div>

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
                            <input type="text" class="form-control" name="direction" value="<%= detail.getFonction().getDirection().getNom() %>"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Fonction ou grade</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="fonction" value="<%= detail.getFonction().getNom() %>"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Frais médicaux de </label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="frais_medicaux" placeholder="Sollicite le remboursement des frais médicaux de :"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Nom du malade</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="nom_malade" placeholder="Nom du malade :"/>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Lien</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="lien"/>
                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="form-group row">
                        <label class="col-sm-3 col-form-label">Montant</label>
                        <div class="col-sm-9">
                            <input type="text" class="form-control" name="montant" placeholder="En Ariary"/>
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

<%@include file="../utils/footer.jsp" %>
