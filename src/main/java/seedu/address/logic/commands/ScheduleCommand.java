package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_NUSID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NUSID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;

import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.NusId;
import seedu.address.model.person.Person;
import seedu.address.model.person.Remark;
import seedu.address.model.person.Schedule;

/**
 * Adds a person to the address book.
 */
public class ScheduleCommand extends Command {

    public static final String COMMAND_WORD = "schedule";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Schedule a meeting with a student. \n"
            + "Note: SCHEDULE must be present if REMARK is present. \n"
            + "Parameters: "
            + PREFIX_NUSID + "NUSID "
            + "[" + PREFIX_SCHEDULE + "SCHEDULE] "
            + "[" + PREFIX_REMARK + "REMARK] \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NUSID + "E1234567 "
            + PREFIX_SCHEDULE + "01-12-2021 "
            + PREFIX_REMARK + "Consultation at 3pm at ComSci 1";

    public static final String MESSAGE_ADD_SUCCESS = "New schedule added with person: %1$s";
    public static final String MESSAGE_DELETE_SUCCESS = "Removed schedule from person: %1$s";

    private final NusId nusId;
    private final Schedule schedule;
    private final Remark remark;


    /**
     * Creates a ScheduleCommand to schedule a meeting with Person with {@code NusId}
     */
    public ScheduleCommand(NusId nusId, Schedule schedule, Remark remark) {
        requireAllNonNull(nusId, schedule, remark);
        this.nusId = nusId;
        this.schedule = schedule;
        this.remark = remark;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Person> lastShownList = model.getFilteredPersonList();
        Person personToEdit = lastShownList.stream().filter(person -> person.getNusId().equals(nusId))
                .findFirst().orElse(null);

        if (personToEdit == null) {
            throw new CommandException(MESSAGE_UNKNOWN_NUSID);
        }

        Person editedPerson = new Person(personToEdit.getNusId(), personToEdit.getName(), personToEdit.getPhone(),
                personToEdit.getEmail(), personToEdit.getTag(), personToEdit.getGroups(), schedule, remark);

        model.setPerson(personToEdit, editedPerson);

        return new CommandResult(generateSuccessMessage(editedPerson));
    }

    private String generateSuccessMessage(Person editedPerson) {
        String message = !schedule.date.isEmpty() ? MESSAGE_ADD_SUCCESS : MESSAGE_DELETE_SUCCESS;
        return String.format(message, Messages.format(editedPerson));
    }
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ScheduleCommand)) {
            return false;
        }

        ScheduleCommand otherScheduleCommand = (ScheduleCommand) other;
        return nusId.equals(otherScheduleCommand.nusId)
                && schedule.equals(otherScheduleCommand.schedule)
                && remark.equals(otherScheduleCommand.remark);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("nusId", nusId)
                .add("schedule", schedule)
                .add("remark", remark)
                .toString();
    }
}
