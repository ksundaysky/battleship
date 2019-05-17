package wkbp.battleships.model;

import org.testng.annotations.Test;
import wkbp.battleships.dao.repository.entity.Role;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class UserTest {

    User user = new User("name","username","email","password");


    @Test
    public void testSetId() {
        user.setId(1L);
        assertEquals(java.util.Optional.of(1L).get(), user.getId());
    }
    @Test
    public void testGetId() {
        user.setId(2L);
        assertEquals(java.util.Optional.of(2L).get(), user.getId());
    }

    @Test
    public void testGetUsername() {
        assertEquals("username",user.getUsername());
    }

    @Test
    public void testSetUsername() {
        user.setUsername("new username");
        assertEquals("new username",user.getUsername());
    }

    @Test
    public void testGetName() {
        assertEquals("name",user.getName());
    }

    @Test
    public void testSetName() {
        user.setName("new name");
        assertEquals("new name",user.getName());
    }

    @Test
    public void testGetEmail() {
        assertEquals("email",user.getEmail());
    }

    @Test
    public void testSetEmail() {
        user.setEmail("new email");
        assertEquals("new email",user.getEmail());
    }

    @Test
    public void testGetPassword() {
        assertEquals("password",user.getPassword());
    }

    @Test
    public void testSetPassword() {
        user.setPassword("new password");
        assertEquals("new password",user.getPassword());
    }

    @Test
    public void testGetRoles() {
        User user1 = new User();
        Set<Role> roles = new HashSet<>();
        assertEquals(roles ,user1.getRoles());
    }

}