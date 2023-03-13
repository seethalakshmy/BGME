package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.AppManager;

import java.util.Locale;

public class languageSelectActivity extends AppCompatActivity {
    //    Spinner mSpin;
    ToggleButton mNotification;
    AppManager mAppManager;
    private RelativeLayout mSubmit;
    private int mSpinSelection =0;
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Locale.getDefault().getDisplayLanguage().equals("ar"))
            setContentView(R.layout.activity_language_ar);
        else
            setContentView(R.layout.activity_language);


        mNotification = (ToggleButton) findViewById(R.id.set_lan);
        mSubmit = (RelativeLayout) findViewById(R.id.submit);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);
        mToolbar.setTitle("");
        back.setVisibility(View.INVISIBLE);

//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (mAppManager.getSystemLanguage().equals("")) {
//
//                    Toast.makeText(getApplicationContext(), "Select your language ", Toast.LENGTH_SHORT).show();
//                    finish();
//                } else {
//                    finish();
//                    startActivity(new Intent(languageSelectActivity.this, NewDashboard.class));
//                }
//            }
//        });

        mAppManager = AppManager.getInstance(this);


        if ( mSpinSelection ==0) {
            mNotification.setChecked(true);

        } else {
            mNotification.setChecked(false);

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
                startActivity(new Intent(languageSelectActivity.this, NewDashboard.class));


            }
        });

    }


    @Override
    public void onBackPressed() {
        if (mAppManager.getSystemLanguage().equals("")) {
            Toast.makeText(getApplicationContext(), "Select your language ", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            finish();
            startActivity(new Intent(languageSelectActivity.this, NewDashboard.class));

        }
    }
}
