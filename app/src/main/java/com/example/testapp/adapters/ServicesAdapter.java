package com.example.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.testapp.R;
import com.example.testapp.models.ServicesModel;

import java.util.List;

public class ServicesAdapter extends ArrayAdapter<ServicesModel> {
    private Context mContext;
    private List<ServicesModel> servicesList;

    public ServicesAdapter(@NonNull Context context, int resource, @NonNull List<ServicesModel> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.servicesList = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItem = convertView;
        if(listItem == null) {
            listItem = LayoutInflater.from(mContext).inflate(R.layout.services_list_item, parent, false);
        }
        ServicesModel service = servicesList.get(position);

        TextView label = listItem.findViewById(R.id.price_text);
        label.setText(service.getLabel());

        TextView number = listItem.findViewById(R.id.price_amount);
        number.setText(service.getNumber());

        return listItem;
    }

}
