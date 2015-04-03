package com.immiapp.immiapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends Activity {
    //for testing, I'm using this to populate the list view in home instead of the database.
    private String [] eventsArray = {"first event", "second event", "third event", "fourth event"};
    private ListView eventsListView;
    private ArrayAdapter arrayAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d("home", "onCreate");

        eventsListView = (ListView) findViewById(R.id.events_list);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, eventsArray);
        eventsListView.setAdapter(arrayAdapter);
    }

}
