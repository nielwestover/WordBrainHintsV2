package com.example.a2558.myapplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by a2558 on 1/9/2016.
 */
public class AnimalPack {
    public String level;
    public List<List<String>> answers = new ArrayList<List<String>>();

    @Override
    public String toString() {
        return this.level;
    }
}
