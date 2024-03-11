package seedu.address.logic.commands;

import seedu.address.model.Model;

public class EchoCommand extends Command {
    public static final String COMMAND_WORD = "echo";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Echoes the input string.\n"
            + "Parameters: TEXT\n"
            + "Example: " + COMMAND_WORD + " hello";

    private final String text;

    public EchoCommand(String text) {
        this.text = text;
    }

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(text);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EchoCommand // instanceof handles nulls
                && text.equals(((EchoCommand) other).text));
    }
}
