package com.example.myapplication.active;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class ThridActive extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thridactivity);
    }

    public void clickAlert(View view){
        ApplicationInfo appInfo = getApplicationInfo();
        int resID = getResources().getIdentifier("app", "drawable", appInfo.packageName);
        Toast.makeText(getApplicationContext(),"这是弹窗",Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class); //点击通知进入的界面
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setContentTitle("这是标题")
                .setContentText("我是内容，我是demo")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(resID)
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(),resID));

                //通过 builder.build() 拿到 notification
                mNotificationManager.notify(1, builder.build());
    }
}
