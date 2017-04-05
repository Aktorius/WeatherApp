package aktorius.com.android.weather;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;

import aktorius.com.android.weather.API.WeatherInterface;
import aktorius.com.android.weather.model.WeatherResponse;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private final static String BASE_URL = "http://api.wunderground.com/api/";
    private final static String API_KEY = "6bffbae23042fb86";

    private TextView countryNameTextView;
    private TextView temperatureTextView;
    private TextView pressureTextView;
    private TextView humidityTextView;
    private TextView weather;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countryNameTextView = (TextView) findViewById(R.id.countryName);
        temperatureTextView = (TextView) findViewById(R.id.temperature);
        pressureTextView = (TextView) findViewById(R.id.pressure);
        humidityTextView = (TextView) findViewById(R.id.humidity);
        weather = (TextView) findViewById(R.id.weather);

        //Execute AsyncTask
        new GetWeatherInformation().execute();
    }

    private class GetWeatherInformation extends AsyncTask<Void, Void, WeatherResponse> {

        @Override
        protected WeatherResponse doInBackground(Void... params) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            WeatherInterface weatherInterface = retrofit.create(WeatherInterface.class);
            Call<WeatherResponse> call = weatherInterface.getWeatherFromSanFrancisco(API_KEY);
            WeatherResponse weatherResponse = null;
            try {
                weatherResponse = call.execute().body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return weatherResponse;
        }

        @Override
        protected void onPostExecute(WeatherResponse weatherResponse) {
            super.onPostExecute(weatherResponse);

            if (weatherResponse != null) {
                //Update textViews with server response
                countryNameTextView.setText(weatherResponse.getWeatherData().getDisplayLocation().getCityName());
                temperatureTextView.setText(weatherResponse.getWeatherData().getTemp());
                weather.setText(weatherResponse.getWeatherData().getWeather());
                pressureTextView.setText(weatherResponse.getWeatherData().getPressure().toString());
                humidityTextView.setText(weatherResponse.getWeatherData().getHumidity());
            }

        }
    }
}
