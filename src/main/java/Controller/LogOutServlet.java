package Controller;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String page, message = "";
        try {
            request.getSession().removeAttribute("loggedInUser");
            page = "index.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong, Unable to log out";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
