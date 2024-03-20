package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_CONTACT_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalContacts.ALICE;
import static seedu.address.testutil.TypicalContacts.BENSON;
import static seedu.address.testutil.TypicalContacts.DANIEL;
import static seedu.address.testutil.TypicalContacts.getTypicalCodeConnect;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.contact.TagsContainKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindTagCommand}.
 */
public class FindTagCommandTest {
    private Model model = new ModelManager(getTypicalCodeConnect(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCodeConnect(), new UserPrefs());

    @Test
    public void equals() {
        List<String> firstPredicate = Collections.singletonList("first");
        List<String> secondPredicate = Collections.singletonList("second");

        FindTagCommand findFirstCommand = new FindTagCommand(firstPredicate);
        FindTagCommand findSecondCommand = new FindTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindTagCommand findFirstCommandCopy = new FindTagCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different contact -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noContactFound() {
        String expectedMessage = String.format(MESSAGE_CONTACT_LISTED_OVERVIEW, 0);
        List<String> tagKeywords = List.of("Acquaintances");
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(tagKeywords);
        FindTagCommand command = new FindTagCommand(tagKeywords);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredContactList());
    }

    @Test
    public void execute_multipleKeywords_multipleContactsFound() {
        String expectedMessage = String.format(MESSAGE_CONTACT_LISTED_OVERVIEW, 3);
        List<String> tagKeywords = List.of("friends");
        TagsContainKeywordsPredicate predicate = new TagsContainKeywordsPredicate(tagKeywords);
        FindTagCommand command = new FindTagCommand(tagKeywords);
        expectedModel.updateFilteredContactList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredContactList());
    }

    @Test
    public void toStringMethod() {
        List<String> tagKeywords= List.of("School", "Work");
        FindTagCommand FindTagCommand = new FindTagCommand(tagKeywords);
        String expected = FindTagCommand.class.getCanonicalName() + "{predicate=" + tagKeywords + "}";
        assertEquals(expected, FindTagCommand.toString());
    }
}
