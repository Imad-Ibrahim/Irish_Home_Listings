<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 23/11/2022
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="row marginTop">
        <c:if test="${fn:length(vendorsList) <= 0}">
            <br><h1>There are no vendors</h1>
        </c:if>
        <c:if test="${fn:length(vendorsList) > 0}">
            <br><h1>List of all vendors (${fn:length(vendorsList)})</h1>
            <table class="table marginTop">
                <tbody>
                <tr class="table-primary">
                    <td>ID</td>
                    <td>First Name</td>
                    <td>Last Name</td>
                    <td>Street</td>
                    <td>City</td>
                    <td>Phone</td>
                    <td>Email</td>
                    <td>Action</td>
                </tr>
                <c:forEach items="${vendorsList}" var="vendor">
                    <tr class="table-active">
                        <td>${vendor.vendorId}</td>
                        <td>${vendor.firstName}</td>
                        <td>${vendor.lastName}</td>
                        <td>${vendor.street}</td>
                        <td>${vendor.city}</td>
                        <td>${vendor.phone}</td>
                        <td>${vendor.email}</td>
                        <td><a href="GetVendorDetailsServlet?vendorID=${vendor.vendorId}" class="btn btn-success">Edit</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:if>
    </div>
</div>
</body>
</html>
