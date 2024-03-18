package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

class RedoCommandTest {
    private Model model;

    @BeforeEach
    void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    void execute_newAddressBook_failure() {
        RedoCommand redoCommand = new RedoCommand();
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_CANNOT_REDO);
    }

    @Test
    void execute_noRedoableStates_failure() throws Exception {
        RedoCommand redoCommand = new RedoCommand();
        Person validPerson = new PersonBuilder().build();
        CommandResult commandResult = new AddCommand(validPerson).execute(model);
        assertCommandFailure(redoCommand, model, RedoCommand.MESSAGE_CANNOT_REDO);
    }

    @Test
    void execute_redoableStatesPresent_success() throws Exception {
        RedoCommand redoCommand = new RedoCommand();
        Person validPerson = new PersonBuilder().build();
        CommandResult addCommandResult = new AddCommand(validPerson).execute(model);
        Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        CommandResult undoCommandResult = new UndoCommand().execute(model);
        assertCommandSuccess(redoCommand, model, RedoCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
