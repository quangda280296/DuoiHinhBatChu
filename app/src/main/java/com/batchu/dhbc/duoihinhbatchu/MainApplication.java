package com.batchu.dhbc.duoihinhbatchu;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;

import com.crashlytics.android.Crashlytics;
import com.facebook.FacebookSdk;
import com.batchu.dhbc.duoihinhbatchu.config.Config;
import com.batchu.dhbc.duoihinhbatchu.service.ShowAdService;

import java.util.Calendar;

import io.fabric.sdk.android.Fabric;

/**
 * Created by keban on 3/6/2018.
 */

public class MainApplication extends Application {

    private Typeface fontBold;
    private Typeface fontMedium;

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());

        FacebookSdk.sdkInitialize(getApplicationContext());

        // Handle font
        fontBold = Typeface.createFromAsset(getAssets(), "Hanzel.ttf");
        fontMedium = Typeface.createFromAsset(getAssets(), "Amerika Sans.ttf");

        // Handle adService
        Calendar calendar = Calendar.getInstance();
        Config.time_start = ((calendar.get(Calendar.YEAR) * 365 + calendar.get(Calendar.DAY_OF_YEAR)) * 24
                + calendar.get(Calendar.HOUR_OF_DAY)) * 3600 + calendar.get(Calendar.MINUTE) * 60 + calendar.get(Calendar.SECOND);
        Config.time_end = Config.time_start;

        Log.d("ttttuuuu", "testtttttttt");

        Config.showAdService = new Intent(this, ShowAdService.class);
        startService(Config.showAdService);

        Log.d("ttttuuuu", "testtttttttt");

        /*Config.mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.theme);
        Config.mediaPlayer.setLooping(true);*/

        SharedPreferences mySharedPreferences = getSharedPreferences(Config.MYPREFS, Config.mode);
        if (mySharedPreferences == null)
            return;

        if (mySharedPreferences.contains("score"))
            Config.score = mySharedPreferences.getInt("score", 0);

        if (mySharedPreferences.contains("ruby"))
            Config.ruby = mySharedPreferences.getInt("ruby", 0);

        if (mySharedPreferences.contains("random")) {
            Config.random = mySharedPreferences.getInt("random", 0);

            if (Config.random != -1) {
                Config.da_hoi.add(Config.random);
                Config.isRandom = false;
            }
        }

        if (mySharedPreferences.contains("reveal")) {
            Config.reveal = mySharedPreferences.getInt("reveal", 0);
        }
    }

    public Typeface getFontBold() {
        return fontBold;
    }

    public Typeface getFontMedium() {
        return fontMedium;
    }
}
