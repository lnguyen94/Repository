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
    public NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);

    public Date getSaleEndDate() {
        return saleEndDate;
    }

    public void setSaleEndDate(Date saleEndDate) {
        this.saleEndDate = saleEndDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public String getRecommendee() {
        return recommendee;
    }

    public void setRecommendee(String recommendee) {
        this.recommendee = recommendee;
    }

    public double getLocLat() {
        return locLat;
    }

    public void setLocLat(double locLat) {
        this.locLat = locLat;
    }

    public double getLocLong() {
        return locLong;
    }

    public void setLocLong(double locLong) {
        this.locLong = locLong;
    }

    public int getQuantRem() {
        return quantRem;
    }

    public void setQuantRem(int quantRem) {
        this.quantRem = quantRem;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public double getPriceThresh() {
        return priceThresh;
    }

    public void setPriceThresh(double priceThresh) {
        this.priceThresh = priceThresh;
    }

    public double getMaxDistance() {
        return maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    public int getMinQuantRem() {
        return minQuantRem;
    }

    public void setMinQuantRem(int minQuantRem) {
        this.minQuantRem = minQuantRem;
    }

    private Date saleEndDate;


    /**
     * Create a user with a name and a price
     * @param name the name of the item
     * @param price the price of the item
     */
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Item() {

    }

    /**
     * The name and price of the item
     *
     * @return The String representation of the Item
     */
    @Override

    public String toString() {
        return this.name + " @ " + currencyFormat.format(this.price);
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
