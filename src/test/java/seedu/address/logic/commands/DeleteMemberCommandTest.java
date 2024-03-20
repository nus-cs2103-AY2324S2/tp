package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_NAME_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_QUERYABLE_SET_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_QUERYABLE_SET_3;
import static seedu.address.testutil.TypicalGroups.SAMPLE_UNQUERYABLE_SET_1;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteMemberCommand}.
 */
public class DeleteMemberCommandTest {
    private final Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteMemberCommand(null, null));
    }

    @Test
    public void execute_nullParameters_throwsNullPointerException() {
        DeleteMemberCommand deleteMemberCommand = new DeleteMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1);
        assertThrows(NullPointerException.class, () ->
                deleteMemberCommand.execute(null));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        DeleteMemberCommand deleteMemberCommand =
                new DeleteMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1);
        assertThrows(CommandException.class, () -> deleteMemberCommand.execute(emptyGroupListModel));
    }

    @Test
    public void execute_groupInListMembersNotInList_throwsCommandException() {
        DeleteMemberCommand deleteMemberCommand =
                new DeleteMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_UNQUERYABLE_SET_1);
        assertThrows(CommandException.class, () ->
                deleteMemberCommand.execute(model));
    }

    @Test
    public void execute_groupInListMembersNotInGroup_throwsCommandException() {
        DeleteMemberCommand deleteMemberCommand =
                new DeleteMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_3);
        assertTrue(model.hasGroup(SAMPLE_GROUP_1));
        assertThrows(CommandException.class, () -> deleteMemberCommand.execute(model));
    }

    @Test
    public void execute_groupInListMemberInGroup_runsNormally() {
        DeleteMemberCommand deleteMemberCommand =
                new DeleteMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1);
        assertDoesNotThrow(() -> deleteMemberCommand.execute(model));
        assertDoesNotThrow(() ->
                new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1).execute(model));
    }

}
