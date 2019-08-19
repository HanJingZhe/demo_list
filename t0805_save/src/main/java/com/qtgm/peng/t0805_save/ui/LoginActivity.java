package com.qtgm.peng.t0805_save.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.qtgm.peng.t0805_save.R;
import com.qtgm.peng.t0805_save.adapter.MyTabAdapter;
import com.qtgm.peng.t0805_save.bean.UserBean;
import com.qtgm.peng.t0805_save.ui.fragment.LoginFragment;
import com.qtgm.peng.t0805_save.ui.fragment.RegistFragment;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.login_iv_bg)
    ImageView ivBg;
    @BindView(R.id.login_tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.login_view_page)
    ViewPager viewPage;

    private List<String> listTitle = Arrays.asList("登录", "注册");
    private List<Fragment> listFragment = Arrays.asList(new LoginFragment(), new RegistFragment());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        MyTabAdapter myTabAdapter = new MyTabAdapter(getSupportFragmentManager(), listFragment, listTitle);
        tabLayout.setupWithViewPager(viewPage);
        viewPage.setAdapter(myTabAdapter);

    }


    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe
    public void selectLoginTab(UserBean userBean) {
        TabLayout.Tab tabAt = tabLayout.getTabAt(0);
        if (tabAt != null) tabAt.select();
    }

}
