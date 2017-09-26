package com.dev.ieeesbjiit.ieeesbjiit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TeamSessions extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] session_array;
    ArrayList<String> arrayList= new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_sessions);

        recyclerView= (RecyclerView) findViewById(R.id.session_recycler);
        session_array= getResources().getStringArray(R.array.sessions);

        int i=0;
        for(String session: session_array)
        {
            arrayList.add(session);

            i++;

        }

        adapter= new SessionAdapter(arrayList,this);
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }


}
