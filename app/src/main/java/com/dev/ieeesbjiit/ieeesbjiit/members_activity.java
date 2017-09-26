package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;

public class members_activity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MemberClass> members= new ArrayList<MemberClass>();

    String[] memberNames, memberPosts, memberEmails, memberWorks=new String[20];
    int session_16_17[]= {R.drawable.shivam, R.drawable.shivan, R.drawable.paras, R.drawable.piyush_tyagi, R.drawable.saurabh_khurana, R.drawable.shivam_shukla, R.drawable.shubhi_khare,R.drawable.manogya, R.drawable.mayank_nayyar, R.drawable.sumit, R.drawable.tejasv};
    int star_alumini[]={R.drawable.harsh,R.drawable.dixit, R.drawable.vaibhav, R.drawable.somani,R.drawable.umang_francis, R.drawable.nischay_gupta,R.drawable.aashish_c,R.drawable.ojasvi,R.drawable.trishant};
    int team[]={R.drawable.yugank, R.drawable.achint,R.drawable.saloni,R.drawable.pragati,R.drawable.yash,R.drawable.saransh, R.drawable.sajal, R.drawable.ambrish, R.drawable.prabhu,R.drawable.karan, R.drawable.reetika, R.drawable.raj, R.drawable.shuanak, R.drawable.avantika};
    int director;

//    private FirebaseDatabase firebaseDatabase;
//    private DatabaseReference databaseReference;
//    private ChildEventListener childEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members_activity);

        Intent intt= getIntent();
        director= intt.getIntExtra("DIRECTOR",2);

        if(director==1)
        {
            memberNames = getResources().getStringArray(R.array.team_name);
            memberPosts= getResources().getStringArray(R.array.team_post);
            memberEmails=getResources().getStringArray(R.array.team_email);
            Arrays.fill(memberWorks,0,memberNames.length-1," ");
            setTitle("Current Core Team");
        }

        if(director==2) {
            memberNames = getResources().getStringArray(R.array.member_names);
            memberPosts= getResources().getStringArray(R.array.member_posts);
            memberEmails=getResources().getStringArray(R.array.member_emails);
            Arrays.fill(memberWorks,0,memberNames.length-1," ");
            setTitle("Senior Advisors");
        }
        else if(director==3)
        {
            memberNames = getResources().getStringArray(R.array.star_alumni_name);
            memberPosts= getResources().getStringArray(R.array.star_alumni_posts);
            memberEmails=getResources().getStringArray(R.array.star_alumni_email);
            memberWorks=getResources().getStringArray(R.array.star_alumni_work);
            setTitle("☆ Star Alumni");
        }

        recyclerView= (RecyclerView)findViewById(R.id.members_recycler_view);

         {
            int i = 0;
            for (String name : memberNames) {
                MemberClass item_data = new MemberClass(name, memberPosts[i], memberEmails[i], memberWorks[i]);
                if (director == 1)
                    item_data.setUrl(team[i]);
                else if (director == 2)
                    item_data.setUrl(session_16_17[i]);
                else if (director == 3)
                    item_data.setUrl(star_alumini[i]);
                members.add(item_data);
                i++;
            }
        }
        adapter= new CoreTeamAdapter(members);
        recyclerView.setHasFixedSize(true);  //this will improve the performance
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

//    public void attachDatabaseReadListener() {
//        if (childEventListener == null) {
//            childEventListener = new ChildEventListener() {
//                @Override
//                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
//                    MemberClass membersFetched = dataSnapshot.getValue(MemberClass.class);
//                    members.add(membersFetched);
//                    adapter.notifyDataSetChanged();
//                }
//
//                @Override
//                public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onChildRemoved(DataSnapshot dataSnapshot) {
//
//                }
//
//                @Override
//                public void onChildMoved(DataSnapshot dataSnapshot, String s) {
//
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//
//                }
//            };
//            databaseReference.addChildEventListener(childEventListener);
//        }
//    }



//    @Override
//    protected void onStart() {
//        super.onStart();
//        if(director==1) {
//            attachDatabaseReadListener();
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        if(director==1)
//        attachDatabaseReadListener();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        if(director==1)
//        detachDatabaseReadListener();
//    }
//    public void detachDatabaseReadListener(){
//        if(childEventListener!= null)
//            databaseReference.removeEventListener(childEventListener);
//
//        childEventListener= null;
//
//    }
}
