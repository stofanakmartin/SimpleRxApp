package com.martinstofanak.simplerxapp.android.data.api.provider;

import android.content.Context;
import android.os.Bundle;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.martinstofanak.simplerxapp.android.data.exception.GoogleAPIConnectionException;
import com.martinstofanak.simplerxapp.android.data.exception.GoogleAPIConnectionSuspendedException;
import com.martinstofanak.simplerxapp.android.data.exception.GoogleApiException;

import java.lang.ref.WeakReference;
import java.util.Arrays;
import java.util.List;

import javax.inject.Singleton;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import timber.log.Timber;

/**
 * Used from mcharmas/Android-ReactiveLocation
 */
@Singleton
public class GoogleApiClientObservable implements Observable.OnSubscribe<GoogleApiClient> {

    private GoogleApiClient mClient;
    private WeakReference<Context> mContextRef;
    private List<Api <? extends Api.ApiOptions.NotRequiredOptions>> mApiServices;


    @SafeVarargs public GoogleApiClientObservable(Context context,
                                                  Api<? extends Api.ApiOptions.NotRequiredOptions>... services) {
        mContextRef = new WeakReference<>(context);
        mApiServices = Arrays.asList(services);
    }


    @Override public void call(Subscriber<? super GoogleApiClient> subscriber) {
        if(mClient != null && mClient.isConnected())
            subscriber.onNext(mClient);

        mClient = buildApiClient(subscriber);

        if(mClient == null)
            subscriber.onError(new GoogleApiException("Build api client returned null"));

        try {
            mClient.connect();
        } catch (Throwable ex) {
            subscriber.onError(ex);
        }
    }

    private GoogleApiClient buildApiClient(Subscriber<? super GoogleApiClient> subscriber) {
        if(mContextRef == null || mContextRef.get() == null) {
            Timber.e("Context is null");
            subscriber.onError(new GoogleApiException("Context is null"));
            return null;
        }

        ApiClientConnectionCallbacks apiCallbacks
                = new ApiClientConnectionCallbacks(subscriber);

        GoogleApiClient.Builder googleApiBuilder = new GoogleApiClient.Builder(mContextRef.get());

        for(Api <? extends Api.ApiOptions.NotRequiredOptions> api : mApiServices) {
            googleApiBuilder.addApi(api);
        }

        googleApiBuilder.addConnectionCallbacks(apiCallbacks)
                        .addOnConnectionFailedListener(apiCallbacks);

        GoogleApiClient client = googleApiBuilder.build();

        apiCallbacks.setClient(client);

        return client;
    }


    private void onGoogleApiClientReady(GoogleApiClient client, Observer<? super GoogleApiClient> observer) {
        observer.onNext(client);
    }



    /**
     * Subclass
     * Google api client connection callbacks
     */
    private class ApiClientConnectionCallbacks implements
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener {

        final private Observer<? super GoogleApiClient> observer;

        private GoogleApiClient apiClient;

        private ApiClientConnectionCallbacks(Observer<? super GoogleApiClient> observer) {
            this.observer = observer;
        }

        @Override
        public void onConnected(Bundle bundle) {
            onGoogleApiClientReady(apiClient, observer);
        }

        @Override
        public void onConnectionSuspended(int cause) {
            observer.onError(new GoogleAPIConnectionSuspendedException(cause));
        }

        @Override
        public void onConnectionFailed(ConnectionResult connectionResult) {
            observer.onError(new GoogleAPIConnectionException("Error connecting to GoogleApiClient.", connectionResult));
        }

        public void setClient(GoogleApiClient client) {
            this.apiClient = client;
        }
    }
}
