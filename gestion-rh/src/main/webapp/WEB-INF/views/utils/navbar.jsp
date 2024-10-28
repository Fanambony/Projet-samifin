<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
  Integer userType = (Integer)session.getAttribute("userType");
%>

<nav class="sidebar sidebar-offcanvas" id="sidebar">
    <ul class="nav">

      <%
        if (userType == null || userType != 15 ) {
      %>

      <li class="nav-item">
        <a class="nav-link" href="/detail_utilisateur/page-conge">
          <i class="ti-user menu-icon"></i>
          <span class="menu-title">Congé</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link" data-toggle="collapse" href="#ui-basic" aria-expanded="false" aria-controls="ui-basic">
          <i class="ti-heart menu-icon"></i>
          <span class="menu-title">Santé</span>
          <i class="menu-arrow"></i>
        </a>
        <div class="collapse" id="ui-basic">
          <ul class="nav flex-column sub-menu">
            <li class="nav-item"> <a class="nav-link" href="/bulletin-consultation">Bulletin</a></li>
            <li class="nav-item"> <a class="nav-link" href="/demande-remboursement">Remboursement</a></li>
          </ul>
        </div>
      </li>
      <% } %>

      <%
        if (userType == null || userType != 1 && userType != 5 && userType != 10) {
      %>
      <li class="nav-item">
        <a class="nav-link" href="/attestation-non-paiement">
          <i class="ti-receipt menu-icon"></i>
          <span class="menu-title">Attes. non paiement</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="/v_etat_demande/liste-demande">
          <i class="ti-check-box menu-icon"></i>
          <span class="menu-title">Validation congé</span>
        </a>
      </li>

      <li class="nav-item">
        <a class="nav-link" href="/list-utilisateur">
          <i class="ti-user menu-icon"></i>
          <span class="menu-title">Gestion des utilisateurs</span>
        </a>
      </li>

      <!-- <li class="nav-item">
        <a class="nav-link" href="/direction/gerer-direction">
          <i class="ti-briefcase menu-icon"></i>
          <span class="menu-title">Gestion de la direction</span>
        </a>
      </li> -->

      <li class="nav-item">
        <a class="nav-link" href="/demande_conge/annulation-conge">
            <i class="ti-back-left menu-icon"></i>
            <span class="menu-title">Annuler congé</span>
        </a>
    </li>

    <li class="nav-item">
      <a class="nav-link" href="/v_soldes_utilisateur/generer-solde">
        <i class="ti-clipboard menu-icon"></i>
        <span class="menu-title">Générer solde de congé</span>
      </a>
    </li>

    <li class="nav-item">
      <a class="nav-link" href="/v_historique_conge/etat-conge">
        <i class="ti-calendar menu-icon"></i>
        <span class="menu-title">Etat congé personnels</span>
      </a>
    </li>

  <% } %>
    </ul>
  </nav>