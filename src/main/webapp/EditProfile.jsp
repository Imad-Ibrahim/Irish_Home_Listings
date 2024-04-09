<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 05/11/2022
  Time: 14:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp"/>
<div class="container">
    <div class="row marginTop">
        <div class="col-md-8 mx-auto">
            <c:if test="${loggedInUser != null}">
                <div class="card border-primary text-white bg-primary mb-3 maxWidth">
                    <div class="card-header">Edit Details Form</div>
                    <div class="card-body">
                        <form action="EditProfileServlet" method="get">
                            <fieldset>
                                <div class="form-group">
                                    <label for="firstName" class="form-label mt-2">First Name</label>
                                    <input type="text" class="form-control" id="firstName" name="firstName" value="${loggedInUser.firstName}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="lastName" class="form-label mt-2">Last Name</label>
                                    <input type="text" class="form-control" id="lastName" name="lastName" value="${loggedInUser.lastName}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="phone" class="form-label mt-2">Phone</label>
                                    <input type="text" class="form-control" id="phone" name="phone" value="${loggedInUser.phone}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="form-label mt-2">Email</label>
                                    <input type="text" class="form-control" id="email" name="email" value="${loggedInUser.email}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="userType" class="form-label mt-2">User Type</label>
                                    <input type="text" class="form-control" id="userType" name="userType" value="${loggedInUser.userType}" readonly/>
                                </div>
                                <div class="form-group">
                                    <label for="userName" class="form-label mt-2">Username</label>
                                    <input type="text" class="form-control" id="userName" name="userName" value="${loggedInUser.userName}" readonly/>
                                </div>
                                <div class="form-group">
                                    <label for="password" class="form-label mt-2">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" value="${loggedInUser.password}" required/>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword" class="form-label mt-2">Re-enter Password</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Enter password again" required/>
                                </div>
                                <br>
                                <input type="submit" value="Save" class="btn btn-success">
                                <input class="btn btn-danger" type="button" value="Cancel" onclick="history.back()">
                            </fieldset>
                        </form>
                    </div>
                </div>
            </c:if>
        </div>
    </div>
</div>
</body>
</html>
