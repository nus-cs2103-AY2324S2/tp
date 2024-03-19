package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.AddressContainsKeywordsPredicate;
import seedu.address.model.person.MatchingEmailPredicate;
import seedu.address.model.person.MatchingPhonePredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindPersonCommand}.
 */
public class FindPersonCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindPersonCommand findPersonFirstCommand = new FindPersonCommand(firstPredicate);
        FindPersonCommand findPersonSecondCommand = new FindPersonCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findPersonFirstCommand.equals(findPersonFirstCommand));

        // same values -> returns true
        FindPersonCommand findFirstCommandCopy = new FindPersonCommand(firstPredicate);
        assertTrue(findPersonFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findPersonFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findPersonFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findPersonFirstCommand.equals(findPersonSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate(" ");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleNameKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = prepareNamePredicate("Kurz Elle Kunz");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_singlePhoneNumber_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        MatchingPhonePredicate predicate = preparePhonePredicate("95352563");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multiplePhoneNumbers_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        MatchingPhonePredicate predicate = preparePhonePredicate("95352563 9482427 9482442");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleEmail_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        MatchingEmailPredicate predicate = prepareEmailPredicate("cornelia@example.com");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleEmails_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        MatchingEmailPredicate predicate = prepareEmailPredicate("lydia@example.com anna@example.com");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleAddressKeyword_singlePersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 1);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate("tokyo");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleAddressKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        AddressContainsKeywordsPredicate predicate = prepareAddressPredicate("street");
        FindPersonCommand command = new FindPersonCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindPersonCommand findPersonCommand = new FindPersonCommand(predicate);
        String expected = FindPersonCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findPersonCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate prepareNamePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code MatchingPhonePredicate}.
     */
    private MatchingPhonePredicate preparePhonePredicate(String userInput) {
        return new MatchingPhonePredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code MatchingEmailPredicate}.
     */
    private MatchingEmailPredicate prepareEmailPredicate(String userInput) {
        return new MatchingEmailPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code AddressContainsKeywordsPredicate}.
     */
    private AddressContainsKeywordsPredicate prepareAddressPredicate(String userInput) {
        return new AddressContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
