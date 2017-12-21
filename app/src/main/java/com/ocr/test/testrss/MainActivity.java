package com.ocr.test.testrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Coucou !");

        // Working with action bar -- menu
        //getActionBar().

        /* Adding HTML from resource file */
        final TextView textView = (TextView) findViewById(R.id.textView);
        final InputStream stream = getResources().openRawResource(R.raw.content);
        final String test;

        // needs compile 'org.apache.commons:commons-io:1.3.2'
        try {
            test = IOUtils.toString(stream);

        } catch (IOException e) {

            throw new RuntimeException(e);
        }

        textView.setText(Html.fromHtml(test));

    }


    /* DEALING WITH ACTION BAR */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent myIntent;

            switch(item.getItemId()) {

                case R.id.action_edit:
                    /* DO EDIT*/
                    Log.i("TEST_RSS","Clic on menu edit item to launch second activity : Recycle Activity");
                    // we launch the second activity :
                    myIntent = new Intent(MainActivity.this, RecycleActivity.class);
                    startActivity(myIntent);
                    return true;

                case R.id.action_fake:
                    /* DO EDIT*/
                    Log.i("TEST_RSS","Clic on menu FAKE item to launch second activity : Fake Activity");
                    // we launch the second activity :
                    myIntent = new Intent(MainActivity.this, FakeActivity.class);
                    startActivity(myIntent);
                    return true;

                case R.id.action_thread:
                    /* DO EDIT*/
                    Log.i("TEST_RSS","Clic on menu THREAD item to launch second activity : Thread Activity");
                    // we launch the second activity :
                    myIntent = new Intent(MainActivity.this, ThreadActivity.class);
                    startActivity(myIntent);
                    return true;

                case R.id.action_internet:
                    /* DO EDIT*/
                    Log.i("TEST_RSS","Clic on menu INTERNET item to launch second activity : Internet Activity");
                    // we launch the second activity :
                    myIntent = new Intent(MainActivity.this, InternetActivity.class);
                    startActivity(myIntent);
                    return true;

                case R.id.action_several:
                    /* DO EDIT*/
                    Log.i("TEST_RSS","Clic on menu SEVERAL RSS FEEDS item to launch 3 RSS Activity");
                    // we launch the second activity :
                    myIntent = new Intent(MainActivity.this, SeveralActivity.class);
                    startActivity(myIntent);
                    return true;
            }

        return super.onOptionsItemSelected(item);
    }
}
