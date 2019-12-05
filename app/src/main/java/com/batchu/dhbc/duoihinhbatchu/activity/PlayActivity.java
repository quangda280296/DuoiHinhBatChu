package com.batchu.dhbc.duoihinhbatchu.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.SharePhoto;
import com.facebook.share.model.SharePhotoContent;
import com.facebook.share.widget.ShareDialog;
import com.batchu.dhbc.duoihinhbatchu.R;
import com.batchu.dhbc.duoihinhbatchu.config.Config;
import com.batchu.dhbc.duoihinhbatchu.database.DHBCHelper;
import com.batchu.dhbc.duoihinhbatchu.listener.OnTouchClickListener;
import com.batchu.dhbc.duoihinhbatchu.utils.Check;
import com.batchu.dhbc.duoihinhbatchu.utils.PlayMusic;
import com.batchu.dhbc.duoihinhbatchu.utils.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static com.batchu.dhbc.duoihinhbatchu.activity.MainActivity.mainActivity;

public class PlayActivity extends AppCompatActivity {

    private List<View> list_dapan = new ArrayList<>();
    private List<View> list_goiy = new ArrayList<>();
    private List<TextView> list_txt_dapan = new ArrayList<>();
    private List<TextView> list_txt_goiy = new ArrayList<>();

    int countBack = 0;

    String dapan;
    String ketqua;

    boolean isStartNew = false;
    boolean canTouch = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        FrameLayout layout_ads = findViewById(R.id.layout_ads);
        RelativeLayout adView = findViewById(R.id.adView);
        Utils.showAd(getApplicationContext(), adView, layout_ads);

        handleClickShare();
        handleClickPass();

