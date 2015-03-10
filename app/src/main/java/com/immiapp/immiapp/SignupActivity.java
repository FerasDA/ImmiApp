package com.immiapp.immiapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SignupActivity extends Activity implements View.OnClickListener {
    private EditText etFirstname;
    private EditText etLastname;
    private EditText etEmail;
    private EditText etUsername;
    private EditText etPassword;
    private EditText etConfirm;

    private DatabaseHelper dh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Log.d("Sign Up", "onCreate");

        etFirstname = (EditText) findViewById(R.id.first_name);
        etLastname = (EditText) findViewById(R.id.last_name);
        etEmail = (EditText) findViewById(R.id.email);
        etUsername = (EditText) findViewById(R.id.user_name);
        etPassword = (EditText) findViewById(R.id.password);
        etConfirm = (EditText) findViewById(R.id.password_confirm);
        View btnAdd = (Button) findViewById(R.id.done);
        btnAdd.setOnClickListener(this);
        View btnCancel = (Button) findViewById(R.id.cancel);
        btnCancel.setOnClickListener(this);
    }

    private void CreateAccount() {
        // this.output = (TextView) this.findViewById(R.id.out_text);
        String firstname = etFirstname.getText().toString();
        String lastname = etLastname.getText().toString();
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();

        //still need to edit the DatabaseHelper class to insert firstname
        //lastname, and email to the databse
        if ((password.equals(confirm)) && (!username.equals(""))
                && (!password.equals("")) && (!confirm.equals(""))
                && (firstname.equals("")) && (lastname.equals(""))
                && (email.equals(""))) {
            this.dh = new DatabaseHelper(this);
            this.dh.insert(username, password);
            // this.labResult.setText("Added");
            Toast.makeText(SignupActivity.this, "new record inserted",
                    Toast.LENGTH_SHORT).show();
            finish();
        } else if ((username.equals("")) || (password.equals(""))
                || (confirm.equals("")) || (firstname.equals(""))
                || (lastname.equals("")) || (email.equals(""))) {
            Toast.makeText(SignupActivity.this, "Missing entry", Toast.LENGTH_SHORT)
                    .show();
        } else if (!password.equals(confirm)) {
            new AlertDialog.Builder(this)
                    .setTitle("Error")
                    .setMessage("passwords do not match")
                    .setNeutralButton("Try Again",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                }
                            })

                    .show();
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            //later will make this go to Home
            case R.id.done:
                CreateAccount();
                finish();
                break;
            case R.id.cancel:
                finish();
                break;
        }
    }

    @Override
    protected  void onStart(){
        super.onStart();
        Log.d("Sign Up", "onStart");
    }

    @Override
    protected  void onResume(){
        super.onResume();
        Log.d("Sign Up", "onResume");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
