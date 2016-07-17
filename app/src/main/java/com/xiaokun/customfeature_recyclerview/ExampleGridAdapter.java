package com.xiaokun.customfeature_recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiaokun.divider.SelectedGridAdapter;

import java.util.List;

/**
 * Created by panwankun on 16/7/16.
 */
public class ExampleGridAdapter extends SelectedGridAdapter<String, ExampleGridAdapter.ViewHolder> {

    public ExampleGridAdapter(Context context, List<String> dataSet) {
        super(context, dataSet);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBind(ViewHolder holder, int position) {
        holder.text.setText(mDataSet.get(position));
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView image;
        public TextView text;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            text = (TextView) itemView.findViewById(R.id.text);
        }
    }
}