package com.betomaluje.android.reigndesigntest.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by betomaluje on 3/11/16.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder {

    @Bind(R.id.relativeLayout_delete)
    View relativeLayoutDelete;

    public ArticleViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void fillData(Hit article) {

    }

}
