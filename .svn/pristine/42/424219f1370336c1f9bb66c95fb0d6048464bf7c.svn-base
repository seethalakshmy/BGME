package com.bgmiddleeast.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bgmiddleeast.Adapter.ServiceCheckdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.AppManager;
import com.bgmiddleeast.Utilities.ConfigureURLS;
import com.bgmiddleeast.Utilities.ConnectionDetector;
import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Service;
import com.bgmiddleeast.Utilities.Type;
import com.bgmiddleeast.database.CommentsDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.bgmiddleeast.Adapter.ServiceCheckdapter.ServiceL;

public class TrainingActivity extends AppCompatActivity {

    Spinner mLang;
    String language,service,requirements;
    private ArrayList<Type> Spin_lang = new ArrayList<>();
    RecyclerView mServiceList;
    ServiceCheckdapter adapter;
    Model model=new Model();
    LinearLayout mLayoutSpinner;
    RelativeLayout mForm,mService;
    TextView mOK;
    ImageButton mSubmit;
    EditText mRequirement;
    private CoordinatorLayout mCoordinatorLayout;
    private AppManager mAppManager;
    private TextView mServiceText;
    private JSONArray jsonArr =new JSONArray();
    private JSONObject FavDocObj = new JSONObject();
    ConnectionDetector cond;
    ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (model.Language.equals("en"))
        setContentView(R.layout.activity_training);
        else
            setContentView(R.layout.activity_training_ar);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) toolbar.findViewById(R.id.left_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in3 = new Intent(TrainingActivity.this, NewDashboard.class);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                startActivity(in3);
                finish();
            }
        });

        mAppManager=AppManager.getInstance(this);
        cond=new ConnectionDetector(this);
        pd=new ProgressDialog(this);

        TextView  mTextHead= (TextView) toolbar.findViewById(R.id.txt_header_plain);

        if (Model.Language.equals( "en" ))
            mTextHead.setText("Training");
        else
            mTextHead.setText( getString( R.string.training_ar) );
        if (Model.Language.equals( "en" ))
            pd.setMessage( "Connecting to server..." );
        else
            pd.setMessage( "جاري الاتصال بمزود الخدمه" );

        mLang = (Spinner) findViewById(R.id.spn_lang);

        mServiceList = (RecyclerView) findViewById(R.id.main_recycler);
        mLayoutSpinner=(LinearLayout)findViewById(R.id.lin_spinner);
        mForm=(RelativeLayout)findViewById(R.id.lin_form) ;
        mOK=(TextView) findViewById(R.id.txt_ok);
        mService=(RelativeLayout) findViewById(R.id.service);
        mSubmit=(ImageButton) findViewById(R.id.bt_Submit);

        mRequirement=(EditText)findViewById(R.id.input_requirement);
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mServiceText=(TextView) findViewById(R.id.input_service);
        getCountryList();

        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayoutSpinner.setVisibility(View.INVISIBLE);
                mForm.setVisibility(View.VISIBLE);
                if(ServiceL.size()==0) {
                    if (Model.Language.equals( "en" ))
                    mServiceText.setHint("Service");
                    else
                        mServiceText.setHint(getString( R.string.service_ar));
                    mServiceText.setHintTextColor(Color.parseColor("#797979"));
                }else {
                    mServiceText.setHintTextColor(Color.parseColor("#FFFFFF"));
                    mServiceText.setHint(ServiceL.size() + " Service selected");
                }

            }
        });
        mService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayoutSpinner.setVisibility(View.VISIBLE);
                mForm.setVisibility(View.INVISIBLE);
            }
        });
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cond.isConnectingToInternet()){

                    if (isValid()) {
                        pd.show();
                        setObj();


                    }
            }else{
                    if (Model.Language.equals( "en" ))
                        callSnackbar("Network connection needed");
                    else
                        callSnackbar("لابد من الاتصال بالشبكه");
            }




            }
        });


        mServiceList.setLayoutManager(new LinearLayoutManager(this));
        mServiceList.setItemAnimator(new DefaultItemAnimator());
        mServiceList.setHasFixedSize(true);

        CommentsDataSource datasource = new CommentsDataSource(this);
        datasource.open();
        if (model.Language.equals("en")) {
            model.ServiceList = (ArrayList<Service>) datasource.getAllServiceList();
            model.ProductList = (ArrayList<Products>) datasource.getAllProductList();
        } else {
            model.ServiceList = (ArrayList<Service>) datasource.getAllServiceListAR();
            model.ProductList = (ArrayList<Products>) datasource.getAllProductListAR();

        }
        if (model.ServiceList.size() == 0) {
            if (model.Language.equals("en")) {
                model.ServiceList = Parser.serviceList_en;
                for (int i = 0; i < model.ServiceList.size(); i++) {

                    for (int j = 0; j > model.ServiceList.get(i).getProducts().size(); j++) {
                        Products product = new Products();
                        model.ProductList.add(product);

                    }

                }
            } else {
                model.ServiceList = Parser.serviceList_ar;
                for (int i = 0; i < model.ServiceList.size(); i++) {

                    for (int j = 0; j > model.ServiceList.get(i).getProducts().size(); j++) {
                        Products product = new Products();
                        model.ProductList.add(product);

                    }

                }
            }
        }
        int value = 0;
        for (int i = 0; i < model.ServiceList.size(); i++) {
            if (value < Integer.parseInt(model.ServiceList.get(i).getService_order()))
                value = Integer.parseInt(model.ServiceList.get(i).getService_order());

        }

