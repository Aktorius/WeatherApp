package aktorius.com.android.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Aktorius.
 */

public class WeatherResponse {

    @SerializedName("current_observation")
    private WeatherData weatherData;

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

}