        findViewById(R.id.img_back).setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = new AlertDialog.Builder(PlayActivity.this)
                        .setTitle("Xác nhận")
                        .setMessage("Bạn có chắc chắn muốn thoát ?")
                        .setIcon(R.mipmap.ic_question)
                        .setNegativeButton("Có", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                mainActivity.setScore();
                                finish();
                            }
                        })//setPositiveButton
                        .setPositiveButton("Không", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                dialog.cancel();
                                LinearLayout root_view = findViewById(R.id.root_view);
                                root_view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                            }
                        })//setNegativeButton
                        .create();
                dialog.show();
            }
        }, 20, getApplicationContext()));

        TextView lbl_score = findViewById(R.id.lbl_score);
        lbl_score.setText(Config.score + "");

        TextView lbl_ruby = findViewById(R.id.lbl_ruby);
        lbl_ruby.setText(Config.ruby + "");

        DHBCHelper helper = new DHBCHelper(getApplicationContext());
        Cursor cursor = helper.getCursor();

        if (Config.isRandom) {
            Config.random = Utils.getRand(cursor.getCount());
            Config.isRandom = false;
            SharedPreferences mySharedPreferences = getSharedPreferences(Config.MYPREFS, Config.mode);
            SharedPreferences.Editor myEditor = mySharedPreferences.edit();
            myEditor.putInt("random", Config.random);
            myEditor.commit();
        }

        cursor.moveToPosition(Config.random);

        String filename = helper.getFilename(cursor);
        String goiy = helper.getGoiy(cursor);
        dapan = helper.getDapan(cursor);
        ketqua = helper.getKetqua(cursor);

        //Utils.shortToast(getApplicationContext(), dapan);

        try {
            InputStream is = getAssets().open("images/" + filename);
            ImageView img_picture = findViewById(R.id.img_picture);
            img_picture.setImageBitmap(BitmapFactory.decodeStream(is));

        } catch (IOException e) {
            e.printStackTrace();
        }

        list_dapan.add(findViewById(R.id.dapan_1));
        list_dapan.add(findViewById(R.id.dapan_2));
        list_dapan.add(findViewById(R.id.dapan_3));
        list_dapan.add(findViewById(R.id.dapan_4));
        list_dapan.add(findViewById(R.id.dapan_5));
        list_dapan.add(findViewById(R.id.dapan_6));
        list_dapan.add(findViewById(R.id.dapan_7));
        list_dapan.add(findViewById(R.id.dapan_8));
        list_dapan.add(findViewById(R.id.dapan_9));
        list_dapan.add(findViewById(R.id.dapan_10));
        list_dapan.add(findViewById(R.id.dapan_11));
        list_dapan.add(findViewById(R.id.dapan_12));
        list_dapan.add(findViewById(R.id.dapan_13));
        list_dapan.add(findViewById(R.id.dapan_14));

        for (int i = 13; i >= dapan.length(); i--) {
            list_dapan.get(i).setVisibility(View.GONE);
        }

        list_goiy.add(findViewById(R.id.goiy_1));
        list_goiy.add(findViewById(R.id.goiy_2));
        list_goiy.add(findViewById(R.id.goiy_3));
        list_goiy.add(findViewById(R.id.goiy_4));
        list_goiy.add(findViewById(R.id.goiy_5));
        list_goiy.add(findViewById(R.id.goiy_6));
        list_goiy.add(findViewById(R.id.goiy_7));
        list_goiy.add(findViewById(R.id.goiy_8));
        list_goiy.add(findViewById(R.id.goiy_9));
        list_goiy.add(findViewById(R.id.goiy_10));
        list_goiy.add(findViewById(R.id.goiy_11));
        list_goiy.add(findViewById(R.id.goiy_12));
        list_goiy.add(findViewById(R.id.goiy_13));
        list_goiy.add(findViewById(R.id.goiy_14));

        list_txt_goiy.add(findViewById(R.id.txt_goiy_1));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_2));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_3));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_4));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_5));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_6));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_7));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_8));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_9));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_10));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_11));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_12));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_13));
        list_txt_goiy.add(findViewById(R.id.txt_goiy_14));

        for (int i = 0; i < goiy.length(); i++) {
            list_txt_goiy.get(i).setText(goiy.charAt(i) + "");
        }

        list_txt_dapan.add(findViewById(R.id.txt_dapan_1));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_2));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_3));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_4));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_5));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_6));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_7));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_8));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_9));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_10));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_11));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_12));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_13));
        list_txt_dapan.add(findViewById(R.id.txt_dapan_14));

        for (int i = 0; i < Config.reveal; i++) {
            list_txt_dapan.get(i).setTextColor(getResources().getColor(android.R.color.holo_blue_bright));
            list_txt_dapan.get(i).setText(dapan.charAt(i) + "");
        }

        for (int i = 0; i < Config.reveal; i++)
            for (int j = 0; j < list_goiy.size(); j++)
                if (list_goiy.get(j).getVisibility() == View.VISIBLE)
                    if (list_txt_goiy.get(j).getText().equals(dapan.charAt(i) + "")) {
                        list_goiy.get(j).setVisibility(View.INVISIBLE);
                        break;
                    }

        //PlayMusic.playStart(getApplicationContext());
    }

    public void clickGoiy(View view) {
        if (!canTouch)
            return;

        canTouch = false;
        countBack = 0;

        int empty = 0;

        for (int i = 0; i < dapan.length(); i++)
            if (list_txt_dapan.get(i).getText().equals(""))
                empty++;

        if (empty == 0) {
            canTouch = true;
            return;
        }

        view.setVisibility(View.INVISIBLE);
        int index = Integer.parseInt(view.getTag().toString());

        for (int i = 0; i < dapan.length(); i++) {
            if (list_txt_dapan.get(i).getText().equals("")) {
                list_txt_dapan.get(i).setText(list_txt_goiy.get(index - 1).getText());
                list_txt_dapan.get(i).setTag(index);
                empty--;
                break;
            }
        }

        if (empty == 0) {
            String dapan_select = "";

            for (int i = 0; i < dapan.length(); i++) {
                dapan_select += list_txt_dapan.get(i).getText();
            }

            if (dapan_select.equals(dapan)) {
                next();

            } else {
                PlayMusic.playWrong(getApplicationContext());

                Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
                findViewById(R.id.lbl_result).setVisibility(View.VISIBLE);
                findViewById(R.id.lbl_result).startAnimation(anim);

                YoYo.with(Techniques.Shake)
                        .duration(1000)
                        .playOn(findViewById(R.id.lbl_result));

                YoYo.with(Techniques.Shake)
                        .duration(1000)
                        .playOn(findViewById(R.id.layout_dapan));
            }
        }

        canTouch = true;
    }

    public void clickDapan(View view) {
        if (!canTouch)
            return;

        canTouch = false;
        countBack = 0;

        int index = Integer.parseInt(view.getTag().toString());

        if (index <= Config.reveal) {
            canTouch = true;
            return;
        }

        if (list_txt_dapan.get(index - 1).getText().equals("")) {
            canTouch = true;
            return;
        }

        list_txt_dapan.get(index - 1).setText("");
        int idx = Integer.parseInt(list_txt_dapan.get(index - 1).getTag().toString());
        list_goiy.get(idx - 1).setVisibility(View.VISIBLE);

        canTouch = true;
    }

    public void handleClickShare() {
        findViewById(R.id.btn_share).setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayMusic.playClick(getApplicationContext());
                countBack = 0;

                if (!Check.checkInternetConnection(getApplicationContext()))
                    return;

                FrameLayout layout_ads = findViewById(R.id.layout_ads);
                layout_ads.setVisibility(View.INVISIBLE);

                Bitmap bitmap = Utils.takeScreenshot(PlayActivity.this);

                SharePhoto photo = new SharePhoto.Builder()
                        .setBitmap(bitmap)
                        .setCaption("Giúp mình câu này với mọi người ơi !!!")
                        .build();

                SharePhotoContent content = new SharePhotoContent.Builder()
                        .addPhoto(photo)
                        .build();

                // this part is optional
                CallbackManager callbackManager = CallbackManager.Factory.create();

                ShareDialog shareDialog = new ShareDialog(PlayActivity.this);
                shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
                    @Override
                    public void onSuccess(Sharer.Result result) {
                        Utils.shortToast(getApplicationContext(), getString(R.string.share_successfull));
                    }

                    @Override
                    public void onCancel() {
                        // App code
                        Utils.shortToast(getApplicationContext(), "Cancel");
                    }

                    @Override
                    public void onError(FacebookException error) {
                        Utils.shortToast(getApplicationContext(), getString(R.string.please_try_again));
                    }
                });// registerCallback

                shareDialog.show(content, ShareDialog.Mode.AUTOMATIC);
            }
        }, 20, getApplicationContext()));
    }

    public void handleClickPass() {
        Snackbar snack = Snackbar.make(findViewById(android.R.id.content), "Bạn không có đủ 5 ruby", Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        View view = snack.getView();
        view.setBackgroundColor(Color.BLACK);
        ((TextView) view.findViewById(android.support.design.R.id.snackbar_text)).setTextColor(Color.WHITE);
        ((TextView) view.findViewById(android.support.design.R.id.snackbar_action)).setTextColor(Color.GREEN);

        findViewById(R.id.btn_pass).setOnTouchListener(new OnTouchClickListener(new OnTouchClickListener.OnClickListener() {
            @Override
            public void onClick(View v) {
                countBack = 0;

                PlayMusic.playClick(getApplicationContext());
                countBack = 0;

                LinearLayout root_view = findViewById(R.id.root_view);
                root_view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                if (Config.ruby < 5) {
                    snack.show();
                    return;
                }

                AlertDialog dialog = new AlertDialog.Builder(PlayActivity.this)
                        .setMessage("Bạn có muốn dùng 5 ruby để mở một ô chữ không ?")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!canTouch)
                                    return;

                                canTouch = false;

                                for (View l : list_goiy)
                                    l.setVisibility(View.VISIBLE);

                                for (int i = dapan.length() - 1; i >= Config.reveal; i--)
                                    list_txt_dapan.get(i).setText("");

                                Config.ruby -= 5;
                                Config.reveal++;
                                list_txt_dapan.get(Config.reveal - 1).setTextColor(getResources().getColor(android.R.color.holo_blue_bright));
                                list_txt_dapan.get(Config.reveal - 1).setText(dapan.charAt(Config.reveal - 1) + "");

                                TextView lbl_ruby = findViewById(R.id.lbl_ruby);
                                lbl_ruby.setText(Config.ruby + "");

                                SharedPreferences mySharedPreferences = getSharedPreferences(Config.MYPREFS, Config.mode);
                                SharedPreferences.Editor myEditor = mySharedPreferences.edit();
                                myEditor.putInt("ruby", Config.ruby);
                                myEditor.putInt("reveal", Config.reveal);
                                myEditor.commit();

                                for (int i = 0; i < Config.reveal; i++)
                                    for (int j = 0; j < list_goiy.size(); j++)
                                        if (list_goiy.get(j).getVisibility() == View.VISIBLE)
                                            if (list_txt_goiy.get(j).getText().equals(dapan.charAt(i) + "")) {
                                                list_goiy.get(j).setVisibility(View.INVISIBLE);
                                                break;
                                            }

                                dialog.dismiss();

                                LinearLayout root_view = findViewById(R.id.root_view);
                                root_view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                                int empty = 0;

                                for (int i = 0; i < dapan.length(); i++)
                                    if (list_txt_dapan.get(i).getText().equals(""))
                                        empty++;

                                if (empty == 0)
                                    next();

                                canTouch = true;
                            }
                        })
                        .setNegativeButton("Không", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                                LinearLayout root_view = findViewById(R.id.root_view);
                                root_view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
                            }
                        })
                        .create();

                dialog.show();
            }
        }, 20, getApplicationContext()));
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
    public void onBackPressed() {
        this.countBack++;
        if (this.countBack == 2) {
            mainActivity.setScore();
            finish();
        } else
            Utils.longToast(getApplicationContext(), getString(R.string.press_back_again));
    }

    public void next() {
        PlayMusic.playTrue(getApplicationContext());
        findViewById(R.id.lbl_result).setVisibility(View.INVISIBLE);

        YoYo.with(Techniques.Pulse)
                .duration(1500)
                .playOn(findViewById(R.id.layout_dapan));

        YoYo.with(Techniques.Flash)
                .duration(1500)
                .playOn(findViewById(R.id.layout_dapan));

        for (int i = 0; i < dapan.length(); i++) {
            list_txt_dapan.get(i).setTextColor(getResources().getColor(R.color.green));
        }

        Config.ruby += 10;
        Config.score++;
        Config.isRandom = true;
        Config.reveal = 0;

        SharedPreferences mySharedPreferences = getSharedPreferences(Config.MYPREFS, Config.mode);
        SharedPreferences.Editor myEditor = mySharedPreferences.edit();
        myEditor.putInt("ruby", Config.ruby);
        myEditor.putInt("score", Config.score);
        myEditor.putInt("reveal", Config.reveal);
        myEditor.putInt("random", -1);
        myEditor.commit();

        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
        intent.putExtra("ketqua", ketqua);
        isStartNew = true;

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, 1000);
    }
}
