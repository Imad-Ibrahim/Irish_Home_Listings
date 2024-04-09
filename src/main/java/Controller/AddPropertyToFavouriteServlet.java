package Controller;


import Database.PropertyDB;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "AddPropertyToFavouriteServlet", value = "/AddPropertyToFavouriteServlet")
public class AddPropertyToFavouriteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String propertyID = request.getParameter("propertyID");
        String page, message = "Something went wrong";
        try {
            PropertyDB propertyDB = new PropertyDB();
            Optional<Property> property = propertyDB.getByID(Integer.parseInt(propertyID));
            Cookie c = new Cookie(String.valueOf(property.get().getId()), String.valueOf(property.get().getId()));
            c.setMaxAge(60 * 60);
            response.addCookie(c);
            page = "GetPropertiesServlet";
        } catch (NumberFormatException e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
