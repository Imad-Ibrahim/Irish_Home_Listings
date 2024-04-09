package Controller;

import Database.UserDB;
import Database.VendorDB;
import Model.Agent;
import Model.User;
import Model.Vendor;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SignUpServlet", value = "/SignUpServlet")
public class SignUpServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        String page, message;
        String userName = firstName +"."+ lastName;
        if (request.getSession().getAttribute("loggedInUser") != null && request.getSession().getAttribute("loggedInUser").getClass().equals(Agent.class)){
            String street = request.getParameter("street");
            String city = request.getParameter("city");
            VendorDB vendorDB = new VendorDB();
            List<Vendor> vendors = vendorDB.getAll();
            int vendorID;
            if (vendors != null)
                vendorID = vendors.subList(vendors.size() - 1, vendors.size()).get(0).getVendorId() + 1;
            else
                vendorID = 1;
            Vendor vendor = new Vendor(vendorID, firstName, lastName, street, city, phone, email);
            try {
                vendorDB.insert(vendor);
                page = "index.jsp";
                message = userName;
            } catch (Exception e) {
                page = "Error.jsp";
                message = "Something went wrong";
            }
        }
        else {
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            if (password.equals(confirmPassword)){
                UserDB userDB = new UserDB();
                List<User> users = userDB.getAll();
                int userID;
                if(users != null)
                    userID= users.subList(users.size() - 1, users.size()).get(0).getId() + 1;
                else
                    userID = 1;
                User user = new User(userID, firstName, lastName, phone, email, userName, password, 1, "frontOffice");
                try {
                    userDB.insert(user);
                    page = "SignIn.jsp";
                    message = userName;
                } catch (Exception e) {
                    page = "Error.jsp";
                    message = "Something went wrong";
                }
            }
            else{
                page = "Error.jsp";
                message = "Passwords does not match";
            }
        }

        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
