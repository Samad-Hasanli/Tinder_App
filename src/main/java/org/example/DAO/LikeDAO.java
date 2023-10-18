package org.example.DAO;

import org.example.Entities.Like;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LikeDAO {
    private Connection connection;

    public LikeDAO(Connection connection) {
        this.connection = connection;
    }

    public void add(Like like) {
        String query = "insert into \"likes\" (likerid, likedid) values (?, ?)";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, like.getLikerId());
            preparedStatement.setInt(2, like.getLikedId());
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void remove(int id) {
        String query = "delete from \"likes\" where id = ?";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public List<Like> getAll() {
        List<Like> likes = new ArrayList<>();
        String sql = "select * from \"likes\" ";

        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                likes.add(new Like(resultSet.getInt("id"), resultSet.getInt("likerId"), resultSet.getInt("likedId")));
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return likes;
    }
}
