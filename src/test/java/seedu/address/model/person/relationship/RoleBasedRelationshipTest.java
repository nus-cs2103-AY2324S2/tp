package seedu.address.model.person.relationship;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleBasedRelationshipTest {

    @Test
    public void addRole_getRole_returnsCorrectRole() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RoleBasedRelationship relationship = new RoleBasedRelationship(person1, person2);
        relationship.addRole(person1, "parent");
        relationship.addRole(person2, "child");
        assertEquals("parent", relationship.getRole(person1));
        assertEquals("child", relationship.getRole(person2));
    }
}
