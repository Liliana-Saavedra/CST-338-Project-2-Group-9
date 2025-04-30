package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cst_338_project_2_group_9.databinding.ActivityCreateaccountBinding;

public class CreateAccountActivity extends AppCompatActivity {

    private ActivityCreateaccountBinding binding;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        binding = ActivityCreateaccountBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createAccount();}
        });
    }

    private void createAccount(){
        String newUserId  = binding.createUserIDEditText.getText().toString();

    }


    public static Intent intentFactory(Context context) {
        return new Intent(context, CreateAccountActivity.class);
    }



}
