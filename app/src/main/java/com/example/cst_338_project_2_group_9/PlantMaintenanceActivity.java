package com.example.cst_338_project_2_group_9;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cst_338_project_2_group_9.entities.MaintenanceTask;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PlantMaintenanceActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;
    private final List<MaintenanceTask> taskList = new ArrayList<>();

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_maintenance);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        Button addTaskButton = findViewById(R.id.addTaskButton);

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(new TaskAdapter(taskList));
        loadTasks();

        addTaskButton.setOnClickListener(v -> {
            MaintenanceTask newTask = new MaintenanceTask(
                    "Water plant",
                    "Water the fern in the living room",
                    "2023-06-15",
                    false
            );
            taskList.add(newTask);
            Objects.requireNonNull(tasksRecyclerView.getAdapter()).notifyDataSetChanged();
        });
    }

    private void loadTasks() {
        // TODO: Replace with actual database query
        taskList.add(new MaintenanceTask(
                "Fertilize",
                "Add fertilizer to the rose bush",
                "2023-06-10",
                false
        ));
        taskList.add(new MaintenanceTask(
                "Prune",
                "Trim the bonsai tree",
                "2023-06-12",
                true
        ));
    }
}