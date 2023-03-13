package com.bgmiddleeast.Activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bgmiddleeast.Model.Model;
import com.bgmiddleeast.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import static com.bgmiddleeast.R.id.map;

/**
 * Created by Sreenadh on 27-Apr-17.
 */

public class ContactActivity extends AppCompatActivity implements OnMapReadyCallback {
    double lat = 25.179699;
    double lon = 55.271884;

    TextView mTxtEmail, mHeader;
    ImageView mImgCall;
    Model model;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        model = new Model();
        if (model.Language.equals( "en" ))
            setContentView( R.layout.contact );
        else
            setContentView( R.layout.contact_ar );

        Toolbar mToolbar = (Toolbar) findViewById( R.id.plain_toolbar );
        setSupportActionBar( mToolbar );
        getSupportActionBar().setDisplayHomeAsUpEnabled( false );
        ImageButton back = (ImageButton) mToolbar.findViewById( R.id.left_back );
        mToolbar.setTitle( "" );


        mHeader = (TextView) mToolbar.findViewById( R.id.txt_header_plain );

        if (Model.Language.equals( "en" ))
            mHeader.setText( getString( R.string.contact ) );
        else
            mHeader.setText( getString( R.string.contact_ar ) );

        mTxtEmail = (TextView) findViewById( R.id.txt_email );
        mImgCall = (ImageView) findViewById( R.id.img_call );
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById( map );
        mapFragment.getMapAsync( this );

        mTxtEmail.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMail();
            }
        } );

        mImgCall.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission( ContactActivity.this, Manifest.permission.CALL_PHONE ) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions( ContactActivity.this, new String[]{Manifest.permission.CALL_PHONE}, 111 );
                } else
                    call();
            }
        } );
        back.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                startActivity( new Intent( ContactActivity.this, NewDashboard.class ) );
                finish();

            }
        } );

    }

    private void call() {
        Intent in = new Intent( Intent.ACTION_CALL, Uri.parse( "tel:+(971) 4 429 6010" ) );
        try {
            startActivity( in );
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText( getApplicationContext(), "Could not find an activity to place the call.", Toast.LENGTH_SHORT ).show();
        }
    }

    private void sendMail() {
        Intent intent = new Intent( Intent.ACTION_SEND );
        intent.setType( "text/plain" );
        intent.putExtra( Intent.EXTRA_EMAIL, new String[]{"info@bgmiddleeast.com"} );

        startActivity( Intent.createChooser( intent, "" ) );
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker( new MarkerOptions()
                .position( new LatLng( lat, lon ) ) );
        googleMap.animateCamera( CameraUpdateFactory.newLatLngZoom( new LatLng( lat, lon ), 16.0f ) );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    call();
                } else {
                    if (Model.Language.equals( "en" ))
                        Toast.makeText( getApplicationContext(), "Please give permission to make call.", Toast.LENGTH_SHORT ).show();
                    else
                        Toast.makeText( getApplicationContext(), "اعطي التصريح لاجراء مكالمه", Toast.LENGTH_SHORT ).show();

                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    public void onBackPressed() {

        startActivity( new Intent( ContactActivity.this, NewDashboard.class ) );
        finish();

    }
}
