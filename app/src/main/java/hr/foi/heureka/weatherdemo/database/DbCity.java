package hr.foi.heureka.weatherdemo.database;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;

import java.util.List;

/**
 * Created by ivan on 15.1.2016..
 */

@Table(name = "City")
public class DbCity extends Model{
    @Column(name = "name")
    public String name;
    @Column(name = "country_short")
    public String country_short;

    public DbCity(){}

    public DbCity(String name, String country){
        this.name = name;
        this.country_short = country;
    }

    public static List<DbCity> getAll(){
        List<DbCity> allCities = null;
        try {
            allCities =  new Select().from(DbCity.class).execute();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        return allCities;
    }
}
