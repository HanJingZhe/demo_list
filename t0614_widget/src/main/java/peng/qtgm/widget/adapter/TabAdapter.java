package peng.qtgm.widget.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import peng.qtgm.widget.ui.fragment.Fragment1;

/**
 * @author peng_wang
 * @date 2019/6/14
 */
public class TabAdapter extends FragmentPagerAdapter {

    private List<View> views;
    private String[] strTitle = {"首页", "藥店", "藥材", "我的"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return new Fragment1();
    }

    @Override
    public int getCount() {
        return strTitle.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return strTitle[position];
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        container.addView(views.get(position));
        return views.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

}
