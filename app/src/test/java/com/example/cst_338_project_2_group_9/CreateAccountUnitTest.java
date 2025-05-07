package com.example.cst_338_project_2_group_9;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import com.example.cst_338_project_2_group_9.entities.User;

import org.junit.Test;

public class CreateAccountUnitTest {
    // User START
    @Test
    public void newAccountTest() {
        User user1 = new User("userPass", "user1");
        assertNotNull(user1);
        assertEquals("user1",user1.getUsername());
        assertEquals("userPass", user1.getPassword());
        assertFalse(user1.isAdmin());

        User user2 = new User("verySecure", "user2");
        assertNotNull(user2);
        assertEquals("user2", user2.getUsername());
        assertEquals("verySecure", user2.getPassword());
        assertFalse(user2.isAdmin());
    }
    // User END

}
