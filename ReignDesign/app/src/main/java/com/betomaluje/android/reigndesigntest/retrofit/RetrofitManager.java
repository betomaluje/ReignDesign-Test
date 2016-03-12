package com.betomaluje.android.reigndesigntest.retrofit;

import com.betomaluje.android.reigndesigntest.retrofit.interfaces.ArticleAPI;
import com.betomaluje.android.reigndesigntest.retrofit.models.ArticlesRequest;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

/**
 * Created by betomaluje on 3/11/16.
 */
public class RetrofitManager {

    public static final String BASE_URL = "http://hn.algolia.com/api/v1/";

    private Retrofit retrofit;

    public RetrofitManager() {

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

    }

    public Call<ArticlesRequest> getArticles(final int page, Callback<ArticlesRequest> cb) {

        // prepare call in Retrofit 2.0
        ArticleAPI articleAPI = retrofit.create(ArticleAPI.class);

        Call<ArticlesRequest> call = articleAPI.getArticles("android", page);
        //asynchronous call
        call.enqueue(cb);

        return call;
    }

}
