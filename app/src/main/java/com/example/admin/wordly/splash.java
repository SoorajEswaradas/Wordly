package com.example.admin.wordly;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class splash extends Activity {

    private static int timeout=3000;
    private static final int RC_SIGN_IN = 123;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);


      /*  AlarmManager alarm=(AlarmManager)getSystemService(ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND,5);

       Intent intent=new Intent("wordly.notification");
       PendingIntent broadcast= PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
       alarm.setExact(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),broadcast);

*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent intent=new Intent(splash.this,login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein,R.anim.fadeout);
                finish();
            }
        },timeout);
    }
}
