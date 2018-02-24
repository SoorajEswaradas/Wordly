package com.example.admin.wordly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class searchword extends AppCompatActivity {

    public String word1="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_searchword);
    }

    public void click(View view)
    {
        EditText word01=findViewById(R.id.word01);
        word1=word01.getText().toString();
        Intent i=new Intent(this,dictionary.class).putExtra("word1",word1);
        startActivity(i);

    }
}
