package fck2068.example.loginpage.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import fck2068.example.loginpage.R;
import org.w3c.dom.Text;


//CLASS to get the extras that we passed
public class LandingActivity extends Fragment {

    private TextView textViewName;
    Session session;
    String[] pages = {"Notifications", "Search", "Timetable", "Location"};
    int[] icons = {R.drawable.notifications_icon, R.drawable.serach_icon, R.drawable.calendar_icon, R.drawable.location_icon};
    ListView list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_landing, container, false);
        session = new Session(this);
        if(!session.statusLoggedIn()){
            logout();
        }

        ListView listView = (ListView)view.findViewById(R.id.landingListView);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                pages);
        listView.setAdapter(listViewAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                    Intent intent = new Intent(getActivity(),TimeTable.class);
                    startActivity(intent);

                }else if(i == 1){
                    Intent intent2 = new Intent(getActivity(),Calander.class);
                    startActivity(intent2);

                }else if (i == 2){
                    Intent intent3 = new Intent(getActivity(),PersonalDetails.class);
                    startActivity(intent3);

                }
            }
        });


        return view;
    }
   /* @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        session = new Session(this);
       if(!session.statusLoggedIn()){
            logout();
        }
       String nameFromIntent = "Welcome "+getIntent().getStringExtra("EMAIL");

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
               // getActivity(),
               // android.R.layout.simple_list_item_1,
               // pages);


        list = (ListView) findViewById(R.id.landingListView);
        //create a CustomAdapter
        CustomAdapter adpater = new CustomAdapter(this, pages, icons);
        //set the list to the adapter
        list.setAdapter(adpater);
        //handle item clicks
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                if(i==0){
                    Toast.makeText(LandingActivity.this, "Notifications clicked...", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getActivity(),TimeTable.class);
                    startActivity(intent);
                }
                if(i==1){
                    Toast.makeText(LandingActivity.this, "Search clicked...", Toast.LENGTH_SHORT).show();
                }
                if(i==2){
                    Toast.makeText(LandingActivity.this, "Timetable clicked...", Toast.LENGTH_SHORT).show();
                }
                if(i==3){
                    Toast.makeText(LandingActivity.this, "Location clicked...", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
            super(c, R.layout.row, R.id.menuNameTextView, pages);
            this.context=c;
            this.imgs = imgs;
            this.pagesMenu = pages;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
            LayoutInflater layoutInflater = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View row = layoutInflater.inflate(R.layout.row, parent, false);
            ImageView images = row.findViewById(R.id.rowImageView);
            TextView page = row.findViewById(R.id.menuNameTextView);
            images. setImageResource(icons[position]);
            page.setText(pages[position]);
            return row;
        }

    }
}
