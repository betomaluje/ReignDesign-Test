package com.betomaluje.android.reigndesigntest.retrofit.interfaces;

import com.betomaluje.android.reigndesigntest.retrofit.models.ArticlesRequest;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by betomaluje on 3/11/16.
 */
public interface ArticleAPI {

    @GET("search_by_date")
    Call<ArticlesRequest> getArticles(@Query("query") String query, @Query("page") int page);

}
