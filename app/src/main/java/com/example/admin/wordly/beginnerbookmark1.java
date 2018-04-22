package com.example.admin.wordly;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
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

public class beginnerbookmark1 extends AppCompatActivity {

    public TextView word;
    public TextView meaning;
    public TextView example1;
    public TextView example2;
    public FirebaseAuth firebaseauth;
    public FirebaseDatabase firebasedatabase;
    public int wordnumber=0;
    public Button bookmarkbutton;
    public String meaning1,examplea,exampleb,word1,bookmarkvalue,bookmarkvalue1;
    public StorageReference str;
    public MediaPlayer player;
    public String[] links;
    public String userid,currentlevel;
    public ProgressBar pb;
    public TextView prognumber;
    public int bookmarknumber=0;
    public Firebase mref;
    public int a=0,count;
    public static String SHARED_PREFS="begbook";
    public static String SHARED_PREFSbookmark="sharedpreferencesbookmark1";
    public ProgressDialog progress;
    Firebase wordref,meaningref,example1ref,example2ref,bookmarkref,bookmarkref1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beginnerbookmark1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(this, "hai", Toast.LENGTH_SHORT).show();
        bookmarkbutton=(Button)findViewById(R.id.bookmarkbutton);


        progress=new ProgressDialog(this);
        progress.setMessage("Loading");

        //loaddatabookmark();
        count=bookmarknumber;



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
        firebaseauth= FirebaseAuth.getInstance();
        firebasedatabase= FirebaseDatabase.getInstance();
        str= FirebaseStorage.getInstance().getReference();

        userid=firebaseauth.getInstance().getCurrentUser().getUid();
        updateword();
    }

    public void savedata()
    {
        SharedPreferences sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        SharedPreferences.Editor spe=sp.edit();
        spe.putInt(userid,wordnumber);

        spe.apply();
    }

    public void loaddata()
    {
        SharedPreferences sp=getSharedPreferences(SHARED_PREFS,MODE_PRIVATE);
        wordnumber=sp.getInt(userid,-1);


    }

    /* public void savedatabookmark()
     {
         SharedPreferences sp=getSharedPreferences(SHARED_PREFSbookmark,MODE_PRIVATE);
         SharedPreferences.Editor spe=sp.edit();
         spe.putInt(userid,bookmarknumber);
         spe.apply();
     }
 */
    public void loaddatabookmark()
    {
        SharedPreferences sp=getSharedPreferences(SHARED_PREFSbookmark,MODE_PRIVATE);
        bookmarknumber=sp.getInt(userid,0);

    }




    public void next(View view) {
        updateword();
    }

    public void previous(View view) {

        if(wordnumber==0)
        {
            Toast.makeText(this, "This is the first word", Toast.LENGTH_SHORT).show();
        }
        else {
            wordnumber = wordnumber - 2;
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
        wordnumber++;

        if(wordnumber==count)
        {


            wordnumber=-1;
           // savedata();
            finish();

            //Intent i=new Intent(this,bookmarkroute.class);
            //startActivity(i);
        }
        pb.setProgress((wordnumber+1));
        prognumber.setText((wordnumber+1)+"/"+pb.getMax());



        wordref=new Firebase("https://wordly-b22f0.firebaseio.com/beginnerbookmark/"+userid+"/"+wordnumber+"/word");
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

        meaningref=new Firebase("https://wordly-b22f0.firebaseio.com/beginnerbookmark/"+userid+"/"+wordnumber+"/meaning");
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

        example1ref=new Firebase("https://wordly-b22f0.firebaseio.com/beginnerbookmark/"+userid+"/"+wordnumber+"/example1");
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

        example2ref=new Firebase("https://wordly-b22f0.firebaseio.com/beginnerbookmark/"+userid+"/"+wordnumber+"/example2");
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
      //  savedata();

       /* bookmarkref=new Firebase("https://wordly-b22f0.firebaseio.com/beginner/"+wordnumber+"/bookmarked");
        bookmarkref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                bookmarkvalue=dataSnapshot.getValue(String.class);
                if(bookmarkvalue!=null&&bookmarkvalue.equalsIgnoreCase("1"))
                bookmarkbutton.setText("Bookmark");
                else
                    bookmarkbutton.setText("Bookmarked");

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

*/












    }
}
