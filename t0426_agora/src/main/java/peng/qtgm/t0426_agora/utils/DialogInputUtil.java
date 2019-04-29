package peng.qtgm.t0426_agora.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import peng.qtgm.t0426_agora.R;

import static peng.qtgm.t0426_agora.app.MyApplication.WP;


/**
 * @author peng_wang
 * @date 2019/4/28
 */
public class DialogInputUtil extends Dialog implements View.OnClickListener {

    private Context mContext;

    private TextView tvTitle;
    private TextView tvDes;
    private EditText etInput;
    private Button btnCancal;
    private Button btnCommit;

    private InputBuilder builder;

    public DialogInputUtil(InputBuilder builder) {
        super(builder.context);
        this.builder = builder;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_util_input);

        //设置window 展示大小
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        params.width = (int) (displayMetrics.widthPixels * 0.8);
        params.gravity = Gravity.CENTER;
        window.setAttributes(params);

        //初始化控件
        tvTitle = findViewById(R.id.dialog_tv_title);
        tvDes = findViewById(R.id.dialog_tv_des);
        etInput = findViewById(R.id.dialog_et_input);
        btnCancal = findViewById(R.id.dialog_btn_cancal);
        btnCommit = findViewById(R.id.dialog_btn_commit);

        tvTitle.setText(builder.title != null ? builder.title : "");
        tvDes.setText(builder.des != null ? builder.des : "");

        btnCancal.setOnClickListener(this);
        btnCommit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.dialog_btn_cancal:
                if (builder.mClickListener != null) builder.mClickListener.onCancalClick(v);
                break;
            case R.id.dialog_btn_commit:
                if (builder.mClickListener != null) {
                    builder.mClickListener.onCommitClick(etInput.getText().toString());
                }
                break;
        }
        dismiss();
    }

    /**
     * 构造弹窗内容
     */
    public static class InputBuilder {

        public Context context;
        public String title;
        public String des;
        public String inputEt;
        public String btnCancal;
        public String btnCommit;
        public OnDialogClickListener mClickListener;

        public InputBuilder(Context context) {
            this.context = context;
        }

        public InputBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public InputBuilder setDes(String des) {
            this.des = des;
            return this;
        }

        public InputBuilder setInputEt(String inputEt) {
            this.inputEt = inputEt;
            return this;
        }

        public InputBuilder setBtnCancal(String btnCancal) {
            this.btnCancal = btnCancal;
            return this;
        }

        public InputBuilder setBtnCommit(String btnCommit) {
            this.btnCommit = btnCommit;
            return this;
        }

        public InputBuilder setmClickListener(OnDialogClickListener mClickListener) {
            this.mClickListener = mClickListener;
            return this;
        }

        public DialogInputUtil build() {
            return new DialogInputUtil(this);
        }

        public void show() {
            new DialogInputUtil(this).show();
        }
    }
}
