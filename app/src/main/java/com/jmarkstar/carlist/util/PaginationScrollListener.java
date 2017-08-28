package com.jmarkstar.carlist.util;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * Created by jmarkstar on 26/08/2017.
 */

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener {

    LinearLayoutManager layoutManager;

    public PaginationScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        //Log.v("scroll", "dx="+dx+"  -  dy"+dy);
        if(dy>0){
            /*Log.v("scroll", "scroll  is working");*/
            int visibleItemCount = layoutManager.getChildCount();
            int totalItemCount = layoutManager.getItemCount();
            int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
            int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

            /*Log.v("scroll", "visibleItemCount= "+visibleItemCount);
            Log.v("scroll", "totalItemCount= "+totalItemCount);
            Log.v("scroll", "firstVisibleItemPosition= "+firstVisibleItemPosition);
            Log.v("scroll", "lastVisibleItemPosition= "+lastVisibleItemPosition);*/
            if (!isLoading() && !isLastPage()) {
                if ((visibleItemCount + firstVisibleItemPosition) >= totalItemCount
                        && firstVisibleItemPosition >= 0) {
                    loadMoreItems();
                }
            }
        }
    }

    protected abstract void loadMoreItems();

    public abstract boolean isLastPage();

    public abstract boolean isLoading();
}
