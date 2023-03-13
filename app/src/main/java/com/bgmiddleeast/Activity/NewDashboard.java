package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.AppManager;
import com.bgmiddleeast.Utilities.ConfigureURLS;
import com.bgmiddleeast.residemenu.ResideMenu;
import com.bgmiddleeast.residemenu.ResideMenuHeader;
import com.bgmiddleeast.residemenu.ResideMenuItem;
import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sreenadh on 04-May-17.
 */

public class NewDashboard extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout mRlServices, mRlSocial, mRlContact, mRlAbout, mRLMsds, mRLTds, mRLFlyer, mRLCatalog;
    //    private View mViewServices, mViewSocial, mViewContact, mViewAbout;
    Model model = new Model();
    boolean doubleBackToExitPressedOnce = false;
    LinearLayout langLeft, LangRight, mLoginPhase1, mLoginPhase2;

    ImageView menuLeft, menuRight, mLongin;
    AppManager mAppManager;
    TextView mLogin;

    TextView mLangLeftEng, mLangLeftArabic, mLangRightEng, mLangRightArabic;
    private int mSpinSelection;
    public static boolean mIsLastEnglish;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        mAppManager = AppManager.getInstance( this );
        model.Language = mAppManager.getSystemLanguage();


        if (mAppManager.getSystemLanguage().equals( "en" )) {
            setContentView( R.layout.new_dashboard );
            mSpinSelection = 0;

        } else {
            setContentView( R.layout.new_dashboard_ar );
            mSpinSelection = 1;

        }


        Toolbar mToolbar = (Toolbar) findViewById( R.id.account_toolbar );
        setSupportActionBar( mToolbar );
        menuLeft = (ImageView) mToolbar.findViewById( R.id.menuLeft );
        menuRight = (ImageView) mToolbar.findViewById( R.id.menuRight );


        langLeft = (LinearLayout) mToolbar.findViewById( R.id.menu_home_left );
        LangRight = (LinearLayout) mToolbar.findViewById( R.id.menu_home_right );

        mLangLeftEng = (TextView) mToolbar.findViewById( R.id.im_Enlish_left );
        mLangLeftArabic = (TextView) mToolbar.findViewById( R.id.im_Arabic_left );
        mLangRightEng = (TextView) mToolbar.findViewById( R.id.im_Enlish_right );
        mLangRightArabic = (TextView) mToolbar.findViewById( R.id.im_Arabic_right );

        mRlServices = (RelativeLayout) findViewById( R.id.rl_services );
        mRlSocial = (RelativeLayout) findViewById( R.id.rl_social );
        mRlContact = (RelativeLayout) findViewById( R.id.rl_contact );
        mRlAbout = (RelativeLayout) findViewById( R.id.rl_about );

        mRLMsds = (RelativeLayout) findViewById( R.id.rl_msds );
        mRLTds = (RelativeLayout) findViewById( R.id.rl_flyer );
        mRLFlyer = (RelativeLayout) findViewById( R.id.rl_catalog );
        mRLCatalog = (RelativeLayout) findViewById( R.id.rl_tds );
        mLongin = (ImageView) findViewById( R.id.img_dash_about );

        mLogin = (TextView) findViewById( R.id.txt_Login );
        if (mAppManager.getIsLogin()) {
            Glide.with( this )
                    .load( R.drawable.dash_training )
                    .into( mLongin );
            if (model.Language.equals( "en" ))
                mLogin.setText( this.getResources().getString( R.string.training ) );
            else
                mLogin.setText( R.string.training_ar );

        }

        mLoginPhase1 = (LinearLayout) findViewById( R.id.login_phase1 );
        mLoginPhase2 = (LinearLayout) findViewById( R.id.login_phase2 );


        mRlServices.setOnClickListener( this );
        mRlSocial.setOnClickListener( this );
        mRlContact.setOnClickListener( this );
        mRlAbout.setOnClickListener( this );
        mRLMsds.setOnClickListener( this );
        mRLTds.setOnClickListener( this );
        mRLFlyer.setOnClickListener( this );
        mRLCatalog.setOnClickListener( this );

        setUpMenu( mAppManager.getSystemLanguage() );
    }


    ResideMenuHeader menuHeader;
    ResideMenu resideMenu;
    ResideMenuItem services, social, contact, about, Login, Profile, Training, Logout,Notification;

    private void setUpMenu(String language) {

        //attach menu to current activity
        resideMenu = new ResideMenu( this );

        resideMenu.setBackground( R.drawable.menu_bg );
        resideMenu.attachToActivity( this );
        resideMenu.setMenuListener( menuListener );

        //setting width of the menu using scale factor between 0.1 f to 1.0 f
        resideMenu.setScaleValue( 0.6f );

        //adding munu items
        menuHeader = new ResideMenuHeader( this, R.drawable.menu_header );
        if (language.equals( "en" )) {
            services = new ResideMenuItem( this, R.drawable.ic_menu_service, R.string.service, language );
            social = new ResideMenuItem( this, R.drawable.ic_menu_social, R.string.social, language );
            Login = new ResideMenuItem( this, R.drawable.ic_logout, R.string.Login, language );
            contact = new ResideMenuItem( this, R.drawable.ic_menu_contact, R.string.contact, language );
            Profile = new ResideMenuItem( this, R.drawable.ic_profile, R.string.profile, language );
            Training = new ResideMenuItem( this, R.drawable.ic_training, R.string.training, language );
            Logout = new ResideMenuItem( this, R.drawable.ic_logout, R.string.logout, language );
            about = new ResideMenuItem( this, R.drawable.icon_about, R.string.about_us, language );
            Notification= new ResideMenuItem( this, R.drawable.ic_notification, R.string.notification, language );

        } else {
            services = new ResideMenuItem( this, R.drawable.ic_menu_service, R.string.service_ar, language );
            social = new ResideMenuItem( this, R.drawable.ic_menu_social, R.string.social_ar, language );
            Login = new ResideMenuItem( this, R.drawable.ic_logout, R.string.Login_ar, language );
            contact = new ResideMenuItem( this, R.drawable.ic_menu_contact, R.string.contact_ar, language );
            Profile = new ResideMenuItem( this, R.drawable.ic_profile, R.string.profile_ar, language );
            Training = new ResideMenuItem( this, R.drawable.ic_training, R.string.training_ar, language );
            Logout = new ResideMenuItem( this, R.drawable.ic_logout, R.string.logout_ar, language );
            about = new ResideMenuItem( this, R.drawable.icon_about, R.string.about_us_ar, language );
            Notification= new ResideMenuItem( this, R.drawable.ic_notification, R.string.notification_ar, language );
        }

        mLangLeftEng.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinSelection = 0;
                mAppManager.saveSystemLanguage( "en" );
                restart();
            }
        } );
        mLangLeftArabic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinSelection = 1;
                mAppManager.saveSystemLanguage( "ar" );

