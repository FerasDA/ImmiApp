package com.immiapp.immiapp.events;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Scroller;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.R;

import java.util.Calendar;
import java.util.Date;


public class EventActivityAdd extends ActionBarActivity implements View.OnClickListener {

    DatabaseHandler db;
    EditText name,description, location, category;
    Button defTime, defDate, defCategory;
    TextView date, hour;
    Calendar datetime;
    long 		id;
    int			day, month, year, hours, minutes;
    static final int TIME_DIALOG_ID = 998;
    static final int DATE_DIALOG_ID = 999;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int yr, int monthOfYear, int dayOfMonth) {
            day = dayOfMonth;
            month = monthOfYear;
            year = yr;
            date.setText(day+"/"+(month+1)+"/"+year);
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hours = hourOfDay;
            minutes = minute;

            datetime = Calendar.getInstance();
            datetime.set(Calendar.MONTH, month);
            datetime.set(Calendar.DAY_OF_MONTH, day);
            datetime.set(Calendar.YEAR, year);
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            updateTime();
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_add);
        setTitle("Insert Event");

        name = (EditText)findViewById(R.id.eventTitle);
        description = (EditText)findViewById(R.id.eventDescription);
        location = (EditText)findViewById(R.id.eventLocation);
        date = (TextView)findViewById(R.id.txtDate);
        hour = (TextView)findViewById(R.id.txtTime);
        defDate = (Button)findViewById(R.id.defDate);
        defTime = (Button)findViewById(R.id.defTime);
        defCategory = (Button)findViewById(R.id.defCategory);

        description.setScroller(new Scroller(getApplicationContext()));
        description.setVerticalScrollBarEnabled(true);

        Calendar c = Calendar.getInstance();
        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);
        year = c.get(Calendar.YEAR);
        hours = c.get(Calendar.HOUR_OF_DAY);
        minutes = c.get(Calendar.MINUTE);

        updateTime();

        date.setText(day+"/"+(month+1)+"/"+year);

        db = new DatabaseHandler(getApplicationContext());

        defDate.setOnClickListener(this);
        defTime.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_save, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        Intent intent;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_save:
                save();
                return true;
            default:
                break;
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.defTime:
                showDialog(TIME_DIALOG_ID);
                break;
            case R.id.defDate:
                showDialog(DATE_DIALOG_ID);
                break;
        }
    }

    private void save() {
        String Sevent = name.getText().toString();
        String Sdescription = description.getText().toString();
        String STime = hour.getText().toString();
        String SDate = date.getText().toString();
        String SLocation = location.getText().toString();
        String SCategpry = category.getText().toString();

        if (Sevent.matches("")) {
            Toast.makeText(EventActivityAdd.this, "Insert a title for the event", Toast.LENGTH_LONG).show();
        }else if(Sdescription.matches("")){
            Toast.makeText(EventActivityAdd.this, "Insert a description to the event", Toast.LENGTH_LONG).show();
        }
        else {
            Event event = new Event(Sevent, Sdescription, STime, SDate, SLocation, SCategpry);
            id = db.addEvent(event);
            Toast.makeText(EventActivityAdd.this, "Event Saved", Toast.LENGTH_SHORT).show();
           // setAlarm();
            this.finish();
        }
    }

    /**
    private void setAlarm(){
        Date date = new Date(year - 1900, month, day, hours, minutes);


        Log.d("cal1", "" + year + "/" + month + "/" + day + " - " + hours + ":" + minutes);

        Intent intent = new Intent(EventActivityAdd.this, AlarmReceiver.class);
        intent.putExtra("id", id);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(EventActivityAdd.this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, date.getTime(), pendingIntent);
    }
     **/


    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, dateSetListener, year, month, day);
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timeSetListener, hours, minutes, false);
        }
        return null;
    }

    private void updateTime() {
        String timeSet = "";
        if (hours > 12) {
            hours -= 12;
            timeSet = "PM";
        } else if (hours == 0) {
            hours += 12;
            timeSet = "AM";
        } else if (hours == 12)
            timeSet = "PM";
        else
            timeSet = "AM";

        String minutes = "";
        if (this.minutes < 10)
            minutes = "0" + this.minutes;
        else
            minutes = String.valueOf(this.minutes);

        // Append in a StringBuilder
        String aTime = new StringBuilder().append(hours).append(':')
                .append(minutes).append(" ").append(timeSet).toString();

        hour.setText(aTime);
    }}
