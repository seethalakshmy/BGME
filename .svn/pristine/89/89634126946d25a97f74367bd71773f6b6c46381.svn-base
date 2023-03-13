package com.bgmiddleeast.Activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bgmiddleeast.Adapter.SearchListAdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;

import java.util.ArrayList;

public class SearchCountryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private SearchListAdapter mSearchListAdapter;
    private Toolbar mToolbar;
    private TextView mHeader;
    ArrayList<String> placenameList;
    private EditText mEdtSearch;
    Model model;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        model=new Model();
       if(model.Language.equals("en") ){
            setContentView( R.layout.activity_search_country );
        }else {
           setContentView( R.layout.activity_search_country_ar );
       }
        mEdtSearch = (EditText) findViewById(R.id.edt_search);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        mToolbar = (Toolbar) findViewById(R.id.account_toolbar);
        ImageView mClose = (ImageView) mToolbar.findViewById(R.id.left_back);
        setSupportActionBar(mToolbar);
        model = new Model();

        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(false);

        mClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        if (Parser.getDataCountryList() != null && Parser.getDataCountryList().size() != 0) {
            placenameList = new ArrayList<>();
//            placenameList.add("Country");
            for (int i = 0; i < Parser.getDataCountryList().size(); i++) {
                placenameList.add(Parser.getDataCountryList().get(i).getCountry_name());
            }

        }

        mSearchListAdapter = new SearchListAdapter(placenameList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mSearchListAdapter);

        mSearchListAdapter.SetOnItemClickListener(new SearchListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (model.isFromRegistration) {

                    for (int i = 0; i < Parser.getDataCountryList().size(); i++) {
                        if (Parser.getDataCountryList().get(i).getCountry_name().equals(placenameList.get(position))) {
                            RegisterActivity activity = new RegisterActivity();
                            activity.setCountry(i);
                        }
                    }



                } else {
                    for (int i = 0; i < Parser.getDataCountryList().size(); i++) {
                        if (Parser.getDataCountryList().get(i).getCountry_name().equals(placenameList.get(position))) {
                            ProfileActivity prof = new ProfileActivity();
                            prof.setCountry(i);
                        }
                    }

                }
                finish();
            }
        });


        mEdtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String text = charSequence.toString();
                mSearchListAdapter.filter(text);
            }

            @Override
            public void afterTextChanged(Editable editable) {


            }
        });

    }


}
