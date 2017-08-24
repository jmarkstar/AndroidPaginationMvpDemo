package com.jmarkstar.carlist_core.domain.repository.network;


import com.jmarkstar.carlist_core.domain.repository.network.response.BaseResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jmarkstar on 22/08/2017.
 */
public interface ApiTestService {

    @GET("car-types/manufacturer")
    Call<BaseResponse<HashMap<String, String>>> getManufacturers(@Query("page") Integer page,
                                                                 @Query("pageSize") Integer pageSize,
                                                                 @Query("wa_key") String waKey);

    @GET("car-types/main-types")
    Call<BaseResponse<HashMap<String, String>>> getMainTypes(@Query("manufacturer") String manufacturer,
                                                             @Query("page") Integer page,
                                                             @Query("pageSize") Integer pageSize,
                                                             @Query("wa_key") String waKey);

    @GET("car-types/built-dates")
    Call<BaseResponse<HashMap<String, String>>> getBuiltDates(@Query("manufacturer") String manufacturer,
                                                              @Query("main-type") String mainType,
                                                              @Query("wa_key") String waKey);
}
