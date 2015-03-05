package com.example.samford.shoppingwithfriends;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends Activity implements LoaderCallbacks<Cursor> {

    /**
     * A dummy authentication store containing known user names and passwords.
     */
    public static ArrayList<User> users = new ArrayList<>();

    /**
     * the user that is logged in
     */
    public static User loginUser = new User();

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mNameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public Button mEmailSignInButton;
    private Context context;

    /**
     * The login activity currently in use
     */
    private static final LoginActivity instance = new LoginActivity();

    /**
     * returns the current instances of the login activity
     * @return the current login activity
     */
    public static LoginActivity getInstance() {
        return instance;
    }

    /**
     * get list of users
     * @return list of users
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * returns to the welcome screen on cancel
     * @param v the current view
     */
    public void cancelClick(View v) {
        startActivity(new Intent(this, WelcomeActivity.class));
    }

    /**
     * the setup of the view
     * @param savedInstanceState current state of the application
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        List<String> startUsers = new ArrayList<>();
        DatabaseHandler dbh = new DatabaseHandler(this);

        startUsers = dbh.getAllUsers();
//
        for (String u : startUsers) {
            User friend = dbh.getBasicUserData(u);
            if (!users.contains(friend)) {
                users.add(friend);
            }
        }

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();

        mNameView = (EditText) findViewById(R.id.name);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(
                new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id,
                                          KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });

        final Button registerExpand =
                (Button) findViewById(R.id.expand_register_button);
        registerExpand.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout l =
                        (LinearLayout) findViewById(R.id.register_layout);
                l.setVisibility(View.VISIBLE);
                registerExpand.setVisibility(View.GONE);

                //change name to register
                TextView t = (TextView) findViewById(R.id.title);
                t.setText("Register");
                Log.wtf("asdf", t.getText().toString());

                mEmailSignInButton.setText("Register");


            }
        });

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
    }

    /**
     * handles autocomplete tasks
     */
    private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mNameView.setError(null);
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        String name = mNameView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
//        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
//            mPasswordView.setError(getString(R.string.error_invalid_password));
//            focusView = mPasswordView;
//            cancel = true;
//        }

        // Check for a valid email address.
//        if (TextUtils.isEmpty(email)) {
//            mEmailView.setError(getString(R.string.error_field_required));
//            focusView = mEmailView;
//            cancel = true;
//        } else if (!isEmailValid(email)) {
//            mEmailView.setError(getString(R.string.error_invalid_email));
//            focusView = mEmailView;
//            cancel = true;
//        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(name, email, password);
            mAuthTask.execute((Void) null);
        }
    }

//    private boolean isEmailValid(String email) {
//        //TODO: Replace this with your own logic
//        return true;
//    }
//
//    private boolean isPasswordValid(String password) {
//        //TODO: Replace this with your own logic
//        return true;
//    }

    /**
     * Shows the progress UI and hides the login form.
     * @param show whether it is done
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * creates a loader
     * @param i the index of the bundle
     * @param bundle the bundle in use
     * @return where the cursor is currently
     */
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY),
                ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE
                        + " = ?",
                new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    /**
     * handles the loader finishing
     * @param cursorLoader the load of the cursor
     * @param cursor where the cursor is
     */
    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        addEmailsToAutoComplete(emails);
    }

    /**
     * handles the loader reseting
     */
    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * handles emails in the autocomplete
     * @param emailAddressCollection all possible emails
     */
    private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginActivity.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

//        public User loginUser;

        private String mName;
        private String mEmail;
        private String mPassword;
        private Context context;

        /**
         * Create a user with an email and password
         *
         * @param email the email of the user
         * @param password the password of the user
         */
        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        /**
         * Create a user with name, email, and pass
         * @param name name of the user
         * @param email email of the user
         * @param password password of the user
         */
        UserLoginTask(String name, String email, String password) {
            mName = name;
            mEmail = email;
            mPassword = password;
        }

        /**
         * tries to log in the user in the background
         */
        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            DatabaseHandler dbh = new DatabaseHandler(LoginActivity.this);

            if (mName.equals("")) {
                try {
                    loginUser = dbh.login(mEmail, mPassword);
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            } else if (!mName.equals("")) {
                //user is trying to register
                try {
                    User newUser = new User(mName, mEmail, mPassword);
                    dbh.createUser(newUser);
                    loginUser = dbh.login(mEmail, mPassword);
                    return true;
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            return false;

//            loginUser = new User(mName, mEmail, mPassword);
//            loginUser.name = mName;
//            loginUser.username = mEmail;
//            loginUser.password = mPassword;

//            if (users.contains(loginUser)) { //checks email and password
//                Log.wtf("adsf", "already in system");
//
//                loginUser = users.get(users.indexOf(loginUser));
//                return true; //user is already in the system - will log in
//            } else if (!loginUser.getName().equals("")
//                    && mName.contains("?/@:;'[{()}]!#%^*")) {
//                name field is populated - want to register
//                loginUser.setName(mName);
//                users.add(loginUser);
//                return true; //registers user and signs in
//            } else { //some kind of error
//                return false;
//            }
        }

        /**
         * proceeds to the friend list on success or returns to the login screen on failed login
         */
        @Override
        protected void onPostExecute(final Boolean success) {
//            mAuthTask = null;
            showProgress(false);


            if (success) {
                Log.wtf("loginuser", loginUser.toString());
                startActivity(new Intent(LoginActivity.this, LoggedInActivity.class));
//                finish();
            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
                startActivity(new Intent(LoginActivity.this, LoginActivity.class));
//                onCreate(new Bundle());

//                finish();

            }
        }

        /**
         * stops the progress
         */
        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
