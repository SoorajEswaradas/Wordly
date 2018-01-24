package com.example.admin.wordly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class quiz extends AppCompatActivity {

    private TextView question;
    public TextView scored;

    private Button choice1,choice2,choice3,choice4;
    public int score=0;

    private int qtnnumber=0;
    private String ans="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);

        question=(TextView)findViewById(R.id.question);
        choice1=(Button)findViewById(R.id.choice1);
        choice2=(Button)findViewById(R.id.choice2);
        choice3=(Button)findViewById(R.id.choice3);
        choice4=(Button)findViewById(R.id.choice4);
        scored=(TextView)findViewById(R.id.scored);

     updatequestion();

    choice1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if(choice1.getText().equals(ans))
            {
                score++;
                Toast.makeText(quiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                updatescore(score);
                updatequestion();
            }
            else
            {
                Toast.makeText(quiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(quiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                    updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(quiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(quiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                   updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(quiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(quiz.this, "Right answer", Toast.LENGTH_SHORT).show();
                    updatescore(score);
                    updatequestion();
                }
                else
                {
                    Toast.makeText(quiz.this, "Wrong answer", Toast.LENGTH_SHORT).show();
                    updatequestion();
                }
            }
        });
    }

    private void updatescore(int score)
    {
        scored.setText(""+score);
    }

    public void updatequestion()
    {
        Firebase questionref,choice1ref,choice2ref,choice3ref,choice4ref,answerref;
        questionref = new Firebase("https://wordly-b22f0.firebaseio.com/"+qtnnumber+"/question");
        questionref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              String question1=dataSnapshot.getValue(String.class);
              question.setText(question1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        choice1ref = new Firebase("https://wordly-b22f0.firebaseio.com/"+qtnnumber+"/choice1");
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

        choice2ref = new Firebase("https://wordly-b22f0.firebaseio.com/"+qtnnumber+"/choice2");
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


        choice3ref = new Firebase("https://wordly-b22f0.firebaseio.com/"+qtnnumber+"/choice3");
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


        choice4ref = new Firebase("https://wordly-b22f0.firebaseio.com/"+qtnnumber+"/choice4");
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


        answerref = new Firebase("https://wordly-b22f0.firebaseio.com/"+qtnnumber+"/answer");
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

    }
}
