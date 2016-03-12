package com.betomaluje.android.reigndesigntest.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.interfaces.OnArticleClicked;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;
import com.betomaluje.android.reigndesigntest.viewholders.ArticleViewHolder;

import java.util.ArrayList;

/**
 * Created by betomaluje on 3/11/16.
 */
public class ArticlesRecyclerAdapter extends RecyclerView.Adapter<ArticleViewHolder> {

    private ArrayList<Hit> items;
    private Context context;
    private LayoutInflater inflater;
    private OnArticleClicked onArticleClicked;

    public ArticlesRecyclerAdapter(Context context, ArrayList<Hit> items, OnArticleClicked onArticleClicked) {
        this.items = items;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.onArticleClicked = onArticleClicked;
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item_article, parent, false);
        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.fillData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

}
