package com.example.avalert1;
import java.util.ArrayList;

public class AvalancheCity {

    private static ArrayList<AvalancheCity> cities = new ArrayList<>();

    private String cityName;
    private String dangerLevel;

    public AvalancheCity(String cityName, String dangerLevel) {
        this.cityName = cityName;
        this.dangerLevel = dangerLevel;
    }

    public String getCityName() {
        return cityName;
    }

    public String getDangerLevel() {
        return dangerLevel;
    }

    // Method to get danger level by city name
    public static String getDangerLevelByCityName(String cityName) {
        for (AvalancheCity city : cities) {
            if (city.getCityName().equalsIgnoreCase(cityName)) {
                return city.getDangerLevel();
            }
        }
        // Return null if city name is not found
        return null;
    }

    // Initialize the permanent array
    static {
        // Appending cities with danger levels to the list
        cities.add(new AvalancheCity("Sydney", "Low"));
        cities.add(new AvalancheCity("Tokyo", "Low"));
        cities.add(new AvalancheCity("Los Angeles", "Low"));
        cities.add(new AvalancheCity("Barcelona", "Low"));
        cities.add(new AvalancheCity("Cape Town", "Low"));
        cities.add(new AvalancheCity("Buenos Aires", "Low"));
        cities.add(new AvalancheCity("Mumbai", "Low"));
        cities.add(new AvalancheCity("Cairo", "Low"));
        cities.add(new AvalancheCity("Singapore", "Low"));
        cities.add(new AvalancheCity("Rio de Janeiro", "Low"));
        cities.add(new AvalancheCity("Istanbul", "Low"));
        cities.add(new AvalancheCity("Seoul", "Low"));
        cities.add(new AvalancheCity("Riyadh", "Low"));
        cities.add(new AvalancheCity("Dallas", "Low"));
        cities.add(new AvalancheCity("Jakarta", "Low"));
        cities.add(new AvalancheCity("Lima", "Low"));
        cities.add(new AvalancheCity("Melbourne", "Low"));
        cities.add(new AvalancheCity("Lagos", "Low"));
        cities.add(new AvalancheCity("Phoenix", "Low"));
        cities.add(new AvalancheCity("Riyadh", "Low"));
        cities.add(new AvalancheCity("Chennai", "Low"));
        cities.add(new AvalancheCity("Johannesburg", "Low"));
        cities.add(new AvalancheCity("Manila", "Low"));
        cities.add(new AvalancheCity("Houston", "Low"));
        cities.add(new AvalancheCity("Bangkok", "Low"));
        cities.add(new AvalancheCity("Dallas", "Low"));
        cities.add(new AvalancheCity("Nairobi", "Low"));
        cities.add(new AvalancheCity("Atlanta", "Low"));
        cities.add(new AvalancheCity("Santiago", "Low"));
        cities.add(new AvalancheCity("Casablanca", "Low"));
        cities.add(new AvalancheCity("San Francisco", "Low"));
        cities.add(new AvalancheCity("Shenzhen", "Low"));
        cities.add(new AvalancheCity("Dallas", "Low"));
        cities.add(new AvalancheCity("Bogotá", "Low"));
        cities.add(new AvalancheCity("Guangzhou", "Low"));
        cities.add(new AvalancheCity("Toronto", "Low"));
        cities.add(new AvalancheCity("Miami", "Low"));
        cities.add(new AvalancheCity("Amsterdam", "Low"));
        cities.add(new AvalancheCity("Moscow", "Low"));
        cities.add(new AvalancheCity("Berlin", "Low"));
        cities.add(new AvalancheCity("Montreal", "Low"));
        cities.add(new AvalancheCity("Barcelona", "Low"));
        cities.add(new AvalancheCity("Munich", "Low"));
        cities.add(new AvalancheCity("Zurich", "Low"));
        cities.add(new AvalancheCity("Vienna", "Low"));
        cities.add(new AvalancheCity("Oslo", "Low"));
        cities.add(new AvalancheCity("Copenhagen", "Low"));
        cities.add(new AvalancheCity("Stockholm", "Low"));
        cities.add(new AvalancheCity("Helsinki", "Low"));
        cities.add(new AvalancheCity("Reykjavik", "Low"));

        cities.add(new AvalancheCity("Denver", "Moderate"));
        cities.add(new AvalancheCity("Innsbruck", "Moderate"));
        cities.add(new AvalancheCity("Vancouver", "Moderate"));
        cities.add(new AvalancheCity("Grenoble", "Moderate"));
        cities.add(new AvalancheCity("Queenstown", "Moderate"));
        cities.add(new AvalancheCity("Anchorage", "Moderate"));
        cities.add(new AvalancheCity("Sapporo", "Moderate"));
        cities.add(new AvalancheCity("Salt Lake City", "Moderate"));
        cities.add(new AvalancheCity("Chamonix", "Moderate"));
        cities.add(new AvalancheCity("Queenstown", "Moderate"));
        cities.add(new AvalancheCity("Andermatt", "Moderate"));
        cities.add(new AvalancheCity("Banff", "Moderate"));
        cities.add(new AvalancheCity("Hakuba", "Moderate"));
        cities.add(new AvalancheCity("Jackson Hole", "Moderate"));
        cities.add(new AvalancheCity("Niseko", "Moderate"));
        cities.add(new AvalancheCity("Aspen", "Moderate"));
        cities.add(new AvalancheCity("Whistler", "Moderate"));
        cities.add(new AvalancheCity("Verbier", "Moderate"));
        cities.add(new AvalancheCity("St. Moritz", "Moderate"));
        cities.add(new AvalancheCity("Lake Tahoe", "Moderate"));
        cities.add(new AvalancheCity("Courchevel", "Moderate"));
        cities.add(new AvalancheCity("Aspen", "Moderate"));
        cities.add(new AvalancheCity("Park City", "Moderate"));
        cities.add(new AvalancheCity("Taos", "Moderate"));
        cities.add(new AvalancheCity("Mammoth Lakes", "Moderate"));
        cities.add(new AvalancheCity("Telluride", "Moderate"));
        cities.add(new AvalancheCity("Cortina d'Ampezzo", "Moderate"));
        cities.add(new AvalancheCity("Almaty", "Moderate"));
        cities.add(new AvalancheCity("Arosa", "Moderate"));
        cities.add(new AvalancheCity("Gstaad", "Moderate"));
        cities.add(new AvalancheCity("Stowe", "Moderate"));
        cities.add(new AvalancheCity("Engelberg", "Moderate"));
        cities.add(new AvalancheCity("La Grave", "Moderate"));
        cities.add(new AvalancheCity("Bozeman", "Moderate"));
        cities.add(new AvalancheCity("Steamboat Springs", "Moderate"));
        cities.add(new AvalancheCity("Big Sky", "Moderate"));
        cities.add(new AvalancheCity("Lech", "Moderate"));
        cities.add(new AvalancheCity("Crested Butte", "Moderate"));
        cities.add(new AvalancheCity("Grandvalira", "Moderate"));
        cities.add(new AvalancheCity("Sun Valley", "Moderate"));
        cities.add(new AvalancheCity("Portillo", "Moderate"));
        cities.add(new AvalancheCity("Tignes", "Moderate"));
        cities.add(new AvalancheCity("Revelstoke", "Moderate"));
        cities.add(new AvalancheCity("Hemsedal", "Moderate"));
        cities.add(new AvalancheCity("Auli", "Moderate"));
        cities.add(new AvalancheCity("Shiga Kogen", "Moderate"));
        cities.add(new AvalancheCity("Courmayeur", "Moderate"));
        cities.add(new AvalancheCity("Kitzbühel", "Moderate"));
        cities.add(new AvalancheCity("Les Deux Alpes", "Moderate"));
        cities.add(new AvalancheCity("Krasnaya Polyana", "Moderate"));

        cities.add(new AvalancheCity("Zermatt", "High"));
        cities.add(new AvalancheCity("La Paz", "High"));
        cities.add(new AvalancheCity("Juneau", "High"));
        cities.add(new AvalancheCity("Tromsø", "High"));
        cities.add(new AvalancheCity("Gulmarg", "High"));
        cities.add(new AvalancheCity("Andermatt", "High"));
        cities.add(new AvalancheCity("Revelstoke", "High"));
        cities.add(new AvalancheCity("Haines", "High"));
        cities.add(new AvalancheCity("Engelberg", "High"));
        cities.add(new AvalancheCity("Huaraz", "High"));
        cities.add(new AvalancheCity("Mürren", "High"));
        cities.add(new AvalancheCity("Mount Cook Village", "High"));
        cities.add(new AvalancheCity("Snowbird", "High"));
        cities.add(new AvalancheCity("Sölden", "High"));
        cities.add(new AvalancheCity("Girdwood", "High"));
        cities.add(new AvalancheCity("Verbier", "High"));
        cities.add(new AvalancheCity("Niseko", "High"));
        cities.add(new AvalancheCity("Les Arcs", "High"));
        cities.add(new AvalancheCity("Alagna Valsesia", "High"));
        cities.add(new AvalancheCity("La Grave", "High"));
        cities.add(new AvalancheCity("Bella Coola", "High"));
        cities.add(new AvalancheCity("Nendaz", "High"));
        cities.add(new AvalancheCity("Krasnaya Polyana", "High"));
        cities.add(new AvalancheCity("St. Anton", "High"));
        cities.add(new AvalancheCity("Hakuba", "High"));
        cities.add(new AvalancheCity("Val Thorens", "High"));
        cities.add(new AvalancheCity("Engelberg", "High"));
        cities.add(new AvalancheCity("Whistler", "High"));
        cities.add(new AvalancheCity("Jackson Hole", "High"));
        cities.add(new AvalancheCity("Fernie", "High"));
        cities.add(new AvalancheCity("Crested Butte", "High"));
        cities.add(new AvalancheCity("Telluride", "High"));
        cities.add(new AvalancheCity("Gstaad", "High"));
        cities.add(new AvalancheCity("Mount Hood", "High"));
        cities.add(new AvalancheCity("Grand Targhee", "High"));
        cities.add(new AvalancheCity("Kirkwood", "High"));
        cities.add(new AvalancheCity("Squaw Valley", "High"));
        cities.add(new AvalancheCity("Saas Fee", "High"));
        cities.add(new AvalancheCity("Hemsedal", "High"));
        cities.add(new AvalancheCity("Tignes", "High"));
        cities.add(new AvalancheCity("Mammoth Lakes", "High"));
        cities.add(new AvalancheCity("Val d'Isère", "High"));
        cities.add(new AvalancheCity("Flaine", "High"));
        cities.add(new AvalancheCity("Banff", "High"));
        cities.add(new AvalancheCity("Arosa", "High"));
        cities.add(new AvalancheCity("Courchevel", "High"));
        cities.add(new AvalancheCity("Big Sky", "High"));
        cities.add(new AvalancheCity("Laax", "High"));
        cities.add(new AvalancheCity("Chamonix", "High"));
        cities.add(new AvalancheCity("Aspen", "High"));
    }
}
