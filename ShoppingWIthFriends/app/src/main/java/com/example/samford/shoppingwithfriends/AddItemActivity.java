package com.example.samford.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class AddItemActivity extends ActionBarActivity {
    private EditText mName, mPrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        mName = (EditText) findViewById(R.id.name);
        mPrice = (EditText) findViewById(R.id.price);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

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
     *
     * @param v
     */
    public void addItemClick(View v){
        String name = mName.getText().toString();
        int price = Integer.parseInt(mPrice.getText().toString());// parses int
        Item item = new Item(name, price);
        User loginUser = LoginActivity.getInstance().loginUser;
        loginUser.addItem(item);
        startActivity(new Intent(this, FriendListActivity.class));
    }
}
