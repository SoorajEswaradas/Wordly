package com.example.admin.wordly;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;

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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class finalquiz extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FirebaseAuth firebaseAuth;
    private TextView question;
    public TextView scored;
    public FirebaseDatabase fbdatabase;

    private Button choice1,choice2,choice3,choice4;
    private TextView qtn;
    public int score=0;
    private int qtnnumber=0;
    private String ans="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finalquiz);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        firebaseAuth=FirebaseAuth.getInstance();
        fbdatabase=FirebaseDatabase.getInstance();

        question=(TextView)findViewById(R.id.question);
        choice1=(Button)findViewById(R.id.choice1);
        choice2=(Button)findViewById(R.id.choice2);
        choice3=(Button)findViewById(R.id.choice3);
        choice4=(Button)findViewById(R.id.choice4);
        scored=(TextView)findViewById(R.id.scored);
        qtn=(TextView)findViewById(R.id.qtn);

        updatequestion();

        choice1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice1.getText().equals(ans))
                {
                    score++;
                    Toast.makeText(finalquiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                    updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(finalquiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                    updatequestion();
                }
            }
        });

        choice2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice2.getText().equals(ans))
                {
                    score++;
                    Toast.makeText(finalquiz.this, "Right answer", Toast.LENGTH_SHORT).show();

                    updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(finalquiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                    updatequestion();
                }
            }
        });

        choice3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice3.getText().equals(ans))
                {
                    score++;
                    Toast.makeText(finalquiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                    updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(finalquiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                    updatequestion();
                }
            }
        });

        choice4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(choice4.getText().equals(ans))
                {
                    score++;
                    Toast.makeText(finalquiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                    updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(finalquiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                    updatequestion();
                }
            }
        });
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
        getMenuInflater().inflate(R.menu.finalquiz, menu);
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
        /*int id = item.getItemId();

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


    private void updatescore(int score)
    {
        scored.setText(""+score);
    }

    public void updatequestion()
    {
        Firebase questionref,choice1ref,choice2ref,choice3ref,choice4ref,answerref;
        questionref = new Firebase("https://wordly-b22f0.firebaseio.com/quiz/"+qtnnumber+"/question");
        questionref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question1=dataSnapshot.getValue(String.class);
                question.setText(question1);
                String qtn1=String.valueOf(qtnnumber);
                if(qtnnumber<=10)
                qtn.setText(qtn1);
                
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        choice1ref = new Firebase("https://wordly-b22f0.firebaseio.com/quiz/"+qtnnumber+"/choice1");
        choice1ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choiceone=dataSnapshot.getValue(String.class);
                choice1.setText(choiceone);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        choice2ref = new Firebase("https://wordly-b22f0.firebaseio.com/quiz/"+qtnnumber+"/choice2");
        choice2ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choicetwo=dataSnapshot.getValue(String.class);
                choice2.setText(choicetwo);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        choice3ref = new Firebase("https://wordly-b22f0.firebaseio.com/quiz/"+qtnnumber+"/choice3");
        choice3ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choicethree=dataSnapshot.getValue(String.class);
                choice3.setText(choicethree);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        choice4ref = new Firebase("https://wordly-b22f0.firebaseio.com/quiz/"+qtnnumber+"/choice4");
        choice4ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choicefour=dataSnapshot.getValue(String.class);
                choice4.setText(choicefour);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        answerref = new Firebase("https://wordly-b22f0.firebaseio.com/quiz/"+qtnnumber+"/answer");
        answerref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ans=dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        qtnnumber++;


        if(qtnnumber>10)
        {
            finish();
            String userid = firebaseAuth.getCurrentUser().getUid();
            DatabaseReference db = fbdatabase.getReference().child("Scores").child(userid);

            String sco=String.valueOf(score);
            Map newmap = new HashMap();
            newmap.put("score",sco);
            db.setValue(newmap);


            if(score<=2)
            {
                finish();
                Intent i=new Intent(this,level1.class);
                startActivity(i);

            }
            else if(score<=4)
            {
                finish();
                Intent i=new Intent(this,level2.class);
                startActivity(i);
            }
            else if(score<=6)
            {
                finish();
                Intent i=new Intent(this,level3.class);
                startActivity(i);
            }
           if(score<=8)
            {
                Intent i=new Intent(this,level4.class);
                startActivity(i);
            }
            if(score<=10)
            {
                Intent i=new Intent(this,level5.class);
                startActivity(i);
            }
        }

    }

    public void logout(MenuItem item)
    {
        firebaseAuth.signOut();
        finish();
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }
}
