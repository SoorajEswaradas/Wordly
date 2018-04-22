package com.example.admin.wordly;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

public class bookmarkroute extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarkroute);


      /*  Window window=getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorbeginner));
        getSupportActionBar().setElevation(0);
*/
    }
    public void beginner(View view)
    {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(this,beginnerbookmark.class);

        startActivity(i);
    }
   public void intermediate(View view)
    {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(bookmarkroute.this,intermediatebookmark.class);
        startActivity(i);
    }

  public void expert(View view)
    {
        Toast.makeText(this, "Loading", Toast.LENGTH_SHORT).show();
        Intent i=new Intent(bookmarkroute.this,expertbookmark.class);
        startActivity(i);
    }

}
