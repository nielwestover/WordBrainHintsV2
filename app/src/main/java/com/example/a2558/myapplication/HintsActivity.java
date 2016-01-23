package com.example.a2558.myapplication;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HintsActivity extends Activity {

    MyApplication app = null;
    private AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_layout);

        app = ((MyApplication) this.getApplication());

        mAdView = (AdView) findViewById(R.id.adView);

        mAdView.loadAd(app.getAdRequest());


        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        try {
            ListView listView = (ListView) findViewById(R.id.hintList);
            TextView levelName = (TextView) findViewById(R.id.levelName);
            levelName.setText(app.curAnimalPack.level + " " + (app.curLevelInPack + 1));

            List<String> answers = app.curAnimalPack.answers.get(app.curLevelInPack);
            List<Answer> singleAnswers = new ArrayList<Answer>();
            for (String str : answers) {
                Answer v = new Answer();
                v.answer = str;
                singleAnswers.add(v);
            }
            CustomAdapter adapter = new CustomAdapter(this, singleAnswers);
            listView.setAdapter(adapter);
        }
        catch (Exception e) {
            try {
                app.loadAllLevels(this);
                finish();
                startActivity(getIntent());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    @Override
    public void onBackPressed() {

        this.finish();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }

    public void advanceLevelClicked(View view){
        app.curLevelInPack++;
        if (app.curLevelInPack >= app.curAnimalPack.answers.size())
        {
            if (app.curPackIndex == app.animalPacks.size()-1) {
                app.curLevelInPack--;
                return;
            }
            app.curPackIndex++;
            app.curAnimalPack = app.animalPacks.get(app.curPackIndex);
            app.curLevelInPack = 0;
        }
        finish();
        startActivity(getIntent());
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }


}
