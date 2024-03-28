package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_NAME_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_SKILL_LIST_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_SKILL_LIST_3;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GroupList;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.skill.Skill;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code UnrequireSkillCommand}.
 */
public class UnrequireSkillCommandTest {
    private final Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private final Model emptyGroupListModel =
            new ModelManager(getTypicalContactList(), new UserPrefs(), new GroupList());
    private final Set<Skill> skillSet1 = new HashSet<>(SAMPLE_SKILL_LIST_1);
    private final Set<Skill> skillSet3 = new HashSet<>(SAMPLE_SKILL_LIST_3);

    @Test
    public void constructor_nullParameters_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new UnrequireSkillCommand(null, null));
    }

    @Test
    public void execute_nullParameters_throwsNullPointerException() {
        UnrequireSkillCommand unrequireSkillCommand = new UnrequireSkillCommand(SAMPLE_GROUP_NAME_1, skillSet1);
        assertThrows(NullPointerException.class, () ->
                unrequireSkillCommand.execute(null));
    }

    @Test
    public void execute_groupNotInList_throwsCommandException() {
        UnrequireSkillCommand unrequireSkillCommand = new UnrequireSkillCommand(SAMPLE_GROUP_NAME_1, skillSet1);
        assertThrows(CommandException.class, () -> unrequireSkillCommand.execute(emptyGroupListModel));
    }

    @Test
    public void execute_groupInListNoCommonSkills_runsNormally() {
        UnrequireSkillCommand unrequireSkillCommand = new UnrequireSkillCommand(SAMPLE_GROUP_NAME_1, skillSet3);
        assertDoesNotThrow(() -> unrequireSkillCommand.execute(model));
    }

    @Test
    public void execute_groupInListDuplicateSkills_runsNormally() {
        UnrequireSkillCommand unrequireSkillCommand = new UnrequireSkillCommand(SAMPLE_GROUP_NAME_1, skillSet1);
        assertDoesNotThrow(() -> unrequireSkillCommand.execute(model));
    }
}
