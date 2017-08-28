package com.jmarkstar.carlist.ui.cartype.pagination;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarkstar on 26/08/2017.
 */
public abstract class PaginationAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected final int VIEW_TYPE_EVEN = 0;
    protected final int VIEW_TYPE_ODD = 1;
    protected final int VIEW_TYPE_LOADING = 2;

    protected OnClickPaginationItem onClickPaginationItem;
    protected List<ItemModel> items;
    private boolean isLoadingAdded = false;

    public PaginationAdapter(){
        this.items = new ArrayList<>();
    }

    @Override public int getItemCount() {
        return items==null ? 0 : items.size();
    }

    @Override public int getItemViewType(int position) {
        if(position == items.size()-1 && isLoadingAdded)
            return VIEW_TYPE_LOADING;
        return position%2==0?VIEW_TYPE_EVEN:VIEW_TYPE_ODD;
    }

    public void addOnClickPaginationItem(OnClickPaginationItem onClick){
        onClickPaginationItem = onClick;
    }

    public void addItems(List<ItemModel> newItems){
        int currentLastPosition = this.items.size()-1;
        this.items.addAll(newItems);
        notifyItemRangeChanged(currentLastPosition, newItems.size()-1);
    }

    public void addLoadingFooter() {
        isLoadingAdded = true;
        items.add(new ItemModel());
        notifyItemInserted(items.size()-1);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        int position = items.size()-1;
        ItemModel item = getItem(position);
        if (item != null) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    public ItemModel getItem(int position) {
        return items.get(position);
    }

    public class PaginationProgressVH extends RecyclerView.ViewHolder{
        public ProgressBar progressBar;

        public PaginationProgressVH(View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.pg_pagination_progress);
        }
    }

    public interface OnClickPaginationItem{
        void onClickItem(ItemModel model);
    }
}
