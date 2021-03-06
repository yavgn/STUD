package fck2068.example.loginpage.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import fck2068.example.loginpage.R;
import fck2068.example.loginpage.helper.InputValidation;
import fck2068.example.loginpage.model.Session;
import fck2068.example.loginpage.sql.DatabaseHelper;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private final AppCompatActivity activity = LoginActivity.this;

    private NestedScrollView nestedScrollView;

    private TextInputLayout textInputLayoutUsername;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextUsername;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton appCompatButtonLogin;

    private AppCompatTextView textViewLinkRegister;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
       // getSupportActionBar().hide();
        session = new Session(this);

        initViews();
        initListeners();
        initObjects();

        if (session.statusLoggedIn()){
            startActivity(new Intent(LoginActivity.this,LandingActivity.class));
            finish();
        }
    }
    private void initViews(){
        nestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollView);

        textInputLayoutUsername = (TextInputLayout) findViewById(R.id.textInputLayoutUsername);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextUsername = (TextInputEditText) findViewById(R.id.textInputEditTextUsername);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        appCompatButtonLogin = (AppCompatButton) findViewById(R.id.appCompatButtonLogin);

        textViewLinkRegister = (AppCompatTextView) findViewById(R.id.textViewLinkRegister);
    }

    private void initListeners(){
        appCompatButtonLogin.setOnClickListener(this);
        textViewLinkRegister.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.appCompatButtonLogin:
                verifyFromSQLite();
                break;
            case R.id.textViewLinkRegister:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite(){
        if (!inputValidation.isInputEditTextFilled(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_email))) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextUsername, textInputLayoutUsername, getString(R.string.error_message_email))) {
                return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_email))) {
            return;
        }

        if (databaseHelper.checkUser(textInputEditTextUsername.getText().toString().trim(), textInputEditTextPassword.getText().toString().trim())) {
            Intent landingIntent = new Intent(activity, LandingActivity.class);
            landingIntent.putExtra("EMAIL", textInputEditTextUsername.getText().toString().trim());
            emptyInputEditText();
            landingIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            landingIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(landingIntent);
            session.setLoggedIn(true);
            startActivity(landingIntent);

        } else {
            Snackbar.make(nestedScrollView, getString(R.string.error_valid_email_password), Snackbar.LENGTH_LONG).show();
        }
    }

    private void emptyInputEditText(){
        textInputEditTextUsername.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
