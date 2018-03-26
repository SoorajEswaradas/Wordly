package com.example.admin.wordly;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

public class easy extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public TextView word;
    public TextView meaning;
    public TextView example1;
    public TextView example2;
    public FirebaseAuth firebaseauth;
    public FirebaseDatabase firebasedatabase;
    public int wordnumber=0;
    public String meaning1,examplea,exampleb,word1,link12;
    public StorageReference str;
    public MediaPlayer player;
    public String[] links;
    public ProgressBar pb;
    public TextView prognumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorbeginner));
        getSupportActionBar().setElevation(0);


        firebaseauth=FirebaseAuth.getInstance();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }
    public  void pronounce(View view)
    {
        try{
            player.start();
        }
        catch(Exception e)
        {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void next(View view) {
        updateword();
    }

    public void previous(View view) {

        if(wordnumber==1)
        {
            Toast.makeText(this, "This is the first word", Toast.LENGTH_SHORT).show();
        }
        else {
            wordnumber = wordnumber - 2;
            updateword();
        }
    }

    public void share(View view)
    {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT,"Wordly App\nWord:"+word1+"\nMeaning:"+meaning1+"\nExample 1:"+examplea+"\nExample 2:"+exampleb);

        try {
            this.startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "App not installed", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateword()
    {



        pb.setProgress((wordnumber+1));

        prognumber.setText((wordnumber+1)+"/"+pb.getMax());


        try {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(links[wordnumber]);
            player.prepare();
        } catch (Exception e) {
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show();
        }
        Firebase wordref,meaningref,example1ref,example2ref;
        wordref=new Firebase("https://wordly-b22f0.firebaseio.com/beginner/"+wordnumber+"/words");
        wordref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                word1=dataSnapshot.getValue(String.class);
                word.setText(word1);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        meaningref=new Firebase("https://wordly-b22f0.firebaseio.com/beginner/"+wordnumber+"/meaning");
        meaningref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                meaning1=dataSnapshot.getValue(String.class);
                meaning.setText(meaning1);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        example1ref=new Firebase("https://wordly-b22f0.firebaseio.com/beginner/"+wordnumber+"/example1");
        example1ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                examplea=dataSnapshot.getValue(String.class);
                example1.setText(examplea);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        example2ref=new Firebase("https://wordly-b22f0.firebaseio.com/beginner/"+wordnumber+"/example2");
        example2ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                exampleb=dataSnapshot.getValue(String.class);
                example2.setText(exampleb);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        wordnumber++;

        if(wordnumber==24)
        {
            finish();
            Toast.makeText(this, "Beginner level completed. Next level unlocked", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,level2.class);
            startActivity(i);
        }
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
        getMenuInflater().inflate(R.menu.easy, menu);
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
        int id = item.getItemId();


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void logout(MenuItem item)
    {
        firebaseauth.signOut();
        finish();
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }

}
