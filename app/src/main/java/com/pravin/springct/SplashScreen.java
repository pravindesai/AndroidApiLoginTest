package com.pravin.springct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;

public class SplashScreen extends AppCompatActivity {

    private int SPLASH_TIME = 3000;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        preferences = PreferenceManager.getDefaultSharedPreferences(this);


        Log.e("**", "onCreate: "+preferences.contains(UserResponse.KEY_name) );
        new Handler().postDelayed(() -> {

            if (preferences.contains(UserResponse.KEY_name)){
                startActivity(new Intent(SplashScreen.this, ProfileActivity.class));
                finish();
            }else {
                startActivity(new Intent(SplashScreen.this, LoginScreen.class));
                finish();
            }

            }, SPLASH_TIME);

    }
}