package com.adyabukhari.pixalist.API;

import com.adyabukhari.pixalist.Model.PixabayImageList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Abkhrr on 17/12/2019.
 *
 * Email: bukhariadbuk@gmail.com
 * GitHub: https://github.com/abkhrr
 *
 */

// GET API From Pixabay.com

public interface PixabayAPI {
    @GET("/api/")
    Call<PixabayImageList> getImageResults(@Query("key") String key, @Query("q") String query, @Query("page") int page, @Query("per_page") int perPage);
}
