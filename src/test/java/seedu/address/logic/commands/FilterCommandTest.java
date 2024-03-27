package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.GEORGE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.MatchingTagPredicate;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        MatchingTagPredicate firstPredicate =
                new MatchingTagPredicate("I");
        MatchingTagPredicate secondPredicate =
                new MatchingTagPredicate("NR");

        FilterCommand filterFirstTag = new FilterCommand(firstPredicate);
        FilterCommand filterSecondTag = new FilterCommand(secondPredicate);

        // same object -> returns true
        assertTrue(filterFirstTag.equals(filterFirstTag));

        // same values -> returns true
        FilterCommand filterFirstTagCopy = new FilterCommand(firstPredicate);
        assertTrue(filterFirstTagCopy.equals(filterFirstTagCopy));

        // different types -> returns false
        assertFalse(filterFirstTag.equals(1));

        // null -> returns false
        assertFalse(filterFirstTag.equals(null));

        // different person -> returns false
        assertFalse(filterFirstTag.equals(filterSecondTag));
    }

    @Test
    public void execute_zeroKeywords_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        MatchingTagPredicate predicate = new MatchingTagPredicate(" ");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 2);
        MatchingTagPredicate predicate = new MatchingTagPredicate("I");
        FilterCommand command = new FilterCommand(predicate);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(BENSON, GEORGE), model.getFilteredPersonList());
    }

    @Test
    public void toStringMethod() {
        MatchingTagPredicate predicate = new MatchingTagPredicate("keyword");
        FilterCommand filterCommand = new FilterCommand(predicate);
        String expected = FilterCommand.class.getCanonicalName() + "{predicate=" + predicate + "}";
        assertEquals(expected, filterCommand.toString());
    }
}
