package com.incture.mobility.oms.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.mobility.oms.R;
import com.incture.mobility.oms.data.SampleDataProvider;
import com.incture.mobility.oms.models.Cart;
import com.incture.mobility.oms.models.Product;
import com.squareup.picasso.Picasso;

import java.util.Map;

import me.himanshusoni.quantityview.QuantityView;

/**
 * Created by satiswardash on 29/01/18.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    public interface CardAdapterListeners {
        void onItemRemoved(String id);
        void onQuantityChanged(String id, int quantity);
    }

    private Context mContext;
    private Map<String, Cart> mData;
    private CardAdapterListeners mListeners;

    public CartAdapter(Context mContext, Map<String, Cart> mData) {
        this.mContext = mContext;
        this.mData = mData;
        mListeners = (CardAdapterListeners) mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_cart_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public Map<String, Cart> getData() {
        return mData;
    }

    public void setData(Map<String, Cart> mData) {
        this.mData = mData;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView discount;
        TextView price;
        ImageView image;
        QuantityView quantity;
        ImageButton deleteItem;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.lc_item_name);
            description = itemView.findViewById(R.id.lc_item_description);
            discount = itemView.findViewById(R.id.lc_item_discount);
            price = itemView.findViewById(R.id.lc_item_price);
            image = itemView.findViewById(R.id.lc_item_image);
            quantity = itemView.findViewById(R.id.lc_item_quantity);
            deleteItem = itemView.findViewById(R.id.lc_remove_item);
        }

        public void bind(int position) {

            final String key = (String) SampleDataProvider.cartMap.keySet().toArray()[position];
            final Cart cartItem = SampleDataProvider.cartMap.get(key);
            Product product = cartItem.getProduct();

            title.setText(product.getName());
            description.setText(product.getShortDescription());
            discount.setText(product.getDiscount());
            price.setText(product.getPrice());
            Picasso.with(mContext).load(product.getImage()).into(image);
            quantity.setQuantity(cartItem.getQuantity());

            deleteItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListeners.onItemRemoved(key);
                }
            });

            quantity.setOnQuantityChangeListener(new QuantityView.OnQuantityChangeListener() {
                @Override
                public void onQuantityChanged(int oldQuantity, int newQuantity, boolean programmatically) {
                    mListeners.onQuantityChanged(key, newQuantity);
                }

                @Override
                public void onLimitReached() {

                }
            });

        }
    }
}
