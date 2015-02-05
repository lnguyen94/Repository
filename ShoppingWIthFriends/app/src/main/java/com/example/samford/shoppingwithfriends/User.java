package com.example.samford.shoppingwithfriends;

/**
 * Created by samford on 2/3/15.
 */
public class User {
    public String name, username, password;
    public User() {

    }

    public User(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean equals(Object object) {
        User u = (User) object;
        if (object instanceof User){
            return ((this.username).equals(u.username) && (this.password).equals(u.password));
        }
        return false;
    }
}
