package scrolls.elder.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static scrolls.elder.logic.commands.CommandTestUtil.assertCommandSuccess;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import scrolls.elder.logic.Messages;
import scrolls.elder.model.Model;
import scrolls.elder.model.ModelManager;
import scrolls.elder.model.ReadOnlyPersonStore;
import scrolls.elder.model.UserPrefs;
import scrolls.elder.model.person.NameContainsKeywordsPredicate;
import scrolls.elder.testutil.TypicalDatastore;
import scrolls.elder.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) for {@code FindCommand}.
 */
public class FindCommandTest {
    private final Model model = new ModelManager(TypicalDatastore.getTypicalDatastore(), new UserPrefs());
    private final ReadOnlyPersonStore personStore = model.getDatastore().getPersonStore();
    private final Model expectedModel = new ModelManager(TypicalDatastore.getTypicalDatastore(), new UserPrefs());
    private final ReadOnlyPersonStore expectedPersonStore = expectedModel.getDatastore().getPersonStore();

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindCommand findFirstCommand = new FindCommand(firstPredicate, true, true);
        FindCommand findSecondCommand = new FindCommand(secondPredicate, true, true);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values and same search -> returns true
        FindCommand findFirstCommandCopy = new FindCommand(firstPredicate, true, true);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // same values but different search -> returns true
        FindCommand findFirstCommandCopy2 = new FindCommand(firstPredicate, false, true);
        assertFalse(findFirstCommand.equals(findFirstCommandCopy2));

        // same values but different search -> returns true
        FindCommand findFirstCommandCopy3 = new FindCommand(firstPredicate, true, false);
        assertFalse(findFirstCommand.equals(findFirstCommandCopy3));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different person -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindCommand command = new FindCommand(predicate, true, true);
        expectedPersonStore.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), personStore.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindCommand command = new FindCommand(predicate, true, true);

        expectedPersonStore.updateFilteredPersonList(predicate);

        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalPersons.CARL, TypicalPersons.ELLE, TypicalPersons.FIONA),
                personStore.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {

        NameContainsKeywordsPredicate predicate = new NameContainsKeywordsPredicate(Arrays.asList("keyword"));
        FindCommand findCommand = new FindCommand(predicate, true, true);
        String expected = FindCommand.class.getCanonicalName()
                + "{"
                + "predicate=" + predicate + ", "
                + "isSearchingVolunteer=true, "
                + "isSearchingBefriendee=true"
                + "}";
        assertEquals(expected, findCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
