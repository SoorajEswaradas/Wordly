package com.example.admin.wordly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class route extends AppCompatActivity {



    public String score="";
    public int a=0;

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


                String uid=FirebaseAuth.getInstance().getCurrentUser().getUid();
                Firebase wordref=new Firebase("https://wordly-b22f0.firebaseio.com/levelprogress/"+uid);
                wordref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String word1 = dataSnapshot.getValue(String.class);
                        if (!TextUtils.isEmpty(word1) && TextUtils.isDigitsOnly(word1)) {
                            a = Integer.parseInt(word1);
                        } else {
                            a = 0;
                        }

                        //a shows levelprogress so only if score=100 ,quiz is to be conducted
                        if (score != null) {
                            if (score.equals("100")) {
                                finish();
                                Intent i = new Intent(route.this, slider.class);
                                startActivity(i);
                                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                            } else if (a==1) {
                                finish();
                                Intent i = new Intent(route.this, level1.class);
                                startActivity(i);
                            } else if (a==2) {
                                finish();
                                Intent i = new Intent(route.this, level2.class);
                                startActivity(i);
                            } else if (a==3) {
                                finish();
                                Intent i = new Intent(route.this, level3.class);
                                startActivity(i);
                            } else {
                                Toast.makeText(route.this, a, Toast.LENGTH_SHORT).show();
                                Toast.makeText(route.this, "Error", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(route.this, finalquiz.class);
                                startActivity(i);
                            }
                        }

                    }
                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });







    }
    }
