package com.example.cst_338_project_2_group_9;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cst_338_project_2_group_9.entities.MaintenanceTask;
import java.util.ArrayList;
import java.util.List;

public class PlantMaintenanceActivity extends AppCompatActivity {
    private RecyclerView tasksRecyclerView;
    private Button addTaskButton;
    private TaskAdapter taskAdapter;
    private List<MaintenanceTask> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_maintenance);

        tasksRecyclerView = findViewById(R.id.tasksRecyclerView);
        addTaskButton = findViewById(R.id.addTaskButton);

        taskAdapter = new TaskAdapter(taskList);

        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        tasksRecyclerView.setAdapter(taskAdapter);

        loadTasks();

        addTaskButton.setOnClickListener(v -> {
            MaintenanceTask newTask = new MaintenanceTask(
                    "Water plant",
                    "Water the fern in the living room",
                    "2023-06-15",
                    false
            );
            taskList.add(newTask);
            taskAdapter.notifyItemInserted(taskList.size() - 1);
        });
    }

    private void loadTasks() {
        taskList.clear();

        // TODO: Replace with actual database query
        taskList.add(new MaintenanceTask(
                "Fertilize",
                "Add fertilizer to the rose bush",
                "2025-06-10",
                false
        ));
        taskList.add(new MaintenanceTask(
                "Prune",
                "Trim the bonsai tree",
                "2025-06-12",
                true
        ));

        // Update the adapter
        taskAdapter.notifyDataSetChanged();
    }
}