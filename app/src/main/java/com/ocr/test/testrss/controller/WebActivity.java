package com.ocr.test.testrss.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ocr.test.testrss.R;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        setTitle(getIntent().getStringExtra("title"));

        MainFragment frag = MainFragment.create(getIntent().getStringExtra("link"));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.webFrg, frag)
                .commit();
    }
}
