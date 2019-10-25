package com.example.testapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class WorkScheduleModel {
    private String startTime;
    private String endTime;
    private int[] dayOfWeek;

    public WorkScheduleModel(JSONObject jsonObject) throws JSONException {
        this.startTime = jsonObject.getString("startTime");
        this.endTime = jsonObject.getString("endTime");
        JSONArray days = jsonObject.getJSONArray("dayOfWeek");
        for (int i = 0; i < days.length(); i++) {
            this.dayOfWeek[i] = Integer.valueOf(days.getJSONObject(i).toString());
        }
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int[] getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(int[] dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
