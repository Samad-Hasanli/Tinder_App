package org.example.Servlets;

import org.example.Utils.FreeMarkerEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;

public class UserServlet extends HttpServlet {
    private final Connection connection;
    FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();

    public UserServlet(Connection connection) {
        this.connection = connection;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
