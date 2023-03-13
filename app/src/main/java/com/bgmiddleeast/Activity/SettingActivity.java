package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ToggleButton;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.AppManager;

public class SettingActivity extends AppCompatActivity {
    //    Spinner mSpin;
    ToggleButton mNotification;
    private String[] SPINNER_DATA = {"English", "عربى"};
    AppManager mAppManager;
    private ImageView mSubmit;
    private int mSpinSelection;
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (model.Language.equals("en"))
            setContentView(R.layout.activity_setting);
        else
            setContentView(R.layout.activity_setting_ar);


        mNotification = (ToggleButton) findViewById(R.id.set_lan);
//        mSpin = (Spinner) findViewById(R.id.spn_city);
        mSubmit = (ImageView) findViewById(R.id.img_submit);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);
        mToolbar.setTitle("");

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(SettingActivity.this, NewDashboard.class));
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
            }
        });


        mAppManager = AppManager.getInstance(this);

        if (mAppManager.getSystemLanguage().equals("en")) {
            mNotification.setChecked(true);
            mSpinSelection = 0;
        } else {
            mNotification.setChecked(false);
            mSpinSelection = 1;
        }
        mNotification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    mSpinSelection = 0;


                } else {

                    mSpinSelection = 1;
                }

            }
        });

        mSubmit.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View view) {
                if (mSpinSelection == 0) {
                    mAppManager.saveSystemLanguage("en");
                } else {
                    mAppManager.saveSystemLanguage("ar");
                }

                model.Language = mAppManager.getSystemLanguage();
                finish();
                startActivity(new Intent(SettingActivity.this, NewDashboard.class));
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
            }
        });


    }



    @Override
    public void onBackPressed() {
        finish();
        startActivity(new Intent(SettingActivity.this, NewDashboard.class));
        overridePendingTransition( R.anim.fadein, R.anim.fadeout );
    }
}
