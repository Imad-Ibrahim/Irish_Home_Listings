<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 02/11/2022
  Time: 17:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="row marginTop">
        <div class="col-md-8 mx-auto">
            <div class="card border-primary text-white bg-primary mb-3 maxWidth">
                <div class="card-header">Sign In Form</div>
                <div class="card-body">
                    <form action="SignInServlet" method="get">
                        <fieldset>
                            <c:if test="${message != null}">
                                <div class="form-group">
                                    <p><span class="bold">Your username is <mark>${message}</mark></span></p>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="userName" class="form-label mt-2">User Name</label>
                                <input type="text" class="form-control" id="userName" name="userName" placeholder="Enter user name" required/>
                            </div>
                            <div class="form-group">
                                <label for="password" class="form-label mt-2">Password</label>
                                <input type="password" class="form-control" id="password" name="password" placeholder="Enter password" required/>
                            </div>
                            <br>
                            <input type="submit" value="Sign in" class="btn btn-success">
                            <input type="reset" value="Clear" class="btn btn-danger">
                                <div class="form-group">
                                    <label class="form-label mt-2">Don't have an account.</label>
                                    <a href="SignUp.jsp">Sign up</a>
                                </div>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
