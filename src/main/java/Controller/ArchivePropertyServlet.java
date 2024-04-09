package Controller;

import Database.PropertyDB;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "ArchivePropertyServlet", value = "/ArchivePropertyServlet")
public class ArchivePropertyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String propertyID = request.getParameter("propertyID");
        String page, message = "Something went wrong";
        try {
            PropertyDB propertyDB = new PropertyDB();
            Optional<Property> property = propertyDB.getByID(Integer.parseInt(propertyID));
            property.get().setArchived(1);
            propertyDB.update(property.get());
            page = "GetAgentPropertiesServlet";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
