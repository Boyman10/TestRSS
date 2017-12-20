package com.ocr.test.testrss.model;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by bob on 20/12/17.
 */
//https://openclassrooms.com/courses/developpez-une-application-pour-android/affichez-du-contenu-de-l-internet
public class XMLAsyncTask  extends AsyncTask<String,Void,Document> {

    interface DocumentConsumer {

        void setXMLDocument(Document document);
    }

    private DocumentConsumer _consumer;

    public XMLAsyncTask(DocumentConsumer cons) {

        _consumer = cons;
    }

    @Override
    protected Document doInBackground(String... params) {
        int seconds = 5;

        try {

            // testing purpose to mimic internet connection
            Thread.sleep(seconds * 1000);

            URL url = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream stream = conn.getInputStream();

            try {
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            }
            finally {

                stream.close();
            }

        }
        catch (InterruptedException e) {
            Log.e("XMLAsyncTask", "Thread interrompu" , e);
            return null;
        }
        catch (Exception e) {

            Log.e("XMLAsyncTask", "Exception while downloading" , e);
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onPostExecute(Document result) {

        Log.i("XMLAsyncTask","Async Task end ");
        _consumer.setXMLDocument(result);

    }
}

