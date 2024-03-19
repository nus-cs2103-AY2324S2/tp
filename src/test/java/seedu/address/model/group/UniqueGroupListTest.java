package seedu.address.model.group;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_2;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.model.group.exceptions.DuplicateGroupException;
import seedu.address.model.group.exceptions.GroupNotFoundException;

/**
 * Unit tests for the {@code UniqueGroupList} class.
 */
public class UniqueGroupListTest {

    private final UniqueGroupList uniqueGroupList = new UniqueGroupList();
    @Test
    public void contains_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.contains(null));
    }

    @Test
    public void contains_groupNotInList_returnsFalse() {
        assertFalse(uniqueGroupList.contains(SAMPLE_GROUP_1));
    }

    @Test
    public void add_sameGroupTwice_doesNotThrowThenThrows() {
        assertDoesNotThrow(() -> uniqueGroupList.add(SAMPLE_GROUP_1));
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.add(SAMPLE_GROUP_1));
    }

    @Test
    public void setGroup_groupNotInList_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.setGroup(SAMPLE_GROUP_1, SAMPLE_GROUP_1));
    }

    @Test
    public void setGroup_newValueInList_throwsDuplicateGroupException() {
        uniqueGroupList.add(SAMPLE_GROUP_1);
        uniqueGroupList.add(SAMPLE_GROUP_2);
        assertThrows(DuplicateGroupException.class, () -> uniqueGroupList.setGroup(SAMPLE_GROUP_1, SAMPLE_GROUP_2));
    }

    @Test
    public void setGroup_newValueNotInList_noExceptions() {
        uniqueGroupList.add(SAMPLE_GROUP_1);
        assertDoesNotThrow(() -> uniqueGroupList.setGroup(SAMPLE_GROUP_1, SAMPLE_GROUP_2));
    }

    @Test
    public void remove_nullGroup_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueGroupList.remove(null));
    }

    @Test
    public void remove_groupNotInList_throwsGroupNotFoundException() {
        assertThrows(GroupNotFoundException.class, () -> uniqueGroupList.remove(SAMPLE_GROUP_1));
    }

    @Test
    public void remove_groupInList_doesNotThrow() {
        uniqueGroupList.add(SAMPLE_GROUP_1);
        assertDoesNotThrow(() -> uniqueGroupList.remove(SAMPLE_GROUP_1));
    }

    @Test
    public void setGroups_emptyGroupList_doesNotThrow() {
        assertDoesNotThrow(() -> uniqueGroupList.setGroups(new UniqueGroupList()));
        assertDoesNotThrow(() -> uniqueGroupList.setGroups(new ArrayList<>()));
    }

    @Test
    public void setGroups_groupListWithDuplicates_throwsDuplicateGroupException() {
        assertThrows(DuplicateGroupException.class, () ->
                uniqueGroupList.setGroups(new ArrayList<>(Arrays.asList(SAMPLE_GROUP_1, SAMPLE_GROUP_1))));
    }

    @Test
    public void toStringMethod() {
        assertEquals(uniqueGroupList.asUnmodifiableObservableList().toString(), uniqueGroupList.toString());
    }
}
