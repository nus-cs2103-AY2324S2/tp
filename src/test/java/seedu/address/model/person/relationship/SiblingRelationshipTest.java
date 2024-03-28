package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class SiblingRelationshipTest {

    @Test
    public void constructor_validParameters_success() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();

        SiblingRelationship relationship = new SiblingRelationship(person1, person2);

        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
        assertEquals("sibling", relationship.getRoleDescriptor(person1));
        assertEquals("sibling", relationship.getRoleDescriptor(person2));
    }
}
