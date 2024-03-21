package seedu.address.logic.commands;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Description;


public class AddDescriptionCommand extends Command {

    public static final String COMMAND_WORD = "description";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Add description. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_DESCRIPTION + "DESCRIPTION";

    public static final String MESSAGE_SUCCESS = "New description added: %1$s";
    public static final String MESSAGE_MISSING = "Description not added yet";

    public static final String MESSAGE_ARGUMENTS = "Index: %1$d, Description: %2$s";

    private final Description description;
    private final Index index;
    /**
     * Creates an AddAttendanceRecord to add the specified {@code date}
     */
    public AddDescriptionCommand(Index index, Description description) {
        requireAllNonNull(description);

        this.index = index;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        return new CommandResult(String.format(MESSAGE_ARGUMENTS, index.getOneBased(), description));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddDescriptionCommand)) {
            return false;
        }

        AddDescriptionCommand otherAddDescriptionCommand = (AddDescriptionCommand) other;
        return description.equals(otherAddDescriptionCommand.description);
    }
}
