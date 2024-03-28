package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertOverallCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertOverallCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_LIST_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFilterxed_showsEverything() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);
        assertOverallCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_LIST_SUCCESS, expectedModel);
    }
}
