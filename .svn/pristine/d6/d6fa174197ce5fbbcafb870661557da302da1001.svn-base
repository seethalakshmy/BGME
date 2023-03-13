package com.bgmiddleeast.Activity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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
import com.bgmiddleeast.Utilities.ConfigureURLS;
import com.bgmiddleeast.Utilities.ConnectionDetector;

import java.util.HashMap;
import java.util.Map;

public class ForgetPasswordActivity extends AppCompatActivity {


    LinearLayout mParent;
    ImageButton mSubmit;
    EditText mUserName;
    String mEmail;
    Model model;
    ConnectionDetector cond;
    private CoordinatorLayout mCoordinatorLayout;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        model = new Model();
        if (model.Language.equals( "en" ))
            setContentView( R.layout.activity_forget_password );
        else
            setContentView( R.layout.activity_forget_password_ar );


        Toolbar mToolbar = (Toolbar) findViewById( R.id.account_toolbar );
        setSupportActionBar( mToolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( false );
        ImageButton back = (ImageButton) mToolbar.findViewById( R.id.left_back );
        TextView mText = (TextView) mToolbar.findViewById( R.id.txt_title );

        mParent = (LinearLayout) findViewById( R.id.activity_register );
        mSubmit = (ImageButton) findViewById( R.id.bt_submit );
        mUserName = (EditText) findViewById( R.id.input_username );
        mCoordinatorLayout = (CoordinatorLayout) findViewById( R.id.coordinatorLayout );

        cond = new ConnectionDetector( this );
        pd = new ProgressDialog( this );
        if (Model.Language.equals( "en" ))
            pd.setMessage( "Connecting to server..." );
        else
            pd.setMessage( "جاري الاتصال بمزود الخدمه" );

        if (model.Language.equals( "en" ))
            mText.setText( R.string.for_pwd );
        else
            mText.setText( R.string.for_pwd_ar );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                finish();

            }
        } );

        mParent.setOnTouchListener( new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent arg1) {

                hideKeyboard( view );
                return false;
            }


        } );
        mSubmit.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cond.isConnectingToInternet()) {

                    if (isvalid())
                        pd.show();
                    setForgetPassword();


                } else {
                    callSnackbar( "Network connection is needed" );
                }
            }
        } );

    }

    private void setForgetPassword() {


        String url = null;
        if (model.Language.equals( "en" )) {
            url = ConfigureURLS.URL_en_ForgetPassword;
        } else {
            url = ConfigureURLS.URL_ar_ForgetPassword;
        }

        System.out.println( "URLForgetPassword======= " + url );

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println( "GET_ForgetPassword" + response );
                        try {
                            boolean mIsSuccess = Parser.getForgetPassword( response.toString() );
                            if (mIsSuccess) {
                                exit();

                            } else {
                                Toast.makeText( ForgetPasswordActivity.this, "" + Parser.getForgetMessage(), Toast.LENGTH_SHORT ).show();

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

                } ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();

                params.put( "email", mUserName.getText().toString().trim() );


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }

    private void exit() {

        Toast.makeText( ForgetPasswordActivity.this, "" + Parser.getForgetMessage(), Toast.LENGTH_SHORT ).show();
        finish();
    }


    private boolean isvalid() {
        mEmail = mUserName.getText().toString().trim();
        if (mEmail.equals( "" )) {
            if (Model.Language.equals( "en" )) {
                mUserName.setError( getString( R.string.edit_error ) );
            }else {
                mUserName.setError( getString( R.string.edit_error_ar ) );
            }
            mUserName.requestFocus();
            return false;
        } else {
            if (!isValidEmail( mEmail )) {
                if (Model.Language.equals( "en" )) {
                    mUserName.setError( getString( R.string.email_error ) );
                }else {
                    mUserName.setError( getString( R.string.email_error_ar ) );
                }
                mUserName.requestFocus();
                return false;
            } else {
                return true;
            }
        }
    }


    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher( target ).matches();
        }
    }

    protected void hideKeyboard(View view) {
        InputMethodManager in = (InputMethodManager) this.getSystemService( Context.INPUT_METHOD_SERVICE );
        in.hideSoftInputFromWindow( view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS );
    }

    @Override
    public void onBackPressed() {

        finish();


    }

    private void callSnackbar(String message) {

        Snackbar snackbar = Snackbar.make( mCoordinatorLayout, message, Snackbar.LENGTH_LONG );
        snackbar.getView().setBackgroundColor( ContextCompat.getColor( this, R.color.colorPrimary ) );
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById( android.support.design.R.id.snackbar_text );
        tv.setTextColor( Color.WHITE );
        snackbar.show();
    }

}
