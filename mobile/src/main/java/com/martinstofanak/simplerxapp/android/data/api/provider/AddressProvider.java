package com.martinstofanak.simplerxapp.android.data.api.provider;

import android.app.Application;
import android.content.Context;
import android.location.Address;
import android.location.Location;

import com.google.android.gms.common.api.GoogleApiClient;
import com.martinstofanak.simplerxapp.android.data.api.model.AddressResult;
import com.martinstofanak.simplerxapp.android.data.geolocation.GeocoderObservable;
import com.martinstofanak.simplerxapp.android.data.geolocation.LocationObservable;

import java.lang.ref.WeakReference;
import java.util.List;

import javax.inject.Singleton;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Add header comment
 */
@Singleton
public class AddressProvider {

    private static final int ADDRESS_MAX_RESULTS = 1;

    private WeakReference<Context> mContextRef;
    private GoogleApiClientObservable mGoogleApiObservable;
    private AddressResult mAddressResult;
    private Observable<AddressResult> mAddressObservable;


    public AddressProvider(Application app, GoogleApiClientObservable googleApiObservable) {
        mContextRef = new WeakReference<Context>(app);
        mGoogleApiObservable = googleApiObservable;
    }


    public Observable<AddressResult> loadAddress() {
        return loadLocation();
    }


    private Observable<AddressResult> loadLocation() {

        Observable<AddressResult> addressObservable = Observable
                .create(mGoogleApiObservable)
                .flatMap(new Func1<GoogleApiClient, Observable<Location>>() {
                    @Override public Observable<Location> call(GoogleApiClient googleApiClient) {
                        return LocationObservable.createObservable(googleApiClient);
                    }
                }).flatMap(new Func1<Location, Observable<List<Address>>>() {
                    @Override public Observable<List<Address>> call(Location location) {
                        return GeocoderObservable.createObservable(mContextRef.get(), location.getLatitude(),
                                location.getLongitude(), ADDRESS_MAX_RESULTS);
                    }
                })
                .map(new Func1<List<Address>, AddressResult>() {
                    @Override public AddressResult call(List<Address> addresses) {
                        final Address address = addresses.get(0);
                        final String city = getCity(address);
                        final String countryCode = address.getCountryCode();
                        return new AddressResult(city, countryCode);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread());

        return addressObservable;
    }


    private String getCity(Address address) {
        return address.getSubAdminArea() != null
                ? address.getSubAdminArea()
                : address.getLocality();
    }

}
