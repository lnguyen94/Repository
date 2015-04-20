package com.example.samford.shoppingwithfriends;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

/**
 * Controller class to view the Friend Detail page
 *
 * @author Sam
 * @version 1.0
 */
public class FriendDetailActivity extends ActionBarActivity {

    /**
     * Creates the FriendDetailActivity
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        User friend = FriendListActivity.getInstance().selectedFriend;

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(friend.getName());
        TextView email = (TextView) findViewById(R.id.email);
        email.setText("Email: " + friend.getEmail());
        TextView rating = (TextView) findViewById(R.id.rating);
        rating.setText("Rating: " + friend.getAvgRating());
        TextView reports = (TextView) findViewById(R.id.reports);
        reports.setText("Reports: " + friend.getNumOfRatings());

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
        getMenuInflater().inflate(R.menu.menu_friend_detail, menu);
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

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Removes a friend from the loginUser's friend list
     * @param v The current view of the app
     */
    public void defriend(View v) {
        User friend = FriendListActivity.getInstance().selectedFriend;
        User loginUser = LoginActivity.getInstance().loginUser;
        DatabaseHandler dbh = new DatabaseHandler(this);
        dbh.removeFriend(loginUser.getEmail(), friend.getEmail());
        loginUser.setFriends(dbh.getFriends(loginUser.getEmail()));
        finish();
    }
}
