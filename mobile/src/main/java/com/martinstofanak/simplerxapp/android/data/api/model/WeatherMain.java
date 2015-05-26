package com.martinstofanak.simplerxapp.android.data.api.model;

/**
 * Add header comment
 */
public class WeatherMain {
    private float temp;
    private float tempMin;
    private float tempMax;
    private float pressure;
    private float seaLevel;
    private float groundLevel;
    private int humidity;


    public WeatherMain(float temp, float tempMin, float tempMax, float pressure, float seaLevel, float groundLevel, int humidity) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.seaLevel = seaLevel;
        this.groundLevel = groundLevel;
        this.humidity = humidity;
    }


    public float getTemp() {
        return temp;
    }


    public float getTempMin() {
        return tempMin;
    }


    public float getTempMax() {
        return tempMax;
    }


    public float getPressure() {
        return pressure;
    }


    public float getSeaLevel() {
        return seaLevel;
    }


    public float getGroundLevel() {
        return groundLevel;
    }


    public int getHumidity() {
        return humidity;
    }
}
