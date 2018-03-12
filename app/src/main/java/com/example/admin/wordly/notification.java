package com.example.admin.wordly;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.admin.wordly.R;
import com.example.admin.wordly.level1;

public class notification extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent notify=new Intent(context,level1.class);

        TaskStackBuilder tsb=TaskStackBuilder.create(context);
        tsb.addParentStack(level1.class);
        tsb.addNextIntent(notify);

        PendingIntent pi=tsb.getPendingIntent(100,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder=new NotificationCompat.Builder((context), "M_CH_ID");

        Notification notification=builder.setContentTitle("Today's word")
                .setContentText("Example word")
                .setTicker("New message")
                .setAutoCancel(true)
                .setSmallIcon(R.id.icon)
                .setContentIntent(pi).build();


        NotificationManager nm=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        nm.notify(0,notification);

    }
}
