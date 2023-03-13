package com.bgmiddleeast.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Service;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by seetha on 4/18/2017.
 */
public class ServiceListAdapter extends RecyclerView.Adapter<ServiceListAdapter.ViewHolder> {


    public static List<Service> ServiceList = new ArrayList<Service>();

    Context context;
    static OnItemClickListener clickListener;
    Model model = new Model();

    public ServiceListAdapter(Context context, ArrayList<Service> serviceList) {
        this.context = context;
        ServiceList = serviceList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if (model.Language.equals("en"))
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.servicelist_item, viewGroup, false);
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.servicelist_item_ar, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder versionViewHolder, int i) {
        if (ServiceList.size() > 0) {
            Glide.with(context)
                    .load(ServiceList.get(i).getIcon_image())
                    .crossFade()
                    .into(versionViewHolder.IconImage);
        } else {

            Glide.clear(versionViewHolder.IconImage);
        }
//        Picasso.with(context)
//                .load(ServiceList.get(i).getIcon_image())
//                .placeholder(R.drawable.ic_service)
//                .into(versionViewHolder.IconImage);


        versionViewHolder.ServiceName.setText(ServiceList.get(i).getTitle());
        versionViewHolder.linColor.setBackgroundColor(Color.parseColor("#" + ServiceList.get(i).getColour_code()));

    }

    @Override
    public int getItemCount() {

        return ServiceList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        TextView ServiceName;
        LinearLayout linColor;
        ImageView IconImage;


        public ViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            ServiceName = (TextView) itemView.findViewById(R.id.txt_ServiceName);
            linColor = (LinearLayout) itemView.findViewById(R.id.lin_color_code);
            IconImage = (ImageView) itemView.findViewById(R.id.img_icon);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(v, getPosition());
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


}
