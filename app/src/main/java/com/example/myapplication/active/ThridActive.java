package com.example.myapplication.active;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

public class ThridActive extends AppCompatActivity{

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thridactivity);
    }

    public void clickAlert(View view){
        NotificationManager mNManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        ApplicationInfo appInfo = getApplicationInfo();
        int resID = getResources().getIdentifier("app", "drawable", appInfo.packageName);
        int id = view.getId();
        if(id==R.id.button_four) {
            Intent intent = new Intent(ThridActive.this,ThridActive.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);
            Notification notification;
            Notification.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                builder=new Notification.Builder(this,"5996773");
            }else {
                builder=new Notification.Builder(this);
            }
            //设置标题
            builder.setContentTitle("设置标题");
            //设置内容
            builder.setContentText("内容是............");
            //设置状态栏显示的图标，建议图标颜色透明
            builder.setSmallIcon(R.mipmap.ic_launcher);
            // 设置通知灯光（LIGHTS）、铃声（SOUND）、震动（VIBRATE）、（ALL 表示都设置）
            builder.setDefaults(Notification.DEFAULT_ALL);
            //灯光三个参数，颜色（argb）、亮时间（毫秒）、暗时间（毫秒）,灯光与设备有关
            builder.setLights(Color.RED, 200, 200);
            // 铃声,传入铃声的 Uri（可以本地或网上）我这没有铃声就不传了
            builder.setSound(Uri.parse("")) ;
            // 震动，传入一个 long 型数组，表示 停、震、停、震 ... （毫秒）
            builder.setVibrate(new long[]{0, 200, 200, 200, 200, 200});
            // 通知栏点击后自动消失
            builder.setAutoCancel(true);
            // 简单通知栏设置 Intent
            builder.setContentIntent(pendingIntent);
            builder.setPriority(Notification.PRIORITY_HIGH);

            //设置下拉之后显示的图片
            builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), resID));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                NotificationChannel channel = new NotificationChannel("5996773", "安卓10a", NotificationManager.IMPORTANCE_DEFAULT);
                channel.enableLights(true);//是否在桌面icon右上角展示小红点
                channel.setLightColor(Color.GREEN);//小红点颜色
                channel.setShowBadge(false); //是否在久按桌面图标时显示此渠道的通知
                mNManager.createNotificationChannel(channel);
            }
            notification=builder.build();
            mNManager.notify(1,notification);
        } else if (id == R.id.button_phone) {

            if (ContextCompat.checkSelfPermission(ThridActive.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
            {
                ActivityCompat.requestPermissions(ThridActive.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
            } else {


                EditText phone = findViewById(R.id.et_phone);
                String phoneText = phone.getText().toString().trim();
                if ("".equals(phoneText)) {
                    Toast.makeText(ThridActive.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel://" + phoneText));
                    startActivity(intent);
                }

//            Toast.makeText(ThridActive.this,phoneText,Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == MY_PERMISSIONS_REQUEST_CALL_PHONE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                EditText phone = findViewById(R.id.et_phone);
                String phoneText = phone.getText().toString().trim();
                if ("".equals(phoneText)) {
                    Toast.makeText(ThridActive.this, "电话号码不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel://" + phoneText));
                    startActivity(intent);
                }

            } else {
                // Permission Denied
                Toast.makeText(ThridActive.this,"Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
