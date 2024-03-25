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
import seedu.address.model.startup.FundingStageContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByFundingStage}.
 */
public class FindByFundingStageCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        // Tests for find by names
        FundingStageContainsKeywordsPredicate firstFundingStagePredicate =
                new FundingStageContainsKeywordsPredicate(Collections.singletonList("A"));
        FundingStageContainsKeywordsPredicate secondFundingStagePredicate =
                new FundingStageContainsKeywordsPredicate(Collections.singletonList("Seed"));

        FindCommand findFirstFundingStageCommand = new FindCommand(firstFundingStagePredicate);
        FindCommand findSecondFundingStageCommand = new FindCommand(secondFundingStagePredicate);

        // same object -> returns true
        assertTrue(findFirstFundingStageCommand.equals(findFirstFundingStageCommand));

        // same values -> returns true
        FindCommand findFirstFundingStageCommandCopy = new FindCommand(firstFundingStagePredicate);
        assertTrue(findFirstFundingStageCommand.equals(findFirstFundingStageCommandCopy));

        // different types -> returns false
        assertFalse(findFirstFundingStageCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstFundingStageCommand.equals(null));

        // different startup -> returns false
        assertFalse(findFirstFundingStageCommand.equals(findSecondFundingStageCommand));
    }

    @Test
    public void execute_zeroKeywords_noStartupFound() {
        String expectedMessage = String.format(MESSAGE_STARTUPS_LISTED_OVERVIEW, 0);
        FundingStageContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStartupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStartupList());
    }

    @Test
    public void execute_multipleKeywords_multipleStartupsFound() {
        String expectedMessage = String.format(MESSAGE_STARTUPS_LISTED_OVERVIEW, 3);
        FundingStageContainsKeywordsPredicate predicate = preparePredicate("C");
        FindCommand command = new FindCommand(predicate);
        expectedModel.updateFilteredStartupList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredStartupList());
    }

    @Test
    public void toStringMethod() {
        FundingStageContainsKeywordsPredicate predicate =
                new FundingStageContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate);
        String expected = FindCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code FundingStageContainsKeywordsPredicate}.
     */
    private FundingStageContainsKeywordsPredicate preparePredicate(String userInput) {
        return new FundingStageContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
