package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_NAME_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_MEMBER_SET_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_MEMBER_SET_2;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code Group} class.
 */
public class GroupTest {


    @Test
    public void constructor_nullParameter_throwsErrors() {
        assertThrows(RuntimeException.class, () -> new Group(null, null));
        assertThrows(RuntimeException.class, () -> new Group(null, SAMPLE_MEMBER_SET_1));
        assertThrows(RuntimeException.class, () -> new Group(SAMPLE_GROUP_NAME_1, null));
        assertThrows(RuntimeException.class, () -> new Group(null));
    }

    @Test
    public void equals_differentGroupsWithSameName_isSameGroupButNotEquals() {
        Group group1 = new Group(SAMPLE_GROUP_NAME_1, SAMPLE_MEMBER_SET_1);
        Group group2 = new Group(SAMPLE_GROUP_NAME_1, SAMPLE_MEMBER_SET_2);
        assertTrue(group1.isSameGroup(group2));
        assertNotEquals(group1, group2);
    }

    @Test
    public void equals_sameGroupsWithDifferentObjects_isSameGroupAndEquals() {
        Group group1 = new Group(SAMPLE_GROUP_NAME_1, SAMPLE_MEMBER_SET_1);
        Group group2 = new Group(SAMPLE_GROUP_NAME_1, SAMPLE_MEMBER_SET_1);
        assertTrue(group1.isSameGroup(group2));
        assertEquals(group1, group2);
    }

    @Test
    public void toString_validGroup_doesNotThrow() {
        Group group = new Group(SAMPLE_GROUP_NAME_1, SAMPLE_MEMBER_SET_1);
        assertDoesNotThrow(group::toString);
    }
}
