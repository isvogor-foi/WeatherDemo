package hr.foi.heureka.weatherdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.activeandroid.ActiveAndroid;

import java.util.List;

import hr.foi.heureka.weatherdemo.database.DbCity;
import hr.foi.heureka.weatherdemo.webservice.WebServiceHandler;
import hr.foi.heureka.weatherdemo.webservice.WsForecast;

public class MainActivity extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        ActiveAndroid.initialize(this);

        final WebServiceHandler sh = new WebServiceHandler();
        sh.getWeatherFromWebService(DbCity.getAll());
        sh.getWeatherForecast();

        // Set on click
        Button addCityButton = (Button) findViewById(R.id.buttonAddCity);
        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(mContext);
                final View dialogView = getLayoutInflater().inflate(R.layout.add_city_dialog, null);

                dialog.setView(dialogView);

                dialog.setTitle("Add new city");
                dialog.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editTextCity = (EditText) dialogView.findViewById(R.id.dialogTextCity);
                        EditText editTextCountry = (EditText) dialogView.findViewById(R.id.dialogTextCountry);

                        DbCity newCity = new DbCity(editTextCity.getText().toString(), editTextCountry.getText().toString());
                        newCity.save();
                        Log.d("WeatherDemo", "Save OK!");

                        // refresh all cities
                        sh.getWeatherFromWebService(DbCity.getAll());
                    }
                });
                dialog.setNegativeButton("Cancel", null);
                dialog.show();
            }
        });

        // handle show data
        Button showData = (Button) findViewById(R.id.buttonShowData);
        showData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, TemperatureListActivity.class);
                startActivity(intent);
            }
        });

    }

}
