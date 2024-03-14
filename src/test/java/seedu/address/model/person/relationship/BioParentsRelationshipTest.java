// BioParentsRelationshipTest.java
package seedu.address.model.person.relationship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class BioParentsRelationshipTest {

    @Test
    public void testBioParentsRelationshipCreation() {
        UUID parentUuid = UUID.randomUUID();
        UUID childUuid = UUID.randomUUID();
        BioParentsRelationship bioParentsRelationship = new BioParentsRelationship(parentUuid, childUuid);
        assertEquals(parentUuid, bioParentsRelationship.getPerson1());
        assertEquals(childUuid, bioParentsRelationship.getPerson2());
        assertEquals("parent", bioParentsRelationship.getRole(parentUuid));
        assertEquals("child", bioParentsRelationship.getRole(childUuid));
    }
}
