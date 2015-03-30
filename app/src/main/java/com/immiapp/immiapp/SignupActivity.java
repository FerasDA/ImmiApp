package com.immiapp.immiapp;

import android.app.Activity;
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
        this.dh = new DatabaseHelper(this);
    }

    private void CreateAccount() {
        // this.output = (TextView) this.findViewById(R.id.out_text);
        String firstname = etFirstname.getText().toString();
        String lastname = etLastname.getText().toString();
        String email = etEmail.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String confirm = etConfirm.getText().toString();

        //still need to edit the DatabaseHelper class to Accounts_Insert firstname
        //lastname, and email to the databse
        String errors = "";
        boolean addToDB = true,
            passwordsMatch = CheckPasswordsMatch(password, confirm),
            usernameUnique = IsUniqueUsername(username),
            usernameLengthOk = username.length() > 0,
            hasEntries = firstname.length() > 0 && lastname.length() > 0,
            validEmail = ValidateEmail(email);

        if(!passwordsMatch)
        {
            addToDB = false;
            errors = "Passwords do not match.";
        }
        else if(!usernameUnique)
        {
            addToDB = false;
            errors = "Username taken.";
        }
        else if(!usernameLengthOk)
        {
            addToDB = false;
            errors = "Username too short.";
        }
        else if(!hasEntries)
        {
            addToDB = false;
            errors = "Missing Entry";
        }
        else if(!validEmail)
        {
            addToDB = false;
            errors = "Invalid email format.";
        }

        if(addToDB)
        {
            dh.Accounts_Insert(username, password, firstname, lastname, email);
            Toast.makeText(SignupActivity.this, "New record inserted.",
                    Toast.LENGTH_SHORT).show();
            finish();
        }
        else
        {
            Toast.makeText(SignupActivity.this, errors, Toast.LENGTH_SHORT).show();
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

    private boolean CheckPasswordsMatch(String password1, String password2)
    {
        return (password1.equals(password2));
    }

    private boolean IsUniqueUsername(String username)
    {
        boolean valOK = false;

        long usernameUnique = dh.Accounts_CheckForUsername(username);
        if(usernameUnique == 0)
        {
            valOK = true;
        }

        return valOK;
    }

    private boolean ValidateEmail(String email)
    {
        boolean valid = false,
            foundAt = false;

        Log.d("EmailValidate", "" + email.length());

        for(int i = 0; i < email.length(); i++)
        {
            if(!foundAt)
            {
                if(email.charAt(i)== '@') foundAt = true;
            }
            else
            {
                if(email.substring(i,email.length()).equals(".com") ||
                    email.substring(i, email.length()).equals(".edu") ||
                    email.substring(i, email.length()).equals(".org") ||
                    email.substring(i,email.length()).equals(".net"))
                {
                    valid = true;
                    break;
                }
            }
        }

        return valid;
    }
}
