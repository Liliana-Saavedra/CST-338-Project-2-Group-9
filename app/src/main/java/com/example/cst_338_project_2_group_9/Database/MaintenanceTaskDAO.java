package com.example.cst_338_project_2_group_9.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_338_project_2_group_9.entities.MaintenanceTask;

import java.util.List;

@Dao
public interface MaintenanceTaskDAO {

    @Query("SELECT * FROM maintenance_tasks WHERE isCompleted = 0 ORDER BY dueDate ASC")
    List<MaintenanceTask> getUpcomingTasks();

    // Add this new query to get tasks for a specific plant that aren't completed
    @Query("SELECT * FROM maintenance_tasks WHERE plantId = :plantId AND isCompleted = 0")
    List<MaintenanceTask> getUpcomingTasksForPlant(int plantId);

    @Query("SELECT * FROM maintenance_tasks ORDER BY dueDate ASC")
    LiveData<List<MaintenanceTask>> getAllTasks();

    @Query("SELECT * FROM maintenance_tasks WHERE plantId = :plantId ORDER BY dueDate ASC")
    LiveData<List<MaintenanceTask>> getTasksForPlant(int plantId);

    @Insert
    void insertTask(MaintenanceTask task);

    @Update
    void updateTask(MaintenanceTask task);

    @Delete
    void deleteTask(MaintenanceTask task);
}