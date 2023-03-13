package com.bgmiddleeast.Activity;

import android.support.v7.app.AppCompatActivity;

public class SplashActivity1 extends AppCompatActivity {

//    AppManager mAppManager;
//    ConnectionDetector mConDect;
//    private CoordinatorLayout coordinatorLayout;
//    Model model;
//    CommentsDataSource datasource;
//    long version_code;
//
//    List<Service> serviceList;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        serviceList = new ArrayList<>();
//        init();
//
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().getDecorView().setSystemUiVisibility(
//                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
//                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
//            getWindow().setStatusBarColor(Color.TRANSPARENT);
//        }
//    }
//
//    private void init() {
//        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
//        mAppManager = AppManager.getInstance(this);
//        mConDect = new ConnectionDetector(this);
//        model = (Model) getApplicationContext();
//        datasource = new CommentsDataSource(this);
//        datasource.open();
//
//        if (mAppManager.getSystemLanguage().equals("")) {
//            if (Locale.getDefault().getLanguage().equals("en"))
//                mAppManager.saveSystemLanguage("en");
//            else
//                mAppManager.saveSystemLanguage("ar");
//        }
//        model.Type = mAppManager.getSystemLanguage();
//
//        if (mConDect.isConnectingToInternet()) {
//            getVersionCode();
//
//        } else {
//
//            serviceList = datasource.getAllServiceList();
//
//            if (serviceList.size() > 0)
//                launchActivity(3000);
//            else
//                callSnackbar("App need sync data first time, Please connect to the network");
//        }
//    }
//
//
//    private void getVersionCode() {
//
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, ConfigureURLS.URL_version,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println("VERSION === " + response);
//                        try {
//                            JSONObject mObject = new JSONObject(response);
//
//                            if (mObject.getString("status").equals("TRUE")) {
//                                version_code = mObject.getLong("version");
//                                if (version_code > Long.parseLong(mAppManager.getVersionCode())) {
//
//                                    System.out.println("call services api");
//                                    mAppManager.saveVersionCode(Long.toString(version_code));
//
//
//                                    if (datasource.hasValuesEn() || datasource.hasValuesAr()) {
//                                        datasource.deleteServiceTable();
//                                        datasource.deleteProductTable();
//                                        datasource.deleteServiceTableAR();
//                                        datasource.deleteProductTableAR();
//                                    }
//
//                                    getDataFirstTimeEn();
//                                } else {
//                                    serviceList = datasource.getAllServiceList();
//
//                                    if (serviceList.size() > 0)
//                                        launchActivity(1000);
//                                    else
//                                        callSnackbar("Data is missing");
//
//                                }
//
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                    }
//
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//    }
//
//    private void getDataFirstTimeEn() {
//
//        String url = ConfigureURLS.URL_catalogues_en_;
//
//        System.out.println("URL======= " + url);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println("GET CATALOGUES === " + response);
//                        try {
//                            boolean mIsSuccess = Parser.getCatalogue(response.toString());
//                            if (mIsSuccess) {
//                                if (Parser.getCatalogueList().size() != 0)
//                                    DataBaseCreation(Parser.getCatalogueList(), "en");
//                                getDataFirstTimeAR();
//                            } else {
//                                callSnackbar("Failed to get data from server");
//                                mAppManager.saveVersionCode(Long.toString(0));
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener()
//
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                    }
//
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//
//    }
//
//    private void getDataFirstTimeAR() {
//
//        String url = ConfigureURLS.URL_catalogues_ar;
//
//
//        System.out.println("URL======= " + url);
//
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
//                        System.out.println("GET CATALOGUES === " + response);
//                        try {
//                            boolean mIsSuccess = Parser.getCatalogue(response.toString());
//                            if (mIsSuccess) {
//                                if (Parser.getCatalogueList().size() != 0)
//                                    DataBaseCreation(Parser.getCatalogueList(), "ar");
//                                serviceList = datasource.getAllServiceList();
//
//                                if (serviceList.size() > 0)
//                                    launchActivity(0);
//                                else
//                                    callSnackbar("Data is missing");
//
//                            } else {
//                                callSnackbar("Failed to get data from server");
//                                mAppManager.saveVersionCode(Long.toString(0));
//                            }
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener()
//
//                {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                    }
//
//                });
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//
//
//    }
//
//
//    private void DataBaseCreation(ArrayList<Service> catalogueList, String lang) {
//
//        ArrayList<Service> catalogueLis = new ArrayList<>();
//        catalogueLis = catalogueList;
//
//        for (int i = 0; i < catalogueLis.size(); i++) {
//
//            Service service = new Service();
//            service = catalogueLis.get(i);
//            if (lang.equals("en")) {
//                datasource.createService(service.getService_id(), service.getTitle(), service.getDescription(), service.getBenefits(),
//                        service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
//                        service.getBefore_after(), service.getTools());
//                for (int j = 0; j < service.getProducts().size(); j++) {
//                    Products products = new Products();
//                    products = service.getProducts().get(j);
//                    datasource.createProduct(products.getProduct_id(), products.getService_id(), products.getProduct_title(),
//                            products.getMain_image(), products.getDescription(), products.getModel());
//                }
//            } else {
//                datasource.createServiceAR(service.getService_id(), service.getTitle(), service.getDescription(), service.getBenefits(),
//                        service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
//                        service.getBefore_after(), service.getTools());
//                for (int j = 0; j < service.getProducts().size(); j++) {
//                    Products products = new Products();
//                    products = service.getProducts().get(j);
//                    datasource.createProductAR(products.getProduct_id(), products.getService_id(), products.getProduct_title(),
//                            products.getMain_image(), products.getDescription(), products.getModel());
//                }
//
//
//            }
//        }
//
//
//    }
//
//
//    private void launchActivity(int timeout) {
//
//        new Handler().postDelayed(new Runnable() {
//
//            @Override
//            public void run() {
//                datasource.close();
//                Intent in = new Intent(SplashActivity1.this, DashbordActivity.class);
//                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
//                startActivity(in);
//                finish();
//            }
//        }, timeout);
//    }
//
//    private void callSnackbar(String message) {
//
//        Snackbar snackbar = Snackbar.make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
//        snackbar.getView().setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
//        View view = snackbar.getView();
//        TextView tv = (TextView) view.findViewById(android.support.design.R.id.snackbar_text);
//        tv.setTextColor(Color.WHITE);
//        snackbar.show();
//    }
}



