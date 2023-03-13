package com.bgmiddleeast.Model;

import android.app.Application;
import android.content.Context;

import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Service;
import com.bgmiddleeast.Utilities.Tools;

import java.util.ArrayList;

/**
 * Created by seetha on 4/25/2017.
 */

public class Model extends Application {

    public static  String Language ="";
    public static String ServiceId="";
    public  static  String ProductId="";
    public  static  String ToolId="";
    public static int ServicePosition=0;
    public static String ThemeColor="#FFD30E";
    public static String social="fb";
    public static String Country_code="";
    public static Boolean isFromRegistration=false;

    public static  ArrayList<Service> ServiceList=new ArrayList<>();
    public static ArrayList<Products> ProductList=new ArrayList<>();
    public static ArrayList<Tools> ToolList=new ArrayList<>();

    public static Boolean isFirstDash=true;
    public static String DocURL="";


public static String  videolink="";
    public static String videoTitle="";
    public void onCreate() {
        super.onCreate();

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);

    }
}
