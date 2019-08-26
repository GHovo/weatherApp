package varmtech.com.weatherapp.general.viewmodel.listeners;

import android.content.Context;
import android.view.View;
import android.widget.Toast;
import varmtech.com.weatherapp.general.model.WeatherCountryModel;

public class EventHandler {
    public void onItemClickedWithContext(View view, WeatherCountryModel.CityModel cityModel) {
        Toast.makeText(view.getContext(), cityModel.getName(), Toast.LENGTH_LONG).show();
    }

    public void onItemClicked(View view, Context contextFromXml, WeatherCountryModel.CityModel cityModel) {
        Toast.makeText(contextFromXml, cityModel.getName(), Toast.LENGTH_LONG).show();
    }
}

