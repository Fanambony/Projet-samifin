<%@ page import="java.util.List" %>
<%@ page import="com.example.gestionrh.Model.Entity.Direction" %>
<%@ page import="com.example.gestionrh.Model.Entity.Fonction" %>

<%
    List<Direction> direction = (List<Direction>)request.getAttribute("directions");
%>

<%@include file="../utils/header.jsp" %>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">Gerer direction et fonction</h4>
            
            <div class="row mb-3">
                <div class="col-md-4">
                    <button type="button" class="btn btn-outline-primary btn-fw btn-block">Ajout direction</button>
                </div>
            </div>

            <div class="row mb-3">
                <div class="col-md-4">
                    <button type="button" class="btn btn-outline-primary btn-fw btn-block">Ajout fonction</button>
                </div>
            </div>

            <div class="table-responsive pt-3">
                <table class="table table-bordered">
                    <thead>
                    <tr>
                        <th>Direction</th>
                        <th>Fonction</th>
                    </tr>
                    </thead>
                    <tbody>
                        <% for(Direction d : direction) { %>
                            <tr>
                                <td><%= d.getNom() %></td>
                                <% for(Fonction f : d.getFonctions()) { %>
                                    <td><%= f.getNom() %></td>
                                <% } %>
                                </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<%@include file="../utils/footer.jsp" %>