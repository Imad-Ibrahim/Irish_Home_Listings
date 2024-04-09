package Controller;

import Database.PropertynotesDB;
import Model.Helper;
import Model.Propertynotes;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "AddNoteServlet", value = "/AddNoteServlet")
public class AddNoteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String note = request.getParameter("note");
        String propertyID = request.getParameter("propertyID");
        String page, message = "Something went wrong";
        try{
            PropertynotesDB propertynotesDB = new PropertynotesDB();
            User user = (User)request.getSession().getAttribute("loggedInUser");
            List<Propertynotes> propertyNoteList = propertynotesDB.getAll();
            if (propertyNoteList != null){
                Propertynotes propertynotes = Helper.getPropertyNote(propertyNoteList, Integer.parseInt(propertyID), user);

                if (propertynotes != null){
                    propertynotes.setNote(note);
                    propertynotesDB.update(propertynotes);
                }
                else {
                    int newID = propertyNoteList.subList(propertyNoteList.size() - 1, propertyNoteList.size()).get(0).getId() + 1;
                    propertynotesDB.insert(new Propertynotes(newID, Integer.parseInt(propertyID), user.getId(), note));
                }
            }
            else
                propertynotesDB.insert(new Propertynotes(1, Integer.parseInt(propertyID), user.getId(), note));
            page = "GetPropertiesServlet";
        } catch (Exception e) {
            message = "Something went wrong";
            page = "Error.jsp";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
