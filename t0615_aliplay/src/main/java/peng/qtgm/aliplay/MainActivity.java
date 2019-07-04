package peng.qtgm.aliplay;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton ibtnGold = findViewById(R.id.main_ibtn_gold);
        Glide.with(this).load(R.mipmap.ic_gold_rotate2).asGif().centerCrop().into(ibtnGold);

    }
}
