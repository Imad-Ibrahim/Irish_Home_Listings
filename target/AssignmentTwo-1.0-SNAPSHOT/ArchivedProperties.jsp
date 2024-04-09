<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 05/11/2022
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="row marginTop">
        <c:if test="${fn:length(properties) <= 0}">
            <br><h1>There are no archived properties</h1>
        </c:if>
        <c:if test="${fn:length(properties) > 0}">
            <br><h1>List of all archived properties (${fn:length(properties)})</h1>
            <c:forEach items="${properties}" var="property">
                <div class="col-md-4 mx-auto">
                    <br><br>
                    <div class="card border-primary mb-3">
                        <h3 class="card-header"> <fmt:formatNumber value="${property.price}" type="currency" currencySymbol="€"/></h3>
                        <img src="assets/images/properties/thumbs/${property.photo}">
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item">Street: <span class="bold underline">${property.street}</span></li>
                            <li class="list-group-item">City: <span class="bold underline">${property.city}</span></li>
                            <li class="list-group-item">Square Feet: <span class="bold underline">${property.squarefeet}</span></li>
                        </ul>
                        <div class="card-body">
                            <c:if test="${loggedInUser == null}">
                                <a href="SignIn.jsp">Add To Favourites</a>
                            </c:if>
                            <c:if test="${loggedInUser != null}">
                                <a href="UnarchivePropertiesServlet?propertyID=${property.id}">Unarchive</a>
                                <a href="Fill_InPropertyDetailsFormServlet?propertyID=${property.id}" class="moreDetails">Edit</a>
                            </c:if>
                            <a href="MoreDetailsServlet?propertyID=${property.id}" class="moreDetails">More Details</a>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
    </div>
</div>
</body>
</html>
