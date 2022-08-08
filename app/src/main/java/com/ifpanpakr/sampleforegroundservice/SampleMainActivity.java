package com.ifpanpakr.sampleforegroundservice;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SampleMainActivity extends AppCompatActivity {

    private Button mStart ;
    private Button mFinish ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        mStart = findViewById(R.id.bStart);
        mStart.setOnClickListener(mOnClickListener);

        mFinish = findViewById(R.id.bFinish);
        mFinish.setOnClickListener(mOnClickListener);
    }

    private View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

          switch (view.getId()){

              case R.id.bStart:
                  createStartForegroundService();
                  break;
              case R.id.bFinish:
                  callFinish();
                  break;

          }
        }
    };

    private void callFinish() {
        finish();
    }

    private void createStartForegroundService() {
        Intent mServiceIntent = new Intent(this,SampleService.class);

        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O ){
            startForegroundService(mServiceIntent);
        }else {
            startService(mServiceIntent);
        }
    }
}
