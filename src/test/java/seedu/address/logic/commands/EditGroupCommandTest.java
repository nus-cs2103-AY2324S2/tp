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
import seedu.address.model.group.TelegramChat;

public class EditGroupCommandTest {
    private final Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new EditGroupCommand(null, null));
    }

    @Test
    public void execute_nullTelegramChat_throwsNullPointerException() {
        EditGroupCommand editGroupCommand = new EditGroupCommand(SAMPLE_GROUP_NAME_1, null);
        assertThrows(NullPointerException.class, () ->
                editGroupCommand.execute(null));
    }

    @Test
    public void execute_groupInList_runsNormally() {
        TelegramChat telegramChat = new TelegramChat("https://t.me/AAAAAEQ8H1J1J1J1J1J1J1");
        EditGroupCommand editGroupCommand = new EditGroupCommand(SAMPLE_GROUP_NAME_1, telegramChat);
        assertTrue(model.hasGroup(SAMPLE_GROUP_1));
        assertDoesNotThrow(() -> editGroupCommand.execute(model));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        TelegramChat telegramChat = new TelegramChat("https://t.me/AAAAAEQ8H1J1J1J1J1J1J1");
        EditGroupCommand editGroupCommand = new EditGroupCommand(SAMPLE_GROUP_NAME_1, telegramChat);
        assertThrows(CommandException.class, () ->
                editGroupCommand.execute(emptyGroupListModel));
    }
}
