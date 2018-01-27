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

import java.util.Map;

/**
 * Created by satiswardash on 27/01/18.
 */

public class SubCategoryFeedAdapter extends RecyclerView.Adapter<SubCategoryFeedAdapter.ViewHolder> {

    private Context mContext;
    private Map<String, String[]> mFeeds;
    private SubCategoryAdapterListeners mListeners;
    public SubCategoryFeedAdapter(Context mContext, Map<String, String[]> feeds, Fragment fragment) {
        this.mContext = mContext;
        this.mFeeds = feeds;
        this.mListeners = (SubCategoryAdapterListeners) fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_sub_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        String s = (String) mFeeds.keySet().toArray()[0];
        return mFeeds.get(s).length;
    }

    public Map<String, String[]> getmFeeds() {
        return mFeeds;
    }

    public void setmFeeds(Map<String, String[]> mFeeds) {
        this.mFeeds = mFeeds;
    }

    public interface SubCategoryAdapterListeners {

        void onItemClick(String id);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageButton details;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.sub_category_title);
            details = itemView.findViewById(R.id.view_sub_category_button);
        }

        public void bind(int position) {
            String s = (String) mFeeds.keySet().toArray()[0];
            title.setText(mFeeds.get(s)[position]);
            details.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListeners.onItemClick(mFeeds.keySet().toArray()[0].toString());
                }
            });
        }
    }
}
