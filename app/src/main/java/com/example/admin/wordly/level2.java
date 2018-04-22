package com.example.admin.wordly;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.text.TextUtils;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;
import java.util.Map;

public class level2 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    public ProgressBar progressbareasy;
    public TextView progresstexteasy;
    public TextView progresspercent;
    public String word1;
    public int a=0;
    public FirebaseAuth firebaseauth;
    public ProgressDialog progress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level2);


       /* Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY,00);
        calendar.set(Calendar.MINUTE,37);
        calendar.set(Calendar.SECOND,00);

        Intent intent=new Intent(getApplicationContext(),notification.class);
        intent.setAction("MY_NOTIFICATION_MESSAGE");
        PendingIntent pendingIntent= PendingIntent.getBroadcast(this,100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager=(AlarmManager)getSystemService(ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);


        Toast.makeText(this, "Error in splash", Toast.LENGTH_SHORT).show();
*/
        progress=new ProgressDialog(this);
        progresspercent=(TextView)findViewById(R.id.progresspercent);
        progresstexteasy=(TextView)findViewById(R.id.progresstexteasy);
        progressbareasy=(ProgressBar)findViewById(R.id.progressbareasy);
        progressbareasy.setMax(24);
        String userid=firebaseauth.getInstance().getCurrentUser().getUid();
        Firebase wordref;
        wordref=new Firebase("https://wordly-b22f0.firebaseio.com/easyprogress/"+userid);
        wordref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                word1=dataSnapshot.getValue(String.class);
                if (!TextUtils.isEmpty(word1) && TextUtils.isDigitsOnly(word1)) {
                    a = Integer.parseInt(word1);
                } else {
                    a = 0;
                }
                progressbareasy.setProgress((a));
                progresstexteasy.setText("Progress : "+(a)+" out of "+progressbareasy.getMax()+" words completed");
                float percent=((float)a/(float)progressbareasy.getMax())*100;
                String percentfinal=String.valueOf(percent);
                percentfinal=String.format("%.2f",percent);
                progresspercent.setText(percentfinal+"%");


            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorbeginner));
        getSupportActionBar().setElevation(0);


        firebaseauth= FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume(){
        super.onResume();
        progress.dismiss();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.level2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
      /*  int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



    public void beginner(View view)
    {
        progress.setMessage("Loading...");
        progress.show();

        Intent i=new Intent(this,beginnerfinal.class);
        startActivity(i);

    }
    public void easy(View view)
    {
        progress.setMessage("Loading...");
        progress.show();

        Intent i=new Intent(this,easyfinal.class);
        startActivity(i);
    }



    public void expert(View view)
    {
        Toast.makeText(this, "Complete previous level to unlock this level", Toast.LENGTH_SHORT).show();
    }
    public void idioms(View view)
    {
        Intent i=new Intent(level2.this,idiomsfinal.class);
        startActivity(i);
    }

    public void extrawords(View view)
    {
        Intent i=new Intent(level2.this,extrawordsfinal.class);
        startActivity(i);
    }
    public void logout(MenuItem item)
    {
        Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
        firebaseauth.signOut();
        finish();
        Intent i=new Intent(this,login.class);
        startActivity(i);

    }

}
