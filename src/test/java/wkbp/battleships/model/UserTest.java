package wkbp.battleships.model;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

/**
 * @author Wiktor Rup
 */
public class UserTest {

    private User user;

    @BeforeMethod
    public void initializeUser() {
        user = new User("Staszek", "stachu", "stachu@gmail.com", "passwd1234");
    }

    @Test
    public void testGetName() {
        String expectedName = "Staszek";
        String actualName = user.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetUsername() {
        String expectedUsername = "stachu";
        String actualUsername = user.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testGetEmail() {
        String expectedEmail = "stachu@gmail.com";
        String actualEmail = user.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testGetPassword() {

        String expectedPassword = "passwd1234";
        String actualPassword = user.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }

    @Test
    public void testSetName() {
        String expectedName = "Zbyszek";
        user.setName(expectedName);
        String actualName = user.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testSetUsername() {
        String expectedUsername = "zbychu";
        user.setUsername(expectedUsername);
        String actualUsername = user.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testSetEmail() {
        String expectedEmail = "zbychu@gmail.com";
        user.setEmail(expectedEmail);
        String actualEmail = user.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testSetPassword() {
        String expectedPassword = "qwerty1234";
        user.setPassword(expectedPassword);
        String actualPassword = user.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }
}