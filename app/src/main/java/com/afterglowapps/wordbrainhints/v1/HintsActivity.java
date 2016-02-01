package com.afterglowapps.wordbrainhints.v1;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.google.android.gms.ads.AdView;


public class HintsActivity extends FragmentActivity {

    MyApplication app = null;

    private class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int pos) {
            int index = -1;
            for (int i = 0; i < app.animalPacks().size(); ++i) {
                for (int j = 0; j < app.animalPacks().get(i).answers.size(); ++j) {
                    ++index;
                    if (index == pos) {
                        app.setCurAnimalPack(app.animalPacks().get(i));
                        app.curPackIndex = i;
                        return HintsFragment.newInstance(i, j);
                    }
                }
            }
            return HintsFragment.newInstance(0, 0);
        }

        @Override
        public int getCount() {
            return 680;
        }
    }


    private boolean isBeingDestroyed = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isBeingDestroyed = true;

        adHandler.removeCallbacksAndMessages(null);

    }

    private Handler adHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_activity_layout);

        app = ((MyApplication) this.getApplication());


        adHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (!isBeingDestroyed) {
                    final AdView adView = (AdView) findViewById(R.id.adView);
                    adView.loadAd(app.getAdRequest());
                }
                return true;
            }
        });
        adHandler.sendEmptyMessageDelayed(0, 500);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        int index = -1;
        for (int i = 0; i < app.animalPacks().size(); ++i) {
            for (int j = 0; j < app.animalPacks().get(i).answers.size(); ++j) {
                ++index;
                if (i == app.curPackIndex && j == app.curLevelInPack) {
                    pager.setCurrentItem(index);
                    app.setCurAnimalPack(app.animalPacks().get(i));
                    app.curPackIndex = i;
                    return;
                }

            }
        }
    }

    @Override
    public void onBackPressed() {

        this.finish();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }


}
