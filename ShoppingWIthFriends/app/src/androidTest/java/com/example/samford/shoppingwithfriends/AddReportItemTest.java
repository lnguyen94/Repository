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
public class AddReportItemTest extends ActivityInstrumentationTestCase2<ReportItemActivity>  {

    private ReportItemActivity activity;
    private Solo solo;

    public AddReportItemTest() {
        super(ReportItemActivity.class);
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
        solo.assertCurrentActivity("wrong activity", ReportItemActivity.class);
    }

    /**
     * Tests that the attempt to add a blank Item raised an AlertDialog
     */
    @SmallTest
    public void testAddBlanks() {
        getInstrumentation().waitForIdleSync();
        solo.clickOnButton("Add Item");
        assertTrue("Could not find the dialog!", solo.searchText("All fields"));
    }

    /**
     * Tests that the map activity is accurately opened
     */
    @MediumTest
    public void testMapActivity() {
        getInstrumentation().waitForIdleSync();
        solo.clickOnButton("Add Location");
        solo.assertCurrentActivity("wrong activity", MapActivity.class);
    }

    /**
     * Tests reporting a valid item
     */
    @LargeTest
    public void testAddItem() {
        getInstrumentation().waitForIdleSync();
        setActivityInitialTouchMode(true);
        solo.enterText((EditText) activity.findViewById(R.id.name),
                "TestingItem");
        solo.enterText((EditText) activity.findViewById(R.id.price),
                "50");
        solo.clickOnButton("Add Location");
        solo.sleep(15000); // Wait 15 seconds
        // TOUCH THE SCREEN HERE TO DROP A PIN
        solo.sleep(5000);
        solo.clickOnButton("Add Item");
        // Activity should silently finish;
    }
}
