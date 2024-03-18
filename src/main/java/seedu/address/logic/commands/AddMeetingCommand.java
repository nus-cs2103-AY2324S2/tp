package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a meeting to the address book under the client's account.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the address book. "
            + "Parameters: "
            + "DATE "
            + "TIME "
            + "LOCATION "
            + "DESCRIPTION\n"
            + "Example: " + COMMAND_WORD + " "
            + "2020-12-25 "
            + "14:00 "
            + "NUS "
            + "CS2103T meeting";

    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book";

    private final Meeting toAdd;

    /**
     * Creates an AddMeetingCommand to add the specified {@code Meeting}
     */
    public AddMeetingCommand(Meeting meeting) {
        requireNonNull(meeting);
        toAdd = meeting;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasMeeting(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }

        model.addMeeting(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS,  Messages.format(toAdd)));
    }
}
