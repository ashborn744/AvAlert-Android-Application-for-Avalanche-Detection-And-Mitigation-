package com.example.avalert1;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class WeatherResponse {

    @SerializedName("location")
    private Location location;

    @SerializedName("current")
    private CurrentWeather currentWeather;

    @SerializedName("forecast")
    private Forecast forecast;

    public WeatherResponse(Location location, CurrentWeather currentWeather, Forecast forecast) {
        this.location = location;
        this.currentWeather = currentWeather;
        this.forecast = forecast;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public CurrentWeather getCurrentWeather() {
        return currentWeather;
    }

    public void setCurrentWeather(CurrentWeather currentWeather) {
        this.currentWeather = currentWeather;
    }

    public Forecast getForecast() {
        return forecast;
    }

    public void setForecast(Forecast forecast) {
        this.forecast = forecast;
    }

    public static class Location {
        @SerializedName("name")
        private String name;

        @SerializedName("region")
        private String region;

        @SerializedName("country")
        private String country;

        @SerializedName("lat")
        private double latitude;

        @SerializedName("lon")
        private double longitude;

        @SerializedName("tz_id")
        private String timeZoneId;

        @SerializedName("localtime")
        private String localTime;

        public Location(String name, String region, String country, double latitude, double longitude,
                        String timeZoneId, String localTime) {
            this.name = name;
            this.region = region;
            this.country = country;
            this.latitude = latitude;
            this.longitude = longitude;
            this.timeZoneId = timeZoneId;
            this.localTime = localTime;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public String getTimeZoneId() {
            return timeZoneId;
        }

        public void setTimeZoneId(String timeZoneId) {
            this.timeZoneId = timeZoneId;
        }

        public String getLocalTime() {
            return localTime;
        }

        public void setLocalTime(String localTime) {
            this.localTime = localTime;
        }
    }

    public static class CurrentWeather {
        @SerializedName("last_updated")
        private String lastUpdated;

        @SerializedName("temp_c")
        private double temperatureC;

        @SerializedName("condition")
        private CurrentCondition currentCondition;

        @SerializedName("humidity")
        private int humidity;

        @SerializedName("uv")
        private double uvIndex;

        @SerializedName("wind_kph")
        private double windSpeedKph;

        public CurrentWeather(String lastUpdated, double temperatureC, CurrentCondition currentCondition,
                              int humidity, double uvIndex, double windSpeedKph) {
            this.lastUpdated = lastUpdated;
            this.temperatureC = temperatureC;
            this.currentCondition = currentCondition;
            this.humidity = humidity;
            this.uvIndex = uvIndex;
            this.windSpeedKph = windSpeedKph;
        }

        public String getLastUpdated() {
            return lastUpdated;
        }

        public void setLastUpdated(String lastUpdated) {
            this.lastUpdated = lastUpdated;
        }

        public double getTemperatureC() {
            return temperatureC;
        }

        public void setTemperatureC(double temperatureC) {
            this.temperatureC = temperatureC;
        }

        public CurrentCondition getCurrentCondition() {
            return currentCondition;
        }

        public void setCurrentCondition(CurrentCondition currentCondition) {
            this.currentCondition = currentCondition;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public double getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(double uvIndex) {
            this.uvIndex = uvIndex;
        }

        public double getWindSpeedKph() {
            return windSpeedKph;
        }

        public void setWindSpeedKph(double windSpeedKph) {
            this.windSpeedKph = windSpeedKph;
        }
    }

    public static class CurrentCondition {
        @SerializedName("text")
        private String conditionText;

        @SerializedName("icon")
        private String iconUrl;

        @SerializedName("code")
        private int code;

        public CurrentCondition(String conditionText, String iconUrl, int code) {
            this.conditionText = conditionText;
            this.iconUrl = iconUrl;
            this.code = code;
        }

        public String getConditionText() {
            return conditionText;
        }

        public void setConditionText(String conditionText) {
            this.conditionText = conditionText;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }

    public static class Forecast {
        @SerializedName("forecastday")
        private List<ForecastDay> forecastDays;

        public Forecast(List<ForecastDay> forecastDays) {
            this.forecastDays = forecastDays;
        }

        public List<ForecastDay> getForecastDays() {
            return forecastDays;
        }

        public void setForecastDays(List<ForecastDay> forecastDays) {
            this.forecastDays = forecastDays;
        }
    }

    public static class ForecastDay {
        @SerializedName("date")
        private String date;

        @SerializedName("day")
        private Day day;

        @SerializedName("hour")
        private List<Hour> hours;

        public ForecastDay(String date, Day day, List<Hour> hours) {
            this.date = date;
            this.day = day;
            this.hours = hours;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Day getDay() {
            return day;
        }

        public void setDay(Day day) {
            this.day = day;
        }

        public List<Hour> getHours() {
            return hours;
        }

        public void setHours(List<Hour> hours) {
            this.hours = hours;
        }
    }

    public static class Day {
        @SerializedName("maxtemp_c")
        private double maxTemperatureC;

        @SerializedName("mintemp_c")
        private double minTemperatureC;

        @SerializedName("avgtemp_c")
        private double avgTemperatureC;

        @SerializedName("maxwind_kph")
        private double maxWindSpeedKph;

        @SerializedName("totalprecip_mm")
        private double totalPrecipitationMm;

        @SerializedName("avgvis_km")
        private double averageVisibilityKm;

        @SerializedName("avghumidity")
        private double averageHumidity;

//        @SerializedName("condition")
//        private Condition condition;

        @SerializedName("uv")
        private double uvIndex;

        public Day(double maxTemperatureC, double minTemperatureC, double avgTemperatureC,
                   double maxWindSpeedKph, double totalPrecipitationMm, double averageVisibilityKm,
                   double averageHumidity, double uvIndex) {
            this.maxTemperatureC = maxTemperatureC;
            this.minTemperatureC = minTemperatureC;
            this.avgTemperatureC = avgTemperatureC;
            this.maxWindSpeedKph = maxWindSpeedKph;
            this.totalPrecipitationMm = totalPrecipitationMm;
            this.averageVisibilityKm = averageVisibilityKm;
            this.averageHumidity = averageHumidity;
//            this.condition = condition;
            this.uvIndex = uvIndex;
        }

        public double getMaxTemperatureC() {
            return maxTemperatureC;
        }

        public void setMaxTemperatureC(double maxTemperatureC) {
            this.maxTemperatureC = maxTemperatureC;
        }

        public double getMinTemperatureC() {
            return minTemperatureC;
        }

        public void setMinTemperatureC(double minTemperatureC) {
            this.minTemperatureC = minTemperatureC;
        }

        public double getAvgTemperatureC() {
            return avgTemperatureC;
        }

        public void setAvgTemperatureC(double avgTemperatureC) {
            this.avgTemperatureC = avgTemperatureC;
        }

        public double getMaxWindSpeedKph() {
            return maxWindSpeedKph;
        }

        public void setMaxWindSpeedKph(double maxWindSpeedKph) {
            this.maxWindSpeedKph = maxWindSpeedKph;
        }

        public double getTotalPrecipitationMm() {
            return totalPrecipitationMm;
        }

        public void setTotalPrecipitationMm(double totalPrecipitationMm) {
            this.totalPrecipitationMm = totalPrecipitationMm;
        }

        public double getAverageVisibilityKm() {
            return averageVisibilityKm;
        }

        public void setAverageVisibilityKm(double averageVisibilityKm) {
            this.averageVisibilityKm = averageVisibilityKm;
        }

        public double getAverageHumidity() {
            return averageHumidity;
        }

        public void setAverageHumidity(double averageHumidity) {
            this.averageHumidity = averageHumidity;
        }

//        public Condition getCondition() {
//            return condition;
//        }
//
//        public void setCondition(Condition condition) {
//            this.condition = condition;
//        }

        public double getUvIndex() {
            return uvIndex;
        }

        public void setUvIndex(double uvIndex) {
            this.uvIndex = uvIndex;
        }
    }

    public static class Hour {
        @SerializedName("time")
        private String time;

        @SerializedName("temp_c")
        private double temperatureC;

        @SerializedName("condition")
        private HourlyCondition hourCondition;

        public Hour(String time, double temperatureC, HourlyCondition hourCondition) {
            this.time = time;
            this.temperatureC = temperatureC;
            this.hourCondition = hourCondition;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public double getTemperatureC() {
            return temperatureC;
        }

        public void setTemperatureC(double temperatureC) {
            this.temperatureC = temperatureC;
        }

        public HourlyCondition getHourCondition() {
            return hourCondition;
        }

        public void setHourCondition(HourlyCondition hourCondition) {
            this.hourCondition = hourCondition;
        }
    }

    public static class HourlyCondition {
        @SerializedName("text")
        private String conditionText;

        @SerializedName("icon")
        private String iconUrl;

        @SerializedName("code")
        private int code;

        public HourlyCondition(String conditionText, String iconUrl, int code) {
            this.conditionText = conditionText;
            this.iconUrl = iconUrl;
            this.code = code;
        }

        public String getConditionText() {
            return conditionText;
        }

        public void setConditionText(String conditionText) {
            this.conditionText = conditionText;
        }

        public String getIconUrl() {
            return iconUrl;
        }

        public void setIconUrl(String iconUrl) {
            this.iconUrl = iconUrl;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }



}

