package com.example.cst_338_project_2_group_9;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cst_338_project_2_group_9.Database.MaintenanceRepository;
import com.example.cst_338_project_2_group_9.entities.MaintenanceTask;
import java.util.ArrayList;
import java.util.List;

public class PlantMaintenanceActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;
    private Button addTaskButton;
    private TaskAdapter taskAdapter;
    private MaintenanceRepository repository;
    private int currentPlantId; // You'll need to get this from Intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_maintenance);
        currentPlantId = getIntent().getIntExtra("PLANT_ID", -1);

        // Set up the "View Details" button
        Button viewDetailsButton = findViewById(R.id.viewDetailsButton);
        viewDetailsButton.setOnClickListener(v -> {
            // Create intent to launch PlantDetailsActivity
            Intent intent = new Intent(PlantMaintenanceActivity.this, PlantDetailsActivity.class);

            // Pass the plant ID to the details activity
            intent.putExtra("PLANT_TYPE", "succulent");

            // Start the activity
            startActivity(intent);
        });



        // Get plant ID from intent
        currentPlantId = getIntent().getIntExtra("PLANT_ID", -1);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);

        // Initialize repository
        repository = new MaintenanceRepository(getApplication());

        // Setup RecyclerView
        taskAdapter = new TaskAdapter(new ArrayList<>());
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        // Observe tasks from database
        repository.getTasksForPlant(currentPlantId).observe(this, tasks -> {
            taskAdapter.updateTasks(tasks);
        });

        addTaskButton.setOnClickListener(v -> {
            showAddTaskDialog();
        });
    }

    private void showAddTaskDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task, null);

        EditText titleInput = dialogView.findViewById(R.id.taskTitleInput);
        EditText descriptionInput = dialogView.findViewById(R.id.taskDescriptionInput);
        EditText dueDateInput = dialogView.findViewById(R.id.taskDueDateInput);

        builder.setView(dialogView)
                .setTitle("Add New Task")
                .setPositiveButton("Add", (dialog, which) -> {
                    String title = titleInput.getText().toString();
                    String description = descriptionInput.getText().toString();
                    String dueDate = dueDateInput.getText().toString();

                    MaintenanceTask newTask = new MaintenanceTask(
                            currentPlantId,
                            title,
                            description,
                            dueDate,
                            false
                    );
                    repository.insert(newTask);
                })
                .setNegativeButton("Cancel", null);

        builder.create().show();
    }
    public static Intent intentFactory(Context context) {
        return new Intent(context, PlantMaintenanceActivity.class);
    }
}