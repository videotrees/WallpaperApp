package com.wallpaper.app.rest;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by OnGraph-SEO on 9/20/2016.
 */
public interface ApiInterface {

    @GET("api/")
    Call<ResponseBody> getImageList(@Query("key") String key,@Query("q") String qury);

}
