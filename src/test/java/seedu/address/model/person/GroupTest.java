package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class GroupTest {
    @Test
    public void constructor_null() {
        Group group = new Group();
        assertEquals(group.getGroupNumber(), 0);
    }

    @Test
    public void constructor_valid() {
        Group group = new Group(5);
        assertEquals(group.getGroupNumber(), 5);
    }

    @Test
    public void isValidGroup() {
        // invalid group number
        assertFalse(Group.isValidGroup(-1)); // negative number
        assertTrue(Group.isValidGroup(0)); // zero

        // valid group number
        assertTrue(Group.isValidGroup(0)); // zero
        assertTrue(Group.isValidGroup(100));
    }

    @Test
    public void equals() {
        Group group = new Group(5);

        // same values -> returns true
        assertEquals(group, new Group(5));

        // different values -> returns false
        assertNotEquals(group, new Group(6));
    }

    @Test
    public void setTotalGroupNumber() {
        Group.setTotalGroupNumber(5);
        assertEquals(Group.getTotalGroupNumber(), 5);
    }
}
