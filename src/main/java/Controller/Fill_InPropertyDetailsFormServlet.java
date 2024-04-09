package Controller;

import Database.*;
import Model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "Fill_InPropertyDetailsFormServlet", value = "/Fill_InPropertyDetailsFormServlet")
public class Fill_InPropertyDetailsFormServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String page, message = "Something went wrong";
        try {
            String propertyID = request.getParameter("propertyID");
            String[] BER = {"A1", "A2", "A3", "B1", "B2", "B3", "C1", "C2", "C3", "D1", "D2", "E1", "E2", "EXEMPT", "F", "G"};
            List<Style> styles = new StyleDB().getAll();
            List<Propertytype> propertyTypes = new PropertytypeDB().getAll();
            List<Garagetype> garageTypes = new GaragetypeDB().getAll();
            List<Vendor> vendors = new VendorDB().getAll();
            request.setAttribute("formHeader", "Add New Property");
            request.setAttribute("btnValue", "Add");
            if (propertyID != null) {
                Optional<Property> property = new PropertyDB().getByID(Integer.parseInt(propertyID));
                Optional<Style> style = new StyleDB().getByID(property.get().getStyleId());
                Optional<Propertytype> propertyType = new PropertytypeDB().getByID(property.get().getTypeId());
                Optional<Garagetype> garageType = new GaragetypeDB().getByID(property.get().getGarageId());
                Optional<Vendor> vendor = new VendorDB().getByID(property.get().getVendorId());

                ArrayList<String> imagesName = new ArrayList<>();
                String path = getServletContext().getRealPath("/");
                path += "assets/images/properties/large/" + property.get().getListingNum();
                File folder = new File(path);
                File[] listOfFiles = folder.listFiles();
                if(folder.exists() && listOfFiles != null)
                {
                    for (int i = 0; i < listOfFiles.length; i++)
                    {
                        if (listOfFiles[i].isFile()) {
                            imagesName.add(listOfFiles[i].getName());
                        }
                    }
                }


                request.setAttribute("property", property.get());
                request.setAttribute("PropertyStyle", style.get());
                request.setAttribute("propertyType", propertyType.get());
                request.setAttribute("garageType", garageType.get());
                request.setAttribute("vendor", vendor.get());
                request.setAttribute("imagesName", imagesName);
                request.setAttribute("formHeader", "Edit Property");
                request.setAttribute("btnValue", "Edit");
            }
            request.setAttribute("styles", styles);
            request.setAttribute("propertyTypes", propertyTypes);
            request.setAttribute("garageTypes", garageTypes);
            request.setAttribute("vendors", vendors);
            request.setAttribute("BER", Arrays.asList(BER));
            page = "AddEditProperty.jsp";
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
