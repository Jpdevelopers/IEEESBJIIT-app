package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.roger.catloadinglibrary.CatLoadingView;

public class registerActivity extends AppCompatActivity {
    EditText name, password, phone, email, enrol;
    RadioGroup hostel;
    RadioButton rdbtn;
    Button register;
    private FirebaseAuth mAuth;
    CatLoadingView mView;
    userInfo app_user;
    String accomodation="";
    SharedPreferences sp;

    private DatabaseReference mDatabaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name= (EditText) findViewById(R.id.et_name);
        password= (EditText) findViewById(R.id.et_passwordreg);
        phone= (EditText) findViewById(R.id.et_phone);
        email= (EditText) findViewById(R.id.et_email);
        enrol= (EditText) findViewById(R.id.et_enrol);

        hostel= (RadioGroup) findViewById(R.id.hostel);
        sp= getSharedPreferences("LoginStatus", MODE_PRIVATE);

        hostel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                int id= hostel.getCheckedRadioButtonId();
                if(id == R.id.rb_hosteller) {
                    accomodation = "Hosteller";
                    enrol.setVisibility(View.VISIBLE);
                }
                else if(id== R.id.day_scholar) {
                    accomodation = "Day Scholar";
                    enrol.setVisibility(View.VISIBLE);
                }
                else {
                    accomodation = "Other";
                    enrol.setVisibility(View.GONE);
                }
            }
        });


    register= (Button) findViewById(R.id.register_button);
        mAuth= FirebaseAuth.getInstance();  //get instancw for firebase auth

        mView= new CatLoadingView();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email_str= email.getText().toString();
                String password_str= password.getText().toString();
                final String name_str= name.getText().toString();
                final String phone_str= phone.getText().toString();
                final String enrol_str= enrol.getText().toString();



                if(name_str.isEmpty())
                {
                    Toast.makeText(registerActivity.this, "Plese enter name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password_str.isEmpty())
                {
                    Toast.makeText(registerActivity.this, "password cant be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(password_str.length() <8 || password_str.length()> 15)
                {
                    Toast.makeText(registerActivity.this, "Change the length of password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(phone_str.isEmpty()) {
                    Toast.makeText(registerActivity.this, "Please enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(email_str.isEmpty())
                {
                    Toast.makeText(registerActivity.this, "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }


                else {
                    mView.show(getSupportFragmentManager(), "");
                    //create user
                    mAuth.createUserWithEmailAndPassword(email_str, password_str)
                            .addOnCompleteListener(registerActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (!isNetworkAvailable()) {
                                        Toast.makeText(registerActivity.this, "Network error. Please check your internet connection!", Toast.LENGTH_SHORT).show();
                                        mView.dismiss();
                                    }
                                    else if (!task.isSuccessful()) {
                                        Toast.makeText(registerActivity.this, "Authentication Failed. Please try again!", Toast.LENGTH_SHORT).show();
                                        mView.dismiss();
                                    } else {
                                        if(enrol_str.isEmpty()|| accomodation.equals("Other"))
                                            app_user = new userInfo(name_str, email_str, phone_str,accomodation);
                                        else
                                        app_user = new userInfo(name_str, email_str, enrol_str, phone_str, accomodation);
                                        mDatabaseUsers = FirebaseDatabase.getInstance().getReference("users");

                                        FirebaseUser firebaseuser = mAuth.getCurrentUser();

                                        assert firebaseuser != null;
                                        mDatabaseUsers.child(firebaseuser.getUid()).setValue(app_user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    SharedPreferences.Editor editor = sp.edit();
                                                    editor.putBoolean("isLogin",false);
                                                    editor.commit();
                                                    startActivity(new Intent(registerActivity.this, MainActivity.class));
                                                    finish();
                                                }else
                                                    Toast.makeText(registerActivity.this, "Oops! Database not updated. Try again.", Toast.LENGTH_SHORT).show();
                                            }
                                        });


                                    }

                                }
                            });
                }

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
