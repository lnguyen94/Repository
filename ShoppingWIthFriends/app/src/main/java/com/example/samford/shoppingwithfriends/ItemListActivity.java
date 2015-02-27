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


public class ItemListActivity extends ActionBarActivity {

    private ListAdapter mAdapter;

    /**
     * creates the itemlistactivity
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        final User loginUser = LoginActivity.getInstance().loginUser;
        ListView list = (ListView) findViewById(R.id.item_list);
        List<Item> items = loginUser.items;
        mAdapter = new ArrayAdapter<Item>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, items);

        list.setAdapter(mAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                selectedFriend = (User) parent.getItemAtPosition(position);
            }
        });
    }

    /**
     * creates menu
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_item_list, menu);
        return true;
    }

    /**
     * handles item selected
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
     * goes to the add item activity
     * @param v
     */
    public void addItemClick(View v) {
        startActivity(new Intent(this, AddItemActivity.class));
    }
}
