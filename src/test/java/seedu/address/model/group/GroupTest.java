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
        assertTrue(Group.isValidGroupName("TUT01"));
        assertTrue(Group.isValidGroupName("LAB01"));
        assertTrue(Group.isValidGroupName("REC01"));
        assertFalse(Group.isValidGroupName("a"));
    }

    @Test
    public void isValidLink() {
        // null group name
        assertThrows(NullPointerException.class, () -> Group.isValidLink(null));
        assertTrue(Group.isValidLink("https://t.me/asdfasdf"));
        assertFalse(Group.isValidLink("a"));
    }

    @Test
    public void isSameGroup() {
        Group group = new Group("TUT01");

        assertTrue(group.isSameGroup(group)); // same group
        assertTrue(group.isSameGroup(new Group("TUT01"))); // same group name
        assertTrue(group.isSameGroup(new Group("TUT01", "https://t.me/abcdefghij")));
        assertFalse(group.isSameGroup(new Group("LAB01")));
    }

    @Test
    public void equals() {
        Group group = new Group("TUT01");

        assertTrue(group.equals(group)); // same object
        assertTrue(group.equals(new Group("TUT01"))); // same group name
        assertTrue(group.equals(new Group("TUT01", "https://t.me/abcdefghij")));
        assertFalse(group.equals(new Group("LAB01")));
    }

}
