package com.batchu.dhbc.duoihinhbatchu.config;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.reward.RewardedVideoAd;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by keban on 4/5/2018
 */

public class Config {

    /*public static String banner_ads = "ca-app-pub-4776693922147142/6293398421";
    public static String popup_ads = "ca-app-pub-4776693922147142/2463207899";*/

    public static String banner_ads = "/112517806/122851524217651";
    public static String popup_ads = "/112517806/322851524217651";

    public static String video_ads = "ca-app-pub-4776693922147142/2413863164";

    public static String popup_API_URL = "http://gamemobileglobal.com/api/control-dhbc.php?";
    public static String codeAccessAPI = "60381";

    public static final int mode = Activity.MODE_PRIVATE;
    public static final String MYPREFS = "MyPreferences";

    public static Intent showAdService;
    public static InterstitialAd iad;
    public static RewardedVideoAd adVideo;

    public static int time_start = 0;
    public static int time_end = 0;
    public static int time_start_show_popup = 0;
    public static int offset_time_show_popup = 0;

    public static MediaPlayer mediaPlayer;
    public static boolean isPlayBackgroundMusic = true;

    public static List<Integer> da_hoi = new ArrayList<>();

    public static int score = 0;
    public static int ruby = 100;

    public static int random = 0;
    public static int reveal = 0;
    public static boolean isRandom = true;
}
