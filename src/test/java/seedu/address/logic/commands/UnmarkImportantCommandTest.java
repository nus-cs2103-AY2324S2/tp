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

import seedu.address.logic.commands.UnmarkImportantCommand.UnmarkImportantDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.UnmarkImportantDescriptorBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for MarkImportantCommand
 */
public class UnmarkImportantCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new UnmarkImportantCommand(null, null));
    }

    @Test
    public void execute_nullParameters_throwsNullPointerException() {
        UnmarkImportantDescriptor unmarkImportantDescriptor = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        UnmarkImportantCommand unmarkImportantCommand =
                new UnmarkImportantCommand(SAMPLE_GROUP_NAME_1, unmarkImportantDescriptor);
        assertThrows(NullPointerException.class, () ->
                unmarkImportantCommand.execute(null));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        UnmarkImportantDescriptor unmarkImportantDescriptor = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        UnmarkImportantCommand unmarkImportantCommand =
                new UnmarkImportantCommand(SAMPLE_GROUP_NAME_1, unmarkImportantDescriptor);
        assertThrows(CommandException.class, () -> unmarkImportantCommand.execute(emptyGroupListModel));
    }

    @Test
    public void execute_groupInListSkillsNotInGroup_throwsCommandException() {
        UnmarkImportantCommand.UnmarkImportantDescriptor unmarkImportantDescriptor =
                new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        UnmarkImportantCommand unmarkImportantCommand =
                new UnmarkImportantCommand(SAMPLE_GROUP_NAME_1, unmarkImportantDescriptor);
        assertThrows(CommandException.class, () ->
                unmarkImportantCommand.execute(model));
    }

    @Test
    public void execute_groupInListSkillsInGroup_runsNormally() {
        UnmarkImportantDescriptor unmarkImportantDescriptor = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CPP).build();
        UnmarkImportantCommand unmarkImportantCommand =
                new UnmarkImportantCommand(SAMPLE_GROUP_NAME_1, unmarkImportantDescriptor);
        assertTrue(model.hasGroup(SAMPLE_GROUP_1));
        assertDoesNotThrow(() -> unmarkImportantCommand.execute(model));
    }

    @Test
    public void equals() {
        UnmarkImportantDescriptor unmarkImportantDescriptor = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_JAVA).build();
        UnmarkImportantCommand unmarkImportantCommand =
                new UnmarkImportantCommand(SAMPLE_GROUP_NAME_1, unmarkImportantDescriptor);
        AddMemberCommand addMemberCommand = new AddMemberCommand(SAMPLE_GROUP_NAME_1, SAMPLE_QUERYABLE_SET_2);

        UnmarkImportantDescriptor otherUnmarkImportantDescriptor = new UnmarkImportantDescriptorBuilder()
                .withSkills(VALID_SKILL_CPP).build();
        UnmarkImportantCommand otherUnmarkImportantCommand =
                new UnmarkImportantCommand(SAMPLE_GROUP_NAME_1, otherUnmarkImportantDescriptor);

        assertFalse(unmarkImportantCommand.equals(otherUnmarkImportantCommand));
        assertFalse(unmarkImportantCommand.equals(addMemberCommand));
        assertTrue(unmarkImportantCommand.equals(unmarkImportantCommand));

    }
}
