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

    // the consumer here being called is the ADAPTER
    public XMLAsyncTask(DocumentConsumer cons) {

        _consumer = cons;
        Log.i("XMLAsyncTask","Constructor called - filling consumer data");

    }

    @Override
    protected Document doInBackground(String... params) {
        int seconds = 5;

        try {

            Log.i("XMLAsyncTask","Entering background process - sleeping first :");
            // testing purpose to mimic internet connection
            Thread.sleep(seconds * 1000);

            URL url = new URL(params[0]);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            InputStream stream = conn.getInputStream();

            Log.i("XMLAsyncTask","Reading url document");

            try {

                Log.i("XMLAsyncTask","Returning parsed document from stream using DocumentBuilderFactory");
                //https://www.jmdoudoux.fr/java/dej/chap-dom.htm
                return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(stream);
            }
            finally {
                Log.i("XMLAsyncTask","Closing the stream");
                stream.close();
            }

        }
        catch (InterruptedException e) {
            Log.e("XMLAsyncTask", "Thread interrupted" , e);
            return null;
        }
        catch (Exception e) {

            Log.e("XMLAsyncTask", "Exception while downloading" , e);
            throw new RuntimeException(e);
        }

    }

    @Override
    protected void onPostExecute(Document result) {

        Log.i("XMLAsyncTask","Async Task ended - fill the consumer with document (iow : the adapter is retrieving the doc !)");
        _consumer.setXMLDocument(result);

    }
}

