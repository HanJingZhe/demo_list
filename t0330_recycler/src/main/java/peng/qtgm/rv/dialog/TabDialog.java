package peng.qtgm.rv.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import peng.qtgm.rv.R;
import peng.qtgm.rv.adapter.MyAdapter;
import peng.qtgm.rv.utils.FlowLayoutManager2;

public class TabDialog extends Dialog {

    public TabDialog(@NonNull Context context) {
        super(context, R.style.dialog_top);
        setContentView(R.layout.dialog_tab_layout);

        //设置window 展示大小
        Window window = getWindow();
        WindowManager.LayoutParams params = window.getAttributes();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        window.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        //params.height = (int) (displayMetrics.heightPixels * 0.8);
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.gravity = Gravity.TOP;
        window.setAttributes(params);

        //initView
        RecyclerView rv = findViewById(R.id.tab_rv);

        MyAdapter myAdapter = new MyAdapter(initData());
        rv.setLayoutManager(new FlowLayoutManager2());
        rv.setAdapter(myAdapter);
    }

    private List<String> initData() {
        List<String> list = new ArrayList<>(Arrays.asList("推荐","爵士舞","中国风","古典舞身韵","HIT燃脂"
                ,"瘦身尊巴舞","瘦身瑜伽","拉丁舞/民族舞/现代舞","少儿成品舞","少儿基本功","小趣说","拉丁舞/民族舞/现代舞"));
        return list;
    }

}
