<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.TypeConge" %>

<%
    List<TypeConge> typeConge = (List<TypeConge>)request.getAttribute("typeConge");
%>

<%@include file="../utils/header.jsp" %>



<div class="col-12 grid-margin">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Demande de conge</h4>
            <form class="form-sample" method="post" action="#">
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Type d'absence</label>
                            <div class="col-sm-9">
                                <select class="form-control">
                                    <% for(TypeConge tc : typeConge) { %>
                                        <option value="<%= tc.getId() %>"><%= tc.getNom() %></option>
                                    <% } %>
                                </select>
                            </div>
                        </div>
                    </div>
                
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date debut</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" name="date_debut" required/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Date fin</label>
                            <div class="col-sm-9">
                                <input type="date" class="form-control" name="date_fin" required/>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Debut d'absence</label>
                            <div class="col-sm-4">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="membershipRadios" id="membershipRadios1" value="" checked>
                                    AM
                                    </label>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="membershipRadios" id="membershipRadios2" value="option2">
                                        PM
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="form-group row">
                            <label class="col-sm-3 col-form-label">Fin d'absence</label>
                            <div class="col-sm-4">
                                <div class="form-check">
                                    <label class="form-check-label">
                                    <input type="radio" class="form-check-input" name="membershipRadiosfin" id="membershipRadios3" value="">
                                    AM
                                    </label>
                                </div>
                            </div>
                            <div class="col-sm-5">
                                <div class="form-check">
                                    <label class="form-check-label">
                                        <input type="radio" class="form-check-input" name="membershipRadiosfin" id="membershipRadios4" value="option2" checked>
                                        PM
                                    </label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="card-body">
                        <div class="form-group">
                            <label for="exampleTextarea1">Commentaire</label>
                            <textarea class="form-control" id="exampleTextarea1" rows="4"></textarea>
                        </div>
                    </div>
                </div>
                
                <button type="submit" class="btn btn-primary mr-2">Submit</button>
                <button class="btn btn-light">Cancel</button>
            </form>
        </div>
    </div>
</div>

<%@include file="../utils/footer.jsp" %>