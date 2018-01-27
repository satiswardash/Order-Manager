package com.incture.mobility.oms.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.incture.mobility.oms.ProductDetailsActivity;
import com.incture.mobility.oms.ProductListActivity;
import com.incture.mobility.oms.R;
import com.incture.mobility.oms.adapters.ProductListAdapter;
import com.incture.mobility.oms.data.SampleDataProvider;
import com.incture.mobility.oms.models.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ProductListFragment extends Fragment
        implements ProductListAdapter.ProductListAdapterListeners{

    private TextView title;
    private ImageButton backNav;
    private ImageButton cartButton;
    private FloatingActionButton fab;

    private ProductListActivity mActivity;
    private ProductListAdapter mAdapter;
    private RecyclerView mProductRecyclerView;

    /**
     * Default constructor REQUIRED
     */
    public ProductListFragment() {
    }

    /**
     * Called when a fragment is first attached to its context.
     * <p>
     * Here we are initializing the view components attached with the host activity and
     * This is the method where you can save the Host Activity instance for the first time before the fragment inflates the layout
     *
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity = (ProductListActivity) getActivity();
        initLayout();
    }

    /**
     * Called to have the fragment instantiate its user interface view. This is optional,
     * and non-graphical fragments can return null (which is the default implementation).
     * This will be called between onCreate(Bundle) and onActivityCreated(Bundle).
     * <p>
     * Here we are inflating the {@link ProductListFragment} layout and initializing its view components
     * which will automatically load the incoming feeds.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_product_list, container, false);
        initProductFragment(view);
        bindProductsAdapter();
        return view;
    }

    /**
     * Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped,
     * but is now again being displayed to the user.
     * <p>
     * Here we are fetching the products feed for a Subcategory id by passing the category id and sub category id and displaying the
     * Title and sun category items count in the ToolBar title section
     * <p>
     */
    @Override
    public void onStart() {
        super.onStart();
        fetchProducts(null);
    }

    /**
     * Initialize the host activity layout view components
     */
    private void initLayout() {
        title = mActivity.findViewById(R.id.plp_frame_title);
        backNav = mActivity.findViewById(R.id.plp_menu_icon);
        fab = mActivity.findViewById(R.id.plp_add_to_cart_fab);
        cartButton = mActivity.findViewById(R.id.plp_cart_menu_icon);
    }

    /**
     * Initialize the {@link ProductListFragment} view components
     *
     * @param view
     */
    private void initProductFragment(View view) {
        mProductRecyclerView = view.findViewById(R.id.plp_recycler_view);
        mProductRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActivity.onBackPressed();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });
    }

    /**
     * Bind the {@link ProductListAdapter} adapter with an empty {@link List<Product>} object
     */
    private void bindProductsAdapter() {
        mAdapter = new ProductListAdapter(getContext(), new ArrayList<Product>(), this);
        mProductRecyclerView.setAdapter(mAdapter);
        mProductRecyclerView.setNestedScrollingEnabled(true);
    }

    /**
     * Fetch the product feeds from {@link SampleDataProvider}
     *
     * @param id
     */
    private void fetchProducts(String id) {
        //TODO Replace with the Network call post checking of Network availability Status using Network Utility
        SampleDataProvider provider = new SampleDataProvider();
        List<Product> feeds = provider.getProductsFeed();
        mAdapter.setData(feeds);
        mAdapter.notifyDataSetChanged();

        title.setText("Products ("+mAdapter.getData().size()+")");
    }

    /**
     * On product clicked event handler
     * Navigate to {@link com.incture.mobility.oms.ProductDetailsActivity} to show the product details
     *
     * Here we are passing the Parcelable {@link Product} object to the PDP activity using Bundle object
     *
     * @param product
     */
    @Override
    public void onItemClicked(Product product) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", product);

        Intent pdpIntent = new Intent(getContext(), ProductDetailsActivity.class);
        pdpIntent.putExtra("bundle", bundle);
        mActivity.startActivity(pdpIntent);
    }
}
