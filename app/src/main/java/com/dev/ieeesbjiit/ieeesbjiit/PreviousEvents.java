package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Collections;

public class PreviousEvents extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter eventAdapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<EventClass> arrayList= new ArrayList<EventClass>();
    int director;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference DatabaseReference;
    private ChildEventListener childEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_previous_events);
        recyclerView= (RecyclerView) findViewById(R.id.event_recycler);
        layoutManager= new LinearLayoutManager(this);
        Intent intent= getIntent();
        director= intent.getIntExtra("DIRECTOR",1);

        firebaseDatabase = FirebaseDatabase.getInstance();
        if(director==1) {
            DatabaseReference = firebaseDatabase.getReference().child("upcoming_events");

            setTitle("Upcoming Events");

        }else if(director==2) {
            DatabaseReference = firebaseDatabase.getReference().child("previous_events");
            setTitle("Previous Events and Workshops");
        }

        eventAdapter= new EventAdapter(arrayList,this);
        recyclerView.setHasFixedSize(true);  //this will improve the performance

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(eventAdapter);



    }



    public void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    EventClass eventFetched = dataSnapshot.getValue(EventClass.class);

                    arrayList.add(eventFetched);
                    if(director==1)
                    {
                        Collections.reverse(arrayList);
                    }
                    eventAdapter.notifyDataSetChanged();
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            };
            DatabaseReference.addChildEventListener(childEventListener);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        attachDatabaseReadListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        attachDatabaseReadListener();
    }

    @Override
    protected void onPause() {
        super.onPause();

        clear();
        detachDatabaseReadListener();
    }

    public void clear(){
        int size = arrayList.size();
        arrayList.clear();
        eventAdapter.notifyItemRangeRemoved(0, size);
    }

    //this is for signedoutcleanup to detach read event listener
    public void detachDatabaseReadListener(){
        if(childEventListener!= null)
            DatabaseReference.removeEventListener(childEventListener);

        childEventListener= null;

    }


}
