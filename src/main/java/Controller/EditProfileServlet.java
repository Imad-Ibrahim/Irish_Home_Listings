package Controller;

import Database.UserDB;
import Model.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EditProfileServlet", value = "/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String userType = request.getParameter("userType");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        User currentUser = (User) request.getSession().getAttribute("loggedInUser");
        String page, message;

        if (password.equals(confirmPassword)){
            String userName = firstName +"."+ lastName;
            if (userType.equals("frontOffice")){
                UserDB userDB = new UserDB();
                User user = new User(currentUser.getId(), firstName, lastName, phone, email, userName, password, currentUser.getActive(), currentUser.getUserType());
                try {
                    userDB.update(user);
                    request.getSession().removeAttribute("loggedInUser");
                    request.getSession().setAttribute("loggedInUser", user);
                    page = "index.jsp";
                    message = userName;
                } catch (Exception e) {
                    page = "Error.jsp";
                    message = "Something went wrong";
                }
            }
            else {
                page = "Error.jsp";
                message = "Something went wrong";
            }
        }
        else{
            page = "Error.jsp";
            message = "Passwords does not match";
        }
        request.setAttribute("message", message);
        RequestDispatcher dispatcher = request.getRequestDispatcher(page);
        dispatcher.forward(request, response);
    }
}
