package com.immiapp.immiapp;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.immiapp.immiapp.Tabs.FilterFragment;

import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.widget.TextView;

import com.immiapp.immiapp.Tabs.FilterFragment;
import com.immiapp.immiapp.events.EventsMain;
import com.immiapp.immiapp.filter.FilterMain;
import com.immiapp.immiapp.more.MoreMain;

public class Main extends ActionBarActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private TextView mLatitudeText;
    private TextView mLongitudeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab tab = actionBar.newTab().setText("Home");
        Fragment t1 = new EventsMain();
        tab.setTabListener(new TabListener(t1));
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Filter");
        Fragment t2 = new FilterMain();
        tab.setTabListener(new TabListener(t2));
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("Me");
        Fragment t3 = new EventsMain();
        tab.setTabListener(new TabListener(t3));
        actionBar.addTab(tab);

        tab = actionBar.newTab().setText("More");
        Fragment t4 = new MoreMain();
        tab.setTabListener(new TabListener(t4));
        actionBar.addTab(tab);
        //this is added for getting current location but not working yet
        buildGoogleApiClient();
    }
//this is added for getting current location but not working yet
    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
    }
    //this is added for getting current location but not working yet
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    //this is added for getting current location but not working yet
    @Override
    public void onConnected(Bundle connectionHint) {
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
            mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
        }
    }
    //this is added for getting current location but not working yet
    @Override
    public void onConnectionSuspended(int i) {

    }
    //this is added for getting current location but not working yet
    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {

    }

    public static class TabListener implements ActionBar.TabListener {
        private Fragment mFragment;

        public TabListener(Fragment fragment) {
            mFragment = fragment;
        }

        @Override
        public void onTabReselected(ActionBar.Tab arg0, android.support.v4.app.FragmentTransaction ft) {
            //Do nothing
        }

        @Override
        public void onTabSelected(ActionBar.Tab arg0, android.support.v4.app.FragmentTransaction ft) {
            ft.replace(android.R.id.content, mFragment, null);
        }

        @Override
        public void onTabUnselected(ActionBar.Tab arg0, android.support.v4.app.FragmentTransaction ft) {
            ft.remove(mFragment);
        }

    }

}
