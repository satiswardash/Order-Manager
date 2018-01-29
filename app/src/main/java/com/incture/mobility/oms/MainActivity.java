package com.incture.mobility.oms;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.incture.mobility.oms.fragments.CategoryFragment;
import com.incture.mobility.oms.fragments.DashboardFragment;

public class MainActivity extends AppCompatActivity {

    private ImageButton cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cart = findViewById(R.id.cart_menu_icon);

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CartActivity.class);
                startActivity(intent);
            }
        });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.root_frame_layout, new DashboardFragment());
        transaction.commit();
    }

    public void showCart(View view) {
        Intent cartIntent = new Intent(this, CartActivity.class);
        startActivity(cartIntent);
    }
}
