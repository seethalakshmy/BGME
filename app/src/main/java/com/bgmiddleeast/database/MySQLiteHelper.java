package com.bgmiddleeast.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "bgmiddleeast.db";
    private static final int DATABASE_VERSION = 2;

    public static final String TABLE_SERVICES_LIST = "servicelist";
    public static final String TABLE_PRODUCT_LIST = "productlist";

    public static final String TABLE_SERVICES_LIST_AR = "servicelist_ar";
    public static final String TABLE_PRODUCT_LIST_AR = "productlist_ar";


    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_TITLE = "service_title";
    public static final String COLUMN_SERVICE_ORDER = "service_order";
    public static final String COLUMN_DESCRIPTION = "service_description";
    public static final String COLUMN_BENEFITS = "service_benefits";
    public static final String COLUMN_RECOMMENDATION = "service_recomendation";
    public static final String COLUMN_TITLE_IMAGE = "service_image";
    public static final String COLUMN_COLOR_CODE = "service_color_code";
    public static final String COLUMN_VIDEO_LINK = "service_video_link";
    public static final String COLUMN_BEFORE_AFTER = "service_before_after";
    public static final String COLUMN_TOOLS = "service_tools";
    public static final String COLUMN_ICON = "service_icon";
    public static final String COLUMN_SYMPTOMS= "symptoms";


    public static final String COLUMN_P_ID = "p_id";
    public static final String COLUMN_PRODUCT_ID= "p_product_id";
    public static final String COLUMN_P_SERVICE_ID = "p_service_id";
    public static final String COLUMN_PRODUCT_ORDER= "p_product_order";
    public static final String COLUMN_P_TITLE = "p_title";
    public static final String COLUMN_P_IMAGE = "p_image";
    public static final String COLUMN_P_DESCRIPTION = "p_description";
    public static final String COLUMN_P_MODEL_NO = "p_model_no";



    // Database creation sql statement
    private static String DATABASE_CREATE_SERVICE = "create table "
            + TABLE_SERVICES_LIST + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SERVICE_ID
            + " text not null, " + COLUMN_SERVICE_TITLE
            + " text not null, " + COLUMN_SERVICE_ORDER
            + " text not null, " + COLUMN_DESCRIPTION
            + " text not null, " + COLUMN_BENEFITS
            + " text not null, " + COLUMN_SYMPTOMS
            + " text not null, " + COLUMN_RECOMMENDATION
            + " text not null, " + COLUMN_TITLE_IMAGE
            + " text not null, " + COLUMN_ICON
            + " text not null, " + COLUMN_COLOR_CODE
            + " text not null, " + COLUMN_VIDEO_LINK
            + " text not null, " + COLUMN_BEFORE_AFTER
            + " text not null, " + COLUMN_TOOLS
            + " text not null);";

    // Database creation sql statement
    private static String DATABASE_CREATE_PRODUCTS = "create table "
            + TABLE_PRODUCT_LIST + "(" + COLUMN_P_ID
            + " integer primary key autoincrement, " + COLUMN_PRODUCT_ID
            + " text not null, " + COLUMN_P_SERVICE_ID
            + " text not null, " + COLUMN_PRODUCT_ORDER
            + " text not null, " + COLUMN_P_TITLE
            + " text not null, " + COLUMN_P_IMAGE
            + " text not null, " + COLUMN_P_DESCRIPTION
            + " text not null, " + COLUMN_P_MODEL_NO
            + " text not null);";

    // Database creation sql statement
    private static String DATABASE_CREATE_SERVICE_AR = "create table "
            + TABLE_SERVICES_LIST_AR + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_SERVICE_ID
            + " text not null, " + COLUMN_SERVICE_TITLE
            + " text not null, " + COLUMN_SERVICE_ORDER
            + " text not null, " + COLUMN_DESCRIPTION
            + " text not null, " + COLUMN_BENEFITS
            + " text not null, " + COLUMN_SYMPTOMS
            + " text not null, " + COLUMN_RECOMMENDATION
            + " text not null, " + COLUMN_TITLE_IMAGE
            + " text not null, " + COLUMN_ICON
            + " text not null, " + COLUMN_COLOR_CODE
            + " text not null, " + COLUMN_VIDEO_LINK
            + " text not null, " + COLUMN_BEFORE_AFTER
            + " text not null, " + COLUMN_TOOLS
            + " text not null);";



    // Database creation sql statement
    private static String DATABASE_CREATE_PRODUCTS_AR = "create table "
            + TABLE_PRODUCT_LIST_AR + "(" + COLUMN_P_ID
            + " integer primary key autoincrement, " + COLUMN_PRODUCT_ID
            + " text not null, " + COLUMN_P_SERVICE_ID
            + " text not null, " + COLUMN_PRODUCT_ORDER
            + " text not null, " + COLUMN_P_TITLE
            + " text not null, " + COLUMN_P_IMAGE
            + " text not null, " + COLUMN_P_DESCRIPTION
            + " text not null, " + COLUMN_P_MODEL_NO
            + " text not null);";



    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE_SERVICE);
        database.execSQL(DATABASE_CREATE_PRODUCTS);
        database.execSQL(DATABASE_CREATE_SERVICE_AR);
        database.execSQL(DATABASE_CREATE_PRODUCTS_AR);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES_LIST);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_LIST);

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SERVICES_LIST_AR);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCT_LIST_AR);


        onCreate(db);
    }

}