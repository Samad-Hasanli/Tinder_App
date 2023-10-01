package org.example.Services;

import org.example.DAO.UserDAO;
import org.example.Entities.User;

import java.sql.Connection;
import java.util.List;

public class UserService {
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addUser(User user){
        userDAO.add(user);
    }

    public User getUserById(User user) {
        return userDAO.getById(user);
    }

    public boolean getLogin(User user){
        User result = userDAO.getLogin(user);
        return result != null && result.getEmail().equals(user.getEmail()) && result.getPassword().equals(user.getPassword());
    }

    public List <User> getUsers(List<User> userList){
        return userDAO.getAll(userList);
    }

    public void checker(User user){
        if(getLogin(user)==true){
            System.out.println("salam");
        }
    }
}
