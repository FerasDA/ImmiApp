package com.immiapp.immiapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HomeActivity extends ActionBarActivity {
    //for testing, I'm using this to populate the list view in home instead of the database.
    private String [] eventsArray = {"first event", "second event", "third event", "fourth event", "fifth event", "sixth event", "seventh event", "8th event", "9th event", "10th event", "11th event", "12th event"};
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_pts:
                openPoints();
                return true;
            case R.id.action_today:
                openToday();
                return true;
            case R.id.action_three_days:
                openThreeDays();
                return true;
            case R.id.action_week:
                openWeek();
                return true;
            case R.id.action_search:
                openSearch();
                return true;
            case R.id.action_settings:
                openSettings();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void openPoints() {

    }

    private void openToday() {

    }

    private void openThreeDays() {

    }

    private void openWeek() {

    }

    private void openSearch() {

    }

    private void openSettings() {

    }
}
