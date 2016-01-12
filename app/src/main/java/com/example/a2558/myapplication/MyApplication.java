package com.example.a2558.myapplication;

import android.app.Application;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2558 on 1/11/2016.
 */
public class MyApplication extends Application {
    public static List<AnimalPack> animalPacks = new ArrayList<AnimalPack>();
    public static int curPackIndex;
    public static int curLevelInPack;
    public static AnimalPack curAnimalPack;

}
