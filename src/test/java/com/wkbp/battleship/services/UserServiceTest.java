package com.wkbp.battleship.services;

import com.wkbp.battleship.model.User;
import org.mockito.Mockito;
import org.testng.annotations.Test;

import static org.mockito.Mockito.mock;
import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class UserServiceTest {

    private UserService userService = mock(UserService.class);
    private UserServiceImpl userServiceImpl = mock(UserServiceImpl.class);

    @Test
    public void testFindEmail(){

        Mockito.when(userService.findByEmailAndPassword("test", "test")).thenReturn(new User(1L, "test", "test", "test"));
        User user = userService.findByEmailAndPassword("test", "test");

        assertEquals("test", user.getEmail());
        assertEquals("test", user.getPassword());
        assertEquals("test", user.getNick());
        assertEquals(Long.valueOf(1L), user.getId());
    }

    @Test
    public void testImplFindEmail(){

        Mockito.when(userServiceImpl.findByEmailAndPassword("test", "test")).thenReturn(new User(1L, "test", "test", "test"));
        User user = userServiceImpl.findByEmailAndPassword("test", "test");

        assertEquals("test", user.getEmail());
    }

}