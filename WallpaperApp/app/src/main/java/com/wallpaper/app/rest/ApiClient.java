package com.wallpaper.app.rest;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by OnGraph-SEO on 9/20/2016.
 */
public class ApiClient {

    //https://pixabay.com/api/?key=6211411-eed888dfabbcf36d67b7d0ae2&q=wallpaper+hd

    public static final String API_KEY = "6238905-e668c0bd454c8958eb36a2fab";
    public static final String BASE_URL = "https://pixabay.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
