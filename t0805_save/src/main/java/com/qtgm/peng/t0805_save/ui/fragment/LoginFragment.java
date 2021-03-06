package com.qtgm.peng.t0805_save.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qtgm.peng.t0805_save.R;
import com.qtgm.peng.t0805_save.bean.EventBase;
import com.qtgm.peng.t0805_save.sql.UserService;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class LoginFragment extends Fragment {

    @BindView(R.id.login_et_username)
    AppCompatEditText etUsername;
    @BindView(R.id.login_et_password)
    AppCompatEditText etPassword;
    Unbinder unbinder;
    @BindView(R.id.login_btn_sign)
    Button btnSign;
    @BindView(R.id.login_tv_to_reg)
    TextView tvToReg;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.login_btn_sign,R.id.login_tv_to_reg})
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_btn_sign:
                UserService userService = new UserService(getActivity());
                boolean login = userService.login(etUsername.getText().toString(), etPassword.getText().toString());
                if (login) {
                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast toast = Toast.makeText(getActivity(), "登录失败", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                }
                break;
            case R.id.login_tv_to_reg:
                EventBase eventBase = new EventBase(100, null);
                EventBus.getDefault().post(eventBase);
                break;
        }
    }

}