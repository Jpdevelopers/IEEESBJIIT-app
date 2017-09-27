package com.dev.ieeesbjiit.ieeesbjiit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CompaniesAssociated extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;


    ArrayList<SponsorClass> sponsors= new ArrayList<SponsorClass>();

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private ChildEventListener childEventListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_companies_associated);

        setTitle("Companies Associated");

        recyclerView= (RecyclerView)findViewById(R.id.sponsor_recycler);
        adapter= new SponsorAdapter(sponsors);
        recyclerView.setHasFixedSize(true);  //this will improve the performance
        recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        recyclerView.setAdapter(adapter);

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference= firebaseDatabase.getReference().child("companies_associated");
    }

    public void attachDatabaseReadListener() {
        if (childEventListener == null) {
            childEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    SponsorClass sponsorFetched = dataSnapshot.getValue(SponsorClass.class);
                    sponsors.add(sponsorFetched);
                    adapter.notifyDataSetChanged();
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
            databaseReference.addChildEventListener(childEventListener);
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
        detachDatabaseReadListener();
    }



    //this is for signedoutcleanup to detach read event listener
    public void detachDatabaseReadListener(){
        if(childEventListener!= null)
            databaseReference.removeEventListener(childEventListener);

        childEventListener= null;

    }
}
