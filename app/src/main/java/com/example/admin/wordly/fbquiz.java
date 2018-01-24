package com.example.admin.wordly;

import android.app.Application;

import com.firebase.client.Firebase;



public class fbquiz extends Application{
    @Override
    public void onCreate() {

        super.onCreate();


        Firebase.setAndroidContext(this);
    }
}
