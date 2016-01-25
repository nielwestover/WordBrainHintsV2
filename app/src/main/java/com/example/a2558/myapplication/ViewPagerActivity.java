package com.example.a2558.myapplication;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ViewPagerActivity extends Activity {

    MyApplication app = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_pager_layout);

        app = ((MyApplication) this.getApplication());
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        //viewPager.setAdapter(new CustomPagerAdapter(this));

    }

    @Override
    public void onBackPressed() {

        this.finish();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }
}
