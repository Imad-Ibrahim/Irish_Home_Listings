package Controller;

import Database.VendorDB;
import Model.Vendor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "EditVendorServlet", value = "/EditVendorServlet")
public class EditVendorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String street = request.getParameter("street");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String vendorID = request.getParameter("vendorID");
        String page, message;

        try {
            Optional<Vendor> vendor = new VendorDB().getByID(Integer.parseInt(vendorID));
            String userName = firstName +"."+ lastName;
            VendorDB vendorDB = new VendorDB();
            Vendor v = new Vendor(vendor.get().getVendorId(), firstName, lastName, street, city, phone, email);
            vendorDB.update(v);
            page = "GetAllVendorsServlet";
            message = userName;
        } catch (NumberFormatException e) {
            throw new RuntimeException(e);
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
