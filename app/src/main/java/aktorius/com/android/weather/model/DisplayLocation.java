package aktorius.com.android.weather.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Aktorius.
 */

public class DisplayLocation {
    @SerializedName("city")
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}