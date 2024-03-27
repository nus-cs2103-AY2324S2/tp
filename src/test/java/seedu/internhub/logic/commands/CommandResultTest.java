package seedu.internhub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internhub.model.Model.PREDICATE_SHOW_ALL_PERSONS;
import static seedu.internhub.testutil.TypicalPersons.ALICE;
import static seedu.internhub.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import javafx.collections.transformation.FilteredList;
import seedu.internhub.model.Model;
import seedu.internhub.model.ModelManager;
import seedu.internhub.model.UserPrefs;

public class CommandResultTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void equals() {

        // constructor 1: CommandResult(String feedback)
        CommandResult commandResult = new CommandResult("feedback");
        // constructor 2: CommandResult(String feedback, Person viewPerson)
        CommandResult commandResultViewPerson = new CommandResult("feedback", ALICE);
        // constructor 3: CommandResult(String feedback, FilteredList<Person> viewList)
        CommandResult commandResultViewList = new CommandResult("feedback",
                new FilteredList<>(model.getFilteredPersonList(), PREDICATE_SHOW_ALL_PERSONS));

        // same values -> returns true
        assertTrue(commandResult.equals(new CommandResult("feedback")));
        assertTrue(commandResult.equals(new CommandResult("feedback", false, false)));

        assertTrue(commandResultViewPerson.equals(new CommandResult("feedback", ALICE)));

        assertTrue(commandResultViewList.equals(new CommandResult("feedback",
                new FilteredList<>(model.getFilteredPersonList(), PREDICATE_SHOW_ALL_PERSONS))));

        // same object -> returns true
        assertTrue(commandResult.equals(commandResult));

        assertTrue(commandResultViewPerson.equals(commandResultViewPerson));
        assertTrue(commandResultViewPerson.getViewPerson().equals(ALICE));

        assertTrue(commandResultViewList.equals(commandResultViewList));
        assertTrue(commandResultViewList.getViewList().equals(
                new FilteredList<>(model.getFilteredPersonList(), PREDICATE_SHOW_ALL_PERSONS)));

        // null -> returns false
        assertFalse(commandResult.equals(null));

        // different types -> returns false
        assertFalse(commandResult.equals(0.5f));

        // different feedbackToUser value -> returns false
        assertFalse(commandResult.equals(new CommandResult("different")));

        // different showHelp value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", true, false)));

        // different exit value -> returns false
        assertFalse(commandResult.equals(new CommandResult("feedback", false, true)));
    }

    @Test
    public void hashcode() {
        CommandResult commandResult = new CommandResult("feedback");

        // same values -> returns same hashcode
        assertEquals(commandResult.hashCode(), new CommandResult("feedback").hashCode());

        // different feedbackToUser value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("different").hashCode());

        // different showHelp value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", true, false).hashCode());

        // different exit value -> returns different hashcode
        assertNotEquals(commandResult.hashCode(), new CommandResult("feedback", false, true).hashCode());
    }

    @Test
    public void toStringMethod() {
        CommandResult commandResult = new CommandResult("feedback");
        String expected = CommandResult.class.getCanonicalName() + "{feedbackToUser="
                + commandResult.getFeedbackToUser() + ", showHelp=" + commandResult.isShowHelp()
                + ", exit=" + commandResult.isExit() + "}";
        assertEquals(expected, commandResult.toString());
    }
}


