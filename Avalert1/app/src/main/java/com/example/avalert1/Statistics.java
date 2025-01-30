package com.example.avalert1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Statistics extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HourlyAdapter hourlyAdapter;

    private ImageButton back_btn;

    Toolbar toolbar;
    TextView toolbartitle;

    private TextView currLocation, weatherDisc, currDate, currDaynDate,
            currTemperature, currWindSpeed, currUvIndex, currHumidity;
    private ImageView currWeatherIcon;
    String receivedCityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        Intent intent = getIntent();
        receivedCityName = intent.getStringExtra("CityName");
        Log.d("Statistics", "Received CityName from Intent: " + receivedCityName);


        // Set up the custom toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set the title in the center
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText("Statistics");



        // Initialize TextViews and ImageView
        currLocation = findViewById(R.id.currlocation);
        weatherDisc = findViewById(R.id.weather_disc);
        currDate = findViewById(R.id.currdate);
        currDaynDate = findViewById(R.id.currDaynDAte);
        currTemperature = findViewById(R.id.currtemperature);
        currWindSpeed = findViewById(R.id.currwind_speed);
        currUvIndex = findViewById(R.id.curruv_index);
        currHumidity = findViewById(R.id.currhumidity);
        currWeatherIcon = findViewById(R.id.currweathericon);
        back_btn = findViewById(R.id.back_arrow);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        fetchDataFromApi();

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void fetchDataFromApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.weatherapi.com/v1/")  // Use HTTPS
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        WeatherApi weatherApi = retrofit.create(WeatherApi.class);
        Call<WeatherResponse> call = weatherApi.getWeatherData(
                "36352173f41a44fbb98152843231612", // Replace with your actual API key
                receivedCityName,
                1,
                "no",
                "no"
        );

        call.enqueue(new Callback<WeatherResponse>() {
            @Override
            public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {
                if (response.isSuccessful()) {
                    WeatherResponse weatherResponse = response.body();
                    if (weatherResponse != null) {
                        List<WeatherResponse.Hour> hourlyList = weatherResponse.getForecast().getForecastDays().get(0).getHours();
                        hourlyAdapter = new HourlyAdapter(Statistics.this, hourlyList);
                        recyclerView.setAdapter(hourlyAdapter);

                        // Set values based on the sample data
                        setWeatherData(weatherResponse.getLocation(), weatherResponse.getCurrentWeather(), weatherResponse.getForecast().getForecastDays());
                        Log.d("Statistics", "API call successful");
                    }
                    else {
                        // Log error if the response body is null
                        Log.e("Statistics", "Response body is null");
                    }
                } else {
                    // Log error if the response is not successful
                    Log.e("Statistics", "API call unsuccessful. Response code: " + response.code());
                }
            }


            @Override
            public void onFailure(Call<WeatherResponse> call, Throwable t) {
                // Log the error
                Log.e("Statistics", "API call failed", t);

                // Handle failure
            }

        });
    }

    private void setWeatherData(WeatherResponse.Location location, WeatherResponse.CurrentWeather currentWeather, List<WeatherResponse.ForecastDay> forecastDays) {
        // Set values for current weather
        currLocation.setText(location.getName() + ", " + location.getCountry());

        // Access condition using WeatherResponse class
        WeatherResponse.CurrentCondition currentCondition = currentWeather.getCurrentCondition();
        if (currentCondition != null) {
            weatherDisc.setText(currentCondition.getConditionText());

            // Load current weather icon using Picasso or any other image loading library
            String iconUrl = "https:" + currentCondition.getIconUrl();
            Picasso.get().load(iconUrl).into(currWeatherIcon);
        }

        String lastUpdatedString = currentWeather.getLastUpdated();

// Parse the string into a Date object
        SimpleDateFormat inputFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault());
        Date lastUpdatedDate;
        try {
            lastUpdatedDate = inputFormat2.parse(lastUpdatedString);
        } catch (ParseException e) {
            e.printStackTrace(); // Handle the exception according to your needs
            return;
        }

// Format the Date object into the desired format
        SimpleDateFormat outputFormat2 = new SimpleDateFormat("MMMM d',' yyyy", Locale.getDefault());
        String formattedDate2 = outputFormat2.format(lastUpdatedDate);

// Set the formatted date to the TextView
        currDate.setText(formattedDate2);
        currTemperature.setText(String.valueOf(currentWeather.getTemperatureC()) + "°C");
        currWindSpeed.setText(String.valueOf(currentWeather.getWindSpeedKph()) + " km/h");
        currUvIndex.setText(String.valueOf(currentWeather.getUvIndex()));
        currHumidity.setText(String.valueOf(currentWeather.getHumidity()) + "%");

        if (forecastDays != null && !forecastDays.isEmpty()) {
            WeatherResponse.ForecastDay firstForecastDay = forecastDays.get(0);

            // Parse the API date format
            SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
            Date date = null;
            try {
                date = apiDateFormat.parse(firstForecastDay.getDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (date != null) {
                // Format the date into "Dayname, Date Monthname year"
                SimpleDateFormat outputDateFormat = new SimpleDateFormat("EEEE, dd MMMM yyyy", Locale.getDefault());
                String formattedDate = outputDateFormat.format(date);

                // Set the formatted date to your TextView
                currDaynDate.setText(formattedDate);
            }
        }
    }


    // Handle the back arrow click event
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
