package aktorius.com.android.weather.API;
import aktorius.com.android.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Aktorius.
 */

public interface WeatherInterface {

/*    @GET("weather?")
    Call<WeatherResponse> getWeatherFromZip(
            @Query("zip") String zip,
            @Query("APPID") String appId
    );*/


    @GET("{api_key}/conditions/q/CA/San_Francisco.json")
    Call<WeatherResponse> getWeatherFromSanFrancisco(
            @Path("api_key") String apiKey
    );
}