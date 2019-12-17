package com.adyabukhari.pixalist.RecyclerView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public abstract class InfiniteScrollable extends RecyclerView.OnScrollListener  {

    /**
     * Created by Abkhrr on 17/12/2019.
     *
     * Email: bukhariadbuk@gmail.com
     * GitHub: https://github.com/abkhrr
     *
     */

    private int previousTotal = 0;
    private boolean loading = true;
    private int image_page = 1;

    private final LinearLayoutManager mLinearLayoutManager;

    // Set The Infinite Scroll

    public InfiniteScrollable(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int VisibleCount = recyclerView.getChildCount();
        int totalCount = mLinearLayoutManager.getItemCount();
        int firstVisibleImage = mLinearLayoutManager.findFirstVisibleItemPosition();

        if (loading && (VisibleCount > previousTotal)) {
            loading = false;
            previousTotal = totalCount;
        }

        int visibleThreshold = 5;
        if (!loading && (totalCount - VisibleCount)
                <= (firstVisibleImage + visibleThreshold)) {
            image_page++;
            onLoadMore(image_page);
            loading = true;
        }
    }

    public abstract void onLoadMore(int currentPage);
}
