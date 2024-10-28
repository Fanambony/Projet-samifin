<%@ page import="jakarta.servlet.http.HttpSession" %>
<%@ page contentType="text/html; charset=UTF-8"%>

<%
  String image = (String)session.getAttribute("image");
  String nom = (String)session.getAttribute("userNom");
  String prenom = (String)session.getAttribute("userPrenom");
  String defaultImage = "/assets/images/faces/user.jpeg";
  String imgSrc = (image != null && !image.isEmpty()) ? "data:image/jpeg;base64, " + image : defaultImage;
%>

<!DOCTYPE html>
<html lang="en">

<head>
  <!-- Required meta tags -->
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>SAMIFIN</title>

  <script src="/assets/js/jquery-3.7.1.min.js"></script>
  
  <script>
    $(document).ready(function() {
        // Function to fetch notifications
        function fetchNotifications() {
            $.ajax({
                url: '/notification_destinataire/notifications',
                type: 'GET',
                dataType: 'json',
                success: function(notifications) {
                    let unreadCount = 0;
                    let notificationDropdown = $("#notificationDropdownList");
    
                    notificationDropdown.empty();
    
                    notifications.forEach(notification => {
                        if (!notification.lu) { 
                            unreadCount++;
                        }
    
                        let notificationItem = 
                            '<a class="dropdown-item preview-item" href="/notification/detail-demande?idDemande=' + notification.notification.idDemande + '&idNotificationDestinataire=' + notification.id + '">'+
                                '<div class="preview-thumbnail">'+
                                    '<i class="mdi mdi-bell-ring-outline text-primary"></i>'+
                                '</div>'+
                                '<div class="preview-item-content d-flex align-items-start flex-column justify-content-center">'+
                                    '<h6 class="preview-subject font-weight-normal mb-1"> ' + notification.notification.typeNotification + '</h6>'+
                                    '<h6 class="preview-subject font-weight-normal mb-1"> ' + notification.notification.message +' </h6>'+
                                    '<p class="text-muted mb-0">Reçu le : ' + notification.notification.dateCreation + '</p>'+
                                '</div>'+
                            '</a>'; 
                        notificationDropdown.append(notificationItem);
                    });
    
                    $("#notificationCount").text(unreadCount);
                },
                error: function(error) {
                    console.log("Error fetching notifications:", error);
                }
            });
        }
    
        // Fetch notifications on page load
        fetchNotifications();
    });
    </script>
    


  <!-- plugins:css -->
  <link rel="stylesheet" href="/assets/vendors/feather/feather.css">
  <link rel="stylesheet" href="/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" href="/assets/vendors/css/vendor.bundle.base.css">
  <!-- endinject -->
  <!-- Plugin css for this page -->
  <link rel="stylesheet" href="/assets/vendors/datatables.net-bs4/dataTables.bootstrap4.css">
  <link rel="stylesheet" href="/assets/vendors/ti-icons/css/themify-icons.css">
  <link rel="stylesheet" type="text/css" href="/assets/js/select.dataTables.min.css">
  <link rel="stylesheet" href="/assets/vendors/mdi/css/materialdesignicons.min.css">
  <!-- End plugin css for this page -->
  <!-- inject:css -->
  <link rel="stylesheet" href="/assets/css/vertical-layout-light/style.css">
  <!-- endinject -->
  <link rel="shortcut icon" href="/assets/images/favicon.png" />
</head>
<body>
  <div class="container-scroller">
    <!-- partial:partials/_navbar.html -->


