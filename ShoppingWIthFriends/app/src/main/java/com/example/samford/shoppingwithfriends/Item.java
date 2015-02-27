package com.example.samford.shoppingwithfriends;

import java.text.NumberFormat;
import java.util.Locale;

/**
 * Created by samford on 2/24/15.
 */
public class Item {
    public String name;
    public double price;
    public NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
    public Item(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return this.name + " @ " + currencyFormat.format(this.price);
    }

    public boolean equals(Item item) {
        if (item instanceof Item){
            return this.name.equals(item.name);
        }
        return false;
    }

}
