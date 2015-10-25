package com.cn.balala.nature.nature.test;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cn.balala.nature.R;
import com.cn.balala.nature.nature.acitivity.MainActivity;

public class NotificationActivity extends Activity {

    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        button = (Button) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onClick(View view) {
                NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(NotificationActivity.this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationActivity.this,
                        0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                Notification.Builder builder = new Notification.Builder(NotificationActivity.this)
                        .setTicker("This is a ticket!")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("This is title")
                        .setContentText("This is text")
                        .setContentIntent(pendingIntent);
                Notification notification = builder.build();
//                long[] vibrates={0,1000,1000,1000};
//                notification.vibrate=vibrates;
//                notification.ledARGB= Color.GREEN;
//                notification.ledOnMS=1000;
//                notification.ledOffMS=1000;
//                notification.flags=Notification.FLAG_SHOW_LIGHTS;
                notification.defaults=Notification.DEFAULT_ALL;
                manager.notify(1, notification);
            }
        });
    }
}
