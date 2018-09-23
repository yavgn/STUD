package fck2068.example.loginpage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import fck2068.example.loginpage.R;

//CLASS to get the extras that we passed
public class LandingActivity extends AppCompatActivity {

    private TextView textViewName;
    Session session;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        session = new Session(this);
        if(!session.statusLoggedIn()){
            logout();
        }
        /*
        logoutButton = (Button)findViewById(R.id.logoutButton);
        logoutButton.setOnclickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logout();
            }
        });
        */

        textViewName = (TextView) findViewById(R.id.welcome);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);

    }

    private void logout(){
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(LandingActivity.this,LoginActivity.class));
    }
}
