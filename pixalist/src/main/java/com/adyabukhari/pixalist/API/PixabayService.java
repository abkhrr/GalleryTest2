package com.adyabukhari.pixalist.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PixabayService {

    public static PixabayAPI createPixabayService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/");

        return builder.build().create(PixabayAPI.class);
    }
}
