package com.example.samford.shoppingwithfriends;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
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
public class ItemDetailActivity extends ActionBarActivity {

    /**
     * Creates the ItemDetailActivity
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        Item item = ItemListActivity.getInstance().selectedItem;

        TextView name = (TextView) findViewById(R.id.name);
        name.setText("Name: " + item.getName());
        TextView price = (TextView) findViewById(R.id.price);
        price.setText("Price: " + item.getPrice());
        TextView distance = (TextView) findViewById(R.id.distance);
        //distance.setText("Distance: " + item.getMaxDistance());

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
        getMenuInflater().inflate(R.menu.menu_item_detail, menu);
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
     * Removes a friend from the loginUser's item list
     * @param v The current view of the app
     */
    public void removeItem(View v) {
        Item selectedItem = ItemListActivity.getInstance().selectedItem;
        User loginUser = LoginActivity.getInstance().loginUser;
        DatabaseHandler dbh = new DatabaseHandler(this);
//        //
//        //need dbh method to remove item
//        //


        dbh.removeItem(loginUser.getEmail(), selectedItem);
        loginUser.setWishList(dbh.getItems(loginUser.getEmail()));
        startActivity(new Intent(this, ItemListActivity.class));
    }

    /**
     * goes to welcome activity
     * @param v The current view of the app
     */
    public void logoutClick(View v) {
        Context context = getApplicationContext();
        Intent intent = new Intent(context, WelcomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        context.startActivity(intent);
    }

    public void showLocation(View v) {
        startActivity(new Intent(this, ShowMapActivity.class));
    }
}
