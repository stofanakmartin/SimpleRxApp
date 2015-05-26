package com.martinstofanak.simplerxapp.android.data.api.model;

import com.google.gson.annotations.SerializedName;

/**
 * Add header comment
 */
public class WeatherRain {
    @SerializedName("3h")
    private float precipitation;


    public WeatherRain(float precipitation) {
        this.precipitation = precipitation;
    }


    public float getPrecipitation() {
        return precipitation;
    }
}
