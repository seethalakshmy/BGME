package com.bgmiddleeast.Fragment;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.BeforeAfter;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;

import static com.bgmiddleeast.R.id.viewpager;

/**
 * A simple {@link Fragment} subclass.
 */
public class BenefitsFragment extends Fragment {

    ViewPager viewPager;
    View v;
    WebView mBenefit;
    TextView mHead,mBeforeHead;
    View mHeadLine,mBeforeLine;
    Model model;
    ArrayList<BeforeAfter> mBeforeAfter=new ArrayList<>();
    final String mimeType = "text/html";
    final String encoding = "UTF-8";
    WebView Recommendation;
    LinearLayout mBefoAftLayout;

    public BenefitsFragment() {

    }

    public static BenefitsFragment newInstance(int n) {
        BenefitsFragment benefitsFragment=new BenefitsFragment();

        Bundle b=new Bundle();
        b.putInt("n",n);

        benefitsFragment.setArguments(b);

        return benefitsFragment;
   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        model=new Model();
        if(model.Language.equals("en"))
        v=inflater.inflate(R.layout.fragment_benefit, container, false);
        else
            v=inflater.inflate(R.layout.fragment_benefit_ar, container, false);
int n=getArguments().getInt( "n" );
        return  v;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        model=new Model();
        viewPager = (ViewPager)v.findViewById(viewpager);
        mBenefit = (WebView) v.findViewById(R.id.texthtmlView);
        mHead=(TextView)v.findViewById(R.id.txt_head);
        mBeforeHead=(TextView)v.findViewById(R.id.txt_before);
        mHeadLine=(View)v.findViewById(R.id.view_line) ;
        mBeforeLine=(View)v.findViewById(R.id.view_before_head);
        mBefoAftLayout=(LinearLayout)v.findViewById(R.id.lin_bef_aftr);
        CircleIndicator indicator = (CircleIndicator) v.findViewById(R.id.indicator);
        Recommendation = (WebView) view.findViewById(R.id.web_recommendation);
        mBenefit.setBackgroundColor(Color.TRANSPARENT);
        Recommendation.setBackgroundColor(Color.TRANSPARENT);

        if (!model.ServiceList.get(model.ServicePosition).getBenefits().equals("")) {
            String detail = model.ServiceList.get(model.ServicePosition).getBenefits().toString();
            detail = detail.replace("\'", "");
            WebSettings settings = mBenefit.getSettings();
            settings.setDefaultTextEncodingName("UTF-8");
//            mBenefit.loadDataWithBaseURL("",  "<ul><li> The provisions of section....</li></ul>", mimeType, encoding, "");
//            String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>";

//            String head1 = "<head><style>@font-face {font-family: 'MyFont';src: url('file:///android_asset/fonts/HELR45W.ttf');}body {font-family: 'MyFont';}</style></head>";
//            String text="<html>"+head1
//                    + "<body style=\"font-family: MyFont\">" + detail
//                    + "</body></html>";

            String head1 = "<head><style>@font-face {" +
                    "body {" +
                    "font-size: 18;" +
                    "}</style></head>";

            String text="<html>"+head1
                    + "<body style=\"font-size: 18\">"+ detail
                    + "</body></html>";

            mBenefit.loadDataWithBaseURL(null, text, "text/html", "utf-8", null);
//            mBenefit.loadData(detail, "text/html; charset=UTF-8", null);
        }


        mHead.setTextColor(Color.parseColor(model.ThemeColor));
        mHead.setText(model.ServiceList.get(model.ServicePosition).getTitle());
        mBeforeHead.setTextColor(Color.parseColor(model.ThemeColor));
        mHeadLine.setBackgroundColor(Color.parseColor(model.ThemeColor));
        mBeforeLine.setBackgroundColor(Color.parseColor(model.ThemeColor));

        setupViewPager(viewPager);

        indicator.setViewPager(viewPager);
        if(mBeforeAfter.size()<=1)
            indicator.setVisibility(View.GONE);
        else
            indicator.setVisibility(View.VISIBLE);
        if(mBeforeAfter.size()!=0){
            mBefoAftLayout.setVisibility(View.VISIBLE);

        }else{
            mBefoAftLayout.setVisibility(View.GONE);
        }
        if (!model.ServiceList.get(model.ServicePosition).getRecommendations().toString().equals("")) {

            String recommendation = model.ServiceList.get(model.ServicePosition).getRecommendations().toString();
            recommendation = recommendation.replace("\'", "");
            Log.e("recommendation", recommendation);
           /* String head1 = "<head><style>@font-face {font-family: 'MyFont';src: url('file:///android_asset/fonts/HELR45W.ttf');}body {font-family: 'MyFont';}</style></head>";
            String text="<html>"+head1
                    + "<body style=\"font-family: MyFont\">" + recommendation
                    + "</body></html>";
*/
//            Log.i( "html1235",text );
            String head1 = "<head><style>@font-face {" +
                    "body {" +
                    "font-size: 18;" +
                    "}</style></head>";

            String text="<html>"+head1
                    + "<body style=\"font-size: 18\">"+ recommendation
                    + "</body></html>";

            Recommendation.loadDataWithBaseURL("", text, mimeType, encoding, "");

            Recommendation.setPadding(0, 0, 0, 0);
            Recommendation.setInitialScale(getScale());
        }


    }

    public void setupViewPager(ViewPager upViewPager) {

        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        String beforeAfter=model.ServiceList.get(model.ServicePosition).getBefore_after();
        mBeforeAfter=Parser.getBeforeAfter(beforeAfter);
        if(mBeforeAfter!=null && mBeforeAfter.size()!=0)
        for (int i=0;i<mBeforeAfter.size();i++)
       // adapter.addFrag(new ImageFragment(getActivity(),mBeforeAfter.get(i)), ""+i);
        adapter.addFrag( ImageFragment.newInstance( mBeforeAfter.get(i)),""+i );

        viewPager.setAdapter(adapter);
    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {

            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
    public static class ImageFragment extends Fragment {


        Activity ctx;
        ImageView mImage;

        public  ArrayList<String> listData = new ArrayList<String>();
        private RecyclerView recyclerView;
        private Model model;
        BeforeAfter mBeforeAfter;

//        public ImageFragment(FragmentActivity activity, BeforeAfter beforeAfter) {
//            model=new Model();
//            mBeforeAfter=beforeAfter;
//        }

        public static ImageFragment newInstance(BeforeAfter beforeAfter){
            ImageFragment imageFragment=new ImageFragment();
            Bundle b=new Bundle();
            b.putSerializable("ba",beforeAfter);
            imageFragment.setArguments(b);
            return imageFragment;
        }

        public ImageFragment() {

        }
        int mutedColor = R.attr.colorPrimary;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.before_after, container, false);
            mImage=(ImageView)view.findViewById(R.id.img_icon);
            model=new Model();
            mBeforeAfter= (BeforeAfter) getArguments().getSerializable("ba");

            Glide.with(this)
                    .load(mBeforeAfter.getImage())
                    .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                    .crossFade()
                    .into(mImage);
            return view;
        }
    }
    private int getScale() {
        Display display = ((WindowManager) getActivity().getSystemService( Context.WINDOW_SERVICE)).getDefaultDisplay();
        int width = display.getWidth();
        Double val = new Double(width - 175) / new Double(100);
        val = val * 25d;
        return val.intValue();
    }
}
