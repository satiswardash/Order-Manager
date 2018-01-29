package com.incture.mobility.oms;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.incture.mobility.oms.adapters.CartAdapter;
import com.incture.mobility.oms.data.SampleDataProvider;
import com.incture.mobility.oms.models.Product;

public class CartActivity extends AppCompatActivity
        implements CartAdapter.CardAdapterListeners{

    private TextView message;
    private TextView frameTitle;

    private RecyclerView cartRecyclerView;
    private CartAdapter adapter;

    private Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            TextView totalAmount = findViewById(R.id.ac_total);
            float amount = msg.getData().getFloat("total");
            totalAmount.setText("$ "+amount+" Total");
        }
    };

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initLayout();
    }

    /**
     * Initialize the {@link CartActivity} layout view component
     * if the cart is empty then display Empty Cart message or else
     * populate the cart items using {@link android.support.v7.widget.RecyclerView}
     */
    private void initLayout() {
        setContentView(R.layout.activity_cart);
        message = findViewById(R.id.ac_message);
        cartRecyclerView = findViewById(R.id.ac_recycler_view);
        frameTitle = findViewById(R.id.ac_frame_title);

        if (SampleDataProvider.cartMap.isEmpty()) {
            message.setVisibility(View.VISIBLE);
            cartRecyclerView.setVisibility(View.INVISIBLE);
            frameTitle.setText("Cart");
        } else {
            message.setVisibility(View.INVISIBLE);
            cartRecyclerView.setVisibility(View.VISIBLE);
            frameTitle.setText("Cart ("+SampleDataProvider.cartMap.size()+")");
            bindCartAdapter();
            calculateTotal();
        }
    }

    /**
     * Bind the cart adapter by fetching the cart items from {@link SampleDataProvider}
     * //TODO Replace with the Network Call for fetching the cart items by passing the userID,
     * post checking the Network Availability
     */
    private void bindCartAdapter() {
        cartRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        cartRecyclerView.setNestedScrollingEnabled(true);
        adapter = new CartAdapter(this, SampleDataProvider.cartMap);
        cartRecyclerView.setAdapter(adapter);
    }

    public void navigateBack(View view) {
        onBackPressed();
    }

    @Override
    public void onItemRemoved(String id) {
        adapter.getData().remove(id);
        adapter.notifyDataSetChanged();
        SampleDataProvider.cartMap.remove(id);
        calculateTotal();
        frameTitle.setText("Cart ("+SampleDataProvider.cartMap.size()+")");
    }

    @Override
    public void onQuantityChanged(String id, int quantity) {
        SampleDataProvider.cartMap.get(id).setQuantity(quantity);
        calculateTotal();
    }

    private void calculateTotal() {
        final float[] i = {0};
        mHandler.post(new Runnable() {
            @Override
            public void run() {
                for (String key :
                        SampleDataProvider.cartMap.keySet()) {

                    Product product = SampleDataProvider.cartMap.get(key).getProduct();
                    i[0] = i[0] + Float.parseFloat(product.getPrice())*SampleDataProvider.cartMap.get(key).getQuantity();
                }
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putFloat("total", i[0]);
                message.setData(bundle);
                mHandler.sendMessage(message);
            }
        });
    }
}
