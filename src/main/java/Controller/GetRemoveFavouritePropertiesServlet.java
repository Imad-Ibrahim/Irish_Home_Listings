package Controller;

import Database.PropertyDB;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetRemoveFavouritePropertiesServlet", value = "/GetRemoveFavouritePropertiesServlet")
public class GetRemoveFavouritePropertiesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        RequestDispatcher dispatcher;
        String removePropertyID = request.getParameter("removePropertyID");
        if (removePropertyID != null){
            removeCookie(request, response, removePropertyID);
            PrintWriter out = response.getWriter();
            out.println("<script type=\"text/javascript\">");
            out.println("window.location = 'GetRemoveFavouritePropertiesServlet';");
            out.println("</script>");
        }
        else {
            try{
                PropertyDB propertyDB = new PropertyDB();
                List<Property> propertyList = propertyDB.getAll();
                List<Property> properties = getData(request, propertyList, propertyDB);
                request.getSession().setAttribute("propertyList", propertyList);
                request.setAttribute("properties", properties);
                dispatcher = request.getRequestDispatcher("PropertiesAddedToFavourite.jsp");
                dispatcher.forward(request, response);
            } catch (Exception e) {
                request.setAttribute("message", "Something went wrong");
                dispatcher = request.getRequestDispatcher("Error.jsp");
                dispatcher.forward(request, response);
            }
        }
    }
    public static ArrayList<Property> getData(HttpServletRequest request, List<Property> propertyList, PropertyDB propertyDB) {
        try {
            ArrayList<Property> properties = new ArrayList<>();
            ArrayList<String> ids = getCookie(request, propertyList);

            for(String id : ids){
                properties.add(propertyDB.getByID(Integer.parseInt(id)).get());
            }
            return properties;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public static ArrayList<String> getCookie(HttpServletRequest request, List<Property> properties) {
        ArrayList<String> ids = new ArrayList<>();
        for(Property p : properties){
            if (request.getCookies() != null) {
                for (Cookie cookie : request.getCookies()) {
                    if (cookie.getName().equals(String.valueOf(p.getId())))
                        ids.add(cookie.getName());
                }
            }
        }
        return ids;
    }
    public static void removeCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);

                }
            }
        }
    }
}
