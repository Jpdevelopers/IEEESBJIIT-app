package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatSupport extends AppCompatActivity {

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    ArrayList<MessageClass> messages;
    private EditText mMessageEditText;
    private Button mSendButton;

    private String mUsername, mUid;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference; // class that references a    specific portion of database
//    private DatabaseReference userDbReference ;

    private ChildEventListener mchildEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_support);

        mUsername = ANONYMOUS;
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference= mFirebaseDatabase.getReference().child("messages");
      //  userDbReference= mFirebaseDatabase.getReference().child("users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());

        mMessageEditText = (EditText) findViewById(R.id.messageEditText);
        mSendButton = (Button) findViewById(R.id.sendButton);

        Intent intt= getIntent();
        mUsername= intt.getStringExtra("Username");
        mUid= intt.getStringExtra("Uid");

        recyclerView= (RecyclerView)findViewById(R.id.chat_recycler_view);

        messages= new ArrayList<>();
        adapter= new MessageAdapter(messages);
        recyclerView.setHasFixedSize(true);  //this will improve the performance
        layoutManager= new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(messages.size()-1);





        mMessageEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mSendButton.setEnabled(true);
                } else {
                    mSendButton.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
        mMessageEditText.setFilters(new InputFilter[]{new InputFilter.LengthFilter(DEFAULT_MSG_LENGTH_LIMIT)});


            mSendButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // TODO: Send messages on click

                    if(mUsername!=null) {

                        MessageClass friendlyMessage = new MessageClass(mUsername, mMessageEditText.getText().toString(),mUid);
                        mDatabaseReference.push().setValue(friendlyMessage);
                    }
                    else
                        Toast.makeText(ChatSupport.this, "Sorry, please try again.", Toast.LENGTH_SHORT).show();
                    // Clear input box
                    mMessageEditText.setText("");
                }
            });


    }



    public void attachDatabaseReadListener() {
        if (mchildEventListener == null) {
            mchildEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    MessageClass friendlyMessage = dataSnapshot.getValue(MessageClass.class);
                    if(friendlyMessage.getUid()!=null && friendlyMessage.getUid().equals(mUid)) {
                        
                        friendlyMessage.setSender("You");
                    }
                    messages.add(friendlyMessage);

                    adapter.notifyDataSetChanged();
                    recyclerView.scrollToPosition(messages.size()-1);


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
            mDatabaseReference.addChildEventListener(mchildEventListener);
        }
    }


        @Override
        protected void onStart() {
            super.onStart();
            attachDatabaseReadListener();
        }

        @Override
        protected void onPause() {
            super.onPause();

            clear();
            detachDatabaseReadListener();
        }

        public void clear(){
            int size = messages.size();
            messages.clear();
            adapter.notifyItemRangeRemoved(0, size);
        }

        //this is for signedoutcleanup to detach read event listener
        public void detachDatabaseReadListener(){
            if(mchildEventListener!= null)
                mDatabaseReference.removeEventListener(mchildEventListener);

            mchildEventListener= null;

        }
}
