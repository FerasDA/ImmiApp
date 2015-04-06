package com.immiapp.immiapp;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.immiapp.immiapp.Fragments.EventListFragment;

public class HomeActivity extends ActionBarActivity {

    //for testing, I'm using this to populate the list view in home instead of the database.
    private String [] eventsArray = {"first event", "second event", "third event", "fourth event",
            "fifth event", "sixth event", "seventh event", "8th event", "9th event", "10th event",
            "11th event", "12th event", "13th event", "14th event", "15th event", "16th event"};
    private ListView eventsListView;
    private ArrayAdapter arrayAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_home);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        actionBar.setHomeButtonEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab = actionBar.newTab()
                .setText("Home")
                .setTabListener(new TabListener<EventListFragment>(
        this, "Events", EventListFragment.class));
        actionBar.addTab(tab);

        tab = actionBar.newTab()
                .setText("ME")
                .setTabListener(new TabListener<EventListFragment>(
                        this, "ME", EventListFragment.class));
        actionBar.addTab(tab);


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
            case R.id.account_preferences:
                Intent i = new Intent(HomeActivity.this, PreferencesActivity.class);
                startActivity(i);
                finish();
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

    private void openPreferences() {
        Intent prefActivity = new Intent(this, PreferencesActivity.class);
        startActivity(prefActivity);
        finish();
    }

    private void openSettings() {

    }

    public static class TabListener<T extends Fragment> implements ActionBar.TabListener {
        private Fragment mFragment;
        private final Activity mActivity;
        private final String mTag;
        private final Class<T> mClass;

        public TabListener(Activity activity, String tag, Class<T> clz) {
            mActivity = activity;
            mTag = tag;
            mClass = clz;
        }

        public void onTabSelected(Tab tab, FragmentTransaction ft) {
            mFragment = mFragment.instantiate(mActivity, mClass.getName());
            ft.add(android.R.id.content, mFragment, mTag);
        }

        public void onTabUnselected(Tab tab, FragmentTransaction ft) {
            ft.detach(mFragment);
        }
        public void onTabReselected(Tab tab, FragmentTransaction ft) {

        }
    }

}
