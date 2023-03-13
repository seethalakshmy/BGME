package com.bgmiddleeast.Adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Videos;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 */
public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

	Context context;
	OnItemClickListener onItemClickListener;
	ArrayList<Videos> mVideoList;
	TextView mVideoTitle;
	View V;
	Model model;
	public VideoAdapter(Context context, ArrayList<Videos> mVideoList) {
		this.context=context;
		this.mVideoList=mVideoList;
	}

	public  class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

		ImageView mThumbnail;
		RelativeLayout mRelativePlay;

		public ViewHolder(View v) {
			super(v);
			model=new Model();
			mThumbnail=(ImageView)v.findViewById(R.id.img_preview);
			mRelativePlay=(RelativeLayout)v.findViewById(R.id.rl_play);
			mVideoTitle=(TextView)v.findViewById(R.id.txt_video);
			v=(View)v.findViewById(R.id.view_lin) ;
			v.setBackgroundColor(Color.parseColor(model.ThemeColor));
			mVideoTitle.setTextColor(Color.parseColor(model.ThemeColor));
			mRelativePlay.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
			onItemClickListener.onItemClick(v,getAdapterPosition());
		}
	}



	@Override
	public VideoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View v;
		if (model.Language.equals("en"))
		 v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item, parent, false);
		else
			v = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_item_ar, parent, false);
		ViewHolder vh = new ViewHolder(v);
		return vh;
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position) {
		try{
		String video_id = getVideoId(mVideoList.get(position).getVideo_url());
		String img_url = "http://img.youtube.com/vi/" + video_id + "/0.jpg";
		System.out.println("video id = " + video_id + " ,image url = " + img_url);

		Glide.with(context).load(img_url).placeholder(R.drawable.video_place_holder).into(holder.mThumbnail);
		if (!mVideoList.get(position).getVideo_title().equals(""))
			mVideoTitle.setText(mVideoList.get(position).getVideo_title());
	}catch(Exception e){

		}
	}

	@Override
	public int getItemCount() {
		return mVideoList.size();
	}


	private String getVideoId(String url){



		String pattern = "(?<=youtu.be/|watch\\?v=|/videos/|embed\\/)[^#\\&\\?]*";
		Pattern compiledPattern = Pattern.compile(pattern);
		Matcher matcher = compiledPattern.matcher(url);
		if(matcher.find()){
			return matcher.group();
		} else {
			return "error";
		}
	}

	public interface OnItemClickListener {
		public void onItemClick(View view, int position);
	}


	public void setOnItemClickListener(final OnItemClickListener itemClickListener) {
		this.onItemClickListener = itemClickListener;
	}

}
