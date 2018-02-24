package com.example.admin.wordly;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class slider extends AppCompatActivity {
    private ViewPager viewpager;
    private slideadapter myadapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);
            viewpager = (ViewPager) findViewById(R.id.viewpager);
            myadapter = new slideadapter(this);
            viewpager.setAdapter(myadapter);

    }

}
