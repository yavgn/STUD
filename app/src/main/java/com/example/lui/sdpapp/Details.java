package com.example.lui.sdpapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Details extends Fragment {

    public Details(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        String[] menuItems={"Timetable","Calander","Details"};

        ListView listView = (ListView)view.findViewById(R.id.detailMenu);

        ArrayAdapter<String> listViewAdapter = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_1,
                menuItems);

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
}
