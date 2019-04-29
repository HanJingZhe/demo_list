package peng.qtgm.t0426_agora.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import peng.qtgm.t0426_agora.R;
import peng.qtgm.t0426_agora.utils.AgoraManager;

public class RoomActivity extends AppCompatActivity {

    public static final String CHANNELNAME = "channelName";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        FrameLayout flPreview = findViewById(R.id.room_fl_preview);
        String channelName = getIntent().getStringExtra(CHANNELNAME);
        AgoraManager.getInstance().joinChannel(channelName).setupLocalVideo().startPreview();
        flPreview.addView(AgoraManager.getInstance().getLocalSurfaceView());
    }
}
