package seedu.address.logic.commands;

import seedu.address.model.Model;

/**
 * Toggles the display (shows/hides depending on whether the display is already being displayed or not).
 */
public class ToggleDisplayCommand extends Command {

    public static final String COMMAND_WORD = "$";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Toggles the display on/off. ";
    public static final String MESSAGE_TOGGLE_ACKNOWLEDGEMENT = "Display toggled";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_TOGGLE_ACKNOWLEDGEMENT, false, false, true);
    }
}
