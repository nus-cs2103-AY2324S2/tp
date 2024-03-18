package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_STARTUPS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStartups.CARL;
import static seedu.address.testutil.TypicalStartups.ELLE;
import static seedu.address.testutil.TypicalStartups.FIONA;
import static seedu.address.testutil.TypicalStartups.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.startup.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByNameCommand}.
 */
public class FindByNameCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        // Tests for find by names
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstNameCommand = new FindCommand(firstNamePredicate);
        FindCommand findSecondNameCommand = new FindCommand(secondNamePredicate);

        // same object -> returns true
        assertTrue(findFirstNameCommand.equals(findFirstNameCommand));

        // same values -> returns true
        FindCommand findFirstNameCommandCopy = new FindCommand(firstNamePredicate);
        assertTrue(findFirstNameCommand.equals(findFirstNameCommandCopy));

        // different types -> returns false
        assertFalse(findFirstNameCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstNameCommand.equals(null));

        // different startup -> returns false
        assertFalse(findFirstNameCommand.equals(findSecondNameCommand));
    }

    @Test
    public void execute_zeroKeywords_noStartupFound() {
        String expectedMessage = String.format(MESSAGE_STARTUPS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStartupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStartupList());
    }

    @Test
    public void execute_multipleKeywords_multipleStartupsFound() {
        String expectedMessage = String.format(MESSAGE_STARTUPS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStartupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStartupList());
    }

    @Test
    public void toStringMethod() {
        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
