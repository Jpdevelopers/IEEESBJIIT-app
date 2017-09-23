package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roger.catloadinglibrary.CatLoadingView;

import java.util.ArrayList;



public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    String[] title_array, subtitle_array;
    int[] img_res= {R.drawable.upcoming_events, R.drawable.events_arrnged, R.drawable.team, R.drawable.partnersicon, R.drawable.auth2, R.drawable.ieeesb, R.drawable.ieee};
    ArrayList<Item_data> arrayList= new ArrayList<Item_data>();

    Toolbar toolbar;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;
    FirebaseAuth mAuth;

    DatabaseReference databaseReference;
    TextView nameTvNav, emailTvNav, enrollTvNav;
    View navHeader;
    String Username;
    CatLoadingView mView;
    MediaPlayer mp;
    SharedPreferences sp;

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


        mView= new CatLoadingView();
        mp = MediaPlayer.create(this, R.raw.welcome_not);
        sp= getSharedPreferences("LoginStatus", MODE_PRIVATE);

        toolbar= (Toolbar) findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        drawerLayout=(DrawerLayout) findViewById(R.id.drawer_layout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        navigationView= (NavigationView) findViewById(R.id.main_nav_view);
        navHeader= navigationView.getHeaderView(0);

        nameTvNav= (TextView) navHeader.findViewById(R.id.name_tv);
        emailTvNav=(TextView) navHeader.findViewById(R.id.email_tv);
        enrollTvNav= (TextView) navHeader.findViewById(R.id.enroll_tv);

        mAuth= FirebaseAuth.getInstance();

        assert mAuth.getCurrentUser() != null;
        databaseReference= FirebaseDatabase.getInstance().getReference().child("users").child(mAuth.getCurrentUser().getUid());


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId())
                {
                    case R.id.goto_site: {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://ieeejiit.in/"));
                        startActivity(browserIntent);
                        break;
                    }

                    case R.id.signout_drawer:
                    {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(MainActivity.this, loginActivity.class));
                        finish();
                        break;
                    }

                    case R.id.change_password:
                    {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        // Get the layout inflater
                        LayoutInflater inflater = (MainActivity.this).getLayoutInflater();
                        // Inflate and set the layout for the dialog
                        // Pass null as the parent view because its going in the
                        // dialog layout
                        builder.setTitle("Change Password");
                        View dialogview= inflater.inflate(R.layout.activity_change_email, null);
                        final TextInputEditText oldPassword= (TextInputEditText) dialogview.findViewById(R.id.old_password);
                        final TextInputEditText newPassword= (TextInputEditText) dialogview.findViewById(R.id.new_password);

                        builder.setView(dialogview)
                                // Add action buttons
                                .setPositiveButton("CONFIRM", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int id) {
                                                mView.show(getSupportFragmentManager(),"Changing");

                                                String oldpassword= oldPassword.getText().toString();
                                                final String newpassword= newPassword.getText().toString();
                                                if (mAuth.getCurrentUser() != null && !newpassword.equals("")) {
                                                    if (newpassword.length() < 8) {
                                                        newPassword.setError("Password too short, enter minimum 8 characters");
                                                        mView.dismiss();
                                                    }
                                                    else if (newPassword.getText().toString().trim().equals("")) {
                                                        newPassword.setError("Enter password");
                                                        mView.dismiss();
                                                    }
                                                    else {
                                                        mAuth.getCurrentUser().reauthenticate(EmailAuthProvider.getCredential(mAuth.getCurrentUser().getEmail(), oldpassword))
                                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                    @Override
                                                                    public void onComplete(@NonNull Task<Void> task) {
                                                                        mView.dismiss();
                                                                        if (task.isSuccessful()) {
                                                                            mAuth.getCurrentUser().updatePassword(newpassword).addOnCompleteListener(new OnCompleteListener<Void>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Void> task) {
                                                                                    if (task.isSuccessful()) {

                                                                                        Toast.makeText(MainActivity.this, "Password Updated", Toast.LENGTH_SHORT).show();
                                                                                    } else {
                                                                                        Toast.makeText(MainActivity.this, "Sorry, Password not updated", Toast.LENGTH_SHORT).show();
                                                                                    }
                                                                                }
                                                                            });
                                                                        } else {
                                                                            Toast.makeText(MainActivity.this, "Old Password is not Correct", Toast.LENGTH_SHORT).show();
                                                                        }
                                                                    }
                                                                });
                                                    }


                                                }
                                            }
                                        }
                                );
                        builder.create();
                        builder.show();
                    }



                }
                return true;
            }
        });


        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userInfo cur_user= dataSnapshot.getValue(userInfo.class);
                if(cur_user!=null) {
                    Username=cur_user.getName();
                    nameTvNav.setText(cur_user.getName());
                    emailTvNav.setText(cur_user.getEmail());
                    enrollTvNav.setText(cur_user.getEnroll());
//                    assert mAuth.getCurrentUser() !=null;
                    if(!sp.getBoolean("isLogin",false)) {
                        Toast.makeText(MainActivity.this, "Welcome " + Username, Toast.LENGTH_SHORT).show();
                        mp.start();
                        SharedPreferences.Editor editor = sp.edit();
                        editor.putBoolean("isLogin",true);
                        editor.commit();
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.chatfab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Loading Chats,press again after few seconds", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                if(Username!=null) {
                    Intent intt = new Intent();
                    //intt.setClass(MainActivity.this, ChatSupport.class);
                    intt.putExtra("Username", Username);
                    intt.putExtra("Uid", mAuth.getCurrentUser().getUid());
                    startActivity(intt);
                }

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_dispaly, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId())
        {
            case  R.id.contact:
            {
                Intent i = new Intent(Intent.ACTION_SENDTO);
                Uri urid = Uri.parse("mailto:ieeesbjiit62@gmail.com");
                i.setData(urid);
                i.putExtra(Intent.EXTRA_SUBJECT, "Complaint/ Suggestion");
                if (i.resolveActivity(getPackageManager()) != null) {
                    startActivity(i);
                }
                break;
            }

            case R.id.sign_out:
            {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(this, loginActivity.class));
                finish();
                break;
            }

            default:
                return super.onOptionsItemSelected(item);
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();



    }




    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        actionBarDrawerToggle.syncState();

    }
}


