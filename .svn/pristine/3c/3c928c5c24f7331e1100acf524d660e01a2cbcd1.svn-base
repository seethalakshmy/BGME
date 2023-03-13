package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bgmiddleeast.Adapter.ServiceListAdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.AppManager;
import com.bgmiddleeast.Utilities.ConfigureURLS;
import com.bgmiddleeast.Utilities.ConnectionDetector;
import com.bgmiddleeast.Utilities.MyRecyclerScroll;
import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Service;
import com.bgmiddleeast.database.CommentsDataSource;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.bgmiddleeast.ParserModel.Parser.getUpdateCatalogueList;

public class DashbordActivity extends AppCompatActivity {
    private RecyclerView mServiceList;
    private ServiceListAdapter adapter;
    Model model;
    private String language;

    FloatingActionButton fab;
    int fabMargin;
    AppManager mAppManager;

    ImageView menuLeft, menuRight, mSync;
    CommentsDataSource datasource;
    static boolean active = false;
    private int mSpinSelection;
    TextView mLangEng, mLangArabic;
    LinearLayout mProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mAppManager = AppManager.getInstance(this);
        language = mAppManager.getSystemLanguage();

        if (mAppManager.getSystemLanguage().equals("en"))
            setContentView(R.layout.activity_dashbord);
        else
            setContentView(R.layout.activity_dashbord_ar);

        active = true;

        if (mAppManager.getSystemLanguage().equals("en")) {

            mSpinSelection = 0;

        } else {

            mSpinSelection = 1;

        }


        Toolbar mToolbar = (Toolbar) findViewById(R.id.htab_toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        ImageButton back = (ImageButton) mToolbar.findViewById(R.id.left_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });

        mProgress = (LinearLayout) findViewById(R.id.loader);


        menuLeft = (ImageView) mToolbar.findViewById(R.id.menuLeft);
        menuRight = (ImageView) mToolbar.findViewById(R.id.menuRight);
        mSync = (ImageView) mToolbar.findViewById(R.id.sync);

        mLangEng = (TextView) mToolbar.findViewById(R.id.im_Enlish);
        mLangArabic = (TextView) mToolbar.findViewById(R.id.im_Arabic);


