package com.example.cst_338_project_2_group_9.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.ForeignKey;

@Entity(tableName = "maintenance_tasks")
public class MaintenanceTask {
    @PrimaryKey(autoGenerate = true)
    private int taskId;

    private int plantId; // Add this field
    private String title;
    private String description;
    private String dueDate;
    private boolean isCompleted;

    // Updated constructor
    public MaintenanceTask(int plantId, String title, String description, String dueDate, boolean isCompleted) {
        this.plantId = plantId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.isCompleted = isCompleted;
    }

    // Add getter and setter for plantId
    public int getPlantId() { return plantId; }
    public void setPlantId(int plantId) { this.plantId = plantId; }

    // Keep all other existing getters and setters
    public int getTaskId() { return taskId; }
    public void setTaskId(int taskId) { this.taskId = taskId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getDueDate() { return dueDate; }
    public void setDueDate(String dueDate) { this.dueDate = dueDate; }
    public boolean isCompleted() { return isCompleted; }
    public void setCompleted(boolean completed) { isCompleted = completed; }
}