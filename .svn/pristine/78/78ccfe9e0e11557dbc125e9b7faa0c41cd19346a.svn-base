package com.bgmiddleeast.Fragment;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgmiddleeast.Activity.ProductDescriptionActivity;
import com.bgmiddleeast.Activity.ToolDescriptionActivity;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Tools;
import com.bgmiddleeast.database.CommentsDataSource;
import com.bumptech.glide.Glide;
import com.rd.PageIndicatorView;
import com.rd.animation.type.AnimationType;

import java.util.ArrayList;
import java.util.List;

import static com.bgmiddleeast.R.id.viewpager;
import static com.bgmiddleeast.R.id.viewpager_tool;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductsFragment extends Fragment {


    ViewPager viewPager;
    ViewPager viewPagerTool;
    ViewPagerAdapter adapter;
    ViewPagerAdapterTool adapterTool;
    View v;
    static TextView mHead;
    static TextView mToolHead;
    View mHeadLine, mToolLine;
    Model model;
    public static ArrayList<Products> ProductList;

    PageIndicatorView indicatorTool;
    public static ArrayList<Tools> ToolList = new ArrayList<>();
    LinearLayout toolLine;


    public static ProductsFragment newInstance(int n) {
        ProductsFragment productsFragment = new ProductsFragment();
        Bundle b = new Bundle();
        b.putInt( "n", n );

        productsFragment.setArguments( b );
        return productsFragment;
    }

    public ProductsFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        model = new Model();
        if (model.Language.equals( "en" ))
            v = inflater.inflate( R.layout.fragment_product, container, false );
        else
            v = inflater.inflate( R.layout.fragment_product_ar, container, false );
        int n = getArguments().getInt( "n" );
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated( view, savedInstanceState );
        ProductList = new ArrayList<>();

        CommentsDataSource datasource = new CommentsDataSource( getActivity() );
        datasource.open();
        model.ProductList.clear();
        if (model.Language.equals( "en" )) {
            model.ProductList = (ArrayList<Products>) datasource.getAllProductList();
        } else {
            model.ProductList = (ArrayList<Products>) datasource.getAllProductListAR();

        }
        datasource.close();

        if (model.ProductList.size() != 0)
            for (int i = 0; i < model.ProductList.size(); i++) {
                if (model.ServiceId.equals( model.ProductList.get( i ).getService_id() )) {

                    ProductList.add( model.ProductList.get( i ) );

                }
            }

        int value = 0;
        if (ProductList.size() != 0)
            for (int i = 0; i < ProductList.size(); i++) {
                if (value < Integer.parseInt( ProductList.get( i ).getProduct_order() )) {
                    value = Integer.parseInt( ProductList.get( i ).getProduct_order() );
                }
            }

        if (model.ProductList.size() != 0) {
            ArrayList<Products> sortTemp = new ArrayList<>();
            for (int i = 0; i <= value; i++) {

                int tmp = i;
                String sortId = String.valueOf( tmp );
                for (int j = 0; j < ProductList.size(); j++) {
                    if (ProductList.get( j ).getProduct_order().equals( sortId )) {
                        sortTemp.add( ProductList.get( j ) );
                    }

                }
            }
            ProductList.clear();
            ProductList = sortTemp;
        }


        String toolString = model.ServiceList.get( model.ServicePosition ).getTools();
        model.ToolList.clear();
        model.ToolList = Parser.getTools( toolString );
        ToolList = Parser.getTools( toolString );


        mHead = (TextView) v.findViewById( R.id.txt_head );
        mToolHead = (TextView) v.findViewById( R.id.txt_tool_head );
        mHeadLine = (View) v.findViewById( R.id.view_line );
        mToolLine = (View) v.findViewById( R.id.view_tool_line );
        viewPager = (ViewPager) v.findViewById( viewpager );
        viewPagerTool = (ViewPager) v.findViewById( viewpager_tool );
        toolLine = (LinearLayout) v.findViewById( R.id.lin_tool );
        toolLine.setVisibility( View.VISIBLE );
        mHead.setTextColor( Color.parseColor( model.ThemeColor ) );
        mToolHead.setTextColor( Color.parseColor( model.ThemeColor ) );
        mHeadLine.setBackgroundColor( Color.parseColor( model.ThemeColor ) );
        mToolLine.setBackgroundColor( Color.parseColor( model.ThemeColor ) );

        mHead.setText( model.ServiceList.get( model.ServicePosition ).getTitle() );


        PageIndicatorView indicator = (PageIndicatorView) v.findViewById( R.id.indicator );
        indicatorTool = (PageIndicatorView) v.findViewById( R.id.indicator_tool );
        indicatorTool. setUnselectedColor( Color.GRAY );
        indicatorTool.setSelectedColor( Color.parseColor( model.ThemeColor ));

        indicator. setUnselectedColor( Color.GRAY );
        indicator.setSelectedColor( Color.parseColor( model.ThemeColor ));
        indicator.setAnimationType( AnimationType.THIN_WORM);
        indicatorTool.setAnimationType( AnimationType.THIN_WORM);
        indicatorTool.setRadius(5);
        indicator.setRadius(5);
        setupViewPager( viewPager );

        viewPager.setPadding( 0, 0, 0, 0 );
        viewPager.setClipToPadding( false );
        viewPager.setPageMargin( 20 );

        if (ProductList.size() != 0) {
            if (android.os.Build.VERSION.SDK_INT < 16) {
                viewPager.setBackgroundDrawable( null );
            } else {
                viewPager.setBackground( null );
            }
            indicator.setViewPager( viewPager );
        }

        if (ProductList.size() <= 1)
            indicator.setVisibility( View.GONE );
        else
            indicator.setVisibility( View.VISIBLE );


        setupViewPagerTools( viewPagerTool );
        indicatorTool.setViewPager( viewPagerTool );

        if (ToolList.size() == 0)
            toolLine.setVisibility( View.GONE );
        else
            toolLine.setVisibility( View.VISIBLE );
        if (ProductList.size() != 0)
            mHead.setText( ProductList.get( 0 ).getProduct_title() );

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHead.setText( ProductList.get( position ).getProduct_title() );

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );
        if (ToolList != null && ToolList.size() != 0)
            mToolHead.setText( ToolList.get( 0 ).getTool_title() );
        viewPagerTool.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mToolHead.setText( ToolList.get( position ).getTool_title() );
