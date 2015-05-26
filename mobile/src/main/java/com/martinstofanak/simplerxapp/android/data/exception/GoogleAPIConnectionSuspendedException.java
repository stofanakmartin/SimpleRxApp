package com.martinstofanak.simplerxapp.android.data.exception;

/**
 * Created by Mac on 24/05/15.
 * Used from mcharmas/Android-ReactiveLocation
 */
public class GoogleAPIConnectionSuspendedException extends RuntimeException {
    private final int cause;

    public GoogleAPIConnectionSuspendedException(int cause) {
        this.cause = cause;
    }

    public int getErrorCause() {
        return cause;
    }
}
