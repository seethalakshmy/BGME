package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;

public class SocialActivity extends AppCompatActivity {
    Model model;
    LinearLayout mProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new Model();
        if (model.Language.equals("en"))

        setContentView(R.layout.activity_social);
        else{
            setContentView(R.layout.activity_social_ar);
        }

        Toolbar mToolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);

        TextView mHead = (TextView) mToolbar.findViewById(R.id.txt_header_plain);

        if (Model.Language.equals( "en" ))
            mHead.setText( getString( R.string.social ) );
        else
            mHead.setText( getString( R.string.social_ar ) );

        mProgress=(LinearLayout) findViewById(R.id.loader);
        mProgress.setVisibility(View.VISIBLE);
        WebView  webView = (WebView) findViewById(R.id.webView);
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgress.getVisibility()==view.VISIBLE) {
                    mProgress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(SocialActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });
        if(model.social.equals("fb")){
            webView.loadUrl("https://www.facebook.com/people/BG-Middle-East/100003592333411");
        }else if(model.social.equals("youtub"))
        webView.loadUrl("https://www.youtube.com/channel/UCwrNem9BixjwJ7pb4zV96Ow");


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                    finish();
                    startActivity(new Intent(SocialActivity.this, SocialListActivity.class));

            }
        });
    }
//        webView.loadUrl("https://www.facebook.com/people/BG-Middle-East/100003592333411");






    public void onBackPressed() {

            finish();
            startActivity(new Intent(SocialActivity.this, SocialListActivity.class));


    }
}