<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
	<div class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
	<a class="navbar-brand brand-logo mr-5" href="index.html"><img src="/assets/images/logo.png" class="mr-2" alt="logo"/></a>
	<a class="navbar-brand brand-logo-mini" href="index.html"><img src="/assets/images/logo-mini.png" alt="logo"/></a>
	</div>
	<div class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
	<button class="navbar-toggler navbar-toggler align-self-center" type="button" data-toggle="minimize">
		<span class="icon-menu"></span>
	</button>
	
	<ul class="navbar-nav mr-lg-2">
		<li class="nav-item nav-search d-none d-lg-block">
		<div class="input-group">
			<div class="input-group-prepend hover-cursor" id="navbar-search-icon">
			<span class="input-group-text" id="search">
				<i class="icon-search"></i>
			</span>
			</div>
			<input type="text" class="form-control" id="navbar-search-input" placeholder="Search now" aria-label="search" aria-describedby="search">
		</div>
		</li>
	</ul>


	<ul class="navbar-nav navbar-nav-right">

    <li class="nav-item dropdown" style="margin-top: 10px;">
      <a class="nav-link count-indicator dropdown-toggle" href="/notification_destinataire/tous_notifications" title="Toutes les notifications">
        <i class="mdi mdi-message-text-outline mx-0" style="font-size: 20px;"></i>
      </a>
    </li>

    <li class="nav-item dropdown">
      <a class="nav-link count-indicator dropdown-toggle" id="notificationDropdown" href="#" data-toggle="dropdown" title="Notifications non lues">
        <i class="icon-bell mx-0"></i>
        <strong><span class="count" id="notificationCount" style="color: rgb(255, 255, 255); font-size: 10px; width: 17px; height: 17px; background-color: rgb(255, 0, 0);">0</span></strong>
      </a>
    
      <div class="dropdown-menu dropdown-menu-right navbar-dropdown preview-list" aria-labelledby="notificationDropdown">
        <p class="mb-0 font-weight-normal float-left dropdown-header">Notifications</p>
        <div id="notificationDropdownList" class="preview-list"></div>
      </div>
      
    </li>
		
		<li class="nav-item nav-profile dropdown">
      <a class="nav-link dropdown-toggle" href="#" data-toggle="dropdown" id="profileDropdown">
        <img src="<%= imgSrc %>" alt="profile"/> <%= nom %> <%= prenom %>
        <i class="mdi mdi-menu-down mdi-24px"></i>
      </a>
      <div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="profileDropdown">
        <a class="dropdown-item" href="/detail-utilisateur">
          <i class="ti-user text-primary"></i>
          Profil
        </a>
        <a class="dropdown-item" href="/deconnecter">
          <i class="ti-power-off text-primary"></i>
          Déconnecter
        </a>
      </div>
		</li>
	</ul>

	
	<button class="navbar-toggler navbar-toggler-right d-lg-none align-self-center" type="button" data-toggle="offcanvas">
		<span class="icon-menu"></span>
	</button>
	</div>
</nav>


     
    <!-- partial -->
    <div class="container-fluid page-body-wrapper">
      <!-- partial:partials/_settings-panel.html -->
      <div class="theme-setting-wrapper">
        <div id="settings-trigger"><i class="ti-settings"></i></div>
        <div id="theme-settings" class="settings-panel">
          <i class="settings-close ti-close"></i>
          <p class="settings-heading">SIDEBAR SKINS</p>
          <div class="sidebar-bg-options selected" id="sidebar-light-theme"><div class="img-ss rounded-circle bg-light border mr-3"></div>Light</div>
          <div class="sidebar-bg-options" id="sidebar-dark-theme"><div class="img-ss rounded-circle bg-dark border mr-3"></div>Dark</div>
          <p class="settings-heading mt-2">HEADER SKINS</p>
          <div class="color-tiles mx-0 px-4">
            <div class="tiles success"></div>
            <div class="tiles warning"></div>
            <div class="tiles danger"></div>
            <div class="tiles info"></div>
            <div class="tiles dark"></div>
            <div class="tiles default"></div>
          </div>
        </div>
      </div>

      <%@include file="navbar.jsp"%>
      
      <!-- partial -->
      <div class="main-panel">
        <div class="content-wrapper">
			<div class="row">