//                if(!model.Type.equals(mAppManager.getSystemLanguage()))

            }
        } );
        mLangRightEng.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinSelection = 0;
                mAppManager.saveSystemLanguage( "en" );
            }
        } );
        mLangRightArabic.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinSelection = 1;
                mAppManager.saveSystemLanguage( "ar" );
//                if(!model.Type.equals(mAppManager.getSystemLanguage()))
                restart();

            }
        } );
        mLoginPhase1.setVisibility( View.GONE );
        mLoginPhase2.setVisibility( View.GONE );

        if (mAppManager.getIsLogin()) {

            getDocs();
        } else {
            mLoginPhase1.setVisibility( View.GONE );
            mLoginPhase2.setVisibility( View.GONE );

        }


       /* mLangLeft.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                } else {

                    mSpinSelection = 1;
                    mAppManager.saveSystemLanguage("ar");
                    if(!model.Type.equals(mAppManager.getSystemLanguage()))
                    restart();
                }



            }
        });
        mLangRight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    mSpinSelection = 0;
                    mAppManager.saveSystemLanguage("en");
                    if(model.Type.equals("ar"))
                        restart();

                } else {

                    mSpinSelection = 1;
                    mAppManager.saveSystemLanguage("en");
                    if(!model.Type.equals(mAppManager.getSystemLanguage()))
                    restart();

                }



            }
        });*/


        services.setOnClickListener( this );
        social.setOnClickListener( this );
        contact.setOnClickListener( this );
        Login.setOnClickListener( this );
        Profile.setOnClickListener( this );
        Training.setOnClickListener( this );
        Logout.setOnClickListener( this );
        about.setOnClickListener( this );
        Notification.setOnClickListener( this );

        //you can disable a side by -->
        //   resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        if (language.equals( "ar" )) {

            menuLeft.setVisibility( View.INVISIBLE );
            menuRight.setVisibility( View.VISIBLE );
            langLeft.setVisibility( View.VISIBLE );
            LangRight.setVisibility( View.INVISIBLE );

            resideMenu.addMenuHeader( menuHeader, ResideMenu.DIRECTION_RIGHT );
            resideMenu.addMenuItem( services, ResideMenu.DIRECTION_RIGHT );
            if (mAppManager.getIsLogin()) {
                resideMenu.addMenuItem( Training, ResideMenu.DIRECTION_RIGHT );
                resideMenu.addMenuItem( Notification, ResideMenu.DIRECTION_RIGHT );
                resideMenu.addMenuItem( Profile, ResideMenu.DIRECTION_RIGHT );
            }
            resideMenu.addMenuItem( social, ResideMenu.DIRECTION_RIGHT );
            resideMenu.addMenuItem( contact, ResideMenu.DIRECTION_RIGHT );
            resideMenu.addMenuItem( about, ResideMenu.DIRECTION_RIGHT );
            if (!mAppManager.getIsLogin())
                resideMenu.addMenuItem( Login, ResideMenu.DIRECTION_RIGHT );
            else
                resideMenu.addMenuItem( Logout, ResideMenu.DIRECTION_RIGHT );


            menuRight.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resideMenu.openMenu( ResideMenu.DIRECTION_RIGHT );
                }
            } );

            resideMenu.setDirectionDisable( ResideMenu.DIRECTION_LEFT );

        } else

        {
            menuRight.setVisibility( View.INVISIBLE );
            menuLeft.setVisibility( View.VISIBLE );

            langLeft.setVisibility( View.INVISIBLE );
            LangRight.setVisibility( View.VISIBLE );

            resideMenu.addMenuHeader( menuHeader, ResideMenu.DIRECTION_LEFT );
            resideMenu.addMenuItem( services, ResideMenu.DIRECTION_LEFT );
            if (mAppManager.getIsLogin()) {
                resideMenu.addMenuItem( Training, ResideMenu.DIRECTION_LEFT );
                resideMenu.addMenuItem( Notification, ResideMenu.DIRECTION_LEFT );
                resideMenu.addMenuItem( Profile, ResideMenu.DIRECTION_LEFT );
            }

            resideMenu.addMenuItem( social, ResideMenu.DIRECTION_LEFT );
            resideMenu.addMenuItem( contact, ResideMenu.DIRECTION_LEFT );
            resideMenu.addMenuItem( about, ResideMenu.DIRECTION_LEFT );
            if (!mAppManager.getIsLogin()) {
                resideMenu.addMenuItem( Login, ResideMenu.DIRECTION_LEFT );
            } else {
                resideMenu.addMenuItem( Logout, ResideMenu.DIRECTION_LEFT );
            }

            menuLeft.setOnClickListener( new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    resideMenu.openMenu( ResideMenu.DIRECTION_LEFT );
                }
            } );

            resideMenu.setDirectionDisable( ResideMenu.DIRECTION_RIGHT );
        }

    }

    private void getDocs() {

        String url = null;
        if (model.Language.equals( "en" )) {
            url = ConfigureURLS.URL_en_documents;
        } else {
            url = ConfigureURLS.URL_ar_documents;
        }

        System.out.println( "URL registration======= " + url );

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println( "GET_Training_doc method" + response );
                        try {
                            boolean mIsSuccess = Parser.getDocumentList( response.toString() );
                            if (mIsSuccess) {

                                if (mAppManager.getIsLogin()) {
                                    mLoginPhase1.setVisibility( View.VISIBLE );
                                    mLoginPhase2.setVisibility( View.VISIBLE );
                                } else {
                                    mLoginPhase1.setVisibility( View.GONE );
                                    mLoginPhase2.setVisibility( View.GONE );
                                }

                            } else {
//                                Toast.makeText(NewDashboard.this, "" + Parser.getMessage(), Toast.LENGTH_SHORT).show();

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

                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put( "user_id", mAppManager.getUserId() );

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }


    void restart() {

        model.Language = mAppManager.getSystemLanguage();
        Intent intent = getIntent();
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity( intent );
        finish();


    }

    private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };


    @Override
    public void onClick(View v) {


        switch (v.getId()) {
            case R.id.rl_services:
                //  mViewServices.setBackgroundColor(Color.parseColor("#FFB915"));
                model.isFirstDash = true;
                Intent in = new Intent( NewDashboard.this, DashbordActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in );
                finish();
                break;
            case R.id.rl_social:
                model.isFirstDash = true;
                //  mViewSocial.setBackgroundColor(Color.parseColor("#FFB915"));
                Intent in1 = new Intent( NewDashboard.this, SocialListActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in1 );
                finish();
                break;
            case R.id.rl_contact:
                model.isFirstDash = true;
                //  mViewContact.setBackgroundColor(Color.parseColor("#FFB915"));
                Intent in2 = new Intent( NewDashboard.this, ContactActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in2 );
                finish();
                break;
            case R.id.rl_about:
                model.isFirstDash = true;
                // mViewAbout.setBackgroundColor(Color.parseColor("#FFB915"));
                if (mAppManager.getIsLogin()) {
                    Intent in3 = new Intent( NewDashboard.this, TrainingActivity.class );
                    overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                    startActivity( in3 );
                    finish();
                } else {
                    Intent in3 = new Intent( NewDashboard.this, LoginActivity.class );
                    overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                    startActivity( in3 );
                    finish();
                }

                break;
            case R.id.rl_msds:
                model.isFirstDash = true;
                // mViewAbout.setBackgroundColor(Color.parseColor("#FFB915"));
                Intent in4 = new Intent( NewDashboard.this, MsdsActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in4 );

                break;
            case R.id.rl_tds:
                model.isFirstDash = true;
                // mViewAbout.setBackgroundColor(Color.parseColor("#FFB915"));
                Intent in5 = new Intent( NewDashboard.this, TdsActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in5 );
                break;

            case R.id.rl_flyer:
                model.isFirstDash = true;
                // mViewAbout.setBackgroundColor(Color.parseColor("#FFB915"));
                Intent in6 = new Intent( NewDashboard.this, FlyerActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in6 );
                break;
            case R.id.rl_catalog:
                model.isFirstDash = true;
                Intent in8 = new Intent( NewDashboard.this, CatalogActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in8 );
                break;


        }
        if (v == services) {

            startActivity( new Intent( NewDashboard.this, DashbordActivity.class ) );
            finish();

        } else if (v == social) {

            startActivity( new Intent( NewDashboard.this, SocialListActivity.class ) );
            finish();
        } else if (v == contact) {
            startActivity( new Intent( NewDashboard.this, ContactActivity.class ) );
            finish();

        } else if (v == Login) {
            startActivity( new Intent( NewDashboard.this, LoginActivity.class ) );
            finish();
        } else if (v == Profile) {
            startActivity( new Intent( NewDashboard.this, ProfileActivity.class ) );
            finish();
        } else if (v == about) {
            startActivity( new Intent( NewDashboard.this, AboutActivity.class ) );
            finish();

        } else if (v == Training) {
            startActivity( new Intent( NewDashboard.this, TrainingActivity.class ) );
            finish();
        } else if (v == Notification) {
            startActivity( new Intent( NewDashboard.this, NotificationActivity.class ) );
            finish();

        } else if (v == Logout) {
            mAppManager.saveIsLogin( false );
            mAppManager.saveUserId( "" );
            mAppManager.saveTokenCode( "" );
            mAppManager.savePushToken( "" );
            Toast.makeText( this, "Successfully Logout ", Toast.LENGTH_SHORT ).show();
            restart();
        }


    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            finish();
            System.exit( 0 );
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText( this, "Please click BACK again to exit", Toast.LENGTH_SHORT ).show();

        new Handler().postDelayed( new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000 );
    }


}