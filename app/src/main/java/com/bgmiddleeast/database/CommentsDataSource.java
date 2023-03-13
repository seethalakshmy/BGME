package com.bgmiddleeast.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Service;

import java.util.ArrayList;
import java.util.List;

import static com.bgmiddleeast.database.MySQLiteHelper.TABLE_PRODUCT_LIST;
import static com.bgmiddleeast.database.MySQLiteHelper.TABLE_PRODUCT_LIST_AR;
import static com.bgmiddleeast.database.MySQLiteHelper.TABLE_SERVICES_LIST;


public class CommentsDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;

    private String[] service_columns = {MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_SERVICE_ID,
            MySQLiteHelper.COLUMN_SERVICE_TITLE,
            MySQLiteHelper.COLUMN_SERVICE_ORDER,
            MySQLiteHelper.COLUMN_DESCRIPTION,
            MySQLiteHelper.COLUMN_BENEFITS,
            MySQLiteHelper.COLUMN_SYMPTOMS,
            MySQLiteHelper.COLUMN_RECOMMENDATION,
            MySQLiteHelper.COLUMN_TITLE_IMAGE,
            MySQLiteHelper.COLUMN_ICON,
            MySQLiteHelper.COLUMN_COLOR_CODE,
            MySQLiteHelper.COLUMN_VIDEO_LINK,
            MySQLiteHelper.COLUMN_BEFORE_AFTER,
            MySQLiteHelper.COLUMN_TOOLS,
    };

    private String[] product_columns = {MySQLiteHelper.COLUMN_P_ID,
            MySQLiteHelper.COLUMN_PRODUCT_ID,
            MySQLiteHelper.COLUMN_P_SERVICE_ID,
            MySQLiteHelper.COLUMN_PRODUCT_ORDER,
            MySQLiteHelper.COLUMN_P_TITLE,
            MySQLiteHelper.COLUMN_P_IMAGE,
            MySQLiteHelper.COLUMN_P_DESCRIPTION,
            MySQLiteHelper.COLUMN_P_MODEL_NO,

    };


    public CommentsDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

//create table en

    public void createService(String service_id, String title, String order, String description, String benefits,String symptoms,
                              String recommendations, String image, String icon, String color, String video_link, String before_after, String tools) {

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_SERVICE_ID, service_id);
        values.put(MySQLiteHelper.COLUMN_SERVICE_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_SERVICE_ORDER, order);
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_BENEFITS, benefits);
        values.put(MySQLiteHelper.COLUMN_SYMPTOMS, symptoms);
        values.put(MySQLiteHelper.COLUMN_RECOMMENDATION, recommendations);
        values.put(MySQLiteHelper.COLUMN_TITLE_IMAGE, image);
        values.put(MySQLiteHelper.COLUMN_ICON, icon);
        values.put(MySQLiteHelper.COLUMN_COLOR_CODE, color);
        values.put(MySQLiteHelper.COLUMN_VIDEO_LINK, video_link);
        values.put(MySQLiteHelper.COLUMN_BEFORE_AFTER, before_after);
        values.put(MySQLiteHelper.COLUMN_TOOLS, tools);


        long insertId = database.insert(TABLE_SERVICES_LIST, null, values);

        Cursor cursor = database.query(TABLE_SERVICES_LIST,
                service_columns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }


    public void createProduct(String product_id, String service_id,String product_order, String product_title,
                              String main_image, String description, String model) {

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_PRODUCT_ID, product_id);
        values.put(MySQLiteHelper.COLUMN_P_SERVICE_ID, service_id);
        values.put(MySQLiteHelper.COLUMN_PRODUCT_ORDER, product_order);
        values.put(MySQLiteHelper.COLUMN_P_TITLE, product_title);
        values.put(MySQLiteHelper.COLUMN_P_IMAGE, main_image);
        values.put(MySQLiteHelper.COLUMN_P_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_P_MODEL_NO, model);


        long insertId = database.insert(MySQLiteHelper.TABLE_PRODUCT_LIST, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCT_LIST,
                product_columns, MySQLiteHelper.COLUMN_P_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

//    Create table ar


    public void createServiceAR(String service_id, String title, String order, String description, String benefits,String symptoms,
                                String recommendations, String image, String icon, String color, String video_link, String before_after, String tools) {

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_SERVICE_ID, service_id);
        values.put(MySQLiteHelper.COLUMN_SERVICE_TITLE, title);
        values.put(MySQLiteHelper.COLUMN_SERVICE_ORDER, order);
        values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_BENEFITS, benefits);
        values.put(MySQLiteHelper.COLUMN_SYMPTOMS, symptoms);
        values.put(MySQLiteHelper.COLUMN_RECOMMENDATION, recommendations);
        values.put(MySQLiteHelper.COLUMN_TITLE_IMAGE, image);
        values.put(MySQLiteHelper.COLUMN_ICON, icon);
        values.put(MySQLiteHelper.COLUMN_COLOR_CODE, color);
        values.put(MySQLiteHelper.COLUMN_VIDEO_LINK, video_link);
        values.put(MySQLiteHelper.COLUMN_BEFORE_AFTER, before_after);
        values.put(MySQLiteHelper.COLUMN_TOOLS, tools);


        long insertId = database.insert(MySQLiteHelper.TABLE_SERVICES_LIST_AR, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVICES_LIST_AR,
                service_columns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }

    public void createProductAR(String product_id, String service_id,String product_order, String product_title,
                                String main_image, String description, String model) {

        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_PRODUCT_ID, product_id);
        values.put(MySQLiteHelper.COLUMN_P_SERVICE_ID, service_id);
        values.put(MySQLiteHelper.COLUMN_PRODUCT_ORDER, product_order);
        values.put(MySQLiteHelper.COLUMN_P_TITLE, product_title);
        values.put(MySQLiteHelper.COLUMN_P_IMAGE, main_image);
        values.put(MySQLiteHelper.COLUMN_P_DESCRIPTION, description);
        values.put(MySQLiteHelper.COLUMN_P_MODEL_NO, model);


        long insertId = database.insert(MySQLiteHelper.TABLE_PRODUCT_LIST_AR, null, values);

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCT_LIST_AR,
                product_columns, MySQLiteHelper.COLUMN_P_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
    }


    //English
    public void deleteService(String id) {
        System.out.println("Comment deleted with id: " + id);
        database.delete(TABLE_SERVICES_LIST, MySQLiteHelper.COLUMN_SERVICE_ID
                + " = " + id, null);
    }

    public void deleteProduct(String id) {
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PRODUCT_LIST, MySQLiteHelper.COLUMN_PRODUCT_ID
                + " = " + id, null);
    }
