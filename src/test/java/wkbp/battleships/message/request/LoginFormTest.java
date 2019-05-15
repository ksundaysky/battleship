package wkbp.battleships.message.request;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class LoginFormTest {


    @Test
    private void testCreatingLoginForm(){
         LoginForm loginForm = new LoginForm("username","password");
         assertEquals("username",loginForm.getUsername());
         assertEquals("password",loginForm.getPassword());
    }

    @Test
    private void testCreatingLoginFormEmptyConstructor(){
        LoginForm loginForm = new LoginForm();
        assertNull(loginForm.getPassword());
        assertNull(loginForm.getUsername());
    }

}