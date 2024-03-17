package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.JAMES;
import static seedu.address.testutil.TypicalPersons.ERIC;
import static seedu.address.testutil.TypicalPersons.BROWN;
import static seedu.address.testutil.TypicalPersons.JAMES_GOH;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DoctorNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class QueryDoctorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DoctorNameContainsKeywordsPredicate firstPredicate =
                new DoctorNameContainsKeywordsPredicate((Collections.singletonList("first")));
        DoctorNameContainsKeywordsPredicate secondPredicate =
                new DoctorNameContainsKeywordsPredicate(Collections.singletonList("second"));

        QueryDoctorCommand findFirstCommand = new QueryDoctorCommand(firstPredicate);
        QueryDoctorCommand findSecondCommand = new QueryDoctorCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        QueryDoctorCommand findFirstCommandCopy = new QueryDoctorCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        DoctorNameContainsKeywordsPredicate predicate = preparePredicate(" ");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        DoctorNameContainsKeywordsPredicate predicate = preparePredicate("James Eric");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JAMES, ERIC), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleKeyword_multipleDoctorsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        DoctorNameContainsKeywordsPredicate predicate = preparePredicate("James");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JAMES, JAMES_GOH), model.getFilteredPersonList());
    }

    @Test
    public void execute_noMatch_noDoctorFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        DoctorNameContainsKeywordsPredicate predicate = preparePredicate("Zorro");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void toStringMethod() {
        DoctorNameContainsKeywordsPredicate predicate =
                new DoctorNameContainsKeywordsPredicate(Arrays.asList("keyword"));
        QueryDoctorCommand findCommand = new QueryDoctorCommand(predicate);
        String expected = QueryDoctorCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DoctorNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DoctorNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}