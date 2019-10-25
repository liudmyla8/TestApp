package com.example.testapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class EmployeeModel {
    private String avatar;
    private String name;
    private String uid;
    private float ratingSpeed;
    private float ratingPunctuality;
    private int numberOfOrders;
    private int numberOfOrdersComplete;
    private WorkScheduleModel workSchedule;
    private List<ServicesModel> services;
    private boolean servicesVisibility;

    public EmployeeModel(JSONObject jsonObject) throws JSONException {
        this.avatar = jsonObject.getString("avatar");
        this.name = jsonObject.getString("name");
        this.uid = jsonObject.getString("uid");
        this.ratingSpeed = Float.valueOf(jsonObject.getString("ratingSpeed"));
        this.ratingPunctuality = Float.valueOf(jsonObject.getString("ratingPunctuality"));
        this.numberOfOrders = jsonObject.getInt("numberOfOrders");
        this.numberOfOrdersComplete = jsonObject.getInt("numberOfOrdersComplete");
        this.workSchedule = new WorkScheduleModel(jsonObject.getJSONObject("workSchedule"));

        JSONArray jsonArray = jsonObject.getJSONArray("services");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject data = jsonArray.getJSONObject(i);
            if (data.length() == 0) {
                return;
            }
            ServicesModel service = new ServicesModel(data);
            this.services.add(service);
        }
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public float getRatingSpeed() {
        return ratingSpeed;
    }

    public void setRatingSpeed(float ratingSpeed) {
        this.ratingSpeed = ratingSpeed;
    }

    public float getRatingPunctuality() {
        return ratingPunctuality;
    }

    public void setRatingPunctuality(float ratingPunctuality) {
        this.ratingPunctuality = ratingPunctuality;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public int getNumberOfOrdersComplete() {
        return numberOfOrdersComplete;
    }

    public void setNumberOfOrdersComplete(int numberOfOrdersComplete) {
        this.numberOfOrdersComplete = numberOfOrdersComplete;
    }

    public WorkScheduleModel getWorkSchedule() {
        return workSchedule;
    }

    public void setWorkSchedule(WorkScheduleModel workSchedule) {
        this.workSchedule = workSchedule;
    }

    public List<ServicesModel> getServices() {
        return services;
    }

    public void setServices(List<ServicesModel> services) {
        this.services = services;
    }

    public boolean getServicesVisibility() {
        return servicesVisibility;
    }

    public void setServicesVisibility(boolean servicesVisibility) {
        this.servicesVisibility = servicesVisibility;
    }
}
