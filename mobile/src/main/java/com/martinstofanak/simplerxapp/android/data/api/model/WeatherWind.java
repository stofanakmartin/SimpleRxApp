package com.martinstofanak.simplerxapp.android.data.api.model;

/**
 * Add header comment
 */
public class WeatherWind {
    private float speed;
    private float deg;


    public WeatherWind(float speed, float deg) {
        this.speed = speed;
        this.deg = deg;
    }


    public float getSpeed() {
        return speed;
    }


    public float getDeg() {
        return deg;
    }


    public String getDirection() {
        if (deg >= 11.25 && deg < 33.75)
            return "NNE";
        else if (deg >= 33.75 && deg < 56.25)
            return "NE";
        else if (deg >= 56.25 && deg < 78.75)
            return "ENE";
        else if (deg >= 78.75 && deg < 101.25)
            return "E";
        else if (deg >= 101.25 && deg < 123.75)
            return "ESE";
        else if (deg >= 123.75 && deg < 146.25)
            return "SE";
        else if (deg >= 146.25 && deg < 168.75)
            return "SSE";
        else if (deg >= 168.75 && deg < 191.25)
            return "S";
        else if (deg >= 191.25 && deg < 213.75)
            return "SSW";
        else if (deg >= 213.75 && deg < 236.25)
            return "SW";
        else if (deg >= 236.25 && deg < 258.75)
            return "WSW";
        else if (deg >= 258.75 && deg < 281.25)
            return "W";
        else if (deg >= 281.25 && deg < 303.75)
            return "WNW";
        else if (deg >= 303.75 && deg < 326.25)
            return "NW";
        else if (deg >= 326.25 && deg < 348.75)
            return "NNW";
        else
            return "N";
//        N 348.75 - 11.25
//        NNE 11.25 - 33.75
//        NE 33.75 - 56.25
//        ENE 56.25 - 78.75
//        E 78.75 - 101.25
//        ESE 101.25 - 123.75
//        SE 123.75 - 146.25
//        SSE 146.25 - 168.75
//        S 168.75 - 191.25
//        SSW 191.25 - 213.75
//        SW 213.75 - 236.25
//        WSW 236.25 - 258.75
//        W 258.75 - 281.25
//        WNW 281.25 - 303.75
//        NW 303.75 - 326.25
//        NNW 326.25 - 348.75

    }
}
