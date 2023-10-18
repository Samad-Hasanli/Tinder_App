package org.example.Servlets;


import org.example.DAO.LikeDAO;
import org.example.Entities.Like;
import org.example.Entities.User;
import org.example.DAO.UserDAO;
import org.example.Utils.FreeMarkerEngine;
import org.example.Services.CookieService;
import org.example.Services.LikeService;
import org.example.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

public class UserServlet extends HttpServlet {

    private final Connection connection;
    private UserService userService;
    private LikeService likeService;
    private final FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
    private CookieService cs;
    private static int counter = 0;
    public UserServlet(Connection connection) {
        this.connection = connection;
        this.userService = new UserService(new UserDAO(connection));
        this.likeService = new LikeService(new LikeDAO(connection));
    }

    @Override
    protected void doGet(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {
        cs = new CookieService(rq, rs);
        int id = Integer.parseInt(cs.getCookie().getValue());
        List<User> allUsers = userService.getAllUsers(id);
        User user = allUsers.get(counter++);
        HashMap<String, Object> data = new HashMap<>();
        data.put("user", user);
        freeMarkerEngine.render(rs, "like-page.ftl", data);
    }

    @Override
    protected void doPost(HttpServletRequest rq, HttpServletResponse rs) throws ServletException, IOException {

        cs = new CookieService(rq, rs);
        int likerId = Integer.parseInt(cs.getCookie().getValue());
        int likedId = Integer.parseInt(rq.getParameter("likedId"));
        Like like = new Like(likerId, likedId);

        if (rq.getParameter("like") != null) {
            likeService.addLike(like);
        }
        else if (rq.getParameter("dislike") != null){
            if(likeService.isLikeExist(like)){
                likeService.removeLike(likeService.getLikeId(like));
            }
        }

        if(counter == userService.getAllUsers(likerId).size()){
            counter = 0;
            rs.sendRedirect("/like");
        }
        else{
            doGet(rq, rs);
        }
    }
}
