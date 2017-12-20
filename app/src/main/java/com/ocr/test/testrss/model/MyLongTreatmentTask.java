package com.ocr.test.testrss.model;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

/**
 * Created by bob on 20/12/17.
 */
//https://openclassrooms.com/courses/developpez-une-application-pour-android/realisez-des-taches-lourdes-sans-bloquer-l-application
public class MyLongTreatmentTask extends AsyncTask<Integer,Void,String> {

    private final TextView _textView;

    public MyLongTreatmentTask(TextView txt) {

        _textView = txt;
    }

    @Override
    protected String doInBackground(Integer... params) {
        int seconds = params[0];

        try { Thread.sleep(seconds * 1000); }
        catch (InterruptedException e) {}

        return "End of background task !";

    }

    @Override
    protected void onPostExecute(String result) {

        Log.i("ASYNC","Returning async tack : " + result);
        _textView.setText(result);

    }
}
