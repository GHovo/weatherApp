package varmtech.com.weatherapp.general.viewmodel.repository;

import android.arch.lifecycle.MutableLiveData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import varmtech.com.weatherapp.AppApplication;
import varmtech.com.weatherapp.R;
import varmtech.com.weatherapp.general.model.DialogContentModel;
import varmtech.com.weatherapp.general.model.WeatherCountryModel;
import varmtech.com.weatherapp.general.model.WeatherCountryObserveModel;

public class WeatherCountriesRepository {
    private final MutableLiveData<WeatherCountryObserveModel> mutableLiveData = new MutableLiveData<>();

    public MutableLiveData<WeatherCountryObserveModel> initRequest(Context context) {
        // get country request
        AppApplication
                .getInstance()
                .getNetworkService()
                .getData()
                .enqueue(new Callback<WeatherCountryModel>() {
                    @Override
                    public void onResponse(@Nullable Call<WeatherCountryModel> call, @Nullable Response<WeatherCountryModel> response) {
                        try {
                            if (response != null) {
                                if (response.body() != null) {
                                    Observable.just(response.body())
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(subscribeData());
                                }
                            }
                        } catch (NullPointerException e) {
                            DialogContentModel dialogContentModel = new DialogContentModel(
                                    context.getString(R.string.general_bad_request_dialog_title),
                                    context.getString(R.string.general_bad_request_dialog_message));
                            mutableLiveData.setValue(new WeatherCountryObserveModel(null, dialogContentModel, false, true));
                        }
                    }

                    @Override
                    public void onFailure(Call<WeatherCountryModel> call, Throwable t) {
                        DialogContentModel dialogContentModel = new DialogContentModel(
                                context.getString(R.string.general_bad_request_dialog_title),
                                context.getString(R.string.general_bad_request_dialog_message));
                        mutableLiveData.setValue(new WeatherCountryObserveModel(null, dialogContentModel, false, true));
                    }
                });

        return mutableLiveData;
    }

    private Observer<WeatherCountryModel> subscribeData() {
        return new Observer<WeatherCountryModel>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(WeatherCountryModel value) {
                mutableLiveData.setValue(new WeatherCountryObserveModel(value, null, true, true));
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        };
    }
}
