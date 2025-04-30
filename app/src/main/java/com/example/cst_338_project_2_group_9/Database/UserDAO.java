package com.example.cst_338_project_2_group_9.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst_338_project_2_group_9.entities.User;
import com.example.cst_338_project_2_group_9.typeConverters.AppDatabase;

public interface UserDAO {
    public class UserDAO {
        @Insert
        void insert(User user);

        @Delete
        void delete(User user);

        @Update
        void update(User user);

        @Query("SELECT * FROM users")
        List<User> getAllUsers();
        @Query("SELECT * FROM users WHERE username = :username")
        LiveData<User> getUserByUserName(String username);
        @Query("DELETE FROM "+ UserDatabase.USER_TABLE)
        void deleteAll();

        @Query("SELECT * FROM users WHERE userId)
        // LiveData<User> getUserByUserId(int userId);

    }
}
