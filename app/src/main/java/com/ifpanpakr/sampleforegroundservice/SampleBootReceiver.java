package com.ifpanpakr.sampleforegroundservice;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class SampleBootReceiver extends BroadcastReceiver {

   private final String TAG = SampleBootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG, "onReceive:  Intent - " + intent.toString());
        String action = intent.getAction();

        if(action.equals(Intent.ACTION_BOOT_COMPLETED)){

            Intent mServiceIntent = new Intent(context,SampleService.class);

            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.O ){
                context.startForegroundService(mServiceIntent);
            }else {
                context.startService(mServiceIntent);
            }
        }
    }
}
