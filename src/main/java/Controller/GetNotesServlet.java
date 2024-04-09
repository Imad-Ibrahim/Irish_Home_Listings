package Controller;

import Database.PropertyDB;
import Database.PropertynotesDB;
import Model.Helper;
import Model.Property;
import Model.Propertynotes;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GetNotesServlet", value = "/GetNotesServlet")
public class GetNotesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String propertyID = request.getParameter("propertyID");
        Propertynotes propertynote = new Propertynotes();
        String page, message = "Something went wrong";
        Optional<Property> property = Optional.empty();
        try{
            PropertyDB propertyDB = new PropertyDB();
            PropertynotesDB propertynotesDB = new PropertynotesDB();
            User user = (User)request.getSession().getAttribute("loggedInUser");
            property = propertyDB.getByID(Integer.parseInt(propertyID));
            List<Propertynotes> propertyNoteList = propertynotesDB.getAll();
            if (propertyNoteList != null)
                propertynote = Helper.getPropertyNote(propertyNoteList, Integer.parseInt(propertyID), user);
            else
                propertynote = new Propertynotes();
            page = "Note.jsp";
            request.setAttribute("propertyID", property.get().getId());
            request.setAttribute("propertynote", propertynote);
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
