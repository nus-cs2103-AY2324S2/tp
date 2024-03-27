package seedu.address.logic.commands;
import seedu.address.model.Model;

/**
 * Allows the user to switch between the Overall View and Day View.
 */
public class SwitchViewCommand extends Command {

    public static final String COMMAND_WORD = "switchView";

    public static final String MESSAGE_SWITCH_VIEW_SUCCESS = "Successfully switched view";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SWITCH_VIEW_SUCCESS, false, false, false, true);
    }
}
