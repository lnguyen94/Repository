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
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class to add a friend
 *
 * @author Sam
 * @version 1.0
 */
public class AddFriendActivity extends ActionBarActivity {

    /**
     * Creates a new AddFriendActivity
     *
     * @param savedInstanceState the saved state of the previous runtime
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ListView list = (ListView) findViewById(R.id.friend_list);
        //users.remove(loginUser);

        final User loginUser = LoginActivity.getInstance().loginUser;
        List<User> users = LoginActivity.getInstance().getUsers();
        List<User> a = new ArrayList<>();

        for (User u : users) {
            Log.wtf("-", u.toString());
            // won't add users already there or
            // the login user to the potential friend list
            if (!loginUser.getFriends().contains(u) && !loginUser.equals(u)) {
                a.add(u);
            }
        }
        ListAdapter mAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, a);

        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
//                Log.wtf("asdf", "onCLick");
//                for (User u : loginUser.friends) {
//                    Log.wtf("asdf", u.toString());
//                }
//                Log.wtf("post", "chicken");
//                final String item =
//                        (String) parent.getItemAtPosition(position);

                User friend = (User) parent.getItemAtPosition(position);

                if (!loginUser.getFriends().contains(friend)) {
                    //add person to the loginUser's friend list
                    loginUser.addFriend(friend);
                }

                startActivity(new Intent(AddFriendActivity.this,
                        FriendListActivity.class));
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
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
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
     * Returns to the FriendListActivity on clicking cancel
     *
     * @param v the current view of the app
     */
    public void cancelClick(View v) {
        startActivity(new Intent(this, FriendListActivity.class));
    }
}
