package seedu.edulink.logic.commands;

import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.edulink.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import seedu.edulink.logic.Messages;
import seedu.edulink.model.Model;
import seedu.edulink.model.ModelManager;
import seedu.edulink.testutil.TypicalPersons;

public class UndoCommandTest {
    private Model model = new ModelManager();

    @Test
    public void undo_command_failure() {
        assertCommandFailure(new UndoCommand(), model, Messages.MESSAGE_NO_HISTORY_FOUND);
    }

    @Test
    public void add_success() {
        model.addPerson(TypicalPersons.ALICE);
        Model expected = new ModelManager();
        assertCommandSuccess(new UndoCommand(), model, new CommandResult(UndoCommand.MESSAGE_SUCCESS), expected);
    }

    @Test
    public void edit_success() {
        model.addPerson(TypicalPersons.ALICE);
        model.setPerson(TypicalPersons.ALICE, TypicalPersons.BOB);
        Model expected = new ModelManager();
        expected.addPerson(TypicalPersons.ALICE);
        assertCommandSuccess(new UndoCommand(), model, new CommandResult(UndoCommand.MESSAGE_SUCCESS), expected);
    }

    @Test
    public void delete_success() {
        model.addPerson(TypicalPersons.ALICE);
        model.deletePerson(TypicalPersons.ALICE);
        Model expected = new ModelManager();
        expected.addPerson(TypicalPersons.ALICE);
        assertCommandSuccess(new UndoCommand(), model, new CommandResult(UndoCommand.MESSAGE_SUCCESS), expected);
    }
}
