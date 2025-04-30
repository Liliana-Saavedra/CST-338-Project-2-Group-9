package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.databinding.ActivityLoginBinding;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;


public class LoginActivity extends AppCompatActivity {
    ActivityLoginBinding binding;
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
        User user = db.UserDAO().getUserByUsername(username);
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
    }
    private void insertPredefinedUsers() {
        if (db.UserDao().getUserCount() == 0) {
            db.UserDao().insert(new User("testuser1", "testuser1", false));
            db.UserDao().insert(new User("admin2", "admin2", true));
        }
    }
    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public static Intent intentFactory(Context context) {
        return new Intent(context, LoginActivity.class);
    }


}
