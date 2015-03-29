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
    private Context context;
    private SQLiteDatabase db;
    private SQLiteStatement Statement;
    private static final String INSERT = "Insert into " + ACCOUNT_TABLE + "(name, password) values (?, ?)" ;

    public DatabaseHelper(Context context)
    {
        this.context = context;
        ImmiOpenHelper openHelper = new ImmiOpenHelper(this.context);
        this.db = openHelper.getWritableDatabase();
        this.Statement = this.db.compileStatement(INSERT);
    }

    public long CheckForUsername(String username)
    {
        String SEL = "SELECT count(*) FROM (SELECT * FROM " + ACCOUNT_TABLE + " WHERE '" + username + "' = " + ACCOUNT_TABLE + ".name);";

        Log.d("DB",SEL);

        SQLiteStatement stmt = db.compileStatement(SEL);
        long rtn = stmt.simpleQueryForLong();
        return rtn;
    }

    public long Insert(String name, String password)
    {
        this.Statement.bindString(1, name);
        this.Statement.bindString(2, password);
        return this.Statement.executeInsert();
    }

    public void DeleteAll() {

        this.db.delete(ACCOUNT_TABLE, null, null);
    }

    public List<String> selectAll(String username, String password) {
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

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            Log.w("Example", "Upgrading database; this will drop and recreate the tables.");
            db.execSQL("DROP TABLE IF EXISTS " + ACCOUNT_TABLE);
            onCreate(db);
        }
    }
}
