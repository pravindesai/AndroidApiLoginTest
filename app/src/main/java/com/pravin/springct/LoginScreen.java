package com.pravin.springct;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.pravin.springct.databinding.ActivityMainBinding;

public class LoginScreen extends AppCompatActivity {

    ActivityMainBinding binding;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new Repository();

        binding.login.setOnClickListener(v -> {

            if ((binding.username.getText().toString().equals("") || binding.username.getText().toString().isEmpty())){
                binding.username.setError("Required");
                binding.username.requestFocus();
                return;
            }
            if (binding.pass.getText().toString().equals("") || binding.pass.getText().toString().isEmpty()){
                binding.pass.setError("Required");
                binding.pass.requestFocus();
                return;
            }

            if ((binding.username.getText().toString().equals("Android test"))){
                repository.getUserData(new LoginResult());
                binding.progressBar.setVisibility(View.VISIBLE);
            }else {
                Toast.makeText(LoginScreen.this, "No user Found", Toast.LENGTH_LONG).show();
            }

        });
    }

    class LoginResult implements OnLoginResult{

        @Override
        public void onLoginSucess(UserResponse userResponse) {
            binding.progressBar.setVisibility(View.GONE);
            Intent intent =new Intent(LoginScreen.this, ProfileActivity.class);
            intent.putExtra(UserResponse.KEY_name, userResponse.getName());
            intent.putExtra(UserResponse.KEY_PHONE,userResponse.getPhone());
            intent.putExtra(UserResponse.KEY_mail, userResponse.getEmail());
            startActivity(intent);
            finish();
        }

        @Override
        public void onLoginFailed(String message) {
            binding.progressBar.setVisibility(View.GONE);
            Toast.makeText(LoginScreen.this, "Failed please check logs", Toast.LENGTH_LONG).show();
        }
    }
}