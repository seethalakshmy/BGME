package com.bgmiddleeast.ParserModel;

import android.util.Log;

import com.bgmiddleeast.Utilities.BeforeAfter;
import com.bgmiddleeast.Utilities.CountryList;
import com.bgmiddleeast.Utilities.Document;
import com.bgmiddleeast.Utilities.Products;
import com.bgmiddleeast.Utilities.Profile;
import com.bgmiddleeast.Utilities.Service;
import com.bgmiddleeast.Utilities.Tools;
import com.bgmiddleeast.Utilities.Type;
import com.bgmiddleeast.Utilities.Videos;
import com.bgmiddleeast.Utilities.notification;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Noble on 8/18/2016.
 */
public class Parser {


// service catalogue

    private static String message = "";
    private static String status = "";
    public static ArrayList<Service> serviceList = new ArrayList<>();
    public static ArrayList<Products> productList = new ArrayList<>();
    public static ArrayList<Service> serviceList_ar = new ArrayList<>();
    public static ArrayList<Service> serviceList_en = new ArrayList<>();
    public static ArrayList<Service> serviceList_update_ar = new ArrayList<>();
    public static ArrayList<Service> serviceList_update_en = new ArrayList<>();
    public static ArrayList<Service> sortTempList = new ArrayList<>();
    public static int val = 0;

    public static boolean getCatalogue(String data, String lan) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            status = dataObject.optString("status");
            message = dataObject.optString("message").toString();
            if (status.equals("TRUE")) {

                if (dataObject.getJSONArray("services") != null) {
                    JSONArray serviceListArray = dataObject.getJSONArray("services");
                    serviceList = new ArrayList<>();
                    for (int i = 0; i < serviceListArray.length(); i++) {
                        JSONObject Json_obj_service = serviceListArray.getJSONObject(i);

                        Service service = new Service();
                        if (val < Integer.parseInt(Json_obj_service.optString("service_order").toString()))
                            val = Integer.parseInt(Json_obj_service.optString("service_order").toString());

                        service.setService_id(Json_obj_service.optString("service_id").toString());
                        service.setTitle(Json_obj_service.optString("title").toString());
                        service.setService_order(Json_obj_service.optString("service_order").toString());
                        service.setDescription(Json_obj_service.optString("description").toString());
                        service.setBenefits(Json_obj_service.optString("benefits").toString());
                        service.setSymptoms(Json_obj_service.optString("symptoms").toString());
                        service.setRecommendations(Json_obj_service.optString("recommendations").toString());
                        service.setTitle_image(Json_obj_service.optString("title_image").toString());
                        service.setColour_code(Json_obj_service.optString("colour_code").toString());
                        service.setIcon_image(Json_obj_service.optString("icon_image").toString());
                        service.setVideo_links("{\"video_links\":" + Json_obj_service.optString("video_links").toString() + "}");
                        service.setBefore_after("{\"before_after\":" + Json_obj_service.optString("before_after").toString() + "}");
                        service.setTools("{\"tools\":" + Json_obj_service.optString("tools").toString() + "}");

                        if (Json_obj_service.getJSONArray("products") != null) {
                            JSONArray productListArray = Json_obj_service.getJSONArray("products");
                            productList = new ArrayList<>();
                            if (productListArray != null && productListArray.length() != 0)
                                for (int j = 0; j < productListArray.length(); j++) {

                                    JSONObject Json_obj_Products = productListArray.getJSONObject(j);
                                    Products products = new Products();
                                   if (!Json_obj_Products.optString("delete_status").equals( "True" )) {
                                       products.setProduct_id( Json_obj_Products.optString( "product_id" ) );
                                       products.setService_id( Json_obj_service.optString( "service_id" ) );
                                       products.setProduct_order( Json_obj_Products.optString( "product_order" ) );
                                       products.setProduct_title( Json_obj_Products.optString( "product_title" ).toString() );
                                       products.setDescription( Json_obj_Products.optString( "description" ).toString() );
                                       products.setModel( Json_obj_Products.optString( "model" ).toString() );
                                       products.setMain_image( Json_obj_Products.optString( "main_image" ).toString() );
                                       productList.add( products );
                                   }

                                }
                            service.setProducts(productList);

                        }

                        serviceList.add(service);
                    }


                    sortTempList = new ArrayList<>();
                    for (int i = 0; i <= val; i++) {

                        int tmp = i;
                        String sortId = String.valueOf(tmp);
                        for (int j = 0; j < serviceList.size(); j++) {
                            if (serviceList.get(j).getService_order().equals(sortId)) {
                                sortTempList.add(serviceList.get(j));
                            }

                        }
                    }
                    if (sortTempList.size() != 0)
                        serviceList.clear();
                    serviceList = sortTempList;
                    if (lan.equals("en"))
                        serviceList_en = serviceList;
                    else
                        serviceList_ar = serviceList;
                    return true;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();

        }
        return false;
    }

