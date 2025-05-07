package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.cst_338_project_2_group_9.databinding.ActivityAdminLandingPageBinding;
import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;


public class AdminLandingPage extends AppCompatActivity {

    private ActivityAdminLandingPageBinding binding;

    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAdminLandingPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        db = AppDatabase.getDatabase(this);

        binding.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeUser();
            }
        });

        binding.homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PlantMaintenanceActivity.intentFactory(getApplicationContext()));
                intent.putExtra("isAdmin", true);
                startActivity(intent);
            }
        });

    }

    private void removeUser(){
        String userToRemove  = binding.removeUserEditText.getText().toString();
        String adminUser = binding.adminUserName.getText().toString();
        String adminPw = binding.adminPassword.getText().toString();

        if(userToRemove.isEmpty()){
            toastMaker("No user selected");
            return;
        }


        if(adminUser.isEmpty() || adminPw.isEmpty()){
            toastMaker("Invalid Login");
            return;
        }


        new Thread(() -> {
            User admin1 = db.userDAO().getUserByUsername(adminUser);
            if(adminPw.equals(admin1.getPassword())) {
                db.userDAO().deleteUser(db.userDAO().getUserByUsername(userToRemove));
            }
            else{
                toastMaker("Invalid Login");
            }
        }).start();
    }
    private void toastMaker(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    public static Intent AdminLandingPageIntentFactory(Context context) {
        return new Intent(context, AdminLandingPage.class);
    }
}