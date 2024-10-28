<%@ page import="com.example.gestionrh.Model.Entity.NotificationDestinataire" %>
<%@ page import="org.springframework.data.domain.Page" %>

<%
    Page<NotificationDestinataire> notifications = (Page<NotificationDestinataire>)request.getAttribute("notifications");
%>

<%@ include file="../utils/header.jsp" %>

<div class="col-lg-12 grid-margin stretch-card">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">NOTIFICATIONS</h4>
            
            <div class="notification-list">
                <% for (NotificationDestinataire n : notifications) { 
                    boolean unread = !n.getLu(); // Vérifie si la notification est non lue
                %>
                <a href="/notification/detail-demande?idDemande=<%= n.getNotification().getIdDemande() %>&&idNotificationDestinataire=<%= n.getId() %>" class="notification-link">
                    <div class="notification-item <%= unread ? "unread" : "" %>">
                        <div class="notification-header">
                            <span class="notification-type"><%= n.getNotification().getTypeNotification() %></span>
                            <div class="notification-date-status">
                                <span class="notification-date"><%= n.getNotification().getDateCreation() %></span>
                                <span class="notification-status"><%= unread ? "Non lu" : "Lu" %></span>
                            </div>
                        </div>
                        <div class="notification-message">
                            <%= n.getNotification().getMessage() %>
                        </div>
                        <div class="notification-sender">
                            <span>De : <%= n.getNotification().getUtilisateur().getNom() %> <%= n.getNotification().getUtilisateur().getPrenom() %></span>
                        </div>
                    </div>
                </a>
                <% } %>
            </div>

            <br>

            <div class="pagination d-flex justify-content-center">
                <ul class="pagination">
                    <!-- Lien vers la page précédente -->
                    <li class="page-item <%= notifications.isFirst() ? "disabled" : "" %>">
                        <a class="page-link" href="?page=<%= notifications.getNumber() - 1 %>&size=<%= notifications.getSize() %>">&laquo;</a>
                    </li>
            
                    <% 
                        int maxPagesToShow = 4;
                        int currentPage = notifications.getNumber() + 1; // Passe en index 1-based
                        int totalPages = notifications.getTotalPages();
                        int startPage = Math.max(1, currentPage - maxPagesToShow / 2);
                        int endPage = Math.min(totalPages, startPage + maxPagesToShow - 1);
            
                        if (startPage > 1) { 
                    %>
                        <li class="page-item"><a class="page-link" href="?page=0&size=<%= notifications.getSize() %>">1</a></li>
                        <% if (startPage > 2) { %>
                            <li class="page-item disabled"><span class="page-link">...</span></li>
                        <% } %>
                    <% } %>
            
                    <!-- Liens pour les pages visibles -->
                    <% for (int i = startPage; i <= endPage; i++) { %>
                        <li class="page-item <%= (i == currentPage) ? "active" : "" %>">
                            <a class="page-link" href="?page=<%= i - 1 %>&size=<%= notifications.getSize() %>"><%= i %></a>
                        </li>
                    <% } %>
            
                    <% if (endPage < totalPages) { %>
                        <% if (endPage < totalPages - 1) { %>
                            <li class="page-item disabled"><span class="page-link">...</span></li>
                        <% } %>
                        <li class="page-item"><a class="page-link" href="?page=<%= totalPages - 1 %>&size=<%= notifications.getSize() %>"><%= totalPages %></a></li>
                    <% } %>
            
                    <!-- Lien vers la page suivante -->
                    <li class="page-item <%= notifications.isLast() ? "disabled" : "" %>">
                        <a class="page-link" href="?page=<%= notifications.getNumber() + 1 %>&size=<%= notifications.getSize() %>">&raquo;</a>
                    </li>
                </ul>
            </div>

        </div>
    </div>
</div>

<%@ include file="../utils/footer.jsp" %>

<style>
    .notification-list {
        display: flex;
        flex-direction: column;
        gap: 15px;
    }
.notification-link {
    text-decoration: none;
    color: inherit;
}
.notification-link:hover {
    text-decoration: none; /* Empêche le soulignement au survol */
}
    .notification-item {
        padding: 15px;
        border: 1px solid #e0e0e0;
        border-radius: 5px;
        background-color: #f8f9fa;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .notification-item.unread {
        background-color: #e3f2fd;
        border-left: 5px solid #1976d2;
    }
    .notification-header {
        display: flex;
        justify-content: space-between;
        font-weight: bold;
        font-size: 0.9rem;
        color: #555;
    }
    .notification-type {
        color: #1976d2;
    }
    .notification-date-status {
        display: flex;
        flex-direction: column;
        align-items: flex-end;
    }
    .notification-date {
        font-size: 0.8rem;
        color: #888;
    }
    .notification-status {
        font-size: 0.8rem;
        color: #00bc00;
        font-weight: bold;
        margin-top: 3px;
    }
    .notification-item.unread .notification-status {
        color: #d32f2f; /* Couleur rouge pour les notifications non lues */
    }
    .notification-message {
        margin-top: -10px; /* Espacement sous le type */
        font-size: 1rem;
        color: #333;
    }
    .notification-sender {
        margin-top: 8px;
        font-size: 0.9rem;
        color: #555;
    }
</style>