    public static ArrayList<Service> getCatalogueList() {
//        Collections.reverse(serviceList);
        return serviceList;
    }

    public static String catalogueErrorMessage() {
        return message;
    }


    //Benefits
    private String Befstatus = "";
    private static ArrayList<BeforeAfter> BeforeAfterList = new ArrayList<>();


    public static ArrayList<BeforeAfter> getBeforeAfter(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                if (dataObject.getJSONArray("before_after") != null) {
                    JSONArray BeforAfterArray = dataObject.getJSONArray("before_after");
                    BeforeAfterList = new ArrayList<>();
                    for (int i = 0; i < BeforAfterArray.length(); i++) {

                        JSONObject Json_obj_ = BeforAfterArray.getJSONObject(i);
                        BeforeAfter beforeAfter = new BeforeAfter();
                        beforeAfter.setCaption(Json_obj_.optString("caption").toString());
                        beforeAfter.setImage(Json_obj_.optString("image").toString());
                        BeforeAfterList.add(beforeAfter);

                    }
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return BeforeAfterList;

        }
        return BeforeAfterList;
    }

    //Tools
    private String ToolStatus = "";
    private static ArrayList<Tools> ToolsList = new ArrayList<>();


    public static ArrayList<Tools> getTools(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                if (dataObject.getJSONArray("tools") != null) {
                    JSONArray BeforAfterArray = dataObject.getJSONArray("tools");
                    ToolsList = new ArrayList<>();
                    for (int i = 0; i < BeforAfterArray.length(); i++) {

                        JSONObject Json_obj_ = BeforAfterArray.getJSONObject(i);
                        Tools tools = new Tools();
                        if (!Json_obj_.optString("delete_status").toString().equals("True")) {
                            tools.setTool_id(Json_obj_.optString("tool_id").toString());
                            tools.setTool_image(Json_obj_.optString("tool_image").toString());
                            tools.setTool_title(Json_obj_.optString("tool_title").toString());
                            tools.setTool_description(Json_obj_.optString("tool_description").toString());
                            tools.setTool_model(Json_obj_.optString("tool_model").toString());


                            ToolsList.add(tools);
                        }

                    }
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return ToolsList;

        }
        return ToolsList;
    }

    //Videos
    private static ArrayList<Videos> VideoList;

    public static ArrayList<Videos> getVideos(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());
            VideoList = new ArrayList<>();
            if (!data.toString().isEmpty()) {
                if (dataObject.getJSONArray("video_links") != null) {
                    JSONArray VideoListArray = dataObject.getJSONArray("video_links");
                    ToolsList = new ArrayList<>();
                    for (int i = 0; i < VideoListArray.length(); i++) {

                        JSONObject Json_obj_ = VideoListArray.getJSONObject(i);
                        Videos video = new Videos();
                        video.setVideo_url(Json_obj_.optString("video_link").toString());
                        video.setVideo_title(Json_obj_.optString("video_title").toString());

                        VideoList.add(video);
                    }
                }
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return VideoList;

        }
        return VideoList;
    }

