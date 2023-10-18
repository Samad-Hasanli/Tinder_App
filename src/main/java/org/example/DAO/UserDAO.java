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

    public List<User> getAll(){
        List<User> userList = new ArrayList<>();
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

    public User get(int id) {
        User user = null;
        String query = "select id, email, password, name, surname, gender, imgurl from \"users\" where id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                user = new User();
                resultSet.getInt("id");
                resultSet.getString("email");
                resultSet.getString("password");
                resultSet.getString("name");
                resultSet.getString("surname");
                resultSet.getBoolean("gender");
                resultSet.getString("imgUrl");
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return user;
    }
}


