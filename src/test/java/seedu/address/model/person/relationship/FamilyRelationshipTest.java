package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class FamilyRelationshipTest {

    @Test
    public void constructor_validParameters_success() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        String role1 = "parent";
        String role2 = "child";

        FamilyRelationship relationship = new FamilyRelationship(person1, person2, role1, role2);

        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
        assertEquals(role1, relationship.getRoleDescriptor(person1));
        assertEquals(role2, relationship.getRoleDescriptor(person2));
    }
}
