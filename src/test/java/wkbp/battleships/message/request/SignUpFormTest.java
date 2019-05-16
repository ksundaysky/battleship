package wkbp.battleships.message.request;

import org.testng.annotations.Test;

import java.util.Collections;
import java.util.HashSet;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class SignUpFormTest {

    @Test
    private void testCreateSignupForm(){
        SignUpForm signUpForm = new SignUpForm("name","username","email",Collections.singleton("role"),"password");
        assertEquals("name",signUpForm.getName());
        assertEquals("username",signUpForm.getUsername());
        assertEquals("email",signUpForm.getEmail());
        assertEquals(1,signUpForm.getRole().size());
        assertEquals("password",signUpForm.getPassword());
    }

    @Test
    private void testCreateSignupFormEmpty(){
        SignUpForm signUpForm = new SignUpForm();
        assertNull(signUpForm.getName());
        assertNull(signUpForm.getUsername());
        assertNull(signUpForm.getEmail());
        assertNull(signUpForm.getPassword());
    }

}