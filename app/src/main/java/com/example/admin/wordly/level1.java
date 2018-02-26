package com.example.admin.wordly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
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
import android.view.WindowManager;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class level1 extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level1);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


      /* SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
       boolean firststart=prefs.getBoolean("firststart",true);

       if(firststart)
           showquiz();
*/
        firebaseauth=FirebaseAuth.getInstance();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

  /*  public void showquiz()
    {
    Intent intent=new Intent(this,finalquiz.class);
    startActivity(intent);

        SharedPreferences prefs=getSharedPreferences("prefs",MODE_PRIVATE);
        SharedPreferences.Editor editor=prefs.edit();
        editor.putBoolean("firststart",false);
        editor.apply();

    }
*/
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
        getMenuInflater().inflate(R.menu.level1, menu);
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
       /* int id = item.getItemId();

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
       Intent i=new Intent(this,level1.class);
       startActivity(i);

    }
    public void easy(View view)
    {
        Toast.makeText(this, "Complete previous level to unlock this level", Toast.LENGTH_SHORT).show();
    }

    public void intermediate(View view)
    {
        Toast.makeText(this, "Complete previous level to unlock this level", Toast.LENGTH_SHORT).show();
    }

    public void hard(View view)
    {
        Toast.makeText(this, "Complete previous level to unlock this level", Toast.LENGTH_SHORT).show();
    }
    public void expert(View view)
    {
        Toast.makeText(this, "Complete previous level to unlock this level", Toast.LENGTH_SHORT).show();
    }

    public void logout(MenuItem item)
    {
        Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
        firebaseauth.signOut();
        finish();
        Intent i=new Intent(this,login.class);
        startActivity(i);

    }
    public void exit(MenuItem item)
    {
        finish();
    }
}
