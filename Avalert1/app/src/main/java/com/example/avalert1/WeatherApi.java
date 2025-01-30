package com.example.avalert1;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeatherApi {

    @GET("forecast.json")
    Call<WeatherResponse> getWeatherData(
            @Query("key") String apiKey,
            @Query("q") String location,
            @Query("days") int days,
            @Query("aqi") String includeAqi,
            @Query("alerts") String includeAlerts
    );
}

