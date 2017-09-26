package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yugank on 22/6/17.
 */

public class SessionAdapter extends RecyclerView.Adapter<SessionAdapter.RecyclerViewholder3>{

    private ArrayList<String> arrayList = new ArrayList<String>();
    Context mContext;
    public SessionAdapter(ArrayList<String> arrayList, Context mContext)
    {
        this.arrayList= arrayList;
        this.mContext= mContext;
    }

    @Override
    public RecyclerViewholder3 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.session_recycler_item,parent,false);
        RecyclerViewholder3 recyclerViewholder= new RecyclerViewholder3(view, mContext, arrayList);
        return recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewholder3 holder, int position) {

        String string= arrayList.get(position);
        holder.session_tv.setText(string);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewholder3 extends RecyclerView.ViewHolder implements  View.OnClickListener{


        TextView session_tv;
        ArrayList<String> sessions= new ArrayList<String>();
        Context ctx;

        public RecyclerViewholder3(View itemView, Context ctx, ArrayList<String> sessions) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.sessions= sessions;
            this.ctx= ctx;
            session_tv= (TextView) itemView.findViewById(R.id.session_tv);
        }

        @Override
        public void onClick(View v) {
            int position= getAdapterPosition();

            switch(position)
            {

                case 0:
                {
                    Intent intt= new Intent();
                    intt.setClass(this.ctx, members_activity.class);
                    intt.putExtra("DIRECTOR", 1);

                    this.ctx.startActivity(intt);
                    break;
                }


                case 1:
                {
                    Intent intt= new Intent();
                    intt.setClass(this.ctx, members_activity.class);
                    intt.putExtra("DIRECTOR", 2);

                    this.ctx.startActivity(intt);
                    break;
                }
                case 2:
                {
                    Intent intt= new Intent();
                    intt.setClass(this.ctx, members_activity.class);
                    intt.putExtra("DIRECTOR", 3);
                    this.ctx.startActivity(intt);
                    break;
                }

                }
            }
        }
    }


