package com.immiapp.immiapp.events;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.util.Linkify;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;

public class EventActivityShow extends Activity {
    private DatabaseHandler db;
    private Event event;
    private int id;
    private TextView txtTitle, txtDescription, txtDate, txtTime, txtLocation, txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_show);

        txtTitle = (TextView)findViewById(R.id.title);
        txtDescription = (TextView)findViewById(R.id.description);
        txtDate = (TextView)findViewById(R.id.date);
        txtTime = (TextView)findViewById(R.id.time);
        txtLocation = (TextView)findViewById(R.id.textViewLocation);
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
    }

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
