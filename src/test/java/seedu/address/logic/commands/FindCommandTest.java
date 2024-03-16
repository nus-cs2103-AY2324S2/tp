package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_COURSE_MATES_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalCourseMates.CARL;
import static seedu.address.testutil.TypicalCourseMates.FIONA;
import static seedu.address.testutil.TypicalCourseMates.getTypicalContactList;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.coursemate.ContainsKeywordPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalContactList(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalContactList(), new UserPrefs());

    @Test
    public void equals() {
        ContainsKeywordPredicate firstPredicate =
                new ContainsKeywordPredicate("first");
        ContainsKeywordPredicate secondPredicate =
                new ContainsKeywordPredicate("second");

        FindCommand findFirstCommand = new FindCommand(firstPredicate);
        FindCommand findSecondCommand = new FindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different courseMate -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_nonMatchingKeywords_noCourseMateFound() {
        String expectedMessage = String.format(MESSAGE_COURSE_MATES_LISTED_OVERVIEW, 0);
        ContainsKeywordPredicate predicate = preparePredicate("foo bar");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCourseMateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, false);
        assertEquals(Collections.emptyList(), model.getFilteredCourseMateList());
    }

    @Test
    public void execute_commonSubstring_multipleCourseMatesFound() {
        String expectedMessage = String.format(MESSAGE_COURSE_MATES_LISTED_OVERVIEW, 2);
        ContainsKeywordPredicate predicate = preparePredicate("z");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredCourseMateList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel, false);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredCourseMateList());
    }

    @Test
    public void toStringMethod() {
        ContainsKeywordPredicate predicate = new ContainsKeywordPredicate("keyword");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code ContainsKeywordsPredicate}.
     */
    private ContainsKeywordPredicate preparePredicate(String userInput) {
        return new ContainsKeywordPredicate(userInput.trim());
    }
}
