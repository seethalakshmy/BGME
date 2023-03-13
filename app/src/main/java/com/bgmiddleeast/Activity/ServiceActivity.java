package com.bgmiddleeast.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgmiddleeast.Adapter.ViewPagerAdapter;
import com.bgmiddleeast.Fragment.AboutFragment;
import com.bgmiddleeast.Fragment.BenefitsFragment;
import com.bgmiddleeast.Fragment.ProductsFragment;
import com.bgmiddleeast.Fragment.VideosFragment;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bumptech.glide.Glide;

import java.lang.reflect.Field;

import static com.bgmiddleeast.Adapter.ServiceListAdapter.ServiceList;


public class ServiceActivity extends AppCompatActivity {
    //This is  viewPager
    private ViewPager viewPager;

    //Fragments

    BenefitsFragment benefitsFragment;
    AboutFragment aboutFragment;
    ProductsFragment productsFragment;
    VideosFragment videosFragment;
    BottomNavigationView bottomNavigationView;
    MenuItem prevMenuItem;
    TextView mTitle;
    Window window;
    int mThemeColor;
    LinearLayout mBackground;
    Model model;
    LinearLayout mImgBackground;
    AppBarLayout mAppBarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new Model();
        if (model.Language.equals("en"))
            setContentView(R.layout.activity_service);
        else
            setContentView(R.layout.activity_service_ar);

        window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);


//        String s=model.ServiceList.get(model.ServicePosition).getColour_code();

        mThemeColor = Color.parseColor(model.ThemeColor);


        setInit();

        mAppBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (verticalOffset > -100) {
                    mTitle.setText("");
                } else {
                    mTitle.setText(ServiceList.get(model.ServicePosition).getTitle());
                }

            }
        });



    }

    private void setInit() {

        final Toolbar toolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(toolbar);
        mTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        ImageButton back = (ImageButton) toolbar.findViewById(R.id.left_back);
        mAppBarLayout = (AppBarLayout) findViewById(R.id.appbar);

        //Initializing viewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mBackground = (LinearLayout) findViewById(R.id.botam_background);
        mImgBackground = (LinearLayout) findViewById(R.id.header_backgroud);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        final CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.htab_collapse_toolbar);
        collapsingToolbarLayout.setTitleEnabled(false);
        collapsingToolbarLayout.setTitle("");
        ImageView header = (ImageView) findViewById(R.id.htab_header);

        Glide.with(this)
                .load(ServiceList.get(model.ServicePosition).getTitle_image())
//                .placeholder(R.drawable.loading_spinner)
                .crossFade()
                .into(header);


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
        //Initializing the bottomNavigationView
        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            window.setStatusBarColor(mThemeColor);

        }
        mBackground.setBackgroundColor(mThemeColor);
        mImgBackground.setBackgroundColor(mThemeColor);


        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @SuppressWarnings("ResourceType")
            @Override
            public void onGenerated(Palette palette) {

                int vibrantColor = palette.getVibrantColor(mThemeColor);
                int vibrantDarkColor = palette.getDarkVibrantColor(mThemeColor);
                collapsingToolbarLayout.setContentScrimColor(vibrantColor);
                collapsingToolbarLayout.setStatusBarScrimColor(vibrantDarkColor);
            }
        });



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });


        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        if (model.Language.equals("en")) {

                            switch (item.getItemId()) {

                                case R.id.action_about:
                                    viewPager.setCurrentItem(0);
                                    break;
                                case R.id.action_benefits:
                                    viewPager.setCurrentItem(1);
                                    break;
                                case R.id.action_products:
                                    viewPager.setCurrentItem(2);
                                    break;
                                case R.id.action_videos:
                                    viewPager.setCurrentItem(3);
                                    break;
                            }
                        } else {
                            switch (item.getItemId()) {

                                case R.id.action_about:
                                    viewPager.setCurrentItem(3);
                                    break;
                                case R.id.action_benefits:
                                    viewPager.setCurrentItem(2);
                                    break;
                                case R.id.action_products:
                                    viewPager.setCurrentItem(1);
                                    break;
                                case R.id.action_videos:
                                    viewPager.setCurrentItem(0);
                                    break;
                            }
                        }
                        return false;
                    }
                });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                Log.d("page", "onPageSelected: " + position);
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //Disable ViewPager Swipe

//       viewPager.setOnTouchListener(new View.OnTouchListener()
//        {
//            @Override
//            public boolean onTouch(View v, MotionEvent event)
//            {
//                return true;
//            }
//        });


        setupViewPager(viewPager);


    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        aboutFragment =AboutFragment.newInstance(1) ;
        benefitsFragment = BenefitsFragment.newInstance(2);
        productsFragment = ProductsFragment.newInstance(3);
        videosFragment = VideosFragment.newInstance(4);

        if (model.Language.equals("en")) {
            adapter.addFragment(aboutFragment);
            adapter.addFragment(benefitsFragment);
            adapter.addFragment(productsFragment);
            adapter.addFragment(videosFragment);
        } else {

            adapter.addFragment(videosFragment);
            adapter.addFragment(productsFragment);
            adapter.addFragment(benefitsFragment);
            adapter.addFragment(aboutFragment);
        }
        viewPager.setAdapter(adapter);
        if (model.Language.equals("en")) {

        } else {
            bottomNavigationView.getMenu().getItem(3).setChecked(true);
            viewPager.setCurrentItem(3);
        }
    }

    public static class BottomNavigationViewHelper {
        public static void disableShiftMode(BottomNavigationView view) {
            BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
            try {
                Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
                shiftingMode.setAccessible(true);
                shiftingMode.setBoolean(menuView, false);
                shiftingMode.setAccessible(false);
                for (int i = 0; i < menuView.getChildCount(); i++) {
                    BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                    //noinspection RestrictedApi
                    item.setShiftingMode(false);
                    // set once again checked value, so view will be updated
                    //noinspection RestrictedApi
                    item.setChecked(item.getItemData().isChecked());
                }
            } catch (NoSuchFieldException e) {
                Log.e("BNVHelper", "Unable to get shift mode field", e);
            } catch (IllegalAccessException e) {
                Log.e("BNVHelper", "Unable to change value of shift mode", e);
            }
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
