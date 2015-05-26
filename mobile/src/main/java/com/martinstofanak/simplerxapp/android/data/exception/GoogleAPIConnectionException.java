package com.martinstofanak.simplerxapp.android.data.exception;

import com.google.android.gms.common.ConnectionResult;

/**
 * Created by Mac on 24/05/15.
 * Used from mcharmas/Android-ReactiveLocation
 */
public class GoogleAPIConnectionException extends RuntimeException {
    private final ConnectionResult connectionResult;

    public GoogleAPIConnectionException(String detailMessage, ConnectionResult connectionResult) {
        super(detailMessage);
        this.connectionResult = connectionResult;
    }

    public ConnectionResult getConnectionResult() {
        return connectionResult;
    }
}
