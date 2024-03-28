package scrolls.elder.logic.commands;

import static java.util.Objects.requireNonNull;
import static scrolls.elder.logic.parser.CliSyntax.PREFIX_DURATION;
import static scrolls.elder.logic.parser.CliSyntax.PREFIX_REMARKS;
import static scrolls.elder.logic.parser.CliSyntax.PREFIX_START;

import java.util.Date;
import java.util.List;

import scrolls.elder.commons.core.index.Index;
import scrolls.elder.commons.util.ToStringBuilder;
import scrolls.elder.logic.Messages;
import scrolls.elder.logic.commands.exceptions.CommandException;
import scrolls.elder.model.LogStore;
import scrolls.elder.model.Model;
import scrolls.elder.model.PersonStore;
import scrolls.elder.model.log.Log;
import scrolls.elder.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class LogAddCommand extends Command {

    public static final String COMMAND_WORD = "logadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a log to the address book. "
        + "Parameters: INDEX1 INDEX2 "
        + PREFIX_START + "START_DATE (yyyy-MM-dd) "
        + PREFIX_DURATION + "DURATION (in hours) "
        + PREFIX_REMARKS + "REMARKS "
        + "Example: " + COMMAND_WORD + " 1 2 "
        + PREFIX_START + "2021-03-01 "
        + PREFIX_DURATION + "2 "
        + PREFIX_REMARKS + "was a good session";

    public static final String MESSAGE_SUCCESS = "New log added!";

    /**
     * Contains data for the log to be added.
     * Does not contain the final log ID.
     */
    private final Index volunteerIndex;
    private final Index befriendeeIndex;
    private final int duration;
    private final Date startDate;
    private final String remarks;

    /**
     * Creates an LogAddCommand to add the specified {@code Log}
     */
    public LogAddCommand(Index volunteerIndex, Index befriendeeIndex, int duration, Date startDate, String remarks) {
        this.volunteerIndex = volunteerIndex;
        this.befriendeeIndex = befriendeeIndex;
        this.duration = duration;
        this.startDate = startDate;
        this.remarks = remarks;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        PersonStore personStore = model.getMutableDatastore().getMutablePersonStore();
        LogStore logStore = model.getMutableDatastore().getMutableLogStore();

        List<Person> lastShownBList = personStore.getFilteredBefriendeeList();
        List<Person> lastShownVList = personStore.getFilteredVolunteerList();

        if (befriendeeIndex.getZeroBased() >= lastShownBList.size()
            || volunteerIndex.getZeroBased() >= lastShownVList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person befriendee = lastShownBList.get(befriendeeIndex.getZeroBased());
        Person volunteer = lastShownVList.get(volunteerIndex.getZeroBased());

        Log toAdd =
            new Log(model.getDatastore(), volunteer.getPersonId(), befriendee.getPersonId(), duration, startDate,
                remarks);

        logStore.addLog(toAdd);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LogAddCommand)) {
            return false;
        }

        LogAddCommand otherAddCommand = (LogAddCommand) other;
        return otherAddCommand.volunteerIndex == volunteerIndex
            && otherAddCommand.befriendeeIndex == befriendeeIndex
            && otherAddCommand.duration == duration
            && otherAddCommand.startDate.equals(startDate)
            && otherAddCommand.remarks.equals(remarks);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("volunteerId", volunteerIndex)
            .add("befriendeeId", befriendeeIndex)
            .add("duration", duration)
            .add("startDate", startDate)
            .add("remarks", remarks)
            .toString();
    }
}
