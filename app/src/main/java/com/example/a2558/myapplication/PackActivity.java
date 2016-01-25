package com.example.a2558.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackActivity extends ListActivity {

    MyApplication app = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.pack_activity);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // my_child_toolbar is defined in the layout file

        app = ((MyApplication) this.getApplication());
        app.lastChosenPackIndex = app.curPackIndex;
        Intent intent = getIntent();
        //List<Integer> listValues = new ArrayList<Integer>();

            List<Integer> seq = makeSequence(0, app.curAnimalPack.answers.size() - 1);
            List<String> levels = new ArrayList<String>();
            for (int i : seq) {
                levels.add("Level " + i);
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.row_layout, R.id.textView, levels);
            setListAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (app.lastChosenPackIndex != app.curPackIndex){
            finish();
            startActivity(getIntent());
        }
    }

    List<Integer> makeSequence(int begin, int end) {
        List<Integer> ret = new ArrayList(end - begin + 1);

        for(int i = begin; i <= end; i++, ret.add(i));

        return ret;
    }

    @Override
    public void onBackPressed() {

        this.finish();
        overridePendingTransition(R.anim.slide_out_left, R.anim.slide_in_left);
    }

   // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        app.curLevelInPack = position;

        startActivity(new Intent(this, HintsActivity.class));
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

}
