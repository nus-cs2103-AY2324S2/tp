package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class RoleBasedRelationshipTest {

    @Test
    public void testRoleBasedRelationshipCreation() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2);
        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
    }

    @Test
    public void testAddRoleAndGetRole() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2);
        String role1 = "role1";
        String role2 = "role2";

        relationship.addRole(person1, role1);
        relationship.addRole(person2, role2);

        assertEquals(role1, relationship.getRole(person1));
        assertEquals(role2, relationship.getRole(person2));
    }

    @Test
    public void testAddExceedingMaxRoles() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2);
        String role1 = "role1";
        String role2 = "role2";
        String role3 = "role3";

        relationship.addRole(person1, role1);
        relationship.addRole(person2, role2);

        assertThrows(IllegalStateException.class, () -> relationship.addRole(person2, role3));
    }

    @Test
    public void testGetAllRoles() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2);
        String role1 = "role1";
        String role2 = "role2";

        relationship.addRole(person1, role1);
        relationship.addRole(person2, role2);

        assertEquals(2, relationship.getAllRoles().size());
        assertTrue(relationship.getAllRoles().containsKey(person1));
        assertTrue(relationship.getAllRoles().containsKey(person2));
        assertEquals(role1, relationship.getAllRoles().get(person1));
        assertEquals(role2, relationship.getAllRoles().get(person2));
    }
}
