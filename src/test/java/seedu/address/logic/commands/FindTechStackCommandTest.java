package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.ELLE;
import static seedu.address.testutil.TypicalPersons.FIONA;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.TechStackContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTechStackCommand}.
 */
public class FindTechStackCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        TechStackContainsKeywordsPredicate firstPredicate =
                new TechStackContainsKeywordsPredicate(Collections.singletonList("first"));
        TechStackContainsKeywordsPredicate secondPredicate =
                new TechStackContainsKeywordsPredicate(Collections.singletonList("second"));

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
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        TechStackContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindTechStackCommand command = new FindTechStackCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        TechStackContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindTechStackCommand command = new FindTechStackCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        TechStackContainsKeywordsPredicate predicate = new TechStackContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindTechStackCommand findTechStackCommand = new FindTechStackCommand(predicate);
        String expected = FindTechStackCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findTechStackCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private TechStackContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TechStackContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
