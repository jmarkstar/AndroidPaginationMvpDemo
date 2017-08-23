package com.jmarkstar.carlist_core.util;

import java.util.concurrent.TimeUnit;

/**
 * Created by jmarkstar on 22/08/2017.
 */

public class Constants {
    public static final String EMPTY = "";
    public static final String SPACE = " ";

    //Network
    public static final int HTTP_TIMEOUT = 30000;

    //ThreadPool
    public static final int CORE_POOL_SIZE = 10;
    public static final int MAX_POOL_SIZE = 15;
    public static final int KEEP_ALIVE_TIME = 120;
    public static final TimeUnit TIME_UNIT = TimeUnit.SECONDS;
}
