package com.example.admin.wordly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class registration extends Activity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button register;
    private EditText phone;
    private EditText username;
    private EditText confirmpassword;
    private ProgressDialog progress;
    private FirebaseAuth firebaseauth;
    private FirebaseDatabase fbdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        firebaseauth=FirebaseAuth.getInstance();
        fbdatabase=FirebaseDatabase.getInstance();

        username=(EditText)findViewById(R.id.username);
        phone=(EditText)findViewById(R.id.phone);
        email= (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        confirmpassword=(EditText) findViewById(R.id.confirmpassword);
        register =(Button) findViewById(R.id.register);
        progress=new ProgressDialog(this);

        register.setOnClickListener(this);


    }
    public void tologin(View view)
    {
        Intent intent=new Intent(this, login.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);
        finish();
    }

    private void registeruser() {
        final String regemail = email.getText().toString().trim();
        String regpassword = password.getText().toString().trim();
        String confirmpass = confirmpassword.getText().toString().trim();

        if (TextUtils.isEmpty(regemail)) {
            Toast.makeText(this, "Please enter an Email ", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(regpassword)) {
            Toast.makeText(this, "Please enter a Password ", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Registering");
        progress.show();


        if (confirmpass.equals(regpassword))
        {
            firebaseauth.createUserWithEmailAndPassword(regemail, regpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(registration.this, "Registration Completed. Login to continue", Toast.LENGTH_SHORT).show();

                                String userid = firebaseauth.getCurrentUser().getUid();
                                DatabaseReference db = fbdatabase.getReference().child("Users").child(userid);

                                String uname = username.getText().toString();
                                String ph = phone.getText().toString();
                                String mailid = email.getText().toString();
                                String pass = password.getText().toString();


                                Map newmap = new HashMap();
                                newmap.put("username", uname);
                                newmap.put("phone", ph);
                                newmap.put("email", mailid);
                                newmap.put("password", pass);

                                db.setValue(newmap);

                                Map newmap1=new HashMap();
                                db=fbdatabase.getReference().child("Scores").child(userid);
                                String score="100";
                                newmap1.put("score",score);
                                db.setValue(newmap1);



                                firebaseauth.signOut();
                                finish();
                                Intent intent = new Intent(registration.this, login.class);
                                startActivity(intent);
                            } else {
                                progress.dismiss();
                                Toast.makeText(registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
        else
        {
            Toast.makeText(this, "Passwords entered should match", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
          if(view==register) {
          registeruser();
          }
    }
}
