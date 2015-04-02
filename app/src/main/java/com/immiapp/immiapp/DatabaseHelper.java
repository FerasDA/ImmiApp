package com.immiapp.immiapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Feras on 3/9/2015.
 * this is modified from the
 */
public class DatabaseHelper {
    private static final String DATABASE_NAME = "Immi.db";
    private static final int DATABASE_VERSION = 1;
    private static final String ACCOUNT_TABLE = "Accounts";
    private  static final String EVENT_TABLE = "Events";
    private static final String RELATIONSHIP_TABLE = "Relationships";
    private static final String USER_SETTINGS_TABLE = "Settings";
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement Statement;
    private static final String INSERT = "Insert into " + ACCOUNT_TABLE + "(name, password) values (?, ?)" ;

    //private static final String INSERT = "Accounts_Insert into " + ACCOUNT_TABLE + "(name, password, firstname, lastname, email) values (?, ?, ?, ?, ?)" ;

    public DatabaseHelper(Context context)
    {
        this.context = context;
        ImmiOpenHelper openHelper = new ImmiOpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.Statement = this.db.compileStatement(INSERT);
    }

    //public long Accounts_CheckForUsername(String username)
    public long CheckForUsername(String username)
    {
        String SEL = "SELECT count(*) FROM (SELECT * FROM " + ACCOUNT_TABLE + " WHERE '" + username + "' = " + ACCOUNT_TABLE + ".name);";

        Log.d("DB",SEL);

        SQLiteStatement stmt = db.compileStatement(SEL);
        long rtn = stmt.simpleQueryForLong();
        return rtn;
    }

    public long Insert(String name, String password)
    //public long Accounts_Insert(String name, String password, String firstname, String lastname, String email)
    {
        this.Statement.bindString(1, name);
        this.Statement.bindString(2, password);
        /*
        Statement.bindString(1, name);
        Statement.bindString(2, password);
        Statement.bindString(3, firstname);
        Statement.bindString(4, lastname);
        Statement.bindString(5, email);
        */
        return this.Statement.executeInsert();
    }

    public void DeleteAll() {

        this.db.delete(ACCOUNT_TABLE, null, null);
    }

    public List<String> selectAll(String username, String password) {
    //public List<String> Accounts_SelectAll(String username, String password) {
        List<String> list = new ArrayList<String>();
        Cursor cursor = this.db.query(ACCOUNT_TABLE, new String[] { "name", "password" }, "name = '"+ username +"' AND password= '"+ password+"'", null, null, null, "name desc");
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(0));
                list.add(cursor.getString(1));
            } while (cursor.moveToNext());
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        return list;
    }

    private static class ImmiOpenHelper extends SQLiteOpenHelper {

        ImmiOpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + ACCOUNT_TABLE + "(id INTEGER PRIMARY KEY, name TEXT, password TEXT)");
        }
        /*
        public void onCreate(SQLiteDatabase db)
        {
            ClearAll(db);
            db.execSQL("PRAGMA foreign_keys = on");
            db.execSQL("CREATE TABLE " + ACCOUNT_TABLE + "(id INTEGER PRIMARY KEY, username TEXT, password TEXT, firstname TEXT, lastname TEXT, email TEXT)");
            db.execSQL("CREATE TABLE " + EVENT_TABLE + "(id INTEGER PRIMARY KEY, title TEXT, startday INT, startmonth INT, startyear INT, starthour INT, startminute INT," +
                    " endday INT, endmonth INT, endyear INT, endhour INT, endminute INT, locationname TEXT, address TEXT, city TEXT, zip TEXT, description TEXT, imagefile TEXT)");
            db.execSQL("CREATE TABLE " + RELATIONSHIP_TABLE + "(id INTEGER PRIMARY KEY, username TEXT, relationship TEXT, FOREIGN_KEY(username) REFERENCES " + ACCOUNT_TABLE + "(username))");
            db.execSQL("CREATE TABLE " + USER_SETTINGS_TABLE + "(id INTEGER PRIMARY KEY, username TEXT, preferredlocation TEXT, FOREIGN_KEY(username) REFERENCES " + ACCOUNT_TABLE + "(username))");
        }
        */

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
        {
            Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
            db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
            //ClearAll(db);
            onCreate(db);
        }
/*
        public void ClearAll(SQLiteDatabase db)
        {
            db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + EVENT_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + RELATIONSHIP_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + USER_SETTINGS_TABLE);
        }
        */
    }
}
