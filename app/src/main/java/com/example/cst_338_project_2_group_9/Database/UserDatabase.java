package com.example.cst_338_project_2_group_9.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cst_338_project_2_group_9.entities.User;

@Database(entities = {User.class}, version = 1)
public abstract class UserDatabase extends RoomDatabase {
    public abstract UserDAO userDAO();
}
