package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.NetConnect;
import seedu.address.model.person.Person;

public class ExportCommand extends Command {

    public static final String MESSAGE_SUCCESS = "Success! Your contact has been exported as ((filename.csv)) ";

    
    private final String filename;

    /**
     * Creates an ExportCommand to export contact as CV file
     */
    public ExportCommand() {
        this.filename = "Contact";
    }

    public ExportCommand(String filename) {
        this.filename = filename;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.exportCSV(filename);
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

