package com.incture.mobility.oms.fragments;


import android.content.Context;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.incture.mobility.oms.MainActivity;
import com.incture.mobility.oms.R;
import com.incture.mobility.oms.adapters.BannerPagerAdapter;
import com.incture.mobility.oms.data.SampleDataProvider;
import com.incture.mobility.oms.models.PromotionBanner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {

    private TextView title;
    private ImageButton backNav;
    private FloatingActionButton fab;

    private ViewPager mBanner;
    private BannerPagerAdapter mBannerAdapter;
    private MainActivity mActivity;

    /**
     * Default constructor (REQUIRED)
     */
    public DashboardFragment() {
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
     * Here we are inflating the {@link DashboardFragment} layout and initializing its view components
     * Bind the view pager adapter with an empty {@link ArrayList<PromotionBanner>}
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
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        initDashboard(view);
        bindBannerAdapter();
        return view;
    }

    /**
     * Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped,
     * but is now again being displayed to the user.
     * <p>
     * Here we are fetching the sample banner feeds from {@link SampleDataProvider}
     */
    @Override
    public void onStart() {
        super.onStart();
        getBannerFeeds();
    }

    /**
     * Initialize host layout view components
     * Title,
     * ToolBar primary icon (Logout)
     * ToolBar secondary icon (Cart)
     */
    private void initLayout() {
        title = mActivity.findViewById(R.id.frame_title);
        backNav = mActivity.findViewById(R.id.logout_menu_icon);
        fab = mActivity.findViewById(R.id.create_order_fab);
    }

    /**
     * Initialize the Dashboard Layout view components
     *
     * @param view
     */
    private void initDashboard(View view) {
        mBanner = view.findViewById(R.id.dashboard_view_pager);
        mBanner.setClipToPadding(false);
        mBanner.setPadding(0, 0, 96, 0);
        mBanner.setPageMargin(32);

        title.setText(R.string.dashboard);
        backNav.setBackground(mActivity.getResources().getDrawable(R.drawable.ic_logout_black));
        backNav.setBackgroundTintList(ColorStateList.valueOf(mActivity.getResources().getColor(R.color.colorWhite)));
        backNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO
            }
        });

        fab.show();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction transaction = mActivity.getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.root_frame_layout, new CategoryFragment());
                transaction.addToBackStack(CategoryFragment.class.toString());
                transaction.commit();
            }
        });
    }

    /**
     * Bind the banner pager adapter using {@link android.support.v4.app.FragmentManager}
     */
    private void bindBannerAdapter() {
        mBannerAdapter = new BannerPagerAdapter(getChildFragmentManager(), new ArrayList<PromotionBanner>());
        mBanner.setAdapter(mBannerAdapter);
    }

    /**
     * Get the banner feed from {@link SampleDataProvider}
     */
    private void getBannerFeeds() {
        //TODO Replace with the Network call post checking of Network availability Status using Network Utility
        SampleDataProvider dataProvider = new SampleDataProvider();
        mBannerAdapter.setmBanners(dataProvider.getPromotionBanners());
        mBannerAdapter.notifyDataSetChanged();
    }
}
