package staffconnect.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static staffconnect.logic.Messages.MESSAGE_PERSONS_LISTED_OVERVIEW;
import static staffconnect.logic.commands.CommandTestUtil.assertCommandSuccess;
import static staffconnect.testutil.TypicalPersons.ALICE;
import static staffconnect.testutil.TypicalPersons.BENSON;
import static staffconnect.testutil.TypicalPersons.DANIEL;
import static staffconnect.testutil.TypicalPersons.getTypicalStaffBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import staffconnect.model.Model;
import staffconnect.model.ModelManager;
import staffconnect.model.UserPrefs;
import staffconnect.model.person.PersonHasTagPredicate;
import staffconnect.model.tag.Tag;

public class FilterCommandTest {

    private Model model = new ModelManager(getTypicalStaffBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalStaffBook(), new UserPrefs());

    @Test
    public void execute_personHasTag_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 0);
        PersonHasTagPredicate tagPredicate = prepareTagPredicate("hello");
        FilterCommand command = new FilterCommand(tagPredicate);
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Collections.emptyList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_personHasTag_multiplePersonsFound() {
        String expectedMessage = String.format(MESSAGE_PERSONS_LISTED_OVERVIEW, 3);
        PersonHasTagPredicate tagPredicate = prepareTagPredicate("friends");
        FilterCommand command = new FilterCommand(tagPredicate);
        expectedModel.updateFilteredPersonList(tagPredicate);
        assertCommandSuccess(command, model, expectedMessage, model);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void equals() {
        PersonHasTagPredicate firstTagPredicate = new PersonHasTagPredicate(new Tag("friend"));
        PersonHasTagPredicate secondTagPredicate = new PersonHasTagPredicate(new Tag("colleagues"));

        FilterCommand filterTagFirstCommand = new FilterCommand(firstTagPredicate);
        FilterCommand filterTagSecondCommand = new FilterCommand(secondTagPredicate);

        // same object -> returns true
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommand));

        // same values -> returns true
        FilterCommand filterTagFirstCommandCopy = new FilterCommand(firstTagPredicate);
        assertTrue(filterTagFirstCommand.equals(filterTagFirstCommandCopy));

        // different types -> returns false
        assertFalse(filterTagFirstCommand.equals(1));

        // null -> returns false
        assertFalse(filterTagFirstCommand.equals(null));

        // different tag -> returns false
        assertFalse(filterTagFirstCommand.equals(filterTagSecondCommand));
    }

    @Test
    public void toStringMethod() {
        PersonHasTagPredicate tagPredicate = new PersonHasTagPredicate(new Tag("hello"));
        FilterCommand filterCommand = new FilterCommand(tagPredicate);
        String expected = FilterCommand.class.getCanonicalName() + "{tagPredicate=" + tagPredicate + "}";
        assertEquals(expected, filterCommand.toString());
    }

    /**
     * Parses {@code userInput} into a {@code PersonHasTagPredicate}.
     */
    private PersonHasTagPredicate prepareTagPredicate(String userInput) {
        return new PersonHasTagPredicate(new Tag(userInput));
    }

}
