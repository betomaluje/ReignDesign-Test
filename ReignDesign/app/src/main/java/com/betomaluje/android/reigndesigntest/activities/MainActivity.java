package com.betomaluje.android.reigndesigntest.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.adapters.ArticlesRecyclerAdapter;
import com.betomaluje.android.reigndesigntest.interfaces.OnArticleClicked;
import com.betomaluje.android.reigndesigntest.retrofit.RetrofitManager;
import com.betomaluje.android.reigndesigntest.retrofit.models.ArticlesRequest;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;
import com.betomaluje.android.reigndesigntest.views.DividerItemDecoration;

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

    //for endless scrolling
    LinearLayoutManager mLayoutManager;
    int currentPage = 0;
    boolean loading = true;
    int pastVisiblesItems, visibleItemCount, totalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initAssets();
        getArticles();
    }

    private void initAssets() {
        adapter = new ArticlesRecyclerAdapter(MainActivity.this, articles, MainActivity.this);
        mLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.VERTICAL_LIST));

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //check for scroll down
                if (dy > 0) {
                    visibleItemCount = mLayoutManager.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    pastVisiblesItems = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loading = false;
                            getArticles();
                        }
                    }
                }
            }
        });

        recyclerView.setAdapter(adapter);
    }

    private void getArticles() {
        progressBar.setVisibility(View.VISIBLE);

        new RetrofitManager().getArticles(currentPage, new Callback<ArticlesRequest>() {
            @Override
            public void onResponse(Response<ArticlesRequest> response, Retrofit retrofit) {
                progressBar.setVisibility(View.GONE);
                textViewNoContent.setVisibility(View.GONE);

                ArticlesRequest articlesRequest = response.body();

                //if there are more pages
                if (currentPage < articlesRequest.getNbPages()) {
                    //we fill the next articles
                    articles.addAll(articlesRequest.getHitsPerPage() * currentPage, articlesRequest.getHits());

                    adapter.notifyDataSetChanged();

                    //we get the next page
                    currentPage = articlesRequest.getPage() + 1;
                }

                recyclerView.setVisibility(View.VISIBLE);
                loading = true;
            }

            @Override
            public void onFailure(Throwable t) {
                progressBar.setVisibility(View.GONE);
                textViewNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                loading = true;
            }
        });
    }

    @Override
    public void onDeletedClicked(View view, int position, Hit hit) {
        Log.i("CLICK", "on delete: " + position);
        adapter.deleteItem(position);
    }

    @Override
    public void onClicked(View view, int position, Hit hit) {
        Log.i("CLICK", "on click: " + position);
    }
}
