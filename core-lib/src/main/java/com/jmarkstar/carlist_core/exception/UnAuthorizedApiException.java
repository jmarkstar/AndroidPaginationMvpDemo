package com.jmarkstar.carlist_core.exception;

import com.jmarkstar.carlist_core.util.Constants;

/**
 * Created by jmarkstar on 22/08/2017.
 */
public class UnAuthorizedApiException extends Exception {

    public UnAuthorizedApiException(){
        super(Constants.EMPTY);
    }
}
