<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 05/11/2022
  Time: 13:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Log out</title>
    <link href="assets/css/stylesheet.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
<br>
<h2>Are you sure you want to log out?</h2><br>
<form>
    <a class="btn btn-success" href="LogOutServlet">Yes</a>
    <input class="btn btn-danger" type="button" value="No" onclick="history.back()">
</form>
</body>
</html>
