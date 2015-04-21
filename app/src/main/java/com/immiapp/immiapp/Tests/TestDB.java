package com.immiapp.immiapp.Tests;

import com.immiapp.immiapp.Date;
import com.immiapp.immiapp.events.Event;
import java.util.List;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.UserHandle;
import android.support.annotation.Nullable;
import android.test.InstrumentationTestCase;
import android.view.Display;

import com.immiapp.immiapp.Database.DatabaseHandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Ben on 4/20/2015.
 */
public class TestDB extends InstrumentationTestCase {
    public void testDBInstantiation() throws Exception {
        DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getTargetContext());
    }

    public void testDBAddEvent1() throws Exception {
        DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getTargetContext());

        List<Event> events = dbh.getAllEvents();

        for(int i = 0; i < events.size(); i++) {
            dbh.deleteEvent(events.get(i));
        }

        Event event = new Event(1, "event", "Just a test event", "Test Location","10:15 am", "April 20, 2015", "Test");

        dbh.addEvent(event);

        Event response = dbh.getAllEvents().get(0);

        String title = response.getTitle();
        String description = response.getDescription();
        String location = response.getLocation();
        String time = response.getTime();
        String date = response.getDate();
        String category = response.getCategory();

        assertEquals(title,"event");
        assertEquals(description,"Just a test event");
        assertEquals(location,"Test Location");
        assertEquals(time,"10:15 am");
        assertEquals(date,"April 20, 2015");
        assertEquals(category,"Test");
    }

    public void testDBAddEvent2() throws Exception {
        DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getTargetContext());

        List<Event> events = dbh.getAllEvents();

        for(int i = 0; i < events.size(); i++) {
            dbh.deleteEvent(events.get(i));
        }

        Event event = new Event(1, "$!#%$%!", "!%!$%!$%", "*)(&($&!><",";!$#%", "!@#$%^&*", "`!~`1");

        dbh.addEvent(event);

        Event response = dbh.getAllEvents().get(0);

        String title = response.getTitle();
        String description = response.getDescription();
        String location = response.getLocation();
        String time = response.getTime();
        String date = response.getDate();
        String category = response.getCategory();

        assertEquals(title,"$!#%$%!");
        assertEquals(description,"!%!$%!$%");
        assertEquals(location,"*)(&($&!><");
        assertEquals(time,";!$#%");
        assertEquals(date,"!@#$%^&*");
        assertEquals(category,"`!~`1");
    }

    public void testDBAddEvent3() throws Exception {
        DatabaseHandler dbh = new DatabaseHandler(getInstrumentation().getTargetContext());

        List<Event> events = dbh.getAllEvents();

        for(int i = 0; i < events.size(); i++) {
            dbh.deleteEvent(events.get(i));
        }

        Event event = new Event(1,"DROP TABLE", "DROP TABLE", "DROP TABLE", "DROP TABLE", "DROP TABLE", "DROP TABLE");

        dbh.addEvent(event);

        Event response = dbh.getAllEvents().get(0);

        String title = response.getTitle();
        String description = response.getDescription();
        String location = response.getLocation();
        String time = response.getTime();
        String date = response.getDate();
        String category = response.getCategory();

        assertEquals(title,"DROP TABLE");
        assertEquals(description,"DROP TABLE");
        assertEquals(location,"DROP TABLE");
        assertEquals(time,"DROP TABLE");
        assertEquals(date,"DROP TABLE");
        assertEquals(category,"DROP TABLE");
    }
}

