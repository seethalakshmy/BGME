package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;

import com.bgmiddleeast.R;

public class RegisterPasswordActivity extends AppCompatActivity {

    ImageButton mRegister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_password);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);
        mRegister=(ImageButton)findViewById(R.id.bt_login);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in1 = new Intent(RegisterPasswordActivity.this,NewDashboard.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(in1);
            }
        });


    }
}
