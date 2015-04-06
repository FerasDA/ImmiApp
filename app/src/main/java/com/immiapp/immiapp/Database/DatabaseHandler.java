package com.immiapp.immiapp.Database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.immiapp.immiapp.events.Event;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final String DB_NAME = "Agenda_ImmiApp.db";
    private static final int DB_VERSION = 1;

    //table names
    private static final String TABLE_EVENTS = "Events";
//    private static final String TABLE_CATEAGORIES = "Categories";
//    private static final String TABLE_TOPIC = "Topicos";
//    private static final String TABLE_CALIFICATIONS = "Calificaciones";

    //fields from Events table
    public static final String KEY_ROWID_EVENTS = "_id";
    public static final String KEY_EVENT_TITLE = "Title";
    public static final String KEY_DESCRIPTION = "Description";
    public static final String KEY_DATE = "Date";
    public static final String KEY_TIME = "Time";
    public static final String KEY_LOCATION = "Location";
    public static final String KEY_CATEGORY = "Category";

    //String whit the SQL instruction Events table
    private static final String CREATE_TABLE_EVENTS = "create table " +
            TABLE_EVENTS + " (" +
            KEY_ROWID_EVENTS + " integer primary key autoincrement, " +
            KEY_EVENT_TITLE + " text not null, " +
            KEY_DESCRIPTION + " text not null, " +
            KEY_DATE + " text not null, " +
            KEY_TIME + " text not null," +
            KEY_LOCATION +" text not null," +
            KEY_CATEGORY + " text not null );";



    private String[] columnsEvents = new String[]{KEY_ROWID_EVENTS,KEY_EVENT_TITLE,KEY_DESCRIPTION,
            KEY_DATE,KEY_TIME,KEY_LOCATION, KEY_CATEGORY};



    public DatabaseHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EVENTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    /****************************** BEGIN EVENTS METHODS ******************************************/
    public List<Event> getAllEvents(){
        SQLiteDatabase db = this.getWritableDatabase();
        List<Event> eventList = new ArrayList<Event>();
        Cursor cursor = db.query(true, TABLE_EVENTS, columnsEvents, null, null, null, null, null, null, null);

        if(cursor.moveToFirst()){
            do{
                Event event = new Event();
                event.setID(cursor.getInt(cursor.getColumnIndex(KEY_ROWID_EVENTS)));
                event.setTitle(cursor.getString(cursor.getColumnIndex(KEY_EVENT_TITLE)));
                event.setDescription(cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)));
                event.setLocation(cursor.getString(cursor.getColumnIndex(KEY_LOCATION)));
                event.setTime(cursor.getString(cursor.getColumnIndex(KEY_TIME)));
                event.setDate(cursor.getString(cursor.getColumnIndex(KEY_DATE)));
                event.setCategory(cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));

                eventList.add(event);
            }while(cursor.moveToNext());
        }
        db.close();
        return eventList;
    }

    public long addEvent(Event event) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        values.put(KEY_DESCRIPTION, event.getDescription());
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_TIME, event.getTime());
        values.put(KEY_LOCATION, event.getLocation());
        values.put(KEY_CATEGORY, event.getCategory());

        long id = db.insert(TABLE_EVENTS, null, values);
        db.close();
        return id;
    }

    public Event getEventsById(int id) throws SQLException {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(true, TABLE_EVENTS, columnsEvents, KEY_ROWID_EVENTS + "=" + id, null, null, null, null, null, null);

        //move to first register of the row
        if(cursor != null)
            cursor.moveToFirst();
        Event event = new Event(cursor.getInt(cursor.getColumnIndex(KEY_ROWID_EVENTS)),
                cursor.getString(cursor.getColumnIndex(KEY_EVENT_TITLE)),
                cursor.getString(cursor.getColumnIndex(KEY_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(KEY_LOCATION)),
                cursor.getString(cursor.getColumnIndex(KEY_TIME)),
                cursor.getString(cursor.getColumnIndex(KEY_DATE)),
                cursor.getString(cursor.getColumnIndex(KEY_CATEGORY)));
        db.close();
        return event;
    }

    public long updateEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_EVENT_TITLE, event.getTitle());
        values.put(KEY_DESCRIPTION, event.getDescription());
        values.put(KEY_DATE, event.getDate());
        values.put(KEY_TIME, event.getTime());
        values.put(KEY_LOCATION, event.getLocation());
        values.put(KEY_CATEGORY, event.getCategory());

        long result = db.update(TABLE_EVENTS, values, KEY_ROWID_EVENTS+" = ?", new String[]{String.valueOf(event.getID())});
        db.close();
        return result;
    }

    public void deleteEvent(Event event){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_EVENTS, KEY_ROWID_EVENTS+" = ? ", new String[]{String.valueOf(event.getID())});
        db.close();
    }


    public Cursor getMatchingStatesEvent(CharSequence constraint){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor;
        if (constraint == null  ||  constraint.length () == 0)  {
            //  Return the full list
            cursor = db.query(true, TABLE_EVENTS, columnsEvents, null, null, null, null, null, null, null);
            db.close();
            return cursor;
        }  else  {
            String value = "%"+constraint.toString()+"%";
            cursor = db.query(TABLE_EVENTS, columnsEvents, "Event like ?", new String[]{value} ,null, null, null);
            db.close();
            return cursor;
        }
    }

/********************************** END EVENTS METHODS **************************************************************/

}
