package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class RoleBasedRelationshipTest {

    @Test
    public void testRoleBasedRelationshipCreation() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2, "Family",
                "Parent", "Child");
        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
    }

    @Test
    public void testAddRoleAndGetRoleDescriptor() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2, "Family",
                "Parent", "Child");
        String role1 = relationship.getRoleDescriptor(person1);
        String role2 = relationship.getRoleDescriptor(person2);
        assertEquals(role1, "Parent");
        assertEquals(role2, "Child");
    }
}
