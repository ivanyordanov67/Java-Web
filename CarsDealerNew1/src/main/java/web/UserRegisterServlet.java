package web;


import models.services.UserService;
import models.services.base.UserServiceImpl;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/register")
public class UserRegisterServlet extends HttpServlet {

    private final UserService userService;


    @Inject
    public UserRegisterServlet(UserService userService) {
        this.userService = userService;
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/users-register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String confirmPassword = req.getParameter("confirmPassword");


        try {
            userService.register(username, email, password,confirmPassword);
            resp.sendRedirect("/home");
        } catch (Exception e) {
            resp.sendRedirect("/users/register");
        }

   }
}
