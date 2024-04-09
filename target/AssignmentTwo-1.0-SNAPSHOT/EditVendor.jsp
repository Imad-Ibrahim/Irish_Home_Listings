<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 23/11/2022
  Time: 14:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="row marginTop">
        <div class="col-md-8 mx-auto">
            <div class="card border-primary text-white bg-primary mb-3 maxWidth">
                <div class="card-header">Edit Vendor Form</div>
                <div class="card-body">
                    <form action="EditVendorServlet" method="get">
                        <fieldset>
                            <div class="form-group">
                                <label for="firstName" class="form-label mt-2">First Name</label>
                                <input type="text" class="form-control" id="firstName" name="firstName" value="${vendor.firstName}" placeholder="Enter first name" required/>
                                <input type="hidden" class="form-control" id="vendorID" name="vendorID" value="${vendor.vendorId}" required/>
                            </div>
                            <div class="form-group">
                                <label for="lastName" class="form-label mt-2">Last Name</label>
                                <input type="text" class="form-control" id="lastName" name="lastName" value="${vendor.lastName}"placeholder="Enter last name" required/>
                            </div>
                            <div class="form-group">
                                <label for="street" class="form-label mt-2">Street</label>
                                <input type="text" class="form-control" id="street" name="street" value="${vendor.street}" placeholder="Enter street" required/>
                            </div>
                            <div class="form-group">
                                <label for="city" class="form-label mt-2">City</label>
                                <input type="text" class="form-control" id="city" name="city" value="${vendor.city}" placeholder="Enter city" required/>
                            </div>
                            <div class="form-group">
                                <label for="phone" class="form-label mt-2">Phone</label>
                                <input type="text" class="form-control" id="phone" name="phone" value="${vendor.phone}" placeholder="Enter phone number" required/>
                            </div>
                            <div class="form-group">
                                <label for="email" class="form-label mt-2">Email</label>
                                <input type="text" class="form-control" id="email" name="email" value="${vendor.email}" placeholder="Enter email address" required/>
                            </div>
                            <br>
                            <input type="submit" value="Save" class="btn btn-success">
                            <input class="btn btn-danger" type="button" value="Cancel" onclick="history.back()">
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
