package com.dev.ieeesbjiit.ieeesbjiit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class AboutAuthority extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MemberClass> members= new ArrayList<MemberClass>();

    String[] authNames, authDecs;
    int authorities[]= {R.drawable.jpgaur, R.drawable.vc, R.drawable.kkrohtagi,R.drawable.krishna1,R.drawable.rcjain };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_authority);


        recyclerView= (RecyclerView)findViewById(R.id.authority_recycler_view);
        setTitle("Authorities");
        authNames= getResources().getStringArray(R.array.auth_name);
        authDecs= getResources().getStringArray(R.array.auth_desc);
        int i=0;
        for(String name: authNames)
        {
            MemberClass memberClass= new MemberClass(name, authDecs[i], authorities[i]);
            members.add(memberClass);
            i++;
        }

        adapter= new CoreTeamAdapter(members);
        recyclerView.setHasFixedSize(true);  //this will improve the performance
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }


}
