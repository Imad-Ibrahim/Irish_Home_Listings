package Controller;

import Model.Helper;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetAllActivatedUsersServlet", value = "/GetAllActivatedUsersServlet")
public class GetAllActivatedUsersServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<User> activatedUsers = Helper.GetActivatedUsers();
        request.setAttribute("activatedUsers", activatedUsers);
        request.setAttribute("message", Helper.message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Helper.page);
        dispatcher.forward(request, response);
    }
}