package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_END;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCHEDULE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_START;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.schedule.Schedule;
import seedu.address.model.tag.Tag;

/**
 * Adds a schedule to the address book.
 */
public class AddSchedCommand extends Command {

    public static final String COMMAND_WORD = "addSched";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a schedule to person(s) in address book. "
            + "Parameters: "
            + "INDEX(S) (must be positive integer) "
            + PREFIX_SCHEDULE + "SCHEDULE "
            + PREFIX_START + "START DATETIME (yyyy-MM-dd HH:mm)"
            + PREFIX_END + "END DATETIME (yyyy-MM-dd HH:mm)"
            + "Example: " + COMMAND_WORD + " "
            + "1, 2"
            + PREFIX_SCHEDULE + "CS2103 weekly meeting "
            + PREFIX_START + "24/02/2024 15:00"
            + PREFIX_END + "24/02/2024 17:00";

    public static final String MESSAGE_SUCCESS = "New schedule added: %1$s";

    private final ArrayList<Index> targetIndexes;

    private final Schedule schedule;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddSchedCommand(ArrayList<Index> targetIndexes, Schedule schedule) {
        requireNonNull(schedule);
        this.targetIndexes = targetIndexes;
        this.schedule = schedule;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        ArrayList<Person> participants = new ArrayList<>();
        for (Index index : targetIndexes) {
            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }
            participants.add(lastShownList.get(index.getZeroBased()));
        }

        ArrayList<Person> addedParticipants = schedule.addParticipants(participants);

        model.addSchedule(schedule);
        for (Person p: addedParticipants) {
            model.setPerson(p, editPersonSchedule(p));
        }

        return new CommandResult(generateSuccessMessage());
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private Person editPersonSchedule(Person personToEdit) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        Address address = personToEdit.getAddress();
        Set<Tag> tags = personToEdit.getTags();
        AddScheduleToListDescriptor descriptor = new AddScheduleToListDescriptor(personToEdit.getSchedules(),
                this.schedule);
        ArrayList<Schedule> updatedSchedules = descriptor.getSchedules();
        return new Person(name, phone, email, address, tags, updatedSchedules);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddSchedCommand)) {
            return false;
        }

        AddSchedCommand asc = (AddSchedCommand) other;
        return targetIndexes.equals(asc.targetIndexes)
                && schedule.equals(asc.schedule);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("person indexes", targetIndexes)
                .add("Schedule", schedule)
                .toString();
    }

    /**
     * Generates a command execution success message based on whether
     * the remark is added to or removed from
     * {@code personToEdit}.
     */
    private String generateSuccessMessage() {
        return String.format(MESSAGE_SUCCESS, schedule);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class AddScheduleToListDescriptor {
        private ArrayList<Schedule> schedules;

        public AddScheduleToListDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public AddScheduleToListDescriptor(ArrayList<Schedule> oldSchedules, Schedule s) {
            this.schedules = oldSchedules;
            addSchedule(s);
        }

        public void addSchedule(Schedule s) {
            this.schedules.add(s);
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public ArrayList<Schedule> getSchedules() {
            return this.schedules;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditCommand.EditPersonDescriptor)) {
                return false;
            }

            AddScheduleToListDescriptor otherAddScheduleToListDescriptor = (AddScheduleToListDescriptor) other;
            return Objects.equals(schedules, otherAddScheduleToListDescriptor.schedules);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("schedule", schedules)
                    .toString();
        }
    }
}

