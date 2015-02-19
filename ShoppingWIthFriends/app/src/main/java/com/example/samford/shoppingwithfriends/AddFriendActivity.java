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

import java.util.ArrayList;
import java.util.List;

public class AddFriendActivity extends ActionBarActivity {

    @Override
    /**
     * creates a new addfriendactivity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);

        ListView list = (ListView) findViewById(R.id.friend_list);
//        users.remove(loginUser);


        final User loginUser = LoginActivity.getInstance().loginUser;
        List<User> users = LoginActivity.getInstance().getUsers();
        List<User> a = new ArrayList<>();


        for (User u : users) {
            Log.wtf("-", u.toString());
            if (!loginUser.friends.contains(u)) {
                a.add(u);
            }
        }
        ListAdapter mAdapter = new ArrayAdapter<User>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, a);

        list.setAdapter(mAdapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.wtf("asdf", "onCLick");
//                for (User u : loginUser.friends) {
//                    Log.wtf("asdf", u.toString());
//                }
//                Log.wtf("post", "chicken");

//                final String item = (String) parent.getItemAtPosition(position);

                User friend = (User) parent.getItemAtPosition(position);

                if (!loginUser.friends.contains(friend)) {
                    loginUser.friends.add(friend); //add person to the loginUser's friend list
                }


                startActivity(new Intent(AddFriendActivity.this, FriendListActivity.class));
            }
        });
    }

    @Override
    /**
     * creates a new menu
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_friend_list, menu);
        return true;
    }

    @Override
    /**
     * handles an item selected from a menu
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
     * returns to the FriendListActivity on clicking cancel
     * @param v
     */
    public void cancelClick(View v){
        startActivity(new Intent(this, FriendListActivity.class));
    }
}
