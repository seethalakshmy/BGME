package com.bgmiddleeast.Activity;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
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
import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Service;
import com.bgmiddleeast.database.CommentsDataSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends AppCompatActivity {

    AppManager mAppManager;
    ConnectionDetector mConDect;
    private CoordinatorLayout coordinatorLayout;
    Model model;
    CommentsDataSource datasource;
    long version_code;
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    List<Service> serviceList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        serviceList = new ArrayList<>();


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        init();

        System.out.println("TokenVal" + mAppManager.getPushToken());
    }

    private void init() {

        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mAppManager = AppManager.getInstance(this);
        mConDect = new ConnectionDetector(this);
        model = (Model) getApplicationContext();
        datasource = new CommentsDataSource(this);
        datasource.open();

        if (mConDect.isConnectingToInternet()) {
            getVersionCode();

        } else {

            serviceList = datasource.getAllServiceList();

            if (serviceList.size() > 0 || Parser.serviceList_en.size() > 0)
                launchActivity(3000);
            else
            if (Model.Language.equals( "en" ))
                callSnackbar("App need to sync data, Please connect to the network");
            else
                callSnackbar("التطبيق تحتاج إلى اعاده ترتيب البيانات، يرجى الاتصال بالشبكة");
        }
    }


    private void getVersionCode() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConfigureURLS.URL_version,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("VERSION === " + response);
                        try {
                            JSONObject mObject = new JSONObject(response);

                            if (mObject.getString("status").equals("TRUE")) {
                                version_code = mObject.getLong("version");
                                if (version_code > Long.parseLong(mAppManager.getVersionCode())) {

                                    System.out.println("call services api");
                                    mAppManager.saveVersionCode(Long.toString(version_code));

                                    if (datasource.hasValuesEn() || datasource.hasValuesAr()) {
                                        datasource.deleteServiceTable();
                                        datasource.deleteProductTable();
                                        datasource.deleteServiceTableAR();
                                        datasource.deleteProductTableAR();
                                    }

                                    getDataFirstTimeEn();
                                } else {
                                    serviceList = datasource.getAllServiceList();

                                    if (serviceList.size() > 0)
                                        launchActivity(100);
                                    else
                                        getDataFirstTimeEn();

                                }

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);

    }

    private void getDataFirstTimeEn() {

        String url = ConfigureURLS.URL_catalogues_en_;

        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET CATALOGUES === " + response);
                        try {
                            boolean mIsSuccess = Parser.getCatalogue(response.toString(), "en");
                            if (mIsSuccess) {
                                if (Parser.getCatalogueList().size() != 0) {
                                    mAppManager.saveMaxValue(Parser.val);
                                    DataBaseCreation(Parser.getCatalogueList(), "en");
                                }
                                getDataFirstTimeAR();
                            } else {
                                if (mAppManager.getSystemLanguage().equals("en"))
//                                    callSnackbar(getString(R.string.failed_message));
                                    callSnackbar(Parser.getMessage());
                                else {
//                                    callSnackbar(getString(R.string.failed_message_ar));
                                    callSnackbar(Parser.getMessage());
                                }
                                mAppManager.saveVersionCode(Long.toString(0));
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);


    }

    private void getDataFirstTimeAR() {

        String url = ConfigureURLS.URL_catalogues_ar;


        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET CATALOGUES === " + response);
                        try {
                            boolean mIsSuccess = Parser.getCatalogue(response.toString(), "ar");
                            if (mIsSuccess) {
                                if (Parser.getCatalogueList().size() != 0) {
                                    mAppManager.saveMaxValue(Parser.val);

                                    DataBaseCreation(Parser.getCatalogueList(), "ar");
                                }
                                serviceList = datasource.getAllServiceList();

                                if (serviceList.size() > 0 || Parser.serviceList_en.size() > 0)
                                    launchActivity(0);
                                else
                                    callSnackbar("Data is missing");

                            } else {
                                if (mAppManager.getSystemLanguage().equals("en"))
                                    callSnackbar(getString(R.string.failed_message));
                                else {
                                    callSnackbar(getString(R.string.failed_message_ar));
                                }
                                mAppManager.saveVersionCode(Long.toString(0));
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
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000, 3,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);


    }


    private void DataBaseCreation(ArrayList<Service> catalogueList, String lang) {

        ArrayList<Service> catalogueLis = new ArrayList<>();
        catalogueLis = catalogueList;

        for (int i = 0; i < catalogueLis.size(); i++) {

            Service service = new Service();
            service = catalogueLis.get(i);
            if (lang.equals("en")) {
                datasource.createService(service.getService_id(), service.getTitle(), service.getService_order(), service.getDescription(), service.getBenefits(),
                        service.getSymptoms(), service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
                        service.getBefore_after(), service.getTools());
                for (int j = 0; j < service.getProducts().size(); j++) {
                    Products products = new Products();
                    products = service.getProducts().get(j);
                    datasource.createProduct(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                            products.getMain_image(), products.getDescription(), products.getModel());
                }
            } else {
                datasource.createServiceAR(service.getService_id(), service.getTitle(), service.getService_order(), service.getDescription(), service.getBenefits(),
                        service.getSymptoms(), service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
                        service.getBefore_after(), service.getTools());
                for (int j = 0; j < service.getProducts().size(); j++) {
                    Products products = new Products();
                    products = service.getProducts().get(j);
                    datasource.createProductAR(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                            products.getMain_image(), products.getDescription(), products.getModel());
                }


            }
        }


    }


    private void launchActivity(int timeout) {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                if (mAppManager.getSystemLanguage().equals("")) {

                    Intent in = new Intent(SplashActivity.this, languageSelectActivity.class);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    startActivity(in);
                    finish();


                } else {
                    model.Language = mAppManager.getSystemLanguage();
                    datasource.close();
                    Intent in = new Intent(SplashActivity.this, NewDashboard.class);
                    overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    startActivity(in);
                    finish();
                }
            }
        }, timeout);
    }

    private void callSnackbar(String message) {

        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
        View view = snackbar.getView();
        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
        tv.setTextColor(Color.WHITE);
        snackbar.show();
    }
}



