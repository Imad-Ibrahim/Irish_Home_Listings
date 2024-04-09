package Controller;

import Database.AgentDB;
import Database.UserDB;
import Model.Agent;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignInServlet", value = "/SignInServlet")
public class SignInServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String page = "Error.jsp", message = "Incorrect user name or password OR you have been suspended, please contact one of our agents";
        try {
            List<User> users = new UserDB().getAll();
            List<Agent> agents = new AgentDB().getAll();

            if (users != null){
                for(User user: users){
                    if (user.getUserName().equals(userName) && user.getPassword().equals(password) && user.getActive() == 1){
                        session.setAttribute("loggedInUser", user);
                        page = "index.jsp";
                        break;
                    }
                }
            }
            if (agents != null) {
                for(Agent agent: agents){
                    if (agent.getUsername().equals(userName) && agent.getPassword().equals(password)){
                        session.setAttribute("loggedInUser", agent);
                        page = "index.jsp";
                        break;
                    }
                }
            }
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}