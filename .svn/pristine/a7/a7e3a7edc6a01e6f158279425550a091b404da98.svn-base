package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bgmiddleeast.Adapter.NotificationListAdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.AppManager;
import com.bgmiddleeast.Utilities.ConfigureURLS;
import com.bgmiddleeast.Utilities.notification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Sreenadh on 07-Jan-17.
 */

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {
    RecyclerView mRecyclerView;
    NotificationListAdapter notificationListAdapter;
    ImageButton mImgBtnClose;
    Model model;
    ArrayList<notification> NotificationList;
    RecyclerView.LayoutManager mLayoutManager;
    AppManager mAppManager;
    TextView mTextHead;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Model model=new Model();
        if (model.Language.equals( "en" ))
        setContentView(R.layout.activity_notification);
        else
            setContentView(R.layout.activity_notification_ar);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) toolbar.findViewById(R.id.left_back);
        mTextHead= (TextView) toolbar.findViewById(R.id.txt_header_plain);

        if (Model.Language.equals( "en" ))
            mTextHead.setText("Notifications");
        else
            mTextHead.setText( getString( R.string.notification_ar ) );
        mAppManager=AppManager.getInstance( this );
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(NotificationActivity.this, NewDashboard.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
        NotificationList=new ArrayList<>( );
        getNotificationList();



    }
    private void getNotificationList() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_notification;
        } else {
            url = ConfigureURLS.URL_ar_notification;
        }


        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        System.out.println("GET_Country_list === " + response);
                        try {
                            boolean mIsSuccess = Parser.getNotificationList(response.toString());
                            if (mIsSuccess) {
                                NotificationList = Parser.getDataNotification();
                                if (NotificationList != null && NotificationList.size() != 0) {
                                    setNotification();

                                }

                            } else {


                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put( "user_id", mAppManager.getUserId());



                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void setNotification() {
        Collections.reverse(NotificationList);
        notificationListAdapter = new NotificationListAdapter(this,NotificationList);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(notificationListAdapter);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {


        }

    }
    @Override
    public void onBackPressed() {
        Intent i=new Intent(NotificationActivity.this, NewDashboard.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();


    }
}
