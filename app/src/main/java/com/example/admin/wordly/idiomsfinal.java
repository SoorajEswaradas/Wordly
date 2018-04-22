package com.example.admin.wordly;

import android.app.ProgressDialog;
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

public class idiomsfinal extends AppCompatActivity {

    public TextView idiom;
    public TextView meaning;
    public TextView usage;
    public FirebaseAuth firebaseauth;
    public FirebaseDatabase firebasedatabase;
    public int wordnumber3=-1;
    public String meaning1,examplea,exampleb,word1,link12;
    public StorageReference str;
    public MediaPlayer player;
    public String[] links;
    String userid;
    public ProgressBar pb;
    public TextView prognumber;
    public Firebase mref;
    public int a=0;

    public static final String SHARED_PREFS="sharedpreferencesidiom";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idiomsfinal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);




        Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorbeginner));
        getSupportActionBar().setElevation(0);


        idiom=(TextView)findViewById(R.id.idiom);
        meaning=(TextView)findViewById(R.id.meaning);
        usage=(TextView)findViewById(R.id.usage);
        prognumber=(TextView)findViewById(R.id.prognumber);

        pb=(ProgressBar)findViewById(R.id.probar);
        pb.setMax(154);
        firebaseauth= FirebaseAuth.getInstance();
        firebasedatabase= FirebaseDatabase.getInstance();
        str= FirebaseStorage.getInstance().getReference();

        userid=firebaseauth.getInstance().getCurrentUser().getUid();
        updateword();
    }

    public void savedata()
    {
        SharedPreferences sp3=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor sp3e=sp3.edit();
        sp3e.putInt(userid,wordnumber3);
        sp3e.apply();
    }

    public void loaddata()
    {
        SharedPreferences sp3=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        wordnumber3=sp3.getInt(userid,-1);


    }


    public void next(View view) {
        updateword();
    }

    public void previous(View view) {

        if(wordnumber3==0)
        {
            Toast.makeText(this, "This is the first idiom", Toast.LENGTH_SHORT).show();
        }
        else {
            wordnumber3 = wordnumber3 - 2;
            savedata();
            updateword();
        }
    }

    public void share(View view)
    {
        Intent whatsappIntent = new Intent(Intent.ACTION_SEND);
        whatsappIntent.setType("text/plain");
        whatsappIntent.setPackage("com.whatsapp");
        whatsappIntent.putExtra(Intent.EXTRA_TEXT,"Wordly App\nIdiom:"+word1+"\nMeaning:"+meaning1+"\nUsage:"+examplea);

        try {
            this.startActivity(whatsappIntent);
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "WhatsApp not installed", Toast.LENGTH_SHORT).show();
        }
    }


    public void updateword()
    {
        loaddata();
        wordnumber3++;

        if(wordnumber3==154)
        {
            wordnumber3=-1;
            savedata();

        }
        pb.setProgress((wordnumber3+1));
        prognumber.setText((wordnumber3+1)+"/"+pb.getMax());



        Firebase wordref,meaningref,usageref;
        wordref=new Firebase("https://wordly-b22f0.firebaseio.com/idioms/"+wordnumber3+"/idioms");
        wordref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                word1=dataSnapshot.getValue(String.class);
                idiom.setText(word1);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        meaningref=new Firebase("https://wordly-b22f0.firebaseio.com/idioms/"+wordnumber3+"/meaning");
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

        usageref=new Firebase("https://wordly-b22f0.firebaseio.com/idioms/"+wordnumber3+"/usage");
        usageref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                examplea=dataSnapshot.getValue(String.class);
                usage.setText(examplea);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        /*userid=firebaseauth.getInstance().getCurrentUser().getUid();
        mref=new Firebase("https://wordly-b22f0.firebaseio.com/easyprogress");
        Firebase mrefchild=mref.child(userid);
        mrefchild.setValue(wordnumber3+1);
*/
        savedata();



    }
}
