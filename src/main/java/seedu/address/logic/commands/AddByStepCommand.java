package seedu.address.logic.commands;

import seedu.address.model.Model;

public class AddByStepCommand extends Command {

    public static final String COMMAND_WORD = "addbystep";

    public static final String MESSAGE_SUCCESS = "addbystep";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS,false,false,true);
    }
}
