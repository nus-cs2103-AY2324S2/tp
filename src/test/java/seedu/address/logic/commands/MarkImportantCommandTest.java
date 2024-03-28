package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_CPP;
import static seedu.address.logic.commands.CommandTestUtil.VALID_SKILL_JAVA;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_NAME_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_QUERYABLE_SET_2;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.MarkImportantCommand.MarkImportantDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.MarkImportantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkImportantCommand
 */
public class MarkImportantCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new MarkImportantCommand(null, null));
    }

    @Test
    public void execute_nullParameters_throwsNullPointerException() {
        MarkImportantDescriptor markImportantDescriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        MarkImportantCommand markImportantCommand =
                new MarkImportantCommand(SAMPLE_GROUP_NAME_1, markImportantDescriptor);
        assertThrows(NullPointerException.class, () ->
                markImportantCommand.execute(null));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        MarkImportantDescriptor markImportantDescriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        MarkImportantCommand markImportantCommand =
                new MarkImportantCommand(SAMPLE_GROUP_NAME_1, markImportantDescriptor);
        assertThrows(CommandException.class, () -> markImportantCommand.execute(emptyGroupListModel));
    }

    @Test
    public void execute_groupInListSkillsNotInGroup_throwsCommandException() {
        MarkImportantCommand.MarkImportantDescriptor markImportantDescriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        MarkImportantCommand markImportantCommand =
                new MarkImportantCommand(SAMPLE_GROUP_NAME_1, markImportantDescriptor);
        assertThrows(CommandException.class, () ->
                markImportantCommand.execute(model));
    }

    @Test
    public void execute_groupInListSkillsInGroup_runsNormally() {
        MarkImportantDescriptor markImportantDescriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CPP).build();
        MarkImportantCommand markImportantCommand =
                new MarkImportantCommand(SAMPLE_GROUP_NAME_1, markImportantDescriptor);
        assertTrue(model.hasGroup(SAMPLE_GROUP_1));
        assertDoesNotThrow(() -> markImportantCommand.execute(model));
    }

    @Test
    public void equals() {
        MarkImportantDescriptor markImportantDescriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        MarkImportantCommand markImportantCommand =
                new MarkImportantCommand(SAMPLE_GROUP_NAME_1, markImportantDescriptor);
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_2);

        MarkImportantDescriptor otherMarkImportantDescriptor = new MarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CPP).build();
        MarkImportantCommand otherMarkImportantCommand =
                new MarkImportantCommand(SAMPLE_GROUP_NAME_1, otherMarkImportantDescriptor);

        assertFalse(markImportantCommand.equals(otherMarkImportantCommand));
        assertFalse(markImportantCommand.equals(addMemberCommand));
        assertTrue(markImportantCommand.equals(markImportantCommand));

    }
}
