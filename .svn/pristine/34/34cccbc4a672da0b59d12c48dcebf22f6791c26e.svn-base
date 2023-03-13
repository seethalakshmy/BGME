package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.ConnectionDetector;

public class SocialListActivity extends AppCompatActivity {
    LinearLayout mFacebook, mInstagram;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Model model = new Model();
        if (model.Language.equals("en"))
        setContentView(R.layout.activity_social_list);
        else{
            setContentView(R.layout.activity_social_list_ar);
        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);
        mToolbar.setTitle("");
        mFacebook = (LinearLayout) findViewById(R.id.facebook);
        mInstagram = (LinearLayout) findViewById(R.id.instagram);
        final ConnectionDetector  mConDect = new ConnectionDetector(this);
        mFacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mConDect.isConnectingToInternet()) {

                    finish();
                    model.social = "fb";
                    startActivity(new Intent(SocialListActivity.this, SocialActivity.class));
                    //overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                }else{
                    Toast.makeText(SocialListActivity.this, "Please connect to network", Toast.LENGTH_SHORT).show();
                }
            }
        });
        mInstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mConDect.isConnectingToInternet()) {

                    model.social = "youtub";
                    finish();
//                Toast.makeText(SocialListActivity.this, "No content to display", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SocialListActivity.this, SocialActivity.class));
                    //overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                }else{
                    Toast.makeText(SocialListActivity.this, "Please connect to network", Toast.LENGTH_SHORT).show();
                }
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();
                startActivity(new Intent(SocialListActivity.this, NewDashboard.class));


            }
        });
    }
    public void onBackPressed() {

        finish();
        startActivity(new Intent(SocialListActivity.this, NewDashboard.class));


    }

}
