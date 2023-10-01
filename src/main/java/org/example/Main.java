package org.example;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.Database.DBConnection;
import org.example.Servlets.*;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception{

        Connection connection = DBConnection.connect();
        ServletContextHandler handler = new ServletContextHandler();

        handler.addServlet(new ServletHolder(new UserServlet(connection)), "/user");
        handler.addServlet(new ServletHolder(new RegisterServlet(connection)), "/register");
        handler.addServlet(new ServletHolder(new LoginServlet(connection)), "/login");
        handler.addServlet(new ServletHolder(new LikeServlet(connection)), "/like");
        handler.addServlet(StaticFileServlet.class, "/*");

        Server server = new Server(8080);
        server.setHandler(handler);
        server.join();
        server.start();
    }
}