package com.example.myapplication.active;

import android.Manifest;
import android.annotation.SuppressLint;
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
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.myapplication.R;

import java.math.BigDecimal;

public class IncomeActive extends AppCompatActivity{

    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_activity);
    }

    public void clickAlert(View view){
        EditText editTextMoney = findViewById(R.id.editTextMoney);
        BigDecimal money = new BigDecimal(editTextMoney.getText().toString());
//        Log.v("money",money);

        EditText editTextTime = findViewById(R.id.editTextTime);
        int time = Integer.parseInt(editTextTime.getText().toString());
//        Log.v("time",time);

        EditText editTextRate = findViewById(R.id.editTextRate);
        BigDecimal rate =  new BigDecimal(editTextRate.getText().toString());
//        Log.v("rate",rate);

        BigDecimal income = money.multiply(rate.divide(new BigDecimal(100)).add(BigDecimal.ONE).pow(time)).setScale(2, BigDecimal.ROUND_HALF_UP);;

        TextView incomeView = findViewById(R.id.editTextIncome);
        incomeView.setText(income.toString());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }
}
