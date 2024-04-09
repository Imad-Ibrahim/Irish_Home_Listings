package Controller;

import Database.*;
import Model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "MoreDetailsServlet", value = "/MoreDetailsServlet")
public class MoreDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String propertyID = request.getParameter("propertyID");
        String page, message = "Something went wrong";

        try {
            Propertynotes propertynotes = new Propertynotes();
            Optional<Property> property = new PropertyDB().getByID(Integer.parseInt(propertyID));
            Optional<Style> style = new StyleDB().getByID(property.get().getStyleId());
            Optional<Propertytype> propertyType = new PropertytypeDB().getByID(property.get().getTypeId());
            Optional<Garagetype> garageType = new GaragetypeDB().getByID(property.get().getGarageId());
            Optional<Agent> agent = new AgentDB().getByID(property.get().getAgentId());
            Optional<Vendor> vendor = new VendorDB().getByID(property.get().getVendorId());

            if (request.getSession().getAttribute("loggedInUser") != null && request.getSession().getAttribute("loggedInUser").getClass().equals(User.class)){
                    User user = (User)request.getSession().getAttribute("loggedInUser");
                    List<Propertynotes> propertyNoteList = new PropertynotesDB().getAll();
                    if (propertyNoteList != null)
                        propertynotes = Helper.getPropertyNote(propertyNoteList, property.get().getId(), user);
            }
            List<String> imagesName = Helper.getFileNames(getServletContext(), property.get());

            request.setAttribute("property", property.get());
            request.setAttribute("style", style.get());
            request.setAttribute("propertyType", propertyType.get());
            request.setAttribute("garageType", garageType.get());
            request.setAttribute("agent", agent.get());
            request.setAttribute("vendor", vendor.get());
            request.setAttribute("propertynote", propertynotes);
            request.setAttribute("imagesName", imagesName);

            page = "MoreDetails.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
