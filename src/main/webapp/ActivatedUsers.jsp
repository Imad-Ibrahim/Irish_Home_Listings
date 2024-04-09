<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 03/11/2022
  Time: 15:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="row marginTop">
        <c:if test="${fn:length(activatedUsers) <= 0}">
            <br><h1>There are no users to suspend</h1>
        </c:if>
        <c:if test="${fn:length(activatedUsers) > 0}">
            <br><h1>List of all users that are activated (${fn:length(activatedUsers)})</h1>
            <table class="table marginTop">
                <tbody>
                <tr class="table-primary">
                    <td>ID</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Phone</td>
                    <td>Email</td>
                    <td>Action</td>
                </tr>
                <c:forEach items="${activatedUsers}" var="user">
                    <tr class="table-active">
                        <td>${user.id}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td>${user.phone}</td>
                        <td>${user.email}</td>
                        <td><a href="SuspendUserServlet?userID=${user.id}" class="btn btn-danger">Suspend</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
