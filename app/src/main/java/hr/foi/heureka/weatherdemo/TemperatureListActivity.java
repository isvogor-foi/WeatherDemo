package hr.foi.heureka.weatherdemo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

import hr.foi.heureka.weatherdemo.database.DbCity;
import hr.foi.heureka.weatherdemo.webservice.WebServiceHandler;
import hr.foi.heureka.weatherdemo.webservice.WsForecast;

public class TemperatureListActivity extends AppCompatActivity {

    ArrayList<WsForecast> forecasts;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temperature_list);

        mContext = this;

        WebServiceHandler sh = new WebServiceHandler();
        sh.getWeatherFromWebService(DbCity.getAll());
        forecasts = sh.getWeatherForecast();


        Button buttonRefresh = (Button) findViewById(R.id.buttonRefresh);
        buttonRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListView listView = (ListView) findViewById(R.id.listViewTemperatures);

                ArrayList<String> values = new ArrayList<>();
                for(WsForecast forecast : forecasts){
                    values.add(forecast.getName() + ": " + forecast.getMainData().getTemp() + "°C, " + forecast.getMainData().getPressure() + "hPa");
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(mContext,
                        android.R.layout.simple_list_item_1, android.R.id.text1, values);

                listView.setAdapter(adapter);
            }
        });
    }

}
