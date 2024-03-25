package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.storage.ImportManager;

/**
 * Represents a command that imports a csv file into Pedagogue Pages.
 */
public class ImportCommand extends Command {

    public static final String COMMAND_WORD = "import";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Imports a CSV file to Pedagogue Pages.";

    public static final String MESSAGE_SUCCESS = "CSV file successfully imported.";

    public static final String MESSAGE_FAILURE = "Unable to import CSV file.";

    private ImportManager importManager;

    private Path pathToImportTo;

    private Path pathToImportFrom;

    /**
     * Returns an Import Command object with the specified path.
     * @param pathToImportFrom {@code Path} which the command will import the csv file from.
     * @param pathToImportTo {@code Path} which the command will import the csv file to.
     */
    public ImportCommand(Path pathToImportFrom, Path pathToImportTo, ImportManager importManager) {
        this.pathToImportFrom = pathToImportFrom;
        this.pathToImportTo = pathToImportTo;
        this.importManager = importManager;
    }

    /**
     * Getter for path to import to
     *
     * @return String representation of path to import to
     */
    public String getPathToImportTo() {
        return this.pathToImportTo.toString();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        try {
            this.importManager.importCsvFileAndConvertToJsonFile();
        } catch (IOException e) {
            return new CommandResult(e + MESSAGE_FAILURE);
        }
        CommandResult result = new CommandResult(MESSAGE_SUCCESS);
        result.setChangeDataSource();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ImportCommand)) {
            return false;
        }

        ImportCommand otherImportCommand = (ImportCommand) other;
        return this.pathToImportTo.toString().equals(otherImportCommand.pathToImportTo.toString())
                && this.pathToImportFrom.toString().equals(otherImportCommand.pathToImportFrom.toString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Importing file from: ", this.pathToImportFrom.toString())
                .add("Importing file to: ", this.pathToImportTo.toString())
                .toString();
    }
}
