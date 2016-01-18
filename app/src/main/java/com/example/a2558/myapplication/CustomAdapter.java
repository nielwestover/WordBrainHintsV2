package com.example.a2558.myapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Answer> {
    public CustomAdapter(Context context, List<Answer> users) {
        super(context, 0, users);
     }

     @Override
     public View getView(int position, View convertView, final ViewGroup parent) {
        // Get the data item for this position
        Answer a = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
           convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_word_with_button, parent, false);
        }
        // Lookup view for data population
        //TextView tvAnswer = (TextView) convertView.findViewById(R.id.answerText);
        //tvAnswer.setText(a.answer);
         SingleWordView wv = (SingleWordView) convertView.findViewById(R.id.wordView);
         wv.setAnswer(a.answer);
         Button b = (Button) convertView.findViewById(R.id.getHint);
         b.setOnClickListener(new MyOnClickListener(wv));
         //wv.
        // Return the completed view to render on screen
        return convertView;
    }
}
