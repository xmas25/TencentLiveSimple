package com.cp.tencentlivesimple.activity;

import android.os.Bundle;

import com.cp.tencentlivesimple.R;
import com.tencent.rtmp.TXLiveConstants;
import com.tencent.rtmp.TXLivePlayer;
import com.tencent.rtmp.ui.TXCloudVideoView;

/**
 * create by libo
 * create on 2019-10-29
 * description 拉流页
 */
public class PullStreamActivity extends BasePermissionActivity {
    private TXLivePlayer livePlayer;
    private TXCloudVideoView videoView;
    private String playUrl = "rtmp://libonet.club/TencentLivesSimple/huyalive";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_stream);

        init();
    }

    private void init() {
        livePlayer = new TXLivePlayer(this);
        videoView = findViewById(R.id.videoview_pull);
        livePlayer.setPlayerView(videoView);

        startLivePlay();
    }

    /**
     * 开启直播播放
     */
    private void startLivePlay() {
        //第二个参数为播放地址类型
        livePlayer.startPlay(playUrl, TXLivePlayer.PLAY_TYPE_LIVE_FLV);
        livePlayer.setRenderMode(TXLiveConstants.RENDER_MODE_ADJUST_RESOLUTION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        livePlayer.stopPlay(true);  //true代表清除最后一帧画面
        videoView.onDestroy();
    }
}