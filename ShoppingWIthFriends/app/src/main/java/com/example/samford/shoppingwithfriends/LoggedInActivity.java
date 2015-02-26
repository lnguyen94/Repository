package com.example.samford.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class LoggedInActivity extends ActionBarActivity {
    /**
     * creates the loggedInActivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in2);
    }

    /**
     * creates the menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logged_in, menu);
        return true;
    }

    /**
     * handles selected item
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
     * goes to friend activity
     * @param v
     */
    public void friendClick(View v) {
        startActivity(new Intent(this, FriendListActivity.class));
    }
    /**
     * goes to itemlist activity
     * @param v
     */
    public void itemClick(View v) {
        startActivity(new Intent(this, ItemListActivity.class));
    }
    /**
     * goes to welcome activity
     * @param v
     */
    public void logoutClick(View v) {
        startActivity(new Intent(this, WelcomeActivity.class));
    }
}