//        int value = mAppManager.getMaxValue();
        ArrayList<Service> sortList = new ArrayList<>();
        for (int i = 0; i <= value; i++) {

            int tmp = i;
            String sortId = String.valueOf(tmp);
            for (int j = 0; j < model.ServiceList.size(); j++) {
                if (model.ServiceList.get(j).getService_order().equals(sortId)) {
                    sortList.add(model.ServiceList.get(j));
                }

            }
        }
        model.ServiceList.clear();
        model.ServiceList = sortList;

        datasource.close();

        if (adapter == null) {
            adapter = new ServiceCheckdapter(this, model.ServiceList);
            mServiceList.setAdapter(adapter);
            ServiceL.clear();
        }

        adapter.SetOnItemClickListener(new ServiceCheckdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


    }

    private void setObj() {

        if(ServiceL.size()!=0) {

            try {

                for (int i = 0; i < ServiceL.size(); i++) {

                    JSONObject pnObj = new JSONObject();
                    try {

                        pnObj.put("service_id", ServiceL.get(i).getService_id());
                        jsonArr.put(pnObj);

                    } catch (JSONException ex) {

                        ex.printStackTrace();
                    }
                }

//                FavDocObj.put("service", jsonArr);
//                Log.i("service", "" + FavDocObj);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        setTraining();
    }

    private void setTraining() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_training;
        } else {
            url = ConfigureURLS.URL_ar_training;
        }

        System.out.println("URL training======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET_Registraion method" + response);
                        try {
                            boolean mIsSuccess = Parser.getTraining(response.toString());
                            if (mIsSuccess) {
                                exit();

                            } else {
                                Toast.makeText(TrainingActivity.this, Parser.getTrainingMessage(), Toast.LENGTH_SHORT).show();

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
                params.put("language_id", language);
                params.put("requirement", requirements);
                params.put("services", jsonArr.toString());



                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void exit() {

        Toast.makeText(TrainingActivity.this, "" + Parser.getTrainingMessage(), Toast.LENGTH_SHORT).show();
        Intent in1 = new Intent(TrainingActivity.this, NewDashboard.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(in1);
        finish();

    }

    private boolean isValid() {

       requirements=mRequirement.getText().toString().trim();

             if (language.equals("")) {
                 if (Model.Language.equals( "en" )) {
                     callSnackbar( getString( R.string.edit_error ) );
                     ((TextView) mLang.getSelectedView()).setError( getString( R.string.edit_error ) );
                 }else {
                     callSnackbar( getString( R.string.edit_error_ar ) );
                     ((TextView) mLang.getSelectedView()).setError( getString( R.string.edit_error_ar ) );
                 }
                mLang.requestFocus();

                return false;
            } else if (ServiceL.size()==0) {
                 if (Model.Language.equals( "en" )) {
                     mServiceText.setError( getString( R.string.edit_error ) );
                     callSnackbar( getString( R.string.edit_error ) );
                 }else {
                     mServiceText.setError( getString( R.string.edit_error_ar ) );
                     callSnackbar( getString( R.string.edit_error_ar ) );
                 }
                 mServiceText.requestFocus();
                return false;
           /* }else if(requirements.equals("")){
                mRequirement.setError(getString(R.string.edit_error));
                mRequirement.requestFocus();
                callSnackbar(getString(R.string.edit_error));
                return false;*/
            }else{
                return true;
            }

    }


    //Laguage
    private void getCountryList() {

        String url = null;
        if (model.Language.equals("en")) {
            url = ConfigureURLS.URL_en_language_list;
        } else {
            url = ConfigureURLS.URL_ar_language_list;
        }


        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET_Language_list === " + response);
                        try {
                            boolean mIsSuccess = Parser.getLanguageList(response.toString());
                            if (mIsSuccess) {
                                Spin_lang = Parser.getDataLanguageList();
                                if (Spin_lang != null && Spin_lang.size() != 0) {
                                    setSpin();

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

    private void setSpin() {

        if (Spin_lang != null && Spin_lang.size() != 0) {
            ArrayList<String> LanguageList = new ArrayList<>();
            if (Model.Language.equals( "en" ))
            LanguageList.add("Language");
            else
                LanguageList.add("لغه");

            for (int i = 0; i < Spin_lang.size(); i++) {
                LanguageList.add(Spin_lang.get(i).getName());
            }
            System.out.print("LanguageList" + LanguageList.size());
            ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, LanguageList);
            adapter2.setDropDownViewResource(R.layout.layout_drop_list);
            mLang.setPrompt("Country");
            mLang.setAdapter(adapter2);
        }
        mLang.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    language = "";
                    ((TextView) mLang.getSelectedView()).setTextColor(Color.parseColor("#797979"));
                } else {
                    int pos = position;
                    language = Spin_lang.get(--pos).getId();
                    ((TextView) mLang.getSelectedView()).setTextColor(Color.parseColor("#FFFFFF"));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });
    }
    private void callSnackbar(String message) {

        Snackbar snackbar = Snackbar.make(mCoordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }
    public void onBackPressed() {

        Intent in3 = new Intent(TrainingActivity.this, NewDashboard.class);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        startActivity(in3);
        finish();

    }


}
