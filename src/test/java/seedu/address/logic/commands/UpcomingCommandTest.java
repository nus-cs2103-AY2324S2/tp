package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.HasUpcomingPredicate;

/**
 * Contains integration tests (interaction with the Model) and unit tests for UpcomingCommand.
 */
public class UpcomingCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_upcomingListIsNotFiltered_showsSameList() {
        assertCommandSuccess(new UpcomingCommand(), model,
                UpcomingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_upcomingListIsFiltered_showsSortedAndFiltered() {
        UpcomingCommand upcomingCommand = new UpcomingCommand();
        UpcomingCommand.UpcomingComparator comparator = upcomingCommand.new UpcomingComparator();
        model.updateFilteredPersonList(new HasUpcomingPredicate());
        model.sortFilteredPersonList(comparator);
        expectedModel.updateFilteredPersonList(new HasUpcomingPredicate());
        expectedModel.sortFilteredPersonList(comparator);

        assertCommandSuccess(new UpcomingCommand(), model,
                UpcomingCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void equals() {
        UpcomingCommand upcomingFirstCommand = new UpcomingCommand();

        // same object -> returns true
        assertTrue(upcomingFirstCommand.equals(upcomingFirstCommand));

        // different types -> returns false
        assertFalse(upcomingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(upcomingFirstCommand.equals(null));
    }

    // Add more tests as needed
}
