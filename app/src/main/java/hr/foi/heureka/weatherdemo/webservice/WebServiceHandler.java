package hr.foi.heureka.weatherdemo.webservice;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import hr.foi.heureka.weatherdemo.database.DbCity;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by ivan on 15.1.2016..
 */
public class WebServiceHandler {

    public static final String API_ID = "f045af2186b24a8ebafdb88e2f890ce7";
    public static final String BASE_URL = "http://api.openweathermap.org/";

    private ArrayList<WsForecast> weatherForecast;

    public void getWeatherFromWebService(List<DbCity> allCities){

        weatherForecast = new ArrayList<WsForecast>();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WsCaller serviceCaller = retrofit.create(WsCaller.class);
        // old
        if(allCities != null) {
            for (DbCity city : allCities) {
                Call<WsForecast> call = serviceCaller.getForecast(city.name + "," + city.country_short, "metric", API_ID);
                // from database
                call.enqueue(new Callback<WsForecast>() {
                    @Override
                    public void onResponse(Response<WsForecast> response, Retrofit retrofit) {
                        if (response.isSuccess()) {
                            Log.d("WeatherDemo", "Web service OK!!");
                            weatherForecast.add(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Throwable t) {
                        Log.d("WeatherDemo", "Web service FAIL!");
                    }
                });
            }
        }
    }

    public ArrayList<WsForecast> getWeatherForecast(){
        return weatherForecast;
    }

}
