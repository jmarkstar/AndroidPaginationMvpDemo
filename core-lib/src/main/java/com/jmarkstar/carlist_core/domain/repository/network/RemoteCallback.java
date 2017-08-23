package com.jmarkstar.carlist_core.domain.repository.network;

import com.jmarkstar.carlist_core.exception.NetworkException;
import com.jmarkstar.carlist_core.exception.UnAuthorizedApiException;

import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by jmarkstar on 22/08/2017.
 */
public abstract class RemoteCallback<OechsleResponse> implements Callback<OechsleResponse> {

    private static final String TAG = "RemoteCallback";

    @Override
    public final void onResponse(Call<OechsleResponse> call, Response<OechsleResponse> response) {
        switch (response.code()) {
            case HttpsURLConnection.HTTP_OK:
            case HttpsURLConnection.HTTP_CREATED:
            case HttpsURLConnection.HTTP_ACCEPTED:
            case HttpsURLConnection.HTTP_NOT_AUTHORITATIVE:
                if (response.body() != null) {
                    onSuccess(response.body());
                }
                break;

            case HttpURLConnection.HTTP_UNAUTHORIZED:
                onFailed(new UnAuthorizedApiException());
                break;

            default:
                onFailed(new NetworkException(response.code()));
        }
    }

    @Override
    public final void onFailure(Call<OechsleResponse> call, Throwable t) {
        onFailed(t);
    }

    public abstract void onSuccess(OechsleResponse response);

    public abstract void onFailed(Throwable throwable);
}
