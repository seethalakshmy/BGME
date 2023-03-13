package com.bgmiddleeast.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bgmiddleeast.Activity.VideoViewActivity;
import com.bgmiddleeast.Adapter.VideoAdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Videos;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideosFragment extends Fragment {
    RecyclerView mRecyclerView;
    private ArrayList<Videos> mVideoList;
    VideoAdapter adapter;
    Model model;
    FrameLayout mFrame;
    TextView mTextMessage;

    public static VideosFragment newInstance(int n) {
        VideosFragment videosFragment=new VideosFragment();
        Bundle b=new Bundle();
        b.putInt("n",n);

        videosFragment.setArguments(b);
        return videosFragment;
    }


    public VideosFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_video, container, false);
int n=getArguments().getInt( "n" );
        mRecyclerView = (RecyclerView) v.findViewById(R.id.recycler_video);
        mTextMessage=(TextView)v.findViewById(R.id.text_msg);
        model = new Model();
        String videos = model.ServiceList.get(model.ServicePosition).getVideo_links();
        mVideoList = new ArrayList<>();
        mVideoList = Parser.getVideos(videos);
        mFrame=(FrameLayout)v.findViewById(R.id.no_frame) ;
        mFrame.setVisibility(View.INVISIBLE);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        if (mVideoList != null && mVideoList.size() != 0) {
            adapter = new VideoAdapter(getActivity(), mVideoList);
            mRecyclerView.setAdapter(adapter);

            adapter.setOnItemClickListener(new VideoAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    if (!mVideoList.get(position).getVideo_url().equals("")) {
                        startActivity(new Intent(getActivity(), VideoViewActivity.class));
                        Model.videolink = mVideoList.get(position).getVideo_url();
                        Model.videoTitle = mVideoList.get(position).getVideo_title();
                    } else {
                        if (model.Language.equals("en"))
                            Toast.makeText(getContext(), getString(R.string.no_video), Toast.LENGTH_SHORT).show();

                        else {
                            Toast.makeText(getContext(), getString(R.string.no_video_ar), Toast.LENGTH_SHORT).show();

                        }
                    }
                }
            });
        }else{
            mFrame.setVisibility(View.VISIBLE);
            if (model.Language.equals("en"))
            mTextMessage.setText(getString(R.string.no_video));
            else
                mTextMessage.setText(getString(R.string.no_video_ar));
        }


        return v;
    }

}
