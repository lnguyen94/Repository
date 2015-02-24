package com.example.samford.shoppingwithfriends;

/**
 * Created by samford on 2/24/15.
 */
public class Item {
    public String name;
    public int price;
    public Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String toString() {
        return this.name + " @ " + this.price;
    }

    public boolean equals(Item item) {
        if (item instanceof Item){
            return this.name.equals(item.name);
        }
        return false;
    }

}
