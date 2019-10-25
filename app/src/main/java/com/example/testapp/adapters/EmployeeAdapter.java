package com.example.testapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapp.R;
import com.example.testapp.adapters.ServicesAdapter;
import com.example.testapp.databinding.ListItemBinding;
import com.example.testapp.models.EmployeeModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<EmployeeModel> mData;
    private OnEmployeeClickListener mListener;

    public EmployeeAdapter(Context mContext, List<EmployeeModel> mData, OnEmployeeClickListener mListener) {
        this.mContext = mContext;
        this.mData = mData;
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ListItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(mContext),
                R.layout.list_item, parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        EmployeeModel item = mData.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bind(item, mListener);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        private final ListItemBinding binding;

        private ViewHolder(ListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        private void bind(final EmployeeModel item, final OnEmployeeClickListener listener) {
            binding.setItem(item);
            Picasso.with(mContext).load(item.getAvatar()).into(binding.avatarImage);
            binding.servicesInfo.setAdapter(new ServicesAdapter(mContext,
                    R.layout.services_list_item, item.getServices()));

            int[] daysNum = item.getWorkSchedule().getDayOfWeek();
            String[] days = mContext.getResources().getStringArray(R.array.daysOfWeek);
            StringBuilder workSchedule = new StringBuilder();
            for (int value : daysNum) {
                workSchedule.append(days[value]);
                workSchedule.append(" ");
            }
            binding.workingDays.setText(workSchedule.toString());

            binding.getRoot().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onEmployeeClicked(item);
                }
            });
        }
    }

    public interface OnEmployeeClickListener {
        void onEmployeeClicked(EmployeeModel card);
    }
}
