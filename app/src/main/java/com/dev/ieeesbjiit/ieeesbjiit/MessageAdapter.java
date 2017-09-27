package com.dev.ieeesbjiit.ieeesbjiit;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yugank on 28/9/17.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.RecyclerViewholder2> {
    private ArrayList<MessageClass> messages= new ArrayList<MessageClass>();
    //String name;

    public MessageAdapter(ArrayList<MessageClass> messages) {
        this.messages = messages;
      //  this.name= name;
    }

    @Override
    public RecyclerViewholder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.message_layout,parent,false);
        RecyclerViewholder2 recyclerViewholder= new RecyclerViewholder2(view);
        return  recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewholder2 holder, int position) {

        MessageClass message= messages.get(position);

        if(!message.getSender().equals("You")) {

            holder.cardView.setCardBackgroundColor(Color.rgb(255,255,255));
            holder.sender_tv.setTextColor(Color.rgb(200,0,0));
            holder.sender_tv.setText(message.getSender());
        }
        else
        {
            holder.cardView.setCardBackgroundColor(Color.rgb(204,255,144));
            holder.sender_tv.setTextColor(Color.rgb(0,150,136));
            holder.sender_tv.setText("You");

            LinearLayout.LayoutParams params= new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.gravity= Gravity.END;
            params.setMargins(30,0,0,10);
            holder.cardView.setLayoutParams(params);

        }


            holder.content_tv.setText(message.getContent());
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }




    public static class RecyclerViewholder2 extends RecyclerView.ViewHolder {

        CardView cardView;
        TextView sender_tv, content_tv;
        LinearLayout messageLayout;

        public RecyclerViewholder2(View itemView) {
            super(itemView);
            cardView= (CardView) itemView.findViewById(R.id.msg_card);
            sender_tv= (TextView) itemView.findViewById(R.id.name_display);
            content_tv= (TextView) itemView.findViewById(R.id.msg_display);
        }
    }

}
