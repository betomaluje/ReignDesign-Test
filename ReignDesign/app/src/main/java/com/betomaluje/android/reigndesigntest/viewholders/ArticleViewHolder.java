package com.betomaluje.android.reigndesigntest.viewholders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.betomaluje.android.reigndesigntest.R;
import com.betomaluje.android.reigndesigntest.interfaces.OnArticleClicked;
import com.betomaluje.android.reigndesigntest.retrofit.models.Hit;
import com.betomaluje.android.reigndesigntest.utils.DateUtils;
import com.daimajia.swipe.SwipeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by betomaluje on 3/11/16.
 */
public class ArticleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.swipeLayout)
    SwipeLayout swipeLayout;

    @Bind(R.id.relativeLayout_surfaceView)
    View relativeLayoutSurfaceView;

    @Bind(R.id.relativeLayout_delete)
    View relativeLayoutDelete;
    @Bind(R.id.textView_title)
    TextView textViewTitle;
    @Bind(R.id.textView_details)
    TextView textViewDetails;

    Context context;
    OnArticleClicked onArticleClicked;

    public ArticleViewHolder(Context context, View itemView, final OnArticleClicked onArticleClicked) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        this.context = context;
        this.onArticleClicked = onArticleClicked;

        swipeLayout.setShowMode(SwipeLayout.ShowMode.PullOut);
        swipeLayout.addDrag(SwipeLayout.DragEdge.Right, relativeLayoutDelete);

        relativeLayoutSurfaceView.setOnClickListener(this);
        relativeLayoutDelete.setOnClickListener(this);
    }

    public void fillData(final Hit article) {
        textViewTitle.setText(article.getTrueTitle());

        String details = article.getAuthor() + " " + DateUtils.getDate(context, article.getCreatedAt());

        textViewDetails.setText(details);

        //little trick for click listener. Otherwise we couldn't get the Hit Object.
        relativeLayoutSurfaceView.setTag(article);
        relativeLayoutDelete.setTag(article);
    }

    @Override
    public void onClick(View v) {
        Hit article = (Hit) v.getTag();
        if (article != null && onArticleClicked != null) {
            if (v.getId() == R.id.relativeLayout_surfaceView) {
                onArticleClicked.onClicked(v, getLayoutPosition(), article);
            } else if (v.getId() == R.id.relativeLayout_delete) {
                onArticleClicked.onDeletedClicked(v, getLayoutPosition(), article);
            }
        }
    }
}
