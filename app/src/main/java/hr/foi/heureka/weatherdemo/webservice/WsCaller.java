package hr.foi.heureka.weatherdemo.webservice;

import com.squareup.okhttp.ResponseBody;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by ivan on 15.1.2016..
 */
public interface WsCaller {
    @GET("/data/2.5/weather")
    Call<WsForecast> getForecast(@Query("q") String city, @Query("units") String units, @Query("appid") String apiid);

}
