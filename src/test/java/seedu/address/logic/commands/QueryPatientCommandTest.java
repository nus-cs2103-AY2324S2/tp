package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.CARL_GOH;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.PatientContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class QueryPatientCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PatientContainsKeywordsPredicate firstPredicate =
                new PatientContainsKeywordsPredicate((Collections.singletonList("first")));
        PatientContainsKeywordsPredicate secondPredicate =
                new PatientContainsKeywordsPredicate(Collections.singletonList("second"));

        QueryPatientCommand findFirstCommand = new QueryPatientCommand(firstPredicate);
        QueryPatientCommand findSecondCommand = new QueryPatientCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        QueryPatientCommand findFirstCommandCopy = new QueryPatientCommand(firstPredicate);
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
        PatientContainsKeywordsPredicate predicate = preparePredicate(" ");
        QueryPatientCommand command = new QueryPatientCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PatientContainsKeywordsPredicate predicate = preparePredicate("Alice Benson");
        QueryPatientCommand command = new QueryPatientCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleKeyword_multiplePatientsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        PatientContainsKeywordsPredicate predicate = preparePredicate("Carl");
        QueryPatientCommand command = new QueryPatientCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, CARL_GOH), model.getFilteredPersonList());
    }

    @Test
    public void execute_noMatch_noPatientFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PatientContainsKeywordsPredicate predicate = preparePredicate("Zorro");
        QueryPatientCommand command = new QueryPatientCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void toStringMethod() {
        PatientContainsKeywordsPredicate predicate =
                new PatientContainsKeywordsPredicate(Arrays.asList("keyword"));
        QueryPatientCommand findCommand = new QueryPatientCommand(predicate);
        String expected = QueryPatientCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private PatientContainsKeywordsPredicate preparePredicate(String userInput) {
        return new PatientContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
