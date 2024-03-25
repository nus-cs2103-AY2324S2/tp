package seedu.edulink.logic.commands;

import seedu.edulink.commons.util.ToStringBuilder;
import seedu.edulink.logic.commands.exceptions.CommandException;
import seedu.edulink.model.Model;

public class UndoCommand extends Command {
    private static final String COMMAND_WORD = "undo";
    public static final String MESSAGE_USAGE = "Usage: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Undo Command done Successfully";
    public static final String MESSAGE_FAILURE = "Can't Undo , No Previous History Available";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult("Success");
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        return other instanceof UndoCommand;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .toString();
    }
}
