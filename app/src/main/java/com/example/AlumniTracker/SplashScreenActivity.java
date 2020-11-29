package com.example.AlumniTracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashScreenActivity extends AppCompatActivity {
    //declaring timer variable or how long the splash screen will last
    int splash_screen_duration=4000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        //setting splash screen handler
        new Handler().postDelayed(() -> {
            Intent intent= new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
        }, splash_screen_duration);
    }
}