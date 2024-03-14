package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class RelationshipTest {

    private Relationship relationship;

    @BeforeEach
    public void setUp() {
        UUID person1 = UUID.randomUUID();
        UUID person2 = UUID.randomUUID();
        relationship = new Relationship(person1, person2) {
        };
    }

    @Test
    public void getPerson1_returnsCorrectUUID() {
        UUID person1 = relationship.getPerson1();
        assertEquals(person1, relationship.getPerson1());
    }

    @Test
    public void getPerson2_returnsCorrectUUID() {
        UUID person2 = relationship.getPerson2();
        assertEquals(person2, relationship.getPerson2());
    }
}
