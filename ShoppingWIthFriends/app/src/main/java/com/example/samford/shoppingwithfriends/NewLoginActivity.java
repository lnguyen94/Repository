package com.example.samford.shoppingwithfriends;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class NewLoginActivity extends ActionBarActivity {

    public static ArrayList<User> users = new ArrayList<>();
    private EditText mNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_login);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mNameView = (EditText) findViewById(R.id.name);

        mPasswordView = (EditText) findViewById(R.id.password);
//        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
//            @Override
//            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
//                if (id == R.id.login || id == EditorInfo.IME_NULL) {
//                    return true;
//                }
//                return false;
//            }
//        });



        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = mEmailView.getText().toString();
                String password = mPasswordView.getText().toString();
                String name = mNameView.getText().toString();
                attemptLogin(name, username, password);
            }
        });

        final Button registerExpand = (Button) findViewById(R.id.expand_register_button);
        registerExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l = (LinearLayout) findViewById(R.id.register_layout);
                l.setVisibility(View.VISIBLE);
                registerExpand.setVisibility(View.GONE);
                TextView t = (TextView) findViewById(R.id.title);
                t.setText("Register");
                Log.wtf("asdf", t.getText().toString());

                mEmailSignInButton.setText("Register");

            }
        });



    }


    public void attemptLogin(String name, String username, String password) {
        User loginUser = new User(name, username, password);
        if (users.contains(loginUser)) { //checks email and password
            Log.wtf("adsf", "already in system");
            startActivity(new Intent(this, LoggedInActivity.class));
//            return true; //user is already in the system - will log in
        }
        else if (!loginUser.name.equals("")) { //name field is populated - want to register
            loginUser.name = name;
            users.add(loginUser);
            for (User u : users) {
                Log.wtf("asdf", "elseif user in users: " + loginUser.name + " - " + loginUser.username + " - " + loginUser.password);
            }
            Log.d("adsf", "added to array");
            startActivity(new Intent(this, LoggedInActivity.class));
//            return true; //registers user and signs in
        } else { //some kind of error
            //
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_login, menu);
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
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_new_login, container, false);
            return rootView;
        }
    }
}
