package peng.qtgm.t0426_preview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.blankj.swipepanel.SwipePanel;

public class MainActivity extends AppCompatActivity {

    private SwipePanel swipePanel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipePanel = findViewById(R.id.swipePanel);
        swipePanel.setOnFullSwipeListener(new SwipePanel.OnFullSwipeListener() {// 设置完全划开松手后的监听
            @Override
            public void onFullSwipe(int direction) {
                finish();
                swipePanel.close(direction);// 关闭
            }
        });


    }
}
