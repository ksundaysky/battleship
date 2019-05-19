package wkbp.battleships.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import wkbp.battleships.dao.repository.entity.Role;
import wkbp.battleships.dao.repository.entity.UserInGameEntity;

import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class UserTest {

    private User user;

    @BeforeMethod
    public void initializeUser(){
        user = new User("Staszek", "stachu", "stachu@gmail.com", "passwd1234");
    }

    @Test
    public void testGetName() {
        String expectedName = "Staszek";
        String actualName = user.getName();

        assert expectedName.equals(actualName);
    }

    @Test
    public void testGetUsername() {
        String expectedUsername = "stachu";
        String actualUsername = user.getUsername();

        assert expectedUsername.equals(actualUsername);
    }

    @Test
    public void testGetEmail() {
        String expectedEmail = "stachu@gmail.com";
        String actualEmail = user.getEmail();

        assert expectedEmail.equals(actualEmail);
    }

    @Test
    public void testGetPassword() {

        String expectedPassword = "passwd1234";
        String actualPassword = user.getPassword();

        assert expectedPassword.equals(actualPassword);
    }

    @Test
    public void testSetName() {
        String expectedName = "Zbyszek";
        user.setName(expectedName);
        String actualName = user.getName();

        assert expectedName.equals(actualName);
    }

    @Test
    public void testSetUsername() {
        String expectedUsername = "zbychu";
        user.setUsername(expectedUsername);
        String actualUsername = user.getUsername();

        assert expectedUsername.equals(actualUsername);
    }

    @Test
    public void testSetEmail() {
        String expectedEmail = "zbychu@gmail.com";
        user.setEmail(expectedEmail);
        String actualEmail = user.getEmail();

        assert expectedEmail.equals(actualEmail);
    }

    @Test
    public void testSetPassword() {
        String expectedPassword = "qwerty1234";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        assert expectedPassword.equals(actualPassword);
    }

}