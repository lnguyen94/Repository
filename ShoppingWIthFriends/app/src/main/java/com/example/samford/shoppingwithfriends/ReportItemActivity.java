package com.example.samford.shoppingwithfriends;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.sql.SQLException;

/**
 * Controller class to add an item
 *
 * @author Sam
 * @version 1.0
 */
public class ReportItemActivity extends ActionBarActivity {
    private EditText mName;
    private EditText mPrice;

    /**
     * Creates the addItemActivity
     *
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_item);
        mName = (EditText) findViewById(R.id.name);
        mPrice = (EditText) findViewById(R.id.price);
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
        if (mPrice.getText().toString().trim().isEmpty()
                || mName.getText().toString().trim().isEmpty()) {
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
        double lat;
        double lng;
        if (MapActivity.getLoc() != null) {
            lat = MapActivity.getLoc().latitude;
            lng = MapActivity.getLoc().longitude;
        } else {
            lat = 33.777512;
            lng = -84.397211;
        }

        String name = mName.getText().toString();

        // parse the int
        double price = Double.parseDouble(mPrice.getText().toString());
//        Item item = new Item(name, price);
        User loginUser = LoginActivity.getInstance().loginUser;
        Item item = new Item(name, price);
        item.setLocLat(lat);
        item.setLocLong(lng);
        DatabaseHandler dbh = new DatabaseHandler(this);
        try {
            dbh.reportProduct(item, loginUser.getEmail());
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        loginUser.setWishList(dbh.getItems(loginUser.getEmail()));
        finish();
    }

    /**
     * Adds the location
     * @param v the view of the button
     */
    public void addLocation(View v) {
        startActivity(new Intent(this, MapActivity.class));
    }
}
