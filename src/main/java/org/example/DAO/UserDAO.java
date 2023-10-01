package org.example.DAO;

import org.example.Entities.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(User user){
        String sql = "insert into \"users\"(email, password, name, surname, gender, imgUrl) values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getName());
            preparedStatement.setString(4, user.getSurname());
            preparedStatement.setBoolean(5, user.isGender());
            preparedStatement.setString(6, user.getImgUrl());
            preparedStatement.execute();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

//    public void getById(int id){
//        String sql = "select id, email, password from \"users\" where id = ? ";
//        try {
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            ResultSet resultSet = preparedStatement.executeQuery();
//            preparedStatement.setInt(1, id);
//            if(resultSet.next()){
//                User user = new User();
//                resultSet.getInt(user.getId());
//                resultSet.getString(user.getEmail());
//                resultSet.getString(user.getPassword());
//            }
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//    }

    public User getById(User user){
        String sql = "select id, email, password from users where id = ?";
        int id = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                  user = new User();
                  resultSet.getInt(user.getId());
                  resultSet.getString(user.getEmail());
                  resultSet.getString(user.getPassword());
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return user;
    }

    public List<User> getAll(List<User> userList){
        String sql = "select * from \"users\"";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                boolean gender = resultSet.getBoolean("gender");
                String imgUrl = resultSet.getString("imgUrl");
                userList.add(new User(id, email, password, name, surname, gender, imgUrl));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return userList;
    }

    public User getLogin(User user) {
        User result = null;
        String sql = "SELECT * FROM users WHERE email=? AND password=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPassword());
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result = new User(rs.getInt("id")
                        , rs.getString("email")
                        , rs.getString("password")
                        , rs.getString("name")
                        , rs.getString("surname")
                        , rs.getBoolean("gender")
                        , rs.getString("imgURL"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}


