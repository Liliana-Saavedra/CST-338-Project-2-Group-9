package com.example.cst_338_project_2_group_9;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.databinding.ActivityLoginBinding;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);
        insertPredefinedUsers();

        binding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyUser();
            }

        });
    }

    private void verifyUser() {
        String username = binding.userNameLogInEditText.getText().toString();
        String password = binding.passwordLogInEditText.getText().toString();

        if (username.isEmpty() || password.isEmpty()) {
            toastMaker("Username and password must not be blank");
            return;
        }

        // Execute on background thread
        new Thread(() -> {
            User user = db.userDAO().getUserByUsername(username);
            runOnUiThread(() -> {
                if (user != null) {
                    if (password.equals(user.getPassword())) {
                        getSharedPreferences("my_prefs", MODE_PRIVATE).edit()
                                .putString("username", user.getUsername())
                                .apply();
                        if (user.isAdmin()) {
                            startActivity(AdminLandingPage.intentFactory(getApplicationContext()));
                        } else {
                            startActivity(LandingPage.intentFactory(getApplicationContext()));
                        }
                        finish();
                    } else {
                        toastMaker("Invalid Password! Please try again.");
                        binding.passwordLogInEditText.setText("");
                    }
                } else {
                    toastMaker(String.format("The username '%s' does not exist!", username));
                    binding.userNameLogInEditText.setText("");
                }
            });
        }).start();
    }

    private void insertPredefinedUsers() {
        new Thread(() -> {
            List<User> users = db.userDAO().getAllUsers();
            if (users == null || users.isEmpty()) {
                User testUser = new User("testuser1", "testuser1", false);
                User adminUser = new User("admin2", "admin2", true);
                db.userDAO().insert(testUser, adminUser);
            }
        }).start();
    }

    private void toastMaker(String message) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();
    }

    public static Intent intentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }
}