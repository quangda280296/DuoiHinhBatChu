package com.batchu.dhbc.duoihinhbatchu.utils;

import android.content.Context;
import android.media.MediaPlayer;

import com.batchu.dhbc.duoihinhbatchu.R;


/**
 * Created by keban on 3/6/2018.
 */

public class PlayMusic {

    public static MediaPlayer mp;

    public static void playClick(Context context) {
        mp = MediaPlayer.create(context, R.raw.click);
        mp.start();
    }

    public static void playTrue(Context context) {
        mp = MediaPlayer.create(context, R.raw.pass);
        mp.start();
    }

    public static void playCorrect(Context context) {
        mp = MediaPlayer.create(context, R.raw.right);
        mp.start();
    }

    /*public static void playStart(Context context) {
        mp = MediaPlayer.create(context, R.raw.start);
        mp.start();
    }*/

    public static void playWrong(Context context) {
        mp = MediaPlayer.create(context, R.raw.wrong);
        mp.start();
    }
}
