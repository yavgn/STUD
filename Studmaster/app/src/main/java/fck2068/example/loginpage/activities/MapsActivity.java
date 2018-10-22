package fck2068.example.loginpage.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import fck2068.example.loginpage.R;

public class MapsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);

        ImageView mapImage = findViewById(R.id.mapImage);
        int imageResource = getResources().getIdentifier("@drawable/map-campus.png",null,this.getPackageName());
        mapImage.setImageResource(imageResource);


    }
}
