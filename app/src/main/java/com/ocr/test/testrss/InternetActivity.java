package com.ocr.test.testrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.ocr.test.testrss.model.RSSAdapter;
import com.ocr.test.testrss.model.XMLAsyncTask;

public class InternetActivity extends AppCompatActivity {

    private XMLAsyncTask _task = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        setTitle("Internet browser");

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RSSAdapter adapter = new RSSAdapter();
        rv.setAdapter(adapter);

        _task = new XMLAsyncTask(adapter,3);
        _task.execute("https://fr.wikipedia.org/w/api.php?hidebots=1&days=7&limit=50&hideWikibase=1&action=feedrecentchanges&feedformat=rss");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(_task != null)
            _task.cancel(true);
    }
}
