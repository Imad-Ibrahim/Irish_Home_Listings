package Controller;

import Model.Helper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "SuspendUserServlet", value = "/SuspendUserServlet")
public class SuspendUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String userID = request.getParameter("userID");
        Helper.SuspendUser(userID);
        request.setAttribute("message", Helper.message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Helper.page);
        dispatcher.forward(request, response);
    }
}