package com.example.admin.wordly;

import android.app.Activity;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends Activity implements View.OnClickListener {
    private static int timeout=3;
    private EditText email;
    private EditText password;
    private Button login;
    private FirebaseAuth firebaseauth;
    private ProgressDialog progress;
    //private EditText search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_login);

       // search=(EditText)findViewById(R.id.search);

        firebaseauth=FirebaseAuth.getInstance();

       if(firebaseauth.getCurrentUser()!=null) {
           finish();
           Intent intent=new Intent(login.this,route.class);
           startActivity(intent);
       }

       email=(EditText) findViewById(R.id.email);
       password=(EditText) findViewById(R.id.password);
       login=(Button)findViewById(R.id.login);

       progress=new ProgressDialog(this);
       login.setOnClickListener(this);

    }

    public void loginuser()
    {
        String logmail=email.getText().toString().trim();
        String logpassword=password.getText().toString().trim();

        if(TextUtils.isEmpty(logmail))
        {
            Toast.makeText(this, "Enter an email id", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(logpassword))
        {
            Toast.makeText(this, "Enter the password", Toast.LENGTH_SHORT).show();
            return;
        }

        progress.setMessage("Logging in");
        progress.show();

        firebaseauth.signInWithEmailAndPassword(logmail,logpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                       if(task.isSuccessful())
                       {

                           Toast.makeText(login.this, "Successfully Logged in", Toast.LENGTH_SHORT).show();
                           finish();
                           Intent intent=new Intent(login.this,route.class);
                           startActivity(intent);
                           overridePendingTransition(R.anim.fadein,R.anim.fadeout);

                       }
                       else
                       {
                           progress.dismiss();
                           Toast.makeText(login.this, "Please enter the correct username and password", Toast.LENGTH_SHORT).show();
                       }
                    }

                });
    }

    public void toregister(View view)
    {
        finish();
        Intent intent=new Intent(this, registration.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);


    }

    @Override
    public void onClick(View view) {
        if(view ==login)
        {
            loginuser();

        }
    }


    public void forgotpass(View view)
    {
        Intent i=new Intent(this,forgotpassword.class);
        startActivity(i);
        overridePendingTransition(R.anim.fadein,R.anim.fadeout);

    }





    /*public void searching(View v)
    {
        try {
            Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            String term = search.getText().toString();
            intent.putExtra(SearchManager.SUGGEST_URI_PATH_QUERY, term);
            startActivity(intent);
        } catch (Exception e) {
            // TODO: handle exception
        }

    }*/
}