//                indicatorTool.setScrollIndicators(  );


//

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        } );

    }


    public void setupViewPager(ViewPager upViewPager) {

        adapter = new ViewPagerAdapter( getChildFragmentManager() );
        for (int i = 0; i < ProductList.size(); i++)
            adapter.addFrag( ImageFragment.newInstance( ProductList.get( i ) ), "" + i );

        viewPager.setAdapter( adapter );
    }

    public void setupViewPagerTools(ViewPager upViewPager) {

        adapterTool = new ViewPagerAdapterTool( getChildFragmentManager() );
        for (int i = 0; i < ToolList.size(); i++)
            adapterTool.addFrag( ImageFragmentTool.newInstance( ToolList.get( i ) ), "Car" + i );

        viewPagerTool.setAdapter( adapterTool );
        if (ToolList.size() > 1) {

            indicatorTool.setVisibility( View.VISIBLE );
        } else {
            indicatorTool.setVisibility( View.GONE );
        }
        if (ToolList.size() != 0)
            if (android.os.Build.VERSION.SDK_INT < 16) {
                viewPagerTool.setBackgroundDrawable( null );
               // viewPagerTool.setBackgroundColor( Color.parseColor( "#80000000" ) );
            } else {

                viewPagerTool.setBackground( null );
                //viewPagerTool.setBackgroundColor( Color.parseColor( "#80000000" ) );

            }

    }

    static class ViewPagerAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super( manager );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get( position );
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        /*@Override
        public float getPageWidth(int position) {

//            if (ProductList.size() > 1) {
//                return (0.5f);
//            } else {
            return (0.9f);
//            }
        }*/

        public void addFrag(Fragment fragment, String title) {

            mFragmentList.add( fragment );
            mFragmentTitleList.add( title );
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get( position );
        }
    }

    public static class ImageFragment extends Fragment {

        Activity ctx;
        ImageView mImageIcon;
        TextView mTxtModel;
        Products products = new Products();
        private RecyclerView recyclerView;
        FragmentActivity activity;

        public static ImageFragment newInstance(Products products) {
            ImageFragment imageFragment = new ImageFragment();
            Bundle b = new Bundle();
            b.putSerializable( "product", products );
            imageFragment.setArguments( b );
            return imageFragment;


        }


        public ImageFragment() {

        }

        int mutedColor = R.attr.colorPrimary;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate( R.layout.product_item, container, false );
            this.products = (Products) getArguments().getSerializable( "product" );
            mImageIcon = (ImageView) view.findViewById( R.id.img_icon );
            mTxtModel = (TextView) view.findViewById( R.id.txt_model );
            Glide.with( this )
                    .load( products.getMain_image() )
                    .crossFade()
                    .into( mImageIcon );

            mTxtModel.setText( products.getModel() );
            mTxtModel.setTextColor( Color.parseColor( Model.ThemeColor ));
            mImageIcon.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Model.ProductId = products.getProduct_id();
                    startActivity( new Intent( getActivity(), ProductDescriptionActivity.class ) );

                }
            } );

            return view;
        }


    }

    static class ViewPagerAdapterTool extends FragmentStatePagerAdapter {

        private List<Fragment> mFragmentList = new ArrayList<>();
        private List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapterTool(FragmentManager manager) {
            super( manager );
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get( position );
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {

            mFragmentList.add( fragment );
            mFragmentTitleList.add( title );
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get( position );
        }
    }

    public static class ImageFragmentTool extends Fragment {


        Activity ctx;

        public Tools tools = new Tools();
        private RecyclerView recyclerView;

        public static ImageFragmentTool newInstance(Tools tools) {

            ImageFragmentTool imageFragmentTool = new ImageFragmentTool();
            Bundle b = new Bundle();
            b.putSerializable( "tool", tools );
            imageFragmentTool.setArguments( b );

            return imageFragmentTool;
        }


        public ImageFragmentTool() {

        }

        int mutedColor = R.attr.colorPrimary;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate( R.layout.service_tool, container, false );
            ImageView ToolImage = (ImageView) view.findViewById( R.id.img_tool );
//            mToolHead.setText(tools.getTool_title());
            this.tools = (Tools) getArguments().getSerializable( "tool" );
            Glide.with( this )
                    .load( tools.getTool_image() )
                    .placeholder( R.drawable.bg_placeholder_tool )
                    .crossFade()
                    .into( ToolImage );

            ToolImage.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Model.ToolId = tools.getTool_id();
                    startActivity( new Intent( getActivity(), ToolDescriptionActivity.class ) );

                }
            } );

            return view;
        }
    }

}
