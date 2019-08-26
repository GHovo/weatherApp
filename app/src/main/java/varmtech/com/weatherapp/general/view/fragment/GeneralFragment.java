package varmtech.com.weatherapp.general.view.fragment;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import varmtech.com.weatherapp.R;
import varmtech.com.weatherapp.databinding.GeneralFragmentDataBinding;
import varmtech.com.weatherapp.general.model.WeatherCountryModel;
import varmtech.com.weatherapp.general.viewmodel.WeatherCountriesViewModel;
import varmtech.com.weatherapp.general.viewmodel.adapters.OwnRecyclerViewAdapter;

public class GeneralFragment extends Fragment {
    // Objects
    private Context context;
    private WeatherCountriesViewModel countriesViewModel;
    private GeneralFragmentDataBinding dataBinding;

    public GeneralFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_general, container, false);
        dataBinding.setGeneralFragment(dataBinding.getGeneralFragment());
        context = getContext();
        initViewModel();
        getCountriesRequest();
        return dataBinding.getRoot();
    }

    private void initViewModel() {
        countriesViewModel = ViewModelProviders.of(this).get(WeatherCountriesViewModel.class);
    }

    private void getCountriesRequest() {
        countriesViewModel.getCountries();
        countriesViewModel.getLiveData().observe(this, response -> {
            if (response != null) {
                if (response.isNetworkConnectionAvailable() && response.isRequestSuccess()) {
                    initGeneralRecyclerAdapter(response.getWeatherCountryModel());
                } else if (response.isNetworkConnectionAvailable() && !response.isRequestSuccess()) {
                    // TODO: need show bad request dialog
                } else if (!response.isNetworkConnectionAvailable()) {
                    // TODO: need show no internet dialog
                } else {

                }
            }
        });
    }

    private void initGeneralRecyclerAdapter(WeatherCountryModel weatherCountryModel) {
        dataBinding.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        dataBinding.recyclerView.addItemDecoration(new DividerItemDecoration(context, DividerItemDecoration.VERTICAL));
        dataBinding.recyclerView.setAdapter(new OwnRecyclerViewAdapter(weatherCountryModel));
    }
}
