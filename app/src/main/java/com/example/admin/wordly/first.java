package com.example.admin.wordly;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class first extends Activity implements View.OnClickListener {

    private FirebaseAuth firebaseauth;
    private DatabaseReference data;
    private EditText name;
    private EditText address;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_first);

        firebaseauth=FirebaseAuth.getInstance();

        name=(EditText)findViewById(R.id.name);
        address=(EditText)findViewById(R.id.address);
        add=(Button) findViewById(R.id.add);

        add.setOnClickListener(this);

    }

    public void addinfo()
    {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hi sooraj");
       /*String addname=name.getText().toString().trim();
       String addaddress=address.getText().toString().trim();

       addinformation info= new addinformation(addname,addaddress);
        FirebaseUser user=firebaseauth.getCurrentUser();
        if(user.getUid()!=null)
        {
        data.child(user.getUid()).setValue(info);
        }
*/
        Toast.makeText(this, "Info added", Toast.LENGTH_SHORT).show();

    }


    public void signout(View view)
    {
        firebaseauth.signOut();
        finish();
        Intent intent=new Intent(this,login.class);
        startActivity(intent);
    }


    @Override
    public void onClick(View view) {
    if(view==add)
    {addinfo();
    }
    }
}
