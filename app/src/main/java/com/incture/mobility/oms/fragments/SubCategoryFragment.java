package com.incture.mobility.oms.fragments;


import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.incture.mobility.oms.MainActivity;
import com.incture.mobility.oms.ProductListActivity;
import com.incture.mobility.oms.R;
import com.incture.mobility.oms.adapters.SubCategoryFeedAdapter;
import com.incture.mobility.oms.data.SampleDataProvider;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class SubCategoryFragment extends Fragment
        implements SubCategoryFeedAdapter.SubCategoryAdapterListeners {

    private TextView title;
    private ImageButton backNav;
    private FloatingActionButton fab;

    private MainActivity mActivity;
    private RecyclerView mRecyclerView;
    private SubCategoryFeedAdapter mAdapter;

    private String key;

    /**
     * Default constructor (REQUIRED)
     */
    public SubCategoryFragment() {
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
     * Here we are inflating the {@link SubCategoryFragment} layout and initializing its view components
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
        key = getArguments().getString("key");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_sub_category, container, false);
        initSubCategory(view);
        bindSubCategoryAdapter();
        return view;
    }

    /**
     * Called after onCreate(Bundle) — or after onRestart() when the activity had been stopped,
     * but is now again being displayed to the user.
     * <p>
     * Here we are fetching the Subcategory list by passing the category id and displaying the
     * Title and sun category items count in the ToolBar title section
     * <p>
     */
    @Override
    public void onStart() {
        super.onStart();
        Map<String, Integer> m = fetchSubCategoryList(key);
        String key = m.keySet().toArray()[0].toString();
        title.setText(key + " (" + m.get(key) + ")");
    }

    /**
     * Initialize the host activity view components
     */
    private void initLayout() {
        title = mActivity.findViewById(R.id.frame_title);
        backNav = mActivity.findViewById(R.id.logout_menu_icon);
        fab = mActivity.findViewById(R.id.create_order_fab);
    }

    /**
     * Initialize the {@link SubCategoryFragment} view components
     *
     * @param view
     */
    private void initSubCategory(View view) {
        mRecyclerView = view.findViewById(R.id.sub_category_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

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
     * Bind the Sub-Category adapter with an empty {@link HashMap<String, String[]>} object
     */
    private void bindSubCategoryAdapter() {
        mAdapter = new SubCategoryFeedAdapter(mActivity, new HashMap<String, String[]>(), this);
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Get the Sub Category list from the {@link SampleDataProvider} by passing the category Id
     * After fetching the Sub Category {@link String[]}, get the Key for value category Id from the parameter
     * <p>
     * Construct a {@link Map<String, Integer>} which will hold the category title and sub category counts
     *
     * @param id
     * @return
     */
    private Map<String, Integer> fetchSubCategoryList(String id) {
        //TODO Replace with the Network call post checking of Network availability Status using Network Utility
        SampleDataProvider dataProvider = new SampleDataProvider();
        Map<String, String[]> feeds = dataProvider.getSubCategory(id);
        mAdapter.setmFeeds(feeds);
        mAdapter.notifyDataSetChanged();

        Map<String, Integer> result = new HashMap<>();
        String title = "Sub Category";
        for (String key :
                dataProvider.getCategoryFeeds().keySet()) {

            if (id.equals(dataProvider.getCategoryFeeds().get(key))) {
                title = key;
                break;
            }
        }
        result.put(title, feeds.get(feeds.keySet().toArray()[0]).length);
        return result;
    }

    /**
     * On Sub Category item clicked event handler
     * Navigate to Item listing activity
     *
     * @param id
     */
    @Override
    public void onItemClick(String id) {
        //TODO Pass the categoryId, subCategoryId using Bundle to  the PLP activity to fetch the respective items in it.
        Intent plpIntent = new Intent(getContext(), ProductListActivity.class);
        mActivity.startActivity(plpIntent);
    }
}
