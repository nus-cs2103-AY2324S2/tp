package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.Model;
import seedu.address.storage.ImportManager;

/**
 * Represents a command that migrates a csv file into the currently opened list in Pedagogue Pages.
 */
public class MigrateCommand extends Command {

    public static final String COMMAND_WORD = "migrate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the contents of a CSV to the current list.";

    public static final String MESSAGE_SUCCESS = "CSV file successfully added.";

    public static final String MESSAGE_FAILURE = "Unable to add CSV file.";

    private Path pathToImportFrom;
    private Path pathToImportTo;

    private ImportManager importManager;

    /**
     * Returns a MigrateCommand with the specified path.
     */
    public MigrateCommand(Path pathToImportFrom, ImportManager importManager) {
        this.pathToImportFrom = pathToImportFrom;
        this.importManager = importManager;
    }

    /**
     * Getter for path to import from
     *
     * @return String representation of path to import from
     */
    public String getPathToImportFrom() {
        return this.pathToImportFrom.toString();
    }

    /**
     * Getter for path to import to
     * @return String representation of path to import to
     */
    public String getPathToImportTo() {
        return this.pathToImportTo.toString();
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Path pathToImportTo = model.getAddressBookFilePath();
        System.out.println(pathToImportTo.toString());
        this.importManager.setPathToImportTo(pathToImportTo);
        try {
            this.importManager.importCsvFileAndAddToJsonFile();
        } catch (IOException e) {
            return new CommandResult(MESSAGE_FAILURE);
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }


    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MigrateCommand)) {
            return false;
        }

        MigrateCommand otherMigrateCommand = (MigrateCommand) other;
        return this.pathToImportFrom.toString().equals(otherMigrateCommand.pathToImportFrom.toString());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("Migrating file from: ", getPathToImportFrom())
                .toString();
    }
}
