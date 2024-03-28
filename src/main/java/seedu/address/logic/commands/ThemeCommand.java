package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_THEME;

import seedu.address.commons.core.Theme;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Command to change the theme of the application.
 */
public class ThemeCommand extends Command {
    public static final String COMMAND_WORD = "theme";

    public static final String MESSAGE_SUCCESS = "Theme updated!";

    public static final String MESSAGE_USAGE = ThemeCommand.COMMAND_WORD
            + " "
            + PREFIX_THEME + "{theme}";
    private final Theme toChange;
    /**
     * Creates a ThemeCommand to change {@code Theme}
     * @param theme
     */
    public ThemeCommand(Theme theme) {
        requireNonNull(theme);
        this.toChange = theme;
    }

    /**
     * Executes the theme change command.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A {@code CommandResult} representing the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setTheme(toChange);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toChange), false, false, true);
    }
}
