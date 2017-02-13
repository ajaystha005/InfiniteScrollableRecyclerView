package com.recylerview.sample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by ajayshrestha on 2/13/17.
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.SampleViewHolder> {

    private Context mContext;
    private ArrayList<String> demoList = new ArrayList<>();


    public DemoAdapter(Context mContext, ArrayList<String> demoList) {
        this.mContext = mContext;
        this.demoList = demoList;
    }

    @Override
    public SampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_demo_item, null);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SampleViewHolder holder, int position) {
        holder.mTextView.setText(demoList.get(position));
    }

    @Override
    public int getItemCount() {
        return demoList.size();
    }

    public class SampleViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;

        public SampleViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.item);
        }
    }
}
