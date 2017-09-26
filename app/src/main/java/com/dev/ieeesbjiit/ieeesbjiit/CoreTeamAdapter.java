package com.dev.ieeesbjiit.ieeesbjiit;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by yugank on 26/9/17.
 */

public class CoreTeamAdapter extends RecyclerView.Adapter<CoreTeamAdapter.RecyclerViewholder4> {
    private ArrayList<MemberClass> members= new ArrayList<MemberClass>();
    //String name;

    public CoreTeamAdapter(ArrayList<MemberClass> members) {
        this.members = members;
    }

    @Override
    public RecyclerViewholder4 onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.team_member_card,parent,false);
        RecyclerViewholder4 recyclerViewholder= new RecyclerViewholder4(view);
        return  recyclerViewholder;
    }

    @Override
    public void onBindViewHolder(RecyclerViewholder4 holder, int position) {

        MemberClass memberClass= members.get(position);
        if(memberClass.getPhotourl()==null) {
            Glide.with(holder.member_dp.getContext())
                    .load(memberClass.getUrl())
                    .into(holder.member_dp);
        }
        else
        {
            Glide.with(holder.member_dp.getContext())
                    .load(memberClass.getPhotourl())
                    .into(holder.member_dp);
        }

        Log.d("Check1", "Yes it rerrer");
        holder.member_name_tv.setText(memberClass.getMember_name());
        holder.member_post_tv.setText(memberClass.getMember_post());
        holder.member_email_tv.setText(memberClass.getMember_email());
        if(memberClass.getMember_work()!=null)
        holder.member_work_tv.setText(memberClass.getMember_work());

    }

    @Override
    public int getItemCount() {
        return members.size();
    }




    public static class RecyclerViewholder4 extends RecyclerView.ViewHolder {


        TextView member_name_tv, member_post_tv, member_email_tv, member_work_tv;
        ImageView member_dp;


        public RecyclerViewholder4(View itemView) {
            super(itemView);
            member_name_tv= (TextView) itemView.findViewById(R.id.member_name_tv);
            member_post_tv= (TextView) itemView.findViewById(R.id.post_tv);
            member_email_tv= (TextView) itemView.findViewById(R.id.member_email_tv);
            member_work_tv= (TextView) itemView.findViewById(R.id.working_at_tv);
            member_dp= (CircleImageView) itemView.findViewById(R.id.circleImageView2);

        }
    }

}
