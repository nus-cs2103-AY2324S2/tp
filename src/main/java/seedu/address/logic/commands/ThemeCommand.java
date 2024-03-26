package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class ThemeCommand extends Command{
    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_SUCCESS = "Successfully changed the theme";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        CommandResult result = new CommandResult(MESSAGE_SUCCESS);
        result.setThemeCommand();
        return result;
    }
}
