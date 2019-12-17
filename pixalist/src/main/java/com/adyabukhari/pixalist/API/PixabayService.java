package com.adyabukhari.pixalist.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Abkhrr on 17/12/2019.
 *
 * Email: bukhariadbuk@gmail.com
 * GitHub: https://github.com/abkhrr
 *
 */

public class PixabayService {

    // Adding PixabayService To call BASE-URL
    // And GET the API Using Retrofit and GsonConverterFactory

    public static PixabayAPI createPixabayService() {
        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://pixabay.com/");

        return builder.build().create(PixabayAPI.class);
    }
}
