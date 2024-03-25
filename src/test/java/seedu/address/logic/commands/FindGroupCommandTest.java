package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_GROUPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_1;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_2;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_3;
import static seedu.address.testutil.TypicalGroups.SAMPLE_GROUP_4;
import static seedu.address.testutil.TypicalGroups.getTypicalGroupList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.group.ContainsGroupKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindGroupCommand}.
 */
public class FindGroupCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());
    private Model expectedModel = new ModelManager(getTypicalContactList(), new UserPrefs(), getTypicalGroupList());

    @Test
    public void equals() {
        ContainsGroupKeywordPredicate firstPredicate =
                new ContainsGroupKeywordPredicate("first");
        ContainsGroupKeywordPredicate secondPredicate =
                new ContainsGroupKeywordPredicate("second");

        FindGroupCommand findFirstCommand = new FindGroupCommand(firstPredicate);
        FindGroupCommand findSecondCommand = new FindGroupCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindGroupCommand findFirstCommandCopy = new FindGroupCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different groups -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nonMatchingKeywords_noGroupFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 0);
        ContainsGroupKeywordPredicate predicate = preparePredicate("foo bar");
        FindGroupCommand command = new FindGroupCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, false);
        assertEquals(Collections.emptyList(), model.getFilteredGroupList());
    }

    @Test
    public void execute_commonSubstring_multipleGroupsFound() {
        String expectedMessage = String.format(MESSAGE_GROUPS_LISTED_OVERVIEW, 4);
        ContainsGroupKeywordPredicate predicate = preparePredicate("Group");
        FindGroupCommand command = new FindGroupCommand(predicate);
        expectedModel.updateFilteredGroupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, false);
        assertEquals(Arrays.asList(SAMPLE_GROUP_1, SAMPLE_GROUP_2, SAMPLE_GROUP_3, SAMPLE_GROUP_4), model.getFilteredGroupList());
    }

    @Test
    public void toStringMethod() {
        ContainsGroupKeywordPredicate predicate = new ContainsGroupKeywordPredicate("keyword");
        FindGroupCommand findGroupCommand = new FindGroupCommand(predicate);
        String expected = FindGroupCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findGroupCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsGroupKeywordPredicate preparePredicate(String userInput) {
        return new ContainsGroupKeywordPredicate(userInput.trim());
    }
}
