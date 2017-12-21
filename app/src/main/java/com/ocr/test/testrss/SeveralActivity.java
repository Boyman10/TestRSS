package com.ocr.test.testrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ocr.test.testrss.model.RSS3Adapter;
import com.ocr.test.testrss.model.XMLAsyncTask;

public class SeveralActivity extends AppCompatActivity {

    private XMLAsyncTask _task = null,_task1 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_several);

        setTitle("Internet browser");

        Log.i("SeveralActivity","onCreate event of activity ---");

        final RecyclerView rv = (RecyclerView) findViewById(R.id.list_recycle);
        rv.setLayoutManager(new LinearLayoutManager(this));
        RSS3Adapter adapter = new RSS3Adapter();
        rv.setAdapter(adapter);


        /* ASYNCTASKS NOT PARALLEL HERE : */
        Log.i("Several","Launching Async task...");
        _task = new XMLAsyncTask(adapter);
        _task.execute("http://www.lemonde.fr/rss/une.xml");
        Log.i("Several","task fetching url 1 executed");

        // Other urls :
        //https://www.melty.fr/actu.rss
        _task1 = new XMLAsyncTask(adapter);
        _task1.execute("https://www.melty.fr/actu.rss");
        Log.i("Several","task fetching url 2 executed");

        /* ENDING NOT PARALLEL ASYNC TASKS*/

        //https://developer.android.com/reference/android/os/AsyncTask.html
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("SeveralActivity","onDestroy event of activity ---");
        if (_task != null)
            _task.cancel(true);
        if (_task1 != null)
            _task1.cancel(true);
    }
}