package com.shanjing.customprogressbar;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressbar1, progressbar2, progressbar3, progressbar4;
    private TextView tv;

    int progress = 0;
    Handler handler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0x111:
                    progressbar1.setProgress(progress);
                    progressbar2.setProgress(progress);
                    progressbar3.setProgress(progress);
                    progressbar4.setProgress(progress);
                    tv.setText("" + progress + "%");
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressbar1 = findViewById(R.id.progressbar1);
        progressbar2 = findViewById(R.id.progressbar2);
        progressbar3 = findViewById(R.id.progressbar3);
        progressbar4 = findViewById(R.id.progressbar4);
        tv = findViewById(R.id.tv);

        timer.schedule(task, 0, 100);
    }

    Timer timer = new Timer();
    TimerTask task = new TimerTask() {
        @Override
        public void run() {
            if (progress < 100) {
                progress++;
                handler.sendEmptyMessage(0x111);
            } else {
                handler.sendEmptyMessage(0x222);
                task.cancel();
                timer.cancel();
            }
        }
    };
}
