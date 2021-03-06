package com.example.samford.shoppingwithfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

/**
 * Controller class to add an item
 *
 * @author Sam
 * @version 1.0
 */
public class AddWishlistActivity extends ActionBarActivity {
    private EditText mName;
    private EditText mPrice;
    private EditText mMaxDist;
    private EditText mMinQuantity;

    /**
     * Creates the addItemActivity
     *
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mName = (EditText) findViewById(R.id.name);
        mPrice = (EditText) findViewById(R.id.price);
        mMaxDist = (EditText) findViewById(R.id.max_dist);
        mMinQuantity = (EditText) findViewById(R.id.min_quantity_rem);
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
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
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
     * Adds an item to the array
     *
     * @param v the current view of the app
     */
    public void addItem(View v) {
        if (
                mPrice.getText().toString().trim().isEmpty()
                || mName.getText().toString().trim().isEmpty()
                || mMaxDist.getText().toString().trim().isEmpty()
                || mMinQuantity.getText().toString().trim().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("All fields must be populated")
                    .setCancelable(false)
                    .setPositiveButton(
                            "OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int id) {
                                }
                            });
            AlertDialog alert = builder.create();
            alert.show();
            return;
        }

        // Build Item data
        String name = mName.getText().toString();
        double price = Double.parseDouble(mPrice.getText().toString());
        int maxDist = Integer.parseInt(mMaxDist.getText().toString());
        int minQuantityRem =
                Integer.parseInt(mMinQuantity.getText().toString());
        User loginUser = LoginActivity.getInstance().loginUser;

        if(loginUser.getEmail() == null) {
            DatabaseHandler dbh = new DatabaseHandler(this);
            dbh.addToWishlist("test", name, price, maxDist, minQuantityRem);
        }

        // Store in the database
        DatabaseHandler dbh = new DatabaseHandler(this);
        dbh.addToWishlist(loginUser.getEmail(), name, price, maxDist, minQuantityRem);
        loginUser.setWishList(dbh.getItems(loginUser.getEmail()));
        finish();
    }
}
