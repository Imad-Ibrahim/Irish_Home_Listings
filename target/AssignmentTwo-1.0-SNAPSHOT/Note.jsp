<%--
  Created by IntelliJ IDEA.
  User: K00252722
  Date: 10/11/2022
  Time: 23:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="row marginTop">
        <div class="col-md-8 mx-auto">
            <div class="card border-primary text-white bg-primary mb-3 maxWidth">
                <div class="card-header">Add Comment</div>
                <div class="card-body">
                    <form action="AddNoteServlet" method="get">
                        <fieldset>
                            <div class="form-group">
                                <input type='hidden' name='propertyID' id='propertyID' value="${propertyID}" />
                                <label for="note" class="form-label mt-2">Note: </label>
                                <textarea class="form-control" id="note" name="note" rows="10">${propertynote.note}</textarea>
                            </div>
                            <br>
                            <input type="submit" id="addNoteBtn" value="Add" class="btn btn-success">
                            <input class="btn btn-secondary" type="button" value="Cancel" onclick="history.back()">
                            <a href="DeleteNoteServlet?propertyNoteID=${propertynote.id}" class="btn btn-danger">Delete</a>
                        </fieldset>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>

<script>
    document.getElementById('addNoteBtn').disabled = true;
    document.getElementById('note').addEventListener('keyup', e => {
        if (e.target.value == "")
            document.getElementById('addNoteBtn').disabled = true;
        else
            document.getElementById('addNoteBtn').disabled = false;
    });
</script>
