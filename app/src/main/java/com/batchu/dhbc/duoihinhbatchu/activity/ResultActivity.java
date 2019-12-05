package com.batchu.dhbc.duoihinhbatchu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.batchu.dhbc.duoihinhbatchu.R;
import com.batchu.dhbc.duoihinhbatchu.config.Config;
import com.batchu.dhbc.duoihinhbatchu.listener.OnTouchClickListener;
import com.batchu.dhbc.duoihinhbatchu.utils.PlayMusic;
import com.batchu.dhbc.duoihinhbatchu.utils.Utils;

public class ResultActivity extends AppCompatActivity {

    boolean isStartNew = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Utils.showAdPopup();

        FrameLayout layout_ads = findViewById(R.id.layout_ads);
        RelativeLayout adView = findViewById(R.id.adView);
        Utils.showAd(getApplicationContext(), adView, layout_ads);

        String ketqua = getIntent().getStringExtra("ketqua");
        TextView lbl_ketqua = findViewById(R.id.lbl_ketqua);
        lbl_ketqua.setText(ketqua);

        findViewById(R.id.layout_play_now).setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                isStartNew = true;
                startActivity(new Intent(ResultActivity.this, PlayActivity.class));
                finish();
            }
        }, 20, getApplicationContext()));


        PlayMusic.playCorrect(getApplicationContext());
    }

    @Override
    protected void onPause() {
        super.onPause();

        /*if (!isStartNew)
            Config.mediaPlayer.pause();*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        // set fullscreen
        findViewById(R.id.root_view).setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        /*if (!Config.mediaPlayer.isPlaying())
            if (Config.isPlayBackgroundMusic)
                Config.mediaPlayer.start();*/
    }
}
