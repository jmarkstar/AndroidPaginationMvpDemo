package com.jmarkstar.carlist.ui.cartype.pagination.maintype;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.ui.cartype.pagination.PaginationAdapter;
import com.jmarkstar.carlist_core.domain.model.ItemModel;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public class MainTypeAdapter extends PaginationAdapter {

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case VIEW_TYPE_EVEN:
                View viewItem = inflater.inflate(R.layout.activity_select_main_type_item_1, parent, false);
                viewHolder = new MainTypeVH(viewItem);
                break;
            case VIEW_TYPE_ODD:
                View viewItem2 = inflater.inflate(R.layout.activity_select_main_type_item_2, parent, false);
                viewHolder = new MainTypeVH(viewItem2);
                break;
            case VIEW_TYPE_LOADING:
                View viewLoading = inflater.inflate(R.layout.view_pagination_progress, parent, false);
                viewHolder = new PaginationProgressVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final ItemModel maintype = items.get(position);
        switch (getItemViewType(position)){
            case VIEW_TYPE_EVEN:
            case VIEW_TYPE_ODD:
                final MainTypeVH mainTypeVH = (MainTypeVH) holder;
                mainTypeVH.tvMainTyperName.setText(maintype.getNumber());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        if(onClickPaginationItem!=null)
                            onClickPaginationItem.onClickItem(maintype);
                    }
                });
                break;
            case VIEW_TYPE_LOADING:
                final PaginationProgressVH paginationProgressVH = (PaginationProgressVH)holder;
                paginationProgressVH.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    private class MainTypeVH extends RecyclerView.ViewHolder{

        TextView tvMainTyperName;

        MainTypeVH(View itemView) {
            super(itemView);
            tvMainTyperName = itemView.findViewById(R.id.tv_main_type_name);
        }
    }
}
