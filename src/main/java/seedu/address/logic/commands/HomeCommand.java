package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class HomeCommand extends Command {
    public static final String COMMAND_WORD = "home";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Returns our user back to the landing page "
            + "with all the client showed\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "You are back to the home page.";

    public HomeCommand() {};

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.getFilteredPersonList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
