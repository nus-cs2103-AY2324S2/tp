package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FILENAME;

import java.io.File;
import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Imports contacts from a file into the contact manager.
 */
public class ImportCommand extends Command {
    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_SUCCESS = "Contacts from file imported";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a datafile into the contact manager."
            + "Parameters: "
            + PREFIX_FILENAME + "FILENAME_1 "
            + "[" + PREFIX_FILENAME + "FILENAME_2 "
            + "[" + PREFIX_FILENAME + "FILENAME_3 "
            + "...";

    Set<File> files;
    public ImportCommand(Set<File> fileSet) {
        requireNonNull(fileSet);
        files = fileSet;
    }
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("files", files)
                .toString();
    }
}
