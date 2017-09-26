package com.dev.ieeesbjiit.ieeesbjiit;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by yugank on 25/9/17.
 */

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.RecyclerViewholder5>{

    private ArrayList<EventClass> arrayList = new ArrayList<EventClass>();
    Context mContext;

    public EventAdapter(ArrayList<EventClass> arrayList, Context mContext)
    {
        this.arrayList= arrayList;
        this.mContext= mContext;
    }

    @Override
    public RecyclerViewholder5 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.event_card,parent,false);
        RecyclerViewholder5 recyclerViewholder= new RecyclerViewholder5(view, mContext, arrayList);
        return recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewholder5 holder, int position) {

        EventClass eventClass= arrayList.get(position);

        if(eventClass.getTime()!= null)
        {
            holder.register_button.setVisibility(View.VISIBLE);
            holder.register_button.setEnabled(true);
            holder.event_time_tv.setText(eventClass.getTime());
            holder.register_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Congratulations, You have been registered.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        holder.event_name_tv.setText(eventClass.getEvent_name());
        holder.event_date_tv.setText(eventClass.getDate());
        holder.event_venue_tv.setText(eventClass.getVenue());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewholder5 extends RecyclerView.ViewHolder implements  View.OnClickListener{


        TextView event_name_tv, event_time_tv, event_date_tv, event_venue_tv;
        ArrayList<EventClass> events= new ArrayList<EventClass>();
        Context ctx;
        Button register_button;
        public RecyclerViewholder5(View itemView, Context ctx, ArrayList<EventClass> events) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.events= events;
            this.ctx= ctx;
            event_name_tv= (TextView) itemView.findViewById(R.id.event_name_tv);
            event_date_tv= (TextView) itemView.findViewById(R.id.event_date_tv);
            event_time_tv= (TextView) itemView.findViewById(R.id.event_time_tv);
            event_venue_tv= (TextView) itemView.findViewById(R.id.event_venue_tv);
            register_button= (Button) itemView.findViewById(R.id.reg_button);
        }

        @Override
        public void onClick(View v) {
            int position= getAdapterPosition();

            Dialog dialog= new Dialog(this.ctx);
            dialog.setContentView(R.layout.dialog_view);
            dialog.setTitle("About "+ events.get(position).getEvent_name());
            TextView information= (TextView) dialog.findViewById(R.id.info_tv);
            information.setText(events.get(position).getinfo());
//            b.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    textview1.setText(txtbox1.getText().toString());
//                    textview2.setText(txtbox2.getText().toString());
//
//
//                    dialog.dismiss();
//                }
//            });

            dialog.show();
        }
    }
}


