package com.betomaluje.android.reigndesigntest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.adapters.ArticlesRecyclerAdapter;
import com.betomaluje.android.reigndesigntest.interfaces.OnArticleClicked;
import com.betomaluje.android.reigndesigntest.retrofit.RetrofitManager;
import com.betomaluje.android.reigndesigntest.retrofit.models.ArticlesRequest;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements OnArticleClicked {

    @Bind(R.id.textView_noContent)
    View textViewNoContent;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.progressBar)
    View progressBar;

    ArticlesRecyclerAdapter adapter;
    ArrayList<Hit> articles = new ArrayList<>();
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getArticles();
    }

    private void getArticles() {
        progressBar.setVisibility(View.VISIBLE);

        new RetrofitManager().getArticles(currentPage, new Callback<ArticlesRequest>() {
            @Override
            public void onResponse(Response<ArticlesRequest> response, Retrofit retrofit) {
                progressBar.setVisibility(View.GONE);
                textViewNoContent.setVisibility(View.GONE);

                ArticlesRequest articlesRequest = response.body();

                //if there are more pages, we select the next one
                if (currentPage < articlesRequest.getNbPages()) {
                    currentPage = articlesRequest.getPage() + 1;
                }

                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Throwable t) {
                progressBar.setVisibility(View.GONE);
                textViewNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onDeletedClicked(View view, int position, Hit hit) {

    }

    @Override
    public void onClicked(View view, int position, Hit hit) {

    }
}
