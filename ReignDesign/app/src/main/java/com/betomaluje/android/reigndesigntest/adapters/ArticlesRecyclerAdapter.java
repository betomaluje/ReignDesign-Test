package com.betomaluje.android.reigndesigntest.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.interfaces.OnArticleClicked;
import com.betomaluje.android.reigndesigntest.models.Article;
import com.betomaluje.android.reigndesigntest.viewholders.ArticleViewHolder;
import com.daimajia.swipe.adapters.RecyclerSwipeAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by betomaluje on 3/11/16.
 */
public class ArticlesRecyclerAdapter extends RecyclerSwipeAdapter<ArticleViewHolder> {

    private ArrayList<Article> items;
    private Context context;
    private LayoutInflater inflater;
    private OnArticleClicked onArticleClicked;

    public ArticlesRecyclerAdapter(Context context, OnArticleClicked onArticleClicked) {
        this.items = new ArrayList<>();
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.onArticleClicked = onArticleClicked;
    }

    public void addItems(ArrayList<Article> articles) {
        items.addAll(articles);
        notifyDataSetChanged();
    }

    public void addItem(Article article) {
        items.add(article);
    }

    public void deleteItem(int position) {
        items.remove(position);
        notifyItemRemoved(position);
    }

    public void clear() {
        items.clear();
        notifyDataSetChanged();
    }

    public void removeDuplicates() {
        Set<Article> hs = new HashSet<>();
        hs.addAll(items);
        items.clear();
        items.addAll(hs);
    }

    @Override
    public ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_item_article, parent, false);
        return new ArticleViewHolder(context, view, onArticleClicked);
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {
        holder.fillData(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public int getSwipeLayoutResourceId(int position) {
        return R.id.swipeLayout;
    }
}
