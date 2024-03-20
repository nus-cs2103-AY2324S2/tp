package seedu.realodex.logic.commands;

import static seedu.realodex.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.realodex.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.realodex.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.realodex.testutil.TypicalPersons.getTypicalRealodex;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.realodex.model.Model;
import seedu.realodex.model.ModelManager;
import seedu.realodex.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRealodex(), new UserPrefs());
        expectedModel = new ModelManager(model.getRealodex(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_PERSON);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
