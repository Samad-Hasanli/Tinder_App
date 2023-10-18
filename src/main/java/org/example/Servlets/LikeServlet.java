package org.example.Servlets;

import org.example.DAO.UserDAO;
import org.example.Entities.User;
import org.example.Services.CookieService;
import org.example.Services.LikeService;
import org.example.Services.UserService;
import org.example.Utils.FreeMarkerEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class LikeServlet extends HttpServlet {
    private CookieService cs;
    private Connection connection;
    private UserService userService;
    private LikeService likeService;
    private final FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();

    public LikeServlet(Connection connection) {
        this.userService = new UserService(new UserDAO(connection));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        cs = new CookieService(req, resp);
        int id = Integer.parseInt(cs.getCookie().getValue());
        List<User> liked = userService.getLikedUsers(id, likeService.getLikesFromUser(id));
        HashMap<String, Object> data = new HashMap<>();
        data.put("users", liked);
        freeMarkerEngine.render(resp, "people-list.ftl", new HashMap<>());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
