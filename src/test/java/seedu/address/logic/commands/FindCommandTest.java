package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LESSTHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MORETHAN;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.exam.Exam;
import seedu.address.model.person.PersonDetailContainsKeywordPredicate;
import seedu.address.model.person.Score;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        PersonDetailContainsKeywordPredicate firstPredicate =
                new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "first");
        PersonDetailContainsKeywordPredicate secondPredicate =
                new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "second");

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

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_name_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_NAME, "Adam");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_email_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 7);
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "@example.com");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_examRequiredNoExamSelected_throwsCommandException() {
        PersonDetailContainsKeywordPredicate predicate =
                new PersonDetailContainsKeywordPredicate(PREFIX_LESSTHAN, "50");
        FindCommand command = new FindCommand(predicate);
        assertCommandFailure(command, model, Messages.MESSAGE_NO_EXAM_SELECTED);
    }

    @Test
    public void execute_examRequiredExamSelectedValueTooHigh_throwsCommandException() {
        model.selectExam(new Exam("Final", new Score(100)));
        PersonDetailContainsKeywordPredicate predicate =
                new PersonDetailContainsKeywordPredicate(PREFIX_MORETHAN, "101");
        FindCommand command = new FindCommand(predicate);
        assertCommandFailure(command, model, FindCommand.MESSAGE_SCORE_GREATER_THAN_MAX);
    }

    @Test
    public void execute_examRequired_multiplePersonsFound() {
        model.selectExam(new Exam("Midterm", new Score(100)));
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonDetailContainsKeywordPredicate predicate =
                new PersonDetailContainsKeywordPredicate(PREFIX_LESSTHAN, "55");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate.withExam(model.getSelectedExam().getValue()));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, ELLE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        PersonDetailContainsKeywordPredicate predicate =
            new PersonDetailContainsKeywordPredicate(PREFIX_EMAIL, "@example.com");
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }
}
