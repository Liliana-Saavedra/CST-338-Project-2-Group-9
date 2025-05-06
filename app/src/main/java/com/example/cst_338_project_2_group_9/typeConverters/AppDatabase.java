package com.example.cst_338_project_2_group_9.typeConverters;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cst_338_project_2_group_9.Database.MaintenanceTaskDAO;
import com.example.cst_338_project_2_group_9.Database.PlantDAO;
import com.example.cst_338_project_2_group_9.Database.UserDAO;
import com.example.cst_338_project_2_group_9.entities.MaintenanceTask;
import com.example.cst_338_project_2_group_9.entities.Plant;
import com.example.cst_338_project_2_group_9.entities.User;

import java.util.concurrent.Executor;

@TypeConverters(LocalDataTypeConverter.class)
@Database(entities = {User.class, Plant.class, MaintenanceTask.class},
        version = 3,  // Incremented to version 3
        exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static Executor databaseWriteExecutor;

    public abstract PlantDAO plantDao();
    public abstract UserDAO userDAO();
    public abstract MaintenanceTaskDAO maintenanceTaskDao();

    private static final String DATABASE_NAME = "PlantDatabase";
    private static volatile AppDatabase INSTANCE;

    // Migration from version 2 to 3 (fixes plantId constraint)
    static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            // Create temporary table with correct schema
            database.execSQL(
                    "CREATE TABLE IF NOT EXISTS plants_new (" +
                            "plantId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                            "name TEXT, " +
                            "species TEXT, " +
                            "description TEXT, " +
                            "lightRequirements TEXT, " +
                            "wateringInstructions TEXT, " +
                            "fertilizerSchedule TEXT, " +
                            "temperatureRange TEXT, " +
                            "dateAdded TEXT)");

            // Copy data from old table
            database.execSQL(
                    "INSERT INTO plants_new (plantId, name, species, description, " +
                            "lightRequirements, wateringInstructions, fertilizerSchedule, " +
                            "temperatureRange, dateAdded) " +
                            "SELECT plantId, name, species, description, lightRequirements, " +
                            "wateringInstructions, fertilizerSchedule, temperatureRange, dateAdded " +
                            "FROM plants");

            // Remove old table
            database.execSQL("DROP TABLE plants");

            // Rename new table
            database.execSQL("ALTER TABLE plants_new RENAME TO plants");
        }
    };

    public static AppDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                                    context.getApplicationContext(),
                                    AppDatabase.class,
                                    DATABASE_NAME)
                            .addMigrations(MIGRATION_2_3)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}