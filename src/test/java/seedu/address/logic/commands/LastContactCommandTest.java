package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LastContactCommand.SORT_COMPARATOR;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IsLastContactedPredicate;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) and unit tests for LastContactCommand.
 */
public class LastContactCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_lastContactListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new LastContactCommand(new IsLastContactedPredicate()), model,
                LastContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_lastContactListIsFiltered_showsSortedAndFiltered() {
        model.updateFilteredPersonList(new IsLastContactedPredicate());
        model.sortFilteredPersonList(SORT_COMPARATOR);
        expectedModel.updateFilteredPersonList(new IsLastContactedPredicate());
        expectedModel.sortFilteredPersonList(SORT_COMPARATOR);

        assertCommandSuccess(new LastContactCommand(new IsLastContactedPredicate()), model,
                LastContactCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        IsLastContactedPredicate firstPredicate = new IsLastContactedPredicate();
        IsLastContactedPredicate secondPredicate = new IsLastContactedPredicate() {
            @Override
            public boolean test(Person person) {
                return false;
            }
        };

        LastContactCommand lastContactFirstCommand = new LastContactCommand(firstPredicate);
        LastContactCommand lastContactSecondCommand = new LastContactCommand(secondPredicate);

        // same object -> returns true
        assertTrue(lastContactFirstCommand.equals(lastContactFirstCommand));

        // same values -> returns true
        LastContactCommand lastContactFirstCommandCopy = new LastContactCommand(firstPredicate);
        assertTrue(lastContactFirstCommand.equals(lastContactFirstCommandCopy));

        // different types -> returns false
        assertFalse(lastContactFirstCommand.equals(1));

        // null -> returns false
        assertFalse(lastContactFirstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(lastContactFirstCommand.equals(lastContactSecondCommand));
    }
}
