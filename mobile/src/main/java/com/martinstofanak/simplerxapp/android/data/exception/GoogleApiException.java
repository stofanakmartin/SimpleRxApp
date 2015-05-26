package com.martinstofanak.simplerxapp.android.data.exception;

/**
 * Add header comment
 */
public class GoogleApiException extends WeatherSimpleRxException {

    public GoogleApiException() {
        super(ExceptionCodes.GOOGLE_API_GENERAL);
    }


    public GoogleApiException(int code) {
        super(code);
    }


    public GoogleApiException(String message) {
        super(ExceptionCodes.GOOGLE_API_GENERAL, message);
    }


    public GoogleApiException(int code, String message) {
        super(code, message);
    }
}
