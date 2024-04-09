<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 02/11/2022
  Time: 22:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
    <link href="assets/css/stylesheet.css" rel="stylesheet" type="text/css" media="all"/>
</head>
<body>
    <br>
    <h2>${message}</h2><br>
    <form>
        <input class="btn btn-success" type="button" value="Go back!" onclick="history.back()">
    </form>
</body>
</html>
