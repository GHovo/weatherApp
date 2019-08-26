package varmtech.com.weatherapp.general.model;

public class WeatherCountryObserveModel {
    // Object
    private WeatherCountryModel weatherCountryModel;
    private DialogContentModel dialogContentModel;

    // Variable
    private boolean isRequestSuccess;
    private boolean isNetworkConnectionAvailable;

    public WeatherCountryObserveModel(WeatherCountryModel weatherCountryModel, DialogContentModel dialogContentModel, boolean isRequestSuccess, boolean isNetworkConnectionAvailable) {
        this.weatherCountryModel = weatherCountryModel;
        this.dialogContentModel = dialogContentModel;
        this.isRequestSuccess = isRequestSuccess;
        this.isNetworkConnectionAvailable = isNetworkConnectionAvailable;
    }

    public DialogContentModel getDialogContentModel() {
        return dialogContentModel;
    }

    public void setDialogContentModel(DialogContentModel dialogContentModel) {
        this.dialogContentModel = dialogContentModel;
    }

    public WeatherCountryModel getWeatherCountryModel() {
        return weatherCountryModel;
    }

    public void setWeatherCountryModel(WeatherCountryModel weatherCountryModel) {
        this.weatherCountryModel = weatherCountryModel;
    }

    public boolean isRequestSuccess() {
        return isRequestSuccess;
    }

    public void setRequestSuccess(boolean requestSuccess) {
        isRequestSuccess = requestSuccess;
    }

    public boolean isNetworkConnectionAvailable() {
        return isNetworkConnectionAvailable;
    }

    public void setNetworkConnectionAvailable(boolean networkConnectionAvailable) {
        isNetworkConnectionAvailable = networkConnectionAvailable;
    }
}
