package wkbp.battleships.model;

import org.testng.annotations.Test;
import wkbp.battleships.dao.repository.entity.Role;

import static org.testng.Assert.*;

/**
 * @author krzysztof.niedzielski
 */
public class RoleTest {

    private Role role = new Role(RoleName.ROLE_USER);


    @Test
    public void testGetId() {
        assertNull(role.getId());
    }

    @Test
    public void testSetId() {
        Role role1 = new Role();
        role1.setId(1L);
        assertEquals(java.util.Optional.of(1L).get(),role1.getId());
    }

    @Test
    public void testGetName() {
        assertEquals("ROLE_USER",role.getName().name());
    }

    @Test
    public void testSetName() {
        role.setName(RoleName.ROLE_ADMIN);
        assertEquals("ROLE_ADMIN",role.getName().name());

    }
}