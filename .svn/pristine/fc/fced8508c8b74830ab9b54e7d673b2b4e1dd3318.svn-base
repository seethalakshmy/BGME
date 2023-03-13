package com.bgmiddleeast.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Document;

import java.util.ArrayList;

/**
 * Created by seetha on 1/2/2017.
 */

public class GridItemAdapter extends RecyclerView.Adapter<GridItemAdapter.ViewHolder> {
    private ArrayList<Document> itemList;
    Context context;
    static OnItemClickListener clickListener;
    Model model=new Model();

    public GridItemAdapter(ArrayList<Document> itemList, Context context) {
        this.itemList = itemList;
        this.context=context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view;
        if (model.Language.equals("en"))
             view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item, viewGroup, false);
        else
            view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.grid_item_ar, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }



    @Override
    public void onBindViewHolder(final GridItemAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_country.setText(itemList.get(i).getTitle());



    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView tv_country;
        private RelativeLayout frame;
        private ImageView mThumb;

        public ViewHolder(View view) {
            super(view);
            frame = (RelativeLayout) view.findViewById(R.id.frame);
            tv_country = (TextView) view.findViewById(R.id.txt_title);
            view.setOnClickListener(this);

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