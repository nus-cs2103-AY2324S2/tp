package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Export the address book data as a CSV file.
 */
public class ExportCommand extends Command {

    public static final String COMMAND_WORD = "export";

    public static final String MESSAGE_SUCCESS = "Success! Your contact has been exported as ";
    public static final String MESSAGE_FAILURE_EMPTY_LIST = "Failed to export contacts. The contact list is empty.";
    public static final String MESSAGE_FAILURE_FILE_WRITE = "Failed to export contacts due to file write error.";

    public static final String MESSAGE_USAGE = "Usage: export [FILENAME]\n"
            + "Exports the contact list as a CSV file with the specified filename.\n"
            + "If no filename is provided, the default filename 'contacts.csv' will be used.\n"
            + "Example: export my_contacts.csv";

    public final String filename;

    /**
     * Creates an ExportCommand to export contact as CV file
     */
    public ExportCommand() {
        this.filename = "contacts.csv";
    }

    public ExportCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.getFilteredPersonList().isEmpty()) {
            throw new CommandException(MESSAGE_FAILURE_EMPTY_LIST);
        }

        boolean isSuccessful = model.exportCsv(filename);

        if (!isSuccessful) {
            throw new CommandException(MESSAGE_FAILURE_FILE_WRITE);
        }

        return new CommandResult(MESSAGE_SUCCESS + filename);

    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof seedu.address.logic.commands.ExportCommand)) {
            return false;
        }

        seedu.address.logic.commands.ExportCommand otherAddCommand = (seedu.address.logic.commands.ExportCommand) other;
        return filename.equals(otherAddCommand.filename);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("filename", filename)
                .toString();
    }
}

