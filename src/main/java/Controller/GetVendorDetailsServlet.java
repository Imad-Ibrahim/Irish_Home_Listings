package Controller;

import Database.VendorDB;
import Model.Vendor;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "GetVendorDetailsServlet", value = "/GetVendorDetailsServlet")
public class GetVendorDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page, message = "Something went wrong";
        String vendorID = request.getParameter("vendorID");
        try{
            Optional<Vendor> vendor = new VendorDB().getByID(Integer.parseInt(vendorID));
            request.setAttribute("vendor", vendor.get());
            page = "EditVendor.jsp";
        } catch (Exception e) {
            page = "Error.jsp";
            message = "Something went wrong";
            throw new RuntimeException(e);
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
