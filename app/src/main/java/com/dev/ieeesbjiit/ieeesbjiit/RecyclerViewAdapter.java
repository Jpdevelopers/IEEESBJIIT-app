package com.dev.ieeesbjiit.ieeesbjiit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;



public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewholder>{

    private ArrayList<Item_data> arrayList = new ArrayList<Item_data>();
    Context mContext;
    public RecyclerViewAdapter(ArrayList<Item_data> arrayList, Context mContext)
    {
        this.arrayList= arrayList;
        this.mContext= mContext;
    }

    @Override
    public RecyclerViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item,parent,false);
        RecyclerViewholder recyclerViewholder= new RecyclerViewholder(view, mContext, arrayList);
        return recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewholder holder, int position) {

        Item_data item_data= arrayList.get(position);

        holder.imageView.setImageResource(item_data.getUrl());
        holder.title_tv.setText(item_data.getTitle());
        holder.subtitle_tv.setText(item_data.getSubtitle());
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class RecyclerViewholder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        ImageView imageView;
        TextView title_tv, subtitle_tv;
        ArrayList<Item_data> items= new ArrayList<Item_data>();
        Context ctx;

        public RecyclerViewholder(View itemView, Context ctx, ArrayList<Item_data> items) {
            super(itemView);

            itemView.setOnClickListener(this);
            this.items= items;
            this.ctx= ctx;
            imageView= (ImageView) itemView.findViewById(R.id.item_image);
            title_tv= (TextView) itemView.findViewById(R.id.item_title);
            subtitle_tv= (TextView) itemView.findViewById(R.id.item_subtitle);
        }


        @Override
        public void onClick(View v) {
            int position= getAdapterPosition();

            switch(position)
            {
                case 0:
                {
                    Intent intt= new Intent();
                    intt.setClass(this.ctx, PreviousEvents.class);
                    intt.putExtra("DIRECTOR", 1);
                    this.ctx.startActivity(intt);
                    break;
                }
                case 1:
                {
                    Intent intt= new Intent();
                    intt.setClass(this.ctx, PreviousEvents.class);
                    intt.putExtra("DIRECTOR", 2);
                    this.ctx.startActivity(intt);
                    break;
                }



            }
        }
    }

}

