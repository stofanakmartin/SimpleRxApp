package com.martinstofanak.simplerxapp.android.data.api.model;

import java.util.List;

/**
 * Add header comment
 */
public class WeatherResponse {
    private List<WeatherItem> weather;
    private WeatherMain main;
    private WeatherWind wind;
    private WeatherRain rain;


    public WeatherResponse(List<WeatherItem> weather, WeatherMain main, WeatherWind wind, WeatherRain rain) {
        this.weather = weather;
        this.main = main;
        this.wind = wind;
        this.rain = rain;
    }


    public List<WeatherItem> getWeather() {
        return weather;
    }


    public WeatherMain getMain() {
        return main;
    }


    public WeatherWind getWind() {
        return wind;
    }


    public WeatherRain getRain() {
        return rain;
    }
}
