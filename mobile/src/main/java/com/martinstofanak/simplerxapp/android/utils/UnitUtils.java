package com.martinstofanak.simplerxapp.android.utils;

/**
 * Add header comment
 */
public class UnitUtils {


    public static String formatTemperature(float temp) {
        return String.format("%.0f˚C", temp);
    }


    public static String formatPressure(float pressure) {
        return String.format("%.0f hPa", pressure);
    }


    public static String formatHumidity(int value) {
        return String.format("%d%%", value);
    }


    public static String formatRainAmount(float amount) {
        return String.format("%.1f mm", amount);
    }


    public static String formatWindSpeed(float value) {
        return String.format("%.0f m/s", value);
    }
}
