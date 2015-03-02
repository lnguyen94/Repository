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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAvgRating() {
        return avgRating;
    }

    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    public int getNumOfRatings() {
        return numOfRatings;
    }

    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    public double getLastLocLat() {
        return lastLocLat;
    }

    public void setLastLocLat(double lastLocLat) {
        this.lastLocLat = lastLocLat;
    }

    public double getLastLocLong() {
        return lastLocLong;
    }

    public void setLastLocLong(double lastLocLong) {
        this.lastLocLong = lastLocLong;
    }

    public ArrayList<User> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }

    public double getPriceThresh() {
        return priceThresh;
    }

    public void setPriceThresh(double priceThresh) {
        this.priceThresh = priceThresh;
    }

    private String email;
    private String name;
    private String password;
    private int avgRating;
    private int numOfRatings;
    private double lastLocLat;
    private double lastLocLong;
    private double priceThresh;
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Create an empty user
     */
    public User() { }

    /**
     * Create a User with name, username, and password
     * @param name the name of the user
     * @param email the user name of the user
     * @param password the password of the user
     */
    public User(String name, String email, String password) {
        this(email, password);
        this.name = name;
    }

//    public User(String name, String email, String password, int avgRating, int numOfRatings, int lastLocLat, int lastLocLong) {
//        this.name = name;
//        this.email = email;
//        this.password = password;
//    }

    /**
     * Create a User with user name and password
     * @param email the user name of the user
     * @param password the password of the user
     */
    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.avgRating = 0;
        this.numOfRatings = 0;
        this.lastLocLat = 0;
        this.lastLocLong = 0;
        this.priceThresh = 0;
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

    public void removeItem(Item item) {
        this.items.remove(item);
    }

    public void removeFriend(User friend) {
        this.friends.remove(friend);
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
        return (this.email.equals(that.email)
                && this.password.equals(that.password));
    }

    /**
     * Returns their name, username(email), and password currently for debugging
     * @return The String representation of the user
     */
    @Override
    public String toString() {
        return this.name + " " + this.email + " " + this.password;
    }
}
