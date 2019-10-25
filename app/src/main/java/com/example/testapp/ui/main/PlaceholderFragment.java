package com.example.testapp.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.testapp.adapters.EmployeeAdapter;
import com.example.testapp.R;
import com.example.testapp.UpdateData;
import com.example.testapp.databinding.FragmentMainBinding;
import com.example.testapp.models.EmployeeModel;
import com.example.testapp.models.PageViewModel;

import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment implements EmployeeAdapter.OnEmployeeClickListener {
    private FragmentMainBinding mBinding;

    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String PORTERS_URL = "https://my-json-server.typicode.com/liudmyla8/TestApp/porters";
    private static final String CARRIERS_URL = "https://my-json-server.typicode.com/liudmyla8/TestApp/carriers";
    private static final String WORKERS_URL = "https://my-json-server.typicode.com/liudmyla8/TestApp/workers";

    private List<EmployeeModel> employeeList;

    static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PageViewModel pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
        switch (index) {
            case 1: employeeList = UpdateData.getInstance().getData(PORTERS_URL);
            case 2: employeeList = UpdateData.getInstance().getData(CARRIERS_URL);
            case 3: employeeList = UpdateData.getInstance().getData(WORKERS_URL);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);
        View root = mBinding.getRoot();
        EmployeeAdapter adapter = new EmployeeAdapter(this.getContext(), employeeList, this);
        mBinding.list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mBinding.list.setAdapter(adapter);
        if (employeeList != null) {
            mBinding.noInfo.setVisibility(View.GONE);
            mBinding.list.setVisibility(View.VISIBLE);
        }
        return root;
    }

    @Override
    public void onEmployeeClicked(EmployeeModel employee) {
        if (!employee.getServicesVisibility()) {
            employee.setServicesVisibility(true);
        } else {
            employee.setServicesVisibility(false);
        }
    }
}