package com.martinstofanak.simplerxapp.android.data.api.model;

/**
 * Add header comment
 */
public class WeatherResult {
    private AddressResult addressResult;
    private WeatherResponse weatherResponse;


    public WeatherResult(AddressResult addressResult, WeatherResponse weatherResponse) {
        this.addressResult = addressResult;
        this.weatherResponse = weatherResponse;
    }


    public AddressResult getAddressResult() {
        return addressResult;
    }


    public WeatherResponse getWeatherResponse() {
        return weatherResponse;
    }
}
