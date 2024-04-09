<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 03/11/2022
  Time: 00:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Irish Home Listings</title>
    <link href="assets/css/stylesheet.css" rel="stylesheet"/>
    <link rel="stylesheet" href="assets/css/design.css"/>
    <%--<script src="assets/js/slideshow.js"></script>--%>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarColor01" aria-controls="navbarColor01" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarColor01">
                <ul class="navbar-nav me-auto">
                    <li class="nav-item">
                        <a class='nav-link active' href="index.jsp">Home</a>
                        <span class="visually-hidden">(current)</span>
                    </li>
                    <c:if test="${loggedInUser == null}">
                        <li class="nav-item"><a class='nav-link' href="GetPropertiesServlet">Properties</a></li>
                        <li class="nav-item"><a class='nav-link' href="SignIn.jsp">Sign in</a></li>
                        <li class="nav-item"><a class='nav-link' href="SignUp.jsp">Sign up</a></li>
                    </c:if>
                    <c:if test="${loggedInUser != null && loggedInUser.userType == 'frontOffice'}">
                        <li class="nav-item"><a class='nav-link' href="GetPropertiesServlet">Properties</a></li>
                        <li class="nav-item"><a class='nav-link' href="GetRemoveFavouritePropertiesServlet">Favourites</a></li>
                        <li class="nav-item"><a class='nav-link' href="EditProfile.jsp">Edit Profile</a></li>
                        <li class="nav-item"><a class='nav-link' href="LogOutConfirmation.jsp">Log out</a></li>
                    </c:if>
                    <c:if test="${loggedInUser != null && loggedInUser.userType == 'backOffice'}">
                        <li class="nav-item"><a class='nav-link' href="GetAgentPropertiesServlet">Properties</a></li>
                        <li class="nav-item"><a class='nav-link' href="Fill_InPropertyDetailsFormServlet">Add Property</a></li>
                        <li class="nav-item"><a class='nav-link' href="GetArchivedPropertiesServlet">Archived Properties</a></li>
                        <li class="nav-item"><a class='nav-link' href="GetAllSuspendedUsersServlet">Activate User</a></li>
                        <li class="nav-item"><a class='nav-link' href="GetAllActivatedUsersServlet">Suspend User</a></li>
                        <li class="nav-item"><a class='nav-link' href="GetAllVendorsServlet">Vendors</a></li>
                        <li class="nav-item"><a class='nav-link' href="SignUp.jsp">Sign Up Vendor</a></li>
                        <li class="nav-item"><a class='nav-link' href="LogOutConfirmation.jsp">Log out</a></li>
                    </c:if>
                </ul>
            </div>
        </div>
    </nav>
</header>
