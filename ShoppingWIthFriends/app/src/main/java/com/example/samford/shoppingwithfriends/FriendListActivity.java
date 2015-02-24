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

import com.example.samford.shoppingwithfriends.R;

import java.util.List;

public class FriendListActivity extends ActionBarActivity {

//    private static List<User> users = LoginActivity.getInstance().getUsers();
//    public static User loginUser = LoginActivity.getInstance().loginUser;
//    public List<User> friends = loginUser.friends;
    private ListAdapter mAdapter;

    private static final FriendListActivity instance = new FriendListActivity();
    public static User selectedFriend = new User();

    public static FriendListActivity getInstance() {
        return instance;
    }

    @Override
    /**
     * creates a new FriendListActivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_list);

        final User loginUser = LoginActivity.getInstance().loginUser;
        List<User> friends = loginUser.friends;
        ListView list = (ListView) findViewById(R.id.friend_list);
        mAdapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, friends);

        list.setAdapter(mAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                selectedFriend = (User) parent.getItemAtPosition(position);
                Log.wtf("selectedFriend", selectedFriend.toString());
                startActivity(new Intent(FriendListActivity.this, FriendDetailActivity.class));
            }
        });

    }


    @Override
    /**
     * creates a new menu option
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
        return true;
    }

    @Override
    /**
     * allows selecting options from a menu
     */
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
     * returns to the welcome screen on click
     * @param v
     */
    public void logoutClick(View v){
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    /**
     * goes to the add friend screen on click
     * @param v
     */
    public void addFriendClick(View v){
        startActivity(new Intent(this, AddFriendActivity.class));
    }
}
