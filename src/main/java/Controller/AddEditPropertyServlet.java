package Controller;

import Database.PropertyDB;
import Model.Agent;
import Model.Property;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@WebServlet(name = "AddEditPropertyServlet", value = "/AddEditPropertyServlet")
@MultipartConfig
public class AddEditPropertyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String page, message = "Something went wrong";
        try {
            String street = request.getParameter("street");
            String city = request.getParameter("city").replaceAll("\\s", "");
            String listingNum = request.getParameter("listingNum");
            String styleId = request.getParameter("styleId");
            String typeId = request.getParameter("typeId");
            String bedrooms = request.getParameter("bedrooms");
            String bathrooms = request.getParameter("bathrooms");
            String squarefeet = request.getParameter("squarefeet");
            String berRating = request.getParameter("berRating");
            String description = request.getParameter("description");
            String lotsize = request.getParameter("lotsize");
            String garagesize = request.getParameter("garagesize");
            String garageId = request.getParameter("garageId");
            String vendorID = request.getParameter("vendorID");
            String price = request.getParameter("price");
            //List<Part> files = request.getParts().stream().filter(part -> part.getName().equals("photo")).collect(Collectors.toList());
            String btnValue = request.getParameter("btnValue");
            String propertyID = request.getParameter("propertyID");
            PropertyDB propertyDB = new PropertyDB();
            Agent agent = new Agent();
            if (request.getSession().getAttribute("loggedInUser") != null && request.getSession().getAttribute("loggedInUser").getClass().equals(Agent.class))
                agent = (Agent)request.getSession().getAttribute("loggedInUser");
            Date sqlDate = Date.valueOf(LocalDate.now());

            if (btnValue.equals("Edit") && propertyID != null){
                Optional<Property> property = propertyDB.getByID(Integer.parseInt(propertyID));
                property.get().setStreet(street);
                property.get().setCity(city);
                property.get().setListingNum(Integer.parseInt(listingNum));
                property.get().setStyleId(Integer.parseInt(styleId));
                property.get().setTypeId(Integer.parseInt(typeId));
                property.get().setBedrooms(Integer.parseInt(bedrooms));
                property.get().setBathrooms(Integer.parseInt(bathrooms));
                property.get().setSquarefeet(Integer.parseInt(squarefeet));
                property.get().setBerRating(berRating);
                property.get().setDescription(description);
                property.get().setLotsize(lotsize);
                property.get().setGaragesize(Integer.parseInt(garagesize));
                property.get().setGarageId(Integer.parseInt(garageId));
                property.get().setPrice(Double.parseDouble(price));
                property.get().setVendorId(Integer.parseInt(vendorID));
                propertyDB.update(property.get());
                page = "GetAgentPropertiesServlet";
            }
            else if (btnValue.equals("Add")){
                List<Property> properties = propertyDB.getAll();
                Comparator<Property> ascendingByDate = Comparator.comparing(Property::getId);
                properties.sort(ascendingByDate);
                Collections.reverse(properties);
                //String photoName = files.get(0).getName().replaceAll(files.get(0).getName(), listingNum+".jpg");
                Property newProperty = new Property(properties.get(0).getId()+1, street, city, Integer.parseInt(listingNum),
                        Integer.parseInt(styleId), Integer.parseInt(typeId), Integer.parseInt(bedrooms),
                        Integer.parseInt(bathrooms), Integer.parseInt(squarefeet), berRating, description, lotsize,
                        Integer.parseInt(garagesize), Integer.parseInt(garageId), agent.getAgentId(), "photoName", Double.parseDouble(price),
                        sqlDate,  0, Integer.parseInt(vendorID));

                propertyDB.insert(newProperty);
                page = "GetAgentPropertiesServlet";
            }
            else {
                page = "Error.jsp";
                message = "Something went wrong";
            }
        } catch (NumberFormatException e) {
            page = "Error.jsp";
            message = "Something went wrong";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}