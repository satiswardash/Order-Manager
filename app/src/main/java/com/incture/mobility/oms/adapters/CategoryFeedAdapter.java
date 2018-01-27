package com.incture.mobility.oms.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.incture.mobility.oms.R;

import java.util.List;

/**
 * Created by satiswardash on 27/01/18.
 */

public class CategoryFeedAdapter extends RecyclerView.Adapter<CategoryFeedAdapter.ViewHolder> {

    private Context mContext;
    private List<Object> mFeeds;
    private CategoryAdapterListener mListener;
    public CategoryFeedAdapter(Context mContext, List<Object> mFeeds, Fragment fragment) {
        this.mContext = mContext;
        this.mFeeds = mFeeds;
        mListener = (CategoryAdapterListener) fragment;
    }

    @Override
    public CategoryFeedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryFeedAdapter.ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mFeeds.size();
    }

    public List<Object> getData() {
        return mFeeds;
    }

    public void setData(List<Object> mFeeds) {
        this.mFeeds = mFeeds;
    }

    public interface CategoryAdapterListener {
        void onDetailButtonPressed(String id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageButton details;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.category_title);
            details = itemView.findViewById(R.id.category_details_button);
        }

        public void bind(final int position) {
            title.setText(mFeeds.get(position).toString());
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onDetailButtonPressed(mFeeds.get(position).toString());
                }
            });
        }
    }
}
