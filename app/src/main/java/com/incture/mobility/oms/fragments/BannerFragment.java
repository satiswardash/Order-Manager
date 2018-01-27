package com.incture.mobility.oms.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.incture.mobility.oms.R;
import com.incture.mobility.oms.data.SampleDataProvider;
import com.incture.mobility.oms.models.PromotionBanner;
import com.squareup.picasso.Picasso;

/**
 * Created by satiswardash on 24/01/18.
 */

public class BannerFragment extends Fragment {

    private PromotionBanner mBanner;
    private SampleDataProvider dataProvider = new SampleDataProvider();

    public BannerFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            int position = bundle.getInt("position");
            mBanner = dataProvider.getPromotionBanners().get(position);
        } else
            mBanner = new PromotionBanner();

        View view = inflater.inflate(R.layout.layout_banner, container, false);
        return bindViewElements(view);
    }

    private View bindViewElements(View view) {

        ImageView bImage = view.findViewById(R.id.banner_image);
        TextView bTitle = view.findViewById(R.id.banner_title);
        TextView bDescr = view.findViewById(R.id.banner_description);
        Button bAction = view.findViewById(R.id.banner_action);


        Picasso.with(getContext()).load(mBanner.getImage()).resize(360, 200).onlyScaleDown().placeholder(getResources().getDrawable(R.drawable.ic_launcher_background)).into(bImage);
        bTitle.setText(mBanner.getTitle());
        bDescr.setText(mBanner.getDescription());
        bAction.setText(mBanner.getAction());

        return view;
    }
}
