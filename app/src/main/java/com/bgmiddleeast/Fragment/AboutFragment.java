package com.bgmiddleeast.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;

import org.xml.sax.XMLReader;


/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {
    TextView mHead, mSymptomTitle;
    View colorBar, colorBar_sypmtoms;
    View v;
    Model model;
    WebView Detail, symptoms;

    final String mimeType = "text/html";
    final String encoding = "UTF-8";

    public  static AboutFragment newInstance(int n) {

        AboutFragment aboutFragment=new AboutFragment();
        Bundle b=new Bundle();
        b.putInt("n",n);

        aboutFragment.setArguments(b);

        return aboutFragment;
    }

    public AboutFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        model = new Model();
        if (model.Language.equals("en"))
            v = inflater.inflate(R.layout.fragment_about, container, false);
        else
            v = inflater.inflate(R.layout.fragment_about_ar, container, false);
        int n=getArguments().getInt( "n" );

        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mHead = (TextView) view.findViewById(R.id.txt_head);
        mSymptomTitle = (TextView) view.findViewById(R.id.txt_symptoms);
        colorBar = (View) view.findViewById(R.id.view_color);
        colorBar_sypmtoms = (View) view.findViewById(R.id.view_syptoms_color);
        Detail = (WebView) view.findViewById(R.id.web_details);

        symptoms = (WebView) view.findViewById(R.id.web_details_symptoms);
        Detail.setVisibility( View.GONE );
        mHead.setVisibility( View.GONE );
        mHead.setTextColor(Color.parseColor(model.ThemeColor));
        mSymptomTitle.setTextColor(Color.parseColor(model.ThemeColor));
        colorBar.setBackgroundColor(Color.parseColor(model.ThemeColor));
        colorBar_sypmtoms.setBackgroundColor(Color.parseColor(model.ThemeColor));

        mHead.setText(model.ServiceList.get(model.ServicePosition).getTitle());

        Detail.setBackgroundColor(Color.TRANSPARENT);
        symptoms.setBackgroundColor(Color.TRANSPARENT);

        if (!model.ServiceList.get(model.ServicePosition).getDescription().equals("")) {
            String detail = model.ServiceList.get(model.ServicePosition).getDescription().toString();
          //  detail = detail.replace("\'", "");

            Detail.loadDataWithBaseURL("", detail, mimeType, encoding, "");

        }

        if (!model.ServiceList.get(model.ServicePosition).getSymptoms().equals("")) {
            String detail = model.ServiceList.get(model.ServicePosition).getSymptoms().toString();
            detail = detail.replace("\'", "");
            mSymptomTitle.setVisibility(View.VISIBLE);
            colorBar_sypmtoms.setVisibility(View.VISIBLE);
            WebSettings settings = symptoms.getSettings();
            settings.setDefaultTextEncodingName("UTF-8");
/*
            String head1 = "<head><style>@font-face {font-family: 'MyFont';src: url('file:///android_asset/fonts/HELR45W.ttf');}body {font-family: 'MyFont';}</style></head>";
            String text="<html>"+head1
                    + "<body style=\"font-family: MyFont\">" + detail
                    + "</body></html>";*/


            String head1 = "<head><style>@font-face {" +
                    "body {" +
                    "font-size: 18;font-family: Futura;" +
                    "}</style></head>";

            String text="<html>"+head1
                    + "<body style=\"font-size: 18\">" + detail
                    + "</body></html>";
            Log.i( "html1234",text );
            symptoms.loadDataWithBaseURL("", text, mimeType, encoding, "");

        } else {
            mSymptomTitle.setVisibility(View.GONE);
            colorBar_sypmtoms.setVisibility(View.GONE);
        }




       /* if(!model.ServiceList.get(model.ServicePosition).getRecommendations().equals("")){
            String recommendation = model.ServiceList.get(model.ServicePosition).getRecommendations().toString();
            recommendation=recommendation.replace("\'", "");
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            Recommendation.setText(Html.fromHtml(recommendation, Html.FROM_HTML_MODE_COMPACT));
            }else{
                Recommendation.setText(Html.fromHtml(recommendation,null,new UlTagHandler()));
            }
        }*/


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
