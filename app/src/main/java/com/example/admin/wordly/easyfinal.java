package com.example.admin.wordly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
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

public class easyfinal extends AppCompatActivity {

    public TextView word;
    public TextView meaning;
    public TextView example1;
    public TextView example2;
    public FirebaseAuth firebaseauth;
    public FirebaseDatabase firebasedatabase;
    public int wordnumber2=-1;
    public String meaning1,examplea,exampleb,word1,link12;
    public StorageReference str;
    public MediaPlayer player;
    public String[] links;
    String userid;
    public ProgressBar pb;
    public TextView prognumber;
    public Firebase mref;
    public int a=0;
    public static final String SHARED_PREFS="sharedpreferences1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easyfinal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        links=new String[25];
        links[0]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2Faback--_gb_1.mp3?alt=media&token=f3847e0f-8c9a-4716-a98f-8257d174a340";
        links[1]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2Fbaffle--_gb_1.mp3?alt=media&token=18688c0e-f2be-49e9-9373-aeb2b96054a0";
        links[2]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2Fcaricature--_gb_1.mp3?alt=media&token=476c2fa6-68fe-47d1-93c6-81ad3541b90e";
        links[3]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2Fdebilitate--_gb_1.mp3?alt=media&token=7d9be315-8de2-4450-a270-46590d519bc0";
        links[4]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2Feccentric--_gb_1.mp3?alt=media&token=e02f71bd-23df-49c1-8f9f-a5d2e1f601f2";
        links[5]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F05-fiend--_gb_1.mp3?alt=media&token=0f25feb9-4f6c-493c-b4b2-f2d0b58bf47e";
        links[6]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F06-gaunt.mp3?alt=media&token=5a7cecdd-8968-4bfa-be35-200447a3b556";
        links[7]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F07-hamper--_gb_1.mp3?alt=media&token=f9577ab6-f553-4fa2-a2b7-030e50608fce";
        links[8]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F08-ignominy--_gb_1.mp3?alt=media&token=62d2d0ee-a53a-49ba-8e1e-69f062cd59b7";
        links[9]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F09-jostle--_gb_1.mp3?alt=media&token=e17d85e7-5634-46a1-84bf-a29e4a2910fc";
        links[10]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F10-kindred--_gb_1.mp3?alt=media&token=fd80eb27-1b54-4c84-8828-cfe2188aebdc";
        links[11]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F11-languid--_gb_1.mp3?alt=media&token=417c0d4b-eebd-4abe-bbab-325322e1e62f";
        links[12]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F12-manifest--_gb_1.mp3?alt=media&token=19592fe3-696a-4c05-8217-8c1f68e09100";
        links[13]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F13-niche--_gb_1.mp3?alt=media&token=ad862d9a-0861-4ac0-9776-9b02e5cb8790";
        links[14]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F14-oblique--_gb_1.mp3?alt=media&token=5cddc4b2-0882-4d5c-ac16-9b1a5b9049fa";
        links[15]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F15-panorama--_gb_1.mp3?alt=media&token=33af1262-2bf5-4e8b-87ff-1633c159d95a";
        links[16]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F16-quagmire--_gb_1.mp3?alt=media&token=0d8b11dd-5cc1-4f12-9598-e098d5501177";
        links[17]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F17-rancid--_gb_1.mp3?alt=media&token=4f8bb37e-85bf-49b8-beab-88fb0d642f9e";
        links[18]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F18-surmise--_gb_1.mp3?alt=media&token=dd350e14-84e2-497b-ab73-015dd772a090";
        links[19]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F19-tactics--_gb_1.mp3?alt=media&token=a1a4421d-4cd0-4a35-b27c-9ff9062a09b6";
        links[20]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F20-unanimity--_gb_1.mp3?alt=media&token=e9fbc3df-8abb-4ce8-b0fd-40b133d165ab";
        links[21]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F21-valour--_gb_1.mp3?alt=media&token=cbcc8d97-10e2-447d-8250-f7b24f0e2f0c";
        links[22]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F22-wary--_gb_1.mp3?alt=media&token=797571d2-681e-444e-b2cc-1ef36a18b1a0";
        links[23]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F22-wary--_gb_1.mp3?alt=media&token=797571d2-681e-444e-b2cc-1ef36a18b1a0";
        links[24]="https://firebasestorage.googleapis.com/v0/b/wordly-b22f0.appspot.com/o/beginervoice%2F24-zephyr--_gb_1.mp3?alt=media&token=eb228b1c-d6a6-46c2-bd57-d5ca432736b1";









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

        userid=firebaseauth.getInstance().getCurrentUser().getUid();
        updateword();
    }

    public void savedata()
    {
        SharedPreferences sp1=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor spe=sp1.edit();
        spe.putInt(userid,wordnumber2);
        spe.apply();
    }

    public void loaddata()
    {
        SharedPreferences sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        wordnumber2=sp.getInt(userid,-1);


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

        if(wordnumber2==0)
        {
            Toast.makeText(this, "This is the first word", Toast.LENGTH_SHORT).show();
        }
        else {
            wordnumber2 = wordnumber2 - 2;
            savedata();
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
        loaddata();
        wordnumber2++;

        if(wordnumber2==25)
        {


            wordnumber2=-1;
            savedata();
            finish();
            Toast.makeText(this, "Beginner level completed. Next level unlocked", Toast.LENGTH_SHORT).show();
            Intent i=new Intent(this,level3.class);
            startActivity(i);
        }
        pb.setProgress((wordnumber2+1));
        prognumber.setText((wordnumber2+1)+"/"+pb.getMax());


        try {
            player = new MediaPlayer();
            player.setAudioStreamType(AudioManager.STREAM_MUSIC);
            player.setDataSource(links[wordnumber2]);
            player.prepare();
        } catch (Exception e) {
            Toast.makeText(this, "Error playing audio", Toast.LENGTH_SHORT).show();
        }
        Firebase wordref,meaningref,example1ref,example2ref;
        wordref=new Firebase("https://wordly-b22f0.firebaseio.com/easy/"+wordnumber2+"/word");
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

        meaningref=new Firebase("https://wordly-b22f0.firebaseio.com/easy/"+wordnumber2+"/meaning");
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

        example1ref=new Firebase("https://wordly-b22f0.firebaseio.com/easy/"+wordnumber2+"/example1");
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

        example2ref=new Firebase("https://wordly-b22f0.firebaseio.com/easy/"+wordnumber2+"/example2");
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


        userid=firebaseauth.getInstance().getCurrentUser().getUid();
        mref=new Firebase("https://wordly-b22f0.firebaseio.com/easyprogress");
        Firebase mrefchild=mref.child(userid);
        mrefchild.setValue(wordnumber2+1);

        savedata();



    }
}
