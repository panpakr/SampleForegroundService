package com.ifpanpakr.sampleforegroundservice;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;

import androidx.core.app.NotificationChannelCompat;

import java.util.Random;

public class SampleService extends Service {

    private static String TAG = SampleService.class.getSimpleName();
    private static int NOTIFICATION_ID = new Random().nextInt();
    private String CHANNEL_ID = "SampleService";

    @Override
    public void onCreate() {
        super.onCreate();
        startForegroundNotification();
    }

    private void startForegroundNotification() {
        createNotificationChannel();
        Notification mNotification = null;

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
             mNotification = new Notification.Builder(this)
                    .setChannelId(CHANNEL_ID)
                    .setOngoing(true)
                    .setContentText(getText(R.string.notification_message))
                    .setContentTitle(getString(R.string.notification_title))
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .build();
        }
        startForeground(NOTIFICATION_ID,mNotification);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private void createNotificationChannel() {

        int mImportance = NotificationManager.IMPORTANCE_DEFAULT;
        String mChannelName = getString(R.string.channel_name);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mNotificationChannel = new NotificationChannel(CHANNEL_ID,mChannelName,mImportance);
            mNotificationChannel.setDescription(getString(R.string.channel_description));
            mNotificationManager.createNotificationChannel(mNotificationChannel);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
