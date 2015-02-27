package com.example.samford.shoppingwithfriends;

import java.util.ArrayList;

/**
 * Data Object class for the User currently logged in and any other
 * registered user
 *
 * @author Sam
 * @version 1.0
 */
public class User {
    private String username;
    private String email;
    private String name;
    private int avgRating;
    private int numOfRatings;
    private double lastLocLat;
    private double lastLocLong;
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Create an empty user
     */
    public User() { }

    /**
     * Create a User with name, username, and password
     * @param name the name of the user
     * @param username the user name of the user
     * @param password the password of the user
     */
    public User(String name, String username, String password) {
        this(username, password);
        this.name = name;
    }

    /**
     * Create a User with user name and password
     * @param username the user name of the user
     * @param password the password of the user
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.rating = 0;
        this.reports = 0;
    }

    /**
     * Mechanic for adding friends
     *
     * @param friend the friend to be added
     */
    public void addFriend(User friend) {
        this.friends.add(friend);
    }

    /**
     * Adds an item to their item list
     *
     * @param item the item to be added
     */
    public void addItem(Item item) {
        this.items.add(item);
    }

    /**
     * Compares user names (currently emails) and passwords
     * @param o User to compare
     * @return whether or not the two object are the same
     */
    @Override
    public boolean equals(Object o) {
        //check for self-comparison
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User that = (User) o;
        return (this.username.equals(that.username)
                && this.password.equals(that.password));
    }

    /**
     * Returns their name, username(email), and password currently for debugging
     * @return The String representation of the user
     */
    @Override
    public String toString() {
        return this.name + " " + this.username + " " + this.password;
    }
}
