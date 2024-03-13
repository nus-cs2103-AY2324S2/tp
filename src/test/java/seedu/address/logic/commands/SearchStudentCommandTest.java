package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.EmailContainsKeywordPredicate;
import seedu.address.model.person.NameContainsKeywordPredicate;
import seedu.address.model.person.StudentIdContainsKeywordPredicate;
import seedu.address.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code SearchStudentCommand}.
 */
public class SearchStudentCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordPredicate firstPredicate = new NameContainsKeywordPredicate("first");
        NameContainsKeywordPredicate secondPredicate = new NameContainsKeywordPredicate("second");

        SearchStudentCommand firstCommand = new SearchStudentCommand(firstPredicate);
        SearchStudentCommand secondCommand = new SearchStudentCommand(secondPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));

        // same values -> returns true
        SearchStudentCommand searchFirstCommandCopy = new SearchStudentCommand(firstPredicate);
        assertTrue(firstCommand.equals(searchFirstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different person -> returns false
        assertFalse(firstCommand.equals(secondCommand));

        // different predicate
        EmailContainsKeywordPredicate differentPredicate = new EmailContainsKeywordPredicate("first");
        SearchStudentCommand differentFirstCommand = new SearchStudentCommand(differentPredicate);
        assertFalse(firstCommand.equals(differentFirstCommand));
    }

    @Test
    public void execute_oneKeyword_success() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate(
                TypicalPersons.ALICE.getName().fullName);
        SearchStudentCommand command = new SearchStudentCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredPersonList());
    }

    @Test
    public void execute_predicateNotInModel_notFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordPredicate predicate = new NameContainsKeywordPredicate("Not in list");
        SearchStudentCommand command = new SearchStudentCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        StudentIdContainsKeywordPredicate predicate = new StudentIdContainsKeywordPredicate("keyword");
        SearchStudentCommand command = new SearchStudentCommand(predicate);
        String expected = SearchStudentCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, command.toString());
    }
}
