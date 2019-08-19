package com.qtgm.peng.t0805_save.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.qtgm.peng.t0805_save.R;
import com.qtgm.peng.t0805_save.bean.UserBean;
import com.qtgm.peng.t0805_save.sql.UserService;
import com.qtgm.peng.t0805_save.ui.LoginActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegistFragment extends Fragment {

    @BindView(R.id.register_et_username)
    EditText etUsername;
    @BindView(R.id.register_et_password)
    EditText etPassword;
    @BindView(R.id.register_et_age)
    EditText etAge;
    @BindView(R.id.registerer_et_sex)
    EditText etSex;
    @BindView(R.id.register_btn)
    Button btnRegist;
    Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_regist, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.register_btn)
    public void onClick() {
        EventBus.getDefault().post(new UserBean());
        //regist();
    }

    private void regist() {
        UserService userService = new UserService(getActivity());
        UserBean userBean = new UserBean(edit2String(etUsername), edit2String(etPassword), Integer.parseInt(edit2String(etAge)), edit2String(etSex));
        userService.register(userBean);
    }

    private String edit2String(EditText et) {
        return et.getText().toString();
    }

}
