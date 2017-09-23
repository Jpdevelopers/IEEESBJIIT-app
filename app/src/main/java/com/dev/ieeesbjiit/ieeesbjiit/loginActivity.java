package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.roger.catloadinglibrary.CatLoadingView;

public class loginActivity extends AppCompatActivity {

    EditText email_et, password;
    TextView register;
    Button signin;
    FirebaseAuth auth;
    CatLoadingView mView;
    String pass_word;
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        auth = FirebaseAuth.getInstance();

        sp= getSharedPreferences("LoginStatus", MODE_PRIVATE);

        if (auth.getCurrentUser() != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("isLogin",false);
            editor.commit();
            startActivity(new Intent(loginActivity.this, MainActivity.class));
            finish();
        }


        mView = new CatLoadingView();
        email_et = (EditText) findViewById(R.id.et_username);
        password = (EditText) findViewById(R.id.et_password);

        register = (TextView) findViewById(R.id.tv_register);
        signin = (Button) findViewById(R.id.login_button);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = email_et.getText().toString();
                pass_word = password.getText().toString();
                if (email.isEmpty()) {

                    Toast.makeText(loginActivity.this, "Email can't be empty", Toast.LENGTH_SHORT).show();                    return;
                }
                if (pass_word.isEmpty()) {
                    password.setError("Please enter password");
                    return;
                } else {
                    mView.show(getSupportFragmentManager(), "");
                    auth.signInWithEmailAndPassword(email, pass_word).addOnCompleteListener(loginActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            mView.dismiss();
                            if (!task.isSuccessful()) {
                                if (pass_word.length() < 8 || pass_word.length() > 15)
                                    password.setError("Invalid passsword length");
                                else
                                    Toast.makeText(loginActivity.this, "Invalid password or email", Toast.LENGTH_SHORT).show();
                            } else {
                                SharedPreferences.Editor editor = sp.edit();
                                editor.putBoolean("isLogin",false);
                                editor.commit();
                                startActivity(new Intent(loginActivity.this, MainActivity.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    public void register(View v)
    {
        Intent reg_i = new Intent();
        reg_i.setClass(this, registerActivity.class);
        startActivity(reg_i);
        finish();
    }

    public void resetPassword(View v)
    {
        String reg_email= email_et.getText().toString();
        if(reg_email.isEmpty())
        {
            Toast.makeText(this, "Please Enter your registered Email", Toast.LENGTH_SHORT).show();
            return;
        }

        mView.show(getSupportFragmentManager(),"");
        auth.sendPasswordResetEmail(reg_email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        mView.dismiss();
                        if(task.isSuccessful())
                            Toast.makeText(loginActivity.this, "Reset link has been sent to your registered email.", Toast.LENGTH_SHORT).show();
                        else
                        {
                            Toast.makeText(loginActivity.this, "Failed to send link.Please try again later.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
