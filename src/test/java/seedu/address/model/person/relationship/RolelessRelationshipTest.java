package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class RolelessRelationshipTest {

    @Test
    public void rolelessRelationship_shouldReturnCorrectUuids() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        RolelessRelationship relationship = new RolelessRelationship(person1, person2);
        assertEquals(person1, relationship.getPerson1());
        assertEquals(person2, relationship.getPerson2());
    }
}
