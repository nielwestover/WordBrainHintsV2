package com.example.a2558.myapplication;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    MyApplication app = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(R.layout.activity_main);
        app = ((MyApplication) this.getApplication());

        ListView listView = (ListView) findViewById(R.id.animalList);
        ArrayAdapter<AnimalPack> adapter = new ArrayAdapter<AnimalPack>(this, R.layout.listview_layout, R.id.textView, app.animalPacks());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                app.curAnimalPack = (AnimalPack) app.animalPacks().get(position);
                app.curPackIndex = position;
                app.curLevelInPack = 0;

                Intent packIntent = new Intent(MainActivity.this, PackActivity.class);

                startActivity(packIntent);
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
            }
        });
    }


}
