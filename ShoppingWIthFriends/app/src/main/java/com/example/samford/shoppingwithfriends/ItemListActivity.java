package com.example.samford.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * Controller class to view the Item List
 *
 * @author Sam
 * @version 1.0
 */
public class ItemListActivity extends ActionBarActivity {

    private static final ItemListActivity instance = new ItemListActivity();
    public static Item selectedItem = new Item();

    /**
     * Gets the instance of this class
     *
     * @return the instance
     */
    public static ItemListActivity getInstance() {
        return instance;
    }

    private ListAdapter mAdapter;
    /**
     * Creates a new ItemListActivity
     *
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        final User loginUser = LoginActivity.getInstance().loginUser;
        ListView list = (ListView) findViewById(R.id.item_list);
        List<Item> items = loginUser.getItems();
        mAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);

        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {

                selectedItem = (Item) parent.getItemAtPosition(position);
                startActivity(new Intent(ItemListActivity.this,
                        ItemDetailActivity.class));
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
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    /**
     * Handles an item selected
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
     * Goes to the add item activity
     * @param v The current view of the app
     */
    public void addItemClick(View v) {
        startActivity(new Intent(this, AddItemActivity.class));
    }
}
