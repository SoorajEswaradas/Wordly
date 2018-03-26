package com.example.admin.wordly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class route extends AppCompatActivity {



    public String score="";

    //private FirebaseDatabase fbdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_route);

        FirebaseAuth firebaseauth;
        firebaseauth=FirebaseAuth.getInstance();
        //fbdatabase=FirebaseDatabase.getInstance();

        String userid=firebaseauth.getCurrentUser().getUid();


        Firebase scoreref = new Firebase("https://wordly-b22f0.firebaseio.com/Scores/"+userid+"/score");
        scoreref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                score = dataSnapshot.getValue(String.class);


                if (score != null) {
                    if (score.equals("100")) {
                        finish();
                        Intent i = new Intent(route.this, slider.class);
                        startActivity(i);
                        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                    } else if (score.equals("0") || score.equals("1") || score.equals("2")) {
                        finish();
                        Intent i = new Intent(route.this, level1.class);
                        startActivity(i);
                    } else if (score.equals("3") || score.equals("4")) {
                        finish();
                        Intent i = new Intent(route.this, level2.class);
                        startActivity(i);
                    } else if (score.equals("5") || score.equals("6")) {
                        finish();
                        Intent i = new Intent(route.this, level3.class);
                        startActivity(i);
                    } else if (score.equals("7") || score.equals("8")) {
                        finish();
                        Intent i = new Intent(route.this, level4.class);
                        startActivity(i);
                    } else if (score.equals("9") || score.equals("10")) {
                        finish();
                        Intent i = new Intent(route.this, level5.class);
                        startActivity(i);
                    } else {
                        finish();
                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });






    }
    }
