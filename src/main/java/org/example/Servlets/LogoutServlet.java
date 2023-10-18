package org.example.Servlets;

import org.example.Services.CookieService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        CookieService cs = new CookieService(req, resp);
        cs.removeCookie();
        resp.sendRedirect("/login");
    }

}
