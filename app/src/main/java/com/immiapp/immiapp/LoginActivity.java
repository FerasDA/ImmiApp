package com.immiapp.immiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;


public class LoginActivity extends Activity implements View.OnClickListener {

    private DatabaseHelper dh;
    private EditText userNameEditableField;
    private EditText passwordEditableField;
    private final static String OPT_NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userNameEditableField = (EditText) findViewById(R.id.user_name);
        passwordEditableField = (EditText) findViewById(R.id.password);

        View btnLogin = (Button) findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(this);
        //We may add a cancel button here or/and Facebook button
        // View btnCancel = (Button) findViewById(R.id.cancelButton);
        //btnCancel.setOnClickListener(this);
        View btnNewUser = (Button) findViewById(R.id.signupButton);
        btnNewUser.setOnClickListener(this);

        Log.d("Login", "onCreate");
    }

    private void checkLogin() {
        String username = this.userNameEditableField.getText().toString();
        String password = this.passwordEditableField.getText().toString();
        this.dh = new DatabaseHelper(this);
        List<String> names = this.dh.selectAll(username, password);
        if (names.size() > 0) { // Login successful
            // Save username as the name of the player
            SharedPreferences settings = PreferenceManager
                    .getDefaultSharedPreferences(this);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString(OPT_NAME, username);
            editor.commit();

            // in the future this should bring up the Immi Home screen
            //but now we'll just use the signup activity as an example.
            startActivity(new Intent(this, PreferencesActivity.class));
            finish();
        } else {
            // Try again?
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("Login failed")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            }).show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.loginButton:
                checkLogin();
                break;
            //case R.id.cancel_button:
            //    finish();
            //    break;
            case R.id.signupButton:
                startActivity(new Intent(this, SignupActivity.class));
                break;
        }
    }

    //

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Login", "onStart");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.d("Login", "onResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
