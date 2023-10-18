package org.example.Services;

import org.example.DAO.UserDAO;
import org.example.Entities.Like;
import org.example.Entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class UserService {
    boolean checker;
    private UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public boolean isChecker() {
        return checker;
    }

    public void addUser(User user){
        userDAO.add(user);
    }

    public List <User> getUsers(){
        return userDAO.getAll();
    }


    public int getLogin(User user) {
        List<User> allUsers = getUsers();
        for (User usr : allUsers) {
            if (usr.getEmail().equals(user.getEmail()) && usr.getPassword().equals(user.getPassword())) {
                checker = true;
                return usr.getId();
            }
        }
        return 0;
    }

    public User getUser(int id){
        return userDAO.get(id);
    }

    public List<User> getAllUsers(int id) {
        List<User> allUsers = getUsers();
        List<User> filteredUsers = new ArrayList<>();

        for (User user : allUsers) {
            if (user.getId() != id) {
                filteredUsers.add(user);
            }
        }
        return filteredUsers;
    }

    public List<User> getLikedUsers(int id, List<Like> likes){
        List<User> likedUsers = new ArrayList<>();

        for (Like lk : likes) {
            if (lk.getLikedId() == id) {
                likedUsers.add(getUser(lk.getLikedId()));
            }
        }
        return likedUsers;
    }

}
