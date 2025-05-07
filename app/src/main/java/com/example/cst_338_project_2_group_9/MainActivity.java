package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.cst_338_project_2_group_9.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "Plant_Log";
    private static final String MAIN_ACTIVITY_USER_ID = "com.example.cst_338_project_2_group_9.MAIN_ACTIVITY_USER_ID";
    private static final int LOGGED_OUT = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.cst_338_project_2_group_9.databinding.ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        checkLoggedInState();

        binding.loginButton.setOnClickListener(v -> {
            startActivity(LoginActivity.intentFactory(getApplicationContext()));
            finish();
        });

        binding.createAccButton.setOnClickListener(v -> {
            Toast.makeText(this, "Create account clicked", Toast.LENGTH_SHORT).show();
            startActivity(CreateAccountActivity.intentFactory(getApplicationContext()));
        });
    }

    private void checkLoggedInState() {
        SharedPreferences sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE);
        if (sharedPreferences.contains("username")) {
            startActivity(LandingPage.landingPageIntentFactory(getApplicationContext()));
            finish();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(MAIN_ACTIVITY_USER_ID, LOGGED_OUT);
    }

    static Intent MainActivityIntentFactory(Context context, int userId) {
        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra(MAIN_ACTIVITY_USER_ID, userId);
        return intent;
    }
}