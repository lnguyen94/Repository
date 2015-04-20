package com.example.samford.shoppingwithfriends;

import android.app.Activity;
import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;

import com.robotium.solo.Solo;

/**
 * Test class for a list of items
 *
 * @author Mika
 * @version 1.0
 */
public class BackButtonTest extends ActivityInstrumentationTestCase2<LoggedInActivity>  {

    private LoggedInActivity activity;
    private Solo solo;

    public BackButtonTest() {
        super(LoggedInActivity.class);
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
        solo.assertCurrentActivity("wrong activity", LoggedInActivity.class);
    }

    /**
     * Tests going back with one window
     */
    @LargeTest
    public void testBackButton() {
            Instrumentation.ActivityMonitor ItemListActivityMon = getInstrumentation().addMonitor(ItemListActivity.class.getName(), null, false);
            Instrumentation.ActivityMonitor LoggedInMon = getInstrumentation().addMonitor(LoggedInActivity.class.getName(), null, false);
            solo.clickOnButton("Items");

            Activity nextActivity = getInstrumentation().waitForMonitorWithTimeout(ItemListActivityMon, 3000);
            // next activity is opened and captured.
            assertNotNull(nextActivity);
            nextActivity.finish();

            solo.goBack();
            Activity nextActivityBack = getInstrumentation().waitForMonitorWithTimeout(LoggedInMon, 3000);
            assertNotNull(nextActivityBack);
    }
}
