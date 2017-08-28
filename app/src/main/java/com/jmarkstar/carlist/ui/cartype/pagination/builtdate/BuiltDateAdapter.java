package com.jmarkstar.carlist.ui.cartype.pagination.builtdate;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.jmarkstar.carlist.R;
import com.jmarkstar.carlist_core.domain.model.ItemModel;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jmarkstar on 27/08/2017.
 */

public class BuiltDateAdapter extends RecyclerView.Adapter<BuiltDateAdapter.BuiltDateVH>{

    private List<ItemModel> builtDates;
    private Context context;
    private OnClickItemListener listener;

    public BuiltDateAdapter(Context context){
        builtDates = new ArrayList<>();
        this.context = context;
    }

    public void addOnClickItemListener(OnClickItemListener listener){
        this.listener = listener;
    }

    public void addList(List<ItemModel> builtDates){
        this.builtDates.addAll(builtDates);
        notifyDataSetChanged();
    }

    @Override public BuiltDateVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_select_built_date_item, parent, false);
        return new BuiltDateVH(view);
    }

    @Override public void onBindViewHolder(BuiltDateVH holder, final int position) {
        final ItemModel item = builtDates.get(position);
        if(position%2==0){
            holder.llRoot.setBackgroundColor(ContextCompat.getColor(context, R.color.builtdate1_bg_color));
        }else{
            holder.llRoot.setBackgroundColor(ContextCompat.getColor(context, R.color.builtdate2_bg_color));
        }
        holder.tvBuiltDate.setText(item.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
                if(listener!=null)
                    listener.onClickItem(item);
            }
        });
    }

    @Override public int getItemCount() {
        return builtDates==null ? 0 : builtDates.size();
    }

    class BuiltDateVH extends RecyclerView.ViewHolder{

        TextView tvBuiltDate;
        LinearLayout llRoot;

        BuiltDateVH(View itemView) {
            super(itemView);
            llRoot = itemView.findViewById(R.id.ll_root);
            tvBuiltDate = itemView.findViewById(R.id.tv_built_date);
        }
    }

    public interface OnClickItemListener{
        void onClickItem(ItemModel item);
    }
}
