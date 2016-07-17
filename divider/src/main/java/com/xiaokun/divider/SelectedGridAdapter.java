package com.xiaokun.divider;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by panwankun on 16/7/16.
 */
public abstract class SelectedGridAdapter<T,V extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<V>{
    protected Context mContext;
    protected List<T> mDataSet = new ArrayList<>();
    protected List<Boolean> mSelectedSet = new ArrayList<>();
    public SelectedGridAdapter(Context context, List<T> dataSet){
        this.mContext = context;
        putData(dataSet);
    }

    @Override
    public void onBindViewHolder(final V holder, final int position) {
        holder.itemView.setTag(R.id.recycler_itemview_position, position);
        holder.itemView.setTag(R.id.recycler_itemview_selected,mSelectedSet.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectedSet.set(holder.getAdapterPosition(), !mSelectedSet.get(holder.getAdapterPosition()));
                notifyDataSetChanged();
            }
        });
        onBind(holder,position);
    }

    public abstract void onBind(V holder, int position);
    @Override
    public int getItemCount() {
        return mDataSet.size();
    }

    public void putData(List<T> dataSet) {
        if (dataSet == null) return;
        mDataSet.clear();
        mDataSet.addAll(dataSet);
        mSelectedSet.clear();
        for (int i = 0; i < mDataSet.size(); i++) {
            mSelectedSet.add(false);
        }
        notifyDataSetChanged();
    }

    public List<T> getSelectedDataSet(){
        List<T> dataSet = new ArrayList<>();
        for (int i = 0; i < mSelectedSet.size(); i++) {
            if (mSelectedSet.get(i)) {
                dataSet.add(mDataSet.get(i));
            }
        }
        return dataSet;
    }
}
