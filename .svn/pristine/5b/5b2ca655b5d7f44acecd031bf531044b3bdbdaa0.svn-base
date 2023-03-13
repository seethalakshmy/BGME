package com.bgmiddleeast.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;

public class DocViewActivity extends AppCompatActivity {

    Model model;
    LinearLayout mProgress;
    String Url;
    String doc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        if(model.Language.equals("en"))
        setContentView( R.layout.activity_doc_view );
        else
            setContentView( R.layout.activity_doc_view_ar );


        Toolbar mToolbar = (Toolbar) findViewById( R.id.plain_toolbar );
        setSupportActionBar( mToolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( false );
        ImageButton back = (ImageButton) mToolbar.findViewById( R.id.left_back );
        mToolbar.setTitle( "" );

        mProgress = (LinearLayout) findViewById( R.id.loader );
        mProgress.setVisibility( View.VISIBLE );
        WebView webView = (WebView) findViewById( R.id.webView );
        WebSettings settings = webView.getSettings();

        settings.setJavaScriptEnabled( true );
        webView.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);

        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setUseWideViewPort(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        Url = model.DocURL;



        webView.getSettings().setJavaScriptEnabled( true );
        webView.getSettings().setPluginState( WebSettings.PluginState.ON );




        doc="<iframe src='http://docs.google.com/viewer?url="+Url+"' width='100%' height='100%' style='border: none;'></iframe>";
        webView.setWebViewClient(new MyWebClient());
        webView.setWebChromeClient(new WebChromeClient());
//        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setAllowFileAccess(true);
        webView.getSettings().setDomStorageEnabled(true);
        String extension = Url.substring( Url.lastIndexOf( "." ) + 1 );

        if (extension.toLowerCase().equals( "jpeg" ) || extension.toLowerCase().equals( "png" ) || extension.toLowerCase().equals( "jpg" )) {
            webView.loadUrl( Url );
        } else {
            webView.loadUrl( "http://docs.google.com/gview?embedded=true&url=" + Url );

        }

//                webView.loadUrl("http://docs.google.com/gview?embedded=true&url="+Url);


//        String doc="<'iframe src=http://docs.google.com/viewer?url=+Url
//        width='100%' height='100%'
//        style='border: none;'></iframe>";

//        String ur="https://docs.google.com/gview?embedded=true&url=http://bgmiddleeast.worldatclick.com/uploads/documents/1496677216.pdf";
////
//        doc="<iframe src="+ur+"></iframe>";
////        doc="https://docs.google.com/gview?embedded=true&url="+ Url;
//        WebView  wv = (WebView)findViewById(R.id.webView);
//        wv.getSettings().setJavaScriptEnabled(true);
//        wv.getSettings().setPluginState(WebSettings.PluginState.ON);
//        wv.getSettings().setAllowFileAccess(true);
//        wv.loadUrl(doc);

//        webView.getSettings().setJavaScriptEnabled(true);
////        webView.getSettings().setPluginsEnabled(true);
//        webView.getSettings().setAllowFileAccess(true);
//        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
////        webView.setWebViewClient(new Callback());
//        webView.loadData(doc, "text/html", "UTF-8");
//        webView.loadUrl("https://docs.google.com/gview?embedded=true&url=http://bgmiddleeast.worldatclick.com/uploads/documents/1496677216.pdf");


      /*  webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String extension = Url.substring( Url.lastIndexOf( "." ) + 1 );

                if (extension.toLowerCase().equals( "jpeg" ) || extension.toLowerCase().equals( "png" ) || extension.toLowerCase().equals( "jpg" )) {
                    view.loadUrl( Url );
                } else {
                    view.loadUrl( "http://docs.google.com/gview?embedded=true&url=" + Url );

                }
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if (mProgress.getVisibility() == view.VISIBLE) {
                    mProgress.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(DocViewActivity.this, "Error:" + description, Toast.LENGTH_SHORT).show();

            }
        });*/
//        if (Url.equals("")) {
//
//        } else
//            webView.loadUrl(Url);


        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();


            }
        } );

    }

    private  class  MyWebClient extends  WebViewClient{

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            view.loadUrl(url);
            return true;
        }
        @Override
        public void onPageFinished(WebView view, String url) {
            if (mProgress.getVisibility() == view.VISIBLE) {
                mProgress.setVisibility(View.GONE);
            }
        }
    }


}