//    Update

    public static boolean getUpdateCatalogue(String data, String lan) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            status = dataObject.optString("status");
            message = dataObject.optString("message").toString();
            if (status.equals("TRUE")) {
                serviceList_update_ar = new ArrayList<>();
                serviceList_update_en = new ArrayList<>();
                if (dataObject.getJSONArray("services") != null) {
                    JSONArray serviceListArray = dataObject.getJSONArray("services");
                    serviceList = new ArrayList<>();
                    for (int i = 0; i < serviceListArray.length(); i++) {
                        JSONObject Json_obj_service = serviceListArray.getJSONObject(i);

                        Service service = new Service();
                        if (val < Integer.parseInt(Json_obj_service.optString("service_order").toString()))
                            val = Integer.parseInt(Json_obj_service.optString("service_order").toString());

                        service.setService_id(Json_obj_service.optString("service_id").toString());
                        service.setTitle(Json_obj_service.optString("title").toString());
                        service.setDelete_status(Json_obj_service.optString("delete_status").toString());
                        service.setService_order(Json_obj_service.optString("service_order").toString());
                        service.setDescription(Json_obj_service.optString("description").toString());
                        service.setBenefits(Json_obj_service.optString("benefits").toString());
                        service.setSymptoms(Json_obj_service.optString("symptoms").toString());
                        service.setRecommendations(Json_obj_service.optString("recommendations").toString());
                        service.setTitle_image(Json_obj_service.optString("title_image").toString());
                        service.setColour_code(Json_obj_service.optString("colour_code").toString());
                        service.setIcon_image(Json_obj_service.optString("icon_image").toString());
                        service.setVideo_links("{\"video_links\":" + Json_obj_service.optString("video_links").toString() + "}");
                        service.setBefore_after("{\"before_after\":" + Json_obj_service.optString("before_after").toString() + "}");
                        service.setTools("{\"tools\":" + Json_obj_service.optString("tools").toString() + "}");

                        if (Json_obj_service.getJSONArray("products") != null) {
                            JSONArray productListArray = Json_obj_service.getJSONArray("products");
                            productList = new ArrayList<>();
                            if (productListArray != null && productListArray.length() != 0)
                                for (int j = 0; j < productListArray.length(); j++) {

                                    JSONObject Json_obj_Products = productListArray.getJSONObject(j);
                                    Products products = new Products();
                                    if (!Json_obj_Products.optString("delete_status").toString().equals("True")) {
                                        products.setProduct_id( Json_obj_Products.optString( "product_id" ).toString() );
                                        products.setService_id( Json_obj_service.optString( "service_id" ).toString() );
                                        products.setDelete_status( Json_obj_Products.optString( "delete_status" ).toString() );
                                        products.setProduct_order( Json_obj_Products.optString( "product_order" ).toString() );
                                        products.setProduct_title( Json_obj_Products.optString( "product_title" ).toString() );
                                        products.setDescription( Json_obj_Products.optString( "description" ).toString() );
                                        products.setModel( Json_obj_Products.optString( "model" ).toString() );
                                        products.setMain_image( Json_obj_Products.optString( "main_image" ).toString() );
                                        productList.add(products);
                                    }


                                }
                            service.setProducts(productList);

                        }

                        serviceList.add(service);
                    }

                    if (lan.equals("en"))
                        serviceList_update_en = serviceList;
                    else
                        serviceList_update_ar = serviceList;
                    return true;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();

        }
        return false;
    }

    public static ArrayList<Service> getUpdateCatalogueList(String lang) {
        if (lang.equals("en"))
            return serviceList_update_en;
        else
            return serviceList_update_ar;


    }

    //CountryList


    private static ArrayList<CountryList> CountryList;


    public static boolean getCountryList(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                CountryList = new ArrayList<>();
                if (dataObject.getJSONArray("country") != null) {
                    JSONArray CountrArray = dataObject.getJSONArray("country");
                    ToolsList = new ArrayList<>();
                    for (int i = 0; i < CountrArray.length(); i++) {

                        JSONObject Json_obj_ = CountrArray.getJSONObject(i);
                        CountryList countryList = new CountryList();

                        countryList.setCountry_id(Json_obj_.optString("id").toString());
                        countryList.setCountry_name(Json_obj_.optString("name").toString());

                        CountryList.add(countryList);

                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static ArrayList<CountryList> getDataCountryList() {
        return CountryList;
    }


    //Language


    private static ArrayList<Type> LanguageList;


    public static boolean getLanguageList(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                LanguageList = new ArrayList<>();
                if (dataObject.getJSONArray("languages") != null) {
                    JSONArray LangArray = dataObject.getJSONArray("languages");
                    LanguageList = new ArrayList<>();
                    for (int i = 0; i < LangArray.length(); i++) {

                        JSONObject Json_obj_ = LangArray.getJSONObject(i);
                        Type language = new Type();

                        language.setId(Json_obj_.optString("id").toString());
                        language.setName(Json_obj_.optString("name").toString());
                        LanguageList.add(language);

                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static ArrayList<Type> getDataLanguageList() {
        return LanguageList;
    }


    //Company
    private static ArrayList<Type> CompanyList;

    public static boolean getCompanyList(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                CompanyList = new ArrayList<>();
                if (dataObject.getJSONArray("companies") != null) {
                    JSONArray CompanyArray = dataObject.getJSONArray("companies");
                    CompanyList = new ArrayList<>();
                    for (int i = 0; i < CompanyArray.length(); i++) {

                        JSONObject Json_obj_ = CompanyArray.getJSONObject(i);
                        Type type = new Type();
                        type.setId(Json_obj_.optString("id"));
                        type.setName(Json_obj_.optString("name"));
                        System.out.println("GET_Type_list === " + type.getName());
                        CompanyList.add(type);

                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static ArrayList<Type> getDataCompanyList() {
        return CompanyList;
    }

    //Registration

    public static boolean getRegistration(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                status = dataObject.optString("status");
                message = dataObject.optString("message");
                if (status.equals("TRUE")) {
//                    user_id= dataObject.optString("user_id");
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static String getMessage() {
        return message;
    }


    //Login


    public static String user_id, token_code;

    public static boolean getLogin(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                status = dataObject.optString("status");
                message = dataObject.optString("message");
                if (status.equals("TRUE")) {
                    user_id = dataObject.optString("user_id");
                    token_code = dataObject.optString("token_code");
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static String getLoginMessage() {
        return message;
    }

    public static String getUserId() {
        return user_id;
    }

    public static String getTokenCode() {
        return token_code;
    }


    //ForgetPassword


    public static String Forgetmessage;

    public static boolean getForgetPassword(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                status = dataObject.optString("status");

                if (status.equals("TRUE")) {
                    Forgetmessage = dataObject.optString("message");
                    return true;
                } else {
                    Forgetmessage = dataObject.optString("message");
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static String getForgetMessage() {
        return Forgetmessage;
    }


    //Training
public static String Trainingmessage;
    public static boolean getTraining(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                status = dataObject.optString("status");
                Trainingmessage = dataObject.optString("message");
                if (status.equals("TRUE")) {

                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static String getTrainingMessage() {
        return Trainingmessage;
    }



    //Document
    public static ArrayList<Document> DocumentList;

    public static boolean getDocumentList(String data) {
        try {
            DocumentList = new ArrayList<>();
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                if (dataObject.getJSONArray("documents") != null) {
                    JSONArray CompanyArray = dataObject.getJSONArray("documents");
                    CompanyList = new ArrayList<>();
                    for (int i = 0; i < CompanyArray.length(); i++) {

                        JSONObject Json_obj_ = CompanyArray.getJSONObject(i);
                        Document document = new Document();
                        document.setId(Json_obj_.optString("id"));
                        document.setTitle(Json_obj_.optString("title"));
                        document.setType(Json_obj_.optString("type"));
                        document.setRecord(Json_obj_.optString("record"));
                        document.setDescription(Json_obj_.optString("description"));

                        System.out.println("GET_Type_list === " + document.getTitle());
                        DocumentList.add(document);

                    }
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static ArrayList<Document> getDataDocumentList() {
        return DocumentList;
    }


    // Profile


    public static Profile profile;
    public static boolean getProfile(String data) {
        try {
            profile = new Profile();
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                status = dataObject.optString("status");
                    status = dataObject.optString("status");

                    if (status.equals("TRUE")) {
                        profile.setFirstName(dataObject.optString("first_name"));
                        profile.setLastName(dataObject.optString("last_name"));
                        profile.setEmail(dataObject.optString("email"));
                        profile.setPhone_number(dataObject.optString("phone_number"));
                        profile.setAge(dataObject.optString("age"));
                        profile.setRole(dataObject.optString("role"));
                        profile.setCompany_name(dataObject.optString("company_name"));
                        profile.setCountry_code(dataObject.optString("country_code"));
                        return true;
                    } else {
                        message = dataObject.optString("message");
                        return false;
                    }
                } else {
                    return false;
                }


        } catch (JSONException e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static Profile getProfileData() {
        return profile;
    }

    //updateProfile

    public static boolean getUpdateProfile(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());

            if (!data.toString().isEmpty()) {
                status = dataObject.optString("status");
                message = dataObject.optString("message");
                if (status.equals("TRUE")) {

                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static String getUpdateMessage() {
        return message;
    }

    //NotificationList


    private static ArrayList<notification> NotificationList;


    public static boolean getNotificationList(String data) {
        try {
            JSONObject dataObject = new JSONObject(data);
            Log.i("data", data.toString());
            status = dataObject.optString("status");
            message = dataObject.optString("message");
            if (status.equals("TRUE")) {
                if (!data.toString().isEmpty()) {
                    NotificationList = new ArrayList<>();
                    if (dataObject.getJSONArray( "notofications" ) != null) {
                        JSONArray CountrArray = dataObject.getJSONArray( "notofications" );
                        for (int i = 0; i < CountrArray.length(); i++) {

                            JSONObject Json_obj_ = CountrArray.getJSONObject( i );
                            notification notificatn = new notification();

                            notificatn.setTitle( Json_obj_.optString( "title" ).toString() );
                            notificatn.setMessage( Json_obj_.optString( "message" ).toString() );
                            notificatn.setTime( Json_obj_.optString( "time" ).toString() );

                            NotificationList.add( notificatn );

                        }
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }
            }else{
                return false;
            }
        } catch (Exception e) {
            Log.e("Exception", e.toString());
            e.printStackTrace();
            return false;

        }

    }

    public static ArrayList<notification> getDataNotification() {
        return NotificationList;
    }

}




