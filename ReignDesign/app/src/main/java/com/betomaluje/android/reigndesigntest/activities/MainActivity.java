package com.betomaluje.android.reigndesigntest.activities;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.adapters.ArticlesRecyclerAdapter;
import com.betomaluje.android.reigndesigntest.dialogs.LoadingDialog;
import com.betomaluje.android.reigndesigntest.interfaces.OnArticleClicked;
import com.betomaluje.android.reigndesigntest.models.Article;
import com.betomaluje.android.reigndesigntest.retrofit.RetrofitManager;
import com.betomaluje.android.reigndesigntest.retrofit.models.ArticlesRequest;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;
import com.betomaluje.android.reigndesigntest.sqlite.ArticlesDBManager;
import com.betomaluje.android.reigndesigntest.utils.InternetUtils;
import com.betomaluje.android.reigndesigntest.views.DividerItemDecoration;
import com.thefinestartist.finestwebview.FinestWebView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements OnArticleClicked {

    @Bind(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeRefreshLayout;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.textView_noContent)
    View textViewNoContent;

    ArticlesRecyclerAdapter adapter;

    LoadingDialog loadingDialog;

    boolean hasInternet;
    ArticlesDBManager articlesDB;
    Call lastCall;

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

        if (savedInstanceState == null) {
            initAssets();
            getArticles();
        } else {

        }
    }

    private void initAssets() {
        loadingDialog = new LoadingDialog(MainActivity.this);

        articlesDB = new ArticlesDBManager(MainActivity.this);
        articlesDB.open();

        adapter = new ArticlesRecyclerAdapter(MainActivity.this, MainActivity.this);
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

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.clear();
                currentPage = 0;
                getArticles();
            }
        });
    }

    private void getArticles() {
        loadingDialog.show();

        if (hasInternet = InternetUtils.isNetworkConnected(MainActivity.this)) {
            getArticlesFromAPI();
        } else {
            Snackbar.make(recyclerView, getString(R.string.action_no_internet), Snackbar.LENGTH_SHORT).show();
            getArticlesFromDB();
        }
    }

    private void getArticlesFromDB() {
        ArrayList<Article> articles = articlesDB.getAllArticles();

        if (!articles.isEmpty()) {
            textViewNoContent.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);

            adapter.addItems(articles);

            swipeRefreshLayout.setEnabled(false);
        } else {
            textViewNoContent.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);

            Snackbar.make(recyclerView, getString(R.string.action_no_more_articles), Snackbar.LENGTH_SHORT).show();

            swipeRefreshLayout.setEnabled(true);
        }

        swipeRefreshLayout.setRefreshing(false);
        loadingDialog.dismiss();
        loadingDialog.cancel();
        loading = true;
    }

    private void getArticlesFromAPI() {
        lastCall = new RetrofitManager().getArticles(currentPage, new Callback<ArticlesRequest>() {
            @Override
            public void onResponse(Response<ArticlesRequest> response, Retrofit retrofit) {

                ArticlesRequest articlesRequest = response.body();

                if (!articlesRequest.getHits().isEmpty()) {
                    //if there are more pages
                    if (loading = currentPage < articlesRequest.getNbPages()) {

                        //we fill the next articles
                        for (Hit hit : articlesRequest.getHits()) {
                            Article article = articlesDB.createArticle(hit);

                            if (article.isActive()) {
                                adapter.addItem(article);
                            }
                        }

                        adapter.removeDuplicates();

                        adapter.notifyDataSetChanged();

                        //we get the next page
                        currentPage = articlesRequest.getPage() + 1;
                    } else {
                        Snackbar.make(recyclerView, getString(R.string.action_no_more_articles), Snackbar.LENGTH_SHORT).show();
                    }

                    textViewNoContent.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                } else {
                    textViewNoContent.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.GONE);
                }

                loadingDialog.dismiss();
                loadingDialog.cancel();

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Throwable t) {
                loadingDialog.dismiss();
                loadingDialog.cancel();

                textViewNoContent.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.GONE);
                loading = true;
                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onDeletedClicked(View view, int position, Article article) {
        adapter.deleteItem(position);
        articlesDB.deleteArticle(article);
    }

    @Override
    public void onClicked(View view, int position, Article article) {
        String url = article.getUrl();
        if (!url.isEmpty()) {
            new FinestWebView.Builder(MainActivity.this).show(url);
        } else {
            Snackbar.make(recyclerView, getString(R.string.error_empty_url), Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        hasInternet = InternetUtils.isNetworkConnected(MainActivity.this);
        articlesDB.open();
        super.onResume();
    }

    @Override
    protected void onPause() {
        if (loadingDialog != null && loadingDialog.isShowing()) {
            loadingDialog.dismiss();
            loadingDialog.cancel();
        }

        if (lastCall != null)
            lastCall.cancel();

        articlesDB.close();
        super.onPause();
    }
}
