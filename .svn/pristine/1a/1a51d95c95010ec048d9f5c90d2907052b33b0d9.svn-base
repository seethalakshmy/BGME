package com.bgmiddleeast.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.bgmiddleeast.Adapter.ServiceCheckdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Service;
import com.bgmiddleeast.database.CommentsDataSource;

import java.util.ArrayList;

public class TrainingActivity1 extends AppCompatActivity {

    Spinner mCompanyName,mCountry,mRole,mLang;
    private String[] Spin_company_name = {"Company name", "TCS", "Seeroo", "Furniture", "Flat"};
    private String[] Spin_country = {"Company name", "TCS", "Seeroo", "Furniture", "Flat"};
    private String[] Spin_role = {"Company name", "TCS", "Seeroo", "Furniture", "Flat"};
    private String[] Spin_lang = {"Type", "English", "Arabic"};
    RecyclerView mServiceList;
    ServiceCheckdapter adapter;
    Model model=new Model();
    LinearLayout mLayoutSpinner;
    RelativeLayout mForm,mService;
    TextView mOK;
    ImageButton mSubmit;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.plain_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        ImageButton back = (ImageButton) toolbar.findViewById(R.id.left_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        mCompanyName = (Spinner) findViewById(R.id.spn_company);
//        mCountry = (Spinner) findViewById(R.id.spn_country);
//        mRole = (Spinner) findViewById(R.id.spn_role);
        mLang = (Spinner) findViewById(R.id.spn_lang);

        mServiceList = (RecyclerView) findViewById(R.id.main_recycler);
        mLayoutSpinner=(LinearLayout)findViewById(R.id.lin_spinner);
        mForm=(RelativeLayout)findViewById(R.id.lin_form) ;
        mOK=(TextView) findViewById(R.id.txt_ok);
        mService=(RelativeLayout) findViewById(R.id.service);
        mSubmit=(ImageButton) findViewById(R.id.bt_Submit);

//        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, Spin_company_name);
//        adapter1.setDropDownViewResource(R.layout.layout_drop_list);
//        mCompanyName.setPrompt("Company name");
//        mCompanyName.setAdapter(adapter1);
//        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, Spin_country);
//        adapter2.setDropDownViewResource(R.layout.layout_drop_list);
//        mCountry.setPrompt("Country");
//        mCountry.setAdapter(adapter2);
//        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, Spin_role);
//        adapter3.setDropDownViewResource(R.layout.layout_drop_list);
//        mRole.setPrompt("Role");
//        mRole.setAdapter(adapter3);
        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, R.layout.book_layout_drop_title, Spin_lang);
        adapter4.setDropDownViewResource(R.layout.layout_drop_list);
        mLang.setPrompt("Laguage");
        mLang.setAdapter(adapter4);
//
//        if (mLayoutSpinner.getVisibility() == View.VISIBLE) {
//            mForm.setVisibility(View.INVISIBLE);
//        } else {
//            mForm.setVisibility(View.VISIBLE);
//        }
        mOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLayoutSpinner.setVisibility(View.INVISIBLE);
                mForm.setVisibility(View.VISIBLE);
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
                finish();
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
        }

        adapter.SetOnItemClickListener(new ServiceCheckdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {


            }
        });


    }

}
