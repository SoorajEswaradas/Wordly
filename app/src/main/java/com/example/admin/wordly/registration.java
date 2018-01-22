package com.example.admin.wordly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
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

public class registration extends Activity implements View.OnClickListener {

    private EditText email;
    private EditText password;
    private Button register;
    private ProgressDialog progress;
    private FirebaseAuth firebaseauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registration);

        firebaseauth=FirebaseAuth.getInstance();

        email= (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        register =(Button) findViewById(R.id.register);
        progress=new ProgressDialog(this);

        register.setOnClickListener(this);


    }
    public void tologin(View view)
    {
        Intent intent=new Intent(this, login.class);
        startActivity(intent);
        finish();
    }

    private void registeruser() {
    String regemail= email.getText().toString().trim();
    String regpassword= password.getText().toString().trim();

    if(TextUtils.isEmpty(regemail))
    {
        Toast.makeText(this, "Please enter an Email ", Toast.LENGTH_SHORT).show();
        return;
    }
    if(TextUtils.isEmpty(regpassword))
        {
            Toast.makeText(this, "Please enter a Password ", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setMessage("Registering");
        progress.show();

        firebaseauth.createUserWithEmailAndPassword(regemail,regpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {   Toast.makeText(registration.this, "Registration Completed. Login to continue", Toast.LENGTH_SHORT).show();
                           finish();
                           Intent intent=new Intent(registration.this,login.class);
                           startActivity(intent);
                       }
                       else
                       {
                           Toast.makeText(registration.this, "Registration failed", Toast.LENGTH_SHORT).show();
                       }
                    }
                });
    }

    @Override
    public void onClick(View view) {
          if(view==register) {
          registeruser();
          }
    }
}
