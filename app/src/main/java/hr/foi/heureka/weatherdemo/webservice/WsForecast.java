package hr.foi.heureka.weatherdemo.webservice;

/**
 * Created by ivan on 15.1.2016..
 */
public class WsForecast {

    // convention over configuration
    public String name;
    public int id;
    public WsMain main;

    public WsMain getMainData() {
        return main;
    }

    public void setMainData(WsMain main) {
        this.main = main;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
