package com.incture.mobility.oms.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.incture.mobility.oms.R;
import com.incture.mobility.oms.models.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by satiswardash on 27/01/18.
 */

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.ViewHolder> {

    public interface ProductListAdapterListeners {
        void onItemClicked(Product product);
    }

    private Context mContext;
    private List<Product> mFeeds;
    private ProductListAdapterListeners mListeners;

    public ProductListAdapter(Context mContext, List<Product> mFeeds, Fragment fragment) {
        this.mContext = mContext;
        this.mFeeds = mFeeds;
        mListeners = (ProductListAdapterListeners) fragment;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mFeeds.size();
    }

    public List<Product> getData() {
        return mFeeds;
    }

    public void setData(List<Product> mFeeds) {
        this.mFeeds = mFeeds;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView price;
        TextView discount;
        ImageView productImage;
        QuantityView quantityView;
        CardView layout;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lp_item_name);
            description = itemView.findViewById(R.id.lp_item_description);
            price = itemView.findViewById(R.id.lp_item_price);
            discount = itemView.findViewById(R.id.lp_item_discount);
            productImage = itemView.findViewById(R.id.lp_item_image);
            quantityView = itemView.findViewById(R.id.lp_item_quantity);
            layout = itemView.findViewById(R.id.plp_item_root);
        }

        public void bind(final int position) {
            Product product = mFeeds.get(position);

            title.setText(product.getName());
            description.setText(product.getShortDescription());
            price.setText(product.getPrice());
            discount.setText(product.getDiscount());
            Picasso.with(mContext).load(product.getImage())//.resize(400, 800)
                    .placeholder(mContext.getResources().getDrawable(R.drawable.ic_launcher_background))
                    .into(productImage);

            quantityView.setOnQuantityChangeListener(new QuantityView.OnQuantityChangeListener() {
                @Override
                public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
                    //TODO
                }

                @Override
                public void onLimitReached() {

                }
            });

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListeners.onItemClicked(mFeeds.get(position));
                }
            });
        }
    }
}
