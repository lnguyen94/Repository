package com.example.samford.shoppingwithfriends;

import android.content.Context;

import java.util.ArrayList;

/**
 * Data Object class for the User currently logged in and any other
 * registered user
 *
 * @author Sam
 * @version 1.0
 */
public class User {

    private String email;
    private String name;
    private String password;
    private int avgRating;
    private int numOfRatings;
    private double lastLocLat;
    private double lastLocLong;
    private double priceThresh;
    private ArrayList<User> friends = new ArrayList<>();
    private ArrayList<Item> wishList = new ArrayList<>();
    private ArrayList<Item> reportedItems = new ArrayList<>();

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
     * Adds an item to their item list
     *
     * @param item the item to be added
     */
    public void addWish(Item item) {
        this.wishList.add(item);
    }

    /**
     * Remove the item from the user's items
     *
     * @param item the item to remove
     */
    public void removeWish(Item item) {
        this.wishList.remove(item);
    }

    /**
     * Remove a friend from the user's friends
     *
     * @param friend the friend to remove
     */
    public void removeFriend(User friend) {
        this.friends.remove(friend);
    }

    /**
     * Remove a friend from the user's friends
     *
     * @param friend the friend to remove
     */
    public void addFriend(User friend, Context context) {

    }


    /**
     * Get the email
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the email of the user
     *
     * @param email the email to change
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Set the name of the user
     *
     * @return the name of the user
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the user
     *
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the password
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the password of the user
     *
     * @param password the new password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the average rating of the user
     *
     * @return the user's average rating
     */
    public int getAvgRating() {
        return avgRating;
    }

    /**
     * set the average rating of the user
     *
     * @param avgRating the new average rating
     */
    public void setAvgRating(int avgRating) {
        this.avgRating = avgRating;
    }

    /**
     * Get the number of ratings
     *
     * @return the number of ratings
     */
    public int getNumOfRatings() {
        return numOfRatings;
    }

    /**
     * Set the number of ratings of a user
     *
     * @param numOfRatings the new number of ratings
     */
    public void setNumOfRatings(int numOfRatings) {
        this.numOfRatings = numOfRatings;
    }

    /**
     * Get the last location of the user - latitude
     *
     * @return the last location of the user - latitude
     */
    public double getLastLocLat() {
        return lastLocLat;
    }

    /**
     * Set the last location of the user - latitude
     *
     * @param lastLocLat the new location of the user - latitude
     */
    public void setLastLocLat(double lastLocLat) {
        this.lastLocLat = lastLocLat;
    }

    /**
     * Gets the last location of the user - longitude
     *
     * @return the last location of the user - longitude
     */
    public double getLastLocLong() {
        return lastLocLong;
    }

    /**
     * Set the last location of the user - longitude
     *
     * @param lastLocLong the last location of the user - longitude
     */
    public void setLastLocLong(double lastLocLong) {
        this.lastLocLong = lastLocLong;
    }

    /**
     * Gets the friends of the users
     *
     * @return the friends of the users
     */
    public ArrayList<User> getFriends() {
        return friends;
    }

    /**
     * Sets the list of the friends for the user
     *
     * @param friends the new set of friends
     */
    public void setFriends(ArrayList<User> friends) {
        this.friends = friends;
    }

    /**
     * Gets the Items of the user
     *
     * @return the Items of the user
     */
    public ArrayList<Item> getWishList() {
        return wishList;
    }

    /**
     * Sets the items of the user
     *
     * @param items the items of the user
     */
    public void setWishList(ArrayList<Item> items) {
        this.wishList = items;
    }

    /**
     * Gets the price threshold of the user
     *
     * @return the price threshold of the user
     */
    public double getPriceThresh() {
        return priceThresh;
    }

    /**
     * Sets the price threshold of the user
     *
     * @param priceThresh the price threshold of the user
     */
    public void setPriceThresh(double priceThresh) {
        this.priceThresh = priceThresh;
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
        return (this.email.equals(that.email));
    }

    /**
     * Returns their name, username(email), and password currently for debugging
     * @return The String representation of the user
     */
    @Override
    public String toString() {
        return this.name + " (" + this.email + ")";
    }
}
