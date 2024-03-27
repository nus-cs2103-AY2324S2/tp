package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.CARL;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.ELLE;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.GEORGE;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.StarsLessThanPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStarsLessThanCommand}.
 */
public class FindStarsLessThanCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        StarsLessThanPredicate firstPredicate =
                new StarsLessThanPredicate(1);
        StarsLessThanPredicate secondPredicate =
                new StarsLessThanPredicate(2);

        FindStarsLessThanCommand findFirstCommand = new FindStarsLessThanCommand(firstPredicate);
        FindStarsLessThanCommand findSecondCommand = new FindStarsLessThanCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStarsLessThanCommand findFirstCommandCopy = new FindStarsLessThanCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroUpperBound_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW, 0);
        StarsLessThanPredicate predicate = preparePredicate(0);
        FindStarsLessThanCommand command = new FindStarsLessThanCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_infiniteUpperBound_allStudentsFound() {
        String expectedMessage = String.format(MESSAGE_STUDENTS_LISTED_OVERVIEW,
                expectedModel.getFilteredStudentList().size());
        StarsLessThanPredicate predicate = preparePredicate(Integer.MAX_VALUE);
        FindStarsLessThanCommand command = new FindStarsLessThanCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE),
                model.getFilteredStudentList());
    }

    @Test
    public void toStringMethod() {
        StarsLessThanPredicate predicate = new StarsLessThanPredicate(1);
        FindStarsLessThanCommand findStarsLessThanCommand = new FindStarsLessThanCommand(predicate);
        String expected = FindStarsLessThanCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findStarsLessThanCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private StarsLessThanPredicate preparePredicate(Integer i) {
        return new StarsLessThanPredicate(i);
    }

}
