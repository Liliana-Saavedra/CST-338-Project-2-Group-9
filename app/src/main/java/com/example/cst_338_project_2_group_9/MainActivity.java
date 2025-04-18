package com.example.cst_338_project_2_group_9;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check if user is already logged in
        SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
        if (prefs.contains("username")) {
            Intent intent = new Intent(this, LandingPage.class);
            startActivity(intent);
            finish();
        }
    }
}