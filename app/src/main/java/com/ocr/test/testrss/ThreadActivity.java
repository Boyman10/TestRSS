package com.ocr.test.testrss;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.ocr.test.testrss.model.MyLongTreatmentTask;

public class ThreadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread);

        Animation animation = AnimationUtils.loadAnimation(ThreadActivity.this, R.anim.rotate);

        findViewById(R.id.imgView).startAnimation(animation);

        final Button btn = (Button) findViewById(R.id.btn);
        final TextView txtView =(TextView) findViewById(R.id.ttView);

        btn.setOnClickListener(new View.OnClickListener() {


            //https://openclassrooms.com/courses/developpez-une-application-pour-android/realisez-des-taches-lourdes-sans-bloquer-l-application
            // TODO THREADING
            @Override
            public void onClick(View v) {
                /*ASYNC TASK VERSION */
                final MyLongTreatmentTask task = new MyLongTreatmentTask(txtView);
                task.execute(2);

                /*
                * THREAD VERSION

                final Thread th = new Thread() {

                    @Override
                    public void run() {

                        try {
                            Thread.sleep(2000);

                        } catch (InterruptedException e) {}

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                txtView.setText("Coucou thread !");
                            }
                        });

                    }

                };

                th.start();

                */
            }

        });

    }
}
