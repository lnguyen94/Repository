package com.example.samford.shoppingwithfriends;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.content.Intent;


public class WelcomeActivity extends ActionBarActivity {
    /**
     * proceeds to the login screen
     * @param v creates view
     */
    public void loginClick(View v){
        startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));

//        Intent intent = new Intent(this, LoginActivity.class);
    }

    @Override
    /**
     * creates the welcome activity
     * @param savedInstanceState creates the Bundle
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    /**
     * creates the menus
     * @param menu the menu to create
     * @return whether this menu was created
     */
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_welcome, menu);
        return true;
    }

    @Override
    /**
     * handles menu item selection
     * @param item the item that was selected
     * @return whether it was selected
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        public PlaceholderFragment() {
        }

        @Override
        /**
         * handles when the fragment is created
         * @param inflater to inflate
         * @param container where the item is contained
         * @param savedInstanceState the bundle that is saved
         * @return the View that is generated
         */
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_welcome, container, false);
            return rootView;
        }

//        Button yourButton = (Button) findViewById(R.id.loginButton);
//
//        loginButton.setOnClickListener(new OnClickListener(){
//            public void onClick(View v){
//                startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
//            }
//        });

//        public void loginClick(View v){
//            startActivity(new Intent(WelcomeActivity.this, LoginActivity.class));
//        }

    }
}
