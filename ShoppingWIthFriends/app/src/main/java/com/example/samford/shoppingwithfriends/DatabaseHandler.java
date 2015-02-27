package com.example.samford.shoppingwithfriends;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class containing all of the methods regarding CRUD
 * on the SQLite local database
 *
 * @author Elliott
 * @version 1.0
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "ShoppingWithFriendsDB";

    private static final String TABLE_USERS = "Users";
    private static final String TABLE_FRIENDS = "Friends";
    private static final String TABLE_PRODUCTS = "Products";

    // USERS Column Names
    private static final String USERS_USERNAME = "UserName";
    private static final String USERS_EMAIL = "Email";
    private static final String USERS_PASSWORD = "Password";
    private static final String USERS_NAME = "Name";
    private static final String USERS_AVGRATING = "AvgRating";
    private static final String USERS_NUMOFRATINGS = "NumOfRatings";
    private static final String USERS_LASTLOCLAT = "LastLocLat";
    private static final String USERS_LASTLOCLONG = "LastLocLong";
    private static final String USERS_PRICETHRESH = "PriceThresh";

    // FRIENDS Column Names
    private static final String FRIENDS_USER1 = "User1";
    private static final String FRIENDS_USER2 = "User2";
    private static final String FRIENDS_RATING = "Rating";

    // PRODUCTS Column Names
    private static final String PRODUCTS_NAME = "Name";
    private static final String PRODUCTS_RECOMMENDER = "Recommernder";
    private static final String PRODUCTS_RECOMMENDEE = "Recommendee";
    private static final String PRODUCTS_PRICE = "Price";
    private static final String PRODUCTS_LOCLAT = "LocLat";
    private static final String PRODUCTS_LOCLONG = "LocLong";
    private static final String PRODUCTS_QUANTREM = "QuantRem";
    private static final String PRODUCTS_STORE = "Store";
    private static final String PRODUCTS_PRICETHRESH = "PriceThresh";
    private static final String PRODUCTS_MAXDISTANCE = "MaxDistance";
    private static final String PRODUCTS_MINQUANTREM = "MinQuantRem";
    private static final String PRODUCTS_SALEEND = "SaleEnd";

    /**
     * Creates a new DatabaseHandler class
     * @param context the context of the current application instance
     */
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * Called when the database is created for the first time.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_USERS + " ("
                + USERS_USERNAME + " TEXT PRIMARY KEY, "
                + USERS_EMAIL + " TEXT, "
                + USERS_PASSWORD + " TEXT, "
                + USERS_NAME + " TEXT, "
                + USERS_AVGRATING + " INTEGER, "
                + USERS_NUMOFRATINGS + " INTEGER, "
                + USERS_LASTLOCLAT + " REAL, "
                + USERS_LASTLOCLONG + " REAL, "
                + USERS_PRICETHRESH + " REAL"
                + ") WITHOUT ROWID;");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_FRIENDS + " ("
                + FRIENDS_USER1 + " TEXT, "
                + FRIENDS_USER2 + " TEXT, "
                + FRIENDS_RATING + " INTEGER, "
                + "FOREIGN KEY (" + FRIENDS_USER1 + ") REFERENCES "
                + TABLE_USERS + "(" + USERS_USERNAME + "),"
                + "FOREIGN KEY (" + FRIENDS_USER2 + ") REFERENCES "
                + TABLE_USERS + "(" + USERS_USERNAME + "),"
                + "PRIMARY KEY ( "
                + FRIENDS_USER1 + ", "
                + FRIENDS_USER2 + ", "
                + FRIENDS_RATING
                + ") WITHOUT ROWID;");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_PRODUCTS + " ("
                + PRODUCTS_NAME + " TEXT, "
                + PRODUCTS_RECOMMENDER + " TEXT, "
                + PRODUCTS_RECOMMENDEE + " TEXT, "
                + PRODUCTS_PRICE + " REAL, "
                + PRODUCTS_LOCLAT + " REAL, "
                + PRODUCTS_LOCLONG + " REAL, "
                + PRODUCTS_QUANTREM + " INTEGER, "
                + PRODUCTS_STORE + " TEXT, "
                + PRODUCTS_PRICETHRESH + " REAL, "
                + PRODUCTS_MAXDISTANCE + " INTEGER, "
                + PRODUCTS_MINQUANTREM + " INTEGER, "
                + PRODUCTS_SALEEND + " TEXT, "
                + "FOREIGN KEY (" + PRODUCTS_RECOMMENDER + ") REFERENCES "
                + TABLE_USERS + "(" + USERS_USERNAME + "),"
                + "FOREIGN KEY (" + PRODUCTS_RECOMMENDEE + ") REFERENCES "
                + TABLE_USERS + "(" + USERS_USERNAME + "),"
                + "PRIMARY KEY ( "
                + PRODUCTS_NAME + ", "
                + PRODUCTS_RECOMMENDER + ", "
                + PRODUCTS_RECOMMENDEE + ") "
                + "WITHOUT ROWID;");
    }

    /**
     * Called when the database needs to be upgraded.
     *
     * @param db The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FRIENDS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);

        // create new tables
        onCreate(db);
    }

    /**
     * Adds a user to the USERS table
     *
     * @param user the user to add
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long createUser(User user) {
        SQLiteDatabase db = super.getWritableDatabase();

        // First check if the User already exists
        Cursor cursor = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "
                + TABLE_USERS + " WHERE " + USERS_USERNAME
                + "=? LIMIT 1)", new String[]{user.username});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getInt(0) == 0) {
            // If the record is not there
            ContentValues values = new ContentValues();
            values.put(USERS_USERNAME, user.getUsername());
            values.put(USERS_EMAIL, user.getEmail());
            values.put(USERS_PASSWORD, user.getPassword());
            values.put(USERS_NAME, user.getName());
            values.put(USERS_AVGRATING, user.getAvgRating());
            values.put(USERS_NUMOFRATINGS, user.getNumOfRatings());
            values.put(USERS_LASTLOCLAT, user.getLastLocLat());
            values.put(USERS_LASTLOCLONG, user.getLastLocLong());

            long toReturn = db.insertOrThrow(TABLE_USERS, null, values);
            db.close();
            return toReturn;
        } else {
            // else the record is already there
            throw new UnsupportedOperationException("Username already exists");
        }
    }

    /**
     * Logs user in and populates their data object
     *
     * @param username the username entered
     * @param password the password entered
     * @return the object of the data for the user
     * @throws SQLException various possible error that could happen
     * in the method
     */
    public User login(String username, String password) throws SQLException {
        User returnUser;
        SQLiteDatabase db = super.getReadableDatabase();

        // First check if the User already exists
        Cursor cursor = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "
                        + TABLE_USERS + " WHERE " + USERS_USERNAME
                        + "=? AND " + USERS_PASSWORD + " =? LIMIT 1)",
                new String[]{username, password});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getInt(0) == 1) {
            cursor.close();
            returnUser = getBasicUserData(username);
            returnUser.setFriends(getFriends(returnUser.username));
            returnUser.setWhishlist(getWishlist(returnUser.username));

        } else {
            throw new SQLException("Credentials invalid or No user exists");
        }
        return returnUser;
    }

    /**
     * Get the data out of the USERS table for a particular user
     * (No wish list or friends list)
     *
     * @param username the username of the user to retrieve
     * @return the basic user data
     */
    public User getBasicUserData(String username) {
        SQLiteDatabase db = super.getReadableDatabase();
        User returnUser = new User();
        Cursor userCursor = db.rawQuery("SELECT * FROM "
                        + TABLE_USERS + " WHERE " + USERS_USERNAME
                        + "=? AND " + USERS_PASSWORD + " =? LIMIT 1",
                new String[]{username, password});
        returnUser.setUsername(userCursor.getString(0));
        returnUser.setEmail(userCursor.getString(1));
        returnUser.setName(userCursor.getString(3));
        returnUser.setAvgRating(userCursor.getInt(4));
        returnUser.setNumOfRatings(userCursor.getInt(5));
        returnUser.setLastLocLat(userCursor.getDouble(6));
        returnUser.setLastLocLong(userCursor.getDouble(7));
        returnUser.setPriceThresh(userCursor.getDouble(8));
        userCursor.close();
        db.close();
        return returnUser;
    }

    /**
     * Get the basic data for each of the friends of the input user
     *
     * @param userName the user whose friends will be returned
     * @return a collection of the user's friends' basic data
     */
    public List<User> getFriends(String userName) {
        SQLiteDatabase db = super.getReadableDatabase();
        ArrayList<User> returnList = new ArrayList<>();
        Cursor friendCursor = db.rawQuery("SELECT * FROM " + TABLE_USERS
                        + " RIGHT JOIN (SELECT " + FRIENDS_USER2 + " WHERE "
                        + FRIENDS_USER1 + " =?) ON " + FRIENDS_USER2 + " = "
                        + USERS_USERNAME,
                new String[]{userName});
        if (friendCursor.moveToFirst()) {
            do {
                User friend = new User();
                friend.setUserName(friendCursor.getString(0));
                friend.setEmail(friendCursor.getString(1));
                friend.setName(friendCursor.getString(3));
                friend.setAvgRating(friendCursor.getInt(4));
                friend.setNumOfRatings(friendCursor.getInt(5));
                friend.setLastLocLat(friendCursor.getInt(6));
                friend.setLastLocLong(friendCursor.getInt(7));
                friend.setPriceThresh(friendCursor.getDouble(8));
                returnList.add(friend);
            } while (friendCursor.moveToNext());
        }
        friendCursor.close();
        db.close();
        return returnList;
    }

    /**
     * Get the basic data for each of the friends of the input user
     *
     * @param userName the user whose friends will be returned
     * @return a collection of the user's friends' basic data
     */
    public List<Item> getWishlist(String userName) {
        SQLiteDatabase db = super.getReadableDatabase();
        ArrayList<Item> returnList = new ArrayList<>();
        Cursor itemCursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS
                        + "WHERE " + PRODUCTS_RECOMMENDEE + " =?",
                new String[]{userName});
        if (itemCursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setName(itemCursor.getString(0));
                item.setRecommender(itemCursor.getString(1));
                item.setRecommendee(itemCursor.getString(2));
                item.setPrice(itemCursor.getDouble(3));
                item.setLocLat(itemCursor.getDouble(4));
                item.setLocLong(itemCursor.getDouble(5));
                item.setQuantRem(itemCursor.getInt(6));
                item.setStore(itemCursor.getString(7));
                item.setPriceThresh(itemCursor.getDouble(8));
                item.setMaxDistance(itemCursor.getInt(9));
                item.setMinQuantRem(itemCursor.getInt(10));

                SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
                try {
                    Date strToDate = sdf.parse(itemCursor.getString(11));
                    item.setSaleEndDate(strToDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                returnList.add(item);
            } while (itemCursor.moveToNext());
        }
        itemCursor.close();
        db.close();
        return returnList;
    }

    /**
     * Adds an item to the PRODUCTS table for their wishlist
     *
     * @param recommendee the username of the person requesting the item
     * @param name the name of the product
     * @param priceThresh the price threshold the user is willing to pay
     * @param maxDistance the maximum distance the recomendee is willing
     *                   to travel
     * @param minQuantRem the minimum quantity remaining th recomendee wants
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addToWishlist(String recommendee, String name,
                              double priceThresh, int maxDistance,
                              int minQuantRem) {
        SQLiteDatabase db = super.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "
                        + TABLE_PRODUCTS + " WHERE " + PRODUCTS_RECOMMENDEE
                        + "=? AND " + PRODUCTS_NAME + " =? LIMIT 1)",
                new String[]{recommendee, name});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getInt(0) == 0) {
            // If the product is not there
            ContentValues values = new ContentValues();
            values.put(PRODUCTS_RECOMMENDEE, recommendee);
            values.put(PRODUCTS_NAME, name);
            values.put(PRODUCTS_PRICETHRESH, priceThresh);
            values.put(PRODUCTS_MAXDISTANCE, maxDistance);
            values.put(PRODUCTS_MINQUANTREM, minQuantRem);

            long toReturn = db.insertOrThrow(TABLE_PRODUCTS, null, values);
            db.close();
            cursor.close();
            return toReturn;
        } else {
            throw new UnsupportedOperationException(
                    "Already Wishlisted that product");
        }
    }

    /**
     * Populate a user's wishlist item with data
     *
     * @param item the Item to fill in data into the database
     * @param recommender the username of the person recommending it
     * @return the number of rows affected, which should be 1
     */
    public int reportProduct(Item item, String recommender) {
        SQLiteDatabase db = super.getWritableDatabase();

        ContentValues v = new ContentValues();
        v.put(PRODUCTS_RECOMMENDER, recommender);
        v.put(PRODUCTS_PRICE, item.getPrice());
        v.put(PRODUCTS_LOCLAT, item.getLocLat());
        v.put(PRODUCTS_LOCLONG, item.getLocLong());
        v.put(PRODUCTS_QUANTREM, item.getQuantRem());
        v.put(PRODUCTS_STORE, item.getStore());

        SimpleDateFormat df = new SimpleDateFormat("MM-DD-YYYY");
        String endDate = df.format(item.getSaleEndDate());
        v.put(PRODUCTS_SALEEND, endDate);

        int returnInt = db.update(TABLE_PRODUCTS, v, PRODUCTS_RECOMMENDEE
                        + " = ? AND " + PRODUCTS_NAME + " = ? ",
                new String[] {item.getRecommendee(), item.getName()});
        db.close();
        return returnInt;
    }

    /**
     * Add a friend Relation to the FRIENDS table
     *
     * @param currUser the username of the user currently logged in
     *                 and who want to add a friend
     * @param friendToAdd the username of the friend who will be added
     * @return the row ID of the newly inserted row, or -1 if an error occurred
     */
    public long addFriend(String currUser, String friendToAdd) {
        SQLiteDatabase db = super.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT EXISTS(SELECT 1 FROM "
                        + TABLE_FRIENDS + " WHERE " + FRIENDS_USER1
                        + "=? AND " + FRIENDS_USER2 + " =? LIMIT 1)",
                new String[]{currUser, friendToAdd});
        if (cursor != null) {
            cursor.moveToFirst();
        }
        if (cursor.getInt(0) == 0) {
            // If the product is not there
            ContentValues values = new ContentValues();
            values.put(FRIENDS_USER1, currUser);
            values.put(FRIENDS_USER2, friendToAdd);

            long toReturn = db.insertOrThrow(TABLE_USERS, null, values);
            db.close();
            cursor.close();
            return toReturn;
        } else {
            throw new UnsupportedOperationException(
                    "Already Added that friend");
        }
    }

    /**
     * Remove a friend relation from the FRIENDS table
     *
     * @param currUser the user who defriending the other user
     * @param friendToRemove the user being defriended
     * @return the number of rows affected, which should be 1
     */
    public int removeFriend(String currUser, String friendToRemove) {
        SQLiteDatabase db = super.getWritableDatabase();
        int toReturn = db.delete(TABLE_FRIENDS, FRIENDS_USER1 + " = ? AND "
                        + FRIENDS_USER2 + " = ? ",
                new String[]{currUser, friendToRemove});
        db.close();
        return toReturn;
    }

    /**
     * Find a product by multiple filters or all null for all products
     * DO ALL LOCATION/DISTANCE FILTERING CLIENT-SIDE
     *
     * @param name the name of the product
     * @param maxPrice the maximum price to look for
     * @param minQuantRem the minimum quantity there must be in stock
     * @param store the store where the item is located
     * @param maxEndDate the maximum date the user is willing to buy
     * @return the items that fit the criteria
     */
    public List<Item> findProductByCriteria(String name, Double maxPrice,
                                            Integer minQuantRem, String store,
                                            Date maxEndDate) {
        SQLiteDatabase db = super.getReadableDatabase();
        ArrayList<Item> returnList = new ArrayList<>();
        String query;
        int multAND = 0;
        ArrayList<String> params = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM "
                + TABLE_PRODUCTS);
        if (name == null && maxPrice == null && minQuantRem == null
                && store == null && maxEndDate == null) {
            query = sql.toString();
        } else {
            sql.append(" WHERE ");

            if (name != null) {
                if (sql.toString().contains("?")) {
                    sql.append(" AND ");
                }
                sql.append(PRODUCTS_NAME + " = ?");
                params.add(name);
            }

            if (maxPrice != null) {
                if (sql.toString().contains("?")) {
                    sql.append(" AND ");
                }
                sql.append(PRODUCTS_PRICE + " < ?");
                params.add(String.valueOf(maxPrice.doubleValue()));
            }

            if (minQuantRem != null) {
                if (sql.toString().contains("?")) {
                    sql.append(" AND ");
                }
                sql.append(PRODUCTS_QUANTREM + " > ?");
                params.add(String.valueOf(minQuantRem.intValue()));
            }

            if (store != null) {
                if (sql.toString().contains("?")) {
                    sql.append(" AND ");
                }
                sql.append(PRODUCTS_STORE + " = ?");
                params.add(store);
            }

            if (maxEndDate != null) {
                if (sql.toString().contains("?")) {
                    sql.append(" AND ");
                }
                sql.append("date(" + PRODUCTS_SALEEND + ") <= date(?)");
                SimpleDateFormat df = new SimpleDateFormat("MM-DD-YYYY");
                String endDate = df.format(maxEndDate);
                params.add(endDate);
            }

            query = sql.toString();
        }

        String[] qualifiers = new String[params.size()];
        for (int i = 0; i < params.size(); i++) {
            qualifiers[i] = params.get(i);
        }

        Cursor itemCursor = db.rawQuery(query, qualifiers);
        if (itemCursor.moveToFirst()) {
            do {
                Item item = new Item();
                item.setName(itemCursor.getString(0));
                item.setRecommender(itemCursor.getString(1));
                item.setRecommendee(itemCursor.getString(2));
                item.setPrice(itemCursor.getDouble(3));
                item.setLocLat(itemCursor.getDouble(4));
                item.setLocLong(itemCursor.getDouble(5));
                item.setQuantRem(itemCursor.getInt(6));
                item.setStore(itemCursor.getString(7));
                item.setPriceThresh(itemCursor.getDouble(8));
                item.setMaxDistance(itemCursor.getInt(9));
                item.setMinQuantRem(itemCursor.getInt(10));

                SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-YYYY");
                try {
                    Date strToDate = sdf.parse(itemCursor.getString(11));
                    item.setSaleEndDate(strToDate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                returnList.add(item);
            } while (itemCursor.moveToNext());
        }
        itemCursor.close();
        db.close();
        return returnList;
    }

    /**
     * Find any users who match either name or email
     *
     * @param name the name of the user to search,
     *             can be null to only look by email
     * @param email the email of the user to search,
     *              can be null to only look by name
     * @return the List of all users that match either criteria
     */
    public List<User> findUser(String name, String email) {
        if (name == null) {
            name = "";
        }
        if (email == null) {
            email = "";
        }
        SQLiteDatabase db = super.getReadableDatabase();
        ArrayList<User> returnList = new ArrayList<>();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE "
                + USERS_NAME + " = ? OR " + USERS_EMAIL + " = ? "
                , new String[]{name, email});
        if (c.moveToFirst()) {
            do {
                User user = new User();
                user.setUserName(c.getString(0));
                user.setEmail(c.getString(1));
                user.setName(c.getString(3));
                user.setAvgRating(c.getInt(4));
                user.setNumOfRatings(c.getInt(5));
                user.setLastLocLat(c.getDouble(6));
                user.setLastLocLong(c.getDouble(7));
                user.setPriceThresh(c.getDouble(8));
                returnList.add(user);
            } while (c.moveToNext());
        }
        c.close();
        db.close();
        return returnList;
    }

    /**
     * Rate a user
     *
     * @param user1 the current user
     * @param user2 the friend to rate
     * @param rating the rating t give
     * @return the number of rows affected, which should be 1
     */
    public int rateFriend(String user1, String user2, int rating) {
        SQLiteDatabase db = super.getWritableDatabase();
        ContentValues v = new ContentValues();
        v.put(FRIENDS_RATING, rating);

        int returnInt = db.update(TABLE_FRIENDS, v, FRIENDS_USER1
                        + " = ? AND " + FRIENDS_USER2 + " = ? ",
                new String[] {user1, user2});
        db.close();
        return returnInt;
    }

    /**
     * Return all registered users' usernames.
     * @return the List of the usernames
     */
    public List<String> getAllUsers() {
        SQLiteDatabase db = super.getReadableDatabase();
        ArrayList<String> returnList = new ArrayList<>();
        Cursor userCursor = db.rawQuery("SELECT " + USERS_USERNAME + " FROM "
                + TABLE_USERS, new String[]{});
        if (userCursor.moveToFirst()) {
            do {
                returnList.add(userCursor.getString(0));
            } while (userCursor.moveToNext());
        }
        userCursor.close();
        db.close();
        return returnList;
    }

    /**
     * Remove expired sales from the end date and today's date
     *
     * @return the number of rows deleted
     */
    public int wipeExpiredSales() {
        SQLiteDatabase db = super.getWritableDatabase();
        int toReturn = db.delete(TABLE_PRODUCTS, "date(" + PRODUCTS_SALEEND
                        + ") < date('now')",
                new String[]{ });
        db.close();
        return toReturn;
    }

    /**
     * Find the path of the database file on the device
     *
     * @return the file path
     */
    public String getDBFilePath() {
        SQLiteDatabase db = super.getReadableDatabase();
        String toReturn = db.getPath();
        db.close();
        return toReturn;
    }
}
