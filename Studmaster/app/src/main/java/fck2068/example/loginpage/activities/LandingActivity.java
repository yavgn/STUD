package fck2068.example.loginpage.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import fck2068.example.loginpage.R;

//CLASS to get the extras that we passed
public class LandingActivity extends AppCompatActivity {

    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        textViewName = (TextView) findViewById(R.id.welcome);
        String nameFromIntent = getIntent().getStringExtra("EMAIL");
        textViewName.setText("Welcome " + nameFromIntent);
    }
}
