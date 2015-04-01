package com.example.samford.shoppingwithfriends;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Test class to for reporting an item
 *
 * @author Elliott
 * @version 1.0
 */
public class ItemListActivityTest extends ActivityInstrumentationTestCase2<ItemListActivity>  {

    private ItemListActivity activity;
    private Solo solo;

    public ItemListActivityTest() {
        super(ItemListActivity.class);
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
        solo.assertCurrentActivity("wrong activity", ItemListActivity.class);
    }


    /**
     * Tests adding a valid friend
     */
    @LargeTest
    public void testItemList() {
        getInstrumentation().waitForIdleSync();
        setActivityInitialTouchMode(true);
        solo.clickInList(0);
        solo.sleep(15000); // Wait 15 seconds
        // Activity should silently finish;
    }
}
