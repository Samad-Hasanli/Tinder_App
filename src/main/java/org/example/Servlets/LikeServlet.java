package org.example.Servlets;

import org.example.DAO.UserDAO;
import org.example.Services.UserService;
import org.example.Utils.FreeMarkerEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class LikeServlet extends HttpServlet {
    private Connection connection;
    private UserService userService;
    private final FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();

    public LikeServlet(Connection connection) {
        this.userService = new UserService(new UserDAO(connection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HashMap<String, Object> data = new HashMap<>();
        List<User> likedUsers = userService.get
        data.put("users", likedUsers);
        freeMarkerEngine.render(resp, "people-list.ftl", data);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
