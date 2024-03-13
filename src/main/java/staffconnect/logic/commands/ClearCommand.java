package staffconnect.logic.commands;

import static java.util.Objects.requireNonNull;

import staffconnect.model.Model;
import staffconnect.model.StaffBook;

/**
 * Clears the staff book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Staff book has been cleared!";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setStaffBook(new StaffBook());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
