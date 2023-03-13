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
import com.bgmiddleeast.Utilities.Profile;
import com.bgmiddleeast.Utilities.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;


public class ProfileActivity extends AppCompatActivity {

    ImageButton mUpdate;

    LinearLayout mForgetPwd;
    Model model;

    Spinner mCompanyName,  mRole;
    FrameLayout mRegisterFrame, mPasswordFrame;
    private static TextView mProfCountry;
    private ArrayList<Type> Spin_company;
    private ArrayList<CountryList> Spin_country;
    private EditText mFirstName, mLastName, mEmail, mPhoneNo,mAge, mPassword;
    private String firstName, lastname, email, phone,age, company, country, role, password;
    private CoordinatorLayout mCoordinatorLayout;
    private AppManager mAppManager;
    private String[] Srole = {"Select Role ", "Distributer", "Branch in charge", "Service advisor", "Inventory Controller", "Technician"};
    private ArrayList<String> Spin_role = new ArrayList<>();
    ConnectionDetector cond;
    ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model = new Model();
        if (model.Language.equals("en"))
            setContentView(R.layout.activity_profile);
        else
            setContentView(R.layout.activity_profile_ar);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) toolbar.findViewById(R.id.left_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, NewDashboard.class));
                overridePendingTransition( R.anim.fadein, R.anim.fadeout );
                finish();
            }
        });

        mUpdate = (ImageButton) findViewById(R.id.bt_update);



        mAppManager = AppManager.getInstance(this);
        cond=new ConnectionDetector(this);
        pd=new ProgressDialog(this);
        if (Model.Language.equals( "en" ))
            pd.setMessage( "Connecting to server..." );
        else
            pd.setMessage( "جاري الاتصال بمزود الخدمه" );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(ProfileActivity.this, NewDashboard.class));
                finish();

            }
        });
        mUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cond.isConnectingToInternet()){
                    if (isvalid()) {
                        pd.show();

                        setUpdate();


                    }
            }else {
                    if (Model.Language.equals( "en" ))
                        callSnackbar( "Network connection needed" );
                    else
                        callSnackbar( "لابد من الاتصال بالشبكه" );
            }
            }
        });


        mCompanyName = (Spinner) findViewById(R.id.spn_company);
        mProfCountry = (TextView) findViewById(R.id.txt_country);
        mRole = (Spinner) findViewById(R.id.spn_role);
        mRegisterFrame = (FrameLayout) findViewById(R.id.fram_register);
        mPasswordFrame = (FrameLayout) findViewById(R.id.fram_password);
        mFirstName = (EditText) findViewById(R.id.input_name);
        mLastName = (EditText) findViewById(R.id.input_last_name);
        mEmail = (EditText) findViewById(R.id.input_email);
        mPhoneNo = (EditText) findViewById(R.id.input_phone_no);
        mPassword = (EditText) findViewById(R.id.input_password);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mAge=(EditText)findViewById(R.id.input_age);

        for (int i = 0; i < Srole.length; i++) {
            Spin_role.add(Srole[i]);
        }

        Spin_country = new ArrayList<>();
        Spin_company = new ArrayList<>();
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, Spin_role);
        adapter3.setDropDownViewResource(R.layout.layout_drop_list);
        mRole.setPrompt("Role");
        mRole.setAdapter(adapter3);
        if(cond.isConnectingToInternet()) {


            getCompany();
        }else{
            callSnackbar("Network connection needed");
        }


        mRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                role = "" + position;
                if (position == 0) {
                    ((TextView) mRole.getSelectedView()).setTextColor(Color.parseColor("#797979"));

                } else {
                    ((TextView) mRole.getSelectedView()).setTextColor(Color.parseColor("#FFFFFF"));

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });


    }
    public  void setCountry(int position){
        model.Country_code = Parser.getDataCountryList().get(position).getCountry_id();
        mProfCountry.setText(Parser.getDataCountryList().get(position).getCountry_name());
        mProfCountry.setTextColor(Color.parseColor("#ffffff"));
        mProfCountry.setError(null);
    }

    private void setUpdate() {
        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_updateProfile;
        } else {
            url = ConfigureURLS.URL_ar_updateProfile;
        }

        System.out.println("URL Update======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET_update method" + response);
                        try {
                            boolean mIsSuccess = Parser.getUpdateProfile(response.toString());
                            if (mIsSuccess) {
                                exit();

                            } else {
                                Toast.makeText(ProfileActivity.this, "" + Parser.getUpdateMessage(), Toast.LENGTH_SHORT).show();

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
                params.put("user_id", mAppManager.getUserId());
                params.put("firstname", firstName);
                params.put("lastname", lastname);
                params.put("email", email);
                params.put("phone_number", phone);
                params.put("age", age);
                params.put("role", role);
                params.put("company_name", company);
                params.put("country_id",   model.Country_code);
                params.put("confirm_password", password);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void getCountryList() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_country_list;
        } else {
            url = ConfigureURLS.URL_ar_country_list;
        }


        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET_Country_list === " + response);
                        try {
                            boolean mIsSuccess = Parser.getCountryList(response.toString());
                            if (mIsSuccess) {
                                Spin_country = Parser.getDataCountryList();
                                if (Spin_country != null && Spin_country.size() != 0) {
                                    setSpin("country");

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

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void getCompany() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_get_companies;
        } else {
            url = ConfigureURLS.URL_ar_get_companies;
        }

        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET_Company_list === " + response);
                        try {
                            boolean mIsSuccess = Parser.getCompanyList(response.toString());
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

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }

    private void setCompany() {
        if (Spin_company != null && Spin_company.size() != 0) {
            ArrayList<String> companyList = new ArrayList<>();
            companyList.add("Company Name");
            for (int i = 0; i < Spin_company.size(); i++) {
                companyList.add(Spin_company.get(i).getName());
            }
            System.out.println("GET_Company === " + companyList.size());

            ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, companyList);
            adapter1.setDropDownViewResource(R.layout.layout_drop_list);
            mCompanyName.setPrompt("Company name");
            mCompanyName.setAdapter(adapter1);


        }
        mCompanyName.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {


                if (position == 0) {
                    company = "";
                    ((TextView) mCompanyName.getSelectedView()).setTextColor(Color.parseColor("#797979"));
                } else {
                    int pos = position;
                    company = Spin_company.get(--pos).getId();
                    ((TextView) mCompanyName.getSelectedView()).setTextColor(Color.parseColor("#FFFFFF"));
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }

    private void setSpin(String type) {

        if(cond.isConnectingToInternet()) {


            getProfile();
        }else{
            callSnackbar("Network connection needed");
        }
        mProfCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                model.isFromRegistration=false;
                mAge.clearFocus();
                Intent in1 = new Intent(ProfileActivity.this, SearchCountryActivity.class);
                startActivity(in1);
            }
        });

       /* if (Spin_country != null && Spin_country.size() != 0) {
            ArrayList<String> countryList = new ArrayList<>();
            countryList.add("Country");
            for (int i = 0; i < Spin_country.size(); i++) {
                countryList.add(Spin_country.get(i).getCountry_name());
            }
            System.out.print("countryList" + countryList.size());
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, countryList);
            adapter2.setDropDownViewResource(R.layout.layout_drop_list);
            mProfCountry.setPrompt("Country");
            mProfCountry.setAdapter(adapter2);
        }
        mProfCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    country = "";
                    ((TextView) mProfCountry.getSelectedView()).setTextColor(Color.parseColor("#797979"));
                } else {
                    int pos = position;
                    country = Spin_country.get(--pos).getCountry_id();
                    ((TextView) mProfCountry.getSelectedView()).setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });*/



    }

    private void getProfile() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_getProfile;
        } else {
            url = ConfigureURLS.URL_ar_getProfile;
        }

        System.out.println("URL getProfile======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("getProfile method" + response);
                        try {
                            boolean mIsSuccess = Parser.getProfile(response.toString());
                            if (mIsSuccess) {
                                setData();

                            } else {
                                Toast.makeText(ProfileActivity.this, "" + Parser.getMessage(), Toast.LENGTH_SHORT).show();

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

                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("user_id", mAppManager.getUserId());


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void setData() {

        Profile profile = Parser.getProfileData();
        mFirstName.setText(profile.getFirstName());
        mLastName.setText(profile.getLastName());
        mEmail.setText(profile.getEmail());
        mPhoneNo.setText(profile.getPhone_number());
        mAge.setText(profile.getAge());
        mProfCountry.setText(profile.getCountry_code());
        mProfCountry.setTextColor(Color.parseColor("#FFFFFF"));




        for (int i = 0; i < Spin_company.size(); i++) {
            if (Spin_company.get(i).getName().equals(profile.getCompany_name())) {
                int pos=i;
                mCompanyName.setSelection(++pos);
            }
        }


        for (int i = 0; i < Spin_country.size(); i++) {
            if (Spin_country.get(i).getCountry_name().equals(profile.getCountry_code())) {
                int pos=i;
                mProfCountry.setText(Spin_country.get(i).getCountry_name());
                model.Country_code=Spin_country.get(i).getCountry_id();
            }
        }
        for (int i = 0; i < Spin_role.size(); i++) {
            if (Spin_role.get(i).equals(profile.getRole())) {
                int pos=i;
                mRole.setSelection(pos);
            }
        }


    }


    private boolean isvalid() {
        firstName = mFirstName.getText().toString().trim();
        lastname = mLastName.getText().toString().trim();
        phone = mPhoneNo.getText().toString().trim();
        email = mEmail.getText().toString().trim();
        password = mPassword.getText().toString().trim();
        age=mAge.getText().toString().trim();
        country=mProfCountry.getText().toString().trim();

        if (firstName.equals("") && lastname.equals("") && email.equals("") && phone.equals("")&&country.equals("")) {
            if (Model.Language.equals( "en" )) {
                callSnackbar( getString( R.string.edit_all_error ) );
            }else {
                callSnackbar( getString( R.string.edit_all_error_ar ) );
            }
            return false;
        } else {
            if (firstName.equals("")) {
                if (Model.Language.equals( "en" )) {
                    mFirstName.setError( getString( R.string.edit_error ) );
                }
                mFirstName.requestFocus();
                return false;
            } else if (lastname.equals("")) {
                if (Model.Language.equals( "en" )) {
                    mLastName.setError( getString( R.string.edit_error ) );
                }else {
                    mLastName.setError( getString( R.string.edit_error_ar ) );
                }
                mLastName.requestFocus();
                return false;

            } else if (email.equals("")) {
                if (Model.Language.equals( "en" )) {
                    mEmail.setError( getString( R.string.edit_error ) );
                }else {
                    mEmail.setError( getString( R.string.edit_error_ar ) );
                }
                mEmail.requestFocus();
                return false;

            } else if (phone.equals("")) {
                if (Model.Language.equals( "en" )) {
                    mPhoneNo.setError( getString( R.string.edit_error ) );
                }else {
                    mPhoneNo.setError( getString( R.string.edit_error_ar ) );
                }
                mPhoneNo.requestFocus();
                return false;
            }else if (age.equals("")) {
                if (Model.Language.equals( "en" )) {
                    mAge.setError( getString( R.string.edit_error ) );
                }else {
                    mAge.setError( getString( R.string.edit_error_ar ) );
                }
                    mAge.requestFocus();
                    return false;
            } else if (company.equals("")) {
                if (Model.Language.equals( "en" )) {
                    callSnackbar( getString( R.string.edit_error ) );
                    ((TextView) mCompanyName.getSelectedView()).setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.edit_error_ar ) );
                    ((TextView) mCompanyName.getSelectedView()).setError( getString( R.string.edit_error_ar ) );
                }
                mCompanyName.requestFocus();
                return false;
            } else if (country.equals("Country")) {
                if (Model.Language.equals( "en" )) {
                    mProfCountry.setError( getString( R.string.edit_error ) );
                }else {
                    mProfCountry.setError( getString( R.string.edit_error_ar ) );
                }
                mProfCountry.requestFocus();
                return false;
            } else if (role.equals("0")) {
                if (Model.Language.equals( "en" )) {
                    callSnackbar( getString( R.string.mandatory_error ) );
                    ((TextView) mRole.getSelectedView()).setError( getString( R.string.edit_error ) );
                }else {
                    callSnackbar( getString( R.string.mandatory_error_ar ) );
                    ((TextView) mRole.getSelectedView()).setError( getString( R.string.edit_error_ar ) );
                }
                mRole.requestFocus();
                return false;
            } else {
                if (!isValidEmail(email)) {
                    if (Model.Language.equals( "en" )) {
                        mEmail.setError( getString( R.string.email_error ) );
                    }else {
                        mEmail.setError( getString( R.string.email_error_ar ) );
                    }
                    mEmail.requestFocus();
                    return false;
                } else {
                    if (!isValidMobile(phone)) {
                        if (Model.Language.equals( "en" )) {
                            mPhoneNo.setError( getString( R.string.phone_error ) );
                        }else {
                            mPhoneNo.setError( getString( R.string.phone_error_ar ) );
                        }
                        mPhoneNo.requestFocus();
                        return false;
                    } else {
                        if(mPassword.getText().toString().trim().length()>0) {
                            if (mPassword.getText().toString().trim().length() < 5) {
                                if (Model.Language.equals( "en" )) {
                                    callSnackbar( getString( R.string.password_error_validate ) );
                                }else {
                                    callSnackbar( getString( R.string.password_error_validate_ar ) );

                                }
                                return false;
                            } else {
                                return true;
                            }
                        }else{
                            return true;
                        }
                    }

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

    private boolean isValidMobile(String phone) {
        boolean check = false;
        if (!Pattern.matches("[a-zA-Z]+", phone)) {
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


    private void callSnackbar(String message) {

        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }
    private void exit() {
        Toast.makeText(ProfileActivity.this, "" + Parser.getUpdateMessage(), Toast.LENGTH_SHORT).show();
        Intent in1 = new Intent(ProfileActivity.this, NewDashboard.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(in1);
        finish();
//        Intent intent = getIntent();
//        finish();
////      overridePendingTransition(R.anim.slide_to_left, R.anim.slide_to_right);
//        startActivity(intent);


    }
    @Override
    public void onBackPressed() {

        startActivity(new Intent(ProfileActivity.this, NewDashboard.class));

        finish();


    }




}
