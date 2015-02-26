package com.example.samford.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class FriendDetailActivity extends ActionBarActivity {
    /**
     * creates teh frienddetailacticity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_detail);

        User friend = FriendListActivity.getInstance().selectedFriend;

        TextView name = (TextView) findViewById(R.id.name);
        name.setText(friend.name);
        TextView email = (TextView) findViewById(R.id.email);
        email.setText("Email: " + friend.username);
        TextView rating = (TextView) findViewById(R.id.rating);
        rating.setText("Rating: " + friend.rating);
        TextView reports = (TextView) findViewById(R.id.reports);
        reports.setText("Reports: " + friend.reports);

    }

    /**
     * creates the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_detail, menu);
        return true;
    }

    /**
     * handles selected items
     * @param item
     * @return
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
     * removes a friend from the loginUser's friend list
     * @param v
     */
    public void defriend(View v) {
        LoginActivity.getInstance().loginUser.friends.remove(FriendListActivity.getInstance().selectedFriend);
        startActivity(new Intent(this, FriendListActivity.class));
    }
}
