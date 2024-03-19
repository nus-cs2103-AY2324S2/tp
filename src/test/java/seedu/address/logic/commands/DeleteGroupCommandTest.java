package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_NAME_1;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class DeleteGroupCommandTest {
    private final Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteGroupCommand(null));
    }

    @Test
    public void execute_nullParameters_throwsNullPointerException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(SAMPLE_GROUP_NAME_1);
        assertThrows(NullPointerException.class, () ->
                deleteGroupCommand.execute(null));
    }

    @Test
    public void execute_groupInList_runsNormally() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(SAMPLE_GROUP_NAME_1);
        assertTrue(model.hasGroup(SAMPLE_GROUP_1));
        assertDoesNotThrow(() -> deleteGroupCommand.execute(model));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        DeleteGroupCommand deleteGroupCommand = new DeleteGroupCommand(SAMPLE_GROUP_NAME_1);
        assertThrows(CommandException.class, () ->
                deleteGroupCommand.execute(emptyGroupListModel));
    }
}
