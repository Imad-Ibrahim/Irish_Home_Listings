<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 05/11/2022
  Time: 14:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="header.jsp" />

<div class="container">
    <div class="row marginTop">
        <c:if test="${property == null}">
            <br><h1>Something went Wrong</h1>
        </c:if>
        <c:if test="${property != null && style != null && propertyType != null && garageType != null && agent != null}">
            <div class="col-md-10 mx-auto">
                <div class="card border-primary mb-3 slideshow-container">
                    <c:forEach items="${imagesName}" var="imageName">
                        <img class="imagesGallery animation" src="assets/images/properties/large/${property.listingNum}/${imageName}">
                    </c:forEach>
                    <a class="prev" onclick="plusSlides(-1)">❮</a>
                    <a class="next" onclick="plusSlides(1)">❯</a>
                    <ul class="list-group list-group-flush">
                        <h3 class="list-group-item"><fmt:formatNumber value="${property.price}" type="currency" currencySymbol="€"/></h3>
                        <li class="list-group-item"><span class="bold">${property.street}, ${property.city}</span></li>
                        <li class="list-group-item">
                            ${property.bedrooms} Bed,
                            ${property.bathrooms} Bath,
                            ${property.squarefeet} Square Feet,
                            ${style.PStyle}
                        </li>
                        <li class="list-group-item"><span class="bold propertyDes">Description</span><br> ${property.description}</li>
                        <c:if test="${loggedInUser != null && loggedInUser.userType == 'frontOffice' && propertyList == null}">
                            <li class="list-group-item"><span class="bold propertyDes">Notes</span><br> ${propertynote.note}</li>
                        </c:if>
                    </ul>
                    <div class="card-body">
                        <c:if test="${loggedInUser == null}">
                            <a href="SignIn.jsp">Add To Favourites</a>
                            <a href="SignIn.jsp" class="moreDetails">Add Note</a>
                        </c:if>
                        <c:if test="${loggedInUser != null && loggedInUser.userType == 'frontOffice' && propertyList == null}">
                            <a href="AddPropertyToFavouriteServlet?propertyID=${property.id}">Add To Favourites</a>
                            <a href="GetNotesServlet?propertyID=${property.id}" class="moreDetails">Add Note</a>
                        </c:if>
                        <c:if test="${loggedInUser != null && loggedInUser.userType == 'frontOffice' && propertyList != null}">
                            <c:forEach items="${propertyList}" var="p">
                                <c:if test="${p.id == property.id}">
                                    <a href="GetRemoveFavouritePropertiesServlet?removePropertyID=${property.id}">Remove From Favourites</a>
                                    <a href="GetNotesServlet?propertyID=${property.id}" class="moreDetails">Add Note</a>
                                </c:if>
                            </c:forEach>
                        </c:if>
                        <c:if test="${loggedInUser != null && loggedInUser.userType == 'backOffice' && property.archived == 0}">
                            <a href="ArchivePropertyServlet?propertyID=${property.id}">Archive</a>
                            <a href="Fill_InPropertyDetailsFormServlet?propertyID=${property.id}" class="moreDetails">Edit</a>
                        </c:if>
                        <c:if test="${loggedInUser != null && loggedInUser.userType == 'backOffice' && property.archived == 1}">
                            <a href="UnarchivePropertiesServlet?propertyID=${property.id}">Unarchive</a>
                            <a href="Fill_InPropertyDetailsFormServlet?propertyID=${property.id}" class="moreDetails">Edit</a>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="col-md-4 mx-auto">
                <div class="card border-primary mb-3">
                    <div class="card-body">
                        <h2 class="propertyDetailsHeader">Property Details</h2>
                        <ul>
                            <li class="propertyDetails"><span class="bold">Bed Rooms: </span>${property.bedrooms}</li>
                            <li class="propertyDetails"><span class="bold">Bath Room: </span>${property.bathrooms}</li>
                            <li class="propertyDetails"><span class="bold">Square Feet: </span>${property.squarefeet}</li>
                            <li class="propertyDetails"><span class="bold">Property Style: </span>${style.PStyle}</li>
                            <li class="propertyDetails"><span class="bold">Property Type: </span>${propertyType.PType}</li>
                            <li class="propertyDetails"><span class="bold">BER Rating: </span><img src="assets/images/BER/${property.berRating}.png"></li>
                            <li class="propertyDetails"><span class="bold">Lot Size: </span> ${property.lotsize}</li>
                            <li class="propertyDetails"><span class="bold">Garage Size: </span>${property.garagesize}</li>
                            <li class="propertyDetails"><span class="bold">Garage Type: </span>${garageType.GType}</li>
                            <li class="propertyDetails"><span class="bold">Date Added: </span>${property.dateAdded}</li>
                        </ul>

                    </div>
                </div>
                <c:if test="${loggedInUser.userType == 'backOffice'}">
                    <div class="card border-primary mb-3">
                        <ul class="list-group list-group-flush">
                            <h3 class="list-group-item">${vendor.firstName} ${vendor.lastName}</h3>
                            <li class="list-group-item"><span class="bold">Address: </span>${vendor.street}, ${vendor.city}</li>
                            <li class="list-group-item"><span class="bold">Phone: </span>${vendor.phone}</li>
                            <li class="list-group-item"><span class="bold">Email: </span><a href="mailto:${vendor.email}">${vendor.email}</a></li>
                        </ul>
                    </div>
                </c:if>
            </div>
            <c:if test="${loggedInUser == null || loggedInUser.userType == 'frontOffice'}">
                <div class="col-md-4 mx-auto">
                    <div class="card border-primary mb-3">
                        <img src="assets/images/agents/${agent.agentId}.jpg">
                        <ul class="list-group list-group-flush">
                            <h3 class="list-group-item">${agent.firstName} ${agent.lastName}</h3>
                            <li class="list-group-item">Phone: <span class="bold">${agent.phone}</span></li>
                            <li class="list-group-item">Fax: <span class="bold">${agent.fax}</span></li>
                            <li class="list-group-item">Email: <span class="bold"><a href="mailto:${agent.email}">${agent.email}</a></span></li>
                        </ul>
                    </div>
                </div>
            </c:if>
            <c:if test="${loggedInUser != null && loggedInUser.userType == 'backOffice'}">
                <div class="col-md-4 mx-auto">
                    <iframe class="google"
                            src="https://maps.google.com/maps?q=${property.street} ${property.city}&t=&z=13&ie=UTF8&iwloc=&output=embed">
                    </iframe>
                </div>
            </c:if>
            <c:if test="${loggedInUser == null || loggedInUser.userType == 'frontOffice'}">
            <div class="col-md-10 mx-auto">
                <iframe class="google"
                        src="https://maps.google.com/maps?q=${property.street} ${property.city}&t=&z=13&ie=UTF8&iwloc=&output=embed">
                </iframe>
            </div>
            </c:if>
        </c:if>
    </div>
</div>

<script>
    let slideIndex = 1;
    showSlides(slideIndex);

    function plusSlides(n) {
        showSlides(slideIndex += n);
    }
    function showSlides(n) {
        let i;
        let slides = document.getElementsByClassName("imagesGallery");
        if (n > slides.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = slides.length
        }
        for (i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        slides[slideIndex-1].style.display = "block";
    }
</script>
</body>
</html>