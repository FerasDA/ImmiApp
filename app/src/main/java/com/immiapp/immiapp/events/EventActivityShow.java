package com.immiapp.immiapp.events;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;
import android.location.Address;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class EventActivityShow extends Activity {
    private DatabaseHandler db;
    private Event event;
    private int id;
    private String address;
    private TextView txtTitle, txtDescription, txtDate, txtTime, txtLocation, txtCategory;
    private GoogleMap map;
    double latitude;
    double longitude;
    List<Address> geocodeMatches = null;
    private static final int GPS_ERRORDIALOG_REQUEST = 9001;


    private static LatLng LAT_LNG;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (servicesOK()) {
            setContentView(R.layout.activity_events_show);
        } else {
            setContentView(R.layout.activity_main);

        }

        txtTitle = (TextView)findViewById(R.id.title);
        txtDescription = (TextView)findViewById(R.id.description);
        txtDate = (TextView)findViewById(R.id.date);
        txtTime = (TextView)findViewById(R.id.time);
        txtLocation = (TextView)findViewById(R.id.location);
        txtCategory = (TextView)findViewById(R.id.category);

        db = new DatabaseHandler(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle == null) return;

        if (bundle.containsKey("isNotification")) {

        }

        if(bundle.containsKey(db.KEY_ROWID_EVENTS)){
            id = bundle.getInt(db.KEY_ROWID_EVENTS);
            consult();
        }

        setTitle("Event - " + event.getTitle());

        try {
            geocodeMatches = new Geocoder(this).getFromLocationName(address, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (!geocodeMatches.isEmpty())
        {
            latitude = geocodeMatches.get(0).getLatitude();
            longitude = geocodeMatches.get(0).getLongitude();
        }

        map = ((MapFragment) getFragmentManager().findFragmentById(R.id.show_map)).getMap();
        if (map != null) {
            Marker loc = map.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(address));
            LAT_LNG = new LatLng(latitude, longitude);
            for (int i = 0; i < 10; i++) {
                for(int j = 0; j < 10; j++) {
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(LAT_LNG, i+j));
                }
            }
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(LAT_LNG, 10));
        }
    }

    private boolean servicesOK() {
        int isAvailable = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);

        if (isAvailable == ConnectionResult.SUCCESS) {

            return true;

        } else if (GooglePlayServicesUtil.isUserRecoverableError(isAvailable)) {

            Dialog dialog = GooglePlayServicesUtil.getErrorDialog(isAvailable,
                    this, GPS_ERRORDIALOG_REQUEST);
            dialog.show();

        } else {

            Toast.makeText(this, "Cant connect!!", Toast.LENGTH_SHORT).show();

        }
        return false;
    }
//    public boolean isNetworkAvailable() {
//        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetworkInfo = connectivityManager
//                .getActiveNetworkInfo();
//        return activeNetworkInfo != null;
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_secondary, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_edit_secondary:
                intent = new Intent(EventActivityShow.this, EventActivityEdit.class);
                intent.putExtra(db.KEY_ROWID_EVENTS, id);
                startActivity(intent);
                return true;
            case R.id.action_delete_secondary:
                alertDialog();
                return true;
            default:
                break;
        }
        return false;
    }


    private void consult() {
        event = db.getEventsById(id);

        txtTitle.setText(event.getTitle());
        txtDescription.setText(event.getDescription());
        Linkify.addLinks(txtDescription, Linkify.PHONE_NUMBERS | Linkify.EMAIL_ADDRESSES | Linkify.WEB_URLS);
        txtLocation.setText(event.getLocation());
        txtDate.setText(event.getDate());
        txtTime.setText(event.getTime());
        txtCategory.setText(event.getCategory());
        address = event.getLocation();

        for (int i = 0; i < 10000; i++) {
            for(int j = 0; j < 10000; j++) {
                float f = i * j;
            }
        }
    }

    public void alertDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("About to remove the event: " + event.getTitle())
                .setTitle("remove event")
                .setCancelable(false)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setPositiveButton("Accept", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        delete();
                    }
                });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void delete(){
        db.deleteEvent(event);
        this.finish();
    }
}
