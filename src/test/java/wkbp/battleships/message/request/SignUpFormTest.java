package wkbp.battleships.message.request;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

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

        assert expectedName.equals(actualName);
    }

    @Test
    public void testGetUsername() {

        String expectedUsername = "Username1";
        String actualUsername = signUpForm.getUsername();

        assert expectedUsername.equals(actualUsername);
    }

    @Test
    public void testGetEmail() {

        String expectedEmail = "email@gmail.com";
        String actualEmail = signUpForm.getEmail();

        assert expectedEmail.equals(actualEmail);
    }

    @Test
    public void testGetRole() {

        Set<String> expectedRole = new HashSet<>();
        expectedRole.add("USER_ROLE");

        Set<String> actualRole = signUpForm.getRole();

        assert expectedRole.equals(actualRole);
    }

    @Test
    public void testGetPassword() {

        String expectedPassword = "passwd1234";
        String actualPasswrod = signUpForm.getPassword();

        assert expectedPassword.equals(actualPasswrod);
    }
}