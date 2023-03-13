package com.bgmiddleeast.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.bgmiddleeast.Adapter.GridItemAdapter;
import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.ParserModel.Parser;
import com.bgmiddleeast.R;
import com.bgmiddleeast.Utilities.Document;

import java.util.ArrayList;

public class FlyerActivity extends AppCompatActivity {
    private  ArrayList<Document> FlyersList;
    Model model = new Model();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (model.Language.equals("en"))
            setContentView(R.layout.activity_msds);
        else
            setContentView(R.layout.activity_msds_ar);

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
        TextView mHead = (TextView) toolbar.findViewById(R.id.txt_header_plain);

        if (Model.Language.equals( "en" ))
            mHead.setText("Flyer");
        else
            mHead.setText( getString( R.string.flyer_ar ) );
        FlyersList = new ArrayList<>();

        if(Parser.DocumentList.size()!=0) {
            for (int i=0;i<Parser.DocumentList.size();i++){
                if(Parser.DocumentList.get(i).getType().equals("Flyer"))
                    FlyersList.add(Parser.DocumentList.get(i));
            }
            if(FlyersList.size()==0) {
                TextView msg=(TextView)findViewById(R.id.txt_message) ;
                msg.setVisibility( View.VISIBLE );
            }

            RecyclerView recyclerView = (RecyclerView) findViewById(R.id.card_recycler_view);
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));

            GridItemAdapter adapter = new GridItemAdapter(FlyersList, this);
            recyclerView.setAdapter(adapter);

            adapter.SetOnItemClickListener(new GridItemAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    model.DocURL = FlyersList.get(position).getRecord();
                    Intent in = new Intent(FlyerActivity.this, DocViewActivity.class);
                    startActivity(in);
                    //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            });
        }

    }
}
