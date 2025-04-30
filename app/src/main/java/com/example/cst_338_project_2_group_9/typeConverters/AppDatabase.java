package com.example.cst_338_project_2_group_9.typeConverters;

import android.content.Context;
import android.util.Log;

import com.example.cst_338_project_2_group_9.Database.UserDAO;
import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.MainActivity;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@TypeConverters(LocalDataTypeConverter.class)
@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String USER_TABLE = "userTable";
    private static final String DATABASE_NAME = "PlantDatabase";
    private static final int NUMBER_OF_THREADS = 4;
    private static volatile AppDatabase INSTANCE;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (AppDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),AppDatabase.class,DATABASE_NAME).addCallback(addDefaultValues).build();

                }
            }
        }
        return INSTANCE;
    }

    private static final RoomDatabase.Callback addDefaultValues = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db){
            super.onCreate(db);
            Log.i(MainActivity.TAG,"DATABASE CREATED!");
            databaseWriteExecutor.execute(()-> {
                UserDAO dao = INSTANCE.userDAO();
                dao.deleteAll();
                User admin = new User("admin1", "admin1",true);
                admin.setAdmin(true);
                dao.insert(admin);
                User testUser1 = new User("testuser1", "testuser1",false);
                dao.insert(testUser1);
            });
        }
    };

    public abstract UserDAO userDAO();
}