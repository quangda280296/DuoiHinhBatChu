package com.batchu.dhbc.duoihinhbatchu.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.batchu.dhbc.duoihinhbatchu.R;
import com.batchu.dhbc.duoihinhbatchu.config.Config;
import com.batchu.dhbc.duoihinhbatchu.listener.OnTouchClickListener;
import com.batchu.dhbc.duoihinhbatchu.utils.PlayMusic;
import com.batchu.dhbc.duoihinhbatchu.utils.Utils;

public class MainActivity extends AppCompatActivity {

    private LinearLayout layout_play_now;
    private LinearLayout layout_earn_ruby;

    private TextView lbl_score;
    private ImageView img_sound;

    public static MainActivity mainActivity;

    boolean isStartNew = false;
    int countBack = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mainActivity = MainActivity.this;

        FrameLayout layout_ads = findViewById(R.id.layout_ads);
        RelativeLayout adView = findViewById(R.id.adView);
        Utils.showAd(getApplicationContext(), adView, layout_ads);

        lbl_score = findViewById(R.id.lbl_score);
        setScore();

        //handleSound();

        layout_play_now = findViewById(R.id.layout_play_now);
        layout_earn_ruby = findViewById(R.id.layout_earn_ruby);

        layout_play_now.setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                countBack = 0;
                isStartNew = true;
                PlayMusic.playClick(getApplicationContext());
                startActivity(new Intent(MainActivity.this, PlayActivity.class));
            }
        }, 20, getApplicationContext()));

        layout_earn_ruby.setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayMusic.playClick(getApplicationContext());
                Utils.showRewardedVideo(MainActivity.this);
            }
        }, 20, getApplicationContext()));
    }

    public void handleSound() {
        img_sound = findViewById(R.id.img_sound);
        if (Config.isPlayBackgroundMusic)
            img_sound.setImageResource(R.mipmap.ic_sound_on);
        else
            img_sound.setImageResource(R.mipmap.ic_sound_off);

        findViewById(R.id.img_sound).setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Config.isPlayBackgroundMusic) {
                    Config.mediaPlayer.pause();
                    Config.isPlayBackgroundMusic = false;
                    img_sound.setImageResource(R.mipmap.ic_sound_off);
                } else {
                    Config.mediaPlayer.start();
                    Config.isPlayBackgroundMusic = true;
                    img_sound.setImageResource(R.mipmap.ic_sound_on);
                }
                //perform onClick
            }
        }, 20, getApplicationContext()));
    } //handleSound

    @Override
    protected void onResume() {
        super.onResume();
        // set fullscreen
        LinearLayout root_view = findViewById(R.id.root_view);
        root_view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        /*if (!Config.mediaPlayer.isPlaying())
            if (Config.isPlayBackgroundMusic)
                Config.mediaPlayer.start();*/
    }

    @Override
    protected void onPause() {
        super.onPause();

        /*if (!isStartNew)
            Config.mediaPlayer.pause();*/
    }

    @Override
    public void onBackPressed() {
        this.countBack++;
        if (this.countBack == 2)
            finish();
        else
            Utils.longToast(getApplicationContext(), getString(R.string.press_back_again));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Config.mediaPlayer.stop();
    }

    public void setScore() {
        lbl_score.setText(Config.score + "");
    }
}
