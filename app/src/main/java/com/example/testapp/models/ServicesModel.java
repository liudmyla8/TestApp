package com.example.testapp.models;

import org.json.JSONException;
import org.json.JSONObject;

public class ServicesModel {
    private String id;
    private int number;
    private String label;

    public ServicesModel(JSONObject jsonObject) throws JSONException {
        this.id = jsonObject.getString("id");
        this.number = jsonObject.getInt("number");
        this.label = jsonObject.getString("label");
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
