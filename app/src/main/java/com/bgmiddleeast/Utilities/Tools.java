package com.bgmiddleeast.Utilities;

import java.io.Serializable;

/**
 * Created by seetha on 4/28/2017.
 */

public class Tools implements Serializable {
    public String tool_id;
    public String tool_image;
    public String tool_title;
    public String tool_description;
    public String tool_model;

    public String getTool_id() {
        return tool_id;
    }

    public void setTool_id(String tool_id) {
        this.tool_id = tool_id;
    }

    public String getTool_image() {
        return tool_image;
    }

    public void setTool_image(String tool_image) {
        this.tool_image = tool_image;
    }

    public String getTool_title() {
        return tool_title;
    }

    public void setTool_title(String tool_title) {
        this.tool_title = tool_title;
    }


    public String getTool_model() {
        return tool_model;
    }

    public void setTool_model(String tool_model) {
        this.tool_model = tool_model;
    }


    public String getTool_description() {
        return tool_description;
    }

    public void setTool_description(String tool_description) {
        this.tool_description = tool_description;
    }
}
