package com.immiapp.immiapp.events;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.immiapp.immiapp.Database.DatabaseHandler;
import com.immiapp.immiapp.Main;
import com.immiapp.immiapp.R;

import java.util.Calendar;


public class EventActivityEdit extends Activity implements View.OnClickListener {

    private DatabaseHandler db;
    private Event event;
    private int id;

    private EditText title, description, location;
    private Button defTime, defDate, defCategory;
    private TextView date, time, category;
    int	day, month, year, hours, minutes;
    static final int TIME_DIALOG_ID = 998;
    static final int DATE_DIALOG_ID = 999;

    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int yr, int monthOfYear, int dayOfMonth) {
            day = dayOfMonth;
            month = monthOfYear;
            year = yr;
            date.setText(day+"/"+(month+1)+"/"+ year);
        }
    };

    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            hours = hourOfDay;
            minutes = minute;
            Calendar datetime = Calendar.getInstance();
            datetime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            datetime.set(Calendar.MINUTE, minute);
            updateTime();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_edit);

        title = (EditText)findViewById(R.id.title);
        description = (EditText)findViewById(R.id.description);
        location = (EditText)findViewById(R.id.location);
        date = (TextView)findViewById(R.id.txtDate);
        time = (TextView)findViewById(R.id.txtTime);
        category = (TextView)findViewById(R.id.txtCategory);

        defDate = (Button)findViewById(R.id.btnDate);
        defTime = (Button)findViewById(R.id.btnTime);
        defCategory = (Button)findViewById(R.id.btnCategory);

        defDate.setOnClickListener(this);
        defTime.setOnClickListener(this);
        defCategory.setOnClickListener(this);

        Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        Intent intent = getIntent();
        Bundle bundle =intent.getExtras();

        db = new DatabaseHandler(this);

        if(bundle == null) return;

        if (bundle.containsKey(db.KEY_ROWID_EVENTS)) {
            id = bundle.getInt(db.KEY_ROWID_EVENTS);
            consult();
        }
        setTitle("Edit - " + event.getTitle());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_save, menu);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
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

    private void consult() {
        event = db.getEventsById(id);

        title.setText(event.getTitle());
        description.setText(event.getDescription());
        location.setText(event.getLocation());
        date.setText(event.getDate());
        time.setText(event.getTime());
        category.setText(event.getCategory());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnTime:
                showDialog(TIME_DIALOG_ID);
                break;
            case R.id.btnDate:
                showDialog(DATE_DIALOG_ID);
                break;
            case R.id.btnCategory:
                //add a choice menu
                break;
        }

    }


    private void save() {

        String Sevent = title.getText().toString();
        String Sdescription = description.getText().toString();
        String Stime = time.getText().toString();
        String Sdate = date.getText().toString();
        String Slocation = location.getText().toString();
        String Scategory = category.getText().toString();

        if (Sevent.matches("")) {
            Toast.makeText(EventActivityEdit.this, "Insert a title for the event", Toast.LENGTH_LONG).show();
        }else if(Sdescription.matches("")){
            Toast.makeText(EventActivityEdit.this, "Insert a description to the event", Toast.LENGTH_LONG).show();
        }else {

            event.setTitle(Sevent);
            event.setDescription(Sdescription);
            event.setTime(Stime);
            event.setDate(Sdate);
            event.setLocation(Slocation);
            event.setCategory(Scategory);
            db.updateEvent(event);

            Toast.makeText(EventActivityEdit.this, "Stored event", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(), Main.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

    }

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

        time.setText(aTime);
    }

}
