package com.bgmiddleeast.Adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bgmiddleeast.R.id.checkBox;

/**
 * Created by seetha on 4/18/2017.
 */
public class ServiceCheckdapter extends RecyclerView.Adapter<ServiceCheckdapter.ViewHolder> {


    public static List<Service> ServiceList = new ArrayList<Service>();
    public static List<Service> ServiceL= new ArrayList<Service>();

    Context context;
    static OnItemClickListener clickListener;
    Model model = new Model();

    public ServiceCheckdapter(Context context, ArrayList<Service> serviceList) {
        this.context = context;
        ServiceList = serviceList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if (model.Language.equals("en"))
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_check_item, viewGroup, false);
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.service_check_item, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder versionViewHolder, final int i) {

        final int position=i;
        versionViewHolder.ServiceName.setText(ServiceList.get(i).getTitle());
        versionViewHolder.ServiceName.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //is chkIos checked?
                Service service=new Service();
                if (((CheckBox) v).isChecked()) {

                    ServiceL.add(ServiceList.get(position));
                }
                else {
                    ServiceL.remove(ServiceList.get(position));
                }

            }
        });
    }

    @Override
    public int getItemCount() {

        return ServiceList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cardItemLayout;
        CheckBox ServiceName;



        public ViewHolder(View itemView) {
            super(itemView);

            cardItemLayout = (CardView) itemView.findViewById(R.id.cardlist_item);
            ServiceName = (CheckBox) itemView.findViewById(checkBox);

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
