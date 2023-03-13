package com.bgmiddleeast.Adapter;

/**
 * Created by Sreenadh on 05-Jan-17.
 */
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;

import java.util.ArrayList;
import java.util.Locale;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.MyViewHolder> {

    private ArrayList<String> placesList=new ArrayList<>();
    private ArrayList<String> placenameList;
    Context context;
    static OnItemClickListener clickListener;;
    Model model;


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView place;

        public MyViewHolder(View view) {
            super(view);
            place = (TextView) view.findViewById(R.id.txt_place);
            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(view, getPosition());
        }
    }


    public SearchListAdapter(ArrayList<String> countryList) {
//        this.context = context;
        model=new Model();
        placesList.size();
        this.placesList = countryList;
        this.placenameList=new ArrayList<>();
        this.placenameList.addAll(placesList);



    }
    View itemView;
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (model.Language.equals( "en" ))
         itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_item, parent, false);
        else
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.search_item_ar, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
     holder.place.setText(placesList.get(position));
    }

    @Override
    public int getItemCount() {
        return placesList.size();
    }


    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        placesList.clear();
        if (charText.length() == 0) {
            placesList.addAll(placenameList);
        } else {
            for (String temp : placenameList) {
                if (temp.toLowerCase(Locale.getDefault()).contains(charText)) {
                    placesList.add(temp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


}