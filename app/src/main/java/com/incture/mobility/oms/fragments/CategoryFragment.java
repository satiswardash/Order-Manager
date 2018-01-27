package com.incture.mobility.oms.fragments;


import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.incture.mobility.oms.MainActivity;
import com.incture.mobility.oms.R;
import com.incture.mobility.oms.adapters.CategoryFeedAdapter;
import com.incture.mobility.oms.data.SampleDataProvider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CategoryFragment extends Fragment
        implements CategoryFeedAdapter.CategoryAdapterListener {

    private TextView title;
    private ImageButton backNav;
    private FloatingActionButton fab;

    private MainActivity mActivity;
    private RecyclerView mRecyclerView;
    private CategoryFeedAdapter mAdapter;


    /**
     * Default constructor (REQUIRED)
     */
    public CategoryFragment() {
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
        mActivity = (MainActivity) getActivity();
        initLayout();
    }

    /**
     * Called to have the fragment instantiate its user interface view. This is optional,
     * and non-graphical fragments can return null (which is the default implementation).
     * This will be called between onCreate(Bundle) and onActivityCreated(Bundle).
     * <p>
     * Here we are inflating the {@link CategoryFragment} layout and initializing its view components
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        initCategory(view);
        bindCategoryAdapter();
        return view;
    }

    /**
     * Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped,
     * but is now again being displayed to the user.
     * <p>
     */
    @Override
    public void onStart() {
        super.onStart();
        getCategoryFeeds();
    }

    /**
     * Initialize host layout view components
     * Title,
     * ToolBar primary icon (Logout)
     * ToolBar secondary icon (Cart)
     * Show the floating action button to create New Order
     */
    private void initLayout() {
        title = mActivity.findViewById(R.id.frame_title);
        backNav = mActivity.findViewById(R.id.logout_menu_icon);
        fab = mActivity.findViewById(R.id.create_order_fab);
    }

    /**
     * Initialize the Category Layout components
     *
     * @param view
     */
    private void initCategory(View view) {
        mRecyclerView = view.findViewById(R.id.category_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

        title.setText(R.string.category);
        backNav.setBackground(mActivity.getResources().getDrawable(R.drawable.ic_arrow_back_black));
        backNav.setBackgroundTintList(ColorStateList.valueOf(mActivity.getResources().getColor(R.color.colorWhite)));
        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Navigate to the immediate back stack fragment entry
                mActivity.getSupportFragmentManager().popBackStackImmediate();
            }
        });

        fab.hide();
    }

    /**
     * Bind the category adapter
     */
    private void bindCategoryAdapter() {
        mAdapter = new CategoryFeedAdapter(mActivity, new ArrayList<Object>(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Get the Category feeds from network
     */
    private void getCategoryFeeds() {
        //TODO Replace with the Network call post checking of Network availability Status using Network Utility
        SampleDataProvider dataProvider = new SampleDataProvider();
        List<Object> feeds = Arrays.asList(dataProvider.getCategoryFeeds().keySet().toArray());
        mAdapter.setData(feeds);
        mAdapter.notifyDataSetChanged();
    }

    /**
     * on detail button pressed event handler
     * Navigate to {@link SubCategoryFragment} to list the available Sub Categories under given Category Id
     *
     * @param id
     */
    @Override
    public void onDetailButtonPressed(String id) {
        SampleDataProvider dataProvider = new SampleDataProvider();
        Bundle bundle = new Bundle();
        bundle.putString("key", dataProvider.getCategoryFeeds().get(id));

        FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();

        SubCategoryFragment fragment = new SubCategoryFragment();
        fragment.setArguments(bundle);

        transaction.replace(R.id.root_frame_layout, fragment);
        transaction.addToBackStack(SubCategoryFragment.class.toString());
        transaction.commit();
    }
}
