package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Change the data source of Pedagogue Pages.
 */
public class ChangeDataSourceCommand extends Command {

    public static final String COMMAND_WORD = "cd";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Change teh data source to another file.\n"
            + "Parameters: File path\n"
            + "Example: " + COMMAND_WORD + " data\\addressbook.json";

    public static final String MESSAGE_SUCCESS = "Successfully changed the file path";

    private final Path newPath;

    public ChangeDataSourceCommand(Path newPath) {
        this.newPath = newPath;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.setAddressBookFilePath(newPath);
        CommandResult result = new CommandResult(MESSAGE_SUCCESS);
        result.setChangeDataSource();
        return result;
    }

    public Path getNewPath() {
        return newPath;
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ChangeDataSourceCommand)) {
            return false;
        }

        ChangeDataSourceCommand otherCommand = (ChangeDataSourceCommand) other;
        return newPath.equals(otherCommand.newPath);
    }

}
