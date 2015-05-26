package com.martinstofanak.simplerxapp.android.data.api.model;

/**
 * Add header comment
 */
public class AddressResult {
    private String city;
    private String countryCode;


    public AddressResult(String city, String countryCode) {
        this.city = city;
        this.countryCode = countryCode;
    }


    public String getCity() {
        return city;
    }


    public String getCountryCode() {
        return countryCode;
    }
}
