package wkbp.battleships.message.response;

import org.springframework.security.core.GrantedAuthority;
import org.testng.annotations.Test;

import java.util.Collections;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class JwtResponseTest {

    private  JwtResponse jwtResponse = new JwtResponse("token","username", Collections.singleton((GrantedAuthority) () -> "role"));
    @Test
    public void testGetAccessToken() {
        assertEquals("token",jwtResponse.getAccessToken());
    }

    @Test
    public void testSetAccessToken() {
        jwtResponse.setAccessToken("token_set");
        assertEquals("token_set",jwtResponse.getAccessToken());

    }

    @Test
    public void testGetTokenType() {
        assertEquals("Bearer",jwtResponse.getTokenType());
    }

    @Test
    public void testSetTokenType() {
        jwtResponse.setTokenType("custom_type");
        assertEquals("custom_type",jwtResponse.getTokenType());

    }

    @Test
    public void testGetUsername() {
        assertEquals("username",jwtResponse.getUsername());
    }

    @Test
    public void testSetUsername() {
        jwtResponse.setUsername("new_username");
        assertEquals("new_username",jwtResponse.getUsername());

    }

    @Test
    public void testGetAuthorities() {
        assertEquals(1,jwtResponse.getAuthorities().size());
    }
}