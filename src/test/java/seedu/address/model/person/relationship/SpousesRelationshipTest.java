package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class SpousesRelationshipTest {

    @Test
    public void testSpousesRelationshipCreation() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        SpousesRelationship spousesRelationship = new SpousesRelationship(person1, person2);
        assertEquals(person1, spousesRelationship.getPerson1());
        assertEquals(person2, spousesRelationship.getPerson2());
    }
}
