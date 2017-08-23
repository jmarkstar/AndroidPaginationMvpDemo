package com.jmarkstar.carlist_core.exception;

import com.jmarkstar.carlist_core.util.Constants;

/**
 * Created by jmarkstar on 22/08/2017.
 */

public class ApiException extends Exception {

    private int errorCode;
    private String errorMessage;

    public ApiException(int errorCode, String errorMessage){
        super(Constants.EMPTY);
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
