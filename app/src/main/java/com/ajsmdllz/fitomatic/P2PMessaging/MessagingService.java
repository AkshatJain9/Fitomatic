package com.ajsmdllz.fitomatic.P2PMessaging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MessagingService extends FirebaseMessagingService {

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        String title = message.getNotification().getTitle();
        String body = message.getNotification().getBody();
        NotificationChannel notificationChannel = new NotificationChannel("HEADS_UP_NOTIFICATION",
                "Heads Up Notification", NotificationManager.IMPORTANCE_HIGH);

        getSystemService(NotificationManager.class).createNotificationChannel(notificationChannel);
        Notification.Builder notification = new Notification.Builder(this, "HEADS_UP_NOTIFICATION")
                .setContentTitle(title).setContentText(body).setAutoCancel(true);
        NotificationManagerCompat.from(this).notify(1, notification.build());
    }


}