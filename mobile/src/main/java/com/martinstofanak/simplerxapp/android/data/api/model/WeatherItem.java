package com.martinstofanak.simplerxapp.android.data.api.model;

/**
 * Add header comment
 */
public class WeatherItem {
    private int id;
    private String main;
    private String description;
    private String icon;


    public WeatherItem(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }


    public int getId() {
        return id;
    }


    public String getMain() {
        return main;
    }


    public String getDescription() {
        return description;
    }


    public String getIcon() {
        return icon;
    }
}
