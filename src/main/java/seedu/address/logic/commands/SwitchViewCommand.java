package seedu.address.logic.commands;
import seedu.address.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class SwitchViewCommand extends Command {

    public static final String COMMAND_WORD = "switchView";

    public static final String MESSAGE_SUCCESS = "Successfully switched view";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }
}
