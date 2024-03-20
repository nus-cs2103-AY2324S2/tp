package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_NAME_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_QUERYABLE_SET_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_QUERYABLE_SET_2;
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
 * {@code AddMemberCommand}.
 */
public class AddMemberCommandTest {
    private final Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddMemberCommand(null, null));
    }

    @Test
    public void execute_nullParameters_throwsNullPointerException() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1);
        assertThrows(NullPointerException.class, () ->
                addMemberCommand.execute(null));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1);
        assertThrows(CommandException.class, () -> addMemberCommand.execute(emptyGroupListModel));
    }

    @Test
    public void execute_groupInListMembersNotInList_throwsCommandException() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_UNQUERYABLE_SET_1);
        assertThrows(CommandException.class, () ->
                addMemberCommand.execute(model));
    }

    @Test
    public void execute_groupInListMembersNotInGroup_runsNormally() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_2);
        assertTrue(model.hasGroup(SAMPLE_GROUP_1));
        assertDoesNotThrow(() -> addMemberCommand.execute(model));
        assertDoesNotThrow(() -> new DeleteMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_2));
    }

    @Test
    public void execute_groupInListMemberInGroup_throwsCommandException() {
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_1);
        assertThrows(CommandException.class, () -> addMemberCommand.execute(model));
    }

}
