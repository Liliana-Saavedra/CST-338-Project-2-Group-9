package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cst_338_project_2_group_9.databinding.ActivityCreateaccountBinding;
import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;

public class CreateAccountActivity extends AppCompatActivity {

    private ActivityCreateaccountBinding binding;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        binding = ActivityCreateaccountBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_createaccount);

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();
            }
        });
    }

    private void createAccount(){
        String newUserId  = binding.createUserIDEditText.getText().toString();
        String newPassword = binding.createPasswordEditText.getText().toString();
        String verifyPassword = binding.verifyPasswordEditText.getText().toString();

        new Thread(() -> {
            if (newPassword.equals(verifyPassword)) {
                User newUser = new User(newPassword, newUserId, false);
                db.userDAO().insert(newUser);
                startActivity(LoginActivity.intentFactory(getApplicationContext()));
            }
        }).start();


    }


    public static Intent intentFactory(Context context) {
        return new Intent(context, CreateAccountActivity.class);
    }



}
