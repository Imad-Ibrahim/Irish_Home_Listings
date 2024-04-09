<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 14/11/2022
  Time: 23:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="row marginTop">
        <div class="col-md-8 mx-auto">
            <div class="card border-primary text-white bg-primary mb-3 maxWidth">
                <div class="card-header">${formHeader}</div>
                <div class="card-body">
                    <form action="AddEditPropertyServlet" method="get" enctype="multipart/form-data">
                        <fieldset>
                            <div class="form-group">
                                <label for="street" class="form-label mt-2">Street</label>
                                <input type="text" class="form-control" id="street" name="street" placeholder="Enter street" value="${property.street}"/>
                                <input type="hidden" class="form-control" id="btnValue" name="btnValue" value="${btnValue}"/>
                                <input type="hidden" class="form-control" id="propertyID" name="propertyID" value="${property.id}"/>
                            </div>
                            <div class="form-group">
                                <label for="city" class="form-label mt-2">City</label>
                                <input type="text" class="form-control" id="city" name="city" placeholder="Enter city" value="${property.city}"/>
                            </div>
                            <div class="form-group">
                                <label for="listingNum" class="form-label mt-2">Listing Number</label>
                                <input type="number" class="form-control" id="listingNum" name="listingNum" placeholder="Enter listing number" value="${property.listingNum}"/>
                            </div>
                            <c:if test="${styles != null || PropertyStyle != null}">
                                <div class="form-group">
                                    <label for="styleId" class="form-label mt-2">Style</label>
                                    <select name="styleId" id="styleId" class="form-select">
                                        <c:forEach items="${styles}" var="style">
                                            <c:if test="${PropertyStyle.PStyle == style.PStyle && PropertyStyle.PStyle != null}">
                                                <option selected value="${style.styleId}">${style.PStyle}</option>
                                            </c:if>
                                            <c:if test="${PropertyStyle.PStyle != style.PStyle}">
                                                <option value="${style.styleId}">${style.PStyle}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <c:if test="${propertyTypes != null  || propertyType != null}">
                                <div class="form-group">
                                    <label for="typeId" class="form-label mt-2">Type</label>
                                    <select name="typeId" id="typeId" class="form-select">
                                        <c:forEach items="${propertyTypes}" var="type">
                                            <c:if test="${type.PType == propertyType.PType}">
                                                <option selected value="${type.typeId}">${type.PType}</option>
                                            </c:if>
                                            <c:if test="${type.PType != propertyType.PType}">
                                                <option value="${type.typeId}">${type.PType}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="bedrooms" class="form-label mt-2">Bed Rooms</label>
                                <input type="number" class="form-control" id="bedrooms" name="bedrooms" placeholder="Enter number of bedrooms" value="${property.bedrooms}"/>
                            </div>
                            <div class="form-group">
                                <label for="bathrooms" class="form-label mt-2">Bath Rooms</label>
                                <input type="number" class="form-control" id="bathrooms" name="bathrooms" placeholder="Enter number of bath rooms" value="${property.bathrooms}"/>
                            </div>
                            <div class="form-group">
                                <label for="squarefeet" class="form-label mt-2">Square Feet</label>
                                <input type="number" class="form-control" id="squarefeet" name="squarefeet" placeholder="Enter square feet" value="${property.squarefeet}"/>
                            </div>
                            <c:if test="${BER != null  || property != null}">
                                <div class="form-group">
                                    <label for="berRating" class="form-label mt-2">BER Rating</label>
                                    <select name="berRating" id="berRating" class="form-select">
                                        <c:forEach items="${BER}" var="ber">
                                            <c:if test="${ber == property.berRating}">
                                                <option selected value="${ber}">${ber}</option>
                                            </c:if>
                                            <c:if test="${ber != property.berRating}">
                                                <option value="${ber}">${ber}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="description" class="form-label mt-4">Description</label>
                                <textarea class="form-control" id="description" name="description" rows="3">${property.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="lotsize" class="form-label mt-2">Lot Size</label>
                                <input type="text" class="form-control" id="lotsize" name="lotsize" placeholder="Enter lot size" value="${property.lotsize}"/>
                            </div>
                            <div class="form-group">
                                <label for="garagesize" class="form-label mt-2">Garage Size</label>
                                <input type="number" class="form-control" id="garagesize" name="garagesize" placeholder="Enter garage size" value="${property.garagesize}"/>
                            </div>
                            <c:if test="${garageTypes != null  || garageType != null}">
                                <div class="form-group">
                                    <label for="garageId" class="form-label mt-2">Garage Type</label>
                                    <select name="garageId" id="garageId" class="form-select">
                                        <c:forEach items="${garageTypes}" var="type">
                                            <c:if test="${type.GType == garageType.GType}">
                                                <option selected value="${type.garageId}">${type.GType}</option>
                                            </c:if>
                                            <c:if test="${type.GType != garageType.GType}">
                                                <option value="${type.garageId}">${type.GType}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <c:if test="${vendors != null  || vendor != null}">
                                <div class="form-group">
                                    <label for="vendorID" class="form-label mt-2">Vendor</label>
                                    <select name="vendorID" id="vendorID" class="form-select">
                                        <c:forEach items="${vendors}" var="v">
                                            <c:if test="${v.vendorId == vendor.vendorId}">
                                                <option selected value="${v.vendorId}">${v.firstName} ${v.lastName}</option>
                                            </c:if>
                                            <c:if test="${v.vendorId != vendor.vendorId}">
                                                <option value="${v.vendorId}">${v.firstName} ${v.lastName}</option>
                                            </c:if>
                                        </c:forEach>
                                    </select>
                                </div>
                            </c:if>
                            <div class="form-group">
                                <label for="price" class="form-label mt-2">Price</label>
                                <input type="number" class="form-control" id="price" name="price" placeholder="Enter price" value="${property.price}"/>
                            </div>
                            <div class="form-group">
                                <label for="photo" class="form-label mt-4">Photos</label>
                                <input class="form-control" type="file" id="photo" name="photo" multiple>
                            </div>
                            <br>
                            <input type="submit" value="${btnValue}" class="btn btn-success">
                            <c:if test="${btnValue == 'Add'}">
                                <input type="reset" value="Clear" class="btn btn-danger">
                            </c:if>
                            <c:if test="${btnValue == 'Edit'}">
                                <input class="btn btn-danger" type="button" value="Cancel" onclick="history.back()">
                            </c:if>
                        </fieldset>
                    </form>

                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">Image</th>
                            <th scope="col">&nbsp;</th>
                            <th scope="col">&nbsp;</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${imagesName}" var="imageName">
                            <tr>
                                <th scope="row"><img style="width: 75px;height: 50px" src="assets/images/properties/large/${property.listingNum}/${imageName}"></th>
                                <td><a href="?propertyID=${property.id}&imageName=${imageName}" class="btn btn-danger">Delete</a></td>
                                <td><a href="?propertyID=${property.id}&imageName=${imageName}" class="btn btn-success">Set as Thumbnail</a></td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>