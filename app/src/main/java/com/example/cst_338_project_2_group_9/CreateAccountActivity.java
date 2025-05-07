package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cst_338_project_2_group_9.databinding.ActivityCreateaccountBinding;
import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;


public class CreateAccountActivity extends AppCompatActivity {

    private ActivityCreateaccountBinding binding;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        binding = ActivityCreateaccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });

        binding.backHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(MainActivity.MainActivityIntentFactory(getApplicationContext(), 1));
            }
        });
    }

    private void createAccount(){
        String newUserId  = binding.createUserIDEditText.getText().toString();
        String newPassword = binding.createPasswordEditText.getText().toString();
        String verifyPassword = binding.verifyPasswordEditText.getText().toString();

        if (newUserId.isEmpty() || newPassword.isEmpty() || verifyPassword.isEmpty()) {
            toastMaker("UserID and password cannot be blank");
            return;
        }

        if (!newPassword.equals(verifyPassword)) {
            toastMaker("Passwords do not match");
            return;
        }

        new Thread(() -> {
            User newUser = new User(newPassword, newUserId, false);
            db.userDAO().insert(newUser);
        }).start();

        startActivity(LoginActivity.intentFactory(getApplicationContext()));

    }

    private void toastMaker(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public static Intent createAccountIntentFactory(Context context) {
        return new Intent(context, CreateAccountActivity.class);
    }



}
