package com.wallpaper.app.rest;

import com.google.gson.Gson;
import com.wallpaper.app.bean.APIResponse;
import com.wallpaper.app.utils.AppListners;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Navdeep on 8/19/2017.
 */

public class APIUtils {

    public static void getImageListAPI(String query, final AppListners listners){

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);
        Call<ResponseBody> call = apiService.getImageList(ApiClient.API_KEY,query);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Gson gson = new Gson();

                try {
                    if (response.body() != null) {
                        APIResponse apiResponse = gson.fromJson(response.body().string(), APIResponse.class);
                        listners.getList(apiResponse.getImageObjs());

                    } else {
                        listners.getList(null);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    listners.getList(null);
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                listners.getList(null);
            }
        });

    }
}
