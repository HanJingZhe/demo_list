package com.qtgm.peng.t0826_tab;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.List;

/**
 * @author peng_wang
 * @date 2019/8/26
 */
public class MyAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> listFragment;
    private List<String> listTitle;

    public MyAdapter(FragmentManager fm, List<Fragment> listFragment, List<String> listTitle) {
        super(fm);
        this.listFragment = listFragment;
        this.listTitle = listTitle;
    }

    @Override
    public Fragment getItem(int i) {
        return listFragment.get(i);
    }

    @Override
    public int getCount() {
        return listFragment == null ? 0 : listFragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return listTitle == null ? "" : listTitle.get(position);
    }

}