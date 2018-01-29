package com.incture.mobility.oms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.mobility.oms.models.Product;
import com.squareup.picasso.Picasso;

public class ProductDetailsActivity extends AppCompatActivity {

    private Product mProduct;
    private ImageView mProductImage;
    private TextView mProductName;
    private TextView mProductDescription;
    private TextView mProductPrice;
    private TextView mProductDiscount;
    private TextView mProductDetails;
    private ImageButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getBundleExtra("bundle");
        if (bundle.containsKey("item")) {
            mProduct = (Product) bundle.get("item");
        }
        initLayout();
        bindData();
    }

    private void initLayout() {
        setContentView(R.layout.activity_product_details);
        mProductImage = findViewById(R.id.pdp_app_bar_image);
        mProductName = findViewById(R.id.pdp_item_name);
        mProductDescription = findViewById(R.id.pdp_item_description);
        mProductPrice = findViewById(R.id.pdp_item_price);
        mProductDiscount = findViewById(R.id.pdp_item_discount);
        mProductDetails = findViewById(R.id.pdp_item_details);
        cart = findViewById(R.id.pdp_cart_menu_icon);
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });
    }

    private void bindData() {
        Picasso.with(getApplicationContext()).load(mProduct.getImage())
                .placeholder(getResources().getDrawable(R.drawable.ic_launcher_background))
                .into(mProductImage);
        mProductName.setText(mProduct.getName());
        mProductDescription.setText(mProduct.getShortDescription());
        mProductPrice.setText(mProduct.getPrice());
        mProductDiscount.setText(mProduct.getDiscount());
        mProductDetails.setText(mProduct.getDescription());
    }

    public void onBackButtonPressed(View view) {
        onBackPressed();
    }

    public void addToCart(View view) {
        onBackPressed();
    }

    public void checkout(View view) {
        onBackPressed();
    }
}
