package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.DAMES;
import static seedu.address.testutil.TypicalPersons.DAMES_GOH;
import static seedu.address.testutil.TypicalPersons.ERIN;
import static seedu.address.testutil.TypicalPersons.GON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.DoctorContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class QueryDoctorCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        DoctorContainsKeywordsPredicate firstPredicate =
                new DoctorContainsKeywordsPredicate((Collections.singletonList("first")));
        DoctorContainsKeywordsPredicate secondPredicate =
                new DoctorContainsKeywordsPredicate(Collections.singletonList("second"));

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
        DoctorContainsKeywordsPredicate predicate = preparePredicate(" ");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        DoctorContainsKeywordsPredicate predicate = preparePredicate("Erin Gon");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ERIN, GON), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleKeyword_multipleDoctorsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        DoctorContainsKeywordsPredicate predicate = preparePredicate("Dames");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DAMES, DAMES_GOH), model.getFilteredPersonList());
    }

    @Test
    public void execute_noMatch_noDoctorFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        DoctorContainsKeywordsPredicate predicate = preparePredicate("Zorro");
        QueryDoctorCommand command = new QueryDoctorCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertTrue(model.getFilteredPersonList().isEmpty());
    }

    @Test
    public void toStringMethod() {
        DoctorContainsKeywordsPredicate predicate =
                new DoctorContainsKeywordsPredicate(Arrays.asList("keyword"));
        QueryDoctorCommand findCommand = new QueryDoctorCommand(predicate);
        String expected = QueryDoctorCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private DoctorContainsKeywordsPredicate preparePredicate(String userInput) {
        return new DoctorContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
