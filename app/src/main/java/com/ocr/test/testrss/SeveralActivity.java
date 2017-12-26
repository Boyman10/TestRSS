package com.ocr.test.testrss;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.ocr.test.testrss.model.RSS3Adapter;
import com.ocr.test.testrss.model.XMLAsyncTask;
import com.ocr.test.testrss.model.XmlPoolAsyncTask;

public class SeveralActivity extends AppCompatActivity {

    private XMLAsyncTask _task = null,_task1 = null;
    private XmlPoolAsyncTask _poolTask = null;

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


        /* ASYNCTASKS NOT PARALLEL HERE :*/
        Log.i("Several","Launching Async task...");
        _task = new XMLAsyncTask(adapter,6);
        //_task.execute("http://www.lemonde.fr/rss/une.xml");
        Log.i("Several","task fetching url 1 executed");
        StartAsyncTaskInParallel(_task,"http://www.lemonde.fr/rss/une.xml");
        // Other urls :
        //https://www.melty.fr/actu.rss
        _task1 = new XMLAsyncTask(adapter,1);
       // _task1.execute("https://www.melty.fr/actu.rss");
        Log.i("Several","task fetching url 2 executed");
        StartAsyncTaskInParallel(_task1,"https://www.melty.fr/actu.rss");
        /* ENDING NOT PARALLEL ASYNC TASKS*/

        //https://developer.android.com/reference/android/os/AsyncTask.html
/*
        Log.i("Several","Launching Async task with multiple urls");
        _poolTask = new XmlPoolAsyncTask(adapter);
        _poolTask.execute("http://www.lemonde.fr/rss/une.xml","https://www.melty.fr/actu.rss");
        Log.i("Several","task fetching url 1 executed");
*/

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


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void StartAsyncTaskInParallel(XMLAsyncTask task,String ... params) {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, params);
        else
            task.execute(params);
    }

}