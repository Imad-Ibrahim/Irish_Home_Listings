package Controller;

import Model.Agent;
import Model.Helper;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "GetAgentPropertiesServlet", value = "/GetAgentPropertiesServlet")
public class GetAgentPropertiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        Agent agent = (Agent)request.getSession().getAttribute("loggedInUser");
        ArrayList<Property> agentProperties = Helper.GetAgentProperties(agent.getAgentId());
        request.setAttribute("properties", agentProperties);
        request.setAttribute("message", Helper.message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Helper.page);
        dispatcher.forward(request, response);
    }
}
