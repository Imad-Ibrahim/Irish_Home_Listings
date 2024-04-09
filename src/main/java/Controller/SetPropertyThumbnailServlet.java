package Controller;

import Database.PropertyDB;
import Model.Agent;
import Model.Helper;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "SetPropertyThumbnailServlet", value = "/SetPropertyThumbnailServlet")
public class SetPropertyThumbnailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getSession().getAttribute("loggedInUser").getClass().equals(Agent.class)) {

            Optional<Property> property = new PropertyDB().getByID(Integer.parseInt(request.getParameter("propertyID")));
            String imageName = request.getParameter("imageName");

            try {
                Helper.setThumbnail(imageName, property.get(), getServletContext());
                response.sendRedirect(request.getContextPath() + "/Fill_InPropertyDetailsFormServlet?propertyID=" + property.get().getId());
            } catch (Exception e) {
                request.setAttribute("message", "Something went wrong");
                request.getRequestDispatcher("Error.jsp");
            }

        } else{
            request.setAttribute("message", "Something went wrong");
            request.getRequestDispatcher("Error.jsp");
        }
    }

}
