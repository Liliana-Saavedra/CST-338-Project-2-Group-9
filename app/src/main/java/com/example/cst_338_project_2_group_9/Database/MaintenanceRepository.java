package com.example.cst_338_project_2_group_9.Database;

import android.app.Application;
import androidx.lifecycle.LiveData;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;
import com.example.cst_338_project_2_group_9.entities.MaintenanceTask;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MaintenanceRepository {
    private MaintenanceTaskDAO maintenanceTaskDao;
    private LiveData<List<MaintenanceTask>> allTasks;
    private final ExecutorService executor;

    public MaintenanceRepository(Application application) {
        executor = Executors.newSingleThreadExecutor();
        AppDatabase db = AppDatabase.getDatabase(application);
        maintenanceTaskDao = db.maintenanceTaskDao();
        allTasks = maintenanceTaskDao.getAllTasks();
    }

    public LiveData<List<MaintenanceTask>> getAllTasks() {
        return allTasks;
    }

    public LiveData<List<MaintenanceTask>> getTasksForPlant(int plantId) {
        return maintenanceTaskDao.getTasksForPlant(plantId);
    }

    public void insert(MaintenanceTask task) {
        executor.execute(() -> {
            maintenanceTaskDao.insertTask(task);
        });
    }

    public void update(MaintenanceTask task) {
        executor.execute(() -> {
            maintenanceTaskDao.updateTask(task);
        });
    }

    public void delete(MaintenanceTask task) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            maintenanceTaskDao.deleteTask(task);
        });
    }
}