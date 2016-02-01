package com.afterglowapps.wordbrainhints.v1;

import android.view.View;

/**
 * Created by a2558 on 1/16/2016.
 */
public class MyOnClickListener implements View.OnClickListener
{
    private SingleWordView wv;
    public MyOnClickListener(SingleWordView wv) {
        this.wv = wv;
    }

    @Override
    public void onClick(View v)
    {
        wv.requestHint();
    }
};
