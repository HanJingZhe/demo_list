package com.example.tanimation;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.tanimation.bean.SurfaceAnimView;
import com.example.tanimation.bean.SurfaceBean;

/**
 * @author peng_wang
 * @des 测试surfaceView
 */
public class TestSurfaceActivity extends AppCompatActivity {

    private SurfaceAnimView surface_anim_view;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_surface);
        surface_anim_view = findViewById(R.id.surface_anim_view);
        button = findViewById(R.id.add);

        surface_anim_view.start();
    }

    public void add(View view) {
        SurfaceBean surfaceBean = new SurfaceBean(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_heart), surface_anim_view);
        surface_anim_view.addBean(surfaceBean);
    }
}
