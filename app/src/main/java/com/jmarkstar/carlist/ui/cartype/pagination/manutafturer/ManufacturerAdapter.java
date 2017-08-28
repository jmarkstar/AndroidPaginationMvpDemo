package com.jmarkstar.carlist.ui.cartype.pagination.manutafturer;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist.ui.cartype.pagination.PaginationAdapter;
import com.jmarkstar.carlist_core.domain.model.ItemModel;

/**
 * Created by jmarkstar on 26/08/2017.
 */
class ManufacturerAdapter extends PaginationAdapter {

    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case VIEW_TYPE_EVEN:
                View viewItem = inflater.inflate(R.layout.activity_select_manufacturer_item_1, parent, false);
                viewHolder = new ManufacturerVH(viewItem);
                break;
            case VIEW_TYPE_ODD:
                View viewItem2 = inflater.inflate(R.layout.activity_select_manufacturer_item_2, parent, false);
                viewHolder = new ManufacturerVH(viewItem2);
                break;
            case VIEW_TYPE_LOADING:
                View viewLoading = inflater.inflate(R.layout.view_pagination_progress, parent, false);
                viewHolder = new PaginationProgressVH(viewLoading);
                break;
        }
        return viewHolder;
    }

    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final ItemModel manufacturer = items.get(position);
        switch (getItemViewType(position)){
            case VIEW_TYPE_EVEN:
            case VIEW_TYPE_ODD:
                final ManufacturerVH manufacturerVH = (ManufacturerVH) holder;
                manufacturerVH.tvManufacturerNumber.setText(manufacturer.getNumber());
                manufacturerVH.tvManufacturerName.setText(manufacturer.getName());
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View view) {
                        if(onClickPaginationItem!=null)
                            onClickPaginationItem.onClickItem(manufacturer);
                    }
                });
                break;
            case VIEW_TYPE_LOADING:
                final PaginationProgressVH paginationProgressVH = (PaginationProgressVH)holder;
                paginationProgressVH.progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    private class ManufacturerVH extends RecyclerView.ViewHolder{
        TextView tvManufacturerNumber;
        TextView tvManufacturerName;

        ManufacturerVH(View itemView) {
            super(itemView);
            tvManufacturerNumber = itemView.findViewById(R.id.tv_manufacturer_number);
            tvManufacturerName = itemView.findViewById(R.id.tv_manufacturer_name);
        }
    }
}
