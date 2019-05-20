package wkbp.battleships.message.request;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Wiktor Rup
 */
public class LoginFormTest {

    LoginForm loginForm = new LoginForm("username1", "passwd1234");


    @Test
    public void testGetUsername() {
        String expectedUsername = "username1";
        String actualUsername = loginForm.getUsername();

        assert expectedUsername.equals(actualUsername);
    }

    @Test
    public void testGetPassword() {
        String expectedPassword = "passwd1234";
        String actualPasswrod = loginForm.getPassword();

        assert expectedPassword.equals(actualPasswrod);
    }
}