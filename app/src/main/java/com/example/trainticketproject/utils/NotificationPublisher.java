package com.example.trainticketproject.utils;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompatExtras;
import androidx.core.app.NotificationManagerCompat;

import com.example.trainticketproject.R;

public class NotificationPublisher extends BroadcastReceiver {
    private static final int REQUEST_PERMISSION = 1;
    public static final String CHANNEL_ID = "channel_id";
    public static final CharSequence CHANNEL_NAME = "REMINDER CHANNEL";
    public static String NOTIFICATION_ID = "notification_id";
    public static String NOTIFICATION_TITLE = "notification_title";
    public static String NOTIFICATION_CONTENT = "notification_content";

    @Override
    public void onReceive(Context context, Intent intent) {
        int notificationId = intent.getIntExtra(NOTIFICATION_ID, 0);
        String title = intent.getStringExtra(NOTIFICATION_TITLE);
        String content = intent.getStringExtra(NOTIFICATION_CONTENT);

        Log.d("NotificationPublisher", "Received broadcast. Notification ID: " + notificationId + ", Title: " + title + ", Content: " + content);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "channel_id")
                .setSmallIcon(R.drawable.baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(content)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[] { Manifest.permission.POST_NOTIFICATIONS }, REQUEST_PERMISSION);
            return;
        }

        notificationManager.notify(notificationId, builder.build());
    }


    public static void createNotificationChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    NotificationPublisher.CHANNEL_ID,
                    NotificationPublisher.CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            channel.setDescription("Channel Description");

            NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
