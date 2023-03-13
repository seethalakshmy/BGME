package com.bgmiddleeast.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.bgmiddleeast.Utilities.ConnectionDetector;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.HashMap;
import java.util.Map;

import static com.bgmiddleeast.R.string.edit_all_error;
import static com.bgmiddleeast.R.string.edit_all_error_ar;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout mForgetPwd;
    ImageButton mRegister, mLogin;
    Model model;
    EditText mUserName, mPassword;
    private CoordinatorLayout mCoordinatorLayout;
    AppManager mAppManager;
    String DeviceId;
    ConnectionDetector cond;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        model = new Model();
        if (model.Language.equals("en"))
            setContentView(R.layout.activity_login);
        else
            setContentView(R.layout.activity_login_ar);


        Toolbar mToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);
        mToolbar.setTitle("");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in3 = new Intent(LoginActivity.this, NewDashboard.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(in3);
                finish();

            }
        });

        mAppManager = AppManager.getInstance(this);
        cond=new ConnectionDetector(this);
        pd=new ProgressDialog(this);
        if (Model.Language.equals( "en" ))
            pd.setMessage( "Connecting to server..." );
        else
            pd.setMessage( "جاري الاتصال بمزود الخدمه" );
        DeviceId = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);

        System.out.println("Token otp" + mAppManager.getPushToken());
        if (mAppManager.getPushToken().equals("")) {
            String token = FirebaseInstanceId.getInstance().getToken();
            System.out.println("Token otp" + token);

            mAppManager.savePushToken(token);

        }

        mForgetPwd = (LinearLayout) findViewById(R.id.ln_forgot_password);
        mRegister = (ImageButton) findViewById(R.id.bt_register);
        mUserName = (EditText) findViewById(R.id.input_username);
        mPassword = (EditText) findViewById(R.id.input_password);
        mLogin = (ImageButton) findViewById(R.id.bt_login);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);

        mForgetPwd.setOnClickListener(this);
        mRegister.setOnClickListener(this);
        mLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ln_forgot_password:
                Intent in1 = new Intent(LoginActivity.this, ForgetPasswordActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(in1);
                break;
            case R.id.bt_register:
                Intent in2 = new Intent(LoginActivity.this, RegisterActivity.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(in2);
                break;
            case R.id.bt_login:
                if(cond.isConnectingToInternet()) {

                    if (isvalid()) {
                        pd.show();
                        setLogin();

                    }
                }else{
            callSnackbar("Network connection is needed");
        }
                break;

        }

    }

    private void setLogin() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_login;
        } else {
            url = ConfigureURLS.URL_ar_login;
        }

        System.out.println("URL Login======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET_Registraion method" + response);
                        try {
                            boolean mIsSuccess = Parser.getLogin(response.toString());
                            if (mIsSuccess) {
                                exit();

                            } else {
                                Toast.makeText(LoginActivity.this, "" + Parser.getLoginMessage(), Toast.LENGTH_SHORT).show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        pd.dismiss();
                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put("email", mUserName.getText().toString().trim());
                params.put("password", mPassword.getText().toString().trim());
                params.put("device_id", mAppManager.getPushToken());
                params.put("os", "1"); // os identification number
                Log.i("params",params.toString());


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    //Validate login
    private boolean isvalid() {
        if (mUserName.getText().toString().trim().equals("") && mPassword.getText().toString().trim().equals("")) {
            if (Model.Language.equals( "en" )) {
                callSnackbar( getString( edit_all_error ) );
            }else {
                callSnackbar( getString( edit_all_error_ar ) );
            }
            return false;
        } else {
            if (mPassword.getText().toString().trim().equals("")) {
                if (Model.Language.equals( "en" )) {
                    mPassword.setError( getString( R.string.edit_error ) );
                }else {
                    mPassword.setError( getString( R.string.edit_error_ar ) );
                }
                mPassword.requestFocus();
                return false;
            } else if (mUserName.getText().toString().trim().equals("")) {
                if (Model.Language.equals( "en" )) {
                    mUserName.setError( getString( R.string.edit_error ) );
                }else {
                    mPassword.setError( getString( R.string.edit_error_ar ) );
                }
                mUserName.requestFocus();
                return false;
            } else {
                    if (!isValidEmail(mUserName.getText().toString().trim())) {
                        if (Model.Language.equals( "en" )) {
                            mUserName.setError( getString( R.string.email_error ) );
                        }else {
                            mPassword.setError( getString( R.string.edit_error_ar ) );
                        }
                        mUserName.requestFocus();
                        return false;
                    } else {
                        return true;
                    }

            }


        }

    }

    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void callSnackbar(String message) {

        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }


    private void exit() {
        mAppManager.saveUserId(Parser.getUserId());
        mAppManager.saveIsLogin(true);
        mAppManager.saveTokenCode(Parser.getTokenCode());
        Toast.makeText(LoginActivity.this, "" + Parser.getMessage(), Toast.LENGTH_SHORT).show();
        Intent in1 = new Intent(LoginActivity.this, NewDashboard.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(in1);
        finish();
    }

    @Override
    public void onBackPressed() {

        Intent in3 = new Intent(LoginActivity.this, NewDashboard.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(in3);
        finish();


    }

}
