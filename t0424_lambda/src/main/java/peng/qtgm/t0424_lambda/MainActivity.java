package peng.qtgm.t0424_lambda;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv1 = findViewById(R.id.main_tv1);
        tv2 = findViewById(R.id.main_tv2);
        tv3 = findViewById(R.id.main_tv3);

        // 练习lambda 表达式
        testLambda();
    }

    private void testLambda() {
    }
}
