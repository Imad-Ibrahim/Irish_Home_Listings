<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 02/11/2022
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="row marginTop">
        <div class="col-md-8 mx-auto">
            <div class="card border-primary text-white bg-primary mb-3 maxWidth">
                <div class="card-header">Sign Up Form</div>
                <div class="card-body">
                    <form action="SignUpServlet" method="get">
                        <fieldset>
                            <div class="form-group">
                                <label for="firstName" class="form-label mt-2">First Name</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" placeholder="Enter first name" required/>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="form-label mt-2">Last Name</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" placeholder="Enter last name" required/>
                            </div>
                            <c:if test="${loggedInUser != null && loggedInUser.userType == 'backOffice'}">
                                <div class="form-group">
                                    <label for="street" class="form-label mt-2">Street</label>
                                    <input type="text" class="form-control" id="street" name="street" placeholder="Enter street" required/>
                                </div>
                                <div class="form-group">
                                    <label for="city" class="form-label mt-2">City</label>
                                    <input type="text" class="form-control" id="city" name="city" placeholder="Enter city" required/>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="phone" class="form-label mt-2">Phone</label>
                                <input type="text" class="form-control" id="phone" name="phone" placeholder="Enter phone number" required/>
                            </div>
                            <div class="form-group">
                                <label for="email" class="form-label mt-2">Email</label>
                                <input type="text" class="form-control" id="email" name="email" placeholder="Enter email address" required/>
                            </div>
                            <c:if test="${loggedInUser == null}">
                                <div class="form-group">
                                    <label for="password" class="form-label mt-2">Password</label>
                                    <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required/>
                                </div>
                                <div class="form-group">
                                    <label for="confirmPassword" class="form-label mt-2">Re-enter Password</label>
                                    <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="Enter password again" required/>
                                </div>
                            </c:if>
                            <br>
                            <input type="submit" value="Sign up" class="btn btn-success">
                            <input type="reset" value="Clear" class="btn btn-danger">
                            <c:if test="${loggedInUser == null}">
                                <div class="form-group">
                                    <label class="form-label mt-2">Already have an account.</label>
                                    <a href="SignIn.jsp">Sign-in</a>
                                </div>
                            </c:if>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
