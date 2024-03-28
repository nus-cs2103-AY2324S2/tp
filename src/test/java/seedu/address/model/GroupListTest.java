package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for the {@code GroupList} class.
 */
public class GroupListTest {

    private final GroupList groupList = new GroupList();
    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> groupList.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyGroupList_replacesData() {
        GroupList newData = getTypicalGroupList();
        groupList.resetData(newData);
        assertEquals(newData, groupList);
    }

    @Test
    public void hasGroup_groupInGroupList_returnsTrue() {
        groupList.addGroup(SAMPLE_GROUP_1);
        assertTrue(groupList.hasGroup(SAMPLE_GROUP_1));
    }

    @Test
    public void getGroupList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> groupList.getGroupList().remove(0));
    }
}