//public class MainActivity extends AppCompatActivity {
//
//    RecyclerView recyclerView;
//    RecyclerView.Adapter adapter;
//    RecyclerView.LayoutManager layoutManager;
//    String[] title_array, subtitle_array;
//    int[] img_res= {R.drawable.upcoming_events, R.drawable.events_arrnged, R.drawable.team, R.drawable.partnersicon, R.drawable.auth2, R.drawable.ieeesb, R.drawable.ieee};
//    ArrayList<Item_data> arrayList= new ArrayList<Item_data>();
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        recyclerView= (RecyclerView) findViewById(R.id.main_recycler_view);
//        title_array= getResources().getStringArray(R.array.title_names);
//        subtitle_array= getResources().getStringArray(R.array.subtitle_names);
//        int i=0;
//        for(String name: title_array)
//        {
//            Item_data item_data= new Item_data(i,name, img_res[i], subtitle_array[i]);
//            arrayList.add(item_data);
//            i++;
//        }
//
//        adapter= new RecyclerViewAdapter(arrayList,this);
//        recyclerView.setHasFixedSize(true);  //this will improve the performance
//        layoutManager= new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_dispaly, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//
//
//        switch (item.getItemId()) {
//            case R.id.contact: {
//                Intent i = new Intent(Intent.ACTION_SENDTO);
//                Uri urid = Uri.parse("mailto:ieeesbjiit62@gmail.com");
//                i.setData(urid);
//                i.putExtra(Intent.EXTRA_SUBJECT, "Complaint/ Suggestion");
//                if (i.resolveActivity(getPackageManager()) != null) {
//                    startActivity(i);
//                }
//                break;
//            }
//        }
//        return true;
//    }
//}
