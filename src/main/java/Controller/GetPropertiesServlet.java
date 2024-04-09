package Controller;


import Database.PropertyDB;
import Model.Helper;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.*;

@WebServlet(name = "GetPropertiesServlet", value = "/GetPropertiesServlet")
public class GetPropertiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        ArrayList<Property> properties = Helper.GetUnarchivedProperties();
        ArrayList<Property> recentProperties = Helper.GetUnarchivedProperties();
        Comparator<Property> ascendingByDate = Comparator.comparing(Property::getDateAdded);
        recentProperties.sort(ascendingByDate);
        Collections.reverse(recentProperties);

        try{
            Random rand = new Random();
            ArrayList<Property> cookieProperties = getCookie(request, properties);
            ArrayList<Property> recommendationProperties = new ArrayList<>();
            for (int i = 0; i < 6; i++) {
                int randomIndex = rand.nextInt(cookieProperties.size());
                recommendationProperties.add(cookieProperties.get(randomIndex));
                cookieProperties.remove(randomIndex);
            }
            request.setAttribute("recommendationProperties", recommendationProperties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        request.getSession().removeAttribute("propertyList");
        request.setAttribute("properties", properties);
        request.setAttribute("recentProperties", recentProperties);
        request.setAttribute("message", Helper.message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Helper.page);
        dispatcher.forward(request, response);

    }
    public static ArrayList<Property> getCookie(HttpServletRequest request, List<Property> properties) {
        ArrayList<Property> propertyList = new ArrayList<>();
        for(Property p : properties){
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(p.getCity()) && p.getPrice() <= Double.parseDouble(cookie.getValue()) && p.getArchived().equals(0))
                        propertyList.add(p);
                }
            }
        }
        return propertyList;
    }
}
