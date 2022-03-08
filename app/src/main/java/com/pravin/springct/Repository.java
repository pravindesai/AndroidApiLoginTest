package com.pravin.springct;

import android.util.Log;
import android.widget.Toast;

import com.google.gson.internal.GsonBuildConfig;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Repository {
    ApiService service;
    private final String TAG = "Repository";
    Repository(){
        service = new Retrofit.Builder().baseUrl(ApiService.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService.class);
    }

    void getUserData(OnLoginResult onLoginResult){
        Call<UserResponse> responseCall = service.getDetails();
        responseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){
                    Log.e(TAG, "onResponse: S "+response.body() );
                    onLoginResult.onLoginSucess(response.body());
                }else {
                    Log.e(TAG, "onFailure: "+response.errorBody() );
                    onLoginResult.onLoginFailed(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage() );
                onLoginResult.onLoginFailed(t.getMessage());
            }
        });
    }

}
