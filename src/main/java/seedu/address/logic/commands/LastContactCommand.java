package seedu.address.logic.commands;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class LastContactCommand extends Command {

    public static final String COMMAND_WORD = "lastcontact";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a last contacted tag to the "
            + "client identified.\n"
            + "Existing date and time tagged will be overwritten by the input.\n"
            + "Parameters: NAME (case in-sensitive), DATETIME (DD-MM-YYYY HHMM) format.";
    public static final String MESSAGE_ARGUMENTS = "Name: %2$s, DateTime: %2$s";
    private final String name;
    private final String dateTime;

    /**
     * Constructor the Last contact command
     * @param name of the person in the contact list
     */
    public LastContactCommand(String name, String dateTime) {
        requireAllNonNull(name);

        this.name = name;
        this.dateTime = dateTime;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        throw new CommandException(String.format(MESSAGE_ARGUMENTS, this.name, this.dateTime));
    }
}
