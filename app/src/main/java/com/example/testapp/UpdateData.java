package com.example.testapp;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.ArchTaskExecutor;

import com.example.testapp.models.EmployeeModel;
import com.example.testapp.models.WorkScheduleModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

public class UpdateData{
    private static volatile UpdateData _instance = null;
    private List<EmployeeModel> employeeList = null;
    private String SERVER_URL = "https://my-json-server.typicode.com/liudmyla8/TestApp/";

    public static synchronized UpdateData getInstance() {
        if (_instance == null)
            synchronized (UpdateData.class) {
                if (_instance == null)
                    _instance = new UpdateData();
            }
        return _instance;
    }

    public List<EmployeeModel> getData(String url) {
        OkHttpClient client = new OkHttpClient();
        Request getRequest = new Request.Builder()
                .url(url)
                .build();

        client.newCall(getRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                String mMessage = e.getMessage();
                Log.w("failure Response", mMessage);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String mMessage = response.body().string();
                Log.e(TAG, mMessage);

                try {
                    JSONObject json = new JSONObject(mMessage);
                    JSONArray jsonArray = json.getJSONArray("data");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject data = jsonArray.getJSONObject(i);
                        if (data.length() == 0) {
                            return;
                        }
                        EmployeeModel employee = new EmployeeModel(data);
                        employeeList.add(employee);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        return employeeList;
    }
}
