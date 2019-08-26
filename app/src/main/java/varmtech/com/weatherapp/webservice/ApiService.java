package varmtech.com.weatherapp.webservice;

import retrofit2.Call;
import retrofit2.http.GET;
import varmtech.com.weatherapp.general.model.WeatherCountryModel;

public interface ApiService {
    @GET("/data/2.5/group?id=616052,524901,703448,2643743,1851632&units=metric&appid=1954c1fa2332e9b85290ce24b1286af4")
    Call<WeatherCountryModel> getData();
}
