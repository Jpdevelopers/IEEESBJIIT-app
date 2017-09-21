package com.dev.ieeesbjiit.ieeesbjiit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] title_array, subtitle_array;
    int[] img_res= {R.drawable.upcoming_events, R.drawable.events_arrnged, R.drawable.team, R.drawable.partnersicon, R.drawable.auth2, R.drawable.ieeesb, R.drawable.ieee};
    ArrayList<Item_data> arrayList= new ArrayList<Item_data>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= (RecyclerView) findViewById(R.id.main_recycler_view);
        title_array= getResources().getStringArray(R.array.title_names);
        subtitle_array= getResources().getStringArray(R.array.subtitle_names);
        int i=0;
        for(String name: title_array)
        {
            Item_data item_data= new Item_data(i,name, img_res[i], subtitle_array[i]);
            arrayList.add(item_data);
            i++;
        }

        adapter= new RecyclerViewAdapter(arrayList,this);
        recyclerView.setHasFixedSize(true);  //this will improve the performance
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
