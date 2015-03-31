package com.example.samford.shoppingwithfriends;

import junit.framework.TestCase;

/**
 * Test class for
 *
 * @author Lien
 * @version 1.0
 */
public class ItemEqualsTest extends TestCase{

    /**
     * First Equals Test
     */
    public void testEqual1() {
        Item item1 = new Item();
        item1.setName("cat");
        item1.setPrice(10);

        Item item2 = new Item();
        item2.setName("cat");
        item2.setPrice(11);

        assertTrue(item1.equals(item2));
    }

    /**
     * Another Equals Test
     */
    public void testEqual2(){
        Item item1 = new Item();
        item1.setName("cat");
        item1.setPrice(11);

        Item item2 = new Item();
        item2.setName("cat");
        item2.setPrice(11);

        assertTrue(item1.equals(item2));
    }

    /**
     * First Not Equals Test
     */
    public void testNotEqual(){
        Item item1 = new Item();
        item1.setName("cat");
        item1.setPrice(11);

        Item item2 = new Item();
        item2.setName("dog");
        item2.setPrice(11);

        assertTrue(!item1.equals(item2));
    }

    /**
     * Other not equals test
     */
    public void testNotEqualName() {
        Item item1 = new Item();
        item1.setName("cat");
        item1.setPrice(11);

        Item item2 = new Item();
        item2.setName("ferret");
        item2.setPrice(111);

        assertTrue(!item1.equals(item2));
    }
}