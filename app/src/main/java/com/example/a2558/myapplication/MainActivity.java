package com.example.a2558.myapplication;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ListActivity {

    public final static String PACK_ID = "PACK_ID_EXTRA";
    MyApplication app = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = ((MyApplication) this.getApplication());

        try {
            getAllLevels();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayAdapter<AnimalPack> adapter = new ArrayAdapter<AnimalPack>(this, R.layout.row_layout, R.id.textView, app.animalPacks);
        setListAdapter(adapter);

    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);
        // get

        app.curAnimalPack = (AnimalPack) list.getItemAtPosition(position);
        app.curPackIndex = position;
        app.curLevelInPack = 0;

        Intent packIntent = new Intent(this, PackActivity.class);

        packIntent.putExtra(PACK_ID, position);
        startActivity(packIntent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
    }

    private void getAllLevels() throws IOException {
        InputStream is = getResources().openRawResource(R.raw.all_levels);
        byte [] buf = new byte[is.available()];
        is.read(buf);
        is.close();
        String levels = new String(buf, "UTF-8");
        try {
            JSONArray animalPacksJSON = new JSONArray(levels);
            for (int i = 0; i < animalPacksJSON.length(); ++i) {
                AnimalPack pack = new AnimalPack();
                JSONObject obj = animalPacksJSON.getJSONObject(i);
                pack.level = (String)obj.get("level");
                JSONArray answers = obj.getJSONArray("answers");
                for (int j = 0; j < answers.length(); ++j){
                    List<String> curAnswers = new ArrayList<String>();
                    JSONArray curAnswersJSON = answers.getJSONArray(j);
                    for (int k = 0; k < curAnswersJSON.length(); ++k){
                        curAnswers.add((String)curAnswersJSON.get(k));
                    }
                    pack.answers.add(curAnswers);
                }
                app.animalPacks.add(pack);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
