package com.ocr.test.testrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ocr.test.testrss.model.MyAdapter;
import com.ocr.test.testrss.model.MyFakeAdapter;

public class FakeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fake);

        final RecyclerView rv = (RecyclerView) findViewById(R.id.listFake);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(new MyFakeAdapter());
    }
}
