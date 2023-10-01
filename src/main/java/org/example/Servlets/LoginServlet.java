package org.example.Servlets;

import org.example.DAO.UserDAO;
import org.example.Entities.User;
import org.example.Services.UserService;
import org.example.Utils.FreeMarkerEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class LoginServlet extends HttpServlet {
    private final Connection connection;
    private final FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    private UserService userService;

    public LoginServlet(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new UserDAO(connection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        freeMarkerEngine.render(resp, "login.ftl", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = new User(email, password);

        userService.getLogin(user);
        userService.getUserById(user);

//        Cookie cookie = new Cookie("login", email);
//        resp.addCookie(cookie);
        if(userService.getLogin(user)==true){
            resp.sendRedirect("/like");
        }else{
            resp.sendRedirect("/login");
        }

    }
}
