package com.bgmiddleeast.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.notification;

import java.util.ArrayList;


public class NotificationListAdapter extends RecyclerView.Adapter<NotificationListAdapter.ViewHolder> {

    Context context;
    ArrayList<notification> NotificationList;
    Model model;

    public NotificationListAdapter(Context context, ArrayList<notification> notificationList) {
model=new Model();
        this.context=context;
        this. NotificationList=notificationList;
    }

    View view;
    @Override
    public NotificationListAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        if (model.Language.equals( "en" ))
         view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_item, viewGroup, false);
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.notification_item_ar, viewGroup, false);



        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final NotificationListAdapter.ViewHolder viewHolder, int i) {

     //   viewHolder.tv_country.setText(itemList.get(i));


            viewHolder.mTxt_Not_Title.setText(NotificationList.get( i ).getTitle());
            viewHolder.mTxt_Not_Desc.setText(NotificationList.get( i ).getMessage());
            viewHolder.mTxt_Date.setText(NotificationList.get( i ).getTime());


    }



    @Override
    public int getItemCount() {
        return NotificationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTxt_Not_Title,mTxt_Date,mTxt_Not_Desc;


        public ViewHolder(View view) {
            super(view);
            mTxt_Date=(TextView)view.findViewById(R.id.txt_date);
            mTxt_Not_Desc=(TextView)view.findViewById(R.id.txt_not_description);
            mTxt_Not_Title=(TextView)view.findViewById(R.id.txt_not_title);

        }
    }
}
