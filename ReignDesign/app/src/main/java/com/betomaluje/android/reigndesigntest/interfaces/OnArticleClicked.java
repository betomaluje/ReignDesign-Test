package com.betomaluje.android.reigndesigntest.interfaces;

import android.view.View;

import com.betomaluje.android.reigndesigntest.models.Article;

/**
 * Created by betomaluje on 3/11/16.
 */
public interface OnArticleClicked {

    void onDeletedClicked(View view, int position, Article article);

    void onClicked(View view, int position, Article article);
}
