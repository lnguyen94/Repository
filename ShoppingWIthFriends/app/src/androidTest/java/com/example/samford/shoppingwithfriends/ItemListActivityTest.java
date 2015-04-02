package com.example.samford.shoppingwithfriends;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.LargeTest;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.EditText;

import com.robotium.solo.Solo;

/**
 * Test class for a list of items
 *
 * @author Mika
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
     * Tests opening list of items
     */
    @LargeTest
    public void testItemList() {
        getInstrumentation().waitForIdleSync();
        setActivityInitialTouchMode(true);
        ListView list = solo.getCurrentListViews().get(0);
        for(int i = 0; i < list.getAdapter().getCount(); i++){
            solo.clickOnView(getViewAtIndex(list, i, getInstrumentation()))
            //solo.assertCurrentActivity("Json Class", JsonActivity.class);
            solo.goBack();
        }
        // Activity should silently finish;
    }

}
