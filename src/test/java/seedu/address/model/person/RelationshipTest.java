package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class RelationshipTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Relationship(null));
    }

    @Test
    public void constructor_invalidAddress_throwsIllegalArgumentException() {
        String invalidRelationship = "";
        assertThrows(IllegalArgumentException.class, () -> new Address(invalidRelationship));
    }

    @Test
    public void isValidRelationship() {
        // null relationship
        assertThrows(NullPointerException.class, () -> Relationship.isValidRelationship(null));

        // invalid relationship
        assertFalse(Relationship.isValidRelationship("")); // empty string
        assertFalse(Relationship.isValidRelationship(" ")); // spaces only

        // valid relationship
        assertTrue(Relationship.isValidRelationship("client"));
        assertTrue(Relationship.isValidRelationship("partner"));
        assertTrue(Relationship.isValidRelationship("Client")); // uppercase
    }

    @Test
    public void equals() {
        Relationship relationship = new Relationship("client");

        // same values -> returns true
        assertTrue(relationship.equals(new Relationship("client")));

        // same object -> returns true
        assertTrue(relationship.equals(relationship));

        // null -> returns false
        assertFalse(relationship.equals(null));

        // different types -> returns false
        assertFalse(relationship.equals(5.0f));

        // different values -> returns false
        assertFalse(relationship.equals(new Address("partner")));
    }
}
