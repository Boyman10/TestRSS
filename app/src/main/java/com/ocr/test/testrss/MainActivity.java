package com.ocr.test.testrss;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ocr.test.testrss.controller.FragActivity;
import com.ocr.test.testrss.controller.WebActivity;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {

    // button to launch fragment
    private Button mButton, webButton;

    public MainActivity () {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Log.i("MAIN ACTIVITY","Calling onCreate - Perform initialization of all fragments and loaders.");


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Coucou !");

        // Working with action bar -- menu
        //getActionBar().

        /* Adding HTML from resource file */
        final TextView textView = (TextView) findViewById(R.id.textView);
        final InputStream stream = getResources().openRawResource(R.raw.content);
        final String test;

        mButton = findViewById(R.id.mButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent;
                // Code here executes on main thread after user presses button
                Log.i("TEST_RSS","Launching fragment activity");
                // we launch the second activity :
                myIntent = new Intent(MainActivity.this, FragActivity.class);
                myIntent.putExtra("saveTag","Waouh that's working nicely !! Intent all the way retrieved on my Fragment...");
                myIntent.putExtra("title","My Fragment");
                startActivity(myIntent);
            }
        });


        // other activity holding a fragment :
        webButton = findViewById(R.id.webButton);

        webButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent;
                // Code here executes on main thread after user presses button
                Log.i("TEST_RSS","Launching fragment activity with web view");
                // we launch the web activity :
                myIntent = new Intent(MainActivity.this, WebActivity.class);
                myIntent.putExtra("link","https://r-h-m.net");
                myIntent.putExtra("title","My Fragment");
                startActivity(myIntent);
            }
        });





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

    //https://developer.android.com/reference/android/support/v7/app/AppCompatActivity.html
    @Override
    protected void onStart() {

        super.onStart();
        Log.i("MAIN ACTIVITY","Calling onStart - dispacth onStart to all fragments");

    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.i("MAIN ACTIVITY","Calling onStop - dispacth onStop to all fragments");

    }

    @Override
    protected void onPostResume() {

        super.onPostResume();
        Log.i("MAIN ACTIVITY","Calling onPostResume - dispacth onResume to all fragments");

    }

    @Override
    protected void onResume() {

        super.onResume();
        Log.i("MAIN ACTIVITY","Calling onResume - dispacth onResume");

    }

}
