package com.bgmiddleeast.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
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
import com.bgmiddleeast.Utilities.CountryList;
import com.bgmiddleeast.Utilities.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    LinearLayout mForgetPwd;
    Model model;
    ImageButton mNext, mRegister;
    private static TextView mCountry;
    Spinner mCompanyName, mRole;
    FrameLayout mRegisterFrame, mPasswordFrame;

    private ArrayList<Type> Spin_company;
    private ArrayList<CountryList> Spin_country;
    private EditText mFirstName, mLastName, mEmail, mPhoneNo, mAge, mPassword, mConformPassword;
    private String firstName, lastname, email, age, phone, company, country, role, password;
    private CoordinatorLayout mCoordinatorLayout;
    private AppManager mAppManager;
    private ConnectionDetector cond;

    private String[] Spin_role = {"Select Role ", "Distributors", "Branch in charge", "Service advisor", "Inventory Controller", "Technician"};

    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        model = new Model();
        if (model.Language.equals( "en" ))
            setContentView( R.layout.activity_register );
        else
            setContentView( R.layout.activity_register_ar );

        final Toolbar toolbar = (Toolbar) findViewById( R.id.account_toolbar );
        setSupportActionBar( toolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( false );
        ImageButton back = (ImageButton) toolbar.findViewById( R.id.left_back );
        mAppManager = AppManager.getInstance( this );
        cond = new ConnectionDetector( this );
        pd = new ProgressDialog( this );
        if (Model.Language.equals( "en" ))
            pd.setMessage( "Connecting to server..." );
        else
            pd.setMessage( "جاري الاتصال بمزود الخدمه" );

        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mPasswordFrame.getVisibility() == View.VISIBLE) {
                    mPasswordFrame.setVisibility( View.GONE );
                    mRegisterFrame.setVisibility( View.VISIBLE );
                } else {
                    finish();
                }
            }
        } );

        mNext = (ImageButton) findViewById( R.id.bt_Next );
        mCompanyName = (Spinner) findViewById( R.id.spn_company );
        mCountry = (TextView) findViewById( R.id.txt_country );
        mRole = (Spinner) findViewById( R.id.spn_role );
        mRegisterFrame = (FrameLayout) findViewById( R.id.fram_register );
        mPasswordFrame = (FrameLayout) findViewById( R.id.fram_password );
        mRegister = (ImageButton) findViewById( R.id.bt_login );
        mFirstName = (EditText) findViewById( R.id.input_name );
        mLastName = (EditText) findViewById( R.id.input_last_name );
        mEmail = (EditText) findViewById( R.id.input_email );
        mPhoneNo = (EditText) findViewById( R.id.input_phone_no );
        mAge = (EditText) findViewById( R.id.input_age );
        mPassword = (EditText) findViewById( R.id.input_password );
        mConformPassword = (EditText) findViewById( R.id.input_password_confirm );
        mCoordinatorLayout = (CoordinatorLayout) findViewById( R.id.coordinatorLayout );
        model.Country_code = "";
        mNext.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (isvalid()) {
                    mRegisterFrame.setVisibility( View.GONE );
                    mPasswordFrame.setVisibility( View.VISIBLE );
                }
            }
        } );
        mRegister.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cond.isConnectingToInternet()) {
                    if (isvalidPasswrd()) {
                        pd.show();
                        password = mPassword.getText().toString().trim();
                        setRegistration();


                    }
                } else {

                    callSnackbar( "Network connection is needed" );
                }
            }
        } );


        Spin_country = new ArrayList<>();
        Spin_company = new ArrayList<>();
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>( this, R.layout.book_layout_drop_title, Spin_role );
        adapter3.setDropDownViewResource( R.layout.layout_drop_list );
        mRole.setPrompt( "Role" );
        mRole.setAdapter( adapter3 );
        if (cond.isConnectingToInternet()) {
            getCompany();
        } else {
            callSnackbar( "Network connection is needed" );
        }


        mRole.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                role = "" + position;
                if (position == 0) {
                    ((TextView) mRole.getSelectedView()).setTextColor( Color.parseColor( "#797979" ) );

                } else {
                    ((TextView) mRole.getSelectedView()).setTextColor( Color.parseColor( "#FFFFFF" ) );

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        } );


    }

    public void setCountry(int position) {
        model.Country_code = Parser.getDataCountryList().get( position ).getCountry_id();
        mCountry.setText( Parser.getDataCountryList().get( position ).getCountry_name() );
        mCountry.setTextColor( Color.parseColor( "#ffffff" ) );
        mCountry.setError( null );
    }

    private boolean isvalidPasswrd() {
        if (mPassword.getText().toString().trim().equals( "" ) && mConformPassword.getText().toString().trim().equals( "" )) {
            if (Model.Language.equals( "en" )) {
                callSnackbar( getString( R.string.edit_all_error ) );
            }else {
                mPassword.setError( getString( R.string.edit_all_error_ar ) );
            }
            return false;
        } else {
            if (mPassword.getText().toString().trim().equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mPassword.setError( getString( R.string.edit_error ) );
                }else {
                    mPassword.setError( getString( R.string.edit_error_ar ) );
                }
                mPassword.requestFocus();
                return false;
            } else if (mConformPassword.getText().toString().trim().equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mConformPassword.setError( getString( R.string.edit_error ) );
                }else {
                    mConformPassword.setError( getString( R.string.edit_error_ar ) );
                }
                mConformPassword.requestFocus();
                return false;
            } else {
                if (mPassword.getText().toString().trim().length() < 5) {
                    callSnackbar( getString( R.string.password_error_validate ) );
                    return false;
                } else {
                    if (!mPassword.getText().toString().trim().equals( mConformPassword.getText().toString().trim() )) {
                        callSnackbar( getString( R.string.password_error_ar ) );
                        return false;
                    } else {
                        return true;
                    }
                }
            }

        }

    }

    private boolean isvalid() {
        firstName = mFirstName.getText().toString().trim();
        lastname = mLastName.getText().toString().trim();
        phone = mPhoneNo.getText().toString().trim();
        age = mAge.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        country = mCountry.getText().toString().trim();
        mCountry.setError( null );
        if (firstName.equals( "" ) && lastname.equals( "" ) && email.equals( "" ) && phone.equals( "" ) && age.equals( "" ) && country.equals( "" )) {
            if (Model.Language.equals( "en" )) {
                callSnackbar( getString( R.string.edit_all_error ) );
            }else {
                callSnackbar( getString( R.string.edit_all_error_ar ) );
            }
            return false;
        } else {
            if (firstName.equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mFirstName.setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                }
                mFirstName.requestFocus();
                return false;
            } else if (lastname.equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mLastName.setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                }
                mLastName.requestFocus();
                return false;

            } else if (email.equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mEmail.setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                }
                mEmail.requestFocus();
                return false;

            } else if (phone.equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mPhoneNo.setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                }
                mPhoneNo.requestFocus();
                return false;

            } else if (age.equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    mAge.setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                }
                mAge.requestFocus();
                return false;

            } else if (company.equals( "" )) {
                if (Model.Language.equals( "en" )) {
                    callSnackbar( getString( R.string.edit_error ) );
                    ((TextView) mCompanyName.getSelectedView()).setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                    ((TextView) mCompanyName.getSelectedView()).setError( getString( R.string.edit_error ) );
                }
                mCompanyName.requestFocus();
                return false;
            } else if (country.equals( "Country" )) {
                if (Model.Language.equals( "en" )) {
                    mCountry.setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                }
                mCountry.requestFocus();
                return false;
            } else if (role.equals( "0" )) {
                if (Model.Language.equals( "en" )) {
                    callSnackbar( getString( R.string.mandatory_error ) );
                    ((TextView) mRole.getSelectedView()).setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_all_error_ar ) );
                    ((TextView) mRole.getSelectedView()).setError( getString( R.string.edit_error_ar ) );
                }
                mRole.requestFocus();
                return false;
            } else {
                if (!isValidEmail( email )) {
                    if (Model.Language.equals( "en" )) {
                        mEmail.setError( getString( R.string.email_error ) );
                    }else {
                        mEmail.setError( getString( R.string.email_error_ar ) );
                    }
                    mEmail.requestFocus();
                    return false;
                } else {
                   /* if (!isValidMobile(phone)) {
                        mPhoneNo.setError(getString(R.string.phone_error));
                        mPhoneNo.requestFocus();
                        return false;
                    } else {*/
                    return true;
//                    }

                }

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

    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches( "[a-zA-Z]+", phone )) {
//            if (phone.length() < 6 || phone.length() > 10) {
//                // if(phone.length() != 10) {
//                check = false;
//
//            } else {
            check = true;
//            }
        } else {
            check = false;
        }
        return check;
    }


    private void getCountryList() {

        String url = null;
        if (model.Language.equals( "en" )) {
            url = ConfigureURLS.URL_en_country_list;
        } else {
            url = ConfigureURLS.URL_ar_country_list;
        }


        System.out.println( "URL======= " + url );

        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {


                        System.out.println( "GET_Country_list === " + response );
                        try {
                            boolean mIsSuccess = Parser.getCountryList( response.toString() );
                            if (mIsSuccess) {
                                Spin_country = Parser.getDataCountryList();
                                if (Spin_country != null && Spin_country.size() != 0) {
                                    setSpin( "country" );

                                }

                            } else {


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

                } );
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );


    }


    private void getCompany() {

        String url = null;
        if (model.Language.equals( "en" )) {
            url = ConfigureURLS.URL_en_get_companies;
        } else {
            url = ConfigureURLS.URL_ar_get_companies;
        }

        System.out.println( "URL======= " + url );

        StringRequest stringRequest = new StringRequest( Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println( "GET_Company_list === " + response );
                        try {
                            boolean mIsSuccess = Parser.getCompanyList( response.toString() );
                            if (mIsSuccess) {
                                Spin_company = Parser.getDataCompanyList();
                                setCompany();
                                getCountryList();

                            } else {


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

                } );
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );


    }

    private void setCompany() {
        if (Spin_company != null && Spin_company.size() != 0) {
            ArrayList<String> companyList = new ArrayList<>();
            if (Model.Language.equals( "en" ))
                companyList.add( "Company Name" );
            else
                companyList.add( "اسم الشركه" );
            for (int i = 0; i < Spin_company.size(); i++) {
                companyList.add( Spin_company.get( i ).getName() );
            }
            System.out.println( "GET_Company === " + companyList.size() );

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>( this, R.layout.book_layout_drop_title, companyList );
            adapter1.setDropDownViewResource( R.layout.layout_drop_list );
            mCompanyName.setPrompt( "Company name" );
            mCompanyName.setAdapter( adapter1 );


        }
        mCompanyName.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                if (position == 0) {
                    company = "";
                    ((TextView) mCompanyName.getSelectedView()).setTextColor( Color.parseColor( "#797979" ) );
                } else {
                    int pos = position;
                    company = Spin_company.get( --pos ).getId();
                    ((TextView) mCompanyName.getSelectedView()).setTextColor( Color.parseColor( "#FFFFFF" ) );
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        } );
    }

    private void setSpin(String type) {
        mCountry.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.isFromRegistration = true;
                mAge.clearFocus();
                Intent in1 = new Intent( RegisterActivity.this, SearchCountryActivity.class );
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                startActivity( in1 );
            }
        } );

       /* if (Spin_country != null && Spin_country.size() != 0) {
            ArrayList<String> countryList = new ArrayList<>();
            countryList.add("Country");
            for (int i = 0; i < Spin_country.size(); i++) {
                countryList.add(Spin_country.get(i).getCountry_name());
            }
            System.out.print("countryList" + countryList.size());
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, countryList);
            adapter2.setDropDownViewResource(R.layout.layout_drop_list);
            mCountry.setPrompt("Country");
            mCountry.setAdapter(adapter2);
        }
        mCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    country = "";
                    ((TextView) mCountry.getSelectedView()).setTextColor(Color.parseColor("#797979"));
                } else {
                    int pos = position;
                    country = Spin_country.get(--pos).getCountry_id();
                    ((TextView) mCountry.getSelectedView()).setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
*/

    }


    //Registration
    private void setRegistration() {
        String url = null;
        if (model.Language.equals( "en" )) {
            url = ConfigureURLS.URL_en_registration;
        } else {
            url = ConfigureURLS.URL_ar_registration;
        }

        System.out.println( "URL registration======= " + url );

        StringRequest stringRequest = new StringRequest( Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println( "GET_Registraion method" + response );
                        try {
                            boolean mIsSuccess = Parser.getRegistration( response.toString() );
                            if (mIsSuccess) {
                                exit();

                            } else {
                                Toast.makeText( RegisterActivity.this, "" + Parser.getMessage(), Toast.LENGTH_SHORT ).show();

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
                params.put( "firstname", firstName );
                params.put( "lastname", lastname );
                params.put( "email", email );
                params.put( "password", password );
                params.put( "phone", phone );
                params.put( "age", age );
                params.put( "role", role );
                params.put( "company_name", company );
                params.put( "country_id", model.Country_code );
//                params.put("confirm_password", password);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue( this );
        requestQueue.add( stringRequest );
    }

    private void exit() {
        Toast.makeText( RegisterActivity.this, "" + Parser.getMessage(), Toast.LENGTH_SHORT ).show();
        Intent in1 = new Intent( RegisterActivity.this, NewDashboard.class );
        overridePendingTransition( R.anim.fadein, R.anim.fadeout );
        startActivity( in1 );
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


    @Override
    public void onBackPressed() {

        if (mPasswordFrame.getVisibility() == View.VISIBLE) {
            mPasswordFrame.setVisibility( View.GONE );
            mRegisterFrame.setVisibility( View.VISIBLE );
        } else {
            finish();
        }


    }


}