        mLangEng.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinSelection = 0;
                mAppManager.saveSystemLanguage("en");
                if (!model.Language.equals(mAppManager.getSystemLanguage()))
                    restart();
            }
        });
        mLangArabic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSpinSelection = 1;
                mAppManager.saveSystemLanguage("ar");

                if (!model.Language.equals(mAppManager.getSystemLanguage()))
                    restart();

            }
        });


        mSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                syncServices();
            }


        });

        model = new Model();
        model.isFirstDash = true;

        CommentsDataSource datasource = new CommentsDataSource(this);
        datasource.open();
        if (language.equals("en")) {
            model.ServiceList = (ArrayList<Service>) datasource.getAllServiceList();
            model.ProductList = (ArrayList<Products>) datasource.getAllProductList();
        } else {
            model.ServiceList = (ArrayList<Service>) datasource.getAllServiceListAR();
            model.ProductList = (ArrayList<Products>) datasource.getAllProductListAR();

        }
        if (model.ServiceList.size() == 0) {
            if (language.equals("en")) {
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
        if (model.ServiceList.size() != 0) {
            init();
//            setUpMenu(language);

        }

    }


    void restart() {

        model.Language = mAppManager.getSystemLanguage();
        Intent intent = getIntent();
        finish();
//                overridePendingTransition(R.anim.slide_to_left, R.anim.slide_to_right);
        startActivity(intent);


    }


    private void init() {

        mServiceList = (RecyclerView) findViewById(R.id.main_recycler);
        mServiceList.setLayoutManager(new LinearLayoutManager(this));
        mServiceList.setItemAnimator(new DefaultItemAnimator());
        mServiceList.setHasFixedSize(true);
        fabMargin = getResources().getDimensionPixelSize(R.dimen.fab);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mServiceList.scrollToPosition(model.ServiceList.size() - 1);
                fab.animate().translationY(fab.getHeight() + fabMargin).setInterpolator(new AccelerateInterpolator(2)).start();

            }
        });
        if (adapter == null) {
            adapter = new ServiceListAdapter(this, model.ServiceList);
            mServiceList.setAdapter(adapter);
        }
        adapter.SetOnItemClickListener(new ServiceListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                model.ServiceId = model.ServiceList.get(position).getService_id();
                model.ServicePosition = position;
                model.ThemeColor = "#" + model.ServiceList.get(position).getColour_code();
                Intent in = new Intent(DashbordActivity.this, ServiceActivity.class);
                startActivity(in);


            }
        });
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        mServiceList.measure(
                View.MeasureSpec.makeMeasureSpec(displayMetrics.widthPixels, View.MeasureSpec.EXACTLY),
                View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int x = mServiceList.getMeasuredHeight();
        if (x <= height)
            fab.setVisibility(View.GONE);
        else
            fab.setVisibility(View.VISIBLE);
        mServiceList.addOnScrollListener(new MyRecyclerScroll() {
            @Override
            public void show() {
                fab.animate().translationY(0).setInterpolator(new DecelerateInterpolator(2)).start();
            }

            @Override
            public void hide() {
                fab.animate().translationY(fab.getHeight() + fabMargin).setInterpolator(new AccelerateInterpolator(2)).start();
            }
        });

    }

    private void syncServices() {
        ConnectionDetector mConDect = new ConnectionDetector(this);
        datasource = new CommentsDataSource(this);
        datasource.open();
        if (mConDect.isConnectingToInternet()) {
            mProgress.setVisibility(View.VISIBLE);
            getVersionCode();

        } else {
            Toast.makeText(getApplicationContext(), getString(R.string.network_message), Toast.LENGTH_SHORT).show();
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
                                Log.i("data",""+mAppManager.getVersionCode());
                                long version_code = mObject.getLong("version");
                                if (version_code > Long.parseLong(mAppManager.getVersionCode())) {

                                    System.out.println("call services api");


                                    getUpdateDataFirstTimeEn(version_code);
                                } else {
                                    mProgress.setVisibility(View.GONE);
                                    if (mAppManager.getSystemLanguage().equals("en"))
                                        Toast.makeText(getApplicationContext(), getString(R.string.updated), Toast.LENGTH_SHORT).show();

                                    else {
                                        Toast.makeText(getApplicationContext(), getString(R.string.updated_ar), Toast.LENGTH_SHORT).show();

                                    }
                                }


                            } else {
                                mProgress.setVisibility(View.GONE);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            mProgress.setVisibility(View.GONE);
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        mProgress.setVisibility(View.GONE);
                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    long version;

    private void getUpdateDataFirstTimeEn(long version_code) {
        version = version_code;
        String url = ConfigureURLS.URL_catalogue_en_version + mAppManager.getVersionCode();

        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET CATALOGUES === " + response);
                        try {
                            boolean mIsSuccess = Parser.getUpdateCatalogue(response.toString(), "en");
                            if (mIsSuccess) {
                                if (Parser.getUpdateCatalogueList("en").size() != 0) {

                                    ArrayList<Service> serviceLists = Parser.getUpdateCatalogueList("en");
                                    for (int i = 0; i < serviceLists.size(); i++) {
                                        Service service = new Service();
                                        service = serviceLists.get(i);
                                        List<Service> temp = datasource.getServiceByID(serviceLists.get(i).getService_id());
                                        if (temp.size() == 0) {

                                            if (!serviceLists.get(i).getDelete_status().equals("True")) {
                                                datasource.createService(service.getService_id(), service.getTitle(), service.getService_order(), service.getDescription(), service.getBenefits(),
                                                        service.getSymptoms(),service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
                                                        service.getBefore_after(), service.getTools());

                                                for (int j = 0; j < service.getProducts().size(); j++) {
                                                    Products products = new Products();
                                                    products = service.getProducts().get(j);
                                                    datasource.createProduct(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                                                            products.getMain_image(), products.getDescription(), products.getModel());
                                                }
                                            }

                                        } else {
                                            if (serviceLists.get(i).getDelete_status().equals("True")) {

                                                datasource.deleteService(serviceLists.get(i).getService_id());
                                            } else {

                                                datasource.update_service("en", service.getService_id(), service.getTitle(), service.getService_order(), service.getDescription(), service.getBenefits(),
                                                        service.getSymptoms(), service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
                                                        service.getBefore_after(), service.getTools());

                                                if (service.getProducts().size() != 0) {
                                                    for (int j = 0; j < service.getProducts().size(); j++) {
                                                        Products products = new Products();
                                                        products = service.getProducts().get(j);
                                                        List<Products> tempp=null;
                                                         tempp = datasource.getProductsByID(products.getProduct_id());
                                                        if (tempp != null && tempp.size() != 0) {
                                                            if (products.getDelete_status().equals("True"))
                                                                datasource.deleteProduct(products.getProduct_id());
                                                            else {
                                                                datasource.deleteProduct(products.getProduct_id());
                                                                datasource.createProduct(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                                                                        products.getMain_image(), products.getDescription(), products.getModel());
                                                            }
                                                        } else {
                                                            datasource.createProduct(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                                                                    products.getMain_image(), products.getDescription(), products.getModel());
                                                        }
                                                    }
                                                }
                                            }
                                        }

                                    }
                                }
                                getUpdateDataFirstTimeAr(version);
                            } else {

                                if (mAppManager.getSystemLanguage().equals("en"))
                                    Toast.makeText(getApplicationContext(), getString(R.string.no_update), Toast.LENGTH_SHORT).show();

                                else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.no_update_ar), Toast.LENGTH_SHORT).show();

                                }
                                mProgress.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mProgress.setVisibility(View.GONE);
                        }

                    }
                },
                new Response.ErrorListener()

                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        mProgress.setVisibility(View.GONE);

                    }

                });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }


    private void getUpdateDataFirstTimeAr(final long version_code) {

        String url = ConfigureURLS.URL_catalogue_ar_version + mAppManager.getVersionCode();


        System.out.println("URL======= " + url);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        System.out.println("GET CATALOGUES === " + response);
                        try {
                            boolean mIsSuccess = Parser.getUpdateCatalogue(response.toString(), "ar");
                            if (mIsSuccess) {
                                if (getUpdateCatalogueList("ar").size() != 0) {
                                    ArrayList<Service> serviceLists = getUpdateCatalogueList("ar");
                                    for (int i = 0; i < serviceLists.size(); i++) {
                                        Service service = new Service();
                                        service = serviceLists.get(i);

                                        List<Service> temp = datasource.getServiceByIDAR(serviceLists.get(i).getService_id());
                                        if (temp.size() == 0) {
                                            if (!serviceLists.get(i).getDelete_status().equals("True")) {
                                                datasource.createServiceAR(service.getService_id(), service.getTitle(), service.getService_order(), service.getDescription(), service.getBenefits(),
                                                        service.getSymptoms(),service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
                                                        service.getBefore_after(), service.getTools());

                                                for (int j = 0; j < service.getProducts().size(); j++) {
                                                    Products products = new Products();
                                                    products = service.getProducts().get(j);
                                                    datasource.createProductAR(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                                                            products.getMain_image(), products.getDescription(), products.getModel());
                                                }
                                            }
                                        } else {

                                            if (serviceLists.get(i).getDelete_status().equals("True")) {

                                                datasource.deleteServiceAR(serviceLists.get(i).getService_id());
                                            } else {

                                                datasource.update_service("ar", service.getService_id(), service.getTitle(), service.getService_order(), service.getDescription(), service.getBenefits(),
                                                        service.getSymptoms(), service.getRecommendations(), service.getTitle_image(), service.getIcon_image(), service.getColour_code(), service.getVideo_links(),
                                                        service.getBefore_after(), service.getTools());


                                                if (service.getProducts().size() != 0) {
                                                    for (int j = 0; j < service.getProducts().size(); j++) {
                                                        Products products = new Products();
                                                        products = service.getProducts().get(j);
                                                        List<Products> tempp = null;
                                                        tempp = datasource.getProductsByIDAR(products.getProduct_id());
                                                        if (tempp != null && tempp.size() != 0) {
                                                            if (products.getDelete_status().equals("True"))
                                                                datasource.deleteProductAR(products.getProduct_id());
                                                            else {
                                                                datasource.deleteProductAR(products.getProduct_id());
                                                                datasource.createProductAR(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                                                                        products.getMain_image(), products.getDescription(), products.getModel());
                                                            }
                                                        } else {
                                                            datasource.createProductAR(products.getProduct_id(), products.getService_id(), products.getProduct_order(), products.getProduct_title(),
                                                                    products.getMain_image(), products.getDescription(), products.getModel());
                                                        }
                                                    }
                                                }
                                            }

                                        }


                                    }

                                    mAppManager.saveVersionCode("" + version_code);
                                    if (!isFinishing()) {
                                        mProgress.setVisibility(View.GONE);
                                        Intent intent = getIntent();
                                        finish();
                                        startActivity(intent);
                                    } else {

                                    }
                                }

                            } else {
                                if (mAppManager.getSystemLanguage().equals("en"))
                                    Toast.makeText(getApplicationContext(), getString(R.string.no_update), Toast.LENGTH_SHORT).show();

                                else {
                                    Toast.makeText(getApplicationContext(), getString(R.string.no_update_ar), Toast.LENGTH_SHORT).show();

                                }
                                mProgress.setVisibility(View.GONE);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            mProgress.setVisibility(View.GONE);
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


    @Override
    public void onBackPressed() {

        startActivity(new Intent(DashbordActivity.this, NewDashboard.class));
        finish();



    }

    @Override
    public void onStart() {
        super.onStart();
        active = true;
    }

    @Override
    public void onStop() {
        super.onStop();
        active = false;
    }
}
