package com.immiapp.immiapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class PreferencesActivity extends ActionBarActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        Log.d("preferences", "onCreate");


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.done_pref:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.cancel_pref:
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
