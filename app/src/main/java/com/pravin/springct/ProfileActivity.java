package com.pravin.springct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.pravin.springct.databinding.ActivityProfileBinding;

public class ProfileActivity extends AppCompatActivity {
    String name;
    String phone;
    String mail;

    ActivityProfileBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProfileBinding.inflate(getLayoutInflater());

        SharedPreferences preferences;
        SharedPreferences.Editor editor;

        setContentView(binding.getRoot());

        preferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = preferences.edit();

        if (getIntent().hasExtra(UserResponse.KEY_name)) {
            name = getIntent().getStringExtra(UserResponse.KEY_name);
            phone = getIntent().getStringExtra(UserResponse.KEY_PHONE);
            mail = getIntent().getStringExtra(UserResponse.KEY_mail);

            editor.putString(UserResponse.KEY_name, name);
            editor.putString(UserResponse.KEY_PHONE, phone);
            editor.putString(UserResponse.KEY_mail, mail);
            editor.apply();

        }else {
            name =      preferences.getString(UserResponse.KEY_name, "");
            phone =     preferences.getString(UserResponse.KEY_PHONE, "");
            mail =      preferences.getString(UserResponse.KEY_mail, "");
        }


        binding.update.setOnClickListener(v -> {
            editor.putString(UserResponse.KEY_name, binding.name.getText().toString());
            editor.putString(UserResponse.KEY_PHONE, binding.phone.getText().toString());
            editor.putString(UserResponse.KEY_mail, binding.email.getText().toString());
            editor.apply();
            Toast.makeText(ProfileActivity.this, "Values updated", Toast.LENGTH_LONG).show();
        });

        binding.logout.setOnClickListener(v -> {
            editor.remove(UserResponse.KEY_mail);
            editor.remove(UserResponse.KEY_PHONE);
            editor.remove(UserResponse.KEY_mail);
            startActivity(new Intent(this, LoginScreen.class));
            finish();
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        binding.name.setText(name);
        binding.phone.setText(phone);
        binding.email.setText(mail);

    }
}