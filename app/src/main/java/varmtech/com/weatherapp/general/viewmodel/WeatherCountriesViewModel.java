package varmtech.com.weatherapp.general.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import varmtech.com.weatherapp.R;
import varmtech.com.weatherapp.general.model.DialogContentModel;
import varmtech.com.weatherapp.general.model.WeatherCountryObserveModel;
import varmtech.com.weatherapp.general.viewmodel.repository.WeatherCountriesRepository;
import varmtech.com.weatherapp.utils.DeviceUtils;

public class WeatherCountriesViewModel extends AndroidViewModel {
    private static final String TAG = WeatherCountriesViewModel.class.getName();
    private MutableLiveData<WeatherCountryObserveModel> liveData;
    private WeatherCountriesRepository repository;

    public WeatherCountriesViewModel(@NonNull Application application) {
        super(application);
    }

    public void getCountries() {
        if (repository == null) {
            repository = new WeatherCountriesRepository();
        }

        if (!DeviceUtils.isNetworkConnectionAvailable(getApplication())) {
            DialogContentModel dialogContentModel = new DialogContentModel(
                    getApplication().getString(R.string.splash_error_dialog_title),
                    getApplication().getString(R.string.splash_error_dialog_message));
            liveData = new MutableLiveData<>();
            liveData.setValue(new WeatherCountryObserveModel(null, dialogContentModel, false, false));
        } else {
            liveData = repository.initRequest(getApplication());
        }
    }

    public LiveData<WeatherCountryObserveModel> getLiveData() {
        return liveData;
    }
}
