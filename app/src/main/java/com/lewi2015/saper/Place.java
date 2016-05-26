package com.lewi2015.saper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Place extends Button {

    public boolean isMine = false;
    public boolean isOpen = false;
    public int y;
    public int x;


    public Place(final Context context, int i, int j) {
        super(context);
        super.setText("");
        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(55, 55);
        int margin = -5;
        param.setMargins(margin, margin, margin, margin);
        super.setLayoutParams(param);

        y = i;
        x = j;

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Cell.open(y,x,true);
                //Cell.rewrite(y,x,Cell.revision(y, x));
            }
        });
    }
}
