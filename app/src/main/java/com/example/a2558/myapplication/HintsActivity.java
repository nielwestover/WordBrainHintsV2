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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class HintsActivity extends Activity {

    MyApplication app = null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.hint_layout);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        ListView listView = (ListView) findViewById(R.id.hintList);
        app = ((MyApplication) this.getApplication());
        List<String> answers = app.curAnimalPack.answers.get(app.curLevelInPack);
        List<Answer> singleAnswers = new ArrayList<Answer>();
        for (String str : answers){
            Answer v = new Answer();
            v.answer = str;
            singleAnswers.add(v);
        }
        CustomAdapter adapter = new CustomAdapter(this, singleAnswers);
        listView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {

        this.finish();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }

//
//    int curHint;
//
//    private void resetText()
//    {
//        TextView textHints = (TextView) findViewById(R.id.hints);
//        textHints.setText("");
//        curHint = 0;
//    }
//
//    public void getHintClicked(View view){
//        TextView textHints = (TextView) findViewById(R.id.hints);
//        String result = getHint(app.curPackIndex, app.curLevelInPack - 1, curHint);
//        int count = 0;
//        while (result == "***TRYAGAIN***" && ++count < 10)
//        {
//            curHint++;
//            result = getHint(app.curPackIndex, app.curLevelInPack - 1, curHint);
//        }
//        if (count == 10)
//            return;
//        textHints.setText(textHints.getText() + "\r\n" + result);
//        curHint++;
//    }

//    public void advanceLevelClicked(View view){
//        app.curLevelInPack++;
//        if (app.curLevelInPack > app.curAnimalPack.answers.size())
//        {
//            if (app.curPackIndex == app.animalPacks.size()-1) {
//                app.curLevelInPack--;
//                return;
//            }
//            app.curPackIndex++;
//            app.curLevelInPack = 1;
//        }
//        Spinner spinnerPackNames = (Spinner) findViewById(R.id.packNames);
//        Spinner spinnerLevelNumber = (Spinner) findViewById(R.id.levelNumber);
//        spinnerPackNames.setSelection(app.curPackIndex);
//        spinnerLevelNumber.setSelection(app.curLevelInPack - 1);
//    }



    public String dashify(String s, int charNumber) {
        StringBuilder sb = new StringBuilder(s.length());

        for (int i = 0; i < charNumber; ++i)
            sb.append(s.charAt(i));
        for (int i = charNumber; i < s.length(); i++) {
            sb.append('-');
        }
        return sb.toString();
    }
    private String getHint(int packIndex, int levelNumber, int hintNumber){

        List<String> answers = app.curAnimalPack.answers.get(levelNumber);
        int wordNumber = hintNumber % answers.size();
        int charNumber = hintNumber / answers.size() + 1;

        String word = answers.get(wordNumber);
        if (charNumber > word.length()) {
            return "***TRYAGAIN***";
        }
        return dashify(word, charNumber);
    }

}
