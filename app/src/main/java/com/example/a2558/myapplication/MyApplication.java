package com.example.a2558.myapplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.google.ads.AdRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2558 on 1/11/2016.
 */
public class MyApplication extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyApplication.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyApplication.context;
    }

    public static com.google.android.gms.ads.AdRequest adRequest = null;

    public static com.google.android.gms.ads.AdRequest getAdRequest() {
        if (adRequest == null) {
            adRequest = new com.google.android.gms.ads.AdRequest.Builder()
                    .addTestDevice("8ADB2A9061AE45166957859FB0675952")
                    .addTestDevice("6CA3F26EED57F8A14BE151ACC87D36EF")
                    .addTestDevice("2B3442A46BC1C40204817BAF6CC5A52E")
                    .addTestDevice(com.google.android.gms.ads.AdRequest.DEVICE_ID_EMULATOR)
                    .build();
        }
        return adRequest;
    }

    private static List<AnimalPack> animalPacks = new ArrayList<AnimalPack>();

    public static List<AnimalPack> animalPacks() {
        if (animalPacks == null || animalPacks.size()==0) {
            try {
                loadAllLevels();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return animalPacks;
    }

    public static int curPackIndex;
    public static int curLevelInPack;
    public static AnimalPack curAnimalPack;

    public static int lastChosenPackIndex;

    private static void loadAllLevels() throws IOException {
        InputStream is = context.getResources().openRawResource(R.raw.all_levels);
        byte[] buf = new byte[is.available()];
        is.read(buf);
        is.close();
        String levels = new String(buf, "UTF-8");
        try {
            JSONArray animalPacksJSON = new JSONArray(levels);

            for (int i = 0; i < animalPacksJSON.length(); ++i) {
                AnimalPack pack = new AnimalPack();
                JSONObject obj = animalPacksJSON.getJSONObject(i);
                pack.level = (String) obj.get("level");
                JSONArray answers = obj.getJSONArray("answers");
                for (int j = 0; j < answers.length(); ++j) {
                    List<String> curAnswers = new ArrayList<String>();
                    JSONArray curAnswersJSON = answers.getJSONArray(j);
                    for (int k = 0; k < curAnswersJSON.length(); ++k) {
                        curAnswers.add((String) curAnswersJSON.get(k));
                    }
                    pack.answers.add(curAnswers);
                }
                animalPacks.add(pack);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
