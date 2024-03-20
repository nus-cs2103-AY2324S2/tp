package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CONTACT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.CARL;
import static seedu.address.testutil.TypicalContacts.ELLE;
import static seedu.address.testutil.TypicalContacts.FIONA;
import static seedu.address.testutil.TypicalContacts.getTypicalCodeConnect;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.TsContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTechStackCommand}.
 */
public class FindTechStackCommandTest {
    private Model model = new ModelManager(getTypicalCodeConnect(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCodeConnect(), new UserPrefs());

    @Test
    public void equals() {
        TsContainsKeywordsPredicate firstPredicate =
                new TsContainsKeywordsPredicate(Collections.singletonList("first"));
        TsContainsKeywordsPredicate secondPredicate =
                new TsContainsKeywordsPredicate(Collections.singletonList("second"));

        FindTechStackCommand findFirstCommand = new FindTechStackCommand(firstPredicate);
        FindTechStackCommand findSecondCommand = new FindTechStackCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTechStackCommand findFirstCommandCopy = new FindTechStackCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACT_LISTED_OVERVIEW, 0);
        TsContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTechStackCommand command = new FindTechStackCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACT_LISTED_OVERVIEW, 3);
        TsContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindTechStackCommand command = new FindTechStackCommand(predicate);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredContactList());
    }

    @Test
    public void toStringMethod() {
        TsContainsKeywordsPredicate predicate = new TsContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindTechStackCommand findTechStackCommand = new FindTechStackCommand(predicate);
        String expected = FindTechStackCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findTechStackCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TsContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TsContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
