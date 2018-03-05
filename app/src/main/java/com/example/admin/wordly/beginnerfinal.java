package com.example.admin.wordly;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
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
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.FileInputStream;

public class beginnerfinal extends AppCompatActivity {
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
        setContentView(R.layout.activity_beginnerfinal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        links=new String[5];
        links[0]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/aback--_gb_1.mp3?alt=media&token=db22ddc1-bcec-4a29-aa43-80b219446ffa";
        links[1]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/baffle--_gb_1.mp3?alt=media&token=93327c8a-2ea4-4376-a007-f0c6b3db1437";
        links[2]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/caricature--_gb_1.mp3?alt=media&token=087e2f2e-3e25-4679-bf0d-76229bc1b491";
        links[3]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/debilitate--_gb_1.mp3?alt=media&token=0f19d314-3a66-4ad4-9623-482f11c761d8";
        links[4]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/eccentric--_gb_1.mp3?alt=media&token=746fd878-302a-48fc-ac5c-81940815a0d6";


        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorbeginner));
        getSupportActionBar().setElevation(0);


        word=(TextView)findViewById(R.id.word);
        meaning=(TextView)findViewById(R.id.meaning);
        example1=(TextView)findViewById(R.id.example1);
        example2=(TextView)findViewById(R.id.example2);
        prognumber=(TextView)findViewById(R.id.prognumber);

        pb=(ProgressBar)findViewById(R.id.probar);
        pb.setMax(24);
        firebaseauth=FirebaseAuth.getInstance();
        firebasedatabase= FirebaseDatabase.getInstance();
        str= FirebaseStorage.getInstance().getReference();

       updateword();
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
}
