package com.incture.mobility.oms.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.incture.mobility.oms.fragments.BannerFragment;
import com.incture.mobility.oms.models.PromotionBanner;

import java.util.List;

/**
 * Created by satiswardash on 24/01/18.
 */

public class BannerPagerAdapter extends FragmentPagerAdapter {

    private List<PromotionBanner> mBanners;

    public BannerPagerAdapter(FragmentManager fm, List<PromotionBanner> bannerList) {
        super(fm);
        mBanners = bannerList;
    }

    @Override
    public Fragment getItem(int position) {

        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        BannerFragment fragment = new BannerFragment();
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public int getCount() {
        return mBanners.size();
    }

    public List<PromotionBanner> getmBanners() {
        return mBanners;
    }

    public void setmBanners(List<PromotionBanner> mBanners) {
        this.mBanners = mBanners;
    }
}
