package com.martinstofanak.simplerxapp.android.data.api.provider;

import com.martinstofanak.simplerxapp.android.data.api.ApiModule;
import com.martinstofanak.simplerxapp.android.data.api.model.AddressResult;
import com.martinstofanak.simplerxapp.android.data.api.model.WeatherResponse;
import com.martinstofanak.simplerxapp.android.data.api.model.WeatherResult;
import com.martinstofanak.simplerxapp.android.data.api.services.WeatherService;

import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Add header comment
 */
@Singleton
public class WeatherProvider {

    private AddressProvider mAddressProvider;
    private WeatherService mWeatherService;
    private Observable<WeatherResult> mWeatherObservable;
    private AddressResult mAddressResult;


    public WeatherProvider(AddressProvider addressProvider, WeatherService weatherService) {
        mAddressProvider = addressProvider;
        mWeatherService = weatherService;
    }


    public Observable<WeatherResult> loadWeather() {

        mWeatherObservable = mAddressProvider.loadAddress()
                .flatMap(new Func1<AddressResult, Observable<WeatherResponse>>() {
                    @Override public Observable<WeatherResponse> call(AddressResult addressResult) {
                        mAddressResult = addressResult;
                        final String address = String.format("%s,%s", addressResult.getCity(), addressResult.getCountryCode());
                        return mWeatherService.getTodayWeather(address, ApiModule.UNITS);
                    }
                })
                .map(new Func1<WeatherResponse, WeatherResult>() {
                    @Override public WeatherResult call(WeatherResponse weatherResponse) {
                        return new WeatherResult(mAddressResult, weatherResponse);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());

        return mWeatherObservable;
    }
}
