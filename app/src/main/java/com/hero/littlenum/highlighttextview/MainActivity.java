package com.hero.littlenum.highlighttextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        HighLightTextView tv1 = (HighLightTextView) findViewById(R.id.tv1);
        HighLightTextView tv2 = (HighLightTextView) findViewById(R.id.tv2);
        HighLightTextView tv3 = (HighLightTextView) findViewById(R.id.tv3);
        HighLightTextView tv4 = (HighLightTextView) findViewById(R.id.tv4);

        tv2.setDirection(HighLightTextView.RIGHT);
        tv2.setPeriod(5000);

        tv3.setDegress(45);

        tv4.setMode(HighLightTextView.START_END_END_START);
    }
}
