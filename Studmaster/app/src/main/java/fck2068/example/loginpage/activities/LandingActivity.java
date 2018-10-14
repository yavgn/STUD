package fck2068.example.loginpage.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import fck2068.example.loginpage.R;
import fck2068.example.loginpage.model.Session;

//CLASS to get the extras that we passed
public class LandingActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView settings;
    private ImageView home;
    private ImageView account;
    Session session;

    //Arrary for listview

    String[] pages = {"Notifications", "Search", "Timetable", "Location"};
    int[] icons = {R.drawable.notifications_icon, R.drawable.serach_icon, R.drawable.calendar_icon, R.drawable.location_icon};
    ListView list;

    //
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_activity);
        //session to stay logged in without having to signin everytime
        setContentView(R.layout.landing_activity);
        session = new Session(this);
        if(!session.statusLoggedIn()){
            logout();
        }
        String nameFromIntent = "Welcome "+getIntent().getStringExtra("EMAIL");
        Toast.makeText(this, nameFromIntent, Toast.LENGTH_LONG).show();

        settings = (ImageView) findViewById(R.id.settingsImageView);
        home = (ImageView) findViewById(R.id.logoImageView);
        account = (ImageView) findViewById(R.id.accountImageView);
        settings.setOnClickListener(this);
        home.setOnClickListener(this);
        account.setOnClickListener(this);

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.action_mail:
                        Toast.makeText(LandingActivity.this, "Mail clicked...", Toast.LENGTH_SHORT).show();
                        Intent emailIntent = new Intent(LandingActivity.this, EmailActivity.class);
                        startActivity(emailIntent);
                        break;
                    case R.id.action_messenger:
                        Toast.makeText(LandingActivity.this, "Messenger clicked...", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_more:
                        Intent timerIntent = new Intent(LandingActivity.this, TimerActivity.class);
                        startActivity(timerIntent);
                        break;
                }
                return true;
            }
        });

        list = (ListView) findViewById(R.id.landingListView);
        //create a CustomAdapter
        CustomAdapter adpater = new CustomAdapter(this, pages, icons);
        //set the list to the adapter
        list.setAdapter(adpater);
        //handle item clicks
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0){
                    Toast.makeText(LandingActivity.this, "Notifications clicked...", Toast.LENGTH_SHORT).show();
                    //Intent intent = new Intent(activity, LandingActivity.class);
                    //startActivity(intent);
                }
                if(position==1){
                    Toast.makeText(LandingActivity.this, "Search clicked...", Toast.LENGTH_SHORT).show();
                    Intent libraryIntent = new Intent(LandingActivity.this, LibraryActivity.class);
                    startActivity(libraryIntent);
                }
                if(position==2){
                    Toast.makeText(LandingActivity.this, "Timetable clicked...", Toast.LENGTH_SHORT).show();
                }
                if(position==3){
                    Toast.makeText(LandingActivity.this, "Location clicked...", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //logout not fully implented yet
        /*
        logoutButton = (Button)findViewById(R.id.logoutButton);
        logoutButton.setOnclickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                logout();
            }
        });
        */

    }
    //logout session
    private void logout(){
        session.setLoggedIn(false);
        finish();
        startActivity(new Intent(LandingActivity.this,LoginActivity.class));

    }
        //create custom adpater class
    class CustomAdapter extends ArrayAdapter<String>{
        Context context;
        String[] pagesMenu;
        int[] imgs;

            CustomAdapter(Context c, String[] pages, int[] imgs){
            super(c, R.layout.landing_row, R.id.menuNameTextView, pages);
            this.context=c;
            this.imgs = imgs;
            this.pagesMenu = pages;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.landing_row, parent, false);
            ImageView images = row.findViewById(R.id.rowImageView);
            TextView page = row.findViewById(R.id.menuNameTextView);
            images. setImageResource(icons[position]);
            page.setText(pages[position]);
            row.setBackground(getContext().getDrawable(R.drawable.listview_item_border));
            return row;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.settingsImageView:
                Toast.makeText(LandingActivity.this, "Settings clicked...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logoImageView:
                Toast.makeText(LandingActivity.this, "Home clicked...", Toast.LENGTH_SHORT).show();
                Intent intent = getIntent();
                finish();
                startActivity(intent);
                break;
            case R.id.accountImageView:
                Toast.makeText(LandingActivity.this, "Account clicked...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
