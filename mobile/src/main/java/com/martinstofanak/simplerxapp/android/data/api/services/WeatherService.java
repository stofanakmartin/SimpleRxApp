package com.martinstofanak.simplerxapp.android.data.api.services;

import com.martinstofanak.simplerxapp.android.data.api.model.WeatherResponse;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * Add header comment
 */
public interface WeatherService {
    @GET("/weather") ///weather?q=London,uk&units=metric
    Observable<WeatherResponse> getTodayWeather(@Query("q") String location,
                                                @Query("units") String units);
}
