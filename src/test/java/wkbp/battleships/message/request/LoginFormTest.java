package wkbp.battleships.message.request;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class LoginFormTest {

    private LoginForm loginForm = new LoginForm("username1", "passwd1234");


    @Test
    public void testGetUsername() {
        String expectedUsername = "username1";
        String actualUsername = loginForm.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testGetPassword() {
        String expectedPassword = "passwd1234";
        String actualPassword = loginForm.getPassword();

        assertEquals(expectedPassword, actualPassword);
    }
}