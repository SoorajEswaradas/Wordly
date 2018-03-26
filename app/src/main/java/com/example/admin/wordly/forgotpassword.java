package com.example.admin.wordly;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgotpassword extends AppCompatActivity {
    private EditText regmail;
    private Button resetpass;
    private FirebaseAuth firebaseauth;
     public String mail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgotpassword);

        regmail=(EditText)findViewById(R.id.regmail);
        resetpass=(Button)findViewById(R.id.resetpass);
        firebaseauth= FirebaseAuth.getInstance();


        resetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mail=regmail.getText().toString().trim();
                if (mail.isEmpty())
                    Toast.makeText(forgotpassword.this, "Enter an email", Toast.LENGTH_SHORT).show();
                else {
                    firebaseauth.sendPasswordResetEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(forgotpassword.this, "Rest password link send to your email", Toast.LENGTH_SHORT).show();
                                finish();
                                Intent i = new Intent(forgotpassword.this, login.class);
                                startActivity(i);
                            } else
                                Toast.makeText(forgotpassword.this, "Enter a registered email id", Toast.LENGTH_SHORT).show();
                        }

                    });
                }
            }
        });


    }
}
