package com.martinstofanak.simplerxapp.android.data.geolocation;

import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.martinstofanak.simplerxapp.android.data.exception.LocationFetchFailedException;

import rx.Observable;
import rx.Subscriber;

/**
 * Add header comment
 */
public class LocationObservable implements Observable.OnSubscribe<Location> {

    private GoogleApiClient mGoogleApiClient;

    public static Observable<Location> createObservable(GoogleApiClient googleApiClient) {
        return Observable.create(new LocationObservable(googleApiClient));
    }

    private LocationObservable(GoogleApiClient googleApiClient) {
        mGoogleApiClient = googleApiClient;
    }


    @Override public void call(Subscriber<? super Location> subscriber) {
        final Location location = obtainLastLocation(mGoogleApiClient);

        if(location == null)
            subscriber.onError(new LocationFetchFailedException());

        subscriber.onNext(location);
    }


    private Location obtainLastLocation(GoogleApiClient apiClient) {
        return LocationServices.FusedLocationApi.getLastLocation(apiClient);
    }
}
