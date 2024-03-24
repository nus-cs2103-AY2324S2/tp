package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class GroupTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Group(null));
    }

    @Test
    public void constructor_invalidGroupName_throwsIllegalArgumentException() {
        String invalidGroupName = "";
        assertThrows(IllegalArgumentException.class, () -> new Group(invalidGroupName));
    }

    @Test
    public void isValidGroupName() {
        // null group name
        assertThrows(NullPointerException.class, () -> Group.isValidGroupName(null));
    }

    @Test
    public void isSameGroup() {
        Group group = new Group("TUT01");

        assertTrue(group.isSameGroup(group)); // same group
        assertTrue(group.isSameGroup(new Group("TUT01"))); // same group name
        assertTrue(group.isSameGroup(new Group("TUT01", "https://t.me/abcdefghij")));
        assertFalse(group.isSameGroup(new Group("LAB01")));
    }

}
