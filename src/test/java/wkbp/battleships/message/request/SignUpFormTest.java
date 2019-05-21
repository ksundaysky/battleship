package wkbp.battleships.message.request;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static org.testng.AssertJUnit.assertEquals;

/**
 * @author Wiktor Rup
 */
public class SignUpFormTest {


    private Set<String> role = new HashSet<>();
    private SignUpForm signUpForm;
    @BeforeMethod
    public void initializeSignUpForm() {
        role.add("USER_ROLE");
        signUpForm = new SignUpForm("Name1", "Username1", "email@gmail.com", role, "passwd1234");
    }

    @Test
    public void testGetName() {

        String expectedName = "Name1";
        String actualName = signUpForm.getName();

        assertEquals(expectedName, actualName);
    }

    @Test
    public void testGetUsername() {

        String expectedUsername = "Username1";
        String actualUsername = signUpForm.getUsername();

        assertEquals(expectedUsername, actualUsername);
    }

    @Test
    public void testGetEmail() {

        String expectedEmail = "email@gmail.com";
        String actualEmail = signUpForm.getEmail();

        assertEquals(expectedEmail, actualEmail);
    }

    @Test
    public void testGetRole() {

        Set<String> expectedRole = new HashSet<>();
        expectedRole.add("USER_ROLE");

        Set<String> actualRole = signUpForm.getRole();

        assertEquals(expectedRole, actualRole);
    }

    @Test
    public void testGetPassword() {

        String expectedPassword = "passwd1234";
        String actualPasswrod = signUpForm.getPassword();

        assertEquals(expectedPassword, actualPasswrod);
    }
}