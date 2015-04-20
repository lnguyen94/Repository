package com.example.samford.shoppingwithfriends;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Test class to for reporting an item
 *
 * @author Sam
 * @version 1.0
 */
public class AddWishListTest extends ActivityInstrumentationTestCase2<AddWishlistActivity>  {

    private AddWishlistActivity activity;
    private Solo solo;

    public AddWishListTest() {
        super(AddWishlistActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        // If you want to send key events via your test,
        // you have to turn off the touch mode in the emulator via
        setActivityInitialTouchMode(true);
        activity = getActivity();
        solo = new Solo(getInstrumentation(), getActivity());
    }

    @Override
    protected void tearDown() throws Exception {
        solo.finishOpenedActivities();
        super.tearDown();
    }

    /**
     * Tests that the activity was set up properly
     */
    @SmallTest
    public void testActivityTestCaseSetUpProperly() {
        assertNotNull("activity should be launched successfully", activity);
        solo.assertCurrentActivity("wrong activity", AddWishlistActivity.class);
    }

//    /**
//     * Tests that the attempt to add a blank Item raised an AlertDialog
//     */
//    @SmallTest
//    public void testAddBlanks() {
//        getInstrumentation().waitForIdleSync();
//        solo.clickOnButton("Add Item");
//        assertTrue("Could not find the dialog!", solo.searchText("All fields"));
//    }


    /**
     * Tests adding item to wishlist
     */
    @LargeTest
    public void testAddItem() {
        getInstrumentation().waitForIdleSync();
        setActivityInitialTouchMode(true);
        solo.enterText((EditText) activity.findViewById(R.id.name),
                "TestingItem11234");
        solo.enterText((EditText) activity.findViewById(R.id.price),
                "50");
        solo.enterText((EditText) activity.findViewById(R.id.max_dist),
                "50");
        solo.enterText((EditText) activity.findViewById(R.id.min_quantity_rem),
                "50");
        solo.clickOnButton("Add Item");
        // Activity should silently finish;
    }
}
