package com.bgmiddleeast.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.Html;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Tools;
import com.bumptech.glide.Glide;

import org.xml.sax.XMLReader;

import java.util.ArrayList;

/**
 * Created by Sreenadh on 28-Apr-17.
 */

public class ToolDescriptionActivity extends AppCompatActivity {


    private TextView mTxtDescHeading, mTxtmodel, mHeader;
    private WebView mTxtDesc;
    private ImageView mImgDesc;
    private View mView;
    private String color, desc, img_url, desc_heading, model;
    public static ArrayList<Tools> ToolList;
    Toolbar mToolbar;
    Model modelv;
    final String mimeType = "text/html";
    final String encoding = "UTF-8";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        modelv=new Model();
        if(modelv.Language.equals("en"))
        setContentView(R.layout.activity_product_description);
        else
            setContentView(R.layout.activity_product_description_ar);


        mToolbar = (Toolbar) findViewById(R.id.pdt_desc_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);

        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        color = Model.ThemeColor;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.parseColor(color));

        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
                finish();
            }
        });

        mToolbar.setBackgroundColor(Color.parseColor(color));
        mHeader = (TextView) mToolbar.findViewById(R.id.txt_header_plain);
        mTxtDescHeading = (TextView) findViewById(R.id.txt_desc_heading);
        mTxtDesc = (WebView) findViewById(R.id.txt_desc);
        mImgDesc = (ImageView) findViewById(R.id.img_desc);
        mTxtmodel = (TextView) findViewById(R.id.txt_model);
        mView = (View) findViewById(R.id.view_desc);
        ToolList = Model.ToolList;

        for (int i = 0; i < ToolList.size(); i++) {
            if (ToolList.get(i).getTool_id().equals(Model.ToolId)) {
                desc = ToolList.get(i).getTool_description();
                img_url = ToolList.get(i).getTool_image();
                desc_heading = ToolList.get(i).getTool_title();
                model = ToolList.get(i).getTool_model();
            }
        }


        mHeader.setText(desc_heading);
        mTxtDescHeading.setText(desc_heading);
        mTxtDescHeading.setTextColor(Color.parseColor(color));
        mView.setBackgroundColor(Color.parseColor(color));

        mTxtDesc .setBackgroundColor(Color.TRANSPARENT);


        mTxtmodel.setText(model);
        Glide.with(this)
                .load(img_url)

//                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(mImgDesc);
        System.out.println("Description== " + desc);



        if (!desc.equals("")) {

            desc = desc.replace("\'", "");

//            String head1 = "<head><style>@font-face {font-family: 'MyFont';src: url('file:///android_asset/fonts/HELR45W.ttf');}body {font-family: 'MyFont';}</style></head>";
//            String text="<html>"+head1
//                    + "<body style=\"font-family: MyFont\">" + desc
//                    + "</body></html>";
            String head1 = "<head><style>@font-face {" +
                    "body {" +
                    "font-size: 18;" +
                    "}</style></head>";

            String text="<html>"+head1
                    + "<body style=\"font-size: 18\">" + desc
                    + "</body></html>";

            mTxtDesc.loadDataWithBaseURL("", text, mimeType, encoding, "");


        }


       /* if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            mTxtDesc.setText(Html.fromHtml(desc, Html.FROM_HTML_MODE_COMPACT));
        } else {
            mTxtDesc.setText(Html.fromHtml(desc, null, new UlTagHandler()));
        }
*/
        mToolbar.setTitle(desc_heading);

    }

    public class UlTagHandler implements Html.TagHandler {
        @Override
        public void handleTag(boolean opening, String tag, Editable output,
                              XMLReader xmlReader) {
            if (tag.equals("ul") && !opening) output.append("\n");
            if (tag.equals("li") && opening) output.append("\n\t•");
        }
    }
}
