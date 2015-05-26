package com.martinstofanak.simplerxapp.android.data.exception;

/**
 * Add header comment
 */
public class LocationFetchFailedException extends WeatherSimpleRxException {

    public LocationFetchFailedException() {
        super(ExceptionCodes.LOCATION_GENERAL);
    }


    public LocationFetchFailedException(int code) {
        super(code);
    }


    public LocationFetchFailedException(String message) {
        super(ExceptionCodes.LOCATION_GENERAL, message);
    }


    public LocationFetchFailedException(int code, String message) {
        super(code, message);
    }
}
