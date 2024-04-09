<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="row marginTop">
        <c:if test="${loggedInUser == null}">
            <br><h1>Welcome to Irish Home Listings!</h1>
        </c:if>
        <c:if test="${loggedInUser != null}">
            <br><h2>Welcome ${loggedInUser.firstName} ${loggedInUser.lastName} to Irish Home Listings!</h2>
        </c:if>
    </div>
</div>
</body>
</html>