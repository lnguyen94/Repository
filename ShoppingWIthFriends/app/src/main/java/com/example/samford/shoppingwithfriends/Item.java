package com.example.samford.shoppingwithfriends;

/**
 * Data Object class for all Items in the app
 *
 * @author Sam
 * @version 1.0
 */
public class Item {
    public String name;
    public int price;

    /**
     * Create a user with a name and a price
     * @param name the name of the item
     * @param price the price of the item
     */
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
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
