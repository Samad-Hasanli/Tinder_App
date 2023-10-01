package org.example.Entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Like {
    private int id;
    private int likerId;
    private int likedId;

    public Like(int likerId, int likedId) {
        this.likerId = likerId;
        this.likedId = likedId;
    }

}
