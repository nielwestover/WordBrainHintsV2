package com.example.a2558.myapplication;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HintsFragment extends Fragment {

    MyApplication app = null;
    private AdView mAdView;

    private int animalLevel;
    private int levelNumber;

    public static final HintsFragment newInstance(int animalLevel, int levelNumber){
        HintsFragment f = new HintsFragment();
        Bundle localBundle = new Bundle(1);
        localBundle.putInt("animalLevel", animalLevel);
        localBundle.putInt("levelNumber", levelNumber);
        f.setArguments(localBundle);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        animalLevel = getArguments().getInt("animalLevel");
        levelNumber = getArguments().getInt("levelNumber");
        return inflater.inflate(R.layout.hint_fragment_layout, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        View view = getView();

        AnimalPack pack = app.animalPacks().get(animalLevel);
        ListView listView = (ListView) view.findViewById(R.id.hintList);
        TextView levelName = (TextView) view.findViewById(R.id.levelName);
        levelName.setText(pack.level + " " + (levelNumber + 1));

        List<String> answers = pack.answers.get(levelNumber);
        List<Answer> singleAnswers = new ArrayList<Answer>();
        for (String str : answers) {
            Answer v = new Answer();
            v.answer = str;
            singleAnswers.add(v);
        }
        CustomAdapter adapter = new CustomAdapter(getActivity(), singleAnswers);
        listView.setAdapter(adapter);


    }

//    public void advanceLevelClicked(View view) {
//        app.curLevelInPack++;
//        if (app.curLevelInPack >= app.curAnimalPack.answers.size()) {
//            if (app.curPackIndex == app.animalPacks.size() - 1) {
//                app.curLevelInPack--;
//                return;
//            }
//            app.curPackIndex++;
//            app.curAnimalPack = app.animalPacks.get(app.curPackIndex);
//            app.curLevelInPack = 0;
//        }
//        finish();
//        startActivity(getIntent());
//        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
//    }


}
