package com.ocr.test.testrss.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.ocr.test.testrss.R;

public class FragActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frag);

        setTitle(getIntent().getStringExtra("title"));

        MainFragment frag = MainFragment.create(getIntent().getStringExtra("saveTag"));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.mainFrg, frag)
                .commit();
    }
}
