package com.martinstofanak.simplerxapp.android.data.exception;


import com.martinstofanak.simplerxapp.android.data.api.model.ErrorResponse;

/**
 * Add header comment
 */
public class WeatherSimpleRxException extends Exception {

    private ErrorResponse mError;


    public WeatherSimpleRxException(int code) {
        super();
        mError = new ErrorResponse(code, null);
    }


    public WeatherSimpleRxException(int code, String message) {
        super(message);
        mError = new ErrorResponse(code, message);
    }


    public WeatherSimpleRxException(ErrorResponse error) {
        super(error.getMessage());
        mError = mError;
    }
}
