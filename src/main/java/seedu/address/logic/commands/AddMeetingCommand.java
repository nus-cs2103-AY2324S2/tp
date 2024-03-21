package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CLIENT_INDEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import java.time.LocalDateTime;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.person.Person;

/**
 * Represents a command to add a meeting associated with a specific client in the address book.
 * This command adds a new meeting to the address book, ensuring that no duplicate meetings are added.
 * A meeting is considered a duplicate if it has the same client, date, and description as an existing meeting.
 */
public class AddMeetingCommand extends Command {
    public static final String COMMAND_WORD = "addMeeting";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a meeting to the "
            + "client identified by the index number. \n"
            + "Parameters: client/ CLIENT_INDEX dt/ DATE_TIME /d DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CLIENT_INDEX + "1 "
            + PREFIX_DATETIME + "02-01-2024 12:00 "
            + PREFIX_DESCRIPTION + "sign life plan";


    public static final String MESSAGE_SUCCESS = "New meeting added: %1$s";
    public static final String MESSAGE_DUPLICATE_MEETING = "This meeting already exists in the address book";

    private final Index clientIndex;
    private final LocalDateTime dateTime;
    private final String description;

    /**
     * Creates an {@code AddMeetingCommand} to add the specified {@code Meeting}.
     *
     * @param dateTime The date and time of the meeting.
     * @param description A description of the meeting.
     * @param clientIndex The index of the client in the filtered person list to whom the meeting is to be added.
     * @throws NullPointerException if any of the input parameters are null.
     */

    public AddMeetingCommand(LocalDateTime dateTime, String description, Index clientIndex) {
        if (dateTime == null || description == null || clientIndex == null) {
            throw new NullPointerException();
        }
        this.dateTime = dateTime;
        this.description = description;
        this.clientIndex = clientIndex;
    }

    /**
     * Executes the AddMeeting command to add a new meeting associated with a client in the address book.
     * <p>
     * The method retrieves the client based on the index provided, creates a new meeting with the specified date, time,
     * and description, and then adds this meeting to the model if it does not already exist to ensure uniqueness.
     * <p>
     * If the specified client index is invalid or the meeting already exists, it throws a CommandException.
     *
     * @param model The model in which the meeting will be added.
     * @return A CommandResult object containing the success message.
     * @throws CommandException If the client index is invalid or the meeting already exists in the model.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (clientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person client = lastShownList.get(clientIndex.getZeroBased());
        Meeting meetingToAdd = new Meeting(description, dateTime, client);

        if (model.hasMeeting(meetingToAdd) && client.hasExistingMeeting(meetingToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_MEETING);
        }
        model.addMeeting(meetingToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(meetingToAdd)));
    }

    /**
     * Checks if another object is equal to this AddMeetingCommand instance.
     * <p>
     * The equality check is based on whether the other object is an instance of AddMeetingCommand
     * and whether the meetings to be added are the same in terms of client, date, time, and description.
     * This ensures that two AddMeetingCommand objects are considered equal if they are attempting
     * to add the same meeting.
     * <p>
     * This method is crucial for command comparisons, especially when testing or when the application
     * logic requires comparing different commands.
     *
     * @param other The object to compare this AddMeetingCommand against.
     * @return true if the given object represents an AddMeetingCommand equivalent to this instance, false otherwise.
     */

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddMeetingCommand)) {
            return false;
        }

        AddMeetingCommand otherCommand = (AddMeetingCommand) other;
        return dateTime.equals(otherCommand.dateTime)
                && description.equals(otherCommand.description)
                && clientIndex.equals(otherCommand.clientIndex);
    }
}
