package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.LastContactCommand.SORT_COMPARATOR;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.IsLastContactedPredicate;

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
}
