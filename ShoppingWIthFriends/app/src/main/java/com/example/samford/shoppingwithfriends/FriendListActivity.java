package com.example.samford.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


import java.util.List;

/**
 * Controller class to view the Friend List
 *
 * @author Sam
 * @version 1.0
 */
public class FriendListActivity extends ActionBarActivity {
    private ListAdapter mAdapter;
    private static final FriendListActivity instance = new FriendListActivity();
    public static User selectedFriend = new User();

    /**
     * Get the instance of the Activity
     *
     * @return the instance
     */
    public static FriendListActivity getInstance() {
        return instance;
    }

    /**
     * Creates a new FriendListActivity
     *
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        final User loginUser = LoginActivity.getInstance().loginUser;
        List<User> friends = loginUser.friends;
        ListView list = (ListView) findViewById(R.id.friend_list);
        mAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                android.R.id.text1, friends);

        list.setAdapter(mAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                selectedFriend = (User) parent.getItemAtPosition(position);
                Log.wtf("selectedFriend", selectedFriend.toString());
                startActivity(new Intent(FriendListActivity.this,
                        FriendDetailActivity.class));
            }
        });
    }

    /**
     * Initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which items are placed.
     * @return return true for the menu to be displayed;
     *         return false means it will not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
        return true;
    }

    /**
     * Handles an item selected from a menu
     *
     * @param item the item selected
     * @return boolean Return false to allow normal menu processing to
     *         proceed, true to consume it here.
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Returns to the welcome screen on click
     * @param v The current view of the app
     */
    public void logoutClick(View v) {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    /**
     * goes to the add friend screen on click
     * @param v The current view of the app
     */
    public void addFriendClick(View v) {
        startActivity(new Intent(this, AddFriendActivity.class));
    }
}
