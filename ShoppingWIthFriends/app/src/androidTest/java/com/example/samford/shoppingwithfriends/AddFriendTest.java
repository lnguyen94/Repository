package com.example.samford.shoppingwithfriends;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.robotium.solo.Solo;

/**
 * Test class to for adding a friend
 *
 * @author Shree
 * @version 1.0
 */
public class AddFriendTest extends ActivityInstrumentationTestCase2<AddFriendActivity>  {

    private AddFriendActivity activity;
    private Solo solo;

    public AddFriendTest() {
        super(AddFriendActivity.class);
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
        solo.assertCurrentActivity("wrong activity", AddFriendActivity.class);
    }

    /**
     * Tests adding a valid friend
     */
    @LargeTest
    public void testAddFriend() {
        getInstrumentation().waitForIdleSync();
        setActivityInitialTouchMode(true);
        solo.clickInList(1);
        // Activity should silently finish;
    }
}
