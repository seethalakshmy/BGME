package com.bgmiddleeast.Utilities;

import java.io.Serializable;

/**
 * Created by seetha on 4/25/2017.
 */

public class Products implements Serializable {

    public  String service_id;
    public  String product_id;
    public  String product_title;
    public  String main_image;
    public  String description;
    public  String model;
    public  String product_order;
    public  String  delete_status;


    public String getDelete_status() {
        return delete_status;
    }

    public void setDelete_status(String delete_status) {
        this.delete_status = delete_status;
    }



    public String getProduct_order() {
        return product_order;
    }

    public void setProduct_order(String product_order) {
        this.product_order = product_order;
    }


    public String getService_id() {
        return service_id;
    }

    public void setService_id(String service_id) {
        this.service_id = service_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getMain_image() {
        return main_image;
    }

    public void setMain_image(String main_image) {
        this.main_image = main_image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


}
