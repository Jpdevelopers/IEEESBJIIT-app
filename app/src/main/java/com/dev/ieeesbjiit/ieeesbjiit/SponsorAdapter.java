package com.dev.ieeesbjiit.ieeesbjiit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by yugank on 27/9/17.
 */

public class SponsorAdapter extends RecyclerView.Adapter<SponsorAdapter.RecyclerViewholder6> {
    private ArrayList<SponsorClass> members= new ArrayList<SponsorClass>();
    //String name;

    public SponsorAdapter(ArrayList<SponsorClass> members) {
        this.members = members;
    }

    @Override
    public RecyclerViewholder6 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.sponsor_card,parent,false);
        RecyclerViewholder6 recyclerViewholder= new RecyclerViewholder6(view);
        return  recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewholder6 holder, int position) {

        SponsorClass sponsorClass= members.get(position);
        Glide.with(holder.sponsor_logo.getContext())
                .load(sponsorClass.getPhotourl())
                .into(holder.sponsor_logo);
        holder.sponsor_name_tv.setText(sponsorClass.getName());
        holder.sponsor_cat_tv.setText(sponsorClass.getCategory());


    }

    @Override
    public int getItemCount() {
        return members.size();
    }




    public static class RecyclerViewholder6 extends RecyclerView.ViewHolder {


        TextView sponsor_name_tv, sponsor_cat_tv;
        ImageView sponsor_logo;


        public RecyclerViewholder6(View itemView) {
            super(itemView);

            sponsor_name_tv= (TextView) itemView.findViewById(R.id.sponsor_name_tv);
            sponsor_cat_tv= (TextView) itemView.findViewById(R.id.sponsor_category_tv);
            sponsor_logo= (ImageView) itemView.findViewById(R.id.sponsor_logo);



        }
    }

}
