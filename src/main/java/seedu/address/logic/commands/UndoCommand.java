package seedu.address.logic.commands;

import seedu.address.model.Model;
import seedu.address.model.exceptions.AddressBookException;

/**
 * Undoes the latest command
 */
public class UndoCommand extends Command {

    public static final String COMMAND_WORD = "undo";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Undo the latest command.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Undid the latest command.";

    @Override
    public String execute(Model model) {
        try {
            model.undo();
            return MESSAGE_SUCCESS;
        } catch (AddressBookException e) {
            return e.getMessage();
        }
    }
}
