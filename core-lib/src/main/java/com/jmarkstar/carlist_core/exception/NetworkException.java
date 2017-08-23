package com.jmarkstar.carlist_core.exception;

/**
 * Created by jmarkstar on 22/08/2017.
 */

public class NetworkException extends Exception {

    private int httpCode;

    public NetworkException(int httpCode){
        this.httpCode = httpCode;
    }

    public int getHttpCode() {
        return httpCode;
    }
}
