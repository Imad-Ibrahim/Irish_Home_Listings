package Controller;

import Database.PropertyDB;
import Model.Agent;
import Model.Property;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "FilterPropertiesServlet", value = "/FilterPropertiesServlet")
public class FilterPropertiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String city = request.getParameter("city").replaceAll("\\s", "");
        String priceFrom = request.getParameter("priceFrom");
        String priceTo = request.getParameter("priceTo");
        String numOfBedRooms = request.getParameter("numberOfBedrooms");
        String numOfBathRooms = request.getParameter("numberOfBathrooms");
        String page, message = "Something went wrong";
        PropertyDB propertyDB = new PropertyDB();
        List<Property> propertyList = propertyDB.getAll();
        ArrayList<Property> properties = new ArrayList<>();
        if (!city.equals("") && !priceFrom.equals("") && !priceTo.equals("") && !numOfBedRooms.equals("") && !numOfBathRooms.equals("")){
            properties = Filter(request, propertyList, city, Double.parseDouble(priceFrom), Double.parseDouble(priceTo), Integer.parseInt(numOfBedRooms), Integer.parseInt(numOfBathRooms));
            page = "Properties.jsp";
            try{
                if (request.getSession().getAttribute("loggedInUser") == null || request.getSession().getAttribute("loggedInUser").getClass().equals(User.class)){
                    Cookie c = new Cookie(city, priceTo);
                    c.setMaxAge(365 * 24 * 60 * 60);
                    response.addCookie(c);
                }
            } catch (Exception e) {
                page = "Error.jsp";
                message = "Something went wrong";
            }
        }
        else {
            page = "Error.jsp";
            message = "Something went wrong, please fill in all fields";
        }
        request.setAttribute("message", message);
        request.setAttribute("properties", properties);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
    public ArrayList<Property> Filter(HttpServletRequest request, List<Property> propertyList, String city, Double priceFrom, Double priceTo, Integer numOfBedRooms, Integer numOfBathRooms){
        ArrayList<Property> properties = new ArrayList<>();
        if (request.getSession().getAttribute("loggedInUser") != null && request.getSession().getAttribute("loggedInUser").getClass().equals(Agent.class)){
            Agent agent = (Agent) request.getSession().getAttribute("loggedInUser");
            for(Property property : propertyList){
                if (property.getCity().equals(city) &&
                        (property.getPrice() >= priceFrom && property.getPrice() <= priceTo) &&
                        property.getBedrooms().equals(numOfBedRooms) &&
                        property.getBathrooms().equals(numOfBathRooms) &&
                        property.getArchived().equals(0) &&
                        property.getAgentId().equals(agent.getAgentId())){
                    properties.add(property);
                }
            }
        }
        else {
            for(Property property : propertyList){
                if (property.getCity().equals(city) && (property.getPrice() >= priceFrom && property.getPrice() <= priceTo) && property.getBedrooms().equals(numOfBedRooms)
                        && property.getBathrooms().equals(numOfBathRooms) && property.getArchived().equals(0)){
                    properties.add(property);
                }
            }
        }
        return properties;
    }
}
