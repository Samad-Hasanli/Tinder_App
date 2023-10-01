package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String url = "jdbc:postgresql://localhost:5432/postgres";
    private static final String userName = "postgres";
    private static final String password = "postgres";

    private static Connection connection;

    public static Connection connect(){
        if(connection == null){
            try {
                connection = DriverManager.getConnection(url, userName, password);
            }catch (SQLException e){
                throw new RuntimeException("There is a problem to connect database");
            }
        }
        return connection;
    }
}
