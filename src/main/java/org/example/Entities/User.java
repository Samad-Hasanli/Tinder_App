package org.example.Entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    private int id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private boolean gender;
    private String imgUrl;

    public User(String email, String password){
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String name, String surname, boolean gender, String imgUrl) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.imgUrl = imgUrl;
    }



}
