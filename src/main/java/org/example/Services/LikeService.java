package org.example.Services;

import org.example.DAO.LikeDAO;
import org.example.Entities.Like;
import org.example.Entities.User;

import java.util.ArrayList;
import java.util.List;

public class LikeService {
    private LikeDAO likeDAO;

    public LikeService(LikeDAO likeDAO) {
        this.likeDAO = likeDAO;
    }

    public void addLike(Like like){
        if(!isLikeExist(like)){
            likeDAO.add(like);
        }
    }

    public boolean isLikeExist(Like like){
        List<Like> allLikes = getAllLikes();
        for (Like lk : allLikes) {
            if (lk.equals(like)) {
                return true;
            }
        }
        return false;
    }

    public List<Like> getAllLikes(){
        return likeDAO.getAll();
    }

    public void removeLike(int id){
        likeDAO.remove(id);
    }

    public int getLikeId(Like like) {
        List<Like> allLikes = getAllLikes();
        for (Like lk : allLikes) {
            if (lk.getLikerId() == like.getLikerId() && lk.getLikedId() == like.getLikedId()) {
                return lk.getId();
            }
        }
        return 0;
    }

    public List<Like> getLikesFromUser(int id) {
        List<Like> allLikes = getAllLikes();
        List<Like> filteredLikes = new ArrayList<>();

        for (Like l : allLikes) {
            if (l.getLikerId() == id) {
                filteredLikes.add(l);
            }
        }
        return filteredLikes;
    }
}