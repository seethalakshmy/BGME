package com.bgmiddleeast.Utilities;

import java.util.ArrayList;

/**
 * Created by seetha on 4/25/2017.
 */

public class Service {
    public  String service_id;
    public  String title;
    public  String description;
    public  String benefits;
    public  String recommendations;
    public  String title_image;
    public  String colour_code;
    public  String video_links;
    public  String before_after;
    public  String tools;
    public  String icon_image;
    public  ArrayList<Products> products;
    public String service_order;
    public String delete_status;

    public String symptoms;




    public String getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(String delete_status) {
        this.delete_status = delete_status;
    }



    public String getIcon_image() {
        return icon_image;
    }

    public void setIcon_image(String icon_image) {
        this.icon_image = icon_image;
    }
    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getRecommendations() {
        return recommendations;
    }

    public void setRecommendations(String recommendations) {
        this.recommendations = recommendations;
    }

    public String getTitle_image() {
        return title_image;
    }

    public void setTitle_image(String title_image) {
        this.title_image = title_image;
    }

    public String getColour_code() {
        return colour_code;
    }

    public void setColour_code(String colour_code) {
        this.colour_code = colour_code;
    }

    public String getVideo_links() {
        return video_links;
    }

    public void setVideo_links(String video_links) {
        this.video_links = video_links;
    }

    public String getBefore_after() {
        return before_after;
    }

    public void setBefore_after(String before_after) {
        this.before_after = before_after;
    }

    public String getTools() {
        return tools;
    }

    public void setTools(String tools) {
        this.tools = tools;
    }

    public ArrayList<Products>  getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Products> products) {
        this.products = products;
    }

    public String getService_order() {
        return service_order;
    }

    public void setService_order(String service_order) {
        this.service_order = service_order;
    }
    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }





}
