package com.martinstofanak.simplerxapp.android.data.api;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.google.android.gms.location.LocationServices;
import com.martinstofanak.simplerxapp.android.BuildConfig;
import com.martinstofanak.simplerxapp.android.data.api.provider.AddressProvider;
import com.martinstofanak.simplerxapp.android.data.api.provider.GoogleApiClientObservable;
import com.martinstofanak.simplerxapp.android.data.api.provider.WeatherProvider;
import com.martinstofanak.simplerxapp.android.data.api.services.WeatherService;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Add header comment
 */
@Module(
        complete = false,
        library = true
)
public class ApiModule {

    public static final String OPEN_WEATHER_MAP_SERVER_URL = "http://api.openweathermap.org/data/2.5";
    public static final String OPEN_WEATHER_MAP_ICON_URL = "http://openweathermap.org/img/w";
    public static final String UNITS = "metric";


    @Provides
    @Singleton Client provideClient(OkHttpClient client) {
        return new OkClient(client);
    }


    @Provides
    @Singleton RestAdapter provideRestAdapter(Client client) {
        return new RestAdapter.Builder()
                .setEndpoint(OPEN_WEATHER_MAP_SERVER_URL)
                .setClient(client)
                .setLogLevel(BuildConfig.DEBUG ? RestAdapter.LogLevel.FULL : RestAdapter.LogLevel.NONE)
                .build();
    }


    @Provides
    @Singleton WeatherService provideWeatherService(RestAdapter restAdapter) {
        return restAdapter.create(WeatherService.class);
    }


    @Provides
    @Singleton GoogleApiClientObservable provideGoogleApiClientProvider(Application app) {
        return new GoogleApiClientObservable(app, LocationServices.API);
    }


    @Provides
    @Singleton AddressProvider provideAddressProvider(Application app,
                                                      GoogleApiClientObservable googleApiClientObservable) {
        return new AddressProvider(app, googleApiClientObservable);
    }


    @Provides
    @Singleton WeatherProvider provideWeatherProvider(AddressProvider addressProvider,
                                                      WeatherService weatherService) {
        return new WeatherProvider(addressProvider, weatherService);
    }


    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}
