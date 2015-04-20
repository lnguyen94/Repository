package com.example.samford.shoppingwithfriends;

import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Data Object class for all Items in the app
 *
 * @author Sam
 * @version 1.0
 */
public class Item {
    private String name;
    private double price;
    private String recommender;
    private String recommendee;
    private double locLat;
    private double locLong;
    private int quantRem;
    private String store;
    private double priceThresh;
    private double maxDistance;
    private int minQuantRem;
    private Date saleEndDate;

    /**
     * Create a user with a name and a price
     * @param name the name of the item
     * @param price the price of the item
     */
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    /**
     * Constructs blank Item
     */
    public Item() { }

    /**
     * Get sale end date
     *
     * @return sale end date
     */
    public Date getSaleEndDate() {
        return saleEndDate;
    }

    /**
     * Set the end sale date
     *
     * @param saleEndDate the new sale end date
     */
    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    /**
     * Get the name of the Item
     *
     * @return the name of the item
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the item
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the price of the item
     *
     * @return the price of the item
     */
    public double getPrice() {
        return price;
    }

    /**
     * Sets the price of item
     *
     * @param price the new price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Get the recommender of the Item
     *
     * @return the recommender
     */
    public String getRecommender() {
        return recommender;
    }

    /**
     * Set the recommender of the Item
     *
     * @param recommender the new recommender
     */
    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    /**
     * Gets the recommendee of the Item
     *
     * @return the recommendee
     */
    public String getRecommendee() {
        return recommendee;
    }

    /**
     * Sets the new recommendee
     *
     * @param recommendee the new recommendee
     */
    public void setRecommendee(String recommendee) {
        this.recommendee = recommendee;
    }

    /**
     * Gets the latitude of hte item location
     *
     * @return the latitude of the item location
     */
    public double getLocLat() {
        return locLat;
    }

    /**
     * Sets the latitude of the location
     *
     * @param locLat the new latitude
     */
    public void setLocLat(double locLat) {
        this.locLat = locLat;
    }

    /**
     * Gets the longitude of the location of the item
     *
     * @return longitude location
     */
    public double getLocLong() {
        return locLong;
    }

    /**
     * Sets the longitude of the location of the item
     *
     * @param locLong the new longitude location
     */
    public void setLocLong(double locLong) {
        this.locLong = locLong;
    }

    /**
     * Gets the Quantity Remaining of the item
     *
     * @return the quantity remaining
     */
    public int getQuantRem() {
        return quantRem;
    }

    /**
     * Sets the new quantity remaining
     *
     * @param quantRem the new quantity remaining
     */
    public void setQuantRem(int quantRem) {
        this.quantRem = quantRem;
    }

    /**
     * Gets the store of the item
     *
     * @return hte store
     */
    public String getStore() {
        return store;
    }

    /**
     * Sets the new store
     *
     * @param store the new store
     */
    public void setStore(String store) {
        this.store = store;
    }

    /**
     * Gets the price threshold of the report
     *
     * @return the price threshold
     */
    public double getPriceThresh() {
        return priceThresh;
    }

    /**
     * Sets the price threshold of the report
     *
     * @param priceThresh the price threshold
     */
    public void setPriceThresh(double priceThresh) {
        this.priceThresh = priceThresh;
    }

    /**
     * Gets the max distance
     *
     * @return the maximum distance
     */
    public double getMaxDistance() {
        return maxDistance;
    }

    /**
     * Sets the new maximum distance of the report
     *
     * @param maxDistance max distance
     */
    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    /**
     * Gets the Minimum quantity remaining
     *
     * @return min quantity remaining
     */
    public int getMinQuantRem() {
        return minQuantRem;
    }

    /**
     * Sets the new minimum quantity remaining
     *
     * @param minQuantRem minimum quantity remaining
     */
    public void setMinQuantRem(int minQuantRem) {
        this.minQuantRem = minQuantRem;
    }

    /**
     * The name and price of the item
     *
     * @return The String representation of the Item
     */
    @Override
    public String toString() {
        return this.name + " @ " + this.price;
    }

    /**
     * Compares name of the product
     *
     * @param o the Item to compare
     * @return whether or not the two object are the same
     */
    @Override
    public boolean equals(Object o) {
        //check for self-comparison
        if (this == o) {
            return true;
        }
        if (!(o instanceof Item)) {
            return false;
        }
        Item that = (Item) o;
        return this.name.equals(that.name);
    }
}
