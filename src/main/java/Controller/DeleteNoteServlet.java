package Controller;

import Database.PropertynotesDB;
import Model.Propertynotes;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "DeleteNoteServlet", value = "/DeleteNoteServlet")
public class DeleteNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String propertyNoteID = request.getParameter("propertyNoteID");
        String page, message = "Something went wrong";
        try{
            PropertynotesDB propertynotesDB = new PropertynotesDB();
            Optional<Propertynotes> propertynotes = propertynotesDB.getByID(Integer.parseInt(propertyNoteID));
            propertynotesDB.delete(propertynotes.get());
            page = "GetPropertiesServlet";
        }  catch (Exception e) {
            message = "Something went wrong";
            page = "Error.jsp";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
