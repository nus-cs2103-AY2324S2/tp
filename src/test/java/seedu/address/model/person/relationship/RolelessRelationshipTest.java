package seedu.address.model.person.relationship;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RolelessRelationshipTest {

    @Test
    public void rolelessRelationship_shouldReturnCorrectUUIDs() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RolelessRelationship relationship = new RolelessRelationship(person1, person2);
        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
    }
}
