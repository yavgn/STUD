package fck2068.example.loginpage.sql;
/*
    CLASS to create SQL database, create tables etc.
        Extends the SQLiteOpenHelper
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import fck2068.example.loginpage.model.User;

public class DatabaseHelper extends SQLiteOpenHelper {
    //database version
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "UserManager.db";
    //table names for the users
    private static final String TABLE_USER = "user";
    //columns for the table
    private static final String COLUMN_ID = "ID";
    private static final String COLUMN_USER_ID = "user_ID";
    private static final String COLUMN_USER_EMAIL = "user_email";
    private static final String COLUMN_USER_PASSWORD = "user_password";
    //SQL query to create USER TABLE
    private String CREATE_USER_TABLE = "CREATE TABLE "+TABLE_USER+"("
            +COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +COLUMN_USER_ID+" TEXT,"
            +COLUMN_USER_EMAIL+" TEXT,"
            +COLUMN_USER_PASSWORD+" TEXT"+")";
    //SQL query to DROP table (Corresponds to CREATE)
    private String DROP_USER_TABLE = "DROP TABLE IF EXISTS "+TABLE_USER;
    //Database Helper constructor
    public DatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    //method to EXECUTE the query when class is created. *needs to be overwritten*. Pass in the SQLite Database, then call the execSQL method
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_USER_TABLE);
    }
    //method to UPGRADE, necessary and *needs to be overridden*. Takes in Database, old and new version, drops the table and recreates it
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL(DROP_USER_TABLE);
        onCreate(db);
    }
    //method to ADD USERs to the DB. Imports the User from the model package. Does not need to put(COLUMN_USER_ID) as this auto increments when a USER is added to the DB
    public void addUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USER_ID, user.getUserName());
        values.put(COLUMN_USER_EMAIL, user.getEmail());
        values.put(COLUMN_USER_PASSWORD, user.getPassword());

        db.insert(TABLE_USER, null, values);
        db.close();
    }
    //method to CHECK for a USER. Aids validation
    public boolean checkUser(String email){
        String [] columns ={
                COLUMN_ID
        };
        //call SQLite DB
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?";
        String[] selectionArgs = {email};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }
    //check for USER when email and password is passed in. Follows same format as the checkUser method however, it takes in an email and password
    public boolean checkUser(String email, String password){
        String [] columns ={
                COLUMN_ID
        };
        //call SQLite DB
        SQLiteDatabase db = this.getWritableDatabase();
        String selection = COLUMN_USER_EMAIL + " = ?" + " AND "+ COLUMN_USER_PASSWORD + " = ?";
        String[] selectionArgs = {email, password};

        Cursor cursor = db.query(TABLE_USER, columns, selection, selectionArgs, null, null, null);
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();
        if(cursorCount > 0){
            return true;
        }
        return false;
    }
}
