package com.example.cst_338_project_2_group_9.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.cst_338_project_2_group_9.entities.User;

import java.util.List;

@Dao
public interface UserDAO {
    @Insert
    void insert(User... user);

    @Delete
    void deleteUser(User user);

    @Query("SELECT * FROM users")
    List<User> getAllUsers();

    @Query("DELETE FROM users")
    void deleteAll();

    @Query("SELECT * FROM users WHERE username = :username AND password = :password")
    User login(String username, String password);

    @Query("SELECT * FROM users WHERE username = :username")
    User getUserByUsername(String username);

    }

