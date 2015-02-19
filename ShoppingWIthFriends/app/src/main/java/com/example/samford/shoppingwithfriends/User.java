package com.example.samford.shoppingwithfriends;

import java.util.ArrayList;

/**
 * Created by samford on 2/3/15.
 */
public class User {
    public String name, username, password;
    //username is synonymous to username at this juncture
    public int rating, salesReports;
    public ArrayList<User> friends = new ArrayList<>();
    public User() {

    }

    public User(String name, String username, String password) {
        this(username, password);
        this.name = name;

    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.rating = 0;
        this.salesReports = 0;
    }

    /**
     * compares usernames (emails) and passwords
     * @param object
     * @return
     */
    public boolean equals(Object object) {
        User u = (User) object;
        if (object instanceof User){
            return ((this.username).equals(u.username) && (this.password).equals(u.password));
        }
        return false;
    }
    /**
     * mechanic for adding friends
     */
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    /**
     * returns their name, username(email), and password currently for debugging
     * @return String
     */
    public String toString() {
        return name + " " + username + " " + password;
    }
}
