package com.example.a2558.myapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Answer> {
    public CustomAdapter(Context context, List<Answer> users) {
        super(context, 0, users);
     }

    static class ViewHolder {
        public SingleWordView singleWordView;
        public Button button;
    }

     @Override
     public View getView(int position, View convertView, final ViewGroup parent) {
         View rowView = convertView;
        // Get the data item for this position
        ViewHolder viewHolder = null;
        // Check if an existing view is being reused, otherwise inflate the view
        if (rowView == null) {
           rowView = LayoutInflater.from(getContext()).inflate(R.layout.single_word_with_button, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.singleWordView = (SingleWordView) rowView.findViewById(R.id.wordView);
            viewHolder.button = (Button) rowView.findViewById(R.id.getHint);
            rowView.setTag(viewHolder);
        }
         else{
            viewHolder=(ViewHolder)rowView.getTag();
        }

         Answer a = getItem(position);

         viewHolder.singleWordView.setAnswer(a.answer);
         viewHolder.button.setOnClickListener(new MyOnClickListener(viewHolder.singleWordView));

        return rowView;
    }
}
