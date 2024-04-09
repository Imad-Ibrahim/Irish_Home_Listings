package Controller;

import Model.Agent;
import Model.Helper;
import Model.Vendor;


import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "GetAllVendorsServlet", value = "/GetAllVendorsServlet")
public class GetAllVendorsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        List<Vendor> vendorsList = Helper.GetAllVendors();
        request.setAttribute("vendorsList", vendorsList);
        request.setAttribute("message", Helper.message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(Helper.page);
        dispatcher.forward(request, response);
    }
}
