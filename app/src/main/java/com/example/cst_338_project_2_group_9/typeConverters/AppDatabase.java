package com.example.cst_338_project_2_group_9.typeConverters;

import com.example.cst_338_project_2_group_9.entities.User;
import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();

}