//Arabic

    public void deleteServiceAR(String id) {
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_SERVICES_LIST_AR, MySQLiteHelper.COLUMN_SERVICE_ID
                + " = " + id, null);
    }

    public void deleteProductAR(String id) {
        System.out.println("Comment deleted with id: " + id);
        database.delete(MySQLiteHelper.TABLE_PRODUCT_LIST_AR, MySQLiteHelper.COLUMN_PRODUCT_ID
                + " = " + id, null);
    }

    //    English
    public void deleteServiceTable() {

        database.execSQL("delete from " + TABLE_SERVICES_LIST);
    }

    public void deleteProductTable() {

        database.execSQL("delete from " + MySQLiteHelper.TABLE_PRODUCT_LIST);

        /*Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCT_LIST,
                service_columns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        cursor.close();
        Log.e("update column", "update column");*/
    }

    //    Arabic
    public void deleteServiceTableAR() {

        database.execSQL("delete from " + MySQLiteHelper.TABLE_SERVICES_LIST_AR);
    }

    public void deleteProductTableAR() {

        database.execSQL("delete from " + MySQLiteHelper.TABLE_PRODUCT_LIST_AR);
    }


    //    update service en
    public void update_service(String en, String service_id, String title, String order, String description,
                               String benefits,String symptoms,String recommendations, String image, String icon,
                               String color, String video_link, String before_after, String tools) {
        try {
            ContentValues values = new ContentValues();
            values.put(MySQLiteHelper.COLUMN_SERVICE_ID, service_id);
            values.put(MySQLiteHelper.COLUMN_SERVICE_TITLE, title);
            values.put(MySQLiteHelper.COLUMN_SERVICE_ORDER, order);
            values.put(MySQLiteHelper.COLUMN_DESCRIPTION, description);
            values.put(MySQLiteHelper.COLUMN_BENEFITS, benefits);
            values.put(MySQLiteHelper.COLUMN_SYMPTOMS, symptoms);
            values.put(MySQLiteHelper.COLUMN_RECOMMENDATION, recommendations);
            values.put(MySQLiteHelper.COLUMN_TITLE_IMAGE, image);
            values.put(MySQLiteHelper.COLUMN_ICON, icon);
            values.put(MySQLiteHelper.COLUMN_COLOR_CODE, color);
            values.put(MySQLiteHelper.COLUMN_VIDEO_LINK, video_link);
            values.put(MySQLiteHelper.COLUMN_BEFORE_AFTER, before_after);
            values.put(MySQLiteHelper.COLUMN_TOOLS, tools);

            if (en.equals("en")) {
                int insertId = database.update(MySQLiteHelper.TABLE_SERVICES_LIST, values, MySQLiteHelper.COLUMN_SERVICE_ID + "='" + service_id + "'", null);

                Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVICES_LIST,
                        service_columns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
                cursor.moveToFirst();
                cursor.close();
                Log.e("update column", "update column");
            } else {
                int insertId = database.update(MySQLiteHelper.TABLE_SERVICES_LIST_AR, values, MySQLiteHelper.COLUMN_SERVICE_ID + "='" + service_id + "'", null);

                Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVICES_LIST_AR,
                        service_columns, MySQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null, null);
                cursor.moveToFirst();
                cursor.close();
                Log.e("update column", "update column");
            }


        } catch (Exception e) {
            e.printStackTrace();

        }

    }


    // EN
    public List<Service> getAllServiceList() {

        List<Service> serviceList = new ArrayList<Service>();
        Cursor cursor = database.query(TABLE_SERVICES_LIST,
                service_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Service ser = cursorToComment(cursor);
            serviceList.add(ser);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return serviceList;
    }

    public List<Products> getAllProductList() {
        List<Products> productList = new ArrayList<Products>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCT_LIST,
                product_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Products product = cursorToCommentProduct(cursor);
            productList.add(product);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return productList;
    }
//Arabic

    public List<Service> getAllServiceListAR() {

        List<Service> serviceList = new ArrayList<Service>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVICES_LIST_AR,
                service_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Service ser = cursorToComment(cursor);
            serviceList.add(ser);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return serviceList;
    }

    public List<Products> getAllProductListAR() {
        List<Products> productList = new ArrayList<Products>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCT_LIST_AR,
                product_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Products product = cursorToCommentProduct(cursor);
            productList.add(product);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return productList;
    }


    public List<Service> getServiceByID(String id) {
        List<Service> comments = new ArrayList<Service>();

        Cursor cursor = database.query(TABLE_SERVICES_LIST,
                service_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Service serv = cursorToComment(cursor);
            String itemname = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_SERVICE_ID));
            if (itemname.equals(id))
                comments.add(serv);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public List<Products> getProductsByID(String id) {
        List<Products> comments = new ArrayList<Products>();

        Cursor cursor = database.query(TABLE_PRODUCT_LIST,
                product_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Products serv = cursorToCommentProduct(cursor);
            String itemname = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_PRODUCT_ID));
            if (itemname.equals(id))
                comments.add(serv);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }


    //Arabic

    public List<Service> getServiceByIDAR(String id) {
        List<Service> comments = new ArrayList<Service>();

        Cursor cursor = database.query(MySQLiteHelper.TABLE_SERVICES_LIST_AR,
                service_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Service serv = cursorToComment(cursor);
            String itemname = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_SERVICE_ID));
            if (itemname.equals(id))
                comments.add(serv);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }

    public List<Products> getProductsByIDAR(String id) {
        List<Products> comments = new ArrayList<Products>();

        Cursor cursor = database.query(TABLE_PRODUCT_LIST_AR,
                product_columns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Products serv = cursorToCommentProduct(cursor);
            String itemname = cursor.getString(cursor.getColumnIndex(MySQLiteHelper.COLUMN_PRODUCT_ID));
            if (itemname.equals(id))
                comments.add(serv);
            cursor.moveToNext();
        }
        // make sure to close the cursor
        cursor.close();
        return comments;
    }


    public boolean hasValuesEn() {

        String count = "SELECT count(*) FROM " + TABLE_SERVICES_LIST;
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0)
            return true;
        else return false;
    }

    public boolean hasValuesAr() {

        String count = "SELECT count(*) FROM " + MySQLiteHelper.TABLE_SERVICES_LIST_AR;
        Cursor mcursor = database.rawQuery(count, null);
        mcursor.moveToFirst();
        int icount = mcursor.getInt(0);
        if (icount > 0)
            return true;
        else return false;
    }

    private Service cursorToComment(Cursor cursor) {
        Service service = new Service();
//	    comment.setId(cursor.getLong(0));
        service.setService_id(cursor.getString(1));
        service.setTitle(cursor.getString(2));
        service.setService_order(cursor.getString(3));
        service.setDescription(cursor.getString(4));
        service.setBenefits(cursor.getString(5));
        service.setSymptoms(cursor.getString(6));
        service.setRecommendations(cursor.getString(7));
        service.setTitle_image(cursor.getString(8));
        service.setIcon_image(cursor.getString(9));
        service.setColour_code(cursor.getString(10));
        service.setVideo_links(cursor.getString(11));
        service.setBefore_after(cursor.getString(12));
        service.setTools(cursor.getString(13));
        return service;
    }

    private Products cursorToCommentProduct(Cursor cursor) {
        Products products = new Products();
//	    comment.setId(cursor.getLong(0));
        products.setProduct_id(cursor.getString(1));
        products.setService_id(cursor.getString(2));
        products.setProduct_order(cursor.getString(3));
        products.setProduct_title(cursor.getString(4));
        products.setMain_image(cursor.getString(5));
        products.setDescription(cursor.getString(6));
        products.setModel(cursor.getString(7));

        return products;
    }


}
