package com.lewi2015.saper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout root = (LinearLayout) findViewById(R.id.root);

        Cell.init(MainActivity.this);



        for (int i = 0; i < Cell.ROWS; i++) {
            LinearLayout row = new LinearLayout(MainActivity.this);
            row.setOrientation(LinearLayout.HORIZONTAL);
            row.setGravity(Gravity.CENTER);
            for (int j = 0; j < Cell.COLS; j++) {
                row.addView(Cell.cell[i][j]);
            }
            root.addView(row);
        }
    }
}
